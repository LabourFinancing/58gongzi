package com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.sandpay.scm.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.DemoBase;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.encrypt.CryptoUtil;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.MobileEwalletDashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付下单报文签名（仅供参考）
 *
 * @author sandpay
 */
public class BindCardServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BindCardServlet.class);

    private static final long serialVersionUID = 1L;

    /**
     * 加载公私钥并初始化（仅需一次）
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConfigurationManager.loadProperties(new String[]{"sandPayConfig"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 签约报文签名
     *
     * @param response
     * @param response
     */
    public static JSONObject cardbind(HttpServletRequest request, HttpServletResponse response,MobileEwalletDashboard mobileEwalletDashboard) throws ServletException {
        BindCardServlet load = new BindCardServlet();
        load.init();


//        Map<String, String[]> params = request.getParameterMap();
//        Map<String, String> map = new HashMap<String, String>();
//        for (String key : params.keySet()) {
//            String[] values = params.get(key);
//            if (values.length > 0) {
//                map.put(key, values[0]);
//            }
//        }

        String TxnID= Tool.PayId();
        StringBuffer retURL = new StringBuffer();
        String method = "paymentreturn",cardNo=null,merchantID="S2135052",nowDate = DemoBase.getNextDayTime();
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(TxnID);
        if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard() != null){
            cardNo = mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard();
        }
        if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard() != null){
            cardNo = mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard();
        }
        String retrul = String.valueOf(retURL.append(nowDate).append("applyBindCard"));
     // 组后台报文
        JSONObject head = new JSONObject();
        head.put("version", "1.0");
        head.put("method", "sandPay.fastPay.apiPay.applyBindCard");
        head.put("productId", "00000018");
        head.put("accessType", "1");
        head.put("mid", "S2135052");
        head.put("plMid", "");
        head.put("channelType", "07");
        head.put("reqTime", nowDate);

        JSONObject body = new JSONObject();
        body.put("userId", merchantID);
        body.put("applyNo", mobileEwalletDashboard.getT_mobilePersonalEwallet_TxnID());
        body.put("cardNo", cardNo);
        body.put("userName", mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierName());
        body.put("phoneNo", mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierMobile());
        body.put("certificateType", "01");
        body.put("certificateNo", mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID());
        body.put("creditFlag", "1");
        body.put("extend", "");
        
/*
        {
            "charset":"utf-8",
            "signType":"01",
            "data":"{
            "head":{
            "version":"1.0",
                "method":"sandPay.fastPay.apiPay.applyBindCard",
                "productId":"00000018",
                "accessType":"1",
                "mid":"S7645439",
                "channelType":"07",
                "reqTime":"20191021183301"
        },
            "body":{
            "userId":"00000000001",
                "applyNo":"20191021183301applyBindCard",
                "cardNo":"622848xxxxxxxxxxxxx",
                "userName":"张三",
                "phoneNo":"131XXXXXXXX",
                "certificateType":"01",
                "certificateNo":"411722197xxxxxxxxx",
                "creditFlag":"1",
                "extend":""
        }
        }",
        "sign":"RfCQgqeiohoiqrhjijq0lpnYa/FDqacOcFA9Xa3LqLG25lbpOrP+QdpqKqerihijhiMwq"
    }
    */
        JSONObject data = new JSONObject();
        data.put("head", head);
        data.put("body", body);
        data.put("charset", "utf-8");
        data.put("signType", "01");
        data.put("sign", "RfCQgqeiohoiqrhjijq0lpnYa/FDqacOcFA9Xa3LqLG25lbpOrP+QdpqKqerihijhiMwq");

        try {
            // 报文签名
            String reqSign = CryptoUtil.digitalSign(data.toJSONString());

            JSONObject respJson = new JSONObject();
            respJson.put("data", JSON.toJSONString(data));
            respJson.put("sign", reqSign);
            response.getOutputStream().write(respJson.toJSONString().getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error("CashierPayServlet post error <<<", e);
            data.put("sandpayCardBind","sandpayCardBind faied");
        }finally {
            data.put("sandpayCardBind","sandpayCardBind successful");
        }
        return data;
    }

}
