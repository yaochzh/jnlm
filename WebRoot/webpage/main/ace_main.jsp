<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title><t:mutiLang langKey="jeect.platform"/></title>
		<meta name="keywords" content="<t:mutiLang langKey="jeect.platform"/>" />
		<meta name="description" content="<t:mutiLang langKey="jeect.platform"/>" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="plug-in/jquery/jquery.contextmenu.css"/>
		<!-- basic styles -->
		<link href="plug-in/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="plug-in/ace/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="plug-in/ace/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<!--  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />  -->

		<!-- ace styles -->

		<link rel="stylesheet" href="plug-in/ace/assets/css/ace.min.css" />
		<link rel="stylesheet" href="plug-in/ace/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="plug-in/ace/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="plug-in/ace/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="plug-in/ace/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="plug-in/ace/assets/js/html5shiv.js"></script>
		<script src="plug-in/ace/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							自助平台
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle">
								<i class="icon-envelope icon-animated-vertical"></i>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header" id="messageTip">
									<i class="icon-envelope-alt"></i>
									0条消息
								</li>

								<li>
									<a href="#" id="messageContent">
										
									</a>
								</li>

								

								<li>
									<a href="javascript:goAllMessage();" id="messageFooter">
										查看所有消息
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="plug-in/ace/avatars/avatar2.png" alt="Jason's Photo" />
								<span class="user-info">
									<small>${userName }</small>
				                    <span style="color: #666633">${roleName }</span>
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="javascript:add('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword','',550,200)">
										<i class="icon-cog"></i>
										 <t:mutiLang langKey="common.change.password"/>
									</a>
								</li>

								<li>
									<a href="javascript:openwindow('<t:mutiLang langKey="common.profile"/>','userController.do?userinfo')">
										<i class="icon-user"></i>
										 <t:mutiLang langKey="common.profile"/>
									</a>
								</li>
								<li>
									<a href="javascript:openwindow('<t:mutiLang langKey="common.ssms.getSysInfos"/>','tSSmsController.do?getSysInfos')">
										<i class="icon-cog"></i>
										 <t:mutiLang langKey="common.ssms.getSysInfos"/>
									</a>
								</li>
								<li>
									<a href="javascript:add('<t:mutiLang langKey="common.change.style"/>','userController.do?changestyle','',550,250)">
										<i class="icon-cog"></i>
										 <t:mutiLang langKey="common.my.style"/>
									</a>
								</li>

								<li>
									<a href="javascript:clearLocalstorage()">
										<i class="icon-cog"></i>
										<t:mutiLang langKey="common.clear.localstorage"/>
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="javascript:logout()">
										<i class="icon-off"></i>
										 <t:mutiLang langKey="common.logout"/>
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
						<li class="active">
							<a  href="javascript:addTabs({id:'home',title:'首页',close: false,url: 'loginController.do?acehome'});">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 首页 </span>
							</a>
							<a  href="javascript:addTabs({id:'taskList',title:'工作任务',close: true,url: 'supplyTaskController.do?list'});">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 工作任务 </span>
							</a>
						</li>
						<t:menu style="ace" menuFun="${menuMap}"></t:menu>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12" style="width: 99%;padding-left:2px;padding-right: 2px;" id="tabs">
	                            <ul class="nav nav-tabs" role="tablist">
	                                <!-- <li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li> -->
	                            </ul>
	                            <div class="tab-content">
	                                <div role="tabpanel" class="tab-pane active" id="Index">
	                                </div>
	                            </div>
	                        </div>
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; 选择皮肤</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
							<label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
							<label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl">切换到左边</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								切换窄屏
								<b></b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->


<div id="changestylePanel" style="display:none" >
	<form id="formobj"  action="userController.do?savestyle" name="formobj" method="post">
	<table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
		<tr><td >风格</td></tr>
			<tr>
				<td class="value"><input type="radio" value="default" name="indexStyle" /> <span>经典风格</span></td>
			</tr>
			<!--
			<tr>
				<td class="value"><input type="radio" value="bootstrap" name="indexStyle" /> <span>BootStrap风格</span></td>
			</tr>
			-->
			<!-- update-start--Author:gaofeng  Date:2014-01-10 for:新增首页风格  -->
			<tr>
				<td class="value"><input type="radio" value="shortcut" name="indexStyle" /> <span>ShortCut风格</span></td>
			</tr>
			<!-- update-start--Author:gaofeng  Date:2014-01-24 for:新增首页风格  -->
			<tr>
				<td class="value"><input type="radio" value="sliding" name="indexStyle"  /><span>Sliding云桌面</span></td>
			</tr>
			<!-- update-end--Author:longjb  Date:2013-03-15 for:新增首页风格  -->	
			<tr>
				<td class="value"><input type="radio" value="ace" name="indexStyle"  /><span>ACE平面风格</span></td>
			</tr>
	</table>
	</form>
</div>
<div id="changepassword" style="display:none">

<input id="id" type="hidden" value="${user.id }">
	<table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			<tr>
				<td align="right" width="20%"><span class="filedzt">原密码:</span></td>
				<td class="value"><input id="password" type="password" value="" name="password" class="inputxt" datatype="*" errormsg="请输入原密码" /> <span class="Validform_checktip"> 请输入原密码 </span></td>
			</tr>
			<tr>
				<td align="right"><span class="filedzt">新密码:</span></td>
				<td class="value"><input  type="password" value="" name="newpassword" class="inputxt" plugin="passwordStrength" datatype="*6-18" errormsg="密码至少6个字符,最多18个字符！" /> <span
					class="Validform_checktip"> 密码至少6个字符,最多18个字符！ </span> <span class="passwordStrength" style="display: none;"> <b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span> </span></td>
			</tr>
			<tr>
				<td align="right"><span class="filedzt">重复密码:</span></td>
				<td class="value"><input id="newpassword" type="password" recheck="newpassword" datatype="*6-18" errormsg="两次输入的密码不一致！"> <span class="Validform_checktip"></span></td>
			</tr>
		</tbody>
	</table>
</div>
		<!-- basic scripts -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='plug-in/ace/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='plug-in/ace/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='plug-in/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="plug-in/ace/assets/js/bootstrap.min.js"></script>
		<script src="plug-in/ace/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="plug-in/ace/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="plug-in/ace/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="plug-in/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="plug-in/ace/assets/js/jquery.slimscroll.min.js"></script>
		<script src="plug-in/ace/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="plug-in/ace/assets/js/jquery.sparkline.min.js"></script>
		<script src="plug-in/ace/assets/js/flot/jquery.flot.min.js"></script>
		<script src="plug-in/ace/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="plug-in/ace/assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ace scripts -->

		<script src="plug-in/ace/assets/js/ace-elements.min.js"></script>
		<script src="plug-in/ace/assets/js/ace.min.js"></script>
		
		<t:base type="tools"></t:base>
		<script src="plug-in/jquery-plugs/storage/jquery.storageapi.min.js"></script>
		<script type="text/javascript" src="plug-in/ace/assets/js/bootstrap-tab.js"></script>
		<script src="plug-in/jquery/jquery.contextmenu.js"></script>
		<script src="plug-in/layer/layer.js"></script>
	    <script src="plug-in/ace/js/bootbox.js"></script>
		<!-- inline scripts related to this page -->
		<script>
		jQuery(function($) {
			//$( "#tabs" ).tabs();
			addTabs({id:'home',title:'首页',close: false,url: 'loginController.do?acehome'});
			$('.theme-poptit .close').click(function(){
	    		$('.theme-popover-mask').fadeOut(100);
	    		$('.theme-popover').slideUp(200);
	    	});
	    	$('#closeBtn').click(function(){
	    		$('.theme-popover-mask').fadeOut(100);
	    		$('.theme-popover').slideUp(200);
	    	});
	    	//$('#ace-settings-sidebar').click();
	    	//$('#sidebar').addClass('compact');
			$('#sidebar li').addClass('hover').filter('.open').removeClass('open').find('> .submenu').css('display', 'none');
		});
		</script>

		<script type="text/javascript">
			jQuery(function($) {
				$('.easy-pie-chart.percentage').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
					var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
					var size = parseInt($(this).data('size')) || 50;
					$(this).easyPieChart({
						barColor: barColor,
						trackColor: trackColor,
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: parseInt(size/10),
						animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
						size: size
					});
				})
			
				$('.sparkline').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
					$(this).sparkline('html', {tagValuesAttribute:'data-values', type: 'bar', barColor: barColor , chartRangeMin:$(this).data('min') || 0} );
				});
			
			
			
			
			  var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
			  var data = [
				{ label: "social networks",  data: 38.7, color: "#68BC31"},
				{ label: "search engines",  data: 24.5, color: "#2091CF"},
				{ label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
				{ label: "direct traffic",  data: 18.6, color: "#DA5430"},
				{ label: "other",  data: 10, color: "#FEE074"}
			  ]
			  function drawPieChart(placeholder, data, position) {
			 	  $.plot(placeholder, data, {
					series: {
						pie: {
							show: true,
							tilt:0.8,
							highlight: {
								opacity: 0.25
							},
							stroke: {
								color: '#fff',
								width: 2
							},
							startAngle: 2
						}
					},
					legend: {
						show: true,
						position: position || "ne", 
						labelBoxBorderColor: null,
						margin:[-30,15]
					}
					,
					grid: {
						hoverable: true,
						clickable: true
					}
				 })
			 }
			 drawPieChart(placeholder, data);
			
			 /**
			 we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			 so that's not needed actually.
			 */
			 placeholder.data('chart', data);
			 placeholder.data('draw', drawPieChart);
			
			
			
			  var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
			  var previousPoint = null;
			
			  placeholder.on('plothover', function (event, pos, item) {
				if(item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : " + item.series['percent']+'%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}
				
			 });
			
			
			
			
			
			
				var d1 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d1.push([i, Math.sin(i)]);
				}
			
				var d2 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d2.push([i, Math.cos(i)]);
				}
			
				var d3 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.2) {
					d3.push([i, Math.tan(i)]);
				}
				
			
				var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
				$.plot("#sales-charts", [
					{ label: "Domains", data: d1 },
					{ label: "Hosting", data: d2 },
					{ label: "Services", data: d3 }
				], {
					hoverable: true,
					shadowSize: 0,
					series: {
						lines: { show: true },
						points: { show: true }
					},
					xaxis: {
						tickLength: 0
					},
					yaxis: {
						ticks: 10,
						min: -2,
						max: 2,
						tickDecimals: 3
					},
					grid: {
						backgroundColor: { colors: [ "#fff", "#fff" ] },
						borderWidth: 1,
						borderColor:'#555'
					}
				});
			
			
				$('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('.tab-content')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			
				$('.dialogs,.comments').slimScroll({
					height: '300px'
			    });
				
				
				//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
				//so disable dragging when clicking on label
				var agent = navigator.userAgent.toLowerCase();
				if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
				  $('#tasks').on('touchstart', function(e){
					var li = $(e.target).closest('#tasks li');
					if(li.length == 0)return;
					var label = li.find('label.inline').get(0);
					if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
				});
			
				$('#tasks').sortable({
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'draggable-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					stop: function( event, ui ) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
					}
				);
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
					if(this.checked) $(this).closest('li').addClass('selected');
					else $(this).closest('li').removeClass('selected');
				});
				
			
			})
		</script>
		
		<script type="text/javascript">

		function loadModule(title,url,target){
			//TODO addTab(title,url);
			    $("#mainTitle").text(title);
      			$("#center").attr("src",url);
      	}
		

	  	function logout(){
	  		bootbox.confirm("<t:mutiLang langKey="common.exit.confirm"/>", function(result) {
	  			if(result)
		  			location.href="loginController.do?logout";
	  		});
  		}
		function opendialog(title,url,target){
			//$("#dialog").attr("src",url);
			bootbox.dialog({
				message:$("#changestylePanel").html(),
				title:title,
				buttons:{
				OK:{
				label: "OK", 
				callback:function(){
					    var indexStyle = $('input[name="indexStyle"]:checked').val();
					    if(indexStyle==undefined||indexStyle==""){
					    	indexStyle = "ace";
					    }
					    var cssTheme = $('input[name="cssTheme"]:checked').val();
					    if(cssTheme==undefined){
					    	cssTheme = "";
					    }
						var form = $("#formobj");//取iframe里的form
						$.ajax({
		        			url:form.attr('action'),
		        			type:form.attr('method'),
		        			data:"indexStyle="+indexStyle,//+"&cssTheme="+cssTheme,
		        			success:function(data){
								var d = $.parseJSON(data);
								if (d.success) {
									var msg = d.msg;
									bootbox.alert(msg);
								}else{
									bootbox.alert(d.msg);
								}
		        			},
		        			error:function(e){
		        				bootbox.alert("出错了哦");
		        			}
						});
					}
			},Cancel: {label: "CLOSE", 
				callback:function() {
						//alert('close');//$("#dialog").dialog("close");
					}
				}
			}});
  			
  	}
		function changepass(title,url,target){
			//$("#dialog").attr("src",url);
			bootbox.dialog({
				message:'<form id="formobj2"  action="userController.do?savenewpwd" name="formobj2" method="post">'
					+$("#changepassword").html()+'</form>',
				title:title,
				buttons:{
				OK:{
				label: "OK", 
				callback:function(){
					//alert('not implement');
						$.ajax({
		        			url:"userController.do?savenewpwd",
		        			type:"post",
		        			data:$('#formobj2').serialize(),// 要提交的表单 ,
		        			success:function(data){
								var d = $.parseJSON(data);
								if (d.success) {
									var msg = d.msg;
									bootbox.alert(msg);
								}else{
									bootbox.alert(d.msg);
									}
		        			},
		        			error:function(e){
		        				bootbox.alert("出错了哦");
		        			}
						});
					}
			},Cancel: {label: "CLOSE", 
				callback:function() {
						alert('close');//$("#dialog").dialog("close");
					}
				}
			}});
  			
  	}
		function profile(title,url,target){
			//$("#dialog").attr("src",url);
			bootbox.dialog({
				message:'<iframe width="100%" height="300px" src="'+url+'" style="border:1px #fff solid; background:#CCC;"></iframe>',
				title:title,
				buttons:{
				OK:{
				label: "OK"},Cancel: {label: "CLOSE"
				}
			}});
  			
  	}
//update-begin--Author:张忠亮  Date:20150605 for：清除浏览器缓存
			function clearLocalstorage(){
				var storage=$.localStorage;
				if(!storage)
					storage=$.cookieStorage;
				storage.removeAll();
				//bootbox.alert( "浏览器缓存清除成功!");
				alertTipTop("浏览器缓存清除成功!","10%");
			}
//update-end--Author:张忠亮  Date:20150605 for：清除浏览器缓存


	$(document).ready(function(){
		//加载公告
		var url = "noticeController.do?getNoticeList";
		jQuery.ajax({
    		url:url,
    		type:"GET",
    		dataType:"JSON",
    		async: false,
    		success:function(data){
    			if(data.success){
    				var noticeList = data.attributes.noticeList;
    				var noticeCount = data.obj;
    				//加载公告条数
    				if(noticeCount>99){
    					$("#noticeCount").html("99+");
    				}else{
    					$("#noticeCount").html(noticeCount);
    				}
    				//加载公告提示
    				var noticeTip = "";
    				noticeTip += "<i class='icon-warning-sign'></i>";
    				noticeTip += noticeCount+" "+data.attributes.tip;
    				$("#noticeTip").html(noticeTip);
    				
    				//加载公告条目
    				var noticeContent = "";
    				if(noticeList.length > 0){
    					for(var i=0;i<noticeList.length;i++){
    						noticeContent +="<li><a href='javascript:goNotice(&quot;"+noticeList[i].id+"&quot;)' ";
    						noticeContent +="style='word-break:keep-all;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;'>";
    						noticeContent +="<i class='btn btn-xs btn-primary fa fa-user'></i>";
    						noticeContent +="&nbsp;"+noticeList[i].noticeTitle + "</a></li></ul></li>";
        				}
    				}
    				alert(noticeContent);
    				$("#noticeContent").html(noticeContent);
    				
    				//加载公告底部文字
    				var noticeSeeAll = data.attributes.seeAll +"<i class='ace-icon fa fa-arrow-right'></i>";
    				$("#noticeFooter").html(noticeSeeAll);
    			}
    		}
    	});
		
		
		//加载消息
		var url = "tSSmsController.do?getMessageList";
		$.ajax({
    		url:url,
    		type:"GET",
    		dataType:"JSON",
    		async: false,
    		success:function(data){
    			if(data.success){
    				var messageList = data.attributes.messageList;
    				var messageCount = data.obj;
    				//加载消息条数
    				if(messageCount>99){
    					$("#messageCount").html("99+");
    				}else{
    					$("#messageCount").html(messageCount);
    				}
    				//加载消息tip提示
    				var messageTip = "";
					messageTip += "<i class='ace-icon fa fa-envelope-o'></i>";
					messageTip += messageCount+" "+data.attributes.tip;
    				$("#messageTip").html(messageTip);
    				
    				//加载消息条目（有限）
    				var messageContent = "";
    				if(messageList.length > 0){
    					for(var i=0;i<messageList.length;i++){
    						messageContent +="<li><a href='javascript:goMessage(&quot;"+messageList[i].id+"&quot;)' class='clearfix'>";
    						messageContent +="<img src='plug-in/ace/avatars/avatar3.png' class='msg-photo' alt='Alex’s Avatar' />";
    						messageContent +="<span class='msg-body'><span class='msg-title'>";
    						messageContent +="<span class='blue'>"+messageList[i].esSender+":</span>";
    						messageContent += messageList[i].esTitle + "</span>";
    						messageContent +="<span class='msg-time'><i class='ace-icon fa fa-clock-o'></i><span>"+messageList[i].esSendtimeTxt+"</span></span>";
    						messageContent +="</span></a><input id='"+messageList[i].id+"_title' type='hidden' value='"+messageList[i].esTitle+"'>";
    						messageContent +="<input id='"+messageList[i].id+"_status' type='hidden' value='"+messageList[i].esStatus+"'>";
    						messageContent +="<input id='"+messageList[i].id+"_content' type='hidden' value='"+messageList[i].esContent+"'></li>";
        				}
    				}
    				$("#messageContent").html(messageContent);
    				
    				//加载消息底部文字
    				var messageSeeAll = data.attributes.seeAll +"<i class='ace-icon fa fa-arrow-right'></i>";
    				$("#messageFooter").html(messageSeeAll);
    			}
    		}
    	});
		
	});

    function goAllNotice(){
    	var addurl = "noticeController.do?noticeList";
  		createdetailwindow("公告", addurl, 800, 400);
    }

    function goNotice(id){
  		var addurl = "noticeController.do?goNotice&id="+id;
		createdetailwindow("通知公告详情", addurl, 750, 600);
    }
    
    function goAllMessage(){
    	var addurl = "tSSmsController.do?getSysInfos";
  		createdetailwindow("消息", addurl, 800, 400);
    }
    
    function goMessage(id){
    	var title = $("#"+id+"_title").val();
    	var content = $("#"+id+"_content").val();
    	$("#msgId").val(id);
    	$("#msgTitle").html(title);
    	$("#msgContent").html(content);
    	var status = $("#"+id+"_status").val();
    	if(status==1){
    		$("#msgStatus").html("未读");
    	}else{
    		$("#msgStatus").html("已读");
    	}

    	$('.theme-popover-mask').fadeIn(100);
    	$('.theme-popover').slideDown(200);
    }
    
    function readMessage(){
    	var msgId = $("#msgId").val();
  		  var url = "tSSmsController.do?readMessage";
  			$.ajax({
  	    		url:url,
  	    		type:"GET",
  	    		dataType:"JSON",
  	    		data:{
  	    			messageId:msgId
  	    		},
  	    		success:function(data){
  	    			if(data.success){
  	    				$("#msgStatus").html("已读");
  	    				$("#"+msgId+"_status").val('2');
  	    			}
  	    		}
  	    	});
    }
		</script>
</body>
</html>

