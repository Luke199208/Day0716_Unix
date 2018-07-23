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
            //删除
            function deleteAccount(e) {
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                if (r){
                    window.location.href = "../service/del.do?id="+e;
                }
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState(e,f) {
                var r = window.confirm("确定要"+f+"此业务账号吗？");
                if (r){
                    window.location.href = "../service/updateStatus.do?id="+e;
                }
                document.getElementById("operate_result_info").style.display = "block";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="<c:url value="/login.html"/> ">[退出]</a>
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<c:url value="/cost/findAll.do"/>" class="fee_off"></a></li>
                <li><a href="<c:url value="/account/findAll.do"/>" class="account_off"></a></li>
                <li><a href="<c:url value="/service/findAll.do"/>" class="service_on"></a></li>
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
                    <div>OS 账号：<input type="text" value="${servicePage.os_username}" class="width100 text_search"  name="os_username"/></div>
                    <div>服务器 IP：<input type="text" value="${servicePage.unix_host}" class="width100 text_search" name="unix_host"/></div>
                    <div>身份证：<input type="text"  value="${servicePage.idcard_no}" class="text_search" name="idcard_no"/></div>
                    <div>状态：
                        <select class="select_search" name="service_status">
                            <option id="all" value="-1">全部</option>
                            <option id="start" value="0">开通</option>
                            <option id="pause" value="1">暂停</option>
                            <option id="del" value="2">删除</option>
                        </select>
                    </div>
                    <div><input type="button" value="搜索" class="btn_search" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='<c:url value="../service/addT.do"/> ';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    ${msg}
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <c:forEach items="${servicePage.list}" var="serviceBean">
                        <tr>
                            <td><a href="<c:url value="/service/showDetail.do?id=${serviceBean.id}"/> " title="查看明细">${serviceBean.id}</a></td>
                            <td>${serviceBean.account_id}</td>
                            <td>${serviceBean.account.idcard_no}</td>
                            <td>${serviceBean.account.real_name}</td>
                            <td>${serviceBean.os_username}</td>

                            <c:choose>
                                <c:when test="${serviceBean.service_status == 0}">
                                    <td>开通</td>
                                    <td>${serviceBean.unix_host}</td>
                                    <td>
                                        <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${serviceBean.cost.name}</a>
                                        <!--浮动的详细信息-->
                                        <div class="detail_info">
                                                ${serviceBean.cost.descr}
                                        </div>
                                    </td>
                                    <td class="td_modi">
                                        <input type="button" value="暂停" class="btn_pause" onclick="setState(${serviceBean.id},'暂停');" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='service_modi.jsp';" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(${serviceBean.id});" />
                                    </td>
                                </c:when>
                                <c:when test="${serviceBean.service_status == 1}">
                                    <td>暂停</td>
                                    <td>${serviceBean.unix_host}</td>
                                    <td>
                                        <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${serviceBean.cost.name}</a>
                                        <!--浮动的详细信息-->
                                        <div class="detail_info">
                                                ${serviceBean.cost.descr}
                                        </div>
                                    </td>
                                    <td class="td_modi">
                                        <input type="button" value="开通" class="btn_start" onclick="setState(${serviceBean.id},'开通');" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='service_modi.jsp';" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(${serviceBean.id});" />
                                    </td>
                                </c:when>
                                <c:when test="${serviceBean.service_status == 2}">
                                    <td>删除</td>
                                    <td>${serviceBean.unix_host}</td>
                                    <td>
                                        <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${serviceBean.cost.name}</a>
                                        <!--浮动的详细信息-->
                                        <div class="detail_info">
                                                ${serviceBean.cost.descr}
                                        </div>
                                    </td>
                                    <td class="td_modi">

                                    </td>
                                </c:when>
                            </c:choose>

                        </tr>
                    </c:forEach>
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <!--分页-->
                <div id="pages">
                    <a onclick="getPage(this,1)">首页</a>
                    <c:choose>
                        <c:when test="${servicePage.currentPage == 1}">
                            <a href="#">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${servicePage.currentPage-1})">上一页</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${servicePage.totalPageSize}" var="p">
                        <c:choose>
                            <c:when test="${servicePage.currentPage == p}" >
                                <a onclick="getPage(this,${p})" class="current_page">${p}</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="getPage(this,${p})">${p}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${servicePage.currentPage == servicePage.totalPageSize}">
                            <a href="#">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${servicePage.currentPage+1})">下一页</a>
                        </c:otherwise>
                    </c:choose>
                    <a onclick="getPage(this,${servicePage.totalPageSize})">末页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司 </p>
        </div>
    <script type="">
        //刷新select选择不变
        $(function () {
            var status = "${servicePage.service_status}";
            if (status == "-1"){
                $("#all").attr("selected","selected");
            }
            if (status == "0"){
                $("#start").attr("selected","selected");
            }
            if (status == "1"){
                $("#pause").attr("selected","selected");
            }
            if (status == "2"){
                $("#del").attr("selected","selected");
            }
        });
        function getPage(e, f) {
            var url = "../service/ConditionQueryByLimit.do?currentPage="+f;
            $("#form").attr("action",url);
            $("form").submit();
        }
        $(".btn_search").click(function () {
            $("#form").attr("action","../service/ConditionQueryByLimit.do?currentPage=1");
            $("form").submit();
        });
    </script>
    </body>
</html>
