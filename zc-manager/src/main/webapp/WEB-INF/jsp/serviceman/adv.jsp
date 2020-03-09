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
                    <button type="button" id="addBtn" class="btn btn-primary"  onclick="addModal()"
                            style="float: left;" data-toggle="modal" data-target="#my_modal">
                        <i class="glyphicon glyphicon-plus"></i> 上传广告
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th>广告名称</th>
                                <th>链接地址</th>
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
        // 列表展开
        $("a[href='${ctx}/serviceman/adv/index']").css("color", "red");
        //加上tree close样式
        $("a[href='${ctx}/serviceman/adv/index']").parents(".list-group-item")
            .removeClass("tree-closed");
        $("a[href='${ctx}/serviceman/adv/index']").parent().parent("ul").show(100);
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
        $.ajax({
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
                if (result.code == 1) {
                    var tableContent = "";
                    var pageContent = "";
                    var advPage = result.content; // 每一页
                    var advs = advPage.datas; // 用户
                    $.each(advs,function(index, adv) {
                        tableContent += '<tr>';
                        tableContent += '	<td>'
                                        +       (index + 1)
                                        +'  </td>';
                        tableContent += '	<td>'+adv.name+'</td>';
                        tableContent += '   <td>'
                                        +       adv.url
                                        +'  </td>';
                        tableContent += '   <td>'
                                        +       adv.description
                                        +'  </td>';
                        tableContent += '   <td>'
                                        +       (adv.status =='ON'?'已上线展示':'未展示')
                                        +'  </td>';
                        tableContent += '   <td>';
                        tableContent += '       <button type="button" onclick="changeAdvStatus('+adv.id + ',\''+adv.status+'\')" '
                                        +'          class="btn btn-primary btn-xs">'
                                        +'          <i class="glyphicon glyphicon-eye-open "></i></button>';
                        tableContent += '		<button type="button" onclick="deleteAdv(' +adv.id +',\''+ adv.name +'\')"'
                                        +'         class="btn btn-danger btn-xs" >'
                                        +'          <i class="glyphicon glyphicon-remove"></i>'
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
        }); // ajax
    }

    function addModal(){
        $(".modal-title").empty().text("广告上传");
        var body = '<form id="addForm" role="form" enctype="multipart/form-data">'
            +'<div class="form-group">'
                +'<label >广告名称</label>'
                +'<input type="text" class="form-control" id="addname" name="name" placeholder="请输入广告名称" pidVal="">'
            +'</div>'
            +'<div class="form-group">'
                +'<label>广告链接</label>'
                +'<input type="text" class="form-control" name="url" id="advurl" placeholder="请输入图片链接地址">'
            +'</div>'
            +'<div class="form-group">'
                +'<label>简介描述</label>'
                +'<input type="text" class="form-control" name="description" id="adddescription" placeholder="请输入简介描述">'
            +'</div>'
            +'<div class="form-group">'
                +'  <label>选择广告</label>'
                +'  <input type="file" name="file" id="upload_input" onchange="readAsDataURL(\'upload_input\',\'uploadShowDiv\')">'
                +'  <div class="pic" id="uploadShowDiv">'
                +'      <img id="img" src=""/>'
                +'  </div>'
            +'</div>'
            +'</form>';
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="insertBtn" class="btn btn-success" onclick="add()">'
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
    // 删除
    function add(){
        var name = $("#addname").val();
        var description = $("#adddescription").val();
        var advurl = $("#advurl").val();
        if(isNullStr(name)){
            layer.msg("请填写广告名称",{icon:5,time:1500,shift:6},function(){
            })
            return;
        }
        if(isNullStr(advurl)){
            layer.msg("请填写链接地址",{icon:5,time:1500,shift:6},function(){
            })
            return;
        }
        $.ajax({
            type:"POST",
            url:"${ctx}/serviceman/adv/addAdv",
            dataType: "json",
            cache: false, // 上传文件不需要缓存。
            data:new FormData($("#addForm")[0]),
            processData: false,// 不处理数据,因为data值是FormData对象，不需要对数据做处理。
            contentType: false, // 不设置内容类型,因为是由<form>表单构造的FormData对象，且已经声明了属性
            success:function(result){
                if(result.code == 1){
                    layer.msg("添加成功",{icon:6,time:1500},function(){
                        $("#my_modal").modal("hide");
                        asyncRequestData(1);
                    })
                }else{
                    layer.msg("添加失败",{icon:5,time:1500,shift:6},function(){

                    })
                }
            }
        })
    }
    function changeAdvStatus(id,status){
        var url;
        if(status == 'OFF'){
            url =  "${ctx}/serviceman/adv/takeon?id=" + id;
        }else{
            url =  "${ctx}/serviceman/adv/takeoff?id=" + id;
        }
        $.ajax({
            type:"POST",
            url:url,
            data:{},
            success:function(result){
                if(result.code == 1){
                    layer.msg("设置成功",{icon:6,time:1500},function(){
                        asyncRequestData(1);
                        $("#my_modal").modal("hide");
                    })
                }else{
                    layer.msg(result.msg,{icon:5,time:1500,shift:6},function(){

                    })
                }
            }
        })

    }
    function deleteAdv(id, name) {
        layer.confirm("是否下线并删除广告【" + name + "】信息?", {
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
                    if (result.code == 1) {
                        layer.msg("删除成功", {
                            time : 2000,
                            icon : 6
                        }, function() {
                            // 回调函数做页面跳转,跳转到列表
                            asyncRequestData(1);
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

    function readAsDataURL(src,dest) {
        if(typeof FileReader=='undifined')			//判断浏览器是否支持filereader
        {
            alert("否支持filereader")
            return false;
        }
        var file=document.getElementById(src).files[0];
        var reader=new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function(e)
        {
            $("#" + dest).empty().html('<img src="'+this.result+'"/>')
        }
    }
    function isNullStr(str){
        if(str == null || str.replace(/(^s*)|(s*$)/g, "").length == 0 ){
            return true;
        }
        return false;
    }
</script>
</body>
</html>
