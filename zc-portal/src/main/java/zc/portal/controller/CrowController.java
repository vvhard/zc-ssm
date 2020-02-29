package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.portal.util.Configuration;
import zc.portal.util.Constant;
import zc.portal.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crow")
public class CrowController {
    @RequestMapping("/detect")
    @ResponseBody
    public AjaxResult<String> jump(HttpSession session){
        AjaxResult<String> result;
        // 判断是否已经登录
        TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
        try {
            if(member == null){
                result = AjaxResult.fail("请先进行登录再发起众筹",null,null);
            }else{
                String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/crow/member/" + member.getLoginacct());
                AjaxResult<Boolean> auth = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
                if(auth.getContent()){
                    result = AjaxResult.success("已经进行实名认证",null,null);
                }else {
                    result = AjaxResult.fail("请先进行实名认证再发起众筹",null,null);
                }
            }
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            result = AjaxResult.fail("请求错误",null,ext);
        }
        return result;
    }
    @RequestMapping("/start")
    public String start(){
        return "strat";
    }
    @RequestMapping("/start_step1")
    public String step1(){
        return "start_step_1";
    }
    @RequestMapping("/start_step2")
    public String step2(){
        return "start_step_2";
    }
    @RequestMapping("/start_step3")
    public String step3(){
        return "start_step_3";
    }
}
