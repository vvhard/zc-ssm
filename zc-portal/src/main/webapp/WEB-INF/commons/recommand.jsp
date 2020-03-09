<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="rec_div">
</div><!-- /.row -->
<!--获取推荐项目的ajax请求，并根据结果动态生成div内容-->
<script>
    $(function(){
        $.sess
        // 获取轮播图下面的三个推荐项目
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
                            +'   <img class="img-circle" src="'+project.headpicpath+'"'
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
    });

</script>

