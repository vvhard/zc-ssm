package zc.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.portal.AjaxResult;

@Controller
public class RegController {
    @RequestMapping("/reg")
    public String index(){
        return "reg";
    }

    /**
     * 注册
     * @return
     */
    @ResponseBody
    @RequestMapping("")
    public Object reg(){
        AjaxResult result = new AjaxResult();
        return result;
    }
    /**
     * 发送邮件
     * @return
     */
    public Object sendEmail(){
        AjaxResult result = new AjaxResult();
        return result;
    }
}
