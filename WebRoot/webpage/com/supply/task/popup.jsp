<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
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
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="supplyTaskController.do?doApprove" tiptype="1" >
					<input id="ids" name="ids" type="hidden" value="${ids }">
				<input id=approveStatus name="approveStatus" type="hidden" value="${approveStatus }">
		<table style="width: 300px;" cellpadding="0" cellspacing="1" class="formtable">
					
					<tr>
						 <textarea type="text" style="width: 90%;" rows="10" name="approveInfo" value="${approveInfo }" id="approveInfo"></textarea> </label>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/supply/vehicle/vehicle.js"></script>		
