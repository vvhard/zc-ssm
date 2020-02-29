package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;
import zc.manager.Page;
import zc.portal.util.Configuration;
import zc.portal.util.Constant;
import zc.portal.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @RequestMapping("/detail")
    public String details(int id, HttpSession session){
        String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/" + id);
        AjaxResult<TProject> result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        String p = JSON.toJSONString(result.getContent());
        TProject project = JSON.toJavaObject(JSON.parseObject(p), TProject.class);
        // 获取项目发布者信息
        String m = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/project/member/" + project.getMemberid());
        AjaxResult<TMember> memberAjaxResult = JSON.toJavaObject(JSON.parseObject(m), AjaxResult.class);
        String mem = JSON.toJSONString(memberAjaxResult.getContent());
        TMember member = JSON.toJavaObject(JSON.parseObject(mem), TMember.class);
        session.setAttribute(Constant.PROJECT_MEMBER, member);
        session.setAttribute(Constant.PROJECT, project);
        return "project";
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
//        Map<String, Object> params = new HashMap<>();
        try {
//            params.put("start", (pageno -1) * pagesize);
//            params.put("pagesize",  pagesize);
//            params.put("type", type);
//            params.put("status", status);
//            params.put("order", order);
//            params.put("queryContent", queryContent);
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
    private Page<TProject> setPageData(String json,int pageno,int pagesize,int totalsize){
        AjaxResult<List<TProject>> ajaxResult = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        List<TProject> projects = JSON.parseArray(JSON.toJSONString(ajaxResult.getContent()), TProject.class);
        Page<TProject> p_page = new Page<>();

//        int totalsize = projects.size();
        // 总页数
        int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
        p_page.setDatas(projects); // 设置List<TProject>
        p_page.setPageno(pageno);
        p_page.setTotalno(totalno); //总页数
        p_page.setTotalsize(totalsize); // 总记录数
        return  p_page;
    }
}
