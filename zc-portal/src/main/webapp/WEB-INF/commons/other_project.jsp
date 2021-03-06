<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="box ui-draggable" id="mainBox">
                <div class="mHd" style="">
                    <div class="path">
                        <a onclick="moreProject('other_category')">更多...</a>
                    </div>
                    <h3>
                        <i id="other_category">其他</i> <small style="color:#FFF;">发现更多惊喜</small>
                    </h3>
                </div>
                <div class="mBd" style="padding-top:10px;">
                    <div class="row" id="oc_div">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--获取分类项目1的ajax请求，并根据结果动态生成div内容-->
<script>
    $(function(){
        $.sess
        // 获取轮播图下面的三个推荐项目
        $.ajax({
            type:"GET",
            url:"${ctx}/category",
            data:{
                "type":$("#other_category").text()
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
                        h+= '<div class="col-md-3">'
                            +'  <div class="thumbnail">'
                            +'      <img style="height: 155px" alt="300x200" onclick="details('+ project.id+')"'
                            +'           src="'+project.headpicpath+'"/>'
                            +'      <div class="caption">'
                            +'          <h3 class="break">'
                            +'              <a onclick="details(' + project.id+ ')">'+project.name+'</a>'
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
                            '                    aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:'+project.completion+'%">'
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
                    $("#oc_div").html(h)
                } // if
            }  //ajax:success
        }); // ajax
    });
</script>


