<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="<%= request.getContextPath()%>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%= request.getContextPath()%>/styles/global_color.css" />
        <script src="/js/jquery-3.2.1.js"></script>
    </head>
    <body class="index">
        <!--导航区域开始-->
        <div id="index_navi">
            <ul id="menu">
                <li><a href="<c:url value="/main/toMain.do"/>" class="index_on"></a></li>
                <li><a href="<c:url value="/role/findAll.do"/>" class="role_off"></a></li>
                <li><a href="admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<%= request.getContextPath()%>/cost/findAll.do" class="fee_off"></a></li>
                <li><a href="<%= request.getContextPath()%>/account/findAll.do" class="account_off"></a></li>
                <li><a href="<%= request.getContextPath()%>/service/findAll.do" class="service_off"></a></li>
                <li><a href="bill/bill_list.html" class="bill_off"></a></li>
                <li><a onclick="exportExcel()" class="report_off"></a></li>
                <li><a href="user/user_info.jsp" class="information_off"></a></li>
                <li><a href="user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
    <script type="">
        function exportExcel(){
            var myurl="../account/export.do?accountDate=201807";
            window.location.href = myurl;
        }

    </script>
    </body>
</html>
