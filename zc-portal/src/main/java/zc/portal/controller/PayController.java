package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberAddress;
import zc.commons.pojo.TOrder;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {
    @GetMapping("/payInfo")
    @ResponseBody
    public AjaxResult<Object> orderInfo(int projectid, int returnid, HttpSession session){
        TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
        if(member == null){
            return AjaxResult.fail("用户未登录", null, null);
        }else{
            Map<String,Object> params = new HashMap<>();
            params.put("projectid", projectid);
            params.put("returnid",returnid);
            params.put("memberid", member.getId());
            try {
                String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/pay/payInfo",params);
                return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            } catch (Exception e) {
                Map<String,Object> ext = new HashMap<>();
                ext.put("err", "跨域请求错误/JSON解析错误");
                ext.put("exception", e.toString());
                return AjaxResult.fail("请求失败", null, ext);
            }
        }
    }
    @RequestMapping("/addAddr")
    @ResponseBody
    public AjaxResult<TMemberAddress> addAddress(String name,String tel,String address,HttpSession session){
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> ext = new HashMap<>();
        TMember member = (TMember)session.getAttribute(Constant.PORTAL_LOGIN_USER);
        if(member == null){
            return AjaxResult.fail("用户未登录", null, null);
        }
        params.put("name", name);
        params.put("tel",tel);
        params.put("address", address);
        try {
            String json = HttpClientUtil.httpPostRequest(
                    Configuration.remoteAddress + "/member/"+member.getId()+"/address/add",params);
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        } catch (UnsupportedEncodingException e) {
            ext.put("err", "跨域请求错误/JSON解析错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("请求失败", null, ext);
        }
    }
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult<TOrder> pay(int price, int count, int totalmoney, int addressid, String remark, HttpSession session){
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> ext = new HashMap<>();
        TMember member = (TMember)session.getAttribute(Constant.PORTAL_LOGIN_USER);
        params.put("price", price);
        params.put("count",count);
        params.put("totalmoney", totalmoney);
        params.put("addressid", addressid);
        params.put("projectid", session.getAttribute(Constant.PROJECTID));
        params.put("returnid", session.getAttribute(Constant.RETURNID));
        params.put("memberid", member.getId());
        params.put("remark", remark);
        try {
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/pay/submit", params);
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        } catch (UnsupportedEncodingException e) {
            ext.put("err", "跨域请求错误/JSON解析错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("请求失败", null, ext);
        }
    }
    @RequestMapping("/success")
    public String paySuccess(){
        return "pay_result";
    }
}
