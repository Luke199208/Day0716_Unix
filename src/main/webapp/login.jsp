<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title></title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css"/>
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
</head>
<body class="index">
<div class="login_box">
        <table>
            <tr>
                <td class="login_info">账号：</td>
                <td colspan="2"><input id="login" name="" type="text" class="width150"/></td>
                <td class="login_error_info"><span class="required" id="loginError">30长度的字母、数字和下划线</span></td>
            </tr>
            <tr>
                <td class="login_info">密码：</td>
                <td colspan="2"><input id="passwd" name="" type="password" class="width150"/></td>
                <td><span class="required" id="passwdError">30长度的字母、数字和下划线</span></td>
            </tr>
            <tr>
                <td class="login_info">验证码：</td>
                <td class="width70"><input id="vcode" name="" type="text" class="width70"/></td>
                <td><img id="verifyCode" src="<c:url value="/VerifyCodeServlet"/> " onclick="_change()"/></td>
                <td><span class="required" id="vcodeError">验证码错误</span></td>
            </tr>
            <tr>
                <td></td>
                <td class="login_button" colspan="2">
                    <a href="#" id="enter"><img src="images/login_btn.png"/></a>
                </td>
                <!--<td><span class="required">用户名或密码错误，请重试</span></td>                -->
            </tr>
        </table>
</div>
<script type="">
    $(function () {
        /**
         * 1.遍历所有的错误信息 确定是否显示
         */
        $(".required").each(function () {
            showError($(this));
        });
        /**
         * 账号.输入框得到焦点 隐藏错误信息
         */
        $("#login").focus(function () {
            $("#loginError").text("");
            showError($("#loginError"));
        });
        /**
         * 账号.输入框失去焦点,校验是否显示错误信息
         */
        $("#login").blur(function () {
            var id = $(this).attr("id");//获取当前输入框的id
            var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
            eval(funName);
        });
        /**
         * 密码.输入框得到焦点 隐藏错误信息
         */
        $("#passwd").focus(function () {
            $("#passwdError").text("");
            showError($("#passwdError"));
        });
        /**
         * 密码.输入框失去焦点,校验是否显示错误信息
         */
        $("#passwd").blur(function () {
            var id = $(this).attr("id");//获取当前输入框的id
            var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
            eval(funName);
        });
        /**
         * 验证码.输入框得到焦点 隐藏错误信息
         */
        $("#vcode").focus(function () {
            $("#vcodeError").text("");
            showError($("#vcodeError"));
        });
        /**
         * 验证码.输入框失去焦点,校验是否显示错误信息
         */
        $("#vcode").blur(function () {
            var id = $(this).attr("id");//获取当前输入框的id
            var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
            eval(funName);
        });

        /**
         * 表单提交
         */
        $("#enter").click(function () {
            var bool = true;
            if (!validateLogin()) {
                console.log(1);
                bool = false;
            }
            if (!validatePasswd()) {
                console.log(2);
                bool = false;
            }
            if (!validateVcode()) {
                console.log(3);
                bool = false;
            }
            if (bool){
                $("#enter").attr("href","<c:url value="/login/login.do"/>");

            }
        });


        /**
         * 账号校验
         */
        function validateLogin() {
            var value = $("#login").val();
            /**
             * 非空校验
             */
            if (!value) {
                $("#loginError").text("账号不能为空");
                showError($("#loginError"));

                return false;
            }
            /**
             * 长度校验
             */
            if (value.length < 3 || value.length > 20) {
                $("#loginError").text("用户名长度必须在3~20之间!");
                showError($("#loginError"));

                return false;
            }
            /**
             * 用户名是否存在校验
             */

            $.ajax({
                type: "post",
                url: "login/validateCode.do",
                async: false,//是否异步请求,若异步,那么不会等服务器返回结果,这个函数就会继续向下执行
                cache: false,
                data: {
                    "admin_code": value
                },
                success: function (data) {
                    if (data == "false") {
                        $("#loginError").text("用户名不存在!");
                        showError($("#loginError"));

                        return false;
                    }
                },
                error: function () {
                    alert("Error!");

                }
            });

            return true;
        }

        /**
         * 密码校验
         */
        function validatePasswd() {
            var code = $("#login").val();
            var value = $("#passwd").val();
            /**
             * 非空校验
             */
            if (!value) {
                $("#passwdError").text("密码不能为空");
                showError($("#passwdError"));

                return false;
            }
            /**
             * 长度校验
             */
            if (value.length < 3 || value.length > 20) {
                $("#passwdError").text("密码长度必须在3~20之间!");
                showError($("#passwdError"));

                return false;
            }
            /**
             * 密码是否正确校验
             */
            $.ajax({
                type: "post",
                url: "login/validatePasswd.do",
                async: false,//是否异步请求,若异步,那么不会等服务器返回结果,这个函数就会继续向下执行
                cache: false,
                data: {
                    "admin_code": code,
                    "admin_password": value
                },
                success: function (data) {
                    if (data == "false") {
                        $("#passwdError").text("密码错误!");
                        showError($("#passwdError"));

                        return false;
                    }
                },
                error: function () {
                    alert("Error!");

                }
            });

            return true;
        }

        /**
         * 验证码校验
         */
        function validateVcode() {
            var value = $("#vcode").val();
            /**
             * 非空校验
             */
            if (!value) {
                $("#vcodeError").text("验证码不能为空");
                showError($("#vcodeError"));

                return false;
            }
            /**
             * 长度校验
             */
            if (value.length != 4) {
                $("#vcodeError").text("错误的验证码!");
                showError($("#vcodeError"));

                return false;
            }
            /**
             * 验证码是否正确校验
             */
            $.ajax({
                type: "post",
                url: "login/validateVcode.do",
                async: false,//是否异步请求,若异步,那么不会等服务器返回结果,这个函数就会继续向下执行
                cache: false,
                data: {
                    "verifyCode": value
                },
                success: function (data) {
                    if (data == "false") {
                        $("#vcodeError").text("验证码错误!");
                        showError($("#vcodeError"));

                        return false;
                    }
                },
                error: function () {
                    alert("Error!");

                }
            });

            return true;
        }

        /**
         *  判断当前内容是否存在内容,如果存在则显示,不存在不显示
         * */
        function showError(ele) {
            var text = ele.text(); //获取元素内容
            if (!text) { //如果没有内容
                ele.css("display", "none"); //隐藏元素
            } else {
                ele.css("display", "");//显示元素
            }
        }

        /*
         * 换一张验证码
         * */
        function _change() {
            /**
             * 1 获取<img元素
             * 2 重新设置他的src
             * 3 使用毫秒来添加参数
             */

            $("#verifyCode").attr("src", "<%=request.getContextPath()%>/VerifyCodeServlet?a=" + new Date().getTime());
        }
    });


</script>

</body>
</html>
