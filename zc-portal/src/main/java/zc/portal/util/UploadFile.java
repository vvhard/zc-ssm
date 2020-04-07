package zc.portal.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.multipart.MultipartFile;
import zc.commons.bean.AjaxResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadFile {

    public static AjaxResult<String> upload(String webPath, MultipartFile file){
        AjaxResult<String> result ;
        // 1. 获取原始文件名
//        String extendName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        // 3. 把文件加上随机数，防止文件重复
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 5).toLowerCase();
        String filename = uuid + "-" + file.getOriginalFilename();
        // 创建sun公司提供的jersey包中的Client对象
        Client client = Client.create();
        // 指定上传文件的地址，该地址是web路径
        WebResource resource = client.resource(webPath + filename);
        try {
            resource.put(file.getBytes());
            result = AjaxResult.success("上传成功",  (webPath + filename), null);
        } catch (IOException e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            result = AjaxResult.fail("上传失败", null, ext);
        }
        return result;
    }

    public static AjaxResult<String> deleteResource(String webPath){
        AjaxResult<String> result ;

        Client client = Client.create();
        try {
            WebResource webService = client.resource(webPath);
            webService.delete();
            result = AjaxResult.success("删除成功", null, null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            result = AjaxResult.fail("删除失败", null, ext);
        }
        return result;
    }
}
