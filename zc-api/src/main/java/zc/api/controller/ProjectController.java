package zc.api.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zc.commons.bean.MyFollow;
import zc.commons.bean.MyInitiate;
import zc.commons.bean.MyProject;
import zc.commons.pojo.TReturn;
import zc.manager.service.ProjectService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping(value = "/all")
    public AjaxResult<List<TProject>> allProjects(){
        AjaxResult<List<TProject>> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TProject> projects = projectService.getProjects();
            if(projects != null){
                result = AjaxResult.success("查询成功",projects, null);
            }else{
                result = AjaxResult.fail("查询失败，项目为空",null, null);
            }
        } catch (Exception e) {
            ext.put("err", "数据库查询失败");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("获取失败", null, ext);
        }
        return  result;
    }
    @GetMapping(value = "/{id}")
    public AjaxResult<MyProject> projectDeatils(@PathVariable("id") int id){
        AjaxResult<MyProject> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            MyProject project = projectService.getProjectById(id);
            if(project != null){
                result = AjaxResult.success("查询成功",project, null);
            }else{
                result = AjaxResult.fail("查询失败，项目不存在",null, null);
            }
        } catch (Exception e) {
            ext.put("err", "数据库查询失败");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("获取失败", null, ext);
        }
        return  result;
    }
    @GetMapping(value = "/member/{id}")
    public AjaxResult<TMember> projectDeployer(@PathVariable("id") int id){
        AjaxResult<TMember> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            TMember member = projectService.getProjectMemById(id);
            if(member != null){
                result = AjaxResult.success("查询成功",member, null);
            }else{
                result = AjaxResult.fail("查询失败，项目不存在",null, null);
            }
        } catch (Exception e) {
            ext.put("err", "数据库查询失败");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("获取失败", null, ext);
        }
        return  result;
    }
    @GetMapping(value = "/category/{type}")
    public AjaxResult<List<TProject>> projectsWithType(@PathVariable("type") String type){
        AjaxResult<List<TProject>> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TProject> projects = projectService.getProjectByType(type);
            if(projects != null){
                result = AjaxResult.success("查询成功",projects, null);
            }else{
                result = AjaxResult.fail("查询失败，项目不存在",null, null);
            }
        } catch (Exception e) {
            ext.put("err", "数据库查询失败");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("获取失败", null, ext);
        }
        return  result;
    }
    @GetMapping(value = "/projects")
    public AjaxResult<List<TProject>> loadData(@RequestParam(required = true)int start,
                                               @RequestParam(required = true)int pagesize,
                                               @RequestParam(required = true)String type,
                                               @RequestParam(required = true)String status,
                                               @RequestParam(required = true)String order,
                                               @RequestParam(required = false)String queryContent){
        AjaxResult<List<TProject>> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TProject> projects = projectService.getProjects(start,pagesize,type,status,order,queryContent);
            ext.put("debug", "load:queryContent" + queryContent);
            result = AjaxResult.success("查询完成",projects, ext);
        } catch (Exception e) {
            ext.put("err", "数据库发生的错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询数据失败",null, ext);
        }
        return result;
     }
    @GetMapping(value = "/count")
    public AjaxResult<Integer> projectCount(@RequestParam(required = true)int start,
                                               @RequestParam(required = true)int pagesize,
                                               @RequestParam(required = true)String type,
                                               @RequestParam(required = true)String status,
                                               @RequestParam(required = true)String order,
                                               @RequestParam(required = false)String queryContent){
        AjaxResult<Integer> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            int count = projectService.getProjectCount(start,pagesize,type,status,order,queryContent);
            result = AjaxResult.success("查询完成",count, ext);
        } catch (Exception e) {
            ext.put("err", "数据库发生的错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询数据失败",null, ext);
        }
        return result;
    }


    @GetMapping(value = "/follow")
    public AjaxResult<List<MyFollow>> getFollowProjects(int memberid, int pageno, int pagesize){
        Map<String,Object> ext = new HashMap<>();
        try {
            List<MyFollow> projects = projectService.getFollowProject(memberid,(pageno-1)*pagesize,pagesize);
            int count = projectService.getFollowProjectCount(memberid);
            ext.put("totalsize", count);
            return AjaxResult.success("查询完成",projects, ext);
        } catch (Exception e) {
            ext.put("err", "数据库发生的错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("查询数据失败",null, ext);
        }
    }

    @GetMapping(value = "/initiate")
    public AjaxResult<List<MyInitiate>> getInitiateProjects(int memberid, int pageno, int pagesize, String status){
        Map<String,Object> ext = new HashMap<>();
        try {
            List<MyInitiate> projects = projectService.getInitiateProject(memberid,(pageno-1)*pagesize,pagesize,status);
            int count = projectService.getInitiateProjectCount(memberid,status);
            ext.put("totalsize", count);
            return AjaxResult.success("查询完成",projects, ext);
        } catch (Exception e) {
            ext.put("err", "数据库发生的错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("查询数据失败",null, ext);
        }
    }

    @PostMapping("/cancelFollow")
    @ResponseBody
    public AjaxResult<Object> cancelFollow(int memberid ,int projectid){
        Map<String,Object> ext = new HashMap<>();
        try{
            projectService.cancelFollow(memberid,projectid);
            return AjaxResult.success("取消关注成功",null,null);
        }catch (Exception e){
            ext.put("err", "数据库错误");
            ext.put("exception",e.toString());
            return AjaxResult.fail("取消关注失败",null,ext);
        }
    }

    @PostMapping("/followProject")
    @ResponseBody
    public AjaxResult<Object> follow(int memberid,int projectid){
        Map<String,Object> ext = new HashMap<>();
        try{
            projectService.followProject(memberid,projectid);
            return AjaxResult.success("关注成功",null,null);
        }catch (Exception e){
            ext.put("err", "请求失败");
            ext.put("exception",e.toString());
            return AjaxResult.fail("关注失败",null,ext);
        }
    }
    @GetMapping(value = "/member/follow")
    public AjaxResult<Boolean> isFollow(int memberid, int projectid){
        Map<String,Object> ext = new HashMap<>();
        try {
            return AjaxResult.success("查询完成",projectService.queryFollowStatus(memberid,projectid),null);
        } catch (Exception e) {
            ext.put("err", "数据库发生的错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("查询数据失败",false, ext);
        }
    }
    @GetMapping("/return/{projectid}")
    public AjaxResult<List<TReturn>> getReturn(@PathVariable("projectid") int projectid){
        Map<String,Object> ext = new HashMap<>();

        try {
            List<TReturn> returns = projectService.getReturnByProjectId(projectid);
            return AjaxResult.success("查询成功", returns, null);
        } catch (Exception e) {
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("数据库访问错误", null, ext);
        }
    }

}
