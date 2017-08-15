package com.supply.pickupplan.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.JeecgSqlUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.supply.common.Identify;
import com.supply.common.SupplyConstants;
import com.supply.deliveryplan.entity.PoDeliveryplanEntity;
import com.supply.deliveryplanb.entity.PoDeliveryplanBEntity;
import com.supply.pickupplan.entity.SoPickupplanEntity;
import com.supply.pickupplan.page.SoPickupplanPage;
import com.supply.pickupplan.service.SoPickupplanServiceI;
import com.supply.pickupplan.service.SoPickupplanU8ServiceI;
import com.supply.pickupplanb.entity.SoPickupplanBEntity;
import com.supply.vehicle.entity.VehicleEntity;

/**
 * @Title: Controller
 * @Description: 提货计划主表
 * @author onlineGenerator
 * @date 2016-07-06 13:11:29
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/soPickupplanController")
public class SoPickupplanController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(SoPickupplanController.class);

    @Autowired
    private SoPickupplanServiceI soPickupplanService;
    @Autowired
    private SoPickupplanU8ServiceI soPickupplanU8Service;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private CommonService commonService;

    /**
     * 提货计划主表列表 页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
	return new ModelAndView("com/supply/pickupplan/soPickupplanList");
    }

    /**
     * easyui AJAX请求数据
     * 
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(SoPickupplanEntity soPickupplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid) {
	CriteriaQuery cq = new CriteriaQuery(SoPickupplanEntity.class, dataGrid);
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		soPickupplan);
	try {
	    // 自定义追加查询条件
	    String query_createDate_begin = request
		    .getParameter("createDate_begin");
	    String query_createDate_end = request
		    .getParameter("createDate_end");
//	    Object createBy = ResourceUtil.getSessionUserName().getUserName();
//	    if (StringUtil.isNotEmpty(createBy)) {
//		cq.eq("createBy", createBy);
//	    }
	    if (StringUtil.isNotEmpty(query_createDate_begin)) {
		cq.ge("createDate", new SimpleDateFormat("yyyy-MM-dd")
			.parse(query_createDate_begin));
	    }
	    if (StringUtil.isNotEmpty(query_createDate_end)) {
		cq.le("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(query_createDate_end+ " 23:59:59"));
	    }
	    String query_expiredate_begin = request
		    .getParameter("expiredate_begin");
	    String query_expiredate_end = request
		    .getParameter("expiredate_end");
	    if (StringUtil.isNotEmpty(query_expiredate_begin)) {
		cq.ge("expiredate", new SimpleDateFormat("yyyy-MM-dd")
			.parse(query_expiredate_begin));
	    }
	    if (StringUtil.isNotEmpty(query_expiredate_end)) {
		cq.le("expiredate", new SimpleDateFormat("yyyy-MM-dd")
			.parse(query_expiredate_end));
	    }
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
	cq.add();
	this.soPickupplanService.getDataGridReturn(cq, true);
	TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除提货计划主表
     * 
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(SoPickupplanEntity soPickupplan,
	    HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	soPickupplan = systemService.getEntity(SoPickupplanEntity.class,
		soPickupplan.getId());
	String message = "提货计划主表删除成功";
	try {
	    if(SupplyConstants.BILLSTATUS_APPROVE.equals(soPickupplan.getBillstatus())){
		 message = "单据已审核，不能进行删除操作！";
		    throw new BusinessException(message);
	    }
	    soPickupplanService.delMain(soPickupplan);
	    systemService.addLog(message, Globals.Log_Type_DEL,
		    Globals.Log_Leavel_INFO);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "提货计划主表删除失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 批量删除提货计划主表
     * 
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	String message = "提货计划主表删除成功";
	try {
	    for (String id : ids.split(",")) {
		SoPickupplanEntity soPickupplan = systemService.getEntity(
			SoPickupplanEntity.class, id);
		soPickupplanService.delMain(soPickupplan);
		systemService.addLog(message, Globals.Log_Type_DEL,
			Globals.Log_Leavel_INFO);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "提货计划主表删除失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 添加提货计划主表
     * 
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(SoPickupplanEntity soPickupplan,
	    SoPickupplanPage soPickupplanPage, HttpServletRequest request) {
	List<SoPickupplanBEntity> soPickupplanBList = soPickupplanPage
		.getSoPickupplanBList();
	AjaxJson j = new AjaxJson();
	String message = "添加成功";
	try {
	    soPickupplan.setPickupplancode(Identify
		    .getIdent(SupplyConstants.THJH));
	    soPickupplanService.addMain(soPickupplan, soPickupplanBList);
	    systemService.addLog(message, Globals.Log_Type_INSERT,
		    Globals.Log_Leavel_INFO);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "提货计划主表添加失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 更新提货计划主表
     * 
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(SoPickupplanEntity soPickupplan,
	    SoPickupplanPage soPickupplanPage, HttpServletRequest request) {
	List<SoPickupplanBEntity> soPickupplanBList = soPickupplanPage
		.getSoPickupplanBList();
	AjaxJson j = new AjaxJson();
	String message = "更新成功";
	try {
	    soPickupplan.setBillstatus(null);
	    soPickupplan.setApproveone(null);
	    soPickupplan.setApproveonename(null);
	    soPickupplan.setApproveonedate(null);
	    soPickupplan.setApproveoneinfo(null);
	    soPickupplan.setApproveonestatus(null);
	    soPickupplan.setApprovetwo(null);
	    soPickupplan.setApprovetwoname(null);
	    soPickupplan.setApprovetwodate(null);
	    soPickupplan.setApprovetwoinfo(null);
	    soPickupplan.setApprovetwostatus(null);
	    soPickupplanService.updateMain(soPickupplan, soPickupplanBList);
	    systemService.addLog(message, Globals.Log_Type_UPDATE,
		    Globals.Log_Leavel_INFO);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "更新提货计划主表失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 提货计划主表新增页面跳转
     * 
     * @return
     * @throws Exception 
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(SoPickupplanEntity soPickupplan,
	    HttpServletRequest req) throws Exception {

	if (StringUtil.isNotEmpty(soPickupplan.getId())) {
	    List<Map<String, Object>> list = soPickupplanU8Service
		    .queryU8SoOrderByOrdercode(soPickupplan.getId());
	    if (list == null) {
		throw new BusinessException("订单数据查询为空！");
	    } else if (list != null && list.size() > 1) {
		throw new BusinessException("订单数据多条，请检查！");
	    } else if (list != null && list.size() == 1) {
		soPickupplan
			.setSoordercode(list.get(0).get("cSOCode") == null ? ""
				: list.get(0).get("cSOCode").toString());// 订单号
		soPickupplan
			.setMaterialcode(list.get(0).get("DPODATE") == null ? ""
				: list.get(0).get("DPODATE").toString());// 订单日期
		soPickupplan
		.setTrancode(list.get(0).get("trancode") == null ? ""
			: list.get(0).get("trancode").toString());// 运输商编码
	soPickupplan
		.setTranname(list.get(0).get("tranname") == null ? ""
			: list.get(0).get("tranname").toString());// 运输商名称
		soPickupplan
			.setMaterialcode(list.get(0).get("cInvCode") == null ? ""
				: list.get(0).get("cInvCode").toString());// 物料编码
		soPickupplan
			.setMaterialname(list.get(0).get("CINVNAME") == null ? ""
				: list.get(0).get("CINVNAME").toString());// 物料名称
		soPickupplan.setSpec(list.get(0).get("CINVSTD") == null ? ""
			: list.get(0).get("CINVSTD").toString());// 规格
		soPickupplan
		.setCustomercode(list.get(0).get("cCusCode") == null ? ""
			: list.get(0).get("cCusCode").toString());// 供应商编码
		soPickupplan
			.setCustomername(list.get(0).get("cCusName") == null ? ""
				: list.get(0).get("cCusName").toString());// 供应商名称
		soPickupplan
			.setSoordernum(list.get(0).get("IQUANTITY") == null ? BigDecimal.ZERO
				: BigDecimal.valueOf(Double.parseDouble(list
					.get(0).get("IQUANTITY").toString())));// 订单数量
		soPickupplan
		.setVnote(list.get(0).get("VNOTE") == null ? ""
			: list.get(0).get("VNOTE").toString());// 备注
		soPickupplan
			.setSurplusnum(list.get(0).get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO
				: BigDecimal.valueOf(Double.parseDouble((list
					.get(0).get("SURPLUSQUANTITY")
					.toString()))));// 剩余数量
		
	    }
	}
	req.setAttribute("soPickupplanPage", soPickupplan);
	return new ModelAndView("com/supply/pickupplan/soPickupplan-add");
    }

    /**
     * 提货计划主表编辑页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(SoPickupplanEntity soPickupplan,
	    HttpServletRequest req) {
	if (StringUtil.isNotEmpty(soPickupplan.getId())) {
	    soPickupplan = soPickupplanService.getEntity(
		    SoPickupplanEntity.class, soPickupplan.getId());
	    req.setAttribute("soPickupplanPage", soPickupplan);
	}
	return new ModelAndView("com/supply/pickupplan/soPickupplan-update");
    }

    /**
     * 加载明细列表[提货计划子表]
     * 
     * @return
     */
    @RequestMapping(params = "soPickupplanBList")
    public ModelAndView soPickupplanBList(SoPickupplanEntity soPickupplan,
	    HttpServletRequest req) {

	// ===================================================================================
	// 获取参数
	Object id0 = soPickupplan.getId();
	// ===================================================================================
	// 查询-提货计划子表
	String hql0 = "from SoPickupplanBEntity where 1 = 1 AND fK_ID = ? ";
	try {
	    List<SoPickupplanBEntity> soPickupplanBEntityList = systemService
		    .findHql(hql0, id0);
	    req.setAttribute("soPickupplanBList", soPickupplanBEntityList);
	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	return new ModelAndView("com/supply/pickupplanb/soPickupplanBList");
    }

    /**
     * 导出excel
     * 
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(SoPickupplanEntity soPickupplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid, ModelMap map) {
	CriteriaQuery cq = new CriteriaQuery(SoPickupplanEntity.class, dataGrid);
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		soPickupplan);
	try {
	    // 自定义追加查询条件
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
	cq.add();
	List<SoPickupplanEntity> list = this.soPickupplanService
		.getListByCriteriaQuery(cq, false);
	List<SoPickupplanPage> pageList = new ArrayList<SoPickupplanPage>();
	if (list != null && list.size() > 0) {
	    for (SoPickupplanEntity entity : list) {
		try {
		    SoPickupplanPage page = new SoPickupplanPage();
		    MyBeanUtils.copyBeanNotNull2Bean(entity, page);
		    Object id0 = entity.getId();
		    String hql0 = "from SoPickupplanBEntity where 1 = 1 AND fK_ID = ? ";
		    List<SoPickupplanBEntity> soPickupplanBEntityList = systemService
			    .findHql(hql0, id0);
		    page.setSoPickupplanBList(soPickupplanBEntityList);
		    pageList.add(page);
		} catch (Exception e) {
		    logger.info(e.getMessage());
		}
	    }
	}
	map.put(NormalExcelConstants.FILE_NAME, "提货计划主表");
	map.put(NormalExcelConstants.CLASS, SoPickupplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("提货计划主表列表",
		"导出人:Jeecg", "导出信息"));
	map.put(NormalExcelConstants.DATA_LIST, pageList);
	return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 通过excel导入数据
     * 
     * @param request
     * @param
     * @return
     */
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request,
	    HttpServletResponse response) {
	AjaxJson j = new AjaxJson();
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
	    MultipartFile file = entity.getValue();// 获取上传文件对象
	    ImportParams params = new ImportParams();
	    params.setTitleRows(2);
	    params.setHeadRows(2);
	    params.setNeedSave(true);
	    try {
		List<SoPickupplanPage> list = ExcelImportUtil.importExcel(
			file.getInputStream(), SoPickupplanPage.class, params);
		SoPickupplanEntity entity1 = null;
		for (SoPickupplanPage page : list) {
		    page.setPickupplancode(Identify
			    .getIdent(SupplyConstants.THJH));
		    entity1 = new SoPickupplanEntity();
		    MyBeanUtils.copyBeanNotNull2Bean(page, entity1);
		    soPickupplanService.addMain(entity1,
			    page.getSoPickupplanBList());
		}
		j.setMsg("文件导入成功！");
	    } catch (Exception e) {
		j.setMsg("文件导入失败！");
		logger.error(ExceptionUtil.getExceptionMessage(e));
	    } finally {
		try {
		    file.getInputStream().close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return j;
    }

    /**
     * 导出excel 使模板
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(ModelMap map) {
	map.put(NormalExcelConstants.FILE_NAME, "提货计划主表");
	map.put(NormalExcelConstants.CLASS, SoPickupplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("提货计划主表列表",
		"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
	map.put(NormalExcelConstants.DATA_LIST, new ArrayList());
	return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 导入功能跳转
     * 
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
	req.setAttribute("controller_name", "soPickupplanController");
	return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SoPickupplanEntity> list() {
	List<SoPickupplanEntity> listSoPickupplans = soPickupplanService
		.getList(SoPickupplanEntity.class);
	return listSoPickupplans;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
	SoPickupplanEntity task = soPickupplanService.get(
		SoPickupplanEntity.class, id);
	if (task == null) {
	    return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(
	    @RequestBody SoPickupplanPage soPickupplanPage,
	    UriComponentsBuilder uriBuilder) {
	// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
	Set<ConstraintViolation<SoPickupplanPage>> failures = validator
		.validate(soPickupplanPage);
	if (!failures.isEmpty()) {
	    return new ResponseEntity(
		    BeanValidators.extractPropertyAndMessage(failures),
		    HttpStatus.BAD_REQUEST);
	}

	// 保存
	List<SoPickupplanBEntity> soPickupplanBList = soPickupplanPage
		.getSoPickupplanBList();

	SoPickupplanEntity soPickupplan = new SoPickupplanEntity();
	try {
	    MyBeanUtils.copyBeanNotNull2Bean(soPickupplan, soPickupplanPage);
	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	soPickupplanService.addMain(soPickupplan, soPickupplanBList);

	// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
	String id = soPickupplanPage.getId();
	URI uri = uriBuilder.path("/rest/soPickupplanController/" + id).build()
		.toUri();
	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(uri);

	return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
	    @RequestBody SoPickupplanPage soPickupplanPage) {
	// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
	Set<ConstraintViolation<SoPickupplanPage>> failures = validator
		.validate(soPickupplanPage);
	if (!failures.isEmpty()) {
	    return new ResponseEntity(
		    BeanValidators.extractPropertyAndMessage(failures),
		    HttpStatus.BAD_REQUEST);
	}

	// 保存
	List<SoPickupplanBEntity> soPickupplanBList = soPickupplanPage
		.getSoPickupplanBList();

	SoPickupplanEntity soPickupplan = new SoPickupplanEntity();
	try {
	    MyBeanUtils.copyBeanNotNull2Bean(soPickupplan, soPickupplanPage);
	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	soPickupplanService.updateMain(soPickupplan, soPickupplanBList);

	// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
	SoPickupplanEntity soPickupplan = soPickupplanService.get(
		SoPickupplanEntity.class, id);
	soPickupplanService.delMain(soPickupplan);
    }

    /**
     * easyui AJAX请求数据
     * 
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     * @throws Exception 
     */
    @RequestMapping(params = "datagridU8")
    public void datagridU8(SoPickupplanEntity poDeliveryplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid) throws Exception {
	//根据输入的用户编码和姓名来过滤
	Object createBy  = ResourceUtil.getSessionUserName().getUserName();
	Object createByName  = ResourceUtil.getSessionUserName().getUserName();
	if(StringUtil.isEmpty(createBy.toString()) && StringUtil.isEmpty(createByName.toString())) {
		throw new BusinessException("至少输入一个查询条件！");
	}
	List<Map<String, Object>> list = soPickupplanU8Service.queryU8SoOrder(createBy.toString(),createByName.toString());
	CriteriaQuery cq = new CriteriaQuery(SoPickupplanEntity.class, dataGrid);
	ArrayList<SoPickupplanEntity> results = new ArrayList<SoPickupplanEntity>();
	for (Map<String, Object> map : list) {
	    SoPickupplanEntity entity = new SoPickupplanEntity();
	    entity.setId((map.get("cSOCode") == null ? "" : map.get("cSOCode")
		    .toString()));// 订单号
	    entity.setSoordercode(map.get("cSOCode") == null ? "" : map.get(
		    "cSOCode").toString());// 订单号
	    entity.setSysOrgCode(map.get("DPODATE") == null ? "" : map.get(
		    "DPODATE").toString());// 订单日期
	    entity.setTrancode(map.get("trancode") == null ? "" : map.get(
		    "trancode").toString());// 运输商编码
	    entity.setTranname(map.get("tranname") == null ? "" : map.get(
		    "tranname").toString());// 运输商名称
	    entity.setCustomercode(map.get("cCusCode") == null ? "" : map.get(
		    "cCusCode").toString());// 供应商编码
	    entity.setCustomername(map.get("cCusName") == null ? "" : map.get(
		    "cCusName").toString());// 供应商名称
	    entity.setMaterialcode(map.get("cInvCode") == null ? "" : map.get(
		    "cInvCode").toString());// 物料编码
	    entity.setMaterialname(map.get("CINVNAME") == null ? "" : map.get(
		    "CINVNAME").toString());// 物料名称
	    entity.setSpec(map.get("CINVSTD") == null ? "" : map.get("CINVSTD")
		    .toString());// 规格
	    entity.setStore(map.get("IQUANTITY") == null ? "" : map.get(
		    "IQUANTITY").toString());// 订单数量
	    entity.setSurplusnum(map.get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO
		    : BigDecimal.valueOf(Double.parseDouble((map
			    .get("SURPLUSQUANTITY").toString()))));// 剩余数量
	    entity.setUnloadplace(map.get("FOUTQUANTITY") == null ? "" : map
		    .get("FOUTQUANTITY").toString());// 出库数量
//	    entity.setVnote(map.get("ITAXPRICE") == null ? "" : map.get(
//		    "ITAXPRICE").toString());// 单价
	    entity.setVnote(map.get("VNOTE") == null ? "" : map.get(
		    "VNOTE").toString());// 备注
	    results.add(entity);
	}
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		poDeliveryplan);
	try {
	    // 自定义追加查询条件
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
	cq.add();
//	this.soPickupplanService.getDataGridReturn(cq, true);
	dataGrid.setResults(results);
	TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 提货计划主表新增页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "querySrc")
    public ModelAndView querySrc(SoPickupplanBEntity soPickupplan,
	    HttpServletRequest req) {
	if (StringUtil.isNotEmpty(soPickupplan.getId())) {
	    soPickupplan = soPickupplanService.getEntity(
		    PoDeliveryplanEntity.class, soPickupplan.getId());
	    req.setAttribute("soPickupplanPage", soPickupplan);
	}
	return new ModelAndView("com/supply/pickupplan/soPickupplanU8List");
    }

    /**
     * 车号查询
     */
    @RequestMapping(params = "queryVehicle")
    @ResponseBody
    public List<VehicleEntity> queryVehicle(HttpServletRequest request,
	    DataGrid dataGrid) {
	CriteriaQuery cq = new CriteriaQuery(VehicleEntity.class);
	// if(StringUtil.isNotEmpty(createBy)) {
	// cq.eq("createBy", createBy);
	// }
	List<VehicleEntity> ls = commonService
		.getListByCriteriaQuery(cq, false);
	return ls;
    }

    /**
     * 批量审核提货计划主表
     * 
     * @return
     */
    @RequestMapping(params = "doApprove")
    @ResponseBody
    public AjaxJson doApprove(String ids, HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	String message = "提货计划审核成功";
	try {
	    for (String id : ids.split(",")) {
		SoPickupplanEntity soPickupplan = systemService.getEntity(
			SoPickupplanEntity.class, id);

		if (SupplyConstants.BILLSTATUS_APPROVE.equals(soPickupplan
			.getBillstatus())) {
		    throw new BusinessException("已审核单据，不能再次审核");
		} else if (SupplyConstants.BILLSTATUS_SAVE.equals(soPickupplan
			.getBillstatus())
			|| StringUtil.isEmpty(soPickupplan.getBillstatus())) {
		    soPickupplanService
			    .updateBySqlString("update so_pickupplan set billstatus = '"
				    + SupplyConstants.BILLSTATUS_APPROVE
				    + "' where id = '" + id + "'");
		}
		systemService.addLog(message, Globals.Log_Type_APPROVE,
			Globals.Log_Leavel_INFO);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "提货计划审核失败" + e.getMessage();
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }
    /**
     * 根据车牌号查询对应的车辆信息
     * 
     * @return
     */
    @RequestMapping(params = "queryByCode")
    @ResponseBody
    public AjaxJson queryByCode(HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	Map<String, Object> jsonObject = new HashMap<String, Object>();
	String code = request.getParameter("code") == null ? "" : request.getParameter("code").toString();
	if(!StringUtil.isEmpty(code)) {
	    //根据id查询车辆的详细信息
	    VehicleEntity vehicle =  systemService.getEntity(VehicleEntity.class, code);
	    if(vehicle != null) {
		jsonObject.put("vehicletype", vehicle.getCvehicletype() == null ? "" :vehicle.getCvehicletype().toString());
		jsonObject.put("drivername", vehicle.getCdriver() == null ? "" :vehicle.getCdriver().toString());
		jsonObject.put("idcard", vehicle.getVlicensenumber() == null ? "" : vehicle.getVlicensenumber().toString());
		jsonObject.put("phone", vehicle.getVdrivermobile() == null ? "" : vehicle.getVdrivermobile().toString());
		jsonObject.put("loadnum", vehicle.getNload() == null ? "" :vehicle.getNload().toString());  
		jsonObject.put("vehiclelicense", vehicle.getId() == null ? "" :vehicle.getId().toString());
		j.setAttributes(jsonObject);
	    }
	}
	return j;
    }
    private void updateCar(List<SoPickupplanBEntity> poDeliveryplanBList, String status) {
	String sql = "update bd_vehicle set bpm_status = '"+status+"' where ";
	List<String> carid = new ArrayList<String>();
	for(SoPickupplanBEntity b :poDeliveryplanBList) {
	    carid.add(b.getVehiclelicense());
	}
	sql += JeecgSqlUtil.buildSqlForIn("id", carid.toArray(new String[0]));
	systemService.updateBySqlString(sql);
    }
    /**
     * 根据承运商查询对应的车辆信息
     * 
     * @return
     */
    @RequestMapping(params = "queryByCarrier")
    @ResponseBody
    public AjaxJson queryByCarrier(HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	Map<String, Object> jsonObject = new HashMap<String, Object>();
	String code = request.getParameter("code") == null ? "" : request.getParameter("code").toString();
	String where = "select * from bd_vehicle where ccarrier = '中汇'";
	if(!StringUtil.isEmpty(code)) {
	    //根据承运商查询车辆的详细信息
	    List<VehicleEntity> vehicles =  systemService.findByProperty(VehicleEntity.class, "ccarrier", code);
	    if(vehicles != null) {
		for(int i=0;i<vehicles.size(); i++ ) {
		jsonObject.put(vehicles.get(i).getId()== null ? "" :vehicles.get(i).getId().toString(),vehicles.get(i).getVehiclelicense() == null ? "" :vehicles.get(i).getVehiclelicense().toString());
		}
		j.setAttributes(jsonObject);
	    }
	}
	return j;
    }
}
