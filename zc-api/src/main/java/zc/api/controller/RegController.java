package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zc.api.util.ApiConstant;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.manager.service.MemberService;
import zc.commons.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegController {
    @Autowired
    private MemberService memberServiceImpl;
    @RequestMapping("/reg")
    @ResponseBody
    public AjaxResult<TMember> regist(String loginacct, String userpswd, String email){
        AjaxResult<TMember> result = new AjaxResult<>();
        // 将密码加密
        String encrypt_password = MD5Util.digest32(userpswd); // 32位，不加盐
        TMember member = new TMember();
        member.setLoginacct(loginacct);
        member.setUserpswd(encrypt_password);
        member.setEmail(email);
//        member.setTel(null); // 默认为空
        member.setAuthstatus(ApiConstant.AUTH_STATUS_NO);
        member.setUsertype(ApiConstant.MEMEBER_TYPE_PERSONAL);
        // 设置其他属性
        member.setUsername(loginacct); // 用户名默认与账号一样
        Map<String, Object> ext = new HashMap<>();
        try{
            member = memberServiceImpl.regist(member); // 若成功则，该对象的id属性自动设置，通过id字段判断是否注册成功
            if(member.getId() == null){
                ext.put("err", "邮箱或者账号已被注册");
                result = AjaxResult.fail("注册失败",member, ext);
            }else{
                result =   AjaxResult.success("注册成功", member,null);
            }
        }catch (Exception e){
            ext.put("err", "数据库操作发生错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("注册失败",null, ext);
        }
        return result;
    }
}
