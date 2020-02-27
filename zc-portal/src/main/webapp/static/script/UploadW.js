/**
 * Created by andy on 2017/11/28.
 */
$(function () {
    (function(){
        /*结构*/
        var mUpload = $(".m_upload");
        mUpload.attr({
            'multiple':'multiple',
            'class':'hide',
            'id': 'm_upload'
        });
        mUpload.wrapAll("<a class='fileW' href='#'>上传文件</a>");
        var file_uploaderA = "<div class='m_holder'></div>";
        $(".fileW").after(file_uploaderA);
        $(".fileW, .m_holder").wrapAll("<div class='file_con'>");
        $(".file_con").wrap("<div class='file_upload'>");

        /*功能*/
        var file = {
            m_upload: function(e){
                var m_file = e.target.files;
                var name='', div='';
                for(var i=0;i<m_file.length;i++){
                    var z = m_file[i];
                    var reader = new FileReader();

                    reader.readAsDataURL(z);
                    reader.onload = (function(i){
                        return function(){
                            div = '<div class="logo"><span class="jFiler-icon-file" title='+ i.name+'>'+i.name+'</span></div>';
                            var imglist = '<div class="img_box"><div class="fileN"><span>文件大小: '+Math.round(i.size / 1024) + 'kb</span><span class="del">x</span></div>' + div+'</div>';
                            $(".m_holder").append(imglist);
                        };
                    })(z);
                };
            },
            /*删除文件*/
            event: function(){
                $("#m_upload").change(function(e){
                    file.m_upload(e);
                });
                $(".file_upload").delegate(".del","click",function(){
                    var odel = $(this);
                    odel.parents(".img_box").remove();
                    $("#upload").val('');
                })
            },
            init: function(){
                this.event();
            }
        }
        file.init();
    }());
    /* 客户端文件 */
    /* 获取文件后缀 */
    var fileTypeof = function () {
        var fileType = $(".jFiler-icon-file-see").text();
        var splType = fileType.indexOf('.');
        fileType = fileType.substring(splType);
        $(".fileSee").click(function () {
            if (fileType != ".bmp" && fileType != ".png" && fileType != ".gif" && fileType != ".jpg" && fileType != ".jpeg") {
                alert("非图片类不支持查看");
            } else {
                alert("可以查看");
            }
        });
    }();
})