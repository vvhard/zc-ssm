package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TAdvertisement;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.AdvService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/serviceman/adv")
@Controller
public class AdvController {
    @Autowired
    private AdvService advServiceImpl;
    @RequestMapping("/index")
    public String index(){
        return "serviceman/adv";
    }
    @ResponseBody
    @RequestMapping("/asyncRequestData")
    public Object asyncRequestData(int pageno,int pagesize,String queryContent){
        AjaxResult result  = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageno -1) * pagesize);
            map.put("size",  pagesize);
            map.put("queryContent", queryContent);
            List<TAdvertisement> advs = advServiceImpl.queryData(map);
            // 总页数
            int totalsize = advServiceImpl.queryUserNums(map);
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TAdvertisement> advPage = new Page<>();
            advPage.setDatas(advs);
            advPage.setPageno(pageno);
            advPage.setTotalno(totalno);
            advPage.setTotalsize(totalsize);
            result.setData(advPage);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/getDetail")
    @ResponseBody
    public Object getDetail(int id){
        AjaxResult result = new AjaxResult();
        TAdvertisement adv = advServiceImpl.getDetailById(id);
        if(adv != null){
            result.setData(adv);
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }
}
