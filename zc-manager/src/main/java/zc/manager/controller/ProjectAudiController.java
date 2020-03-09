package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;
import zc.commons.pojo.TProjectTemp;
import zc.manager.Page;
import zc.manager.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/serviceaudi/project")
public class ProjectAudiController {
    @Autowired
    private ProjectTempService projectTempService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ReturnTempService returnTempService;
    @Autowired
    private ReturnService returnService;
    @Autowired
    private TaskService taskService;
    @RequestMapping("/index")
    public String index(){
        return "audi/project_audi";
    }

    @ResponseBody
    @RequestMapping("/asyncRequestData")
    public AjaxResult<Page<TProjectTemp>> asyncRequestData(int pageno,int pagesize,String queryContent){
        AjaxResult<Page<TProjectTemp>> result;
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> ext = new HashMap<>();
        try {
            // 分页对象
            Page<TProjectTemp> projectTempPage = new Page<>();
            map.put("start", (pageno -1) * pagesize);
            map.put("size",  pagesize);
            List<TProjectTemp> projectTemps = projectTempService.queryData(map);
            projectTempPage.setDatas(projectTemps);
            // 总页数
            int totalsize = projectTempService.queryProjectCount();
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            projectTempPage.setTotalsize(totalsize);
            projectTempPage.setTotalno(totalno);
            projectTempPage.setPageno(pageno);
            // 获取项目发起人
            result = AjaxResult.success("加载完成",projectTempPage, ext);
        } catch (Exception e) {
            // 查询失败
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("加载失败",null, ext);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/checkpass")
    public zc.commons.bean.AjaxResult<Object> authPass(@RequestParam("projectid")int projectTempId ){
        zc.commons.bean.AjaxResult<Object> result ;
        Map<String,Object> ext = new HashMap<>();
        try {
            // 插入到正式项目表,并设置还分类表，回报表,并删除原来的临时记录
            projectService.addProjectByTempProject(projectTempId);
//            //
//            returnService.addRetunForProject(projectid);
            // 设置项目分类表，并删除原来的临时记录
            result = zc.commons.bean.AjaxResult.success("审核完成",null, null);
        } catch (Exception e) {
            ext.put("err", "数据错误");
            ext.put("exception", e.toString());
            result = zc.commons.bean.AjaxResult.fail("审核失败",null, ext);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/checkfail")
    public zc.commons.bean.AjaxResult<Object> authFail(int projectid,String feedback ){
        Map<String,Object> ext = new HashMap<>();
        try {

            return zc.commons.bean.AjaxResult.success("查询成功",null, ext);
        } catch (Exception e) {
            ext.put("err", "数据错误");
            ext.put("exception", e.toString());
            return zc.commons.bean.AjaxResult.fail("查询失败",null, ext);
        }
    }

    @ResponseBody
    @RequestMapping("/detail")
    public zc.commons.bean.AjaxResult<Object> projectDetail(int projectid ){
        Map<String,Object> ext = new HashMap<>();
        try {
            return zc.commons.bean.AjaxResult.success("查询成功",null, ext);
        } catch (Exception e) {
            ext.put("err", "数据错误");
            ext.put("exception", e.toString());
            return zc.commons.bean.AjaxResult.fail("查询失败",null, ext);
        }
    }

}
