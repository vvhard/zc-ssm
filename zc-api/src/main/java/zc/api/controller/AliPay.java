package zc.api.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AliPay {

//    private final String APP_ID = "应用的APPID";
//    private final String APP_PRIVATE_KEY = "生成的应用私钥";
//    private final String ALIPAY_PUBLIC_KEY = "支付宝公钥";
    private final String CHARSET = "UTF-8";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址,测试预留接口，实际需更改
    private final String NOTIFY_URL = "http://loaclhost:8082/zc_api/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://loaclhost:8082/zc_api/alipay/result";

    @RequestMapping("/pay")
    public void pay(String ordernum,int money,String name,String app_id,String app_private_key,
                    String alipay_public_key,HttpServletResponse httpResponse) throws IOException {
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, app_id, app_private_key, FORMAT, CHARSET, alipay_public_key, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL + "?alipay_public_key=" + alipay_public_key);
        request.setNotifyUrl(NOTIFY_URL);
        //商品描述，可空
        String body = "";
        // 请求参数集合
        request.setBizContent("{\"out_trade_no\":\""+ ordernum +"\","
                + "\"total_amount\":\""+ money +"\","
                + "\"subject\":\""+ name +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String payResult(String alipay_public_key,HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+total_amount);
            //支付成功，更改支付状态
            return "ok";//跳转付款成功页面
        }else{
            return "no";//跳转付款失败页面
        }
    }
}
