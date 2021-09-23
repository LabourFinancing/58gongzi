package com.qucai.sample.sandpaybackstagefast.test.java.cn.com.sand.pay.test.gateway;

import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.http.HttpUtil;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.util.DynamicPropertyHelper;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.sandpay.scm.demo.FastPayApiUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description 支付
 * @Date:2019-08-21 13:43
 * @Version 1.0
 **/
public class FastPayApiPayTest {

    @Before
    public void init() throws Exception {
        ConfigurationManager.loadProperties(new String[]{"sandPayConfig"});
    }


    /**
     * 支付
     */
    @Test
    public void testAgCreateOrder() {
        try {
            String data = getSendData();
            System.out.println(data);
            //读取配置中公共URL
            String url = DynamicPropertyHelper.getStringProperty("sandpay.gateWay.url").get();
            //拼接本交易URL
            url += FastPayApiUtil.GATEWAY_FUNDPAY_URL;
            //创建HTTP辅助工具
            HttpUtil httpUtil = new HttpUtil();
            //通过辅助工具发送交易请求，并获取响应报文
            String result = httpUtil.sendGateWayPost(url, data, "");
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 组报文
     */
    public static String getSendData() {
        // 数据域
        JSONObject dataJson = new JSONObject();

        // 报文体
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("userId", "test11126");
        bodyJson.put("bid", "SDSMP10021170116000120210121055123522714");
        bodyJson.put("phoneNo", "");
        bodyJson.put("orderCode", FastPayApiUtil.getOrderCode());
        bodyJson.put("orderTime", FastPayApiUtil.getCurTimestampStr());
        bodyJson.put("smsCode", "");
        bodyJson.put("totalAmount", "000000000005");
        bodyJson.put("subject", "test subject");
        bodyJson.put("body", "test body");
        bodyJson.put("currencyCode", "156");
        String url = DynamicPropertyHelper.getStringProperty("sandpay.gateWay.backnotice.url").get();
        bodyJson.put("notifyUrl", url);
        bodyJson.put("clearCycle", "0");  // 0-T1, 1-T0, 2-D0
        bodyJson.put("extend", "TEST");
        // 报文头
        dataJson.put("head", FastPayApiUtil.getAgHeadJson("sandPay.fastPay.apiPay.pay"));
        dataJson.put("body", bodyJson);

        String returnString = dataJson.toJSONString();
        return returnString;
    }
}
