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
        .nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
            border-bottom-color: #ddd;
        }
        .nav-tabs>li>a {
            border-radius: 0;
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
                <input type="hidden" id="projectid" value="${projectid}">
                <div class="jumbotron nofollow" style="padding-top: 10px;" id="headDiv">
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row clearfix" >
                    <div class="col-md-8 column" id="picDiv">
                    </div>
                    <div class="col-md-4 column">
                        <div class="panel panel-default" style="border-radius: 0px;" id="infoDiv">
                        </div>
                        <div class=" panel panel-default" style="border-radius: 0px;">
                            <div class="panel-heading">
                                <h3 >
                                    风险提示
                                </h3>
                            </div>
                            <div class="panel-body">
                                <p> 1.众筹并非商品交易，存在一定风险。支持者根据自己的判断选择、支持众筹项目，与发起人共同实现梦想并获得发起人承诺的回报。<br>
                                    2.众筹平台仅提供平台网络空间及技术支持等中介服务，众筹仅存在于发起人和支持者之间，使用众筹平台产生的法律后果由发起人与支持者自行承担。<br>
                                    3.本项目必须在2017-06-09之前达到￥10000.00 的目标才算成功，否则已经支持的订单将取消。订单取消或募集失败的，您支持的金额将原支付路径退回。<br>
                                    4.请在支持项目后15分钟内付款，否则您的支持请求会被自动关闭。<br>
                                    5.众筹成功后由发起人统一进行发货，售后服务由发起人统一提供；如果发生发起人无法发放回报、延迟发放回报、不提供回报后续服务等情况，您需要直接和发起人协商解决。<br>
                                    6.如您不同意上述风险提示内容，您有权选择不支持；一旦选择支持，视为您已确认并同意以上提示内容。</p>
                            </div>
                        </div>
                        <div id="recDiv"><h2>为你推荐</h2><hr></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../commons/footer.jsp"%>
</div> <!-- /container -->


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog "  style="width:960px;height:400px;" role="document">
        <div class="modal-content" data-spy="scroll" data-target="#myScrollspy">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择支持项</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-sm-3 col-md-3 column" id="myScrollspy">
                            <ul class="nav nav-tabs nav-stacked">
                            </ul>
                        </div>
                        <div id="navList" class="col-sm-9 col-md-9 column" style="height:400px;overflow-y:auto;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script src="${ctx}/static/script/back-to-top.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script>
    $(function(){
        $.ajax({
            type:"GET",
            url:"${ctx}/project/detail",
            data:{
                "id":$("#projectid").val()
            },
            success:function(result){
                if(result.code == 1){
                    var info ='';
                    var head ='';
                    var project = result.content;
                    head += '<h3>'+project.project_name+'</h3>'
                            +'<div style="float:left;width:70%;">'
                            + project.remark
                            +'</div>'
                            +'<div style="float:right;">'
                            +'   <button type="button" onclick="follow('+project.follower+')" class="btn btn-default">'
                            +'      <i style="color:#f60" class="glyphicon glyphicon-heart"></i> 关注'
                            +          project.follower
                            +'   </button>'
                            +'</div>'
                    $("#headDiv").html(head);
                    if(project.status == 'I'){
                        info +='<div class="panel-heading" style="background-color: #fff;border-color: #fff;" >'
                              +'  <span class="label label-success"><i class="glyphicon glyphicon-tag"></i>众筹中</span>'
                              +'</div>'
                    }else if(project.status =='C'){
                        info +='<div class="panel-heading" style="background-color: #fff;border-color: #fff;" >'
                            +'  <span class="label label-success"><i class="glyphicon glyphicon-tag"></i>众筹完成</span>'
                            +'</div>'
                    }
                    info  += ' <div class="panel-body">'
                            +'     <h3 >'
                            +'     已筹资金:' + project.support_money
                            +'     </h3>'
                            +'     <p><span>目标金额:'+project.money+'</span>'
                            +'          <span style="float:right;">达成'+project.completion+'%</span>'
                            +'     </p>'
                            +'     <div class="progress" style="height:10px; margin-bottom: 5px;">'
                            +'        <div class="progress-bar progress-bar-success" role="progressbar" '
                            +'              aria-valuenow="'+project.completion+'" aria-valuemin="0" aria-valuemax="100"'
                            +'              style="width: '+project.completion+'%;">'
                            +'        </div>'
                            +'     </div>'
                            +'     <p>剩余'+project.remaining_day+'天</p>'
                            +'        <div>'
                            +'            <p><span>已有'+project.supporter+'人支持该项目</p>'
                            +'            <button type="button" class="btn  btn-warning btn-lg btn-block" onclick=getReturns('+project.project_id+')'
                            +'                 data-toggle="modal" data-target="#myModal" id="supportBtn">立即支持</button>'
                            +'        </div>'
                            +'</div>'
                            +'<div class="panel-footer" style=" background-color: #fff;border-top: 1px solid #ddd;'
                            +'       border-bottom-right-radius: 0px;border-bottom-left-radius: 0px;">'
                            +'    <div class="container-fluid">'
                            +'        <div class="row clearfix">'
                            +'            <div class="col-md-3 column" style="padding:0;">'
                            +'                <img alt="140x140" src="${ctx}/static/img/services-box2.jpg"'
                            +'                     data-holder-rendered="true" style="width: 80px; height: 80px;">'
                            +'            </div>'
                            +'            <div class="col-md-9 column">'
                            +'                <div class="">'
                            +'                    <h4>'
                            +'                         <b>'+project.initiator_name+'</b> '
                            +'                             <span style="float:right;font-size:12px;" '
                            +'                              class="label label-success">已认证</span>'
                            +'                    </h4>'
                            +'                    <p style="font-size:12px">'+project.initiator_desc+' </p>'
                            +'                    <p style="font-size:12px">'+project.contact+'</p>'
                            +'                </div>'
                            +'            </div>'
                            +'        </div>'
                            +'     </div>'
                            +'</div>'
                    if(project.remaining_day <= 0){
                        $("#supportBtn").attr("disabled", "disabled");
                    }
                    $("#infoDiv").html(info);
                    var detail = '<img alt="140x140" width="740" height="500" src="'+project.project_head_pic+'" />'
                                +'  <img alt="140x140" width="740" src="'+project.project_detail_pic+'" />'
                    $("#picDiv").html(detail);
                }else{
                    layer.msg(result.msg,{icon:5,time:2000},function(){
                    });
                }
            }
        })
        $.ajax({
            type:"GET",
            url:"${ctx}/rec",
            data:{},
            success:function(result){
                if(result.code == 1){
                    var projects =  result.content;
                    var rec = '';
                    if(projects.length == 0){
                        $("#recDiv").after(rec);
                        return;
                    }
                    $.each(projects,function(index,project){
                        rec +='<div class="prjtip panel panel-default" style="border-radius: 0px;">'
                            +'      <div class="panel-body" style="text-align: center">'
                            +'          <img src="'+project.headpicpath+'" style="width: 200px;height: 200px;" width="100%" height="100%">'
                            +'      </div>'
                            +'</div>'
                    })
                    $("#recDiv").after(rec);
                }else{

                }
            }
        })

    })
    $(".prjtip img").css("cursor", "pointer");
    $(".prjtip img").click(function(){
        window.location.href = 'project.html';
    });
    function start(){
        $.ajax({
            type:"GET",
            url:"${ctx}/crow/detect",
            data:{},
            success:function(result){
                if(result.code == 1){
                    window.location.href = "${ctx}/crow/start";
                }else{
                    layer.msg(result.msg,{icon:5,time:2000},function(){
                    });
                }
            }
        })
    }
    function follow(follower){
        $.ajax({
            type : "POST",
            url : "${ctx}/member/project/followProject",
            data : {
                "projectid":${projectid}
            },
            success : function(result) {
                if (result.code == 1) {
                    layer.msg("关注成功",{time:2000,icon:6},function(){
                        var attention ='<i style="color:#f60" class="glyphicon glyphicon-heart"></i> 已关注 '+(follower+1) ;
                        $(".glyphicon-heart").parent().attr('disabled',true).empty().html(attention);
                    });
                } else {
                    layer.msg(result.msg, {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {});
                }
            } // success
        }); // ajax
    }
    function getReturns(projectid) {
        $.ajax({
            type:"GET",
            url:"${ctx}/project/return",
            data:{"projectid":projectid},
            success:function(result){
                if(result.code == 1){
                    var returns = result.content;
                    var nl = '' ;
                    var content = '';
                    if(returns.length == 0){
                        $(".modal-body").empty().html('<label>获取数据失败，请重试!</label>')
                    }else{
                        $.each(returns,function(index,rtn){
                            nl += '<li class="active"><a href="#section-'+index+'">￥'+rtn.supportmoney+'</a></li>'
                            content +='<h2 id="section-'+index+'" style="border-bottom:1px dashed #ddd;" >'
                                +'      <span style="font-size:20px;font-weight:bold;">￥'+rtn.supportmoney+'</span>'
                                +'      <span style="font-size:12px;margin-left:60px;">'+(rtn.signalpurchase==0?'无限额':'个人限额:'+rtn.signalpurchase )+'</span>'
                                +'</h2>'
                                +'<p>配送费用：'+(rtn.freight ==0?'全国包邮':rtn.freight)+'</p>'
                                +'<p>预计发放时间：项目筹款成功后的'+rtn.rtndate+'天内</p>'
                                +'<button type="button" class="btn  btn-warning btn-lg " '
                                +'      onclick="support('+rtn.projectid+','+rtn.id+')">支持</button>'
                                +'    <br><br>'
                                +'    <p>'+rtn.content+'</p>'
                                +'<hr>'
                        });
                        $(".nav.nav-tabs.nav-stacked").html(nl);
                        $("#navList").html(content)
                    }

                }else{
                    $(".modal-body").empty().html('<label>获取数据失败，请重试!</label>')
                }
            }
        })
    }

    function support(projectid,returnid){
       $.ajax({
           type:"GET",
           url:"${ctx}/project/support",
           data:{
                "projectid":projectid,
                "returnid":returnid
           },
           success:function(result){
               if(result.code == 1){
                   window.location.href = "${ctx}/project/pay?projectid=" + result.ext.projectid + "&returnid=" + result.ext.returnid;
               }else{
                   layer.msg(result.msg,{icon:5,time:1500,shift:6},function(){

                   })
               }
           }
       })
    }
</script>
</body>
</html>
