package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.util.Configuration;
import zc.portal.util.Constant;
import zc.commons.util.HttpClientUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(String loginacct, String userpswd, HttpSession session){
        // 进行跨域请求
        // 带参数，使用get方法
        Map<String, Object> params = new HashMap<>();
        params.put("loginacct", loginacct);
        params.put("userpswd", userpswd);
        AjaxResult<TMember> result;
        // 发送请求
        try{
            // 返回结果为字符串
            String result_json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/login", params);
            // 根据结果对json字符串进行转换，得到原来的内容
            // 先转换为json对象,JSON.parseObject(result)
            result = JSON.toJavaObject(JSON.parseObject(result_json), AjaxResult.class);
            // 成功返回主页面
            if(result.getCode() == 1){
                // 登录成功
                // 先转为json字符串
                String content = JSON.toJSONString(result.getContent());
                // 解析为JSON对象，再将JSON对象转换为Java对象
                TMember member = JSON.toJavaObject(JSON.parseObject(content), TMember.class);
                session.setAttribute(Constant.PORTAL_LOGIN_USER, member);
                return "redirect:/index.jsp"; // 重定向，防止表单重复提交;
            }else{
                // 登录失败,携带相关信息返回
                session.setAttribute(Constant.PORTAL_LOGIN_FAIL_MSG, result.getMsg());
                session.setAttribute(Constant.PORTAL_LOGIN_FAIL_ACCT, result.getExt().get("acct")); // 查询数据库登录失败会返回空
                session.setAttribute(Constant.PORTAL_LOGIN_RESULT, result);
                return "redirect:/login.jsp" ; // 重定向，防止表单重复提交
                }
        }catch (Exception e){
            // 抛出异常则，留在当前页面
            session.setAttribute(Constant.PORTAL_LOGIN_FAIL_MSG, "请求登陆失败，请稍后再试!");
            return "redirect:/login.jsp" ; // 重定向，防止表单重复提交
        }
    }
    @RequestMapping("/logout")
    public Object logout(HttpSession session){
        // 暂不考虑通过跨域进行处理
        // 清空seesion里的内容
        if(session != null){
            session.invalidate(); // session失效
        }
        return "redirect:/index.jsp";
    }
}
