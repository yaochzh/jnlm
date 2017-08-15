<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
<head>
<t:base type="jquery,easyui,tools"></t:base>
        <script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
		<link rel="stylesheet" href="plug-in/Validform/css/style.css" type="text/css"/>
		<link rel="stylesheet" href="plug-in/Validform/css/tablefrom.css" type="text/css"/>
	    <link rel="stylesheet" href="plug-in/Formdesign/js/ueditor/formdesign/bootstrap/css/bootstrap.css">
<script type="text/javascript">

function approve()	{
	var ids = document.getElementById('ids').value;
	var approveInfo = document.getElementById('approveInfo').value;
	$.ajax({
		type : "POST",
		url : "supplyTaskController.do?doApprove&ids="+ids+"&approveInfo="+approveInfo+"&approveStatus="+1+"",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert("请检查改车牌号对应的车辆档案信息是否完整！");
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				//暂时先不刷新
				//window.top.document.getElementById('tabiframe').contentWindow.reloadTable();
				window.top.document.getElementById('maintabs').contentWindow.reloadTable();
				//window.top.$('#maintabs').tabs('close', title);
				tip("添加成功");
			}		
		}
	});
}

function unapprove()	{
	var ids = document.getElementById('ids').value;
	var approveInfo = document.getElementById('approveInfo').value;
	$.ajax({
		type : "POST",
		url : "supplyTaskController.do?doApprove&ids="+ids+"&approveInfo="+approveInfo+"&approveStatus="+2+"",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert("请检查改车牌号对应的车辆档案信息是否完整！");
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			}		
		}
	});
}
$(function(){
	$('#approveInfo').val('请输入审批意见!(不可超过150字)');
    $('#approveInfo').focus(function(){
        if($(this).val() == '请输入审批意见!(不可超过150字)'){
        	  $(this).val()==''? $(this).val('请输入审批意见!(不可超过150字)'): $(this).val('');
            }
    }).blur(function(){
        if($(this).val() == ''){
        	  $(this).val()==''? $(this).val('请输入审批意见!(不可超过150字)'): $(this).val('');
            }
    });
   
});
</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<div class="formDetails">
 <textarea type="text" style="width: 90%;" rows="10" name="billingInfo.remark" value="" id="approveInfo"></textarea> </label>
<div class="button-row">
<div style="margin-top:5px;text-align: center;">
<input id="ids" name="ids" type="hidden" value="${ids }">
<button name="button" onclick="approve()"  class="btn btn-primary  navbar-button" type="button">确定</button>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
<button name="button" onclick="unapprove()"  class="btn btn-primary  navbar-button">驳回</button>
</div>
</div>


</body>
</html>