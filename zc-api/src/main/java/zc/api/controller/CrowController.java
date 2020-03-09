package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zc.manager.service.MemberService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TProjectTemp;
import zc.commons.pojo.TReturnTemp;
import zc.manager.service.ProjectTempService;
import zc.manager.service.ReturnTempService;
import zc.manager.service.TaskService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/crow")
public class CrowController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectTempService projectTempService;
    @Autowired
    private ReturnTempService returnTempService;
    @Autowired
    private TaskService taskService;
    @GetMapping(value = "/member/{loginacct}")
    public AjaxResult<Boolean> isAuthed(@PathVariable String loginacct){
        AjaxResult<Boolean> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            boolean r = memberService.isAuth(loginacct);
            result = AjaxResult.success("查询成功",r,null);
        }catch (Exception e){
            ext.put("err", "数据库查询错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
    @PostMapping(value = "/step1")
    public AjaxResult<TProjectTemp> submit(TProjectTemp projectTemp){
        AjaxResult<TProjectTemp> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            projectTempService.insert(projectTemp); // 插入成功后返回id
            // 创建任务
//            taskService.createTask(projectTemp.getId());
            ext.put("projectTempId", projectTemp.getId());
            result = AjaxResult.success("数据提交成功，请进行下一步",projectTemp,ext);
        }catch (Exception e){
            ext.put("err", "数据库查询错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("数据提交失败",null,ext);
        }
        return result;
    }
    @PostMapping(value="/rtn")
    public AjaxResult<TReturnTemp> addReturn(TReturnTemp returnTemp){
        AjaxResult<TReturnTemp> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            returnTempService.add(returnTemp);
            // 更新任务表
            taskService.addWithReturnIdAndProjectTempId(returnTemp.getProjectid(),returnTemp.getId());
            result = AjaxResult.success("添加成功",returnTemp,null);
        }catch (Exception e){
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("数据提交失败",null,ext);
        }
        return result;
    }
    @PostMapping(value = "/crow/pay_info")
    public AjaxResult<TProjectTemp> payInfo(int project_temp_id,String app_id,String app_private_key,String alipay_public_key){
        AjaxResult<TProjectTemp> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            projectTempService.addPayInfo(project_temp_id,app_id,app_private_key,alipay_public_key);
            // 设置待审核状态CHECKING
            projectTempService.checking(project_temp_id);
            result = AjaxResult.success("添加成功",null,null);
        } catch (Exception e) {
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("数据提交失败",null,ext);
        }
        return result;
    }
}
