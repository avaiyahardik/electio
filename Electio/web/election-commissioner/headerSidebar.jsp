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
        <title>
            <%
                String title = (String) request.getAttribute("title");
            %>
            <%=title%>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" href="../assets/img/favicon.png">
        <!-- END META SECTION -->
        <!-- BEGIN MANDATORY STYLE -->
        <link href="../assets/css/icons/icons.min.css" rel="stylesheet">
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="../assets/css/plugins.min.css" rel="stylesheet">
        <link href="../assets/css/style.min.css" rel="stylesheet">
        <link href="../assets/css/colors/color-light.css" rel="stylesheet">
        <!-- END  MANDATORY STYLE -->
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
                <div class="navbar-center"><a  href="#" style="text-decoration:none; font-weight: bold;"><%=title%></a></div>
                <div class="navbar-collapse collapse">
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <ul class="nav navbar-nav pull-right header-menu">

                        <!-- BEGIN USER DROPDOWN -->
                        <li class="dropdown" id="user-header">
                            <a href="#" class="dropdown-toggle c-white" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <span class="username">
                                    <% String email = (String) session.getAttribute("email");%>
                                    Welcome, <strong><%=email%></strong>
                                </span>
                                <i class="fa fa-angle-down p-r-10"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="Controller?action=profile">
                                        <i class="glyph-icon flaticon-account"></i> My Profile
                                    </a>
                                </li>

                                <li class="dropdown-footer clearfix">
                                    <a href="javascript:;" class="toggle_fullscreen" title="Fullscreen">
                                        <i class="glyph-icon flaticon-fullscreen3"></i>
                                    </a>
                                    <a href="Controller?action=lock_screen&old_action=<%= request.getParameter("action")%>" title="Lock Screen">
                                        <i class="glyph-icon flaticon-padlock23"></i>
                                    </a>
                                    <a href="Controller?action=logout_election_commissioner" title="Logout">
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
                            <a href="Controller?action=dashboard"><i class="fa fa-dashboard"></i><span class="sidebar-text">Dashboard</span></a>
                        </li>

                        <li>
                        <li>
                            <a href="Controller?action=view_elections"><span class="sidebar-text"><i class="fa fa-list"></i>List All Election</span></a>
                        </li>

                        <li>
                            <a href="Controller?action=create_new_election&mode=display"><span class="sidebar-text"><i class="fa fa-pencil"></i>Create New Election</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- END MAIN SIDEBAR -->
            <div id="main-content">
                <!-- BEGIN ERROR BOX --> 
                <div class="col-lg-12">
                    <% String err = (String) request.getAttribute("err");
                        String err1 = (String) request.getParameter("err");
                        if (err != null && !err.equals("") && !err.equals("null")) {%>
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <%=err%>
                    </div>
                    <% } else if (err1 != null && !err1.equals("") && !err1.equals("null")) {%>
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <%=err1%>
                    </div>
                    <%}
                        String msg = (String) request.getAttribute("msg");
                        String msg1 = (String) request.getParameter("msg");
                        if (msg != null && !msg.equals("") && !msg.equals("null")) {%>
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <%=msg%>
                    </div>
                    <%} else if (msg1 != null && !msg1.equals("") && !msg1.equals("null")) {
                    %>
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <%=msg1%>
                    </div>
                    <%}%>
                </div>
                <!-- END ERROR BOX --> 