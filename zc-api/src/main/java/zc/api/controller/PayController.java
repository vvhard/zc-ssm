package zc.api.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zc.commons.bean.AjaxResult;
import zc.commons.bean.MyProject;
import zc.commons.bean.ProjectPayInfo;
import zc.commons.pojo.TMemberAddress;
import zc.commons.pojo.TOrder;
import zc.commons.pojo.TReturn;
import zc.commons.util.Configuration;
import zc.commons.util.DateUtil;
import zc.commons.util.HttpClientUtil;
import zc.manager.service.MemberService;
import zc.manager.service.OrderService;
import zc.manager.service.ProjectService;
import zc.manager.service.ReturnService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ReturnService returnService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/payInfo")
    public AjaxResult<Object> getPayInfo(int projectid, int returnid,int memberid){
        Map<String,Object> ext = new HashMap<>();
        try {
            MyProject project = projectService.getProjectById(projectid);
            List<TMemberAddress> address = memberService.getAddress(memberid);
            TReturn rtn = returnService.getReturn(returnid);
            ext.put("project", project);
            ext.put("rtn", rtn);
            ext.put("address", address);
            return AjaxResult.success("获取完成", null, ext);
        }catch (Exception e){
            ext.put("err", "数据访问出错");
            ext.put("exception", e.toString());
            return AjaxResult.fail("获取数据失败", null, ext);
        }
    }
    @PostMapping("/addr")
    public AjaxResult<TMemberAddress> addAddress(int memberid,String name,String tel,String address){

        TMemberAddress memberAddress = new TMemberAddress();
        memberAddress.setAddress(address);
        memberAddress.setMemberid(memberid);
        memberAddress.setName(name);
        memberAddress.setTel(tel);
        try {
            memberService.addAddress(memberAddress);
            return AjaxResult.success("添加成功",memberAddress , null);
        } catch (Exception e) {
            Map<String,Object> ext = new HashMap<>();
            ext.put("err", "数据访问出错");
            ext.put("exception", e.toString());
            return AjaxResult.fail("添加失败", null, ext);
        }
    }
    @PostMapping("/submit")
    public AjaxResult<TOrder> pay(int projectid,int returnid,int price,int count,int totalmoney,
                                  int memberid,int addressid,String remark){
        TMemberAddress memberAddress = null;
        TReturn rtn = null;
        Map<String,Object> ext = new HashMap<>();
        try {
            memberAddress = memberService.getAddressByAddressId(addressid);
            rtn = projectService.getReturnByReturnId(returnid);
        } catch (Exception e) {
            ext.put("err", "数据访问出错");
            ext.put("exception", e.toString());
            return AjaxResult.fail("获取数据失败", null, ext);
        }
        TOrder order = new TOrder();
        order.setMemberid(memberid);
        order.setProjectid(projectid);
        order.setReturnid(returnid);
        // 五位随机数+日期充当订单号(int)(Math.random() * 10000)+DateUtil.currentTime()
        String ordernum = String.valueOf(new Date().getTime())+(int)(Math.random() * 10000);
        order.setOrdernum(ordernum);
        order.setAddress( memberAddress.getAddress());
        order.setTel(memberAddress.getTel());
        order.setReceiptname(memberAddress.getName());
        // 创建时间
        order.setCreatedate(DateUtil.currentTime());
        order.setCount(count);
        order.setMoney(totalmoney);
        order.setPrice(rtn.getSupportmoney());
        order.setRemark(remark);
        order.setStatus("Y"); // 默认都成功
        order.setInvoice("N");
        order.setInvoictitle(null);
        try {
            // 调用支付宝
//          ProjectPayInfo payinfo = projectService.getProjectPayInfo(projectid);
//          Map<String,Object> params = new HashMap<>();
//          params.put("ordernum", order.getOrdernum()); // 订单号
//          params.put("money", order.getMoney()); // 金额
//          params.put("name", rtn.getContent());
//          params.put("app_id", payinfo.getAppid());
//          params.put("app_private_key", payinfo.getAppprvatekey());
//          params.put("alipay_public_key", payinfo.getAlipaypublickey());
//          HttpClientUtil.httpPostRequest(Configuration.remoteAddress + "/alipay/pay", params);
            // 插入数据库
            orderService.addOrder(order);
            return AjaxResult.success("付款成功", order, ext);
        } catch (Exception e) {
            ext.put("err", "付款请求失败");
            ext.put("exception", e.toString());
            return AjaxResult.fail("付款失败", null, ext);
        }
    }




}
