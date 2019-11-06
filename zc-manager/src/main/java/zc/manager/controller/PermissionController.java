package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TPermission;
import zc.manager.AjaxResult;
import zc.manager.service.PermissionService;

@Controller
@RequestMapping("/perm")
public class PermissionController {
    @Autowired
    private PermissionService permissionServiceImpl;
    @RequestMapping("/index")
    public String index(){
        return "user/permission";
    }
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        return permissionServiceImpl.getAllPermission();
    }
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(int id){
        AjaxResult result  = new AjaxResult();
        TPermission p = permissionServiceImpl.getOneById(id);
        if(p != null){
            result.setData(p);
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/save")
    public Object savePermission(TPermission p) {
        AjaxResult result = new AjaxResult();
        // 带图标的添加以后做

        if(permissionServiceImpl.updatePermission(p) == 1) {
            result.setSuccess(true);
        }else
            result.setSuccess(false);
        return result;
    }

    @ResponseBody
    @RequestMapping("/addPermission")
    public Object addPermission(String name,String url,int pid) {
        AjaxResult result = new AjaxResult();
        // 带图标的添加以后做
        TPermission p = new TPermission();
        p.setName(name);
        p.setUrl(url);
        p.setPid(pid);
        if(permissionServiceImpl.addPermission(p) == 1) {
            result.setSuccess(true);
        }else
            result.setSuccess(false);
        return result;
    }
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(int id) {
        AjaxResult result  = new AjaxResult();
        // 注意外键
        if(permissionServiceImpl.deletePermission(id) == 1) {
            result.setSuccess(true);
        }else
            result.setSuccess(false);
        return result;
    }
}
