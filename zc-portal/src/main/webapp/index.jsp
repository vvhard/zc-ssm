<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="utf-8">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/static/css/carousel.css">
	<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
	<style>
		h3 {
			font-weight:bold;
		}
		#footer {
			padding: 15px 0;
			background: #fff;
			border-top: 1px solid #ddd;
			text-align: center;
		}
		/* 侧栏导航 */
		.sideBox{padding:10px;height:220px;background:#fff;margin-bottom:10px;overflow:hidden;}
		.sideBox .hd{height:30px; line-height:30px; background:#f60; padding:0 10px;text-align:center;overflow:hidden;}
		.sideBox .hd .more{color:#fff;}
		.sideBox .hd h3 span{font-weight:bold; font-size:14px;color:#fff;}
		.sideBox .bd{padding:5px 0 0;}

		#sideMenu .bd li{margin-bottom:2px; height:30px; line-height:30px;text-align:center; overflow:hidden;}
		#sideMenu .bd li a{display:block;background:#EAE6DD;}
		#sideMenu .bd li a:hover{background:#D5CFBF;}

		/* 列表页 */
		#mainBox{margin-bottom:10px;padding:10px;background:#fff;overflow:hidden;}
		#mainBox .mHd{border-bottom:2px solid #09c;height:30px;line-height:30px;}
		#mainBox .mHd h3{display:initial;*display:inline;zoom:1;padding:0 15px;background:#09c;color:#fff;}
		#mainBox .mHd h3 span{color:#fff;font-size:14px;font-weight:bold;}
		#mainBox .path{float:right;}

		/* 位置导航 */
		.path{ height:30px; line-height:30px; padding-left:10px;}
		.path a,.path span{ margin:0 5px;}

		/* 文章列表 */
		.newsList{padding:10px;text-align:left;}
		.newsList li{background:url("../images/share/point.png") no-repeat 2px 14px; padding-left:10px;height:30px; line-height:30px;}
		.newsList li a{display:inline-block;*display:inline;zoom:1;font-size:14px;}
		.newsList li .date{float:right; color:#999;}
		.newsList li.split{margin-bottom:10px;padding-bottom:10px;border-bottom:1px dotted #ddd;height:0px;line-height:0px;overflow:hidden;}

		/* 通用带图片的信息列表_普通式 */
		.picList{padding:10px;text-align:left;}
		.picList li{margin:0 5px;height:190px;}

		h3.break {
			font-size:16px;
			display: block;
			white-space: nowrap;
			word-wrap: normal;
			overflow: hidden;
			text-overflow: ellipsis;
		}
		h3.break>a {
			text-decoration:none;
		}
	</style>
</head>
<body>
<%@include file="/WEB-INF/commons/nav_bar.jsp" %>
<!-- 广告轮播
================================================== -->
<%@include file="/WEB-INF/commons/index_carousel.jsp"%>
<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing" >
	<!-- Three 推荐项目 -->
	<%@include file="/WEB-INF/commons/recommand.jsp"%>
	<%--分类1--%>
	<%@include file="/WEB-INF/commons/tec_project.jsp"%>
	<%--分类2--%>
	<%@include file="/WEB-INF/commons/internet_project.jsp"%>
	<%--分类3--%>
	<%@include file="/WEB-INF/commons/agr_project.jsp"%>
	<%--分类4--%>
	<%@include file="/WEB-INF/commons/other_project.jsp"%>
</div><!-- /.container -->
<!-- FOOTER -->
<div class="container"  >
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div id="footer">
				<div class="footerNav">
					<a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
				</div>
				<div class="copyRight">
					Copyright ?2017-2017atguigu.com 版权所有
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/script/docs.min.js"></script>
<script src="${ctx}/static/script/back-to-top.js"></script>
<script>
	$(function(){
		$.sess
		// genRecDivContent();
		// genDivContent("c1_div","test");
		// genDivContent("c2_div","test");
		// genDivContent("c3_div","test");
		// genDivContent("oc_div","test");
		<%--$.ajax({--%>
			<%--type:"GET",--%>
			<%--url:"${ctx}/advInfo",--%>
			<%--data:{},--%>
			<%--success:function(result){--%>
				<%--if(result.code == 1){--%>
					<%--var advs = result.content;--%>
					<%--var a = '';--%>
					<%--$.each(advs,function(index,adv){--%>
						<%--a+='<div class="item" onclick="" style="cursor:pointer;">'--%>
							<%--+'	<img src="'+adv.iconpath+'" alt="Third slide"/>'--%>
							<%--+'</div>'--%>
					<%--})--%>
					<%--$("#advDiv").prepend(a);--%>
				<%--}else{--%>

				<%--}--%>
			<%--}--%>
		<%--})--%>
	});
	$(".thumbnail img").css("cursor", "pointer");
	function genRecDivContent() {
		$.ajax({
			type:"GET",
			url:"${ctx}/rec",
			data:{
				"types":$("#category").text()
			},
			success:function(result){
				// $(".container.marketing").html(result)
				if(result.code == 1){
					console.log(result.content)
					var rec = result.content
					var h ='';
					$.each(rec,function(index,value){
						console.log(value.name)
						var project = value;
						h += '<div class="col-lg-4">'
								+'   <img class="img-circle" src="${ctx}/static/img/p'+(index+1)+'.jpg"'
								+'      alt="Generic placeholder image" style="width: 140px; height: 140px;">'
								+'   <h2>' + project.name + '</h2>'
								+'   <p>' + project.remark + '</p>'
								+'   <p><a class="btn btn-default" onclick="details('+ project.id +')" role="button">'
								+'       项目详情 &raquo;</a></p>'
								+'</div>';
					})
					$("#rec_div").html(h)
				}
			}
		}); // ajax
	}
	function genDivContent(div_id,type){
		$.ajax({
			type:"GET",
			url:"${ctx}/category",
			data:{
				"type":type
			},
			success:function(result){
				if(result.code == 1){
					console.log(result.content)
					var rec = result.content
					var h ='';
					$.each(rec,function(index,value){
						console.log(value.name)
						var project = value;
						h+= '<div class="col-md-3">'
								+'  <div class="thumbnail">'
								+'      <img alt="300x200" src="${ctx}/static/img/product-'+(index+1)+'.jpg"/>'
								+'      <div class="caption">'
								+'          <h3 class="break">'
								+'              <a href="project.html">'+project.name+'</a>'
								+'          </h3>'
								+'          <p>'
								+'          <div style="float:left;">'
								+'              <i class="glyphicon glyphicon-screenshot" title="目标金额" ></i>'
								+                   project.money
								+'          </div>'
								+'          <div style="float:right;"><i title="上线日期" class="glyphicon glyphicon-calendar">'
								+'              </i>'+ project.deploydate
								+'          </div>'
								+'          </p>'
								+'          <br>'
								+'          <div class="progress" style="margin-bottom: 4px;">'
								+'              <div class="progress-bar progress-bar-success" role="progressbar" ' +
								'                    aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">'
								+'                  <span >' + project.completion + '% </span>'
								+'              </div>'
								+'          </div>'
								+'          <div><span style="float:right;"><i class="glyphicon glyphicon-star-empty" ></i></span>'
								+'              <span ><i class="glyphicon glyphicon-user" title="支持人数"></i>'
								+                   project.supporter
								+'              </span>'
								+'          </div>'
								+'      </div><!--caption-->'
								+'  </div> <!--thumbnail-->'
								+'</div> <!--col-->'
					}) // each循环
					$("#" + div_id).html(h)
				} // if
			}  //ajax:success
		}); // ajax
	}
	function details(id){
		window.location.href ="${ctx}/project/toDetail?id=" +id ;
	}
	function moreProject(div_id){
		var type = $("#" + div_id).text() // 获取分类
		window.location.href = "${ctx}/project/more?type=" + type ;
	}
</script>
</body>
</html>