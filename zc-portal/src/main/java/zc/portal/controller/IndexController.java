package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAdvertisement;
import zc.commons.pojo.TProject;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/loadData")
    @ResponseBody
    public  Object loadData(String[] types, HttpSession session){
        AjaxResult<String> result;
        AjaxResult<List<TProject>> res;
        Map<String,Object> ext = new HashMap<>();
        // 加载推荐项目
        String rec_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/recommand");
        // 转换成JSON对象
        res = JSON.toJavaObject(JSON.parseObject(rec_project), AjaxResult.class);
        // 获取内容
        // 转为json字符串,再转为list
        String c1_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category");
        List<TProject> rec_projects = JSON.parseArray(JSON.toJSONString(res.getContent()), TProject.class);
        session.setAttribute(Constant.INDEX_RECOMMAND_PROJECT, rec_projects); // 通过session保存
        // 先写死
        res = JSON.toJavaObject(JSON.parseObject(c1_project), AjaxResult.class);
        List<TProject> c1_projects = JSON.parseArray(JSON.toJSONString(res.getContent()), TProject.class);
        session.setAttribute(Constant.INDEX_CATEGORY_1_PROJECT, c1_project);
        session.setAttribute(Constant.INDEX_CATEGORY_2_PROJECT, c1_project);
        session.setAttribute(Constant.INDEX_CATEGORY_3_PROJECT, c1_project);
        session.setAttribute(Constant.INDEX_CATEGORY_OTHER_PROJECT, c1_project);
        if( (rec_projects == null ||rec_projects.size() == 0 )&& (c1_projects == null || c1_projects.size() == 0)){
            result = AjaxResult.fail("加载数据失败","项目为空",null);
        }else{
            ext.put("rec", rec_projects);
            ext.put("c1",c1_projects );
            result = AjaxResult.success("加载成功",null, ext);
        }
        return result;
    }
    @RequestMapping("/rec")
    @ResponseBody
    public  Object getRec(){
        // 加载推荐项目
        String rec_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/recommand");
        // 转换成JSON对象
        return JSON.toJavaObject(JSON.parseObject(rec_project), AjaxResult.class);

    }
    @RequestMapping("/category")
    @ResponseBody
    public  Object getCategory1(String type){
        Map<String,Object> ext = new HashMap<>();
        try {
            String project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category/" + type);
            // 转换成JSON对象
            return JSON.toJavaObject(JSON.parseObject(project), AjaxResult.class);
        } catch (Exception e) {
            ext.put("err", "跨域请求失败");
            ext.put("exception", e.toString());
            return AjaxResult.fail("加载失败",null,ext);
        }
    }

    @RequestMapping("/advInfo")
    @ResponseBody
    public  AjaxResult<List<TAdvertisement>> getCategory(){
        Map<String,Object> ext = new HashMap<>();
        try {
            String adv = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/advInfo");
            // 转换成JSON对象
            return JSON.toJavaObject(JSON.parseObject(adv), AjaxResult.class);

        } catch (Exception e) {
            ext.put("err", "跨域请求失败");
            ext.put("exception", e.toString());
            return AjaxResult.fail("加载失败",null,ext);
        }

    }

}
