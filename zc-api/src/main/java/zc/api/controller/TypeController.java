package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TProject;
import zc.commons.pojo.TType;
import zc.manager.service.TypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping(value = "/types")
    public AjaxResult<List<TType>> allTypes(){
        AjaxResult<List<TType>> result;
        Map<String,Object> ext = new HashMap<>();
        try {
            List<TType> types = typeService.getTypes();
            if(types != null){
                result = AjaxResult.success("查询成功",types, null);
            }else{
                result = AjaxResult.fail("查询失败，项目不存在",null, null);
            }
        } catch (Exception e) {
            ext.put("err", "数据库查询失败");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("获取失败", null, ext);
        }
        return  result;
    }
}
