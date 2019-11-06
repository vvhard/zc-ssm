package zc.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.manager.AjaxResult;

@Controller
@RequestMapping("/serviceman/proc")
public class ProcessController {

    @RequestMapping("/index")
    public String index(){
        return "serviceman/proc";
    }

    @ResponseBody
    @RequestMapping("/asyncRequestData")
    public Object asyncRequestData(int pageno,int pagesize,String queryContent){
        AjaxResult result  = new AjaxResult();
        return result;
    }

}
