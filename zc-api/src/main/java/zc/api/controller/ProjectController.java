package zc.api.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zc.api.service.ProjectService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;

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
    public AjaxResult<TProject> projectDeatils(@PathVariable("id") int id){
        AjaxResult<TProject> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            TProject project = projectService.getProjectById(id);
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
}
