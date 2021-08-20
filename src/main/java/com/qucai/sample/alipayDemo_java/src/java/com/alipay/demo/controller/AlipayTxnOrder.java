package com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.DemoBase;
import com.qucai.sample.vo.MobileEwalletDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.config.AlipayConfig;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.config.DefaultAlipayClientFactory;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.entites.Result;

@Controller

public class AlipayTxnOrder {

    public static Map<String, Object>  alipayCreateOrder(MobileEwalletDashboard mobileEwalletDashboard) throws AlipayApiException {
        Result<AlipayFundTransOrderQueryResponse> result = new Result<AlipayFundTransOrderQueryResponse>();
        JSONObject resp = JSONObject.parseObject(result.getErrorCode());
        Properties prop = AlipayConfig.getProperties();

        //初始化请求类
        AlipayFundTransOrderQueryRequest alipayRequest = new AlipayFundTransOrderQueryRequest();
        //添加demo请求标示，用于标记是demo发出
        alipayRequest.putOtherTextParam(AlipayConfig.ALIPAY_DEMO, AlipayConfig.ALIPAY_DEMO_VERSION);
        //设置业务参数，alipayModel为前端发送的请求信息，开发者需要根据实际情况填充此类
//        alipayRequest.setBizModel(alipayModel);
        alipayRequest.setReturnUrl(prop.getProperty("RETURN_URL"));
        alipayRequest.setNotifyUrl(prop.getProperty("NOTIFY_URL"));
        
        //sdk请求客户端，已将配置信息初始化
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");  //gateway:支付宝网关（固定）https://openapi.alipay.com/gateway.do
        certAlipayRequest.setAppId(prop.getProperty("APP_ID"));  //APPID 即创建应用后生成,详情见创建应用并获取 APPID
        certAlipayRequest.setPrivateKey(prop.getProperty("RSA2_PRIVATE_KEY"));  //开发者应用私钥，由开发者自己生成
        certAlipayRequest.setFormat("json");  //参数返回格式，只支持 json 格式
        certAlipayRequest.setCharset(prop.getProperty("CHARSET"));  //请求和签名使用的字符编码格式，支持 GBK和 UTF-8
        certAlipayRequest.setSignType(prop.getProperty("SIGN_TYPE"));  //商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐商家使用 RSA2。   
        certAlipayRequest.setCertPath("/Users/project/58gongzi/src/main/resources/appCertPublicKey_H5.crt"); //应用公钥证书路径（app_cert_path 文件绝对路径）
        certAlipayRequest.setAlipayPublicCertPath("/Users/project/58gongzi/src/main/resources/alipayCertPublicKey_RSA2_H5.crt"); //支付宝公钥证书文件路径（alipay_cert_path 文件绝对路径）
        certAlipayRequest.setRootCertPath("/Users/project/58gongzi/src/main/resources/alipayRootCert_H5.crt");  //支付宝CA根证书文件路径（alipay_root_cert_path 文件绝对路径）
        
        AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ProdName("Alipay Internal Transfer");
        Map<String, Object> rsAlipayTxn = new HashMap<>(); //uid支付宝用户唯一的标识2088开头的数字 spearsharp@163.com
        String paymentID = DemoBase.getOrderCode();
        mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(paymentID);
        
        request.setBizContent("{" +
            "\"out_biz_no\":\"202108180001\"," +  //唯一订单号
            "\"trans_amount\":1.00," +
            "\"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," + 
            "\"biz_scene\":\"DIRECT_TRANSFER\"," +
            "\"order_title\":\"202108代发\"," +    // product Name
            "\"payee_info\":{" +
            "\"identity\":\"2088002082117160\"," +   // alipay userid
            "\"identity_type\":\"ALIPAY_USER_ID\"," + 
            "\"name\":\"姚诚铭\"," +   // receiver Name
            "      }," +
            "\"remark\":\"单笔转账\"," +
            "\"business_params\":\"{\\\"payer_show_name\\\":\\\"高孚信科\\\"}\"," +
            "  }");
        try {
            AlipayFundTransUniTransferResponse alipayResponse = alipayClient.certificateExecute(request);
            if (alipayResponse.isSuccess()) {
                rsAlipayTxn.put("Msg","Alipay intern transmit Succ");
                System.out.println("调用成功");
            } else {
                rsAlipayTxn.put("errMsg", "rsMobileEwalletTxn Alipay internal Transaction failed");
                System.out.println("调用失败");
                System.out.println(alipayResponse);
                rsAlipayTxn.put("CALLALIPAY-ERRCODE",alipayResponse.getErrorCode());
                rsAlipayTxn.put("CALLALIPAY-STATUS",alipayResponse.getStatus());
                rsAlipayTxn.put("CALLALIPAY-MSG",alipayResponse.getMsg());
                rsAlipayTxn.put("CALLALIPAY-FAILREASON",alipayResponse.getPayFundOrderId());
                rsAlipayTxn.put("CALLALIPAY-ORDERFEE",alipayResponse.getTransDate());
                rsAlipayTxn.put("CALLALIPAY-ORDERID",alipayResponse.getOrderId());
                rsAlipayTxn.put("CALLALIPAY-BODY",alipayResponse.getBody());
                return rsAlipayTxn;
            }
        }catch (AlipayApiException e) {
            e.printStackTrace();
            if(e.getCause() instanceof java.security.spec.InvalidKeySpecException){
                rsAlipayTxn.put("errMsg","商户私钥格式不正确，请确认配置文件Alipay-Config.properties中是否配置正确");
                rsAlipayTxn.put("CALLALIPAY","商户私钥格式不正确，请确认配置文件Alipay-Config.properties中是否配置正确");
                return rsAlipayTxn;
            }
        }
        return rsAlipayTxn;
    }
}
