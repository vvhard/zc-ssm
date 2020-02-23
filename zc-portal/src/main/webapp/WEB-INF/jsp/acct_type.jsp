<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="utf-8">
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
            border-top: 1px solid #ddd;
            text-align: center;
        }
        .seltype {
            position: absolute;
            margin-top: 70px;
            margin-left: 10px;
            color: red;
        }
    </style>
</head>
<body>
<%@include file="../commons/nav_bar.jsp"%>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1>实名认证 - 账户类型选择</h1>
    </div>
    <div style="padding-top:10px;">
        <div class="row">
            <div class="col-xs-6 col-md-3">
                <h2>商业公司</h2>
                <a href="#" class="thumbnail">

                    <img alt="100%x180" src="${ctx}/static/img/services-box1.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <h2>个体工商户</h2>
                <a href="#" class="thumbnail">
                    <img alt="100%x180" src="${ctx}/static/img/services-box2.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <h2>个人经营</h2>
                <a href="#" class="thumbnail">
                    <img alt="100%x180" src="${ctx}/static/img/services-box3.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <h2>政府及非营利组织</h2>
                <a href="#" class="thumbnail">
                    <img alt="100%x180" src="${ctx}/static/img/services-box4.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
        </div>
        <button type="button" class="btn btn-danger btn-lg btn-block" onclick="window.location.href='apply.html'">认证申请</button>
    </div> <!-- /container -->
    <!-- /END THE FEATURETTES -->
    <div class="container" style="margin-top:20px;">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div id="footer">
                    <div class="footerNav">
                        <a rel="nofollow" href="#">关于我们</a> | <a rel="nofollow" href="#">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                    </div>
                    <div class="copyRight">
                        Copyright ?2020-2020zc.com 版权所有
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script>
    $(".thumbnail").click(function(){
        $('.seltype').remove();
        $(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');
    });
</script>
</body>
</html>

