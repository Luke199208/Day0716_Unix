<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>云科技</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <script language="javascript" type="text/javascript">
            //删除
            function deleteAccount(self) {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                if (r) window.location.href="../account/deleteAccount.do?id="+self;
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState(self,s) {
                var r = window.confirm("确定要"+s+"此账务账号吗？");
                if (r) window.location.href="../account/setState.do?id="+self;
                document.getElementById("operate_result_info").style.display = "block";
            }

            //ConditionQuery
            function ConditionQuery(self) {

                $("#form").attr("action","../account/ConditionQueryByLimit.do?currentPage=1");
                $("form").submit();
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
                <li><a href="<c:url value="/role/findAll.do"/>" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<c:url value="/cost/findAll.do"/>" class="fee_off"></a></li>
                <li><a href="<c:url value="/account/findAll.do"/>" class="account_on"></a></li>
                <li><a href="<c:url value="/service/findAll.do"/>" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.jsp" class="report_off"></a></li>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form id="form" action="" method="post">
                <!--查询-->
                <div class="search_add">                        
                    <div>身份证：<input type="text"  class="text_search" name="idcard_no" value="${accountPage.idcard_no}"/></div>
                    <div>姓名：<input type="text" class="width70 text_search"  name="real_name" value="${accountPage.real_name}"/></div>
                    <div>登录名：<input type="text"   class="text_search" name="login_name" value="${accountPage.login_name}"/></div>
                    <div>
                        状态：
                        <select class="select_search" name="status">
                            <option id="all" value="0">全部</option>
                            <option id="start" value="1">开通</option>
                            <option id="pause" value="2">暂停</option>
                            <option id="del" value="3">删除</option>
                        </select>
                    </div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="ConditionQuery(this)"/></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='<c:url value="/account/addAccountT.do"/>';" />
                </div>  
                <!--删除等的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="<%=request.getContextPath()%>/images/close.png" onclick="this.parentNode.style.display='none';" />
                    ${msg}
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th>账号ID</th>
                        <th>姓名</th>
                        <th class="width150">身份证</th>
                        <th>登录名</th>
                        <th>状态</th>
                        <th class="width100">创建日期</th>
                        <th class="width150">上次登录时间</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <c:forEach items="${accountPage.list}" var="account">
                        <tr>
                            <td>${account.account_id}</td>
                            <td><a href="<c:url value="/account/findDetail.do?id=${account.account_id}"/> ">${account.real_name}</a></td>
                            <td>${account.idcard_no}</td>
                            <td>${account.login_name}</td>
                            <c:choose>
                                <c:when test="${account.status == 1}">
                                    <td>开通</td>
                                    <td>${account.create_date}</td>
                                    <td>${account.last_login_time}</td>
                                    <td class="td_modi">
                                        <input type="button" value="暂停" class="btn_pause" onclick="setState(${account.account_id},'暂停');" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/account/modiAccT.do?id=${account.account_id}" />';" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(${account.account_id});" />
                                    </td>
                                </c:when>
                                <c:when test="${account.status == 2}">
                                    <td>暂停</td>
                                    <td>${account.create_date}</td>
                                    <td>${account.last_login_time}</td>
                                    <td class="td_modi">
                                        <input type="button" value="开启" class="btn_start" onclick="setState(${account.account_id},'开启');" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/account/modiAccT.do?id=${account.account_id}" />';" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(${account.account_id});" />
                                    </td>
                                </c:when>
                                <c:when test="${account.status == 3}">
                                    <td>删除</td>
                                    <td>${account.create_date}</td>
                                    <td>${account.last_login_time}</td>
                                    <td class="td_modi"></td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                  </table>
                <p>业务说明：<br />
                1、创建则开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、暂停账务账号，同时暂停下属的所有业务账号；<br />                
                6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br />
                7、删除账务账号，同时删除下属的所有业务账号。</p>
                </div>                   
                <!--分页-->
                <div id="pages">
                    <a onclick="getPage(this,1)">首页</a>
                    <c:choose>
                        <c:when test="${accountPage.currentPage == 1}">
                            <a href="#">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${accountPage.currentPage-1})">上一页</a>
                        </c:otherwise>
                    </c:choose>
        	        <c:forEach begin="1" end="${accountPage.totalPageSize}" var="p">
                        <c:choose>
                         <c:when test="${accountPage.currentPage == p}" >
                            <a onclick="getPage(this,${p})" class="current_page">${p}</a>
                         </c:when>
                         <c:otherwise>
                            <a onclick="getPage(this,${p})">${p}</a>
                         </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${accountPage.currentPage == accountPage.totalPageSize}">
                            <a href="#">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${accountPage.currentPage+1})">下一页</a>
                        </c:otherwise>
                    </c:choose>
                    <a onclick="getPage(this,${accountPage.totalPageSize})">末页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司 </p>
        </div>
       <%-- <div style="align-content: center">
            <form action="<%=request.getContextPath()%>/account/import.do" method="post" enctype="multipart/form-data">
                请选择文件:<input type="file" name="upfile"><br/>
                <input type="submit" value="提交" style="align-content: center;width: auto;">
            </form>
        </div>--%>
    <script type="">
        $(function () {
            var status = "${accountPage.status}";
            if (status == "0"){
                $("#all").attr("selected","selected");
            }
            if (status == "1"){
                $("#start").attr("selected","selected");
            }
            if (status == "2"){
                $("#pause").attr("selected","selected");
            }
            if (status == "3"){
                $("#del").attr("selected","selected");
            }
        });
        function getPage(e, f) {
            var url = "../account/ConditionQueryByLimit.do?currentPage="+f;
            $("#form").attr("action",url);
            $("form").submit();
        }

    </script>
    </body>
</html>
