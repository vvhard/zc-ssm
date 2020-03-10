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
    </style>
</head>
<body>
<%@include file="../commons/nav_bar.jsp"%>
<div class="container theme-showcase" role="main">

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="panel panel-default" >
                    <div class="panel-heading" style="text-align:center;">
                        <div class="container-fluid">
                            <div class="row clearfix">
                                <div class="col-md-3 column">
                                    <div class="progress" style="height:50px;border-radius:0;position: absolute;width: 75%;right:49px;">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;"> 确认订单</div>
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
                                            <div style="font-size:16px;margin-top:15px;"> 付款</div>
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
                                            <div style="font-size:16px;margin-top:15px;"> 完成</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">

                        <div id="address" class="container-fluid">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                                        <b>
                                            收货地址
                                        </b>
                                    </blockquote>
                                </div>
                                <div class="col-md-12 column" style="padding:0 120px;" id="myAddress">
                                    <div class="newAddress" style="display:none;border:10px solid #f60;border-bottom-color: #eceeef;border-width: 10px;width:20px;margin-left:20px;margin-top:-20px;
                                             border-left-color: rgba(221, 221, 221, 0);
                                             border-top-color: rgba(221, 221, 221, 0);
                                             border-right-color: rgba(221, 221, 221, 0);"></div>
                                    <div class="newAddress panel panel-default" style="display:none;border-style: dashed;background-color:#eceeef">
                                        <div class="panel-body">
                                            <div class="col-md-12 column" >
                                                <form class="form-horizontal" id ="addrForm">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">收货人（*）</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control"name="name" style="width:200px;" placeholder="姓名" >
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">手机（*）</label>
                                                        <div class="col-sm-10">
                                                            <input class="form-control" name="tel"type="text" style="width:200px;" placeholder="请填写11位手机号码"></input>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">地址（*）</label>
                                                        <div class="col-sm-10">
                                                            <input class="form-control" type="text" name="address"style="width:400px;" placeholder="请填写收货地址"></input>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-10">
                                                            <button type="button" class="btn btn-primary" onclick="addAddr()">确认配送信息</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                                        <b>
                                            项目信息
                                        </b>
                                    </blockquote>
                                </div>
                                <div class="col-md-12 column">
                                    <table class="table table-bordered" style="text-align:center;">
                                        <thead>
                                        <tr style="background-color:#ddd;">
                                            <td>项目名称</td>
                                            <td>发起人</td>
                                            <td width="300">回报内容</td>
                                            <td width="80">数量</td>
                                            <td>支持单价</td>
                                            <td>配送费用</td>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-md-12 column">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">备注</label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" id="remark" rows="1" placeholder="填写关于回报或发起人希望您备注的信息"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12 column">
                                    <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                                        <b>
                                            账单
                                        </b>
                                    </blockquote>
                                </div>

                                <div class="col-md-12 column">
                                    <div class="alert alert-warning alert-dismissable" style="text-align:right; border:2px solid #ffc287;">
                                        <ul style="list-style:none;" >
                                            <li style="margin-top:10px;">支持金额：<span style="color:red;"id="money"></span></li>
                                            <li style="margin-top:10px;">配送费用：<span style="color:red;" id="freight"></span></li>
                                            <li style="margin-top:10px;margin-bottom:10px;"><h2>支付总金额：<span style="color:red;" id="totalmoney"></span></h2></li>
                                            <li style="margin-top:10px;">
                                                <button type="button" class="btn btn-warning btn-lg" onclick="pay()">
                                                    <i class="glyphicon glyphicon-credit-card"></i> 付款</button>
                                            </li>
                                            <li style="margin-top:10px;">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" checked> 我已了解风险和规则
                                                    </label>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="container">
                                    <div class="row clearfix">
                                        <div class="col-md-12 column">
                                            <blockquote>
                                                <p >
                                                    <i class="glyphicon glyphicon-info-sign" style="color:#2a6496;"></i> 提示
                                                </p><br>
                                                <span style="font-size:12px;">1.众筹并非商品交易，存在一定风险。支持者根据自己的判断选择、支持众筹项目，与发起人共同实现梦想并获得发起人承诺的回报。<br>
                                                2.众筹平台仅提供平台网络空间及技术支持等中介服务，众筹仅存在于发起人和支持者之间，使用众筹平台产生的法律后果由发起人与支持者自行承担。<br>
                                                3.本项目必须在众筹结束之前达到目标金额才算成功，否则已经支持的订单将取消。订单取消或募集失败的，您支持的金额将原支付路径退回。<br>
                                                4.众筹成功后由发起人统一进行发货，售后服务由发起人统一提供；如果发生发起人无法发放回报、延迟发放回报、不提供回报后续服务等情况，您需要直接和发起人协商解决。<br>
                                                5.如您不同意上述风险提示内容，您有权选择不支持；一旦选择支持，视为您已确认并同意以上提示内容。</span>
                                            </blockquote>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
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
<script src="${ctx}/static/layer/layer.js"></script>
<script>
    $(function(){
        $.ajax({
            type:"GET",
            url:"${ctx}/pay/payInfo",
            data:{
                "projectid":${projectid},
                "returnid":${returnid}
            },
            success:function(result){
                var address = result.ext.address;
                var project = result.ext.project;
                var rtn = result.ext.rtn;
                var a = ''; // 地址
                $.each(address,function(index,addr){
                    a+= '<div class="radio">'
                        +'    <label>'
                        +'       <input type="radio" onclick="radioClick(this)"name="optionsRadios" id="optionsRadios'+(index+1) + '" value="'+addr.id + '">'
                        +'           <b style="font-size: 15px">'+addr.name + ' ' +addr.tel + ' ' + addr.address +'</b>'
                        +'    </label>'
                        +'</div>';
                });
                a+= '<div class="radio">'
                    +'    <label>'
                    +'        <input type="radio" onclick="radioClick(this)"name="optionsRadios"'
                    +'              value="new">'
                    +'          <b style="font-size: 15px">新地址</b>'
                    +'    </label>'
                    +'</div>';
                $("#myAddress").prepend(a);
                var b =''; // 内容
                b += '<tr>'
                    +'   <td>'+project.project_name+'</td>'
                    +'   <td>'+ project.initiator_name +'</td>'
                    +'   <td>'+rtn.content+'</td>'
                    +'   <td><input type="text" class="form-control" style="width:60px;" id="count" value="1" onchange="change(this)"></td>'
                    +'   <td style="color:#F60" id="price">￥'+ rtn.supportmoney +'</td>'
                    +'   <td >'+(rtn.freight == 0?'免运费':rtn.freight)+'</td>'
                    +'</tr>'
                $("#money").text("￥" + rtn.supportmoney * 1) // 默认1个
                $("#freight").text("￥" + rtn.freight)
                $("#totalmoney").text("￥" + parseInt(rtn.supportmoney*1 + rtn.freight))
                $("tbody").html(b);
            }
        });
    })
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    function change(btn){
        // slice(1) ;// 截取数字，第二个开始到最后
        var money = parseInt($(btn).val()) * parseInt($("#price").text().slice(1));
        var freight =  $("#freight").text().slice(1);
        var totalmoney = money + parseInt(freight);
        $("#money").empty().text("￥" + money)
        $("#totalmoney").empty().text("￥" + totalmoney)
    }
    function radioClick(radio){
        if ( $(radio).val() == 'new' ) {
            $(".newAddress").show();
        } else {
            $(".newAddress").hide();
        }
    };
    function addAddr(){
        $.ajax({
           type:"POST",
           url:"${ctx}/member/address/add",
           data:$("#addrForm").serialize(),
            success:function(result){
                if(result.code == 1){
                    var a ='';
                    var addr = result.content;
                    var count = $(":radio").length
                    a+= '<div class="radio">'
                        +'    <label>'
                        +'       <input type="radio" onclick="radioClick(this)"name="optionsRadios" id="optionsRadios'+count + '" value="'+addr.id + '">'
                        +'           <b style="font-size: 15px">'+addr.name + ' ' +addr.tel + ' ' + addr.address +'</b>'
                        +'    </label>'
                        +'</div>';
                    $("#myAddress").prepend(a);
                    layer.msg("添加成功",{icon:6,time:1500},function(){
                        $("#optionsRadios" + count).attr("checked",true)
                        $(".newAddress").hide();
                    })
                }else{
                    layer.msg("添加失败，请重试",{icon:5,time:1500,shift:6},function(){

                    })
                }
            }
        });
    }
    function pay(){
        var price = $("#price").text().slice(1);
        var count = $("#count").val();
        var totalmoney = $("#totalmoney").text().slice(1);
        if($(":radio:checked").length > 0){
            var addressid = $(":radio:checked").val()
            $.ajax({
               type:"POST",
               url:"${ctx}/pay/submit",
               data:{
                   "price":price,
                   "count":count,
                   "totalmoney":totalmoney,
                   "addressid":addressid,
                   "remark":$("#remark").val()
               },
                success:function(result){
                   if(result.code == 1){
                       // 接入支付宝沙箱支付，但没有公网地址，默认付款成功
                       window.location.href = "${ctx}/pay/success";
                   }else{
                       layer.msg("付款失败",{icon:5,time:1500,shift:6},function(){

                       })
                   }
                }
            });
        }else{
            layer.msg("请先设置收货信息",{icon:5,time:1500,shift:6},function(){

            })
        }
    }
</script>
</body>
</html>

