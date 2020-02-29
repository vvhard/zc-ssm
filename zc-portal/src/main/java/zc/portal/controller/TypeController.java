package zc.portal.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TType;
import zc.portal.util.Configuration;
import zc.portal.util.HttpClientUtil;

import java.util.List;

@Controller
public class TypeController {
    @RequestMapping("/types")
    @ResponseBody
    public AjaxResult<List<TType>> allTypes(){
        String json = HttpClientUtil.httpGetRequest(Configuration.remoteAddress + "/type/types");
        AjaxResult result = JSON.toJavaObject(JSON.parseObject(json), AjaxResult.class);
        return result;
    }
}
