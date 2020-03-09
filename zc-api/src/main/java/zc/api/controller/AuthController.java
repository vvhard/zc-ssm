package zc.api.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zc.manager.service.MemberService;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;
import zc.commons.pojo.TMember;
import zc.manager.service.AcctTypeService;

import javax.servlet.http.HttpSession;
import java.beans.Transient;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private AcctTypeService acctTypeServiceImpl; // 不能依赖注入可能是配置文件没有配置扫描其他模块的包
    // @RequestMapping(value = "/",method = RequestMethod.GET)
    // 与上述注解等价,只拦截get请求
    @GetMapping(value = "/acct_type")
    public  AjaxResult<List<TAcctType>> getAcctTypes(){
        AjaxResult<List<TAcctType>> result ;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TAcctType> allAcctTypes = acctTypeServiceImpl.getAllAcctTypes();
            for(TAcctType type: allAcctTypes){
                type.setCerts(acctTypeServiceImpl.getAcctCertsByName(type.getName()));
            }
            result = AjaxResult.success("查询成功", allAcctTypes, null);
        } catch (Exception e) {
            ext.put("err", e.toString());
            result = AjaxResult.fail("查询数据库失败",null, ext);
        }
        return result;
    }
    @GetMapping(value = "/status/{id}")
    public  AjaxResult<TMember> getStatus(@PathVariable("id")int memberid){
        AjaxResult<TMember> result ;
        Map<String,Object> ext = new HashMap<>();
        try{
            TMember member = memberService.getByMemberId(memberid);
            result = AjaxResult.success("查询成功",member , null);
        }catch (Exception e){
            ext.put("err", "查询数据库出错");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }

    @GetMapping(value = "/{acct_type}")
    public  AjaxResult<List<TCert>> getCerts(@PathVariable("acct_type")String acct_type){
        AjaxResult<List<TCert>> result ;
        Map<String,Object> ext = new HashMap<>();
        try{
            List<TCert> certs = acctTypeServiceImpl.getAcctCertsByName(acct_type);
            result = AjaxResult.success("查询成功", certs, null);
        }catch (Exception e){
            ext.put("err", "查询数据库出错");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
    @RequestMapping("/submit")
    @ResponseBody
    @Transactional
    public AjaxResult<TMember> uploadInfo(String file, String loginacct, String tel, String realname, String cardnum,
                                          String accttype, String validateCode, String certids, HttpSession session) {
        AjaxResult<TMember> result;
        Map<String, Object> ext = new HashMap<>();
        List<String> files = JSON.parseArray(file, String.class);
        List<Integer> certid = JSON.parseArray(certids, Integer.class);
        if ((files != null || files.size() > 0) && (certid!=null || certid.size() >0)) {
            try{
                // 形成新的Member
                memberService.submitAuthInfoWithLoginacct(loginacct,realname,tel,cardnum,accttype); //更新信息
                int memberId = memberService.queryId(loginacct);
                memberService.updateMemberCert(memberId,certid,files); // 保存带t_member_cert表，list 内容为tmc
                result = AjaxResult.success("信息提交成功,等待人工审核", null, null);
            }catch (Exception e){
                ext.put("err", "数据库操作失败");
                ext.put("exception", e.toString());
                result = AjaxResult.fail("信息提交及图片上传失败", null, ext);
            }
            return result;
        }else{
            ext.put("err", "没有图片");
            result = AjaxResult.fail("信息提交及图片上传失败", null, ext);
            return result;
        }
    }
}
