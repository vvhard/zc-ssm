<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%--这句话会导致c:if标签出错,排查错误原因为uri错误，一下为正确--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-wrapper">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${ctx}/index.jsp" style="font-size:32px;">创意产品众筹平台</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse" style="float:right;">
                    <c:if test="${!empty portal_login_user}">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="glyphicon glyphicon-user"></i>
                                    欢迎您，${portal_login_user.username}
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="${ctx}/member/personal_center?id=${portal_login_user.id}">
                                        <i class="glyphicon glyphicon-scale"></i> 个人中心</a>
                                    </li>
                                    <li><a href="#">
                                        <i class="glyphicon glyphicon-comment"></i> 消息</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li><a href="${ctx}/logout">
                                        <i class="glyphicon glyphicon-off"></i> 退出系统</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${empty portal_login_user}">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${ctx}/login.jsp">登录</a></li>
                            <li><a href="${ctx}/reg.jsp">注册</a></li>
                        </ul>
                    </c:if>
                </div>
            </div>
        </nav>
    </div>
</div>
