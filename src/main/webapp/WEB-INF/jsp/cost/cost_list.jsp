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
            //排序按钮的点击事件
            function sort(btnObj) {
                var value = $(btnObj).attr("value");
                console.log(value);
                if (btnObj.className == "sort_desc"){
                    btnObj.className = "sort_asc";
                    if (value == "基费"){
                        window.location.href="../cost/findAllAscSortByBC.do";
                    }
                    if (value == "时长"){
                        window.location.href="../cost/findAllAscSortByBD.do";
                    }

                }
                else{
                    btnObj.className = "sort_desc";
                    if (value == "基费"){
                        window.location.href="../cost/findAllDecSortByBC.do";
                    }
                    if (value == "时长"){
                        window.location.href="../cost/findAllDecSortByBD.do";
                    }
                }

            }

            //启用
            function startFee(self) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                var id = $(self).attr("id");
                if (r){
                  window.location.href="../cost/updateStatus.do?id="+id;
                }

                document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteFee(self) {
                var r = window.confirm("确定要删除此资费吗？");
                var id = $(self).attr("id");
                if (r){
                   window.location.href="../cost/deleteCostById.do?id="+id ;
                }
                document.getElementById("operate_result_info").style.display = "block";
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
                <li><a href="../role/role_list.jsp" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<c:url value="/cost/findAll.do"/>" class="fee_on"></a></li>
                <li><a href="<c:url value="/account/findAll.do"/>" class="account_off"></a></li>
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
            <form action="" method="post">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='../cost/addCost.do';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    ${msg}
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
                        <c:forEach items="${costPage.list}" var="cost">
                            <tr>
                                <td>${cost.cost_id}</td>
                                <td><a href="../cost/finddetail.do?id=${cost.cost_id}">${cost.name}</a></td>
                                <td>${cost.base_duration} 小时</td>
                                <td>${cost.base_cost} 元</td>
                                <td>${cost.unit_cost} 元/小时</td>
                                <td>${cost.creatime}</td>
                                <td>${cost.startime}</td>

                                <c:choose>
                                   <c:when test="${cost.status == 1}">
                                   <td>开通</td>
                                   <td>
                                       <input id="${cost.cost_id}" type="button" value="暂停" class="btn_pause" onclick="startFee(this);" />
                                       <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/cost/modifyCostT.do?id=${cost.cost_id}"/> ';" />
                                       <input id="${cost.cost_id}" type="button" value="删除" class="btn_delete" onclick="deleteFee(this);" />
                                   </td>
                                   </c:when>
                                   <c:when test="${cost.status == 2}">
                                   <td> 暂停</td>
                                    <td>
                                        <input id="${cost.cost_id}" type="button" value="启用" class="btn_start" onclick="startFee(this);" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/cost/modifyCostT.do?id=${cost.cost_id}"/> ';" />
                                        <input id="${cost.cost_id}" type="button" value="删除" class="btn_delete" onclick="deleteFee(this);" />
                                    </td>
                                    </c:when>
                                    <c:when test="${cost.status == 3}">
                                        <td>删除</td>
                                        <td>

                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td></td>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>

                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                    <a onclick="getPage(this,1)">首页</a>
                    <c:choose>
                        <c:when test="${costPage.currentPage == 1}">
                            <a href="#">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${costPage.currentPage-1})">上一页</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${costPage.totalPageSize}" var="p">
                        <c:choose>
                            <c:when test="${costPage.currentPage == p}" >
                                <a onclick="getPage(this,${p})" class="current_page">${p}</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="getPage(this,${p})">${p}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${costPage.currentPage == costPage.totalPageSize}">
                            <a href="#">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="getPage(this,${costPage.currentPage+1})">下一页</a>
                        </c:otherwise>
                    </c:choose>
                    <a onclick="getPage(this,${costPage.totalPageSize})">末页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司</p>
        </div>
        <script type="">
            function getPage(e, f) {
                window.location.href ="../cost/findCostByLimit.do?currentPage="+f;
            }

        </script>
    </body>
</html>
