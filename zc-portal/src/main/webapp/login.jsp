<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <style>
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">创意产品众筹平台-用户登录</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <form class="form-signin" role="form" action="${ctx}/login" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
        <span style="color: red;text-align: center;">${portal_login_fail_msg}</span>
        <!-- 取出一次就将session中的这个属性移除,scope=""不写默认从所有域 -->
        <c:remove var="portal_login_fail_msg"/>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号"
                   autofocus value="${portal_login_fail_acct} ">
            <c:remove var="portal_login_fail_acct" />
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <span style="color: red;" class="errorinfo"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <span style="color: red;" class="errorinfo"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control" >
                <option value="user">会员</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <label style="float:right">
                <a href="">忘记密码</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="loginBtn" onclick="login()"> 登录</a>
    </form>
</div>
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script>
    function isNullStr(str){

        if(str == null || str == ""){
            return true;
        }
        return false;
    }
    function login(){
        var loginacct = $("#loginacct").val().trim();
        var userpswd = $("#userpswd").val().trim();
        if(isNullStr(loginacct) || isNullStr(userpswd)){
            return false;
        }
        var r = /(^\s)/ ; // 表单回显时，会发生错误
        $(".form-signin").submit();
    }
</script>
</body>
</html>
