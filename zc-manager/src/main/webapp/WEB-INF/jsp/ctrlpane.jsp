<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header">控制面板</h1>
    <div class="row placeholders">
        <c:set var="i" value="0"></c:set>
        <c:forEach items="${user_menus}" var="permission">
            <c:forEach items="${permission.childs}" var="child">
                <c:if test="${i < 4}">
                    <c:set var="i" value="${i+1}"></c:set>

                        <div class="col-xs-6 col-sm-3 placeholder">
                            <c:if test="${i % 2 == 0}">
                                <img data-src="holder.js/300x300/auto/vine" class="${child.icon }" >
                            </c:if>
                            <c:if test="${i % 2 != 0}">
                                <img data-src="holder.js/300x300/auto/sky" class="${child.icon }" >
                            </c:if>
                            <h4><a href="${ctx }${child.url }">${child.name }</a></h4>
                        </div>
                </c:if>
            </c:forEach>
            <c:set var="i" value="${i}"></c:set>
        </c:forEach>
    </div>
</div>
