<%-- 
    Document   : headerSidebar
    Created on : Oct 19, 2014, 1:59:15 PM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js sidebar-large lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js sidebar-large lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js sidebar-large lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js sidebar-large"> <!--<![endif]-->

    <head>
        <!-- BEGIN META SECTION -->
        <meta charset="utf-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" href="../assets/img/favicon.png">
        <!-- END META SECTION -->
        <!-- BEGIN MANDATORY STYLE -->
        <link href="../assets/css/icons/icons.min.css" rel="stylesheet">
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="../assets/css/plugins.min.css" rel="stylesheet">
        <link href="../assets/css/style.min.css" rel="stylesheet">
        <link href="#" rel="stylesheet" id="theme-color">
        <!-- END  MANDATORY STYLE -->
        <script src="../assets/plugins/modernizr/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>

    <body data-page="blank_page">
        <!-- BEGIN TOP MENU -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sidebar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a id="menu-medium" class="sidebar-toggle tooltips">
                        <i class="fa fa-outdent"></i>
                    </a>

                </div>
                <div class="navbar-center"><a  href="dashboard.jsp" style="color:#FFF; text-decoration:none;">Electio</a></div>
                <div class="navbar-collapse collapse">
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <ul class="nav navbar-nav pull-right header-menu">

                        <!-- BEGIN NOTIFICATION DROPDOWN -->
                        <li class="dropdown" id="notifications-header">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <i class="glyph-icon flaticon-notifications"></i>
                                <span class="badge badge-danger badge-header">3</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="dropdown-header clearfix">
                                    <p class="pull-left">Notifications</p>
                                </li>
                                <li>
                                    <ul class="dropdown-menu-list withScroll" data-height="220">
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-exclamation-triangle p-r-10 f-18 c-orange"></i>

                                                <span class="dropdown-time">Just now</span>
                                            </a>
                                        </li>

                                    </ul>
                                </li>
                                <li class="dropdown-footer clearfix">
                                    <a href="#" class="pull-left">See all notifications</a>
                                    <a href="#" class="pull-right">
                                        <i class="fa fa-cog"></i>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!-- END NOTIFICATION DROPDOWN -->

                        <!-- BEGIN USER DROPDOWN -->
                        <li class="dropdown" id="user-header">
                            <a href="#" class="dropdown-toggle c-white" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img src="" alt="user avatar" width="30" class="p-r-5">
                                <span class="username">Vishal Jain</span>
                                <i class="fa fa-angle-down p-r-10"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="profile">
                                        <i class="glyph-icon flaticon-account"></i> My Profile
                                    </a>
                                </li>
                                <li>
                                    <a href="profile/account">
                                        <i class="glyph-icon flaticon-settings21"></i> Account Settings
                                    </a>
                                </li>
                                <li class="dropdown-footer clearfix">
                                    <a href="javascript:;" class="toggle_fullscreen" title="Fullscreen">
                                        <i class="glyph-icon flaticon-fullscreen3"></i>
                                    </a>
                                    <a href="profile/lockScreen" title="Lock Screen">
                                        <i class="glyph-icon flaticon-padlock23"></i>
                                    </a>
                                    <a href="profile/logout" title="Logout">
                                        <i class="fa fa-power-off"></i>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!-- END USER DROPDOWN -->

                    </ul>
                    <!-- END TOP NAVIGATION MENU -->
                </div>
            </div>
        </nav>
        <!-- END TOP MENU -->
        <!-- BEGIN WRAPPER -->
        <div id="wrapper">
            <!-- BEGIN MAIN SIDEBAR -->
            <nav id="sidebar">
                <div id="main-menu">
                    <ul class="sidebar-nav">
                        <li>
                            <a href="dashboard.jsp"><i class="fa fa-dashboard"></i><span class="sidebar-text">Dashboard</span></a>
                        </li>

                        <li>
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span class="sidebar-text">Election</span>
                                <span class="fa arrow"></span></a>
                            <ul class="submenu collapse">
                                <li>
                                    <a href="listElections.jsp"><span class="sidebar-text"><i class="fa fa-list"></i> List All</span></a>
                                </li>

                                <li>
                                    <a href="newElection.jsp"><span class="sidebar-text"><i class="fa fa-pencil"></i>Create New</span></a>
                                </li>

                            </ul>
                        </li>
                        <br>
                    </ul>
                </div>
            </nav>
            <!-- END MAIN SIDEBAR -->
