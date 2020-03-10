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
                <li role="presentation" class="active"><a href="#"><span class="badge">1</span>填写实名信息</a></li>
                <li role="presentation"><a href="#"><span class="badge">2</span> 认证申请完成</a></li>
            </ul>
            <div class="row my-border" style="margin-left: 10px;margin-right: 10px">
                <form role="form" style="margin-top:20px;" enctype="multipart/form-data" id="formTag">
                    <div class="col-sm-3">
                        <div class="form-group">
                            申请账号:abc
                            <div>
                                申请认证类型:
                                <label>个人经营</label>
                            </div>
                            预留邮箱:aaaa@zc.com<a href="#">[修改]</a>
                        </div>
                        <div class="form-group">
                            <label for="realname">真实名称</label>
                            <input type="text" class="form-control" name="realname"id="realname" placeholder="请输入真实名称">
                        </div>
                        <div class="form-group">
                            <label for="realname">身份证号码</label>
                            <input type="text" class="form-control" name="cardnum" id="cardnum" placeholder="请输入身份证号码">
                        </div>
                        <div class="form-group">
                            <label for="realname">电话号码</label>
                            <input type="text" class="form-control" name="tel" id="tel" placeholder="请输入电话号码">
                        </div>
                        <div class="form-group" style="float: left">
                            <label >验证码</label>
                            <input type="text" class="form-control" style="width: 150px;"
                                   name="validateCode" id="validateCode" placeholder="请输入你邮箱中接收到的验证码">
                        </div>
                    </div> <%-- 基本信息填写 --%>
                    <%-- 资质文件上传 --%>
                    <div class="col-sm-9">
                        <div style="margin-left: 20px" id="">
                            <%--<form id="formTag" enctype="multipart/form-data">--%>
                                <c:forEach items="${portal_auth_acct_type_certs}" var="cert" varStatus="i" begin="0" end="6">
                                    <div style="float:left">
                                        <label>${cert.name}</label>
                                        <input type="hidden" name="certid" value="${cert.id }">
                                        <input type="file"cn="cert_img_${cert.id}"id="file_${cert.id}" name="file"
                                               onchange="readAsDataURL(${cert.id})">
                                        <div class="pic" id="cert_img_${cert.id}">
                                            <img id="img1" src=""/>
                                        </div>
                                    </div>
                                </c:forEach>
                            <%--</form>--%>
                        </div>
                    </div> <%-- 资质文件上传 --%>
                </form>
            </div>
            <div class="row" style="text-align: center;margin-bottom: 5px;margin-top: 5px">
                <button class="btn btn-primary" id="confirmBtn" onclick="upload()">确认申请</button>
                <button class="btn btn-warning" id="cancelBtn">放弃申请</button>
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
    function readAsDataURL(id) {
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
                    window.location.href = "${ctx}/member/auth_complete";
                }else{
                    alert("信息提交失败")
                }
            }
        })
    }
</script>
</body>
</html>