package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TPermission;
import zc.commons.pojo.TRole;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.PermissionService;
import zc.manager.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleServiceImpl;
    @Autowired
    private PermissionService permissionServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "user/role";
    }
    @ResponseBody
    @RequestMapping("/asyncRequestData")
    public Object asyncRequestData(int pageno,int pagesize,String queryContent){
        AjaxResult result  = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageno -1) * pagesize);
            map.put("size",  pagesize);
            map.put("queryContent", queryContent);
            List<TRole> roles = roleServiceImpl.queryData(map);
            // 总页数
            int totalsize = roleServiceImpl.queryRoleNums(map);
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TRole> rolePage = new Page<>();
            rolePage.setDatas(roles);
            rolePage.setPageno(pageno);
            rolePage.setTotalno(totalno);
            rolePage.setTotalsize(totalsize);
            result.setData(rolePage);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(int id){
        AjaxResult result  = new AjaxResult();
        try {
            if( roleServiceImpl.deleteRoleById(id) == 1){
                result.setSuccess(true);
            }else{
                result.setSuccess(false);
            }
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteBatch")
    public Object deleteBatch(int[] roleid){
        AjaxResult result  = new AjaxResult();
        if(roleServiceImpl.deleteRoleBatch(roleid) != 0)
            result.setSuccess(true);
        else
            result.setSuccess(false);
        return result;
    }
    @ResponseBody
    @RequestMapping("/getOne")
    public Object getOne(int id){
        AjaxResult result  = new AjaxResult();
        try{
            TRole role = roleServiceImpl.getRoleById(id);
            result.setData(role);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/initAssign")
    public Object initAssign(int id){
        AjaxResult result  = new AjaxResult();

        return result;
    }
    @ResponseBody
    @RequestMapping("/doAssign")
    public Object doAssign(int roleid,int[] unassignroleids) {
        AjaxResult result  = new AjaxResult();

        return result;
    }
    @ResponseBody
    @RequestMapping("/unAssign")
    public Object unAssign(int roleid,int[] assignroleids) {
        AjaxResult result  = new AjaxResult();

        return result;
    }
    @ResponseBody
    @RequestMapping("/loadAssignData")
    public Object loadAssignData(int roleid) {
        // 获取所有的permission
        List<TPermission> all_list = permissionServiceImpl.getAllPermission();
        return all_list;
    }
    @ResponseBody
    @RequestMapping("/ckeckedPermission")
    public Object ckeckedPermission(int roleid) {
        return permissionServiceImpl.getRolePermissions(roleid);
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object add(TRole role) {
        AjaxResult result = new AjaxResult();
        try {
            roleServiceImpl.addRole(role);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object uppdate(Integer roleid,String name,String description) {
        AjaxResult result = new AjaxResult();
        try {
            roleServiceImpl.updateRoleById(roleid,name,description);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }
    
}
