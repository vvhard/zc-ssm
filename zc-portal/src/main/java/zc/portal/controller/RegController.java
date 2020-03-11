package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.util.Configuration;
import zc.commons.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class RegController {
    // TODO 将注册功能完善，做到邮件发送验证码，验证邮箱可用
    @RequestMapping("/reg")
    public AjaxResult<TMember> index(String loginacct, String userpswd, String email){
        AjaxResult<TMember> result ;
        Map<String,Object> params = new HashMap<>();
        params.put("loginacct", loginacct);
        params.put("userpswd", userpswd);
        params.put("email",email);
        // 跨域请求相当于请求转发，尽量不改变请求内容
        try{
            String result_json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/reg", params);
            // 转换为传输前的对象
            result = JSON.toJavaObject(JSON.parseObject(result_json), AjaxResult.class);
            if(result.getCode() == 1){

            }else{

            }
        }catch (Exception e){
            Map<String,Object> ext = new HashMap<>();
            ext.put("err", "跨域请求发生错误");
            result = AjaxResult.fail("请求失败，稍后重试！",null, ext);

        }
        return result;
    }
    /**
     * 发送邮件
     * @return
     */
    public Object sendEmail(){
        AjaxResult result = new AjaxResult();
        return result;
    }
}
