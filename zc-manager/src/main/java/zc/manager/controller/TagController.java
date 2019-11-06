package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.commons.pojo.TTag;
import zc.manager.AjaxResult;
import zc.manager.service.TagService;

@Controller
@RequestMapping("/serviceman/tag")
public class TagController {
    @Autowired
    private TagService tagServiceImpl;
    @RequestMapping("/index")
    public String index(){
        return "serviceman/tag";
    }
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
//        AjaxResult result = new AjaxResult();
        return tagServiceImpl.getAllTags();
    }
    @RequestMapping("/getDetail")
    @ResponseBody
    public Object getDetail(int id){
        AjaxResult result = new AjaxResult();

        TTag tag = tagServiceImpl.getTagDetailById(id);
        if(tag != null){
            result.setData(tag);
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }
}
