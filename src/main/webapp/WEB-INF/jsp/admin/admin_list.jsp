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
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
                alert("请至少选择一条数据！");
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteAdmin() {
                var r = window.confirm("确定要删除此管理员吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
            }

            function ConditionQuery(self) {
                $("#form").attr("action","../admin/ConditionQueryLimit.do?currentPage=1");
                $("form").submit();
            }
        </script>       
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="<c:url value="/login.html"/>">[退出]</a>
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="<c:url value="/main/toMain.do"/>" class="index_off"></a></li>
                <li><a href="<c:url value="/role/findAll.do"/>" class="role_off"></a></li>
                <li><a href="<c:url value="/admin/findAll.do"/>" class="admin_on"></a></li>
                <li><a href="<c:url value="/cost/findAll.do"/>" class="fee_off"></a></li>
                <li><a href="<c:url value="/account/findAll.do"/>" class="account_off"></a></li>
                <li><a href="<c:url value="/service/findAll.do"/>" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form id="form" action="" method="post">
                <!--查询-->
                <div class="search_add">
                    <div>
                        模块：
                        <select id="selModules" class="select_search" name="module_name">
                            <option value="All">全部</option>
                            <c:forEach items="${moduleList}" var="module">
                                <c:choose>
                                    <c:when test="${module.module_name == adminPage.module_name}">
                                        <option selected ">${module.module_name}</option>
                                    </c:when>
                                    <c:otherwise>
                                    <option >${module.module_name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div>角色：<input type="text" value="${adminPage.role_name}" class="text_search width200" name="role_name"/></div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="ConditionQuery(this)"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='<c:url value="/admin/toAdd.do"/> ';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${adminPage.list}" var="admin">
                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>${admin.admin_id}</td>
                                <td>${admin.admin_name}</td>
                                <td>${admin.admin_code}</td>
                                <td>${admin.admin_telephone}</td>
                                <td>${admin.admin_email}</td>
                                <td>${admin.enrolldate}</td>
                                <td>
                                    <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
                                        <c:forEach begin="1" end="${admin.role_infoList.size()}" var="p">
                                            <c:if test="${p == 1}">
                                                ${admin.role_infoList[0].role_name}
                                            </c:if>
                                            <c:if test="${p == 2}">
                                                ..
                                            </c:if>
                                        </c:forEach>
                                    </a>
                                    <!--浮动的详细信息-->
                                    <div class="detail_info">
                                            <c:forEach items="${admin.role_infoList}" var="role">
                                                ${role.role_name}&nbsp;
                                            </c:forEach>
                                    </div>
                                </td>
                                <td class="td_modi">
                                    <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/admin/toModi.do?id=${admin.admin_id}"/>';" />
                                    <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin();" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <!--分页-->
                <!--分页-->
                <div id="pages">
                    <a onclick="getPage(this,1)">首页</a>
                    <c:choose>
                        <c:when test="${adminPage.currentPage == 1}">
                            <a href="#">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${adminPage.currentPage-1})">上一页</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${adminPage.totalPageSize}" var="p">
                        <c:choose>
                            <c:when test="${adminPage.currentPage == p}" >
                                <a onclick="getPage(this,${p})" class="current_page">${p}</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="getPage(this,${p})">${p}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${adminPage.currentPage == adminPage.totalPageSize}">
                            <a href="#">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${adminPage.currentPage+1})">下一页</a>
                        </c:otherwise>
                    </c:choose>
                    <a onclick="getPage(this,${adminPage.totalPageSize})">末页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <span>版权所有(C)云科技有限公司 </span>
        </div>
    <script type="">

        function getPage(e, f) {
            var url = "../admin/ConditionQueryLimit.do?currentPage="+f;
            $("#form").attr("action",url);
            $("form").submit();
        }
    </script>
    </body>
</html>
