package com.supply.task.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

public interface SupplyTaskServiceI  extends CommonService{
    /**
     * 
    * @Title: queryApproveOrder 
    * @Description: 根据编码查询需要审批的单据
    * @param @param usercode
    * @param @return
    * @param @throws Exception
    * @return List<Map<String,Object>>
    * @throws 
    * @author yaochzh
     */
    public List<Map<String, Object>> queryApproveOrder(String usercode) throws Exception;
    /**
     * 
    * @Title: updateApproveInfo 
    * @Description: 审批
    * @param @param approveInfo
    * @param @throws Exception
    * @return void
    * @throws 
    * @author yaochzh
     */
    public void updateApproveInfo(HashMap<String,String> approveInfo) throws Exception;
}
