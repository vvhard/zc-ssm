<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
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
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" role="form" id="regForm">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址" style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <%--<div class="form-group has-success has-feedback">--%>
            <%--<select class="form-control" >--%>
                <%--<option>会员</option>--%>
                <%--<option>管理</option>--%>
            <%--</select>--%>
        <%--</div>--%>
        <div class="checkbox">
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="login.jsp">我有账号</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="regBtn"> 注册</a>
    </form>

</div>
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/jquery-validation-1.13.1/dist/jquery.validate.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script type="text/javascript">
   // $(function(){
   //
   // });
   // $.validator.setDefaults({
   //      showErrors:{
   //
   //      }
   // }); // 设置校验器的默认内容

    $("#regBtn").click(function(){
        var loginacct = $("#loginacct").val()
        var userpswd = $("#userpswd").val()
        var email = $("#email").val()
        if(isNullStr(loginacct) || isNullStr(userpswd)){
            return ;
        }
        if(notEmail(email)){
            return ;
        }
        // 先用jq做简单校验，后续优化再进行详细校验
        $.ajax({
            type:"POST",
            url:"${ctx}/reg",
            data:{
                "loginacct":loginacct,
                "userpswd":userpswd,
                "email":email
            },
            success:function (result) {
                if(result.code == 1){
                    window.location.href = "${ctx}/index.jsp";
                }else{
                    layer.msg("注册失败",{icon:5,time:1500,shift:6},function(){

                    });
                }
            }
        })
    });
   function isNullStr(str){
       if(str == null || str.replace(/(^s*)|(s*$)/g, "").length == 0 ){
           return true;
       }
       return false;
   }
    function notEmail(email){
        // 非邮箱格式
        var reg=/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
        if(reg.test(email))
            return false
        return true
    }

</script>
</body>
</html>

