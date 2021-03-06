package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.commons.pojo.TAdvertisement;
import zc.manager.service.AdvService;
import zc.manager.service.ProjectService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TProject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AdvService advService;
    @RequestMapping("/recommand")
    public AjaxResult<List<TProject> > recommandProject(){
        AjaxResult<List<TProject> > result;
        // 暂时先将前三个项目当作推荐项目
        Map<String,Object> ext= new HashMap<>();
        try{
            List<TProject> projects = projectService.getRecommand();
            result = AjaxResult.success("数据加载完成",projects,null);
        }catch (Exception e){
            ext.put("err", "数据查询错误");
            ext.put("exception", e.toString());
            result = zc.commons.bean.AjaxResult.fail("数据加载失败", null, ext);
        }
        return result;

    }
    @GetMapping(value = "/category/{type}")
    public AjaxResult<List<TProject> > category(@PathVariable("type")String project_type){
        AjaxResult<List<TProject> > result;
        Map<String,Object> ext= new HashMap<>();
        try{
            System.out.println(project_type);
            List<TProject> projects = projectService.getIndexProject(project_type);
            // 目前项目数过少，可以先全选，不分类
//            List<TProject> projects = projectService.getProjects();
            result = AjaxResult.success("数据加载完成",projects,null);
        }catch (Exception e){
            ext.put("err", "数据查询错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("数据加载失败", null, ext);
        }
        return result;
    }
    @GetMapping(value = "/advInfo")
    public AjaxResult<List<TAdvertisement> > category(){
        List<TAdvertisement> advs ;
        Map<String,Object> ext= new HashMap<>();
        try{
            advs = advService.getShowAdv();
            return AjaxResult.success("数据加载完成",advs,null);
        }catch (Exception e){
            ext.put("err", "数据查询错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("数据加载失败", null, ext);
        }
    }
}
