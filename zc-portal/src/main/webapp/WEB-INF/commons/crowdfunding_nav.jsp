<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default" role="navigation">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a rel="nofollow" href="${ctx}/index.jsp"><i class="glyphicon glyphicon-home"></i> 众筹首页</a>
                        </li>
                        <li class="active">
                            <a rel="nofollow" href="javascript:;" class="active"><i class="glyphicon glyphicon-th-large"></i> 项目总览</a>
                        </li>
                        <li>
                            <a rel="nofollow" onclick="start()"><i class="glyphicon glyphicon-edit"></i> 发起众筹</a>
                        </li>
                        <li>
                            <a rel="nofollow" href="${ctx}/member/personal_center?id=${portal_login_user.id}"><i class="glyphicon glyphicon-user"></i> 我的众筹</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>
