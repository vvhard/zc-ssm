package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAcctType;
import zc.portal.util.Configuration;
import zc.portal.util.HttpClientUtil;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @RequestMapping("/personal_center")
    public String personalCenter(){
        return "personal_center";
    }
    @RequestMapping("/auth")
    @ResponseBody
    public String toAuthPage(){
        // 携带数据过去
        String result_json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/acct_type");
        // 转换成JSON对象  ,再转Java对象
        AjaxResult<List<TAcctType>> result =  JSON.toJavaObject(JSON.parseObject(result_json),AjaxResult.class);

        return "acct_type";
    }

}
