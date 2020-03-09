package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.*;
import zc.commons.util.DateUtil;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;
import zc.portal.util.UploadFile;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
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
    public String step1(HttpSession session){
        // 获取所有分类
        String type_json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/type/types");
        AjaxResult<List<TType>>  types = JSON.toJavaObject(JSON.parseObject(type_json), AjaxResult.class);
        String type = JSON.toJSONString(types.getContent());
        session.setAttribute(Constant.TYPES, JSON.parseArray(type, TType.class));
        // 获取标签
        return "start_step_1";
    }

    @RequestMapping("/start_step2")
    public String step2(int projectTempId,HttpSession session){
        session.setAttribute(Constant.PROJECTTEMPID,projectTempId);
        return "start_step_2";
    }
    @RequestMapping("/start_step4")
    public String step3(int project_temp_id,String app_id,String app_private_key,String alipay_public_key,
                        HttpSession session){
        session.setAttribute(Constant.PROJECTTEMPID,project_temp_id);
        Map<String,Object> params = new HashMap<>();
        params.put("project_temp_id", project_temp_id);
        params.put("app_id", app_id);
        params.put("app_private_key", app_private_key);
        params.put("alipay_public_key", alipay_public_key);
        try {
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/crow/pay_info",params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "start_step_4";
    }
    @RequestMapping("/start_step3")
    public String step4(int projectTempId,HttpSession session){
        session.setAttribute(Constant.PROJECTTEMPID,projectTempId);
        return "start_step_3";
    }

    @RequestMapping("/sumbit")
    @ResponseBody
    public AjaxResult<TProjectTemp> submit(@RequestParam(required = false) String projectid, String type, String name, String remark, int money, int day,
                                           MultipartFile hfile, MultipartFile dfile, String contact, HttpSession session){
        AjaxResult<TProjectTemp> result;
        TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
        if(member == null){
            return AjaxResult.fail("未登录",null,null);
        }
        Map<String,Object> ext = new HashMap<>();
        AjaxResult<String> uploadResult1 = null;
        AjaxResult<String> uploadResult2 = null;
        try {
            uploadResult1 = UploadFile.upload(Configuration.remoteAddress+"/upload/crow/", hfile);
            uploadResult2 = UploadFile.upload(Configuration.remoteAddress+"/upload/crow/", dfile);
        } catch (Exception e) {
            // 失败时，删除已上传的图片,只需删除第一张
            UploadFile.deleteResource(Configuration.remoteAddress+"/upload/crow/" + uploadResult1);
            ext.put("exception", e.toString());
            result = AjaxResult.fail("图片上传错误",null,ext);
            return result;
        }
        if((uploadResult1.getCode() == 1) && (uploadResult2.getCode() ==1)){

            String hFileRealPath = uploadResult1.getContent();
            String dFileRealPath = uploadResult2.getContent();
            // 添加到数据库
            Map<String,Object> params = new HashMap<>();
            params.put("type",type);
            params.put("memberid",member.getId());
            params.put("name",name);
            params.put("remark",remark);
            params.put("money",money);
            params.put("day",day);
            params.put("createdate",DateUtil.currentDate());
            params.put("headpicpath",hFileRealPath); // 项目头图
            params.put("detailpicpath",dFileRealPath); // 项目详情图
            params.put("contact",contact);
            try {
                String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/crow/step1",params);
                result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
            } catch (Exception e) {
                // 失败时，删除已上传的图片
                UploadFile.deleteResource(Configuration.remoteAddress+"/upload/crow/" + uploadResult1);
                // 失败时，删除已上传的图片,只需删除第一张
                UploadFile.deleteResource(Configuration.remoteAddress+"/upload/crow/" + uploadResult2);
                ext.put("err", "跨域请求错误");
                ext.put("exception", e.toString());
                result = AjaxResult.success("项目信息提交失败",null,ext);
            }
        }else{
            result = AjaxResult.success("上传失败",null,null);
        }
        return result;
    }
    @RequestMapping("/addrtn")
    @ResponseBody
    public AjaxResult<TReturnTemp> addReturnTemp(int projectid,String type, int supportmoney ,String content,
                                                 int count, int signalpurchase,int purchase,int freight,String invoice,
                                                 int rtndate){
        AjaxResult<TReturnTemp> result;
        Map<String,Object> ext = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("projectid", projectid);
        params.put("type",type);
        params.put("supportmoney",supportmoney);
        params.put("content",content);
        params.put("count",count);
        params.put("signalpurchase",signalpurchase);
        params.put("purchase",purchase);
        params.put("freight",freight);
        params.put("invoice",invoice);
        params.put("rtndate",rtndate);
        try {
            String json = HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/crow/rtn", params);
            result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        } catch (Exception e) {
            ext.put("err", "跨域请求错误");
            ext.put("exception", e.toString());
            result = AjaxResult.success("新增失败",null,null);
        }
        return result;
    }
}
