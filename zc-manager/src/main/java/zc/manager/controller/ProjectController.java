package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TType;
import zc.manager.AjaxResult;
import zc.manager.Page;
import zc.manager.service.TypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/serviceman/project")
public class ProjectController {
    @Autowired
    private TypeService typeServiceImpl;
    @RequestMapping("/index")
    public String index(){
        return "serviceman/project_type";
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
            List<TType> types = typeServiceImpl.queryData(map);
            // 总页数
            int totalsize = typeServiceImpl.queryNums(map);
            // 总页数
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            // 分页对象
            Page<TType> typePage = new Page<>();
            typePage.setDatas(types);
            typePage.setPageno(pageno);
            typePage.setTotalno(totalno);
            typePage.setTotalsize(totalsize);
            result.setData(typePage);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            result.setSuccess(false);
        }
        result.setSuccess(true);
        return result;
    }
}
