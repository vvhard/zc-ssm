<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    </style>
</head>
<body>
<%-- 携带seesion信息过来--%>
<%@include file="../commons/nav_bar.jsp"%>
<div class="container">
    <div class="row clearfix">
        <div class="col-sm-3 col-md-3 column">
            <div class="row">
                <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading" style="text-align: center;font-size: 18px;">个人信息</div>
                            <div class="panel-body">
                                <div class="thumbnail" >
                                    <img src="${ctx}/static/img/services-box1.jpg" class="img-thumbnail" alt="无法显示">
                                    <div class="caption" style="text-align:center;">
                                        <h3 id="username">
                                            ${portal_login_user.username}
                                        </h3>
                                        <%-- 根据数据库的记录验证是否已经进行实名认证--%>
                                    </div>
                                </div>
                                <div>
                                    <div>
                                        <label>
                                            <a onclick="editModal('${portal_login_user.loginacct}',
                                                    '${portal_login_user.username}',
                                                    '${portal_login_user.email}',
                                                    '${portal_login_user.tel}',
                                                    '${portal_login_user.description}')"
                                               data-toggle="modal" data-target="#my_modal">
                                                更新个人信息 </a>
                                        </label>
                                        <label style="margin-left: 10px">
                                            <a onclick=" modifyPassword('${portal_login_user.loginacct}')"
                                               data-toggle="modal" data-target="#my_modal"> 修改密码</a>
                                        </label><br>
                                    </div>
                                   <div style="margin-top: 10px;">
                                       <label>
                                           <span class="glyphicon glyphicon-user">账号:${portal_login_user.loginacct}</span>
                                       </label><br>
                                       <label>
                                           <span class="glyphicon glyphicon-user">用户名:${portal_login_user.username}</span>
                                       </label><br>
                                       <label>
                                           <span class="glyphicon glyphicon-envelope" title="Email">邮箱:${portal_login_user.email}</span>
                                       </label><br>
                                       <label><span class="glyphicon glyphicon-phone" title="Mobile">联系电话:${portal_login_user.tel}</span>
                                       </label><br>
                                       <label>简介:</label>${portal_login_user.description}<br>
                                   </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9 column">
            <div id="myTabContent" class="tab-content" style="margin-top:10px;">
                <div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
                    <ul id="myTab1" class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="#support" onclick="mySupport(1,'all')">我支持的</a></li>
                        <li role="presentation"><a href="#attension"onclick="myFollow(1)">我关注的</a></li>
                        <li role="presentation"><a href="#add"onclick="myInitiate(1,'all')">我发起的</a></li>
                        <li role="presentation"><a href="#addr"onclick="myAddress()">我的地址</a></li>
                        <li class=" pull-right">
                            <button type="button" class="btn btn-warning" onclick="start()">发起众筹</button>
                        </li>
                    </ul>
                    <div  class="tab-content" style="margin-top:10px;">
                        <!--我支持的-->
                        <div role="tabpanel" class="tab-pane fade active in" id="support" aria-labelledby="home-tab">
                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column" id="s_status">
                                        <a class="label label-warning" style="color:#000;"status="all">全部</a>
                                        <a class="label" style="color:#000;"status="Y">已支付</a>
                                        <a class="label " style="color:#000;"status="N">未支付</a>
                                    </div>
                                    <div class="col-md-12 column" style="margin-top:10px;padding:0;">
                                        <table class="table table-bordered" style="text-align:center;">
                                            <thead>
                                            <tr style="background-color:#ddd;">
                                                <td>项目信息</td>
                                                <td width="90">支持日期</td>
                                                <td width="120">支持金额（元）</td>
                                                <td width="80">回报数量</td>
                                                <td width="80">交易状态</td>
                                                <td width="120">操作</td>
                                            </tr>
                                            </thead>
                                            <tbody id="orderContent">
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td colspan="6" align="center">
                                                        <ul class="orderPagination pagination">
                                                            <!--内容通过ajax异步获取-->
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </tfoot>

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--我发起的-->
                        <div role="tabpanel" class="tab-pane fade" id="attension" aria-labelledby="attension-tab">
                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column" style="padding:0;">
                                        <table class="table table-bordered" style="text-align:center;">
                                            <thead>
                                            <tr style="background-color:#ddd;">
                                                <td>项目信息</td>
                                                <td width="120">支持人数</td>
                                                <td width="120">关注人数</td>
                                                <td width="120">操作</td>
                                            </tr>
                                            </thead>
                                            <tbody id="followContent">
                                            </tbody>
                                            <tfoot>
                                                <tr >
                                                    <td colspan="4" align="center">
                                                        <ul class="followPagination pagination">
                                                            <!--内容通过ajax异步获取-->
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </tfoot>

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--我关注的-->
                        <div role="tabpanel" class="tab-pane fade  " id="add" aria-labelledby="add-tab">
                            <div class="container-fluid">
                                <div class="row clearfix" id="i_status">
                                    <div class="col-md-12 column">
                                        <a class="label label-warning" style="color:#000;" status="all">全部</a>
                                        <a class="label" style="color:#000;" status="I">众筹中</a>
                                        <a class="label " style="color:#000;" status="S">众筹成功</a>
                                        <a class="label " style="color:#000;" status="F">众筹失败</a>
                                    </div>
                                    <div class="col-md-12 column" style="padding:0;margin-top:10px;">
                                        <table class="table table-bordered" style="text-align:center;">
                                            <thead>
                                            <tr style="background-color:#ddd;">
                                                <td>项目信息</td>
                                                <td width="120">募集金额（元）</td>
                                                <td width="80">当前状态</td>
                                                <td width="120">操作</td>
                                            </tr>
                                            </thead>
                                            <tbody id="initiateContent">
                                            </tbody>
                                            <tfoot>
                                            <tr >
                                                <td colspan="4" align="center">
                                                    <ul class="initiatePagination pagination">
                                                        <!--内容通过ajax异步获取-->
                                                    </ul>
                                                </td>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--我的地址-->
                        <div role="tabpanel" class="tab-pane fade  " id="addr" aria-labelledby="addr-tab">
                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column" id="myAddress">
                                    </div >

                                    <div class="col-md-12 column" style="padding:0 30px;" >
                                        <div style="margin-top: 15px;">
                                            <button type="button" class="btn btn-primary" onclick="addAddress()">
                                                <b style="font-size: 15px">新地址</b>
                                            </button>
                                        </div>
                                        <div class="newAddress panel panel-default" style="display:none;border-style: dashed;background-color:#eceeef;margin-top: 15px">
                                            <div class="panel-body">
                                                <div type="button" class="close" style="float: right" onclick="closeDiv()">&times;</div>
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
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../commons/footer.jsp"%>
<!-- 添加用户模态框（Modal） -->
<div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;height: 420px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" style="text-align: center"></h3>
            </div>
            <div class="modal-body">
            </div>
            <%--<div class="modal-footer" style="text-align: center">--%>
            <%--</div>--%>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
           url:"${ctx}/member/authstatus",
           data:
               {
                   "memberid":${portal_login_user.id}
                },
           success:function(result){  var h = '';
               if(result.code == 1){
                   var member = result.content;
                   if(member.authstatus =="Y"){
                       h+= '<span class="label label-success" style="cursor:pointer;" onclick="auth(\'Y\')">'+member.accttype+'</span>';
                   }else if(authstatus == 'N'){
                       h += '<span class="label label-danger" style="cursor:pointer;" onclick="auth(\'N\')">未实名认证</span>'
                   }else {
                       h+= '<span class="label label-warning" style="cursor:pointer;" onclick="auth(\'CHECKING\')">实名信息待审核</span>'
                   }
               }else{
                   h += '<span class="label label-danger" style="cursor:pointer;" onclick="auth(\'N\')">未实名认证</span>'
               }
               $("#username").after(h);
           }
        }); // ajax
        mySupport(1,'all');
    })
    /*选择项目状态时的点击事件处理*/
    $("div[id='s_status'] a").click(function(){
        $("div[id='s_status'] a.label-warning").removeClass(" label-warning").addClass("label");
        $(this).addClass(" label-warning");
        var status =  $(this).attr("status");
        mySupport(1,status) ; //重新加载数据
    });
    /*选择项目状态时的点击事件处理*/
    $("div[id='i_status'] a").click(function(){
        $("div[id='i_status'] a.label-warning").removeClass("label label-warning").addClass("label");
        $(this).addClass("label-warning");
        var status =  $(this).attr("status");
        myInitiate(1,status) ; //重新加载数据
    });
    function mySupport(pageno,status){
        $.ajax({
           type:"GET",
           url:"${ctx}/member/support",
           data:{
               "memberid":${portal_login_user.id},
               "pageno":pageno,
               "pagesize":2,
                "status":status
                },
           success:function(result) {
               if(result.code == 1){
                   var orderPage = result.content; // 分页对象
                   var orders = orderPage.datas;
                   var h = '';
                   var pageContent = '';
                   if(orders.length == 0){
                       h+= '<tr ><td colspan="6" align="center">暂时没有支持过项目</td> </tr>'
                   }
                   $.each(orders,function(index,order){
                       h+='<tr>';
                       h+='<td style="vertical-align:middle;">'
                           +'   <div class="thumbnail">'
                           +'       <div class="caption">'
                           +'           <h3>'
                           +'               <a onclick="projectDetail('+order.project_id+')">'+order.project_name+'</a>'
                           +'           </h3>'
                           +'           <p>'
                           +'              订单编号:' + order.order_num
                           +'           </p>'
                           +'           <p>'
                           +'               <div style="float:left;">'
                           +'                   <i class="glyphicon glyphicon-screenshot" title="目标金额" ></i> '
                           +'                       已完成'+ order.completion+'% '
                           +'               </div>'
                           +'               <div style="float:right;">'
                           +'                   <i title="截至日期" class="glyphicon glyphicon-calendar"></i> '
                           +'                      剩余'+order.remainingDay+'天 '
                           +'               </div>'
                           +'           </p>'
                           +'           <br>'
                           +'           <div class="progress" style="margin-bottom: 4px;">'
                           +'               <div class="progress-bar progress-bar-success" role="progressbar" '
                           +'                    aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width:'+order.completion+'%">'
                           +'                   <span >众筹中</span>'
                           +'               </div>'
                           +'           </div>'
                           +'       </div>'
                           +'   </div>'
                           +' </td>';
                       h+='<td style="vertical-align:middle;">'+order.support_time+'</td>';
                       h+='<td style="vertical-align:middle;">'+order.support_money+'<br>(运费：0.00 )</td>';
                       h+='<td style="vertical-align:middle;">'+order.return_count+'</td>';
                       if(order.order_status == 'Y'){
                           h+='<td style="vertical-align:middle;">交易完成</td>';
                       }else{
                           h+='<td style="vertical-align:middle;">交易未完成</td>';
                       }
                       h+='<td style="vertical-align:middle;">'
                           +'    <div class="btn-group-vertical" role="group" aria-label="Vertical button group">'
                           +'       <button type="button" class="btn btn-default" onclick="deleteOrder('+order.order_num+')">删除订单</button>'
                           +'       <button type="button" class="btn btn-default" onclick="orderDetail('+order.order_num+')" '
                           +'           data-toggle="modal" data-target="#my_modal">交易详情</button>'
                           +'     </div>'
                           +'</td>';
                       h+='</tr>';
                   }); // each
                   // 动态生成分页导航
                   if (pageno > 1) {
                       pageContent +='<li><a href="#" onclick="mySupport('+(pageno - 1)+',\''+status+'\')">上一页</a></li>';
                   }
                   // 循环生成1 2 3 4 5 等等
                   for (var i = 1; i <= orderPage.totalno; i++) {
                       if (i == pageno) {
                           pageContent += '<li class="active"><a href="#" >'+ i +'</a></li>';
                       } else {
                           pageContent += '<li ><a href="#" onclick="mySupport('+ i +',\''+status+'\')">' + i + '</a></li>';
                       }
                   }
                   if (pageno < orderPage.totalno) {
                       pageContent += '<li ><a href="#" onclick="mySupport('+  (pageno+1) +',\''+status+'\')">下一页</a></li>';
                   }
                   $("#orderContent").html(h);
                   $(".orderPagination").html(pageContent);
               }else{
                   layer.msg("获取数据失败,请稍后重试",{icon:5,time:1500},function(){
                   });
               }
           }
        });
    }
    function myFollow(pageno){
        $.ajax({
            type:"GET",
            url:"${ctx}/member/follow",
            data:{
                    "memberid":${portal_login_user.id},
                    "pageno":pageno,
                    "pagesize":2
                },
            success:function(result) {
                if(result.code == 1){
                    var projectPage = result.content;
                    var projects = projectPage.datas;
                    var h='';
                    var pageContent ='';
                    if(projects.length == 0){
                        h+= '<tr ><td colspan="4" align="center">暂时没有关注的项目 </td> </tr>'
                    }
                    $.each(projects,function (index,project) {
                        h+='<tr>';
                        h+='<td style="vertical-align:middle;">'
                            +'  <div class="thumbnail">'
                            +'      <div class="caption">'
                            +'      <p>'
                            +'          <a onclick="projectDetail('+project.id+')">' + project.name +'</a>'
                            +'      </p>'
                            +'      <p>'
                            +'          <div style="float:left;">'
                            +'              <i class="glyphicon glyphicon-screenshot" title="目标金额" ></i>'
                            +'                  已完成'+project.completion+'% '
                            +'          </div>'
                            +'          <div style="float:right;">'
                            +'               <i title="截至日期" class="glyphicon glyphicon-calendar"></i> '
                            +'                  剩余'+project.remainingDay+'天 '
                            +'           </div>'
                            +'      </p>'
                            +'      <br>'
                            +'          <div class="progress" style="margin-bottom: 4px;">'
                            +'              <div class="progress-bar progress-bar-success" role="progressbar" '
                            +'                  aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width:'+project.completion+'%">'
                            +'              <span >众筹中</span>'
                            +'              </div>'
                            +'          </div>'
                            +'      </div>'
                            +'  </div>'
                            +'</td>';
                        h+='<td style="vertical-align:middle;">'+project.supporter+'</td>';
                        h+='<td style="vertical-align:middle;">'+project.follower+'</td>';
                        h+='<td style="vertical-align:middle;">'
                            +'  <div class="btn-group-vertical" role="group" aria-label="Vertical button group">'
                            +'      <button type="button" class="btn btn-default" onclick="cancelFollow('+project.id+')">取消关注</button>'
                            +'  </div>'
                            +'</td>';
                        h+='</tr>';
                    });
                    // 动态生成分页导航
                    if (pageno > 1) {
                        pageContent +='<li><a href="#" onclick="myFollow('+(pageno - 1)+')">上一页</a></li>';
                    }
                    // 循环生成1 2 3 4 5 等等
                    for (var i = 1; i <= projectPage.totalno; i++) {
                        if (i == pageno) {
                            pageContent += '<li class="active"><a href="#" >'+ i +'</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="myFollow('+ i +')">' + i + '</a></li>';
                        }
                    }
                    if (pageno < projectPage.totalno) {
                        pageContent += '<li ><a href="#" onclick="myFollow('+  (pageno+1)+')">下一页</a></li>';
                    }
                    $("#followContent").html(h);
                    $(".followPagination").html(pageContent);
                }else{
                    layer.msg("获取数据失败,请稍后重试",{icon:5,time:1500},function(){

                    });
                }
            }
        });
    }
    // 我发起的
    function myInitiate(pageno,status){
        $.ajax({
            type:"GET",
            url:"${ctx}/member/initiate",
            data:{
                "memberid":${portal_login_user.id},
                "pageno":pageno,
                "pagesize":2,
                "status":status
                },
            success:function(result) {
                if(result.code == 1){
                    var h='';
                    var pageContent ='';
                    var projectPage = result.content;
                    var projects = projectPage.datas;
                    if(projects.length == 0){
                        h+= '<tr ><td colspan="4" align="center">暂时没有发起项目 </td> </tr>';
                    }
                    $.each(projects,function (index,project) {
                        h+='<tr>'
                            +'<td style="vertical-align:middle;">'
                            +'  <div class="thumbnail">'
                            +'      <div class="caption">'
                            +'      <p>'
                            +'          <a onclick="projectDetail('+project.id+')">' + project.name +'</a>'
                            +'      </p>'
                            +'      <p>'
                            +'          <div style="float:left;">'
                            +'              <i class="glyphicon glyphicon-screenshot" title="目标金额" ></i> 已完成'+project.completion+'% '
                            +'          </div>'
                            +'          <div style="float:right;">'
                            +'               <i title="截至日期" class="glyphicon glyphicon-calendar"></i> 剩余'+project.remainingDay+'天 '
                            +'           </div>'
                            +'      </p>'
                            +'      <br>'
                            +'          <div class="progress" style="margin-bottom: 4px;">'
                            +'              <div class="progress-bar progress-bar-success" role="progressbar" '
                            +'                  aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width:'+project.completion+'%">'
                            +'              <span >众筹中</span>'
                            +'              </div>'
                            +'          </div>'
                            +'      </div>'
                            +'  </div>'
                            +'</td>';
                        h+='<td style="vertical-align:middle;">'+project.money+'</td>';
                       if(project.status == 'I'){
                           h+='<td style="vertical-align:middle;">众筹中</td>';
                       }else if(project.status == 'C'){
                           h+='<td style="vertical-align:middle;">众筹成功</td>';
                       }else {
                           h+='<td style="vertical-align:middle;">众筹失败</td>';
                       }
                        h+='<td style="vertical-align:middle;">'
                            +'  <div class="btn-group-vertical" role="group" aria-label="Vertical button group">'
                            +'      <button type="button"onclick="projectDetail('+project.id+')" class="btn btn-default">项目预览</button>'
                            +'  </div>'
                            +'</td>';
                        h+='</tr>';
                    });
                    // 动态生成分页导航
                    if (pageno > 1) {
                        pageContent +='<li><a href="#" onclick="myInitiate('+(pageno - 1)+',\''+status+'\')">上一页</a></li>';
                    }
                    // 循环生成1 2 3 4 5 等等
                    for (var i = 1; i <= projectPage.totalno; i++) {
                        if (i == pageno) {
                            pageContent += '<li class="active"><a href="#" >'+ i +'</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="myInitiate('+ i +',\''+status+'\')">' + i + '</a></li>';
                        }
                    }
                    if (pageno < projectPage.totalno) {
                        pageContent += '<li ><a href="#" onclick="myInitiate('+ (pageno+1) +',\''+status+'\')">下一页</a></li>';
                    }
                    $("#initiateContent").html(h);
                    $(".initiatePagination").html(pageContent);
                }else{
                    layer.msg("获取数据失败,请稍后重试",{icon:5,time:1500},function(){

                    });
                }
            }
        });
    }
    $('#myTab1 a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    function auth(status) {
        if('Y' == status){
            // 查看认证信息页面
        }
        else if(status == 'N'){
            window.location.href = "${ctx}/member/auth" // 跳转到实名认证页面
        }else {
            // 查看认证信息页面
        }
    }
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
        });
    }
    function projectDetail(projectid){
        window.location.href = "${ctx}/project/toDetail?id="+projectid;
    }
    function orderDetail(ordernum){
        $.ajax({
            type:"GET",
            url:"${ctx}/member/order/detail",
            data:{"ordernum":ordernum},
            success:function(result){
                $(".modal-title").empty().text("订单详情");
                if(result.code == 1){
                    var order = result.content;
                    var body = '';
                    body+='<div style=" border:1px solid #ddd">'
                        +'      <div style="margin-top:10px;margin-left: 10px;">'
                        +'          <label>订单编号：</label>' + order.order_num
                        +'          <label style="margin-left: 30px">创建时间：</label>' + order.create_date
                        +'          <label style="margin-left: 50px">订单状态：</label>' + (order.status =='N'?'未完成':'已完成')
                        +'          <label style="margin-left: 30px">是否开具发票:</label>' + (order.invoice == 'N'?'是':'否')
                        +'      </div>'
                        +'      <div style="margin-top:20px;margin-left: 10px;margin-right: 10px">'
                        +'          <table class="table table-bordered" style="text-align:center;">'
                        +'              <thead>'
                        +'                  <tr style="background-color:#ddd;"> '
                        +'                      <td>项目名称</td>'
                        +'                      <td>发起人</td>'
                        +'                      <td width="300">回报内容</td>'
                        +'                      <td width="80">数量</td>'
                        +'                      <td>支持单价</td>'
                        +'                      <td>配送费用</td>'
                        +'                  </tr>'
                        +'              </thead>'
                        +'              <tbody>'
                        +'                  <tr> '
                        +'                      <td>'+order.project_name+'</td>'
                        +'                      <td>'+order.realname+'</td>'
                        +'                      <td>'+order.content+'</td>'
                        +'                      <td>'+order.count+'</td>'
                        +'                      <td style="color:#F60">￥ '+order.price+'</td>'
                        +'                      <td>'+(order.freight == 0?'免运费':order.freight)+'</td>'
                        +'                  </tr>'
                        +'              </tbody>'
                        +'          </table>'
                        +'      </div>'
                        +'      <div style="margin-top:10px;margin-left: 10px;">'
                        +'          <label>收货人姓名：</label>'+order.receipt_name+'<br>'
                        +'          <label>联系电话：</label>'+order.tel+'<br>'
                        +'          <label>收货地址：</label>'+order.address+'<br>'
                        +'          <label>备注:</label>'+order.remark+'<br>'
                        +'      </div>'
                        +'      <div style="float:right;margin-top: 30px">'
                        +'          <p style="font-size:20px;">总价(含运费):<span style="font-size:20px;color:#F60;">￥'
                        +               (order.price * order.count + order.freight)
                        +'              </span>'
                        +'          </p>'
                        +'      </div>'
                        +'</div>';
                    $(".modal-body").empty().html(body);
                }else{
                    // layer.msg(result.msg,{icon:5,time:2000},function(){
                    // });
                    var body ='<div style="float:left;margin-left: 10px">获取数据错误</div>' ;
                    $(".modal-body").empty().html(body);
                }
            }
        });
    }
    function deleteOrder(ordernum){
        layer.confirm("是否删除订单信息?", {
            icon : 3,
            title : '提示'
        }, function(cindex) {
            // 删除信息
            $.ajax({
                type : "POST",
                url : "${ctx}/member/order/delete",
                data : {
                    "ordernum":ordernum
                },
                success : function(result) {
                    if (result.code == 1) {
                        layer.msg("删除成功",{time:2000,icon:6},function(){
                            // 回调函数做页面跳转,跳转到列表
                           mySupport(1,'all');
                        });
                    } else {
                        layer.msg("删除失败", {
                            time : 2000,
                            icon : 5,
                            shift : 6
                        }, function() {});
                    }
                } // success
            }); // ajax
        }, function(cindex) {
            layer.close(cindex);
        }); // layer回调
    }
    function cancelFollow(projectid){
        layer.confirm("是否取消关注该项目?", {
            icon : 3,
            title : '提示'
        }, function(cindex) {
            // 删除信息
            $.ajax({
                type : "POST",
                url : "${ctx}/member/project/cancelFollow",
                data : {
                    "memberid":${portal_login_user.id},
                    "projectid":projectid
                },
                success : function(result) {
                    if (result.code == 1) {
                        layer.msg("取消关注成功",{time:2000,icon:6},function(){
                            // 回调函数做页面跳转,跳转到列表
                            myFollow(1);
                        });
                    } else {
                        layer.msg("取消关注失败", {
                            time : 2000,
                            icon : 5,
                            shift : 6
                        }, function() {});
                    }
                } // success
            }); // ajax
        }, function(cindex) {
            layer.close(cindex);
        }); // layer回调
    }
    function editModal(loginacct,username,email,tel,description){
        $(".modal-title").empty().text("信息修改");
        var body ='<div>'
                +'    <form id="editForm">'
                +'      <div class="form-group">'
                +'         <label >用户名</label>'
                +'              <input type="hidden" readonly class="form-control" name="loginacct" value="' +loginacct + '">'
                +'              <input type="text" class="form-control" name="username" id="un" value="' +username + '">'
                +'      </div>'
                +'      <div class="form-group">'
                +'          <label>邮箱地址</label>'
                +'          <input type="email"class="form-control" name="email" id="email" value="' + email+'">'
                +'      </div>'
                +'      <div class="form-group">'
                +'          <label>联系电话</label>'
                +'              <input type="text" class="form-control"name="tel" id="tel" value="' +tel + '">'
                +'      </div>'
                +'      <div class="form-group">'
                +'          <label>简介</label>'
                +'              <input type="text"class="form-control" name="description" id="description" value="' +description + '">'
                +'      </div>'
                +'   </form>'
                +'</div>'
                +'<div style="text-align: center">'
                +'   <button type="button" id="modifyBtn" onclick="update()" class="btn btn-success">修改</button>'
                +'   <button type="reset" class="btn btn-danger" onclick="reset()" class="glyphicon glyphicon-refresh">重置</button>'
                +'</div>'
        $(".modal-body").empty().html(body);
    }
    function modifyPassword(loginacct){
        $(".modal-title").empty().text("修改密码");
        var body ='<div>'
            +'    <form id="editForm">'
            +'      <div class="form-group">'
            +'         <label >旧密码</label>'
            +'              <input type="hidden" readonly class="form-control" name="loginacct" value="' +loginacct + '">'
            +'              <input type="password" class="form-control"name="oldPassword" id="oldPassword">'
            +'      </div>'
            +'      <div class="form-group">'
            +'          <label>新密码</label>'
            +'          <input type="password"class="form-control" name="newPassword" id="newPassword" >'
            +'      </div>'
            +'   </form>'
            +'</div>'
            +'<div style="text-align: center">'
            +'   <button type="button" id="modifyBtn" onclick="updatePassword()" class="btn btn-success">确定</button>'
            +'   <button type="reset" class="btn btn-danger" onclick="reset()" class="glyphicon glyphicon-refresh">重置</button>'
            +'</div>'
        $(".modal-body").empty().html(body);
    }
    function myAddress(){
        $("#myAddress").empty();
        $.ajax({
            type:"GET",
            url:"${ctx}/member/address",
            data:{
                "memberid":${portal_login_user.id}
            },
            success:function(result){
                var address = result.content;
                var body = ''; // 地址
                $.each(address,function(index,addr){
                    body+= '<div class="col-sm-4 col-sm-offset-2"  style="float: left;height:100px;width:300px;background-color:#eceeef;'
                        +'                  margin-left: 20px;margin-top: 10px" >'
                        +' <div type="button" class="close" style="float: right" onclick="delAddress('+addr.id +')">&times;</div>'
                        +'<div style="margin-top: 10px">'
                        +'    <label style="margin-left: 5px;margin-top: 5px">姓名:'
                        +'           <b style="font-size: 14px">'+addr.name+'</b>'
                        +'    </label><br>'
                        +'    <label style="margin-left: 5px;margin-top: 5px">电话:'
                        +'           <b style="font-size: 14px">'+addr.tel + '</b>'
                        +'    </label><br>'
                        +'    <label style="margin-left: 5px;margin-top: 5px">地址:'
                        +'           <b style="font-size: 14px">'+ addr.address +'</b>'
                        +'    </label>'
                        +'</div>'
                        +'</div>';
                });
                $("#myAddress").html(body);
                $(".newAddress").hide();
            }
        });
    }
    function addAddress(){
        $(".newAddress").show();
    }
    function addAddr(){
        $.ajax({
            type:"POST",
            url:"${ctx}/member/address/add",
            data:$("#addrForm").serialize(),
            success:function(result){
                if(result.code == 1){
                    layer.msg("添加成功",{icon:6,time:1500},function(){
                        myAddress();
                        $(".newAddress").hide();
                    })
                }else{
                    layer.msg("添加失败，请重试",{icon:5,time:1500,shift:6},function(){

                    })
                }
            }
        });
    }
    function delAddress(id){
        layer.confirm("是否删除该地址信息?", {
            icon : 3,
            title : '提示'
        }, function(cindex) {
            // 删除信息
            $.ajax({
                type : "POST",
                url : "${ctx}/member/address/delete",
                data : {
                    "addressid": id
                },
                success : function(result) {
                    if (result.code == 1) {
                        layer.msg("删除成功",{time:2000,icon:6},function(){
                            myAddress();
                        });
                    } else {
                        layer.msg("删除失败", {
                            time : 2000,
                            icon : 5,
                            shift : 6
                        }, function() {});
                    }
                } // success
            }); // ajax
        }, function(cindex) {
            layer.close(cindex);
        }); // layer回调
    }
    function closeDiv(){
        $(".newAddress").hide();
    }
    function update(){
        var username=$("#un").val()
        var email =$("#email").val()
        var tel = $("#tel").val()
        var description = $("#description").val()

        if(isNullStr(username)){
            layer.msg("请填写完成用户名",{icon:5,time:1500,shift:6},function(){
            })
            return ;
        }
        if(isNullStr(tel)){
            layer.msg("请填写联系电话",{icon:5,time:1500,shift:6},function(){
            })
            return ;
        }

        if(notEmail(email)){
            layer.msg("请正确填写邮箱",{icon:5,time:1500,shift:6},function(){
            })
            return ;
        }
        $.ajax({
            type:"POST",
            url:"${ctx}/member/update",
            data:$("#editForm").serialize(),
            success:function (result) {
                if(result.code == 1){
                    layer.msg("修改成功",{time:1500,icon:6},function(){
                        $("#my_modal").modal("hide");
                        window.location.href="${ctx}/member/personal_center?id=" + ${portal_login_user.id};
                    });
                }else{
                    layer.msg("修改失败，请重试", {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {});
                }
            } //success
        });// ajax
    }
    function updatePassword(){
        var oldPassword=$("#oldPassword").val()
        var newPassword=$("#newPassword").val()
        if(isNullStr(oldPassword) || isNullStr(newPassword)){
            layer.msg("请检查所有信息是否填写完成",{icon:5,time:1500,shift:6},function(){
            })
            return ;
        }
        $.ajax({
            type:"POST",
            url:"${ctx}/member/updatePassword",
            data:$("#editForm").serialize(),
            success:function (result) {
                if(result.code == 1){
                    layer.msg("修改成功",{time:1500,icon:6},function(){
                        $("#my_modal").modal("hide");
                        window.location.href="${ctx}/member/personal_center?id=" + ${portal_login_user.id};
                    });
                }else{
                    layer.msg("修改失败，请重试", {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {});
                }
            } //success
        });// ajax
    }
    function reset(){
        // 不要用$("#addForm").reset();
        if(document.getElementById("addForm") != null){
            document.getElementById("addForm").reset();
        }
        if(document.getElementById("editForm") != null){
            document.getElementById("editForm").reset();
        }
    }
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