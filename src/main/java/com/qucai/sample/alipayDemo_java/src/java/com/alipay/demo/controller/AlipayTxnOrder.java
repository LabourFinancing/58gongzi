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

    public static Map<String, Object>  alipayCreateOrder(MobileEwalletDashboard mobileEwalletDashboard, AlipayFundTransOrderQueryModel alipayModel) throws AlipayApiException {
        Result<AlipayFundTransOrderQueryResponse> result = new Result<AlipayFundTransOrderQueryResponse>();
        JSONObject resp = JSONObject.parseObject(result.getErrorCode());
        Properties prop = AlipayConfig.getProperties();

        //初始化请求类
        AlipayFundTransOrderQueryRequest alipayRequest = new AlipayFundTransOrderQueryRequest();
        //添加demo请求标示，用于标记是demo发出
        alipayRequest.putOtherTextParam(AlipayConfig.ALIPAY_DEMO, AlipayConfig.ALIPAY_DEMO_VERSION);
        //设置业务参数，alipayModel为前端发送的请求信息，开发者需要根据实际情况填充此类
        alipayRequest.setBizModel(alipayModel);
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
        certAlipayRequest.setCertPath(""); //应用公钥证书路径（app_cert_path 文件绝对路径）
        certAlipayRequest.setAlipayPublicCertPath(""); //支付宝公钥证书文件路径（alipay_cert_path 文件绝对路径）
        certAlipayRequest.setRootCertPath("");  //支付宝CA根证书文件路径（alipay_root_cert_path 文件绝对路径）
        
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2021002158619784", prop.getProperty("RSA2_PRIVATE_KEY"), "json", "GBK", prop.getProperty("ALIPAY_PUBLIC_KEY"), "RSA2");
//        AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ProdName("Alipay Internal Transfer");
        Map<String, Object> rsAlipayTxn = new HashMap<>(); //uid支付宝用户唯一的标识2088开头的数字 spearsharp@163.com
        request.setBizContent("{" +
            "\"out_biz_no\":\"202108130001\"," +
            "\"trans_amount\":1.01," +
            "\"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
            "\"biz_scene\":\"DIRECT_TRANSFER\"," +
            "\"order_title\":\"PRODUCT_NAME\"," +
            "\"original_order_id\":\"20210620110075000006640020210813\"," +
            "\"payee_info\":{" +
            "\"identity\":\"18001869161\"," +
            "\"identity_type\":\"ALIPAY_LOGON_ID\"," +
            "\"name\":\"姚诚铭\"" +
            "    }," +
            "\"remark\":\"单笔转账\"," +
            "\"business_params\":\"{\\\"sub_biz_scene\\\":\\\"代发转账\\\"}\"," +
            "\"sign_data\":{" +
            "\"ori_sign\":\"EqHFP0z4a9iaQ1ep==\"," +
            "\"ori_sign_type\":\"RSA2\"," +
            "\"ori_char_set\":\"UTF-8\"," +
            "\"partner_id\":\"2088002082117160\"," +
            "\"pay_fund_order_id\":\"20210620110075000006640020210813\"," +
            "\"ori_app_id\":\"2021002158619784\"," +
            "\"ori_out_biz_no\":\"20210813000000001\"" +
            "    }" +
            "  }");
        try {
            AlipayFundTransUniTransferResponse alipayResponse = alipayClient.execute(request);
            if (alipayResponse.isSuccess()) {
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
