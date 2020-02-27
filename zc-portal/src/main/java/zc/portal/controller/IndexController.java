package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TProject;
import zc.portal.util.Configuration;
import zc.portal.util.Constant;
import zc.portal.util.HttpClientUtil;

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
        System.out.println(types);
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
        for(TProject p:rec_projects)
            System.out.println(p.toString());
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
        AjaxResult<List<TProject>> result;
        // 加载推荐项目
        String rec_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/recommand");
        // 转换成JSON对象
        result = JSON.toJavaObject(JSON.parseObject(rec_project), AjaxResult.class);
        if(result.getCode() == 1){
            result.setMsg("加载成功");
        }else{
            result.setMsg("加载失败");
        }
        return result;
    }
    @RequestMapping("/category")
    @ResponseBody
    public  Object getCategory1(String type){
        AjaxResult<List<TProject>> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            // TODO 先默认请求这个，后续改回这个
//            String project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category/" + type);
            String project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category");
            // 转换成JSON对象
            result = JSON.toJavaObject(JSON.parseObject(project), AjaxResult.class);
            if(result.getCode() == 0){
                result.setMsg("加载失败");
            }else{
                result.setMsg("加载成功");
            }
        } catch (Exception e) {
            ext.put("err", "跨域请求失败");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("加载失败",null,ext);
        }
        return result;
    }
//    @RequestMapping("/loadData")
//    @ResponseBody
//    public  AjaxResult<String> loadData(String[] types){
//        AjaxResult<String> result;
//        // 加载推荐项目
//        String rec_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/recommand");
//        // 先写死
//        // 加载分类1
//        String c1_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category/");
//        // 加载分类2
//        String c2_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category2");
//        // 加载分类3
//        String c3_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category3");
//        // 加载其他分类
//        String oth_project = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/category_oth");
//        return result;
//    }
}
