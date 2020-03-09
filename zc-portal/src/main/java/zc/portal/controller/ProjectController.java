package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.bean.MyProject;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;
import zc.commons.pojo.TReturn;
import zc.manager.Page;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @RequestMapping("/detail")
    @ResponseBody
    public AjaxResult<MyProject> details(int id){
        String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/" + id);
        AjaxResult<MyProject> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        return result;
    }
    @RequestMapping("/toDetail")
    public String projectDetail(int id,HttpSession session){
        session.setAttribute(Constant.PROJECTID, id);
        return "project";
    }

    @GetMapping(value = "/support")
    @ResponseBody
    public AjaxResult<?> support(int projectid,int returnid,HttpSession session){
        session.setAttribute(Constant.PROJECTID, projectid);
        try {
            TMember member = (TMember) session.getAttribute(Constant.PORTAL_LOGIN_USER);
            if(member == null){
                return AjaxResult.fail("请先进行登录", null, null);
            }else{
                Map<String,Object> ext = new HashMap<>();
                ext.put("projectid", projectid);
                ext.put("returnid", returnid);
                return AjaxResult.success("已经登陆", null, ext);
            }
        } catch (Exception e) {
            return AjaxResult.fail("请求错误", null, null);
        }
    }
    @RequestMapping("/pay")
    public String pay_1(int projectid,int returnid,HttpSession session){
        session.setAttribute(Constant.PROJECTID, projectid);
        session.setAttribute(Constant.RETURNID, returnid);
        return "pay";
    }
    @RequestMapping("/more")
    public String moreProject(String type,HttpSession session){
        String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/category/" + type);
        AjaxResult<List<TProject>> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        String pro = JSON.toJSONString(result.getContent());
        List<TProject> projects = JSON.parseArray(pro, TProject.class);
        session.setAttribute(Constant.CURRENT_SELECTED_TYPE,type);
        session.setAttribute(Constant.PROJECTS_BY_TYPE, projects);
        session.setAttribute(Constant.PROJECTS_COUNT, projects.size());
        return "projects";
    }
    @GetMapping(value = "/asyncLoadData")
    @ResponseBody
    public AjaxResult<Page<TProject>> loadData(@RequestParam(required = true)int pageno,
                                               @RequestParam(required = true)int pagesize,
                                               @RequestParam(required = true)String type,
                                               @RequestParam(required = true)String status,
                                               @RequestParam(required = true)String order,
                                               @RequestParam(required = false)String queryContent){
        AjaxResult<Page<TProject>> result;
        Map<String, Object> ext = new HashMap<>();
        try {
            String url = null;
            if(queryContent == null || "".equals(queryContent)){
                url = Configuration.remoteAddress +"/project/projects?start="+((pageno -1) * pagesize)
                        +"&pagesize="+ pagesize +"&type="+type+"&status="+ status
                        +"&order="+ order ;
            }else{
                url = Configuration.remoteAddress +"/project/projects?start="+((pageno -1) * pagesize)
                        +"&pagesize="+ pagesize +"&type="+type+"&status="+ status
                        +"&order="+ order +"&queryContent=" + queryContent;
            }
            String json = HttpClientUtil.httpGetRequest(url);
            if(queryContent == null || "".equals(queryContent)){
                url = Configuration.remoteAddress +"/project/count?start="+((pageno -1) * pagesize)
                        +"&pagesize="+ pagesize +"&type="+type+"&status="+ status
                        +"&order="+ order ;
            }else{
                url = Configuration.remoteAddress +"/project/count?start="+((pageno -1) * pagesize)
                        +"&pagesize="+ pagesize +"&type="+type+"&status="+ status
                        +"&order="+ order +"&queryContent=" + queryContent;
            }
            String count_json = HttpClientUtil.httpGetRequest(url);
            AjaxResult<Integer> count = JSON.toJavaObject(JSON.parseObject(count_json), AjaxResult.class);
            // 这种方法，有中文时会乱码，服务端接受请求时不能正确解码
//            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress +"/project/projects",params);
            result = AjaxResult.success("数据加载完成",setPageData(json,pageno,pagesize,count.getContent()),null);
        } catch (Exception e) {
            // 查询失败
            ext.put("err", "跨域请求错误/跨域返回结果处理错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("加载数据失败",null,ext);
        }
        return result;
    }

    @GetMapping(value = "/return")
    @ResponseBody
    public AjaxResult<List<TReturn>> getReturn(int projectid){
        Map<String, Object> ext = new HashMap<>();
        try {
            String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress+"/project/return/" + projectid);
            return JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        } catch (Exception e) {
            // 查询失败
            ext.put("err", "跨域请求错误/跨域返回结果处理错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("加载数据失败",null,ext);
        }
    }

    private Page<TProject> setPageData(String json,int pageno,int pagesize,int totalsize){
        AjaxResult<List<TProject>> ajaxResult = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        List<TProject> projects = JSON.parseArray(JSON.toJSONString(ajaxResult.getContent()), TProject.class);
        Page<TProject> p_page = new Page<>();
        // 总页数
        int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
        p_page.setDatas(projects); // 设置List<TProject>
        p_page.setPageno(pageno);
        p_page.setTotalno(totalno); //总页数
        p_page.setTotalsize(totalsize); // 总记录数
        return  p_page;
    }

}
