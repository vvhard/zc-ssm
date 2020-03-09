<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/css/doc.min.css">
    <link rel="stylesheet" href="${ctx}/static/ztree/zTreeStyle.css">

    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
<%@include file="/WEB-INF/commons/jsp/nav_bar.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/commons/jsp/menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <ul id="tagTree" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
      -->
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
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<script src="${ctx}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    var likeflg = false;
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
        $("a[href='${ctx}/serviceman/tag/index']").css("color", "red");
        //加上tree close样式
        $("a[href='${ctx}/serviceman/tag/index']").parents(".list-group-item")
            .removeClass("tree-closed");
        $("a[href='${ctx}/serviceman/tag/index']").parent().parent("ul").show(100);
        initTagTree();
    });

    function initTagTree() {
        var setting = {
            data : {
                simpleData : {
                    enable : true,
                    idKey : "id",
                    pIdKey : "pid",
                },
                key : {
                    url : "haha" // 不使用url属性
                }
            },
            view : {
                //自定义显示的效果
                addDiyDom : function(treeId, treeNode) { // 用于在节点上固定显示用户自定义控件
                    var icoObj = $("#" + treeNode.tId + "_ico"); // 获取到图标，treeNode.tId自动生成
                    //treeNode里面有一个tId；
                    //这个tid用来拼串以后就是图标显示位置的元素id和名字显示位置的元素id
                    //tId:"permissionTree_3"
                    //<span id="permissionTree_2_span">用户维护</span>
                    //<span id="permissionTree_2_ico" ></span>
                    //console.log(treeNode);
                    //改图标；找到当前元素图标显示的节点，将这个节点的class设置为当前节点的icon
                    if (treeNode.icon) {
                        icoObj.removeClass("button ico_docu ico_open");
                        icoObj.parent().remove("target");
                        icoObj.before("<span class='" + treeNode.icon  +"'></span>");
                    }

                },
                // 鼠标移到节点显示自定义的图标
                addHoverDom: function(treeId, treeNode){
                    var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                    aObj.attr("href", "javascript:;");
                    aObj.attr("target", "");
                    if (treeNode.editNameFlag
                        || $("#btnGroup" + treeNode.tId).length > 0)
                        return;
                    var s = '<span id="btnGroup'+treeNode.tId+'">';
                    if (treeNode.level == 0) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"'
                            +'onclick="addModal('+ treeNode.id +')"data-toggle="modal" data-target="#my_modal">&nbsp;&nbsp;'
                            +'<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 1) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"'
                            +'onclick="editModal('+ treeNode.id +')"data-toggle="modal" data-target="#my_modal">&nbsp;&nbsp;'
                            +'<i class="fa fa-fw fa-edit rbg "></i></a>';

                        if (treeNode.children == null) {
                            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"'
                                +'onclick="deleteNode('+ treeNode.id +')"data-toggle="modal" data-target="#my_modal">&nbsp;&nbsp;'
                                +'<i class="fa fa-fw fa-times rbg "></i></a>';
                        }
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" '
                            +'onclick="addModal('+ treeNode.id +')"data-toggle="modal" data-target="#my_modal">&nbsp;&nbsp;'
                            +'<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 2) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"'
                            +'onclick="editModal('+ treeNode.id +')" data-toggle="modal" data-target="#my_modal">&nbsp;&nbsp;'
                            +'<i class="fa fa-fw fa-edit rbg "></i></a>';
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"'
                            +'onclick="deleteNode('+ treeNode.id +')"data-toggle="modal" data-target="#my_modal">&nbsp;&nbsp;'
                            +'<i class="fa fa-fw fa-times rbg "></i></a>';
                    }
                    s += '</span>';
                    aObj.after(s);
                },
                // 鼠标移走节点隐藏自定义的图标
                removeHoverDom: function(treeId, treeNode){
                    $("#btnGroup"+treeNode.tId).remove();
                }
            }
        };
        //从数据库查出的所有权限节点数据
        //发送ajax请求获取到所有权限的json数据
        $.getJSON("${ctx}/serviceman/tag/loadData", function(nodes) {
            //给每一个节点修改或者添加一些属性
            $.each(nodes, function() {
                if (this.pid == 1 || this.pid == 0) {
                    this.open = true;
                }
            });
            //如果不是用var声明的变量，这个变量就默认变为全局的
            //把初始化好的ztree对象传递给外界使用，可以通用操作这个对象，来改变树
            //ztree为了不影响下面的操作是异步展示数据的
            $.fn.zTree.init($("#tagTree"), setting,nodes);
        })
    }
    function addModal(pid){
        $(".modal-title").empty().text("分类添加");
        var body = '<form id="pForm" role="form">'
            +'<div class="form-group">'
            +'<label >分类名称</label>'
            +'<input type="text" class="form-control" id="name" placeholder="请输入分类名称" pidVal="">'
            +'</div>'
            +'<div class="form-group">'
            +'<label>简介描述</label>'
            +'<input type="text" class="form-control" id="description" placeholder="请输入简介描述">'
            +'</div>'
            +'</form>';
        $(".modal-body").empty().html(body);
        var footer = '<button type="button" id="insertBtn" class="btn btn-success">'
            +'<i class="glyphicon glyphicon-plus"></i> 新增'
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
            url:"${ctx}/serviceman/tag/getDetail",
            data:{id:id},
            success:function(result){
                if(result.success){
                    var p = result.data;
                    $(".modal-title").empty().text("标签修改");
                    var body = '<form id="pForm" role="form">'
                        +'<div class="form-group">'
                        +'<label for="exampleInputPassword1">标签名称</label>'
                        +'<input type="text" class="form-control" value=" '+p.name+'" id="permissionname" placeholder="请输入标签名称">'
                        +'</div>'
                        +'<div class="form-group">'
                        +'<label for="exampleInputPassword1">标签描述</label>'
                        +'<input type="text" class="form-control" value="'+p.description+'" id="url" placeholder="请输入标签描述">'
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
    function deleteNode(id){
        layer.confirm("是否删除",{icon:3,title:'提示'},
            function(cindex){
                $.ajax({
                    type:"POST",
                    url:"${ctx}/serviceman/tag/delete",
                    data:{id:id},
                    success:function(result){
                        if(result.success){
                            layer.msg("删除成功",{time:2000,icon:6},function(){
                                var treeObj = $.fn.zTree.getZTreeObj("tagTree");
                                treeObj.reAsyncChildNodes(null, "refresh");// 属性许可树
                            });
                        }else{
                            layer.msg("删除失败",{time:2000,icon:5,shift:6},function(){});
                        }
                    }
                });
            },
            function(cindex){layer.close(cindex)})
    }
</script>
</body>
</html>
