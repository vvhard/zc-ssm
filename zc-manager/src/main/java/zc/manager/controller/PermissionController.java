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
    public Object addPermission(Integer pid,String permName,String permUrl,String permIcon) {
        AjaxResult result = new AjaxResult();
        // 带图标的添加以后做
        TPermission p = new TPermission();
        p.setName(permName);
        p.setUrl(permUrl);
        p.setPid(pid);
        p.setIcon(permIcon);
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

    @ResponseBody
    @RequestMapping("/edit")
    public Object getDetail(Integer id){
        AjaxResult result = new AjaxResult();
        try{
            TPermission permission = permissionServiceImpl.getOneById(id);
            if(permission != null){
                result.setSuccess(true);
                result.setData(permission);
            }else{
                result.setSuccess(false);
            }
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object savePermission(Integer permId,String permName,String permUrl,String permIcon) {
        AjaxResult result = new AjaxResult();
        // 带图标的添加以后做
        if(permissionServiceImpl.updatePermission(permId,permName,permUrl,permIcon) == 1) {
            result.setSuccess(true);
        }else
            result.setSuccess(false);
        return result;
    }
}
