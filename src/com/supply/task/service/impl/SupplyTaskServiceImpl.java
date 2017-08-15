package com.supply.task.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.JeecgSqlUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.common.SupplyConstants;
import com.supply.task.service.SupplyTaskServiceI;

@Service("supplyTaskService")
@Transactional
public class SupplyTaskServiceImpl extends CommonServiceImpl implements
	SupplyTaskServiceI {

    public List<Map<String, Object>> queryApproveOrder(String usercode)
	    throws Exception {
	// 查询员工对应的角色，根据角色进行查询
	String approveonestatus = "approveonestatus";
	String poapprovetwostatus = "pi.approvetwostatus is null and pi.approveonestatus = '1'";
	String soapprovetwostatus = "de.approvetwostatus is null and de.approveonestatus = '1'";
	List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
	String approvetatus = "approvetatus";
	String dateSql = /*"select de.id,\n"
		+ "              de.deliveryplancode plancode,\n"
		+ "              de.poordercode      ordercode,\n"
		+ "              de.supplername      cussupname,\n"
		+ "              de.tranname,\n"
		+ "              de.materialname,\n"
		+ "              de.spec,\n"
		+ "              de.create_date,\n"
		+ "              de.create_name,\n"
		+ "              de.update_date,\n"
		+ "              de.update_name\n"
		+ "         from po_deliveryplan de\n"
		+ "        where de.approvetatus is null\n"
		+ "       union all\n" +*/ "       select pi.id,\n"
		+ "              pi.pickupplancode plancode,\n"
		+ "              pi.soordercode ordercode,\n"
		+ "              pi.customername   cussupname,\n"
		+ "              pi.tranname,\n"
		+ "              pi.materialname,\n"
		+ "              pi.spec,\n"
		+ "              pi.create_date,\n"
		+ "              pi.create_name,\n"
		+ "              pi.update_date,\n"
		+ "              pi.update_name\n"
		+ "         from so_pickupplan pi\n"
		+ "        where pi.approvetatus is null\n" + "order by\n"
		+ "              create_date,\n"
		+ "              create_name,\n" + "              plancode,\n"
		+ "              ordercode,\n" + "              cussupname,\n"
		+ "              tranname,\n" + "              materialname,\n"
		+ "              spec desc";
	;

	try {
		    if (queryApproveRole(usercode).equals(SupplyConstants.APPRO_SWB)) {
			dateSql = dateSql.replace(approvetatus,
				approveonestatus);
			results = this.findForJdbc(dateSql, null);
			return results;
		    } else if (queryApproveRole(usercode).equals(SupplyConstants.APPRO_CWB)) {
//			dateSql = dateSql.replace("de.approvetatus is null",
//				soapprovetwostatus);

			dateSql = dateSql.replace("pi.approvetatus is null",
				poapprovetwostatus);
			results = this.findForJdbc(dateSql, null);
			return results;
		    }

	} catch (Exception e) {
	    throw new Exception("数据库查询异常");
	}
	return null;
    }

    public void updateApproveInfo(HashMap<String, String> approveInfos)
	    throws Exception {
	// 根据传来的相关信息进行一级或者二级审批
	String ids = approveInfos.get("ids");
	String userCode = approveInfos.get("userCode");
	String userName = approveInfos.get("userName");
	String approveInfo = approveInfos.get("approveInfo");
	String approveStatus = approveInfos.get("approveStatus");
	String updateSql1 = "";
	String updateSql2 = "";
	String updateSql3 = "";
	String updateSql4 = "";
	if (queryApproveRole(userCode).equals(SupplyConstants.APPRO_SWB)) {
	    if (approveStatus.equals("1")) {
		updateSql1 = "update po_deliveryplan set billstatus='1' ,approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='1'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql2 = "update so_pickupplan set billstatus='1',approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='1'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));

	    } else if (approveStatus.equals("2")) {
		updateSql1 = "update po_deliveryplan set billstatus='2' ,approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='2'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql2 = "update so_pickupplan set billstatus='2',approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='2'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));

	    }
//	    this.executeSql(updateSql1, userCode,userName,new Date(),approveInfo);
	    this.executeSql(updateSql2, userCode,userName,new Date(),approveInfo);
	} else if (queryApproveRole(userCode).equals(SupplyConstants.APPRO_CWB)) {
	    if (approveStatus.equals("1")) {
		updateSql1 = "update po_deliveryplan set billstatus='1' ,approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='1'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql2 = "update so_pickupplan set billstatus='1',approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='1'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));

	    } else if (approveStatus.equals("2")) {
		updateSql1 = "update po_deliveryplan set billstatus='2' ,approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='2'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql2 = "update so_pickupplan set billstatus='2',approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='2'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));

	    }
//	    this.executeSql(updateSql1, userCode,userName,new Date(),approveInfo);
	    this.executeSql(updateSql2, userCode,userName,new Date(),approveInfo);
	} else if(queryApproveRole(userCode).equals(SupplyConstants.APPRO_SCWB)) {
	    if (approveStatus.equals("1")) {
		updateSql1 = "update po_deliveryplan set billstatus='1' ,approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='1'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql2 = "update so_pickupplan set billstatus='1',approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='1'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql3 = "update po_deliveryplan set billstatus='1' ,approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='1'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql4 = "update so_pickupplan set billstatus='1',approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='1'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));

	    } else if (approveStatus.equals("2")) {
		updateSql1 = "update po_deliveryplan set billstatus='2' ,approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='2'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql2 = "update so_pickupplan set billstatus='2',approveone =?,approveonename =?,approveonedate=?,approveoneinfo=?,approveonestatus='2'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql3 = "update po_deliveryplan set billstatus='2' ,approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='2'  where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));
		updateSql4 = "update so_pickupplan set billstatus='2',approvetwo =?,approvetwoname =?,approvetwodate=?,approvetwoinfo=?,approvetwostatus='2'   where  "
			+ JeecgSqlUtil.buildSqlForIn("id", ids.split(","));

	    }
//	    this.executeSql(updateSql1, userCode,userName,new Date(),approveInfo);
	    this.executeSql(updateSql2, userCode,userName,new Date(),approveInfo);
//	    this.executeSql(updateSql3, userCode,userName,new Date(),approveInfo);
	    this.executeSql(updateSql4, userCode,userName,new Date(),approveInfo);
	}

    }

    private String queryApproveRole(String userCode) throws Exception {
	String rolecode ="";
	String sql = "select c.rolecode from t_s_base_user a\n"
		+ "left join  t_s_role_user b\n" + "on a.id = b.userid\n"
		+ "left join t_s_role c\n" + "on b.roleid = c.id\n"
		+ "where a.username = '" + userCode + "'";
	try {
	    List<Map<String, Object>> list = this.findForJdbc(sql, null);
	    if (list != null) {
		for (Map<String, Object> map : list) {
		     rolecode += map.get("rolecode") == null ? "" : map
			    .get("rolecode").toString();
		}
		if(rolecode.contains(SupplyConstants.APPRO_CWB) && !rolecode.contains(SupplyConstants.APPRO_SWB)) {
		    return SupplyConstants.APPRO_CWB;
		}else if (rolecode.contains(SupplyConstants.APPRO_SWB)&& !rolecode.contains(SupplyConstants.APPRO_CWB)){
		    return SupplyConstants.APPRO_SWB;
		}else if(rolecode.contains(SupplyConstants.APPRO_CWB) && rolecode.contains(SupplyConstants.APPRO_SWB)){
		    return SupplyConstants.APPRO_SCWB;
		}
	    }

	} catch (Exception e) {
	    throw new Exception("数据库查询异常");
	}
	return "";
    }
}
