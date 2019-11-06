<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${ctx }/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx }/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx }/static/css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>
<%@ include file="/WEB-INF/commons/jsp/nav_bar.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/commons/jsp/menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" id="queryContent" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryBtn" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"> </i> 查询
                        </button>
                    </form>
                    <button type="button" id="addBtn" class="btn btn-primary"  onclick="addModal()"
                            style="float: right;" data-toggle="modal" data-target="#my_modal">
                        <i class="glyphicon glyphicon-plus"></i> 上传
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th>广告描述</th>
                                <th>状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="advData">
                            </tbody>

                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination"><!-- 内容通过ajax请求动态生成 --></ul>
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
<!-- 添加用户模态框（Modal） -->
<div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" style="text-align: center"></h3>
            </div>
            <div class="modal-body">
                在这里添加一些文本
            </div>
            <div class="modal-footer" style="text-align: center">

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx }/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx }/static/script/docs.min.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script type="text/javascript">
    var likeflg = false;
    $(function() {
        $(".list-group-item").click(function() {
            // jquery对象的回调方法中的this关键字为DOM对象
            // $(DOM) ==> JQuery
            if ($(this).find("ul")) { // 3 li
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
        // 页面加载完成后，查询第一页
        asyncRequestData(1);
        // 给查询按钮绑定事件处理
        $("#queryBtn").click(function() {
            // 获取查询输入框内容，不为空时使用模糊查询
            var queryContent = $("#queryContent").val();
            if (queryContent == "") {
                likeflg = false;
            } else {
                likeflg = true;
            }
            asyncRequestData(1);
        });

    });

    // 异步分页查询
    function asyncRequestData(pageno) {
        var loadingIndex = null;
        var jsonData = {
            "pageno" : pageno,
            "pagesize" : 10
        };
        // 拼接查询条件
        if (likeflg == true) {
            jsonData.queryContent = $("#queryContent").val();
        }
        $
            .ajax({
                type : "POST",
                url : "${ctx}/serviceman/adv/asyncRequestData",
                data : jsonData,
                beforeSend : function() {
                    loadingIndex = layer.msg("处理中", {
                        icon : 16
                    });
                },
                success : function(result) {
                    layer.close(loadingIndex);
                    // 返回内容不为空
                    if (result.success) {
                        var tableContent = "";
                        var pageContent = "";
                        var advPage = result.data; // 每一页
                        var advs = advPage.datas; // 用户
                        $.each(advs,function(index, adv) {
                            tableContent += '<tr>';
                            tableContent += '	<td>'
                                + (index + 1)
                                + '</td>';
                            tableContent += '	<td>'+adv.name+'</td>';
                            tableContent += '    <td>'
                                + adv.status
                                + '</td>';
                            tableContent += '    <td>';
                            tableContent += '	    <button type="button"  onclick="editModal('
                                + adv.id
                                + ')" class="btn btn-success btn-xs" data-toggle="modal" data-target="#my_modal">'
                                +'<i class=" glyphicon glyphicon-check"></i></button>';
                            tableContent += '	    <button type="button" onclick="editModal('
                                + adv.id
                                + ')" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#my_modal">'
                                +'<i class=" glyphicon glyphicon-pencil"></i></button>';
                            tableContent += '		<button type="button" onclick="deleteAdv('
                                + adv.id
                                + ',\''
                                + adv.name
                                + '\')"class="btn btn-danger btn-xs" data-toggle="modal" data-target="#my_modal">'
                                +'<i class=" glyphicon glyphicon-remove"></i></button>';
                            tableContent += '	</td>';
                            tableContent += '</tr>';
                        });

                        // 动态生成分页导航
                        if (pageno > 1) {
                            pageContent += '<li><a href="#" onclick="asyncRequestData('
                                + (pageno - 1) + ')">上一页</a></li>';
                        }
                        // 循环生成1 2 3 4 5 等等
                        for (var i = 1; i <= advPage.totalno; i++) {
                            if (i == pageno) {
                                pageContent += '<li class="active"><a href="#" >'
                                    + i + '</a></li>';
                            } else {
                                pageContent += '<li ><a href="#" onclick="asyncRequestData('
                                    + i + ')">' + i + '</a></li>';
                            }
                        }
                        if (pageno < advPage.totalno) {
                            pageContent += '<li ><a href="#" onclick="asyncRequestData('
                                + (pageno + 1) + ')">下一页</a></li>';
                        }
                        $("#advData").html(tableContent);
                        $(".pagination").html(pageContent);
                    } else { // 返回内容为空
                        layer.msg("分页查询失败", {
                            time : 2000,
                            icon : 5,
                            shift : 6
                        }, function() {
                        });
                    }
                }
            })
    }

    function addModal(){
        $(".modal-title").empty().text("广告上传");
        var body = '<form id="pForm" role="form" method="post" enctype="multipart/form-data">'
            +'<div class="form-group">'
                +'<label >广告名称</label>'
                +'<input type="text" class="form-control" id="name" placeholder="请输入广告名称" pidVal="">'
            +'</div>'
            +'<div class="form-group">'
                +'<label>简介描述</label>'
                +'<input type="text" class="form-control" id="description" placeholder="请输入简介描述">'
            +'</div>'
            +'<div class="form-group">'
                +'<label>选择广告</label>'
                +'<input type="file" name="ad" id="ad_file_input">'
            +'</div>'
            +'<div class="form-group">'
                +' <div class="row">'
                    +'<div class="col-md-12 imgdiv">'
                    +'</div>'
                +'</div>'
            +'</div>'
            +'</form>';
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="insertBtn" class="btn btn-success">'
            +'<i class="glyphicon glyphicon-plus"></i> 上传'
            +'</button>'
            +'<button type="reset" class="btn btn-danger">'
            +'<i class="glyphicon glyphicon-refresh"></i> 重置'
            +'</button>';
        $(".modal-footer").empty().html(footer);
    }
    function editModal(id){
        // 通过ajax获取
        $.ajax({
            type:'POST',
            url:"${ctx}/serviceman/adv/getDetail",
            data:{id:id},
            success:function(result){
                if(result.success){
                    var p = result.data;
                    $(".modal-title").empty().text("广告内容修改");
                    var body = '<form id="pForm" role="form">'
                        +'<div class="form-group">'
                        +'<label for="exampleInputPassword1">广告名称</label>'
                        +'<input type="text" class="form-control" value=" '+p.name+'" id="name" >'
                        +'</div>'
                        +'<div class="form-group">'
                        +'<label for="exampleInputPassword1">广告描述</label>'
                        +'<input type="text" class="form-control" value="'+p.description+'" id="description" >'
                        +'</div>'
                        +'</form>';
                    $(".modal-body").empty().html(body);
                    var footer = '<button type="button" id="modifyBtn" class="btn btn-success">' +
                        '<i class="glyphicon glyphicon-plus"></i> 修改' +
                        '</button>' +
                        '<button type="reset" class="btn btn-danger">' +
                        '<i class="glyphicon glyphicon-refresh">' +
                        '</i> 重置' +
                        '</button>';
                    $(".modal-footer").empty().html(footer);
                }
            }


        })

    }
    // 删除用户
    function deleteAdv(id, name) {
        layer.confirm("是否删除广告【" + name + "】信息?", {
            icon : 3,
            title : '提示'
        }, function(cindex) {
            // 删除信息
            $.ajax({
                type : "POST",
                url : "${ctx}/serviceman/adv/delete",
                data : {
                    id : id
                },
                success : function(result) {
                    if (result.success) {
                        layer.msg("删除成功", {
                            time : 2000,
                            icon : 6
                        }, function() {
                            // 回调函数做页面跳转,跳转到列表
                            pageQuery(1);
                        });
                    } else {
                        layer.msg("删除失败", {
                            time : 2000,
                            icon : 5,
                            shift : 6
                        }, function() {
                        });
                    }
                }
            });
        }, function(cindex) {
            layer.close(cindex);
        });
    }
</script>
</body>
</html>
