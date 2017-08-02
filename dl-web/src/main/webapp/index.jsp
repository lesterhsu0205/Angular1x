<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>宇曦國際交通顧問監造管理系統</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="system-asset/css/bootstrap.css" rel="stylesheet">
	    <link href="system-asset/plugins/bootstrap-notify-3.1.3/animate.css" rel="stylesheet">
	    <link href="system-asset/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
	
	    <!-- Custom CSS -->
	    <link href="system-asset/css/sb-admin.css" rel="stylesheet">
	
	    <!-- Morris Charts CSS -->
	    <link href="system-asset/css/plugins/morris.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="system-asset/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	
	</head>
	
	<body  ng-controller="mainController">
	
	    <div id="wrapper">
	
	        <!-- Navigation -->
	        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="index.jsp"><i class="fa fa-fw fa-line-chart"></i>宇曦國際交通顧問</a>
	            </div>
	            <!-- Top Menu Items -->
	            <ul class="nav navbar-right top-nav">
	                <!-- <li class="dropdown">
	                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
	                    <ul class="dropdown-menu message-dropdown">
	                        <li class="message-preview">
	                            <a href="#">
	                                <div class="media">
	                                    <span class="pull-left">
	                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
	                                    </span>
	                                    <div class="media-body">
	                                        <h5 class="media-heading"><strong>John Smith</strong>
	                                        </h5>
	                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
	                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
	                                    </div>
	                                </div>
	                            </a>
	                        </li>
	                        <li class="message-preview">
	                            <a href="#">
	                                <div class="media">
	                                    <span class="pull-left">
	                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
	                                    </span>
	                                    <div class="media-body">
	                                        <h5 class="media-heading"><strong>John Smith</strong>
	                                        </h5>
	                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
	                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
	                                    </div>
	                                </div>
	                            </a>
	                        </li>
	                        <li class="message-preview">
	                            <a href="#">
	                                <div class="media">
	                                    <span class="pull-left">
	                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
	                                    </span>
	                                    <div class="media-body">
	                                        <h5 class="media-heading"><strong>John Smith</strong>
	                                        </h5>
	                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
	                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
	                                    </div>
	                                </div>
	                            </a>
	                        </li>
	                        <li class="message-footer">
	                            <a href="#">Read All New Messages</a>
	                        </li>
	                    </ul>
	                </li> -->
	                <!-- <li class="dropdown">
	                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
	                    <ul class="dropdown-menu alert-dropdown">
	                        <li>
	                            <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
	                        </li>
	                        <li>
	                            <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
	                        </li>
	                        <li>
	                            <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
	                        </li>
	                        <li>
	                            <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
	                        </li>
	                        <li>
	                            <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
	                        </li>
	                        <li>
	                            <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
	                        </li>
	                        <li class="divider"></li>
	                        <li>
	                            <a href="#">View All</a>
	                        </li>
	                    </ul>
	                </li> -->
	                <li class="dropdown">
	                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>&nbsp;${sessionScope.loginUser.name}&nbsp;<b class="caret"></b></a>
	                    <ul class="dropdown-menu">
	                        <!-- <li>
	                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
	                        </li>
	                        <li>
	                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
	                        </li>-->
	                        <li>
	                            <a href="javascript:;" ng-click="initModifyPassword()">
	                            	<i class="fa fa-fw fa-gear"></i> 修改密碼
                            	</a>
	                        </li> 
	                        <li class="divider"></li>
	                        <li>
	                            <a href="logout.action"><i class="fa fa-fw fa-power-off"></i> 登出</a>
	                        </li>
	                    </ul>
	                </li>
	            </ul>
	            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	            <div class="collapse navbar-collapse navbar-ex1-collapse">
	                <ul class="nav navbar-nav side-nav">
	                    <!-- <li>
	                        <a href="javascript:;" data-toggle="collapse" data-target="#demo">
	                        	<i class="fa fa-fw fa-arrows-v"></i> 
	                        		example 
                        		<i class="fa fa-fw fa-caret-down"></i>
                        	</a>
	                        <ul id="demo" class="collapse">
			                    <li>
			                        <a href="#/dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
			                    </li>
			                    <li>
			                        <a href="#/charts"><i class="fa fa-fw fa-bar-chart-o"></i> Charts</a>
			                    </li>
			                    <li>
			                        <a href="#/tables"><i class="fa fa-fw fa-table"></i> Tables</a>
			                    </li>
			                    <li>
			                        <a href="#/forms"><i class="fa fa-fw fa-edit"></i> Forms</a>
			                    </li>
			                    <li>
			                        <a href="#/bootstrapElements"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
			                    </li>
			                    <li>
			                        <a href="#/bootstrapGrid"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
			                    </li>
			                    <li>
			                        <a href="#/blankPage"><i class="fa fa-fw fa-file"></i> Blank Page</a>
			                    </li>
	                        </ul>
	                    </li> -->
	                    <li>
	                        <a href="#/createTicket"><i class="fa fa-fw fa-dashboard"></i> 建立工單</a>
	                    </li>
	                    <li>
	                        <a href="#/queryTicket"><i class="fa fa-fw fa-table"></i> 查詢工單</a>
	                    </li>
                    	<!-- roleId == 1 : 系統管理員 -->
	                    <c:choose>
							<c:when test="${sessionScope.loginUser.roleId == 1}">
			                    <li>
			                        <a href="#/userBoard"><i class="fa fa-fw fa-users"></i> 人員管理</a>
			                    </li>
							</c:when>
						</c:choose>
	                    <!-- <li>
	                        <a href="#/test"><i class="fa fa-fw fa-users"></i> 檔案上傳測試</a>
	                    </li> -->
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->
	        </nav>
	
	        <div id="page-wrapper">
	
	            <div class="container-fluid">
					<div class="ng-view"></div>
	            </div>
	            <!-- /.container-fluid -->
	
	        </div>
	        <!-- /#page-wrapper -->
	
	    </div>
	    
	    <div class="modal fade" id="{{modalKey.modifyPassword}}" tabindex="-1">
			<div class="modal-dialog modal-med">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" >修改密碼</h4>
					</div>
					<div class="modal-body form-horizontal">
						<div class="form-group">
							<label class="control-label col-lg-3">舊密碼</label>
			          		<div class="col-lg-6">
			          			<input type="password" class="form-control" ng-model="modifyPassword.oldPassword">
			                </div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3">新密碼</label>
			          		<div class="col-lg-6">
			          			<input type="password" class="form-control" ng-model="modifyPassword.newPassword">
			                </div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3">再次輸入新密碼</label>
			          		<div class="col-lg-6">
			          			<input type="password" class="form-control" ng-model="modifyPassword.newPassword2">
			                </div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" 
							    class="btn btn-primary" ng-click="modifyPasswordConfirm()">
								確定
						</button>
					</div>
				</div>
			</div>
		</div>
	    
	    <script type="text/javascript">
	    	window.contextPath = "${pageContext.request.contextPath}";
	    </script>
	    
	    <!-- /#wrapper -->
	    <script data-main="app/main/init.js" src="system-asset/js/require.js"></script>
	</body>
</html>