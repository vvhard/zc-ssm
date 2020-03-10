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
        .label-text {
            margin:0 10px;
            text-decoration: none;
        }
        .panel {
            border-radius:0;
        }
        h3.break {
            font-size:16px;
            display: block;
            white-space: nowrap;
            word-wrap: normal;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        h3.break>a {
            text-decoration:none;
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
                <div class="panel panel-default">
                    <div class="panel-body">
                        <ul id="typeList" style="list-style: none;">
                            <li id="category_li">
                            </li>
                            <li id="status_li">
                                状态：
                                <a class="label-status active"  value="all">全部</a>
                                <a class="label-text"  value="W">即将开始</a>
                                <a class="label-text"  value="I">众筹中</a>
                                <a class="label-text"  value="C">众筹成功</a>
                            </li>
                            <li  id="order_li">
                                排序：
                                <a class="label-order active"  value="all">综合排序</a>
                                <a class="label-text"  value="time">最新上线</a>
                                <a class="label-text"  value="money">金额最多</a>
                                <a class="label-text"  value="supporter">支持最多</a>
                            </li>
                        </ul>
                    </div>
                    <div class="panel-footer" style="height:50px;padding:0;">
                        <div style="float:left;padding:15px;">
                            共<i id="project_num">${projects_count}</i>个众筹项目
                        </div>
                        <div style="float:right;">
                            <div class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" id="queryContent" class="form-control" placeholder="请输入搜索内容">
                                </div>
                                <button  onclick="search()" class="btn btn-default"><i class="glyphicon glyphicon-search"></i> 搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--第一行-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row" id="row1">

                </div>
            </div>
        </div>
    </div>
    <!--第二行-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row" id="row2">
                </div>
            </div>
        </div>
    </div>
    <!--第三行-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row" id="row3">
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column" style="text-align:center">
                <ul class="pagination">
                </ul>
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
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    $(function () {
        genCategory();
    })
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
    /*选择项目分类时的点击事件处理*/
    function changeType(a){
        $(".label-type.active").removeClass("label-type active").addClass("label-text");
        $(a).removeClass("label-text").addClass("label-type active");
        loadData(1) ; //重新加载数据
    };
    /*选择项目状态时的点击事件处理*/
    $("li[id='status_li'] a").click(function(){
        $(".label-status.active").removeClass("label-status active").addClass("label-text");
        $(this).removeClass("label-text").addClass("label-status active");
        loadData(1) ; //重新加载数据
    });

    /*选择排序方式时的点击事件处理*/
    $("li[id='order_li'] a").click(function(){
        $(".label-order.active").removeClass("label-order active").addClass("label-text");
        $(this).removeClass("label-text").addClass("label-order active");
        loadData(1) ; //重新加载数据
    });
    function loadData(pageno){
        $("#row1").empty();
        $("#row2").empty();
        $("#row3").empty();
        var type = $(".label-type.active").attr("value");
        var status = $(".label-status.active").attr("value");
        var order = $(".label-order.active").attr("value");
        var jsonData = {
            "pageno" : pageno,
            "pagesize" : 12, // 默认12
            "type":type,
            "status":status,
            "order":order
        };
        // 拼接查询条件
        if ($("#queryContent").val() != null && $("#queryContent").val().trim() != '') {
            jsonData.queryContent = $("#queryContent").val()
        }
        console.log(jsonData)
        $.ajax({
            type : "GET",
            url : "${ctx}/project/asyncLoadData",
            data : jsonData,
            success : function(result) {
                // 返回内容不为空
                if (result.code == 1) {
                    var page = result.content; // 获取分页对象json
                    var projects = page.datas; // 取出project
                    var h ='';
                    var cur_row = 1;
                    $("#project_num").empty().text(page.totalsize);
                    // 默认一行4个
                    $.each(projects,function(index, project) {
                        var project = projects[index];
                        h +='<div class="col-md-3">'
                            +'  <div class="thumbnail">'
                            +'      <img style="height: 155px" alt="300x200" onclick="details('+ project.id+')"'
                            +'           src="'+project.headpicpath+'"/>'
                            +'      <div class="caption">'
                            +'          <h3 class="break">'
                            +'              <a onclick="details(' + project.id+ ')">'+project.name+'</a>'
                            +'          </h3>'
                            +'          <p>'
                            +'              <div style="float:left;">'
                            +'                  <i class="glyphicon glyphicon-screenshot" title="目标金额" ></i>'
                            +                       project.money
                            +'              </div>'
                            +'              <div style="float:right;"><i title="上线日期" class="glyphicon glyphicon-calendar">'
                            +'                  </i>'+ project.deploydate
                            +'              </div>'
                            +'          </p>'
                            +'          <br>'
                            +'          <div class="progress" style="margin-bottom: 4px;">'
                            +'              <div class="progress-bar progress-bar-success" role="progressbar" '
                            +'                    aria-valuenow="'+project.completion+'" aria-valuemin="0" '
                            +'                    aria-valuemax="100" style="width:'+project.completion +'%">'
                            +'                  <span >' + project.completion + '% </span>'
                            +'              </div>'
                            +'          </div>'
                            +'          <div><span style="float:right;"><i class="glyphicon glyphicon-star-empty" ></i></span>'
                            +'              <span ><i class="glyphicon glyphicon-user" title="支持人数"></i>'
                            +                   project.supporter
                            +'              </span>'
                            +'          </div>'
                            +'      </div><!--caption-->'
                            +'  </div> <!--thumbnail-->'
                            +'</div> <!--col-->'
                        row = (parseInt(index / 4) +1); // js int除法默认不向下/或向上取整
                        $("#row" + row).append(h);
                        h = '';
                    }); // each
                    // 动态生成分页导航
                    var pageContent = '';
                    if (pageno > 1) {
                        pageContent += '<li><a href="#" onclick="loadData('
                            + (pageno - 1) + ')">上一页</a></li>';
                    }
                    // 循环生成1 2 3 4 5 等等
                    for (var i = 1; i <= page.totalno; i++) {
                        if (i == pageno) {
                            pageContent += '<li class="active"><a href="#" >'
                                + i + '</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="loadData('
                                + i + ')">' + i + '</a></li>';
                        }
                    }
                    if (pageno < page.totalno) {
                        pageContent += '<li ><a href="#" onclick="loadData('
                            + (pageno + 1) + ')">下一页</a></li>';
                    }
                    $(".pagination").html(pageContent);
                } else { // 返回内容为空
                    layer.msg("数据失败", {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {});
                } // else
            } // success
        }); // ajax
    }
    function search(){
        loadData(1)
    }
    /*生成导航栏分类列表内容*/
    function genCategory(){
        $.ajax({
            type:"GET",
            url:"${ctx}/types",
            data:{},
            success:function(result){
                var h = '分类：'
                        +'<a class="label-text" id="all_type_span" onclick="changeType(this)" value="all">全部</a>';
                var flg = false;
                var currentType = '${current_selected_type}';
                if(result.code == 1){
                    var content = result.content; // 获取
                    $.each(content,function(index,value){
                       var type = value;
                       if(type.name == currentType){
                           h += '<a class="label-type active" onclick="changeType(this)" value="'+ type.name + '">'+ type.name +'</a>';
                           flg = true; // 标志
                       }else{
                            h += '<a class="label-text"  onclick="changeType(this)" value="'+ type.name + '">'+ type.name +'</a>';
                       }
                    }); // each
                    if(!flg){
                        $("#all_type_span").addClass("label-type active")
                    }
                }else{
                    $("#all_type_span").addClass("label-type active")
                } // if-else
                $("#category_li").html(h);
                loadData(1) ; // 加载第一页
            } // success
        }); //ajax
    }
    /*项目详情*/
    function details(id){
        window.location.href ="${ctx}/project/toDetail?id=" + id ;
    }
</script>
</body>
</html>


