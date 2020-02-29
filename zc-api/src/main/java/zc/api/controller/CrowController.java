package zc.api.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.api.service.MemberService;
import zc.commons.bean.AjaxResult;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/crow")
public class CrowController {
    @Autowired
    private MemberService memberService;
    @GetMapping(value = "/member/{loginacct}")
    public AjaxResult<Boolean> isAuthed(@PathVariable String loginacct){
        AjaxResult<Boolean> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            boolean r = memberService.isAuth(loginacct);
            result = AjaxResult.success("查询成功",r,null);
        }catch (Exception e){
            ext.put("err", "数据库查询错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
}
