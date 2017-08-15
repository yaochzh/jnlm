<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>提货计划主表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
  function checkvehicle(){ 
	  var size = $('#add_soPickupplanB_table tr').length;
	  var vehicles =[];
	  var surplusnum = document.getElementById("surplusnum").value;
	  if (surplusnum <= 0){
		  $.messager.alert('错误', "剩余数量必须大于0，不允许保存！");
          return false;  
	  }
	  if(size==0) {
		  $.messager.alert('错误', "表体不能为空！");
          return false;  
	  }
	  if(size>1) {
		  $.messager.alert('错误', "提货计划，只能分配一辆车子！");
          return false;  
	  }
	  for(var i=0;i<size;i++) {
		  var vehiclelicense = document.getElementById("soPickupplanBList["+i+"].vehiclelicense").value;
		 //var carrier = document.getElementById("soPickupplanBList["+i+"].carrier").value;
		  //if(vehiclelicense.length==0 ||carrier.length==0 ){
		  if(vehiclelicense.length==0){
			  $.messager.alert('错误', "车号或承运商不能为空！");
	            return false;
		  }
		  var myselect = document.getElementById("soPickupplanBList["+i+"].vehiclelicense");
		  var index=myselect.selectedIndex ;  
		  vehicles[i] = myselect.options[index].text;
	  }
	  for(var i=0;i<size;i++) {
		  for(var j=i+1;j<size;j++) {
			  var vehiclelicense = document.getElementById("soPickupplanBList["+i+"].vehiclelicense").value;
			  //var carrier = document.getElementById("soPickupplanBList["+i+"].carrier").value;
			  var vehiclelicense1 = document.getElementById("soPickupplanBList["+j+"].vehiclelicense").value;
			  //var carrier1 = document.getElementById("soPickupplanBList["+j+"].carrier").value;
			  //if(vehiclelicense==vehiclelicense1&&carrier==carrier1){
			  if(vehiclelicense==vehiclelicense1){
				  $.messager.alert('错误', "承运商对应车号不能重复！");  
				  return false;
			  }
		  }
		  }
	//check for allvehicle
		var vnote = document.getElementById("vnote").value;
	    var materialcode = document.getElementById("materialcode").value;
		if(vnote.length!=0 && materialcode=='1106000002'){
		  var vnotes =[];
		 	vnotes = vnote.split("，");
		 	for(var m = 0; m < vnotes.length; m++) {
		 		var isExist = true;
		 		for ( var n = 0; n < vehicles.length; n++) {
		 			if (vnotes[m]==vehicles[n]){
		 				isExist = false
		 			}
		 		}
		 		if(isExist){
		 			 $.messager.alert('错误', "车号："+vnotes[m]+"；此车辆没有被安排提货，请重新选择车辆！！");  
		 			return false;
		 		}
			}
		}
	}
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="soPickupplanController.do?doAdd"  beforeSubmit="checkvehicle()">
					<%--<input id="id" name="id" type="hidden" value="${soPickupplanPage.id }">
					<input id="createName" name="createName" type="hidden" value="${soPickupplanPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${soPickupplanPage.createBy }">
					<input id="updateName" name="updateName" type="hidden" value="${soPickupplanPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${soPickupplanPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${soPickupplanPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${soPickupplanPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${soPickupplanPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${soPickupplanPage.bpmStatus }">
					<input id="createDatetime" name="createDatetime" type="hidden" value="${soPickupplanPage.createDatetime }">
					<input id="updateDatetime" name="updateDatetime" type="hidden" value="${soPickupplanPage.updateDatetime }">
					<input id="unloadplace" name="unloadplace" type="hidden" value="${soPickupplanPage.unloadplace }">
					<input id="dr" name="dr" type="hidden" value="${soPickupplanPage.dr }">
					<input id="pickupbegindate" name="pickupbegindate" type="hidden" value="${soPickupplanPage.pickupbegindate }">
					<input id="billstatus" name="billstatus" type="hidden" value="${soPickupplanPage.billstatus }">
					<input id="ts" name="ts" type="hidden" value="${soPickupplanPage.ts }">
					<input id="approve" name="approve" type="hidden" value="${soPickupplanPage.approve }">
					<input id="approvedate" name="approvedate" type="hidden" value="${soPickupplanPage.approvedate }">
					<input id="cancel" name="cancel" type="hidden" value="${soPickupplanPage.cancel }">
					<input id="canceldate" name="canceldate" type="hidden" value="${soPickupplanPage.canceldate }">--%>
					<input id="customercode" name="customercode" type="hidden" value="${soPickupplanPage.customercode }">
					<input id="trancode" name="trancode" type="hidden" value="${soPickupplanPage.trancode }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">单据日期:</label>
			</td>
			<td class="value">
					  <input id="createDate" name="createDate" type="text" style="width: 150px" 
							 class="Wdate" readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单据日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">计划单号:</label>
			</td>
			<td class="value">
		     	 <input id="pickupplancode" name="pickupplancode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.pickupplancode}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">计划单号</label>
			</td>
			
			<td align="right">
				<label class="Validform_label">销售订单号:</label>
			</td>
			<td class="value">
		     	 <input id="soordercode" name="soordercode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.soordercode}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">销售订单号</label>
			</td>
		</tr>
		<tr>
			
			<td align="right">
				<label class="Validform_label">物料编码:</label>
			</td>
			<td class="value">
		     	 <input id="materialcode" name="materialcode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.materialcode}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物料编码</label>
			</td>
			<td align="right">
				<label class="Validform_label">物料名称:</label>
			</td>
			<td class="value">
		     	 <input id="materialname" name="materialname" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.materialname}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物料名称</label>
			</td>
			
			<td align="right">
				<label class="Validform_label">规格型号:</label>
			</td>
			<td class="value">
		     	 <input id="spec" name="spec" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.spec}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">规格型号</label>
			</td>
		</tr>
		<tr>
		<td align="right">
				<label class="Validform_label">单位:</label>
			</td>
			<td class="value">
		     	 <input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.unit}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">客户名称:</label>
			</td>
			<td class="value">
		     	 <input id="customername" name="customername" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.customername}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			<%--</td>
				<td align="right">
				<label class="Validform_label">付款客户:</label>
			</td>
			<td class="value">
		     	 <!-- <input id="delivercustomer" name="delivercustomer" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.delivercustomer}' readOnly="true"> -->
				
				<t:dictSelect id ="delivercustomer"  field="delivercustomer" type="list"
										dictTable="t_s_depart" dictField="id" dictText="departname" defaultVal="" hasLabel="false"  title="客户档案" datatype = "*"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款客户</label>
			</td>
			
		</tr>--%>
		</td>
				<td align="right">
				<label class="Validform_label">运输商名称:</label>
			</td>
			<td class="value">
		     	<input id="tranname" name="tranname" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.tranname}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运输商名称</label>
			</td>
			
		</tr>
		<tr>
		
			<td align="right">
				<label class="Validform_label">仓库:</label>
			</td>
			<td class="value">
		     	 <input id="store" name="store" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.store}' >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">仓库</label>
			</td>
			
			<td align="right">
				<label class="Validform_label">剩余数量:</label>
			</td>
			<td class="value">
		     	 <input id="surplusnum" name="surplusnum" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.surplusnum}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">剩余数量</label>
			</td>
			
			<td align="right">
				<label class="Validform_label">到期日期:</label>
			</td>
			<td class="value">
					  <input id="expiredate" name="expiredate" type="text" style="width: 150px" 
							 class="Wdate"  value='${soPickupplanPage.expiredate}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">到期日期</label>
			</td>
			
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">已计划未提量</label>
			</td>
			<td class="value">
		      <input id="planassnum" name="planassnum" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.planassnum}' >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">已计划未提量</label>
			</td>
			
			
			<td align="right">
				<label class="Validform_label">接收单位</label>
			</td>
			<td class="value">
		      <input id="receiver" name="receiver" type="text" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">接收单位</label>
			</td>
			
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="vnote" name="vnote" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.vnote}' readOnly="true">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="soPickupplanController.do?soPickupplanBList&id=${soPickupplanPage.id}" icon="icon-search" title="提货计划子表" id="soPickupplanB"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_soPickupplanB_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
			 
				  <!-- <td align="left" width = "126px">
							<t:dictSelect id ="soPickupplanBList[#index#].carrier"  field="soPickupplanBList[#index#].carrier" type="list"
									dictCondition = "fuck" 	dictTable="bd_carrier" dictField="id" dictText="carriername" defaultVal="" hasLabel="false"  title="承运单位"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">承运单位</label>
				  </td> 
				  
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].pickupnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">收货数量</label>
				  </td>
				  <td align="left">
							<input name="soPickupplanBList[#index#].plandate" maxlength="32" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"
					  		 >
					  <label class="Validform_label" style="display: none;">计划到货日期</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="soPickupplanBList[#index#].packtype" type="list"
										typeGroupCode="packtype" defaultVal="" hasLabel="false"  title="包装类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">包装类型</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].carstatus" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">车辆状态</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].surplusnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">剩余数量</label>
				  </td>


			<td align="left">
				<input name="soPickupplanBList[#index#].meafinish" maxlength="9"
					   type="text" class="inputxt"  style="width:120px;" >
				<label class="Validform_label" style="display: none;">计量完成</label>
			</td>
			<td align="left">
				<input name="soPickupplanBList[#index#].tarenum" maxlength="9"
					   type="text" class="inputxt"  style="width:120px;" >
				<label class="Validform_label" style="display: none;">皮重</label>
			</td>

			<td align="left">
				<input name="soPickupplanBList[#index#].grossnum" maxlength="9"
					   type="text" class="inputxt"  style="width:120px;"  >
				<label class="Validform_label" style="display: none;">毛重</label>
			</td>

			<td align="left">
				<input name="soPickupplanBList[#index#].netnum" maxlength="9"
					   type="text" class="inputxt"  style="width:120px;"  >
				<label class="Validform_label" style="display: none;">净重</label>
			</td>-->
				  <td align="left" width = "126px">
							<t:dictSelect id ="soPickupplanBList[#index#].vehiclelicense"  field="soPickupplanBList[#index#].vehiclelicense" type="list"
									dictCondition = "fuck" 	dictTable="bd_vehicle" dictField="id" dictText="vehiclelicense" defaultVal="" hasLabel="false"  title="车牌号" datatype = "*"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">车牌号</label>
				  </td>
				  <td align="left">
				  <input id="soPickupplanBList[#index#].drivername" name="soPickupplanBList[#index#].drivername" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype = "*">
					  <label class="Validform_label" style="display: none;">驾驶员</label>
				  </td>
				  <td align="left">
					  	<input id="soPickupplanBList[#index#].idcard" name="soPickupplanBList[#index#].idcard" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype = "*">
					  <label class="Validform_label" style="display: none;">身份证号</label>
				  </td>
				  <td align="left">
					  	<input id="soPickupplanBList[#index#].phone" name="soPickupplanBList[#index#].phone" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype = "*">
					  <label class="Validform_label" style="display: none;">手机号</label>
				  </td>
				  <td align="left">
					  	<input id="soPickupplanBList[#index#].loadnum" name="soPickupplanBList[#index#].loadnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">标准载量</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].planpieces" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">预提包数</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].plannum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">预提数量</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].vnote" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">备注</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/supply/pickupplan/soPickupplan.js"></script>
	