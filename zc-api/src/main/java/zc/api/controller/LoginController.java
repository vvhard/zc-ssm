package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.manager.service.MemberService;
import zc.commons.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * restcontrolle, 返回的是json数据
 */
@RestController
public class LoginController {
    @Autowired
    private MemberService memberServiceImpl;
    @RequestMapping(value = "/login")
    public AjaxResult<TMember> login(String loginacct, String userpswd){
        AjaxResult<TMember> result ;
        // 如需返回额外信息，则使用map存储进行返回
        Map<String, Object> ext = new HashMap<>();
        try{
            // 处理参数，生成
            TMember member = memberServiceImpl.login(loginacct, MD5Util.digest32(userpswd)); // 登录
            if(member == null){
                // 如需返回额外信息，则使用map存储进行返回
                ext.put("err", "密码错误");
                ext.put("acct", loginacct);
                result = AjaxResult.fail("登录失败",null,ext); // 登录失败
            }else{
                result = AjaxResult.success("登录成功", member,null); // 登录成功
            }
        }catch (Exception e){
            ext.put("err", "访问数据库异常");
            ext.put("exception",e.toString());
            result = AjaxResult.fail("登录失败",null,ext); // 登录失败
        }
        return result;
    }
}
