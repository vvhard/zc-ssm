package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberCert;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/serviceaudi/realname")
public class AudiController {

    @Autowired
    private MemberService memberService;
    @RequestMapping("/index")
    public String index(){
        return "audi/realname_audi";
    }
    @ResponseBody
    @RequestMapping("/asyncRequestData")
    public Object asyncRequestData(int pageno,int pagesize,String queryContent){
        AjaxResult result  = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageno -1) * pagesize);
            map.put("size",  pagesize);
            List<TMember> members = memberService.queryData(map);
            // 总页数
            int totalsize = memberService.queryMemberCount();
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TMember> memberPage = new Page<>();
            memberPage.setTotalsize(totalsize);
            memberPage.setTotalno(totalno);
            memberPage.setPageno(pageno);
            memberPage.setDatas(members);
            result.setData(memberPage);
        } catch (Exception e) {
            // 查询失败
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        result.setSuccess(true);
        return result;
    }
    @ResponseBody
    @RequestMapping("/certPicPath")
    public zc.commons.bean.AjaxResult<List<TMemberCert>> getCertPic(int memberid ){
        zc.commons.bean.AjaxResult<List<TMemberCert>> result ;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TMemberCert> tmc = memberService.getCertByMemberId(memberid);
            List<String> certname = memberService.getCertNameByMemberId(memberid);
            ext.put("certname", certname);
            result = zc.commons.bean.AjaxResult.success("查询成功",tmc, ext);
        } catch (Exception e) {
            ext.put("err", "数据错误");
            ext.put("exception", e.toString());
            result = zc.commons.bean.AjaxResult.fail("查询失败",null, ext);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/checkpass")
    public zc.commons.bean.AjaxResult<Object> authPass(int memberid ){
        zc.commons.bean.AjaxResult<Object> result ;
        Map<String,Object> ext = new HashMap<>();
        try {
            memberService.passAuth(memberid);
            result = zc.commons.bean.AjaxResult.success("认证功",null, null);
        } catch (Exception e) {
            ext.put("err", "数据错误");
            ext.put("exception", e.toString());
            result = zc.commons.bean.AjaxResult.fail("认证失败",null, ext);
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("/checkfail")
    public zc.commons.bean.AjaxResult<Object> authFail(int memberid,String feedback ){
        Map<String,Object> ext = new HashMap<>();
        try {
            memberService.failAuth(memberid,feedback);
           return zc.commons.bean.AjaxResult.success("查询成功",null, ext);
        } catch (Exception e) {
            ext.put("err", "数据错误");
            ext.put("exception", e.toString());
            return zc.commons.bean.AjaxResult.fail("查询失败",null, ext);
        }
    }

}
