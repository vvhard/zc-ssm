package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAcctType;
import zc.manager.service.AcctTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
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
            result = AjaxResult.success("查询成功", allAcctTypes, null);
        } catch (Exception e) {
            ext.put("err", e.toString());
            result = AjaxResult.fail("查询数据库失败",null, ext);
        }
        return result;
    }
}
