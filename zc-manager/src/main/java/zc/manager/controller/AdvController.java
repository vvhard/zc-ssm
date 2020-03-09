package zc.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TAdvertisement;
import zc.commons.util.Configuration;
import zc.manager.Page;
import zc.manager.UploadFile;
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
    public AjaxResult<Page<TAdvertisement>> asyncRequestData(int pageno, int pagesize){
        AjaxResult<Page<TAdvertisement>>  result;
        Map<String, Object> map = new HashMap<>();
        map.put("start", (pageno -1) * pagesize);
        map.put("size",  pagesize);
        try {
            List<TAdvertisement> advs = advServiceImpl.queryData(map);
            int totalsize = advServiceImpl.queryUserNums(map);
            int totalno = ((totalsize % pagesize) == 0)?(totalsize / pagesize):(totalsize / pagesize) + 1;
            Page<TAdvertisement> advPage = new Page<>();
            advPage.setDatas(advs);
            advPage.setPageno(pageno);
            advPage.setTotalno(totalno);
            advPage.setTotalsize(totalsize);
            return AjaxResult.success("查询成功",advPage, null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            return AjaxResult.fail("删除失败",null,ext);
        }
    }
    @RequestMapping("/getDetail")
    @ResponseBody
    public AjaxResult<TAdvertisement> getDetail(int id){
        AjaxResult<TAdvertisement>  result ;
        TAdvertisement adv = advServiceImpl.getDetailById(id);
        if(adv != null){
            result = AjaxResult.success("成功",adv, null);
        }else{
            result = AjaxResult.fail("失败",null, null);
        }
        return result;
    }
    @RequestMapping("/addAdv")
    @ResponseBody
    public AjaxResult<Object> getDetail(String name, String description, String url,MultipartFile file){
        AjaxResult<String> upload = UploadFile.upload(Configuration.remoteAddress + "/upload/adv/", file);
        if(upload.getCode() == 0){
            return zc.commons.bean.AjaxResult.fail("上传图片失败", null, null);
        }
        String path = upload.getContent();
        TAdvertisement advertisement = new TAdvertisement();
        advertisement.setName(name);
        advertisement.setIconpath(path);
        advertisement.setDescription(description);
        advertisement.setUrl(url);
        advertisement.setStatus("OFF");
        try {
            advServiceImpl.addAdv(advertisement);
            return AjaxResult.success("新增成功",advertisement,null);
        } catch (Exception e) {
           Map<String,Object> ext = new HashMap<>();
           ext.put("exception", e.toString());
           return AjaxResult.fail("新增失败",null,ext);
        }
    }
    @RequestMapping("/takeoff")
    @ResponseBody
    public AjaxResult<Object> takeoff(int id){

        try {
            advServiceImpl.changAdvStatus(id,"OFF");
            return AjaxResult.success("设置成功",null,null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            return AjaxResult.fail("设置失败",null,ext);
        }
    }
    @RequestMapping("/takeon")
    @ResponseBody
    public AjaxResult<Object> takeon(int id){
        try {
            int row = advServiceImpl.changAdvStatus(id,"ON");
            if(row != 0)
                return AjaxResult.success("设置成功",null,null);
            return AjaxResult.fail("设置失败,上线项目数已达到3个",null,null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            return AjaxResult.fail("设置失败",null,ext);
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult<Object> delete(int id){

        try {
            advServiceImpl.deleteAdvById(id);
            return AjaxResult.success("删除成功",null ,null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("exception", e.toString());
            return AjaxResult.fail("删除失败",null,ext);
        }
    }
}
