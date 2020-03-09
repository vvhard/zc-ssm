package zc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.commons.bean.MyOrder;
import zc.commons.bean.MySupport;
import zc.commons.bean.AjaxResult;
import zc.commons.pojo.TOrder;
import zc.manager.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/support")
    public AjaxResult<List<MySupport>> getSupports(int memberid, int pageno, int pagesize, String status){
        AjaxResult<List<MySupport>> result;
        Map<String,Object> map = new HashMap<>();
        map.put("memberid", memberid);
        map.put("start", (pageno-1)*pagesize);
        map.put("size", pagesize);
        map.put("status", status);
        Map<String,Object> ext = new HashMap<>();
        try {
            List<MySupport> mySupports = orderService.getMySupports(map);
            ext.put("totalsize", orderService.getMySupportCount(map));
            result = AjaxResult.success("查询成功",mySupports,ext);
        } catch (Exception e) {
            ext.put("err", "数据库查询错误");
            ext.put("exception", e.toString());
           result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
    @GetMapping(value = "/{ordernum}")
    public AjaxResult<MyOrder> getOrder(@PathVariable("ordernum") String ordernum){
        AjaxResult<MyOrder> result;
        Map<String,Object> map = new HashMap<>();

        Map<String,Object> ext = new HashMap<>();
        try {
            MyOrder order = orderService.getOrderByOrderNum(ordernum);
            result = AjaxResult.success("查询成功",order,ext);
        } catch (Exception e) {
            ext.put("err", "数据库查询错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
    @GetMapping(value = "/delete/{ordernum}")
    public AjaxResult<MyOrder> deleteOrder(@PathVariable("ordernum") String ordernum){
        AjaxResult<MyOrder> result;

        Map<String,Object> ext = new HashMap<>();
        try {
            MyOrder order = orderService.delOrderByOrderNum(ordernum);
            result = AjaxResult.success("查询成功",null,null);
        } catch (Exception e) {
            ext.put("err", "数据库查询错误");
            ext.put("exception", e.toString());
            result = AjaxResult.fail("查询失败",null,ext);
        }
        return result;
    }
}
