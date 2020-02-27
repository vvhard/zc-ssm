package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;
import zc.portal.util.Configuration;
import zc.portal.util.Constant;
import zc.portal.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @RequestMapping("/personal_center")
    public String personalCenter(){
        return "personal_center";
    }
    @RequestMapping("/auth")
    public String toAuthPage(HttpSession session){
        // 携带数据过去
        String result_json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/auth/acct_type");
        // 转换成JSON对象  ,再转Java对象
        AjaxResult<List<TAcctType>> result =  JSON.toJavaObject(JSON.parseObject(result_json),AjaxResult.class);
        List<TAcctType> list ;
        String content = JSON.toJSONString(result.getContent());// 转为json字符串
        list = JSON.parseArray(content, TAcctType.class);
        session.setAttribute(Constant.PORTAL_AUTH_ACCT_TYPE, list);
        return "acct_type";
    }
    @RequestMapping("/auth_apply")
    @ResponseBody
    public   AjaxResult<List<TCert>> authApply(String acct_type, HttpSession session){
        session.setAttribute(Constant.PORTAL_AUTH_ACCT_TYPE, acct_type); // 更新被选中的
        AjaxResult<List<TCert>> result;
        Map<String,Object> ext = new HashMap<>();
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/auth/" + acct_type);
            AjaxResult<List<TCert>> content = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            List<TCert> certs = JSON.parseArray(JSON.toJSONString(content.getContent()),TCert.class);
            session.setAttribute(Constant.PORTAL_AUTH_ACCT_TYPE_CERTS, certs);
            result = AjaxResult.success("请求成功",certs, null);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            result = AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
        return result;
    }

    /**
     * 申请第一步
     * @return
     */
    @RequestMapping("/apply_page")
    public  String authApply_1(){
        return "apply";
    }

}
