package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zc.commons.pojo.TPermission;
import zc.commons.pojo.TUser;
import zc.manager.constant.MyConstant;
import zc.manager.service.LoginService;
import zc.manager.service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginServiceImpl;
    @Autowired
    private PermissionService permissionServiceImpl;
    @RequestMapping("/login")
    public String login(@RequestParam(value = "loginacct") String acct,
                        @RequestParam(value = "userpswd") String password,
                        HttpServletRequest req){
        TUser user = loginServiceImpl.login(acct, password);
        if( user != null){
            req.getSession().setAttribute(MyConstant.LOGIN_USER, user);
            return "redirect:/main.html"; // 伪静态页面
        }else{
            return "redirect:/login.jsp"; //重定向，防止表单重复提交
        }
    }
    @RequestMapping("/forget")
    public String forgetPwd(){

        return "";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(MyConstant.LOGIN_USER); // 移除登陆的用户
        return "redirect:/login.jsp";
    }
    @RequestMapping("/main.html")
    public String toMain(HttpSession session){
        if(session.getAttribute(MyConstant.LOGIN_USER) == null){
            session.setAttribute(MyConstant.USER_UNLOGINED, "请先登陆!");
            return "redirect:/login.jsp";
        }
        // 构建菜单
        TUser user = (TUser) session.getAttribute(MyConstant.LOGIN_USER);
        List<TPermission> permissions = permissionServiceImpl.getUserPermissons(user);
        session.setAttribute(MyConstant.USER_MENUS, permissions);
        return "main";
    }
}
