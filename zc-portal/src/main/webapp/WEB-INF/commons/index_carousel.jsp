<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox" id="advDiv">

    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->
<script>
    $(function(){
        $.ajax({
            type:"GET",
            url:"${ctx}/advInfo",
            data:{},
            success:function(result){
                if(result.code == 1){
                    var advs = result.content;
                    var a = '';
                    $.each(advs,function(index,adv){
                        if(index == 0){
                            a+='<div class="item active" onclick="" style="cursor:pointer;">'
                                +'	<img src="'+adv.iconpath+'" alt="Third slide"/>'
                                +'</div>'
                        }else{
                            a+='<div class="item " onclick="" style="cursor:pointer;">'
                                +'	<img src="'+adv.iconpath+'" alt="Third slide"/>'
                                +'</div>'
                        }
                    })
                    $("#advDiv").prepend(a);
                }else{

                }
            }
        })
    });
</script>
