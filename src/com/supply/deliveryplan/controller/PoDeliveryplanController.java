package com.supply.deliveryplan.controller;

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
import org.jeecgframework.core.common.model.json.ComboTree;
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
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
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

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.supply.common.Identify;
import com.supply.common.SupplyConstants;
import com.supply.deliveryplan.entity.PoDeliveryplanEntity;
import com.supply.deliveryplan.page.PoDeliveryplanPage;
import com.supply.deliveryplan.service.PoDeliveryplanServiceI;
import com.supply.deliveryplan.service.PoDeliveryplanU8ServiceI;
import com.supply.deliveryplanb.entity.PoDeliveryplanBEntity;
import com.supply.pickupplan.entity.SoPickupplanEntity;
import com.supply.pickupplan.service.SoPickupplanServiceI;
import com.supply.vehicle.entity.VehicleEntity;

/**
 * @Title: Controller
 * @Description: 送货计划主表
 * @author onlineGenerator
 * @date 2016-07-05 11:16:40
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/poDeliveryplanController")
public class PoDeliveryplanController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(PoDeliveryplanController.class);

    @Autowired
    private PoDeliveryplanServiceI poDeliveryplanService;
    @Autowired
    private PoDeliveryplanU8ServiceI poDeliveryplanU8Service;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SoPickupplanServiceI soPickupplanService;

    /**
     * 送货计划主表列表 页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {

	return new ModelAndView("com/supply/deliveryplan/poDeliveryplanList");
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
    public void datagrid(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid) {
	CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class,
		dataGrid);
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		poDeliveryplan);
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
	this.poDeliveryplanService.getDataGridReturn(cq, true);
	TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除送货计划主表
     * 
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	poDeliveryplan = systemService.getEntity(PoDeliveryplanEntity.class,
		poDeliveryplan.getId());
	String message = "送货计划主表删除成功";
	try {
	    if(SupplyConstants.BILLSTATUS_APPROVE.equals(poDeliveryplan.getBillstatus())){
		 message = "单据已审核，不能进行删除操作！";
		    throw new BusinessException(message);
	    }
	    poDeliveryplanService.delMain(poDeliveryplan);
	    systemService.addLog(message, Globals.Log_Type_DEL,
		    Globals.Log_Leavel_INFO);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "送货计划主表删除失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 批量删除送货计划主表
     * 
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	String message = "送货计划主表删除成功";
	try {
	    for (String id : ids.split(",")) {
		PoDeliveryplanEntity poDeliveryplan = systemService.getEntity(
			PoDeliveryplanEntity.class, id);
		poDeliveryplanService.delMain(poDeliveryplan);
		systemService.addLog(message, Globals.Log_Type_DEL,
			Globals.Log_Leavel_INFO);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "送货计划主表删除失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 添加送货计划主表
     * 
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(PoDeliveryplanEntity poDeliveryplan,
	    PoDeliveryplanPage poDeliveryplanPage, HttpServletRequest request) {
	List<PoDeliveryplanBEntity> poDeliveryplanBList = poDeliveryplanPage
		.getPoDeliveryplanBList();
	AjaxJson j = new AjaxJson();
	String message = "添加成功";
	try {
	    poDeliveryplan.setDeliveryplancode(Identify
		    .getIdent(SupplyConstants.SHJH));
	    poDeliveryplanService.addMain(poDeliveryplan, poDeliveryplanBList);
	    systemService.addLog(message, Globals.Log_Type_INSERT,
		    Globals.Log_Leavel_INFO);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "送货计划主表添加失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    private void updateCar(List<PoDeliveryplanBEntity> poDeliveryplanBList, String string) {
	String sql = "update bd_vehicle set bpm_status = '"+string+"' where ";
	List<String> carid = new ArrayList<String>();
	for(PoDeliveryplanBEntity b :poDeliveryplanBList) {
	    if(!StringUtil.isEmpty(b.getVehiclelicense())) {
		carid.add(b.getVehiclelicense());
	    }
	}
	if(carid !=null && carid.size()>0) {
	sql += JeecgSqlUtil.buildSqlForIn("id", carid.toArray(new String[0]));
	systemService.updateBySqlString(sql);
	}
    }

    /**
     * 更新送货计划主表
     * 
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(PoDeliveryplanEntity poDeliveryplan,
	    PoDeliveryplanPage poDeliveryplanPage, HttpServletRequest request) {
	List<PoDeliveryplanBEntity> poDeliveryplanBList = poDeliveryplanPage
		.getPoDeliveryplanBList();
	AjaxJson j = new AjaxJson();
	String message = "更新成功";
	try {
	    poDeliveryplan.setBillstatus("");
	    poDeliveryplan.setBillstatus(null);
	    poDeliveryplan.setApproveone(null);
	    poDeliveryplan.setApproveonename(null);
	    poDeliveryplan.setApproveonedate(null);
	    poDeliveryplan.setApproveoneinfo(null);
	    poDeliveryplan.setApproveonestatus(null);
	    poDeliveryplan.setApprovetwo(null);;
	    poDeliveryplan.setApprovetwoname(null);
	    poDeliveryplan.setApprovetwodate(null);
	    poDeliveryplan.setApprovetwoinfo(null);
	    poDeliveryplan.setApprovetwostatus(null);
	    poDeliveryplanService.updateMain(poDeliveryplan,
		    poDeliveryplanBList);
	    systemService.addLog(message, Globals.Log_Type_UPDATE,
		    Globals.Log_Leavel_INFO);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "更新送货计划主表失败";
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 送货计划主表新增页面跳转
     * 
     * @return
     * @throws Exception 
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest req)  {
	if (StringUtil.isNotEmpty(poDeliveryplan.getId())) {
	    List<Map<String, Object>> list = null;
		try {
		    list = poDeliveryplanU8Service
		        .queryU8PoOrderByOrdercode(poDeliveryplan.getId());
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    
	    if (list == null) {
		throw new BusinessException("订单数据查询为空！");
	    } else if (list != null && list.size() > 1) {
		throw new BusinessException("订单数据多条，请检查！");
	    } else if (list != null && list.size() == 1) {
		poDeliveryplan
			.setPoordercode(list.get(0).get("POID") == null ? ""
				: list.get(0).get("POID").toString());// 订单号
		poDeliveryplan
			.setMaterialcode(list.get(0).get("CINVCODE") == null ? ""
				: list.get(0).get("CINVCODE").toString());// 订单日期
		poDeliveryplan
			.setMaterialname(list.get(0).get("CINVNAME") == null ? ""
				: list.get(0).get("CINVNAME").toString());// 物料名称
		poDeliveryplan.setSpec(list.get(0).get("CINVSTD") == null ? ""
			: list.get(0).get("CINVSTD").toString());// 规格
		poDeliveryplan
		.setTrancode(list.get(0).get("trancode") == null ? ""
			: list.get(0).get("trancode").toString());// 运输商编码"SysOrgCode"
		poDeliveryplan
		.setSysOrgCode(list.get(0).get("dpodate") == null ? ""
			: list.get(0).get("dpodate").toString());// 运输商名称
		poDeliveryplan
		.setTranname(list.get(0).get("tranname") == null ? ""
			: list.get(0).get("tranname").toString());// 运输商名称
		poDeliveryplan
		.setSupplercode(list.get(0).get("CVENCODE") == null ? ""
			: list.get(0).get("CVENCODE").toString());// 供应商名称
		poDeliveryplan
			.setSupplername(list.get(0).get("CVENNAME") == null ? ""
				: list.get(0).get("CVENNAME").toString());// 供应商名称
		poDeliveryplan
			.setPoordernum(list.get(0).get("IQUANTITY") == null ? BigDecimal.ZERO
				: BigDecimal.valueOf(Double.parseDouble(list
					.get(0).get("IQUANTITY").toString())));// 订单数量
		poDeliveryplan
			.setSurplusnum(list.get(0).get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO
				: BigDecimal.valueOf(Double.parseDouble((list
					.get(0).get("SURPLUSQUANTITY")
					.toString()))));// 剩余数量
	    }
	}
	req.setAttribute("poDeliveryplanPage", poDeliveryplan);
	return new ModelAndView("com/supply/deliveryplan/poDeliveryplan-add");
    }

    /**
     * 送货计划主表编辑页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest req) {
	PoDeliveryplanEntity poDeliveryplanOld = poDeliveryplan;
	if (StringUtil.isNotEmpty(poDeliveryplan.getId())) {
	    if(poDeliveryplan.getBillstatus() != null && !SupplyConstants.BILLSTATUS_SAVE.equals(poDeliveryplan.getBillstatus()) ){
		    throw new BusinessException("单据已审核，不能进行更新操作！");
	    }
	    poDeliveryplan = poDeliveryplanService.getEntity(
		    PoDeliveryplanEntity.class, poDeliveryplan.getId());
	    if(poDeliveryplan != null) {
	    req.setAttribute("poDeliveryplanPage", poDeliveryplan);

		return new ModelAndView("com/supply/deliveryplan/poDeliveryplan-update");
	    }else {
		 SoPickupplanEntity soPickupplan = soPickupplanService.getEntity(
			    SoPickupplanEntity.class, poDeliveryplanOld.getId());
		    req.setAttribute("soPickupplanPage", soPickupplan);
		return new ModelAndView("com/supply/pickupplan/soPickupplan-update");
	    }
	}
	return null;
    }

    /**
     * 加载明细列表[送货计划子表]
     * 
     * @return
     */
    @RequestMapping(params = "poDeliveryplanBList")
    public ModelAndView poDeliveryplanBList(
	    PoDeliveryplanEntity poDeliveryplan, HttpServletRequest req) {

	// ===================================================================================
	// 获取参数
	Object id0 = poDeliveryplan.getId();
	// ===================================================================================
	// 查询-送货计划子表
	String hql0 = "from PoDeliveryplanBEntity where 1 = 1 AND fK_ID = ? ";
	try {
	    List<PoDeliveryplanBEntity> poDeliveryplanBEntityList = systemService
		    .findHql(hql0, id0);
	    req.setAttribute("poDeliveryplanBList", poDeliveryplanBEntityList);
	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	return new ModelAndView("com/supply/deliveryplanb/poDeliveryplanBList");
    }

    /**
     * 导出excel
     * 
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid, ModelMap map) {
	CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class,
		dataGrid);
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		poDeliveryplan);
	try {
	    // 自定义追加查询条件
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
	cq.add();
	List<PoDeliveryplanEntity> list = this.poDeliveryplanService
		.getListByCriteriaQuery(cq, false);
	List<PoDeliveryplanPage> pageList = new ArrayList<PoDeliveryplanPage>();
	if (list != null && list.size() > 0) {
	    for (PoDeliveryplanEntity entity : list) {
		try {
		    PoDeliveryplanPage page = new PoDeliveryplanPage();
		    MyBeanUtils.copyBeanNotNull2Bean(entity, page);
		    Object id0 = entity.getId();
		    String hql0 = "from PoDeliveryplanBEntity where 1 = 1 AND fK_ID = ? ";
		    List<PoDeliveryplanBEntity> poDeliveryplanBEntityList = systemService
			    .findHql(hql0, id0);
		    page.setPoDeliveryplanBList(poDeliveryplanBEntityList);
		    pageList.add(page);
		} catch (Exception e) {
		    logger.info(e.getMessage());
		}
	    }
	}
	map.put(NormalExcelConstants.FILE_NAME, "送货计划主表");
	map.put(NormalExcelConstants.CLASS, PoDeliveryplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("送货计划主表列表",
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
		List<PoDeliveryplanPage> list = ExcelImportUtil
			.importExcel(file.getInputStream(),
				PoDeliveryplanPage.class, params);
		PoDeliveryplanEntity entity1 = null;
		for (PoDeliveryplanPage page : list) {
		    page.setDeliveryplancode(Identify
			    .getIdent(SupplyConstants.SHJH));
		    entity1 = new PoDeliveryplanEntity();
		    MyBeanUtils.copyBeanNotNull2Bean(page, entity1);
		    poDeliveryplanService.addMain(entity1,
			    page.getPoDeliveryplanBList());
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
	map.put(NormalExcelConstants.FILE_NAME, "送货计划主表");
	map.put(NormalExcelConstants.CLASS, PoDeliveryplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("送货计划主表列表",
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
	req.setAttribute("controller_name", "poDeliveryplanController");
	return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<PoDeliveryplanEntity> list() {
	List<PoDeliveryplanEntity> listPoDeliveryplans = poDeliveryplanService
		.getList(PoDeliveryplanEntity.class);
	return listPoDeliveryplans;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
	PoDeliveryplanEntity task = poDeliveryplanService.get(
		PoDeliveryplanEntity.class, id);
	if (task == null) {
	    return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(
	    @RequestBody PoDeliveryplanPage poDeliveryplanPage,
	    UriComponentsBuilder uriBuilder) {
	// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
	Set<ConstraintViolation<PoDeliveryplanPage>> failures = validator
		.validate(poDeliveryplanPage);
	if (!failures.isEmpty()) {
	    return new ResponseEntity(
		    BeanValidators.extractPropertyAndMessage(failures),
		    HttpStatus.BAD_REQUEST);
	}

	// 保存
	List<PoDeliveryplanBEntity> poDeliveryplanBList = poDeliveryplanPage
		.getPoDeliveryplanBList();

	PoDeliveryplanEntity poDeliveryplan = new PoDeliveryplanEntity();
	try {
	    MyBeanUtils
		    .copyBeanNotNull2Bean(poDeliveryplan, poDeliveryplanPage);
	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	poDeliveryplanService.addMain(poDeliveryplan, poDeliveryplanBList);

	// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
	String id = poDeliveryplanPage.getId();
	URI uri = uriBuilder.path("/rest/poDeliveryplanController/" + id)
		.build().toUri();
	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(uri);

	return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
	    @RequestBody PoDeliveryplanPage poDeliveryplanPage) {
	// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
	Set<ConstraintViolation<PoDeliveryplanPage>> failures = validator
		.validate(poDeliveryplanPage);
	if (!failures.isEmpty()) {
	    return new ResponseEntity(
		    BeanValidators.extractPropertyAndMessage(failures),
		    HttpStatus.BAD_REQUEST);
	}

	// 保存
	List<PoDeliveryplanBEntity> poDeliveryplanBList = poDeliveryplanPage
		.getPoDeliveryplanBList();

	PoDeliveryplanEntity poDeliveryplan = new PoDeliveryplanEntity();
	try {
	    MyBeanUtils
		    .copyBeanNotNull2Bean(poDeliveryplan, poDeliveryplanPage);
	} catch (Exception e) {
	    logger.info(e.getMessage());
	}
	poDeliveryplanService.updateMain(poDeliveryplan, poDeliveryplanBList);

	// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
	PoDeliveryplanEntity poDeliveryplan = poDeliveryplanService.get(
		PoDeliveryplanEntity.class, id);
	poDeliveryplanService.delMain(poDeliveryplan);
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
    public void datagridU8(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid) throws Exception {
	Object createBy  = ResourceUtil.getSessionUserName().getUserName();
	Object createByName  = ResourceUtil.getSessionUserName().getUserName();
	if(StringUtil.isEmpty(createBy.toString()) && StringUtil.isEmpty(createByName.toString())) {
	    throw new BusinessException("至少输入一个查询条件！");
	}
	List<Map<String, Object>> list = poDeliveryplanU8Service
		.queryU8PoOrder(createBy.toString(),createByName.toString());
	CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class,
		dataGrid);
	ArrayList<PoDeliveryplanEntity> results = new ArrayList<PoDeliveryplanEntity>();
	for (Map<String, Object> map : list) {
	    PoDeliveryplanEntity entity = new PoDeliveryplanEntity();
	    entity.setId((map.get("POID") == null ? "" : map.get("POID")
		    .toString()));// 订单号
	    entity.setPoordercode(map.get("POID") == null ? "" : map
		    .get("POID").toString());// 订单号
	    entity.setSysOrgCode(map.get("DPODATE") == null ? "" : map.get(
		    "DPODATE").toString());// 订单日期
	    entity.setTranname(map.get("tranname") == null ? "" : map.get(
		    "tranname").toString());// 运输商名称
	    entity.setTrancode(map.get("trancode") == null ? "" : map.get(
		    "trancode").toString());// 运输商编码
	    entity.setSupplercode(map.get("CVENCODE") == null ? "" : map.get(
		    "CVENCODE").toString());// 供应商名称
	    entity.setSupplername(map.get("CVENNAME") == null ? "" : map.get(
		    "CVENNAME").toString());// 供应商名称
	    entity.setMaterialcode(map.get("CINVCODE") == null ? "" : map.get(
		    "CINVCODE").toString());// 物料编码
	    entity.setMaterialname(map.get("CINVNAME") == null ? "" : map.get(
		    "CINVNAME").toString());// 物料名称
	    entity.setSpec(map.get("CINVSTD") == null ? "" : map.get("CINVSTD")
		    .toString());// 规格
	    entity.setStore(map.get("IQUANTITY") == null ? "" : map.get(
		    "IQUANTITY").toString());// 订单数量
	    entity.setSurplusnum(map.get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO
		    : BigDecimal.valueOf(Double.parseDouble((map
			    .get("SURPLUSQUANTITY").toString()))));// 剩余数量
	    entity.setUnloadplace(map.get("FPOARRQUANTITY") == null ? "" : map
		    .get("FPOARRQUANTITY").toString());// 到货量
	    entity.setVnote(map.get("ITAXPRICE") == null ? "" : map.get(
		    "ITAXPRICE").toString());// 单价
	    entity.setApprove(map.get("VNOTE") == null ? "" : map.get(
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
	// cq.setDataGrid(dataGrid);
//	this.poDeliveryplanService.getDataGridReturn(cq, true);
	dataGrid.setResults(results);
	TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 送货计划主表新增页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "querySrc")
    public ModelAndView querySrc(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest req) {
	if (StringUtil.isNotEmpty(poDeliveryplan.getId())) {
	    poDeliveryplan = poDeliveryplanService.getEntity(
		    PoDeliveryplanEntity.class, poDeliveryplan.getId());
	    req.setAttribute("poDeliveryplanPage", poDeliveryplan);
	}
	return new ModelAndView("com/supply/deliveryplan/poDeliveryplanU8List");
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
     * 批量审核送货计划主表
     * 
     * @return
     */
    @RequestMapping(params = "doApprove")
    @ResponseBody
    public AjaxJson doApprove(String ids, HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	String message = "送货计划审核成功";
	try {
	    for (String id : ids.split(",")) {
		PoDeliveryplanEntity poDeliveryplan = systemService.getEntity(
			PoDeliveryplanEntity.class, id);

		if (SupplyConstants.BILLSTATUS_APPROVE.equals(poDeliveryplan
			.getBillstatus())) {
		    throw new BusinessException("已审核单据，不能再次审核");
		} else if (SupplyConstants.BILLSTATUS_SAVE
			.equals(poDeliveryplan.getBillstatus())
			|| StringUtil.isEmpty(poDeliveryplan.getBillstatus())) {
		    poDeliveryplanService
			    .updateBySqlString("update po_deliveryplan set billstatus = '"
				    + SupplyConstants.BILLSTATUS_APPROVE
				    + "' where id = '" + id + "'");
		}
		systemService.addLog(message, Globals.Log_Type_APPROVE,
			Globals.Log_Leavel_INFO);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "送货计划审核失败" + e.getMessage();
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
		j.setAttributes(jsonObject);
	    }
	}
	return j;
    }
    /**
     * 
    * @Title: getVehicleTree 
    * @Description: 车辆下拉选择
    * @param @param request
    * @param @return
    * @return List<ComboTree>
    * @throws 
    * @author yaochzh
     */
    @RequestMapping(params = "getVehicleTree")
    @ResponseBody
    public String getVehicleTree(HttpServletRequest request) {
//        findHql不能处理is null条件
//        List<TSDepart> departsList = systemService.findHql("from TSPDepart where TSPDepart.id is null");
        List<VehicleEntity> vehicleList = systemService.findByQueryString("from VehicleEntity order by vehiclelicense");
        List<Map<String,String>> a = new ArrayList<Map<String,String>>();
        Map<String,String> b = new HashMap<String,String>();
        b.put("id", "1");
        b.put("value", "条目1");
        a.add(b);
        b.put("id", "2");
        b.put("value", "条目2");
        a.add(b);
        b.put("id", "3");
        b.put("value", "条目3");
        a.add(b);
       
        return JSONArray.toJSONString(a);
    }
}
