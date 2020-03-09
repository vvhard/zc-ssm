<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        /*这是一个用做回显的盒子的样式*/
        .pic {
            width: 250px;
            height: 300px;
        }

        .pic img {
            width: 100%;
            height: 100%;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<%@ include file="/WEB-INF/commons/jsp/nav_bar.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/commons/jsp/menu.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th>项目名称</th>
                                <th>发起人</th>
                                <th>项目简介</th>
                                <th>目标金额</th>
                                <th>众筹周期(天)</th>
                                <th>项目详情</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="proData">
                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="8" align="center">
                                    <ul class="pagination">
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
    </div>
</div>
<!-- Modal-->
<div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" style="text-align: center"></h3>
            </div>
            <div class="modal-body col-sm-12">

            </div>
            <div class="modal-footer" style="text-align: center">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
        // 列表展开
        $("a[href='${ctx}/serviceaudi/project/index']").css("color", "red");
        //加上tree close样式
        $("a[href='${ctx}/serviceaudi/project/index']").parents(".list-group-item")
            .removeClass("tree-closed");
        $("a[href='${ctx}/serviceaudi/project/index']").parent().parent("ul").show(100);
        asyncRequesyData(1);
    });


    function  asyncRequesyData(pageno) {
        var loadingIndex = null;
        var jsonData = {
            "pageno" : pageno,
            "pagesize" : 10
        };
        $.ajax({
            type : "POST",
            url : "${ctx}/serviceaudi/project/asyncRequestData",
            data : jsonData,
            beforeSend : function() {
                loadingIndex = layer.msg("处理中", {icon : 16});
            },
            success : function(result) {
                layer.close(loadingIndex);
                // 返回内容不为空
                if (result.code == 1) {
                    var tableContent = "";
                    var pageContent = "";
                    var projectPage = result.content; // 每一页
                    var projects = projectPage.datas; // 用户
                    if(projects.length == 0){
                        tableContent += '<tr ><td colspan="9" align="center">暂时没有待审核的项目申请 </td> </tr>'
                    }
                    $.each(projects,function(index, project) {
                        tableContent += '<tr id=" '+ project.id + '">';
                        tableContent += '	 <td>' + (index + 1) + '</td>';
                        tableContent += '	 <td>' + project.name + '</td>';
                        tableContent += '    <td>' + project.membername + '</td>';
                        tableContent += '    <td>' + project.remark + '</td>';
                        tableContent += '    <td>' + project.money + '</td>';
                        tableContent += '    <td>' + project.day + '</td>';
                        tableContent += '    <td> <a onclick="details('+project.id +')">查看详情</a></td>';
                        tableContent += '    <td>';
                        tableContent += '	    <button type="button"  onclick="checkPass('+ project.id + ')" class="btn btn-success btn-xs">'
                            +'              <i class=" glyphicon glyphicon-check" ></i>'
                            +'        </button>';

                        tableContent += '		<button type="button" onclick="feedback('+project.id+')"class="btn btn-danger btn-xs" '
                            +'            data-toggle="modal" data-target="#my_modal">'
                            +'               <i class=" glyphicon glyphicon-remove"></i>'
                            +'      </button>';
                        tableContent += '	</td>';
                        tableContent += '</tr>';
                    });

                    // 动态生成分页导航
                    if (pageno > 1) {
                        pageContent += '<li><a href="#" onclick="asyncRequestData('
                            + (pageno - 1) + ')">上一页</a></li>';
                    }
                    // 循环生成1 2 3 4 5 等等
                    for (var i = 1; i <= projectPage.totalno; i++) {
                        if (i == pageno) {
                            pageContent += '<li class="active"><a href="#" >'
                                + i + '</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="asyncRequestData('
                                + i + ')">' + i + '</a></li>';
                        }
                    }
                    if (pageno < projectPage.totalno) {
                        pageContent += '<li ><a href="#" onclick="asyncRequestData('
                            + (pageno + 1) + ')">下一页</a></li>';
                    }
                    $("#proData").html(tableContent);
                    $(".pagination").html(pageContent);
                } else { // 返回内容为空
                    layer.msg("分页查询失败", {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {});
                } // else
            } // success
        }); // ajax
    } // asyncReq

    function details(projectid){
        $.ajax({
            type:"GET",
            url:"${ctx}/serviceaudi/project/detail",
            data:{"projectid":projectid},
            success:function (result) {
                if(result.code == 1){
                    var tmc = result.content;
                    var cn = result.ext.certname;
                    var body = '';
                    for(var i =0;i<tmc.length;i++){
                        body+='<div style="float:left;margin-left: 10px">'
                            +'      <label>'+ cn[i] +'</label>'
                            +'     <div class="pic">'
                            +'         <img src="http://localhost:8082/zc_api/upload/auth/'+tmc[i].path+'">'
                            +'     </div>'
                            +'</div>' ;
                    }
                    $(".modal-title").empty().text("资质文件");
                    $(".modal-body").empty().html(body);
                    var footer ='<button class="btn btn-danger" class="glyphicon glyphicon-refresh">'
                        +'关闭'
                        +'</button>';
                    $(".modal-footer").empty().html(footer);
                }else{
                    var body ='<div style="float:left;margin-left: 10px">获取数据错误</div>' ;
                    $(".modal-title").empty().text("项目详情");
                    $(".modal-body").empty().html(body);
                    var footer ='<button class="btn btn-danger" class="glyphicon glyphicon-refresh">'
                        +'关闭'
                        +'</button>';
                    $(".modal-footer").empty().html(footer);
                }
            }
        })
    }
    function feedback(projectid){
        $(".modal-title").empty().text("审核不通过反馈");
        var body ='<div class="col-sm-12" style="margin-left: 10px;text-align: center">'
            +'<textarea id="feedback" style="width: 100%;height: 100px"></textarea>'
            +'</div>' ;
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="insertBtn" onclick="checkFail('+projectid+')" class="btn btn-success">'
            +'<i class="glyphicon glyphicon-check"></i> 确定'
            +'</button>'
            +'<button type="reset" class="btn btn-danger" onclick="reset()" '
            +'lass="glyphicon glyphicon-refresh">'
            +'</i> 重置'
            +'</button>';
        $(".modal-footer").empty().html(footer);
    }
    function reset(){
        $("#feedback").val('')
    }
    function checkPass(projectid){
        $.ajax({
            type:"POST",
            url:"${ctx}/serviceaudi/project/checkpass",
            data:{"projectid": projectid},
            success:function (result) {
                if (result.code == 1){
                    layer.msg("审核完成",{icon:6,time:1500},function() {
                    });
                    asyncRequesyData(1);
                }else{
                    layer.msg(result.msg,{icon:5,time:1500,shift:6},function() {
                    });
                }
            }
        });
    }
    function checkFail(projectid){
        $.ajax({
            type:"POST",
            url:"${ctx}/serviceaudi/project/checkfail",
            data:{
                "projectid": projectid,
                "feedback":$("#feedback").val()
            },
            success:function (result) {
                if (result.code == 1){
                    layer.msg("反馈完成",{icon:6,time:1500},function() {
                    });
                    asyncRequesyData(1);
                    $("#my_modal").modal("hide");
                }else{
                    layer.msg(result.msg,{icon:5,time:1500,shift:6},function() {
                    });
                }
            }
        });
    }
</script>
</body>
</html>


