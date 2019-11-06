<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="   UTF-8">
<head>
    <meta charset="   UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/ztree/zTreeStyle.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
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
                    <form class="form-inline" cert="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" id="queryContent" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning" id="queryBtn"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="delBatch()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="addModal()" data-toggle="modal" data-target="#my_modal">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <form id="typeForm">
                            <table class="table  table-bordered">
                                <thead>
                                <tr >
                                    <th width="30">#</th>
                                    <th width="30"><input type="checkbox" id="select-all-box"></th>
                                    <th>分类名称</th>
                                    <th>简介描述</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody id="typeData">
                                </tbody>
                                <tfoot>
                                <tr >
                                    <td colspan="6" align="center">
                                        <ul class="pagination">
                                            <!--内容通过ajax异步获取-->
                                        </ul>
                                    </td>
                                </tr>

                                </tfoot>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加用户模态框（Modal） -->
<div class="modal fade" id="my_modal" tabindex="-1" cert="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script src="${ctx}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
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
        asyncRequesyData(1); // 页面初始化请求第一页
    });// init
    var likeflg = false;
    $("#queryBtn").click(function() {
        // 获取查询输入框内容，不为空时使用模糊查询
        var queryContent = $("#queryContent").val();
        if (queryContent.trim() == "") {
            likeflg = false;
        } else {
            likeflg = true;
        }
        asyncRequesyData(1);
    });
    // 全选框绑定点击事件
    $("#select-all-box").click(function() {
        // 记录选择框状态
        var flg = this.checked;
        $("#certData :checkbox").each(function() {
            this.checked = flg;
        })
    });

    function  asyncRequesyData(pageno) {
        var loadingIndex = null;
        var jsonData = {
            "pageno" : pageno,
            "pagesize" : 10
        };
        // 拼接查询条件
        if (likeflg == true) {
            jsonData.queryContent = $("#queryContent").val();
        }
        $.ajax({
            type : "POST",
            url : "${ctx}/serviceman/project/asyncRequestData",
            data : jsonData,
            beforeSend : function() {
                loadingIndex = layer.msg("处理中", {icon : 16});
            },
            success : function(result) {
                layer.close(loadingIndex);
                // 返回内容不为空
                if (result.success) {
                    var tableContent = "";
                    var pageContent = "";
                    var projectPage = result.data; // 每一页
                    var types = projectPage.datas; // 用户
                    $.each(types,function(index, type) {
                        tableContent += '<tr id=" '+ type.id + '">';
                        tableContent += '	<td>' + (index + 1) + '</td>';
                        tableContent += '	<td><input type="checkbox" name="typeid" value="'+type.id+'"></td>';
                        tableContent += '    <td>' + type.name + '</td>';
                        tableContent += '    <td> 后续扩展 </td>';
                        tableContent += '    <td>';
                        tableContent += '	    <button type="button" onclick="editModal('
                            + type.id
                            + ',\''
                            + type.name
                            + '\',\'描述后续修改'
                            +'\')" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#my_modal">'
                            +'<i class=" glyphicon glyphicon-pencil"></i>'
                            + '</button>';

                        tableContent += '		<button type="button" onclick="del('
                            + type.id
                            + ',\''
                            + type.name
                            + '\')"class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
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
                    $("#typeData").html(tableContent);
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

    function del(typeid,name){
        layer.confirm("是否删除分类【" + name + "】信息?", {
            icon : 3,
            title : '提示'
        }, function(cindex) {
            // 删除信息
            $.ajax({
                type : "POST",
                url : "${ctx}/serviceman/project/delete",
                data : {
                    id : typeid
                },
                success : function(result) {
                    if (result.success) {
                        layer.msg("删除成功",{time:2000,icon:6},function(){
                            // 回调函数做页面跳转,跳转到列表
                            asyncRequesyData(1);
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
    function delBatch(){
        // 选中的checkbox
        var boxes = $("#certData :checkbox:checked");
        if (boxes.length == 0) {
            layer.msg("请选择删除的分类", {
                time : 2000,
                icon : 5,
                shift : 6
            }, function() {});
        } else {
            layer.confirm("是否删除选中分类信息?", {
                icon : 3,
                title : '提示'
            }, function(cindex) {
                // 删除信息
                $.ajax({
                    type : "POST",
                    url : "${ctx}/serviceman/project/deleteBatch",
                    data : $("#typeForm").serialize(), // 元素属性必须使用name
                    success : function(result) {
                        if (result.success) {
                            layer.msg("删除成功",{time:2000,icon:6},function(){
                                // 回调函数做页面跳转,跳转到列表
                                asyncRequesyData(1);
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
                layer.close(cindex);
            }, function(cindex) {
                layer.close(cindex);
            }); // layer
        }
    }
    function addModal(){
        $(".modal-title").empty().text("资质添加");
        var body = '<div class="form-group">'
            +'<label for="exampleInputPassword1">资质名字</label>'
            +'<input type="text" class="form-control" id="name" name="name" placeholder="分类名字">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +' </div>'
            +'<div class="form-group">'
            +'<label for="exampleInputPassword1">简介描述</label>'
            +'<input type="text" class="form-control" id="description" name="description" placeholder="简介描述">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +'</div>';
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="insertBtn" class="btn btn-success">'
            +'<i class="glyphicon glyphicon-plus"></i> 新增'
            +'</button>'
            +'<button type="reset" class="btn btn-danger">'
            +'<i class="glyphicon glyphicon-refresh">'
            +'</i> 重置'
            +'</button>';
        $(".modal-footer").empty().html(footer);
    }
    function editModal(typeid,name,description){
        $(".modal-title").empty().text("分类信息修改");
        var body = '<div class="form-group">'+
            '<label for="exampleInputPassword1">分类名称</label>' +
            '<input type="text" class="form-control" id="name" name="name" value="' +name + '">' +
            '<i style="color:red;" class="errorinfo"></i>' +
            '</div>' +
            '<div class="form-group">' +
            '<label for="exampleInputPassword1">简介描述</label>' +
            '<input type="text" class="form-control" id="description" name="description" value="' +description + '">' +
            '<i style="color:red;" class="errorinfo"></i>' +
            '</div>';
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
</script>
</body>
</html>


