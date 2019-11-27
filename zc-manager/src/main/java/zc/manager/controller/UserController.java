package zc.manager.controller;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TRole;
import zc.commons.pojo.TUser;
import zc.commons.pojo.TUserRole;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.RoleService;
import zc.manager.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RoleService roleServiceImpl;
    @RequestMapping("/index")
    public String index(){
        return "user/user";
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
            List<TUser> users = userServiceImpl.queryData(map);
            // 总页数
            int totalsize = userServiceImpl.queryUserNums(map);
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TUser> userPage = new Page<>();
            userPage.setDatas(users);
            userPage.setPageno(pageno);
            userPage.setTotalno(totalno);
            userPage.setTotalsize(totalsize);
            result.setData(userPage);
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
        if(userServiceImpl.deleteUser(id) == 1){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteBatch")
    public Object deleteBatch(int[] userid){
        AjaxResult result  = new AjaxResult();
        if(userServiceImpl.deleteUserBatch(userid) != 0)
            result.setSuccess(true);
        else
            result.setSuccess(false);
        return result;
    }
    @ResponseBody
    @RequestMapping("/getOne")
    public Object getOne(int id){
        AjaxResult result  = new AjaxResult();
        TUser user = userServiceImpl.getOne(id);
        if(user != null){

            result.setSuccess(true);
            result.setData(user);
        }
        else
            result.setSuccess(false);
        return result;
    }
    @ResponseBody
    @RequestMapping("/initAssign")
    public Object initAssign(int id){

        AjaxResult result  = new AjaxResult();
        List< List<TRole>> data = new ArrayList<>();
        try {
            // 用户已被分配的
            List<TRole> assign = roleServiceImpl.getUserRoles(id);
            // 用户未被分配的角色
            List<TRole> unassign = roleServiceImpl.getUnAssignRoles(id);
            data.add(assign);
            data.add(unassign);
            result.setData(data);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/doAssign")
    public Object doAssign(int userid,int[] unassignroleids) {
        AjaxResult result  = new AjaxResult();
        try {
            for(int roleid:unassignroleids) {
                TUserRole ur = new TUserRole();
                ur.setRoleid(roleid);
                ur.setUserid(userid);
                roleServiceImpl.assignToUser(ur);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/unAssign")
    public Object unAssign(int userid,int[] assignroleids) {
        AjaxResult result  = new AjaxResult();
        try {
            for(int roleid:assignroleids) {
                TUserRole ur = new TUserRole();
                ur.setRoleid(roleid);
                ur.setUserid(userid);
                roleServiceImpl.unAssignForUser(ur);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object add(String loginacct,String username,String email){
        AjaxResult result = new AjaxResult();
       TUser user  = new TUser();
       user.setLoginacct(loginacct);
       user.setUsername(username);
        user.setEmail(email);
        try{
            userServiceImpl.addUser(user);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(String loginacct,String username,String email){
        AjaxResult result = new AjaxResult();
        try {
            userServiceImpl.updateUser(loginacct,username,email);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

}
