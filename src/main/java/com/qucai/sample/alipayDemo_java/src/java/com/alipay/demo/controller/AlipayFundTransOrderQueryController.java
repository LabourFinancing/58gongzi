package com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.DemoBase;
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
public class AlipayFundTransOrderQueryController {
    

    @RequestMapping(value="/alipayFundTransOrderQuery.htm")
//    public String toHtml(HttpServletRequest request,ModelMap modelMap){
    public String toHtml(ModelMap modelMap){
        
        return "api/alipayFundTransOrderQuery";
        
    }

//    @RequestMapping(value="/alipayFundTransOrderQuery.json", method = RequestMethod.POST)
//    @ResponseBody
//    public static JSONObject doPost(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, AlipayFundTransOrderQueryModel alipayModel){
    public static JSONObject doPost( HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, AlipayFundTransOrderQueryModel alipayModel) throws Exception {
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
		AlipayClient alipayClient = DefaultAlipayClientFactory.getAlipayClient();
		try {
									//因为是接口服务，使用exexcute方法获取到返回值
			AlipayFundTransOrderQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()){
				System.out.println("调用成功");
                resp.put("CALLALIPAY","商户私钥格式正确，确认配置文件Alipay-Config.properties中所有都正确");
				//TODO 实际业务处理，开发者编写。可以通过alipayResponse.getXXX的形式获取到返回值
			} else {
				System.out.println("调用失败");
				System.out.println(alipayResponse);
                resp.put("CALLALIPAY-ERRCODE",alipayResponse.getErrorCode());
                resp.put("CALLALIPAY-STATUS",alipayResponse.getStatus());
                resp.put("CALLALIPAY-MSG",alipayResponse.getMsg());
                resp.put("CALLALIPAY-FAILREASON",alipayResponse.getFailReason());
                resp.put("CALLALIPAY-ORDERFEE",alipayResponse.getOrderFee());
                resp.put("CALLALIPAY-ORDERID",alipayResponse.getOrderId());
                resp.put("CALLALIPAY-BODY",alipayResponse.getBody());
                
                resp.put("CALLALIPAY","调用失败");
			}
			result.setSuccess(true);
			result.setValue(alipayResponse);
			return resp;
						
		} catch (AlipayApiException e) {
			e.printStackTrace();
		    if(e.getCause() instanceof java.security.spec.InvalidKeySpecException){
		        result.setMessage("商户私钥格式不正确，请确认配置文件Alipay-Config.properties中是否配置正确");
                resp.put("CALLALIPAY","商户私钥格式不正确，请确认配置文件Alipay-Config.properties中是否配置正确");
		        return resp;
		    }
		}

        return resp;
        
    }
}