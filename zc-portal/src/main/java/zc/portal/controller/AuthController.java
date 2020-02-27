package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zc.api.service.MemberService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberCert;
import zc.portal.util.Configuration;
import zc.portal.util.Constant;
import zc.portal.util.HttpClientUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping("/apply1")
    @ResponseBody
    public Object apply(MultipartFile[] file, TMember member, String validateCode, int[] certid,HttpSession session){
        AjaxResult<String> result;
        Map<String,Object> params = new HashMap<>();
        String acct = ((TMember)session.getAttribute(Constant.PORTAL_LOGIN_USER)).getLoginacct();
        // 设置认证类型
        member.setAccttype((String) session.getAttribute(Constant.PORTAL_AUTH_ACCT_TYPE));
        // 将参数对象转为json ，进行传送
//        JSON.toJSONString(file);
//        JSON.toJSONString(member);
//        JSON.toJSONString(certid);
        List<MultipartFile> fl = new ArrayList<>();
        for(MultipartFile f :file){
            fl.add(f);
        }
        List<Integer> cl = new ArrayList<>();
        for (int e:certid)
            cl.add(e);
        params.put("loginacct", acct);
        params.put("file", JSON.toJSONString(fl));
        params.put("member", JSON.toJSONString(member));
        params.put("validateCode", validateCode);
        params.put("certid",JSON.toJSONString(cl));
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/auth/upload" , params );
            System.out.println(json);
            result = AjaxResult.success("提交成功,请等待后台审核结果", json, null);
        }catch (Exception e){
            Map<String,Object> ext = new HashMap<>();
            ext.put("err", "跨域请求异常");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("提交信息失败",null,ext);
        }
        return result;
    }

}
