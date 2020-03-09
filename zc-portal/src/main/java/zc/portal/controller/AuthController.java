package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;
import zc.portal.util.UploadFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping("/apply")
    @ResponseBody
    public AjaxResult<TMember> apply(MultipartFile[] file, String tel,String realname,String cardnum,
                        String validateCode, int[] certid,HttpSession session){

        AjaxResult<TMember> result;
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> ext = new HashMap<>();
        List<AjaxResult<String>> uploadResults = new ArrayList<>();
        // 资质上传
        try {
            for(MultipartFile f:file){
                 uploadResults.add(UploadFile.upload(Configuration.remoteAddress +"/upload/auth/", f));
            }
        } catch (Exception e) {
            // 删除已上传的图片
            try {
                for(AjaxResult<String> r:uploadResults){
                    UploadFile.deleteResource(Configuration.remoteAddress +"/upload/auth/" + r.getContent());
                }
            } catch (Exception e1) {
                ext.put("exception", e.toString());
                result = AjaxResult.fail("上传失败时，删除已上传图片错误",null,ext);
                return result;
            }
            ext.put("exception", e.toString());
            result = AjaxResult.fail("图片上传错误",null,ext);
            return result;
        }
        // 上传成功
        String[] filenames = new String[uploadResults.size()];
        for(int i = 0;i < uploadResults.size();i++){
            filenames[i] = uploadResults.get(i).getContent();
        }
        String acct = ((TMember)session.getAttribute(Constant.PORTAL_LOGIN_USER)).getLoginacct();
        // 设置认证类型
        String acct_type = (String)session.getAttribute(Constant.PORTAL_AUTH_ACCT_TYPE);
        params.put("file",JSON.toJSONString(filenames));
        params.put("loginacct", acct);
        params.put("realname", realname);
        params.put("tel", tel);
        params.put("cardnum", cardnum);
        params.put("accttype", acct_type);
        params.put("validateCode", validateCode);
        params.put("certids",JSON.toJSONString(certid));
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/auth/submit" , params );
            result = JSON.toJavaObject(JSON.parseObject(json),AjaxResult.class);
            return result;
        }catch (Exception e){
            ext.put("err", "跨域请求异常");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("提交信息失败",null,ext);
        }
        return result;
    }
}
