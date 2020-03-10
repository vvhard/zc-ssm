package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zc.commons.bean.*;
import zc.commons.pojo.*;
import zc.manager.Page;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @RequestMapping("/personal_center")
    public String personalCenter(int id,HttpSession session){
        // 返回结果为字符串
        String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/member/"+id);
        // 根据结果对json字符串进行转换，得到原来的内容
        // 先转换为json对象,JSON.parseObject(result)
        AjaxResult<TMember> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        String content = JSON.toJSONString(result.getContent());
        // 解析为JSON对象，再将JSON对象转换为Java对象
        TMember member = JSON.toJavaObject(JSON.parseObject(content), TMember.class);
        session.setAttribute(Constant.PORTAL_LOGIN_USER, member);
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
    @RequestMapping("/authstatus")
    @ResponseBody
    public   AjaxResult<TMember> authStatus(int memberid){
        AjaxResult<TMember> result;
        Map<String,Object> ext = new HashMap<>();
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/auth/status/" + memberid);
            result =JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
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

    @RequestMapping("/auth_complete")
    public String completeSubmit(){
        return "apply1";
    }

    @GetMapping("/support")
    @ResponseBody
    public   AjaxResult<Page<MySupport>> mySupport(int memberid, int pageno, int pagesize, String status){
        AjaxResult<List<MySupport>> result;
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("memberid", memberid);
        params.put("pageno", pageno);
        params.put("pagesize", pagesize);
        params.put("status", status);
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/order/support",params);
            result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            if(result.getCode() == 0){
                return AjaxResult.fail("跨域请求失败，请稍后重试",null,result.getExt());
            }
            List<MySupport> supports = JSON.parseArray(JSON.toJSONString(result.getContent()), MySupport.class);
            // 分页对象
            Page<MySupport> supportPage = new Page<>();
            supportPage.setDatas(supports);
            // 总页数
            int totalsize = (int)result.getExt().get("totalsize");
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            supportPage.setTotalsize(totalsize);
            supportPage.setTotalno(totalno);
            supportPage.setPageno(pageno);
            return AjaxResult.success("加载完成", supportPage, null);
        }catch (Exception e){
            e.printStackTrace();
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
           return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @GetMapping("/initiate")
    @ResponseBody
    public AjaxResult<Page<MyInitiate>> myInitiate(int memberid, int pageno, int pagesize, String status){
        AjaxResult<List<MyInitiate>> result;
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("memberid", memberid);
        params.put("pageno", pageno);
        params.put("pagesize", pagesize);
        params.put("status", status);
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/initiate",params);
            result =JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            if(result.getCode() == 0){
                return AjaxResult.fail("跨域请求失败，请稍后重试",null,result.getExt());
            }
            List<MyInitiate> initiateProjects = JSON.parseArray(JSON.toJSONString(result.getContent()), MyInitiate.class);
            // 分页对象
            Page<MyInitiate> projectPage = new Page<>();
            projectPage.setDatas(initiateProjects);
            // 总页数
            int totalsize = (int)result.getExt().get("totalsize");
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            projectPage.setTotalsize(totalsize);
            projectPage.setTotalno(totalno);
            projectPage.setPageno(pageno);
            return AjaxResult.success("加载完成", projectPage, null);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @GetMapping("/follow")
    @ResponseBody
    public AjaxResult<Page<MyFollow>> myFollow(int memberid, int pageno, int pagesize){
        AjaxResult<List<MyFollow>> result;
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("memberid", memberid);
        params.put("pageno", pageno);
        params.put("pagesize", pagesize);
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/follow",params);
            result =JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            if(result.getCode() == 0){
                return AjaxResult.fail("跨域请求失败，请稍后重试",null,result.getExt());
            }
            List<MyFollow> followProjects = JSON.parseArray(JSON.toJSONString(result.getContent()), MyFollow.class);
            // 分页对象
            Page<MyFollow> projectPage = new Page<>();
            projectPage.setDatas(followProjects);
            // 总页数
            int totalsize = (int)result.getExt().get("totalsize");
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            projectPage.setTotalsize(totalsize);
            projectPage.setTotalno(totalno);
            projectPage.setPageno(pageno);
            return AjaxResult.success("加载完成", projectPage, null);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }

    @GetMapping("/project/detail")
    @ResponseBody
    public AjaxResult<TProject> projectDetail(int projectid){
        Map<String,Object> ext = new HashMap<>();
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/" + projectid);
            AjaxResult<TProject> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            return result;
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @GetMapping("/order/detail")
    @ResponseBody
    public AjaxResult<MyOrder> orderDetail(String ordernum){
        Map<String,Object> ext = new HashMap<>();
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/order/" + ordernum);
            AjaxResult<MyOrder> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            return result;
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }

    @PostMapping("/order/delete")
    @ResponseBody
    public AjaxResult<Object> deleteOrder(String ordernum){
        Map<String,Object> ext = new HashMap<>();
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/order/delete" + ordernum);
            AjaxResult<Object> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            return result;
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }

    @PostMapping("/project/cancelFollow")
    @ResponseBody
    public AjaxResult<Object> cancelFollow(int memberid ,int projectid){
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("memberid", memberid);
        params.put("projectid",projectid);
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/project/cancelFollow",params);
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);

        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }

    @PostMapping("/project/followProject")
    @ResponseBody
    public AjaxResult<Object> follow(int projectid,HttpSession session){
        Map<String,Object> ext = new HashMap<>();
        TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
        if(member == null){
            ext.put("err", "用户未登录");
            return AjaxResult.fail("请先进行登录",null,ext);
        }
        Map<String,Object> params = new HashMap<>();
        params.put("memberid", member.getId());
        params.put("projectid",projectid);
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/project/followProject",params);
            AjaxResult<Object> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            return result;
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult<TMember> updateInfo(String loginacct,String username,String tel,String email,String description){
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("loginacct", loginacct);
        params.put("username",username);
        params.put("tel",tel);
        params.put("email",email);
        params.put("description",description);
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/member/update",params);
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @RequestMapping("/updatePassword")
    @ResponseBody
    public AjaxResult<TMember> updateInfo(String loginacct,String oldPassword,String newPassword){
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("loginacct", loginacct);
        params.put("oldPassword",oldPassword);
        params.put("newPassword",newPassword);
        try{
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/member/updatePassword",params);
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @RequestMapping("/address")
    @ResponseBody
    public AjaxResult<List<TMemberAddress>> updateInfo(String memberid){
        Map<String,Object> ext = new HashMap<>();
        try{
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/member/"+ memberid +"/address" );
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("请求失败，请稍后重试",null,ext);
        }
    }
    @RequestMapping("/address/add")
    @ResponseBody
    public AjaxResult<TMemberAddress> addAddress(String name,String tel,String address,HttpSession session){
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
        if(member == null){
            return AjaxResult.fail("用户未登录", null, null);
        }else {
            try {
                params.put("name", name);
                params.put("tel", tel);
                params.put("address", address);
                String json = HttpClientUtil.httpPostRequest(
                        Configuration.remoteAddress + "/member/"+member.getId()+"/address/add" , params);
                return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            } catch (Exception e) {
                ext.put("err", "请求失败");
                ext.put("exception", e.toString());
                return AjaxResult.fail("请求失败，请稍后重试", null, ext);
            }
        }
    }
    @RequestMapping("/address/delete")
    @ResponseBody
    public AjaxResult<Object> deleteAddress(String addressid,HttpSession session){
        Map<String,Object> ext = new HashMap<>();
        TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
        if(member == null){
            return AjaxResult.fail("用户未登录", null, null);
        }else {
            try {
                String json = HttpClientUtil.httpPostRequest(
                        Configuration.remoteAddress + "/member/address/delete/" + addressid);
                return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            } catch (Exception e) {
                ext.put("err", "请求失败");
                ext.put("exception", e.toString());
                return AjaxResult.fail("请求失败，请稍后重试", null, ext);
            }
        }
    }





}
