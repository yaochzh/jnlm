<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>送货计划主表</title>
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
  function addTwo()
  {
  	$(this).val(parseFloat($(this).val()).toFixed(2));
  }
  function checkvehicle(){ 
	  var size = $('#add_poDeliveryplanB_table tr').length;
	  if(size==0) {
		  $.messager.alert('错误', "表体不能为空！");
          return false;  
	  }
	  var surplusnum = document.getElementById("surplusnum").value;
	  if (surplusnum <= 0){
		  $.messager.alert('错误', "剩余数量必须大于0，不允许保存！");
          return false;  
	  }
	  for(var i=0;i<size;i++) {
		  var vehiclelicense = document.getElementById("poDeliveryplanBList["+i+"].vehiclelicense").value;
		  //var carrier = document.getElementById("poDeliveryplanBList["+i+"].carrier").value;
		  //if(vehiclelicense.length==0 ||carrier.length==0 ){
		  if(vehiclelicense.length==0  ){
			  $.messager.alert('错误', "车号或承运商不能为空！");
	            return false;
		  }
	  }
	  var materialcode = document.getElementById("materialcode").value;
	  if(materialcode == '110500000') {
		  for(var i=0;i<size;i++) {
		  var outpoundbillcode = document.getElementById("poDeliveryplanBList["+i+"].outpoundbillcode").value;
		  if(outpoundbillcode.length==0 ){
			  $.messager.alert('错误', "出厂磅单号不能为空！");
	            return false;
		  }
		  }
	  }
	  for(var i=0;i<size;i++) {
		  for(var j=i+1;j<size;j++) {
			  var vehiclelicense = document.getElementById("poDeliveryplanBList["+i+"].vehiclelicense").value;
			  //var carrier = document.getElementById("poDeliveryplanBList["+i+"].carrier").value;
			  var vehiclelicense1 = document.getElementById("poDeliveryplanBList["+j+"].vehiclelicense").value;
			  //var carrier1 = document.getElementById("poDeliveryplanBList["+j+"].carrier").value;
			  //if(vehiclelicense==vehiclelicense1&&carrier==carrier1){
			  if(vehiclelicense==vehiclelicense1){
				  $.messager.alert('错误', "承运商对应车号不能重复！");  
				  return false;
			  }
		  }
	  }
	  
	}
 </script>
</head>
<body style="overflow-x: hidden;">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" tiptype="1" action="poDeliveryplanController.do?doAdd" beforeSubmit="checkvehicle()">
		<input id="id" name="id" type="hidden"
			value="${poDeliveryplanPage.id }">
		<%--<input id="createName" name="createName" type="hidden"
			value="${poDeliveryplanPage.createName }">
		<input id="createBy" name="createBy" type="hidden"
			value="${poDeliveryplanPage.createBy }">
		<input id="createDate" name="createDate" type="hidden"
			value="${poDeliveryplanPage.createDate }">
		<input id="updateName" name="updateName" type="hidden"
			value="${poDeliveryplanPage.updateName }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${poDeliveryplanPage.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${poDeliveryplanPage.updateDate }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden"
			value="${poDeliveryplanPage.sysOrgCode }">
		<input id="sysCompanyCode" name="sysCompanyCode" type="hidden"
			value="${poDeliveryplanPage.sysCompanyCode }">
		<input id="bpmStatus" name="bpmStatus" type="hidden"
			value="${poDeliveryplanPage.bpmStatus }">
		<input id="createDatetime" name="createDatetime" type="hidden"
			value="${poDeliveryplanPage.createDatetime }">
		<input id="updateDatetime" name="updateDatetime" type="hidden"
			value="${poDeliveryplanPage.updateDatetime }">
		<input id="approve" name="approve" type="hidden"
			value="${poDeliveryplanPage.approve }">
		--%><input id="dr" name="dr" type="hidden"
			value="${poDeliveryplanPage.dr }">
		<input id="billstatus" name="billstatus" type="hidden"
			value="${poDeliveryplanPage.billstatus }">
		<input id="ts" name="ts" type="hidden"
			value="${poDeliveryplanPage.ts }">
		<input id="approvedate" name="approvedate" type="hidden"
			value="${poDeliveryplanPage.approvedate }">
		<input id="cancel" name="cancel" type="hidden"
			value="${poDeliveryplanPage.cancel }">
		<input id="canceldate" name="canceldate" type="hidden"
			value="${poDeliveryplanPage.canceldate }">
		<input id="poordernum" name="poordernum" type="hidden"
			value="${poDeliveryplanPage.poordernum }">
		<input id="supplercode" name="supplercode" type="hidden"
			value="${poDeliveryplanPage.supplercode }">
		<input id="trancode" name="trancode" type="hidden"
			value="${poDeliveryplanPage.trancode }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right"><label class="Validform_label">计划单号:</label>
				</td>
				<td class="value"><input id="deliveryplancode"
					name="deliveryplancode" type="text" style="width: 150px"
					class="inputxt" value='${poDeliveryplanPage.deliveryplancode}' readOnly="true" > <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">计划单号</label></td>
				<td align="right"><label class="Validform_label">合同编号:</label>
				</td>
				<td class="value"><input id="contractcode" name="contractcode"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.contractcode}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">合同编号</label></td>
				<td align="right"><label class="Validform_label">到期日期:</label>
				</td>
				<td class="value"><input id="expiredate" name="expiredate"
					type="text" style="width: 150px" class="Wdate" type="date" onClick="WdatePicker()" pattern="yyyy-MM-dd" 
					value=<fmt:formatDate value='${poDeliveryplanPage.expiredate}'/>>  <span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">到期日期</label>
				</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">供应商名称:</label>
				</td>
				<td class="value"><input id="supplername" name="supplername"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.supplername}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">供应商名称</label></td>

				<td align="right"><label class="Validform_label">物料编码:</label>
				</td>
				<td class="value"><input id="materialcode" name="materialcode"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.materialcode}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">物料编码</label></td>
				<td align="right"><label class="Validform_label">物料名称:</label>
				</td>
				<td class="value"><input id="materialname" name="materialname"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.materialname}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">物料名称</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">规格:</label></td>
				<td class="value"><input id="spec" name="spec" type="text"
					style="width: 150px" class="inputxt" value='${poDeliveryplanPage.spec}' > <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">规格</label></td>
				<td align="right"><label class="Validform_label">卸货地点:</label>
				</td>
				<td class="value"><input id="unloadplace" name="unloadplace"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.unloadplace}' > <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">卸货地点</label></td>
				<td align="right"><label class="Validform_label">仓库:</label></td>
				<td class="value"><input id="store" name="store" type="text"
					style="width: 150px" class="inputxt" value='${poDeliveryplanPage.store}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">仓库</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">采购订单号:</label>
				</td>
				<td class="value"><input id="poordercode" name="poordercode"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.poordercode}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">采购订单号</label></td>
				<td align="right"><label class="Validform_label">剩余数量:</label>
				</td>
				<td class="value"><input id="surplusnum" name="surplusnum"	
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.surplusnum}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">剩余数量</label></td>
				<td align="right"><label class="Validform_label">备注:</label></td>
				<td class="value"><input id="vnote" name="vnote" type="text"
					style="width: 150px" class="inputxt" value='${poDeliveryplanPage.vnote}'> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">备注</label></td>
			</tr>
			<tr>
				
				<td align="right"><label class="Validform_label">单据日期</label>
				</td>
				<td class = "value"><input id="createDate" name="createDate"
					type="text" style="width: 150px" class="Wdate"  type="date" pattern="yyyy-MM-dd" readOnly="true" value=<fmt:formatDate value='${poDeliveryplanPage.createDate}'/>>
				<td align="right"><label class="Validform_label">运输商名称</label></td>
				<td class="value"><input id="tranname" name="tranname"
					type="text" style="width: 150px" class="inputxt" value='${poDeliveryplanPage.tranname}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">运输商名称</label></td>
				<td align="right"><label class="Validform_label"></label></td>
				<td class="value"><span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;"></label></td>
			</tr>
		</table>
		<div style="width: auto;height: 200px;">
			<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
			<div style="width:800px;height:1px;"></div>
			<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				<t:tab
					href="poDeliveryplanController.do?poDeliveryplanBList&id=${poDeliveryplanPage.id}"
					icon="icon-search" title="送货计划子表" id="poDeliveryplanB"></t:tab>
			</t:tabs>
		</div>
	</t:formvalid>
	<!-- 添加 附表明细 模版 -->
	<table style="display:none">
		<tbody id="add_poDeliveryplanB_table_template">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh"></div>
				</td>
				<td align="center"><input style="width:25px;" type="checkbox"
					name="ck" />
				</td>
				<%--<td align="left"><!-- <input id = "cc"
					name="poDeliveryplanBList[#index#].vehiclelicense" type="text" class="inputxt" maxlength="32" style="width:120px;" datatype="*" errormsg="该字段不为空"> <label
					class="Validform_label" style="display: none;">车号</label> -->
			
			<t:dictSelect id = "poDeliveryplanBList[#index#].carrier"  field="poDeliveryplanBList[#index#].carrier" type="list" dictCondition = "fuck"
										dictTable="bd_carrier" dictField="id" dictText="carriername" defaultVal="" hasLabel="false"  title="承运单位"></t:dictSelect>     

       			 <label class="Validform_label" style="display: none;">承运单位</label>
			
					</td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].unloadplace" maxlength="200"
					type="text" class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">卸货地点</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].content" maxlength="9"
					type="text" class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">含量</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].deliverynum" maxlength="9"
					type="text" class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">供应商送货数量</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].deliverydate" maxlength="32"
					type="text" class="Wdate" onClick="WdatePicker()"
					style="width:120px;"> <label
					class="Validform_label" style="display: none;">送货日期</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].plandate" maxlength="32"
					type="text" class="Wdate" onClick="WdatePicker()"
					style="width:120px;"> <label
					class="Validform_label" style="display: none;">计划到货日期</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].discou" maxlength="9"
					type="text" class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">折百</label></td>
					
				<td align="left">
					<input name="poDeliveryplanBList[#index#].meafinish" maxlength="9"
						   type="text" class="inputxt"  style="width:120px;">
					<label class="Validform_label" style="display: none;">计量完成</label>
				</td>
				<td align="left">
					<input name="poDeliveryplanBList[#index#].tarenum" maxlength="9"
						   type="text" class="inputxt"  style="width:120px;" >
					<label class="Validform_label" style="display: none;">皮重</label>
				</td>
				<td align="left">
					<input name="poDeliveryplanBList[#index#].grossnum" maxlength="9"
						   type="text" class="inputxt"  style="width:120px;" >
					<label class="Validform_label" style="display: none;">毛重</label>
				</td>
				<td align="left">
					<input name="poDeliveryplanBList[#index#].netnum" maxlength="9"
						   type="text" class="inputxt"  style="width:120px;" >
					<label class="Validform_label" style="display: none;">净重</label>
				</td>
				--%><td align="left">
				<t:dictSelect id = "poDeliveryplanBList[#index#].vehiclelicense"  field="poDeliveryplanBList[#index#].vehiclelicense" type="list" dictCondition = "fuck"
										dictTable="bd_vehicle" dictField="id" dictText="vehiclelicense" defaultVal="" hasLabel="false"  title="车牌号"></t:dictSelect>     

       			 <label class="Validform_label" style="display: none;">车牌号</label>
					</td>
				<td align="left" width = "126px"><input
					name="poDeliveryplanBList[#index#].drivername" maxlength="32" id="poDeliveryplanBList[#index#].drivername"
					type="text" class="inputxt" style="width:120px;" datatype = "*">      <label
					class="Validform_label" style="display: none;">驾驶员</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].idcard" maxlength="32" id="poDeliveryplanBList[#index#].idcard"
					type="text" class="inputxt" style="width:120px;" datatype="*" errormsg="该字段不为空"> <label
					class="Validform_label" style="display: none;">身份证号</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].phone" maxlength="32" id="poDeliveryplanBList[#index#].phone" 
					type="text" class="inputxt" style="width:120px;" datatype="m" errormsg="请输入正确的手机号"> <label
					class="Validform_label" style="display: none;">手机号</label></td>
				<td align="left"><input
					name="poDeliveryplanBList[#index#].loadnum" maxlength="9" id="poDeliveryplanBList[#index#].loadnum"
					type="text" class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">标准载重</label></td>
				
				<td align="left"><input
					name="poDeliveryplanBList[#index#].carstatus" maxlength="32"
					type="text" class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">车辆状态</label></td>
				<td align="left">
					<input name="poDeliveryplanBList[#index#].outnetnum" maxlength="9"
						   type="text" class="inputxt"  style="width:120px;"    onblur="addTwo()" datatype = "*">
					<label class="Validform_label" style="display: none;">出厂净重</label>
				</td>
				<td align="left">
					<input name="poDeliveryplanBList[#index#].outpoundbillcode" maxlength="20" id = "poDeliveryplanBList[#index#].outpoundbillcode" 
						   type="text" class="inputxt"  style="width:120px;" datatype = "*">
					<label class="Validform_label" style="display: none;">出厂磅单号</label>
				</td>
				<td align="left">
					<input name="poDeliveryplanBList[#index#].outbalancenum" maxlength="9"
						   type="text" class="inputxt"  style="width:120px;"   ignore="ignore" datatype = "*">
					<label class="Validform_label" style="display: none;">出厂结算量</label>
				</td>
			</tr>
		</tbody>
	</table>
</body>
<script src="webpage/com/supply/deliveryplan/poDeliveryplan.js"></script>