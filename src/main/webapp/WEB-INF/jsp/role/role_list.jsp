<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <script language="javascript" type="text/javascript">
            function deleteRole() {
                var r = window.confirm("确定要删除此角色吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="<c:url value="/login.jsp"/>">[退出]</a>
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="<c:url value="/main/toMain.do"/>" class="index_off"></a></li>
                <li><a href="<c:url value="/role/findAll.do"/>" class="role_on"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<c:url value="/cost/findAll.do"/>" class="fee_off"></a></li>
                <li><a href="<c:url value="/account/findAll.do"/>" class="account_off"></a></li>
                <li><a href="<c:url value="/service/findAll.do"/>" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='<c:url value="/role/ToAdd.do"/>';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>
                        <c:forEach items="${rolePage.list}" var="role_info">
                            <tr>
                                <td>${role_info.role_id}</td>
                                <td>${role_info.role_name}</td>
                                <td>
                                    <c:forEach items="${role_info.module_infoList}" var="module_info">
                                        ${module_info.module_name}&nbsp
                                    </c:forEach>
                                </td>
                                <td>
                                    <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/role/ToModi.do?id=${role_info.role_id}"/> ';"/>
                                    <input type="button" value="删除" class="btn_delete" onclick="deleteRole();" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                    <a onclick="getPage(this,1)">首页</a>
                    <c:choose>
                        <c:when test="${rolePage.currentPage == 1}">
                            <a href="#">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${rolePage.currentPage-1})">上一页</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${rolePage.totalPageSize}" var="p">
                        <c:choose>
                            <c:when test="${rolePage.currentPage == p}" >
                                <a onclick="getPage(this,${p})" class="current_page">${p}</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="getPage(this,${p})">${p}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${rolePage.currentPage == rolePage.totalPageSize}">
                            <a href="#">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${rolePage.currentPage+1})">下一页</a>
                        </c:otherwise>
                    </c:choose>
                    <a onclick="getPage(this,${rolePage.totalPageSize})">末页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司 </p>
        </div>
    <script type="">
        function getPage(e, f) {
            window.location.href = "../role/findRoleByLimt.do?currentPage="+f;

        }
    </script>
    </body>
</html>
