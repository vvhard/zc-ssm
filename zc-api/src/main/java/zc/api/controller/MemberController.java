package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.util.MD5Util;
import zc.manager.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @RequestMapping(value = "/{id}")
    public AjaxResult<TMember> getMember(@PathVariable("id")int  id){
        try {

            return AjaxResult.success("获取成功",  memberService.getByMemberId(id),null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("err", "数据库访问出错");
            ext.put("excetion", e.toString());
            return AjaxResult.fail("获取失败", null,ext);
        }
    }
    @RequestMapping("/update")
    public AjaxResult<TMember> updateInfo(TMember member){
        try {
            memberService.update(member);
            return AjaxResult.success("更新成功", member,null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("err", "数据库访问出错");
            ext.put("excetion", e.toString());
            return AjaxResult.fail("更新失败", member,ext);
        }
    }
    @RequestMapping("/updatePassword")
    public AjaxResult<Object> updatePassword(String loginacct,String oldPassword,String newPassword){
        try {

            boolean r = memberService.updatePassword(loginacct,MD5Util.digest32(oldPassword),MD5Util.digest32(newPassword));
            if(r)
                return AjaxResult.success("修改成功", null,null);
            return AjaxResult.fail("旧密码错误,修改失败", null,null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("err", "数据库访问出错");
            ext.put("excetion", e.toString());
            return AjaxResult.fail("修改失败", null,null);
        }
    }
}
