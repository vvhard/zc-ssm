<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
    <div class="tree">
        <ul style="padding-left: 0px;" class="list-group">
            <!-- 控制面板 -->
            <li class="list-group-item tree-closed"><a href="${ctx }/main.html">
                <i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
            </li>
            <c:forEach items="${user_menus}" var="permission">
                <li class="list-group-item tree-closed">
                    <span><i class="${permission.icon} "></i>${permission.name}<span class="badge" style="float: right">3</span></span>
                    <ul style="margin-top: 10px; display: none;">
                        <c:forEach items="${permission.childs }" var="child">
                            <li style="height: 30px;"><a href="${ctx }${child.url }">
                                <i class="${child.icon }"></i> ${child.name }</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
            <!-- 控制面板 -->
            <li class="list-group-item tree-closed"><a href="${ctx }/main.html">
                <i class="glyphicon glyphicon-list-alt"></i>参数管理</a>
            </li>
        </ul>
    </div>
</div>

