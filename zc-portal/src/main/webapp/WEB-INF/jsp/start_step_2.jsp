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
                                        <div class="progress-bar progress-bar-default" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;">1. 项目及发起人信息</div>
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
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;height:50px;">
                                            <div style="font-size:16px;margin-top:15px;">2. 回报设置</div>
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
                                            回报设置
                                        </b>
                                    </blockquote>
                                </div>
                                <div class="col-md-12 column">
                                    <table class="table table-bordered" style="text-align:center;">
                                        <thead>
                                        <tr style="background-color:#ddd;">
                                            <td>序号</td>
                                            <td>支付金额</td>
                                            <td>名额</td>
                                            <td>单笔限购</td>
                                            <td>回报内容</td>
                                            <td>回报时间</td>
                                            <td>运费</td>
                                            <td>是否可开发票</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody id="rtnContent">
                                        </tbody>
                                    </table>
                                    <button type="button" class="btn btn-primary btn-lg" onclick="$('.huibao').show();">
                                        <i class="glyphicon glyphicon-plus"></i> 添加回报</button>
                                    <input type="hidden"id="projectid" value="${projectTempId}">
                                    <div class="huibao" style="display:none;border:10px solid #f60;border-bottom-color: #eceeef;border-width: 10px;width:20px;margin-left:20px;
                                             border-left-color: rgba(221, 221, 221, 0);
                                             border-top-color: rgba(221, 221, 221, 0);
                                             border-right-color: rgba(221, 221, 221, 0);">
                                    </div>
                                    <div class="huibao panel panel-default" style="display:none; border-style: dashed;background-color:#eceeef">
                                        <div class="panel-body">
                                            <div class="col-md-12 column">
                                                <form class="form-horizontal" enctype="multipart/form-data">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">回报类型</label>
                                                        <div class="col-sm-10" id="return_type">
                                                            <label class="radio-inline">
                                                                <input type="radio"name="type"  value="A"> 实物回报
                                                            </label>
                                                            <label class="radio-inline">
                                                                <input type="radio"name="type" value="B"> 虚拟物品回报
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">支持金额（元）</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control" id="supportmoney"
                                                                   name="supportmoney"style="width:100px;" >
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">回报内容</label>
                                                        <div class="col-sm-10">
                                                            <textarea class="form-control" id="content" name="content"
                                                                      rows="5" placeholder="简单描述回报内容，不超过200字">
                                                            </textarea>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">回报数量（名）</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control" id="count" name="count"
                                                                   style="width:100px;display:inline" >
                                                            <label class="control-label">“0”为不限回报数量</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">单笔限购</label>
                                                        <div class="col-sm-10" >
                                                            <label class="radio-inline">
                                                                <input type="radio" name="signalpurchase"
                                                                       id="signalpurchase_n" value="0"> 不限购
                                                            </label>
                                                            <label class="radio-inline">
                                                                <input type="radio" name="signalpurchase"
                                                                       id="signalpurchase_y" > 限购
                                                            </label>
                                                            <input type="text" class="form-control" id="signalpurchase" style="width:100px;display:inline" >
                                                            <label class="radio-inline">默认数量为1，且不超过上方已设置的回报数量</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">限购</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control" id="purchase" style="width:100px;display:inline" value="0">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">运费(元)</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control" style="width:100px;display:inline"
                                                                   id="freight" name="freight" >
                                                            <label class="control-label">“0”为包邮</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">发票</label>
                                                        <div class="col-sm-10">
                                                            <label class="radio-inline" id="invoice">
                                                                <input type="radio" name="invoice" value="N"> 不可开发票
                                                            </label>
                                                            <label class="radio-inline">
                                                                <input type="radio" name="invoice" value="Y"> 可开发票（包括个人发票和自定义抬头发票）
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">回报时间</label>
                                                        <div class="col-sm-10">
                                                            <label class="radio-inline">
                                                                项目结束后
                                                            </label>
                                                            <input type="text" class="form-control" style="width:100px;display:inline"
                                                                   name="rtndate" id="rtndate">
                                                            <label class="radio-inline">天，向支持者发送回报</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-10">
                                                            <button type="button" class="btn btn-primary" id="confirm">确定</button>
                                                            <button type="button" class="btn btn-default" id="cancel">取消</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="container" >
                                    <div class="row clearfix">
                                        <div class="col-md-12 column" style="margin-top:10px;">
                                            <blockquote>
                                                <p >
                                                    <i class="glyphicon glyphicon-info-sign" style="color:#2a6496;"></i> 提示
                                                </p> <small>3个以上的回报：多些选择能提高项目的支持率。几十、几百、上千元的支持：3个不同档次的回报，能让你的项目更快成功。回报最好是项目的衍生品：<br>与项目内容有关的回报更能吸引到大家的支持。</small>
                                            </blockquote>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                    <div class="panel-footer" style="text-align:center;">
                        <button type="button" class="btn  btn-warning btn-lg" onclick="nextStep()">下一步</button>
                        <a class="btn" > 预览 </a>
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
    var index =0;
    $(function(){
        index++;
    });
    function nextStep(){
        var id = $("#projectid").val();
        window.location.href="${ctx}/crow/start_step3?projectTempId=" + id;
    }
    $("#confirm").click(function(){
        // new FormData($(".form-horizontal")[0])
        var type = $("#return_type input[name='type']:checked").val();
        var projectid = $("#projectid").val();
        var supportmoney =$("#supportmoney").val();
        var content =$("#content").val();
        var count = $("#count").val();
        var signalpurchase ;
        if($("#signalpurchase_n :checked")){
            signalpurchase = 0;
        }else if($("#signalpurchase_y :checked")){
            signalpurchase = $("#signalpurchase").val();
        }
        var purchase = $("#purchase").val();
        var freight = $("#freight").val();
        var invoice = $("#invoice input[name='invoice']:checked").val();
        var rtndate = $("#rtndate").val();
        $.ajax({
            type:"POST",
            url:"${ctx}/crow/addrtn",
            data: {
                "projectid":projectid,
                "type":type,
                "supportmoney":supportmoney,
                "content":content,
                "count":count,
                "signalpurchase":signalpurchase,
                "purchase":purchase,
                "freight":freight,
                "invoice":invoice,
                "rtndate":rtndate,
                  },
            success:function(result){
                if(result.code == 1){
                    var rtn = result.content;
                    var h= '<tr>'
                        +'      <td scope="row">'+ index + '</td>\n'
                        +'      <td>￥'+rtn.supportmoney+'</td>'
                        +'      <td>无限制</td>'
                        +'      <td>'+rtn.signalpurchase+'</td>'
                        +'      <td>'+rtn.content+'</td>'
                        +'      <td>项目结束后的'+rtn.rtndate+'天</td>'
                        +'      <td>'+rtn.freight+'</td>'
                        +'      <td>'+rtn.invoice+'</td>'
                        +'      <td>'
                        +'          <button type="button" class="btn btn-primary btn-xs">'
                        +'              <i class=" glyphicon glyphicon-pencil"></i>'
                        +'          </button>'
                        +'          <button type="button" class="btn btn-danger btn-xs">'
                        +'              <i class=" glyphicon glyphicon-remove"></i>'
                        +'          </button>'
                        +'      </td>'
                        +' </tr>';
                    $("#rtnContent").append(h);
                    $(".huibao").hide();
                    index++;
                }else{
                    alert("err")
                }
            }
        });
    })
</script>
</body>
</html>


