<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>云科技</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script src="../js/jquery-3.2.1.js"></script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left" />
            <a href="<c:url value="/login.html"/>">[退出]</a>
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="<c:url value="/main/toMain.do"/>" class="index_off"></a></li>
                <li><a href="../role/role_list.jsp" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<c:url value="/cost/findAll.do"/>" class="fee_off"></a></li>
                <li><a href="<c:url value="/account/findAll.do"/>"></a></li>
                <li><a href="../service/service_list.jsp" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.jsp" class="report_off"></a></li>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="" method="" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info"><input type="text" value="${account.account_id}" readonly class="readonly" /></div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info"><input type="text" value="${account.real_name}" readonly class="readonly" /></div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" value="${account.idcard_no}" readonly class="readonly" />
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input type="text" value="${account.login_name}" readonly class="readonly" />
                </div>                   
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" class="width200 readonly" readonly value="${account.telephone}" />
                </div>
                <div class="text_info clearfix"><span>推荐人账务账号ID：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.recommender_id}" /></div>
                <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${reAccount.idcard_no}" /></div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                    <select disabled>
                        <c:choose>
                            <c:when test="${account.status == '1'}">
                                <option>开通</option>
                            </c:when>
                            <c:when test="${account.status == '2'}">
                                <option>暂停</option>
                            </c:when>
                            <c:when test="${account.status == '3'}">
                                <option>删除</option>
                            </c:when>
                        </c:choose>
                    </select>                        
                </div>                    
                <div class="text_info clearfix"><span>开通/暂停/删除时间：</span></div>
                <div class="input_info"><input id="time" type="text" readonly class="readonly" value="" /></div>

                <div class="text_info clearfix"><span>上次登录时间：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.last_login_time}" /></div>
                <div class="text_info clearfix"><span>上次登录IP：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.last_login_ip}" /></div>
                <!--可选项数据-->
                <div class="text_info clearfix"><span>生日：</span></div>
                <div class="input_info">
                    <input type="text" readonly class="readonly" value="${account.birthdate}" />
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input type="text" class="width350 readonly" readonly value="${account.email}" />
                </div> 
                <div class="text_info clearfix"><span>职业：</span></div>
                <div class="input_info">
                    <select disabled>
                        <c:choose>
                            <c:when test="${account.occupation == '干部'}">
                                <option>干部</option>
                            </c:when>
                            <c:when test="${account.occupation == '学生'}">
                                <option>学生</option>
                            </c:when>
                            <c:when test="${account.occupation == '技术人员'}">
                                <option>技术人员</option>
                            </c:when>
                            <c:when test="${account.occupation == '其他'}">
                                <option>其他</option>
                            </c:when>
                        </c:choose>
                    </select>                        
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="radSex"  id="female" disabled />
                    <label for="female">女</label>
                    <input type="radio" name="radSex" id="male" disabled />
                    <label for="male">男</label>
                </div> 
                <div class="text_info clearfix"><span>通信地址：</span></div>
                <div class="input_info"><input type="text" class="width350 readonly" readonly value="${account.mailaddress}" /></div>
                <div class="text_info clearfix"><span>邮编：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${account.zipcode}" /></div>
                <div class="text_info clearfix"><span>QQ：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${account.qq}" /></div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="window.history.go(-1);" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)云科技有限公司 </span>
        </div>
    <script type="">

        $(function () {
            var t = "${account.status}";
            if (t == "1"){
                $("#time").attr("value","${account.create_date}");
            }else if (t == "2"){
                $("#time").attr("value","${account.pause_date}");
            }else if (t == "3"){
                $("#time").attr("value","${account.close_date}");
            }

        });

        $(function () {
            var gender = "${account.gender}";
            if (gender == "1"){
                $("#male").attr("checked","checked");
            }else if (gender == "2"){
                $("#female").attr("checked","checked");
            }
        });


    </script>
    </body>
</html>
