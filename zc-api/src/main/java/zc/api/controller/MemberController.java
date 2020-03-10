package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberAddress;
import zc.commons.util.MD5Util;
import zc.manager.service.MemberService;

import java.util.HashMap;
import java.util.List;
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


    @GetMapping("/{memberid}/address")
    public AjaxResult<List<TMemberAddress>> getAddress(@PathVariable("memberid") int memberid){
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TMemberAddress> returns = memberService.getAddress(memberid);
            return AjaxResult.success("查询成功", returns, null);
        } catch (Exception e) {
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("数据库访问错误", null, ext);
        }
    }

    @PostMapping("/{memberid}/address/add")
    public AjaxResult<TMemberAddress> getAddress(@PathVariable("memberid") int memberid,String name,
                                                 String tel,String address){
        Map<String,Object> ext = new HashMap<>();
        try {
            TMemberAddress memberAddress = new TMemberAddress();
            memberAddress.setAddress(address);
            memberAddress.setMemberid(memberid);
            memberAddress.setName(name);
            memberAddress.setTel(tel);
            memberService.addAddress(memberAddress);
            return AjaxResult.success("添加成功", memberAddress, null);
        } catch (Exception e) {
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("数据库访问错误", null, ext);
        }
    }
    @PostMapping("/address/delete/{addressid}")
    public AjaxResult<Object> deleteAddress(@PathVariable("addressid") int addressid){
        Map<String,Object> ext = new HashMap<>();
        try {
            memberService.delteAddress(addressid);
            return AjaxResult.success("查询成功", null, null);
        } catch (Exception e) {
            ext.put("err", "数据库错误");
            ext.put("exception", e.toString());
            return AjaxResult.fail("数据库访问错误", null, ext);
        }
    }

}
