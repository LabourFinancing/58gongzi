package com.qucai.sample.sandpaybackstagefast.test.java.cn.com.sand.pay.test.gateway;

import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.http.HttpUtil;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.util.DynamicPropertyHelper;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.sandpay.scm.demo.FastPayApiUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 * 确认绑卡接口DEMO（仅供参考）
 * @author sandpay
 */
public class FastPayConfirmBindCardTest {
    @Before
    public void init() throws Exception {
        ConfigurationManager.loadProperties(new String[]{"sandPayConfig"});
    }


    /**
     * 确认绑卡
     */
    @Test
    public void testAgCreateOrder() {
        try {
            String data = getSendData();
            System.out.println(data);
            //读取配置中公共URL
            String url = DynamicPropertyHelper.getStringProperty("sandpay.gateWay.url").get();
            //拼接本交易URL
            url += FastPayApiUtil.GATEWAY_QUEREN_URL;
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
        bodyJson.put("sdMsgNo", "0121175032900023");
        bodyJson.put("phoneNo", "17621710000");
        bodyJson.put("smsCode", "674363");
        bodyJson.put("notifyUrl", "http://www.baidu.com");
        bodyJson.put("extend", "");
        // 报文头
        dataJson.put("head", FastPayApiUtil.getAgHeadJson("sandPay.fastPay.apiPay.confirmBindCard"));
        dataJson.put("body", bodyJson);

        String returnString = dataJson.toJSONString();
        return returnString;
    }
}

