<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/theme.css">
    <style>
        #footer {
            padding: 15px 0;
            background: #fff;
            /*border-top: 1px solid #ddd;*/
            text-align: center;
        }
        .my-border{
            border:1px solid #ddd
        }
        /*这是一个用做回显的盒子的样式*/
        .pic {
            width: 100px;
            height: 100px;
        }

        .pic img {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<%@include file="../commons/nav_bar.jsp"%>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1>实名认证 - 申请</h1>
    </div>
    <div>
        <div class="my-border">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation"><a href="#"><span class="badge">1</span>填写实名信息</a></li>
                <li role="presentation" class="active"><a href="#"><span class="badge">2</span> 认证申请完成</a></li>
            </ul>
            <div class="row my-border" style="margin-left: 10px;margin-right: 10px">
                信息提交完成，请耐心等待人工审核！返回<a href="${ctx}/member/personal_center">个人中心</a>
            </div>
        </div>
    </div>
</div> <!-- /container -->
<div class="container" style="margin-top:20px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="footer">
                <div class="footerNav">
                    <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                </div>
                <div class="copyRight">
                    Copyright ?2017-2017 atguigu.com 版权所有
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script>
    function readAsDataURL(id)
    {
        if(typeof FileReader=='undifined')			//判断浏览器是否支持filereader
        {
            alert("否支持filereader")
            return false;
        }
        var file=document.getElementById("file_"+id).files[0];
        var i = document.getElementById("file_"+id).getAttribute("cn");
        var reader=new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function(e)
        {
            $("#" + i).html('<img src="'+this.result+'"/>')
        }
    }
    function upload(){
        /**
         * 我们存一下this对象，
         * 我们将在ajax的回调函数中，
         * 将要用这个对象，
         * 用它来改变父盒子的背景图
         */
        var self = this;
        $.ajax({
            url: "${ctx}/auth/apply",
            type: "post",
            dataType: "json",
            cache: false, // 上传文件不需要缓存。
            data: new FormData($("#formTag")[0]),
            processData: false,// 不处理数据,因为data值是FormData对象，不需要对数据做处理。
            contentType: false, // 不设置内容类型,因为是由<form>表单构造的FormData对象，且已经声明了属性
            success: function(result){
                if(result.code == 1){
                    window.location.href = "${ctx}/auth/"
                }else{
                    alert("信息提交失败")
                }
            }
        })
    }
</script>
</body>
</html>