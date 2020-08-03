package com.fh.pay.controller;

import com.fh.common.ServerResponse;
import com.fh.sdk.MyConfig;
import com.fh.sdk.WXPay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pay")
public class PayController {

    @RequestMapping("createNative")
    public ServerResponse createNative(String orderNo) {
        try {
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "飞狐购物中心_1908b");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", orderNo);
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "192.168.13.33");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");


            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            if (!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信支付平台报错："+resp.get("err_code_des"));
            }
            if (!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信支付平台报错："+resp.get("return_msg"));
            }
            String code_url = resp.get("code_url");
            return ServerResponse.success(code_url);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error(e.getMessage());
        }


    }
}
