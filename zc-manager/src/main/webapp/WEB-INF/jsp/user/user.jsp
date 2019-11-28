<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
                    <form class="form-inline" role="form" style="float:left;">
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
                        <form id="userForm">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="select-all-box"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="userData">
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
<div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" style="text-align: center">添加用户</h3>
            </div>
            <div class="modal-body">
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
<%--<script src="/WEB-INF/js/user/user.js"></script>--%>
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
    // user.jsp 用到的js
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
        $("#userData :checkbox").each(function() {
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
            url : "${ctx}/user/asyncRequestData",
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
                    var userPage = result.data; // 每一页
                    var users = userPage.datas; // 用户
                    $.each(users,function(index, user) {
                        tableContent += '<tr id=" '+ user.id + '">';
                        tableContent += '	<td>' + (index + 1) + '</td>';
                        tableContent += '	<td><input type="checkbox" name="userid" value="'+user.id+'"></td>';
                        tableContent += '    <td>' + user.loginacct + '</td>';
                        tableContent += '    <td>' + user.username + '</td>';
                        tableContent += '    <td>' + user.email + '</td>';
                        tableContent += '    <td>';
                        tableContent += '	    <button type="button"  onclick="assignModal('
                            + user.id
                            + ')" class="btn btn-success btn-xs" data-toggle="modal" data-target="#my_modal">'
                            +'<i class=" glyphicon glyphicon-check" ></i>'
                            +'</button>';

                        tableContent += '	    <button type="button" onclick="editModal('
                            + '\''+ user.loginacct +'\',\'' + user.username + '\',\''+ user.email
                            +'\')" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#my_modal">'
                            +'<i class=" glyphicon glyphicon-pencil"></i>'
                            + '</button>';

                        tableContent += '		<button type="button" onclick="del('
                            + user.id
                            + ',\''
                            + user.loginacct
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
                    for (var i = 1; i <= userPage.totalno; i++) {
                        if (i == pageno) {
                            pageContent += '<li class="active"><a href="#" >'
                                + i + '</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="asyncRequestData('
                                + i + ')">' + i + '</a></li>';
                        }
                    }
                    if (pageno < userPage.totalno) {
                        pageContent += '<li ><a href="#" onclick="asyncRequestData('
                            + (pageno + 1) + ')">下一页</a></li>';
                    }
                    $("#userData").html(tableContent);
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


    function add(){
        $.ajax({
           type:"POST",
            url:"${ctx}/user/add",
            data:$("#addForm").serialize(),
            success:function(result){
                if(result.success){
                    layer.msg("新增成功",{time:1500,icon:6},function(){
                        // 回调函数做页面跳转,跳转到列表
                        asyncRequesyData(1);
                        $("#my_modal").modal("hide");
                    });
                }else{
                    layer.msg("新增失败", {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {});
                }
            } // success

        }); // ajax
    }
    function update(){
        $.ajax({
            type:"POST",
            url:"${ctx}/user/update",
            data:$("#editForm").serialize(),
            success:function (result) {
                if(result.success){
                    layer.msg("修改成功",{time:1500,icon:6},function(){
                        asyncRequesyData(1);
                        $("#my_modal").modal("hide");
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
    function del(userid,loginacct){
        layer.confirm("是否删除用户【" + loginacct + "】信息?", {
            icon : 3,
            title : '提示'
        }, function(cindex) {
            // 删除信息
            $.ajax({
                type : "POST",
                url : "${ctx}/user/delete",
                data : {
                    id : userid
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
        var boxes = $("#userData :checkbox:checked");
        if (boxes.length == 0) {
            layer.msg("请选择删除的用户", {
                time : 2000,
                icon : 5,
                shift : 6
            }, function() {});
        } else {
            layer.confirm("是否删除选中用户信息?", {
                icon : 3,
                title : '提示'
            }, function(cindex) {
                // 删除信息
                $.ajax({
                    type : "POST",
                    url : "${ctx}/user/deleteBatch",
                    data : $("#userForm").serialize(), // 元素属性必须使用name
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
        $(".modal-title").empty().text("用户添加");
        var body ='<form id="addForm">'
            + '<div class="form-group">'
            +'<label for="exampleInputPassword1">登陆账号</label>'
            +'<input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登陆账号">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +' </div>'
            +'<div class="form-group">'
            +'<label for="exampleInputPassword1">用户名称</label>'
            +'<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名称">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +'</div>'
            +'<div class="form-group">'
            +'<label for="exampleInputEmail1">邮箱地址</label>'
            +'<input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱地址">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +'</div>'
            +'</form>';
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="insertBtn" onclick="add()" class="btn btn-success">'
            +'<i class="glyphicon glyphicon-plus"></i> 新增'
            +'</button>'
            +'<button type="reset" class="btn btn-danger" onclick="reset()" '
            +'lass="glyphicon glyphicon-refresh">'
            +'</i> 重置'
            +'</button>';
        $(".modal-footer").empty().html(footer);
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
    function assignModal(userid){
        var assign;
        var unassign;
        var ass_str;
        var un_ass_str;
        $.ajax({
            type:"POST",
            url:"${ctx}/user/initAssign",
            data:{id:userid},
            success:function(result){
                if(result.success){
                    assign = result.data[0];
                    unassign = result.data[1];
                    // 放在外面，由于是异步，所以可能会加载不了
                    $.each(assign,function(index,role){
                        ass_str += '<option value="' + role.id +  '">' + role.name + '</option>';
                    });
                    $.each(unassign,function(index,role){

                        un_ass_str += '<option value="' + role.id +  '">' + role.name + '</option>';
                    });
                    $(".modal-title").empty().text("用户角色分配");
                    var body = '<form id="roleForm" role="form" class="form-inline">'
                        +'<input type="hidden" name="userid" value="'+ userid + '">'
                        +'<div class="form-group">   '
                        +'<label for="exampleInputPassword1">已分配角色列表</label><br>'
                        +'<select id="leftList" name="assignroleids" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">'
                        + ass_str
                        +'</select>'
                        +'</div>'
                        +'<div class="form-group">'
                        +'<ul>   '
                        +'<li id="left2rightBtn" onclick="unAssign()" class="btn btn-default glyphicon glyphicon-chevron-right"></li>'
                        +'<br>   '
                        +'<li id="right2leftBtn" onclick="assign()"class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>'
                        +' </ul>   '
                        +'</div>   '
                        +'<div class="form-group" style="margin-left:40px;">'
                        +'<label >未分配角色列表</label><br>'
                        +'<select id="rightList" name="unassignroleids" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">'
                        + un_ass_str
                        +'</select>'
                        +'</div>'
                        +'</form>';
                    $(".modal-body").empty().html(body);
                }else {
                    layer.msg("请重试", {
                        time : 2000,
                        icon : 5,
                        shift : 6
                    }, function() {return;});
                } // else
            }//success
        });// ajax

    }
    function editModal(acct,username,email){
        $(".modal-title").empty().text("用户修改");
        var body = '<form id="editForm">'
            +'<div class="form-group">'
            +'<label for="exampleInputPassword1">登陆账号</label>'
            +'<input type="text" class="form-control" readonly id="loginacct" name="loginacct" value="' +acct + '">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +'<div class="form-group">'
            +'<label for="exampleInputPassword1">用户名称</label>'
            +'<input type="text" class="form-control" id="username" name="username" value="' +username + '">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +'</div>'
            +'<div class="form-group">'
            +'<label for="exampleInputEmail1">邮箱地址</label>'
            +'<input type="email" class="form-control" id="email" name="email" value="' + email+'">'
            +'<i style="color:red;" class="errorinfo"></i>'
            +'</div>'
            +'</form>';
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="modifyBtn" onclick="update()" class="btn btn-success">' +
            '<i class="glyphicon glyphicon-plus"></i> 修改' +
            '</button>' +
            '<button type="reset" class="btn btn-danger" onclick="reset()" ' +
            'lass="glyphicon glyphicon-refresh">' +
            '</i> 重置' +
            '</button>';
        $(".modal-footer").empty().html(footer);
    }
    function  assign() {
        // 获取被选中的角色
        var opts = $("#rightList :selected");
        if(opts.length == 0){
            layer.msg("请选择角色",{time:2000,icon:5,shift:6},function(){});
        }else{
            $.ajax({
                type:"POST" ,
                url:"${ctx}/user/doAssign",
                data:$("#roleForm").serialize(),
                success:function(result){
                    if(result.success){
                        $("#leftList").append(opts);
                        layer.msg("分配成功",{time:1000,icon:6},function(){});

                    }else{
                        layer.msg("分配失败",{time:1000,icon:5,shift:6},function(){});
                    }
                }
            });
        }
    }
    function unAssign(){
        var opts = $("#leftList :selected");
        if(opts.length == 0){
            layer.msg("请选择需要取消分配的角色",{time:1000,icon:5,shift:6},function(){});
        }else{
            $.ajax({
                type:"POST" ,
                url:"${ctx}/user/unAssign",
                data:$("#roleForm").serialize(),
                success:function(result){
                    if(result.success){
                        $("#rightList").append(opts);
                        layer.msg("取消分配成功",{time:1000,icon:6},function(){});
                    }else{
                        layer.msg("取消分配失败",{time:1000,icon:5,shift:6},function(){});
                    }
                }
            });
        }
    }

</script>
</body>
</html>

