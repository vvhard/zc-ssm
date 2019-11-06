package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TCert;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.CertService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/serviceman/cert")
public class CertController {
    @Autowired
    private CertService certServiceImpl;
    @RequestMapping("/index")
    public String index(){
        return "serviceman/cert";
    }
    @RequestMapping("/asyncRequestData")
    @ResponseBody
    public Object asyncRequestData(int pageno,int pagesize,String queryContent){
        AjaxResult result = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageno -1) * pagesize);
            map.put("size",  pagesize);
            map.put("queryContent", queryContent);
            List<TCert> certs = certServiceImpl.queryData(map);
            // 总页数
            int totalsize = certServiceImpl.queryUserNums(map);
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TCert> certPage = new Page<>();
            certPage.setDatas(certs);
            certPage.setPageno(pageno);
            certPage.setTotalno(totalno);
            certPage.setTotalsize(totalsize);
            result.setData(certPage);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(int id){
        AjaxResult result = new AjaxResult();
        return result;
    }

    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Object deleteBatch(int id){
        AjaxResult result = new AjaxResult();
        return result;
    }
}
