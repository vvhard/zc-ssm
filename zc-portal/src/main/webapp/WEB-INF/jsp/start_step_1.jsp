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
            border-top: 1px solid #ddd;
            text-align: center;
        }
        #topcontrol {
            color: #fff;
            z-index: 99;
            width: 30px;
            height: 30px;
            font-size: 20px;
            background: #222;
            position: relative;
            right: 14px !important;
            bottom: 11px !important;
            border-radius: 3px !important;
        }

        #topcontrol:after {
            /*top: -2px;*/
            left: 8.5px;
            content: "\f106";
            position: absolute;
            text-align: center;
            font-family: FontAwesome;
        }

        #topcontrol:hover {
            color: #fff;
            background: #18ba9b;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }

        .label-type, .label-status, .label-order {
            background-color: #fff;
            color: #f60;
            padding : 5px;
            border: 1px #f60 solid;
        }
        #typeList  :not(:first-child) {
            margin-top:20px;
        }
        .label-txt {
            margin:10px 10px;
            border:1px solid #ddd;
            padding : 4px;
            font-size:14px;
        }
        .panel {
            border-radius:0;
        }

        .progress-bar-default {
            background-color: #ddd;
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
    <%@include file="../commons/crowdfunding_nav.jsp"%>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="panel panel-default" >
                    <div class="panel-heading" style="text-align:center;">
                        <div class="container-fluid">
                            <div class="row clearfix">
                                <div class="col-md-3 column">
                                    <div class="progress" style="height:50px;border-radius:0;    position: absolute;width: 75%;right:49px;">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;">1. 项目及发起人信息</div>
                                        </div>
                                    </div>
                                    <div style="right: 0;border:10px solid #ddd;border-left-color: #88b7d5;border-width: 25px;position: absolute;
                                             border-left-color: rgba(92, 184, 92, 1);
                                             border-top-color: rgba(92, 184, 92, 0);
                                             border-bottom-color: rgba(92, 184, 92, 0);
                                             border-right-color: rgba(92, 184, 92, 0);
                                        ">
                                    </div>
                                </div>
                                <div class="col-md-3 column">
                                    <div class="progress" style="height:50px;border-radius:0;position: absolute;width: 75%;right:49px;">
                                        <div class="progress-bar progress-bar-default" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;">2. 回报设置</div>
                                        </div>
                                    </div>
                                    <div style="right: 0;border:10px solid #ddd;border-left-color: #88b7d5;border-width: 25px;position: absolute;
                                             border-left-color: rgba(221, 221, 221, 1);
                                             border-top-color: rgba(221, 221, 221, 0);
                                             border-bottom-color: rgba(221, 221, 221, 0);
                                             border-right-color: rgba(221, 221, 221, 0);
                                        ">
                                    </div>
                                </div>
                                <div class="col-md-3 column">
                                    <div class="progress" style="height:50px;border-radius:0;position: absolute;width: 75%;right:49px;">
                                        <div class="progress-bar progress-bar-default" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;">3. 确认信息</div>
                                        </div>
                                    </div>
                                    <div style="right: 0;border:10px solid #ddd;border-left-color: #88b7d5;border-width: 25px;position: absolute;
                                             border-left-color: rgba(221, 221, 221, 1);
                                             border-top-color: rgba(221, 221, 221, 0);
                                             border-bottom-color: rgba(221, 221, 221, 0);
                                             border-right-color: rgba(221, 221, 221, 0);
                                        ">
                                    </div>
                                </div>
                                <div class="col-md-3 column">
                                    <div class="progress" style="height:50px;border-radius:0;">
                                        <div class="progress-bar progress-bar-default" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;">4. 完成</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                                        <b>
                                            项目及发起信息
                                        </b>
                                    </blockquote>
                                </div>
                                <div class="col-md-12 column">
                                    <div class="page-header" style="    border-bottom-style: dashed;">
                                        <h3>
                                            项目信息
                                        </h3>
                                    </div>
                                    <form class="form-horizontal" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">分类信息</label>
                                            <div class="col-sm-10">
                                                <c:forEach items="${types}" var="type">
                                                    <label class="radio-inline">
                                                       <input type="radio" name="type" id="inlineRadio1" value="${type.name}"> ${type.name}
                                                    </label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">标签</label>
                                            <div class="col-sm-10">
                                                <ul style="list-style:none;padding-left:0;" id="type_ul">
                                                    <li style="margin:10px 0">[手机]
                                                        <span class="label-txt">手机</span> <span class="label-txt">快充</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span>
                                                    </li>
                                                    <li style="margin:10px 0">[数码]
                                                        <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span>
                                                    </li>
                                                    <li style="margin:10px 0">[电脑]
                                                        <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span> <span class="label-txt">文字标签</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">项目名称</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="name" name="name" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">一句话简介</label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control" rows="5" id="remark" name="remark"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">筹资金额（元）</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="money" name="money" style="width:100px;" >
                                                <label class="control-label">筹资金额不能低于100元,不能高于1000000000元</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">筹资天数（天）</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control"id="day" name="day" style="width:100px;" >
                                                <label class="control-label">一般10-90天，建议30天</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">项目头图</label>
                                            <div class="col-sm-10">
                                                <div style="float: left">
                                                    <input type="file"name="hfile" id="hfile"onchange="readAsDataURL('hfile')">
                                                </div>
                                                <div class="pic" id="hfileImg"  style="float: left">
                                                    <img  src=""/>
                                                </div>
                                            </div>
                                        </div>
                                        <label  class="control-label col-sm-offset-2" style="margin-bottom: 20px;margin-top: 5px">
                                            图片上无文字,支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：740*457px
                                        </label>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">项目详情</label>
                                            <div class="col-sm-10">
                                                <div style="float: left">
                                                    <input type="file" name="dfile" id="dfile"onchange="readAsDataURL('dfile')">
                                                </div>
                                                <div class="pic" id="dfileImg"  style="float: left">
                                                    <img src=""/>
                                                </div>
                                            </div>
                                        </div>
                                        <label class="control-label col-sm-offset-2" style="margin-bottom: 20px;margin-top: 5px">
                                            支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：宽740px
                                        </label>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">客服电话</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="contact" name="contact"
                                                       placeholder="此信息显示在项目页面">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer" style="text-align:center;">
                        <button type="button" class="btn  btn-warning btn-lg" id="nextStepBtn">下一步</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="../commons/footer.jsp"%>

</div> <!-- /container -->
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script src="${ctx}/static/script/back-to-top.js"></script>
<script>
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    $("#nextStepBtn").click(function(){
        $.ajax({
            type:"POST",
            url:"${ctx}/crow/sumbit",
            dataType: "json",
            cache: false, // 上传文件不需要缓存。
            data: new FormData($(".form-horizontal")[0]),
            processData: false,// 不处理数据,因为data值是FormData对象，不需要对数据做处理。
            contentType: false, // 不设置内容类型,因为是由<form>表单构造的FormData对象，且已经声明了属性
            success:function(result){
                if(result.code == 1){
                    window.location.href = "${ctx}/crow/start_step2?projectTempId=" + result.ext.projectTempId;
                }else{

                }
            }
        });
    });
    function readAsDataURL(id) {
        if(typeof FileReader=='undifined')			//判断浏览器是否支持filereader
        {
            alert("否支持filereader")
            return false;
        }
        var file=document.getElementById(id).files[0];
        var reader=new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function(e)
        {
            $("#" + id +"Img").html('<img src="'+this.result+'"/>')
        }
    }
</script>
</body>
</html>