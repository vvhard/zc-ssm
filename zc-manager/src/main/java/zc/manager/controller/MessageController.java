package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TMessage;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/serviceman/msg")
public class MessageController {
    @Autowired
    private MessageService messageServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "serviceman/message";
    }

    @ResponseBody
    @RequestMapping("/asyncRequestData")
//    ,String queryContent
    public Object asyncRequestData(int pageno,int pagesize){
        AjaxResult result  = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageno -1) * pagesize);
            map.put("size",  pagesize);
//            map.put("queryContent", queryContent);
            List<TMessage> msgs = messageServiceImpl.queryWithCondition(map);
            // 总页数
            int totalsize = messageServiceImpl.queryNums(map);
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TMessage> msgPage = new Page<>();
            msgPage.setDatas(msgs);
            msgPage.setPageno(pageno);
            msgPage.setTotalno(totalno);
            msgPage.setTotalsize(totalsize);
            result.setData(msgPage);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }

}
