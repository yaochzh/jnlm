package com.supply.task.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supply.common.SupplyConstants;
import com.supply.deliveryplan.entity.PoDeliveryplanEntity;
import com.supply.deliveryplan.service.PoDeliveryplanServiceI;
import com.supply.pickupplan.entity.SoPickupplanEntity;
import com.supply.task.service.SupplyTaskServiceI;
import com.supply.task.service.impl.SupplyTaskServiceImpl;
import com.supply.vehicle.entity.VehicleEntity;

/**
 * @Title: Controller
 * @Description: 工作任务主表
 * @author onlineGenerator
 * @date 2016-07-05 11:16:40
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/supplyTaskController")
public class SupplyTaskController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(SupplyTaskController.class);

    @Autowired
    private SupplyTaskServiceI supplyTaskService;
    @Autowired
    private PoDeliveryplanServiceI poDeliveryplanService;
    @Autowired
    private SystemService systemService;

    /**
     * 工作任务主表列表 页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
	return new ModelAndView("com/supply/task/supplyTaskList");
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

    @RequestMapping(params = "datagrid")
    public void datagrid(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid) throws Exception {
	Object createBy  = ResourceUtil.getSessionUserName().getUserName();
	List<Map<String, Object>> list = supplyTaskService.queryApproveOrder(createBy.toString());
	CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class,
		dataGrid);
	ArrayList<PoDeliveryplanEntity> results = new ArrayList<PoDeliveryplanEntity>();
	if(list == null ) {
	    return;
	}
	for (Map<String, Object> map : list) {
	    PoDeliveryplanEntity entity = new PoDeliveryplanEntity();
	    entity.setId((map.get("id") == null ? "" : map.get("id")
		    .toString()));// id
	    entity.setSysOrgCode(map.get("create_date") == null ? "" : map.get(
		    "create_date").toString());// 单据日期
	    entity.setTranname(map.get("create_name") == null ? "" : map.get(
		    "create_name").toString());// 提交人
	    entity.setPoordercode(map.get("plancode") == null ? "" : map
		    .get("plancode").toString());// 计划单号
	    entity.setTrancode(map.get("cussupname") == null ? "" : map.get(
		    "cussupname").toString());// 客户/供应商
	    entity.setSupplercode(map.get("tranname") == null ? "" : map.get(
		    "tranname").toString());// 运输商
	    entity.setSupplername(map.get("materialname") == null ? "" : map.get(
		    "materialname").toString());// 物料名称
	    entity.setMaterialcode(map.get("spec") == null ? "" : map.get(
		    "spec").toString());// 规格型号
	    entity.setMaterialname(map.get("CINVNAME") == null ? "" : map.get(
		    "CINVNAME").toString());//计划数量
	    entity.setSpec(map.get("CINVSTD") == null ? "" : map.get("CINVSTD")
		    .toString());// 安排车次
	    entity.setStore(map.get("IQUANTITY") == null ? "" : map.get(
		    "IQUANTITY").toString());// 订单数量
	    entity.setSurplusnum(map.get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO
		    : BigDecimal.valueOf(Double.parseDouble((map
			    .get("SURPLUSQUANTITY").toString()))));// 剩余数量
	    entity.setUnloadplace(map.get("FPOARRQUANTITY") == null ? "" : map
		    .get("FPOARRQUANTITY").toString());// 到货量
	    entity.setVnote(map.get("ITAXPRICE") == null ? "" : map.get(
		    "ITAXPRICE").toString());// 单价
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
     * 批量审核工作任务主表
     * 
     * @return
     */
    @RequestMapping(params = "doApprove")
    @ResponseBody
    public AjaxJson doApprove(String ids, String approveInfo,String approveStatus,HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	String message = "工作任务审核成功";
	if(approveStatus.equals("1")) {
	 message = "工作任务审核成功";
	}else if(approveStatus.equals("2")){
	    message = "工作任务弃审成功";  
	}
	if(StringUtils.isEmpty(ids)) {
	    j.setMsg("工作任务为空");
	    return j;
	}
	if(!StringUtils.isEmpty(approveInfo) && approveInfo.equals(SupplyConstants.BILLSTATUS_INFO) && approveStatus.equals("1")) {
	    approveInfo = "同意";
	}else if(!StringUtils.isEmpty(approveInfo) && approveInfo.equals(SupplyConstants.BILLSTATUS_INFO) && approveStatus.equals("2")) {
	    approveInfo = "不同意";
	}
	String userCode = ResourceUtil.getSessionUserName().getUserName();
	String userName = ResourceUtil.getSessionUserName().getRealName();
	HashMap<String,String> params = new HashMap<String,String>();
	params.put("userCode", userCode);
	params.put("userName", userName);
	params.put("ids", ids);
	params.put("approveInfo", approveInfo);
	params.put("approveStatus", approveStatus);
	try {
	   supplyTaskService.updateApproveInfo(params);
	} catch (Exception e) {
	    e.printStackTrace();
	    message = "工作任务审核失败" + e.getMessage();
	    throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
    }

    /**
     * 审核页面跳转
     * 
     * @return
     */
    @RequestMapping(params = "goApprove")
    public ModelAndView goApprove(String ids,
	    HttpServletRequest req) {
	    req.setAttribute("ids", ids);
	    return new ModelAndView("com/supply/task/popup");
    }
}