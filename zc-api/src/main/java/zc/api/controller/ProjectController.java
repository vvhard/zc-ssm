package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.api.service.ProjectService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
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
}
