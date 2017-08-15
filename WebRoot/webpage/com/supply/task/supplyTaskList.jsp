<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="poDeliveryplanList" checkbox="true" showPageList="false" fitColumns="false" fit="true"  showText="false" title="待审批计划" actionUrl="supplyTaskController.do?datagrid" idField="id"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据日期"  field="SysOrgCode"  queryMode="group"  width="150"></t:dgCol>
   <t:dgCol title="提交人"  field="tranname"   queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="计划单号"  field="poordercode"    width="180"></t:dgCol>
   <t:dgCol title="客户/供应商"  field="trancode"   queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="运输商"  field="supplercode"   queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="物料名称"  field="supplername"    queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="规格型号"  field="spec" queryMode="single"  width="120"></t:dgCol>
    <%--<t:dgCol title="物料编码"  field="materialcode"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料名称"  field="materialname"   queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="采购订单数量"  field="store"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到货量"  field="unloadplace"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="剩余数量"  field="surplusnum" width="120"></t:dgCol>
   <t:dgCol title="单价"  field="vnote" width="120"></t:dgCol>
  
   <t:dgCol title="物料编码"  field="materialcode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="承运单位"  field="carrier"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购订单数量"  field="poordernum" width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="store"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到期日期"  field="expiredate" formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="卸货地点"  field="unloadplace"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="vnote"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间戳"  field="ts" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核日期"  field="approvedate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="作废人"  field="cancel"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="作废日期"  field="canceldate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期时间"  field="createDatetime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
   <t:dgCol title="修改日期时间"  field="updateDatetime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   --%>
   <t:dgToolBar title="审核"  icon="icon-edit" url="supplyTaskController.do?goApprove" funname="approveSelect" width="300" height="200"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="poDeliveryplanController.do?goUpdate" funname="detail" width="1200" height="500"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/supply/deliveryplan/poDeliveryplanList.js"></script>	