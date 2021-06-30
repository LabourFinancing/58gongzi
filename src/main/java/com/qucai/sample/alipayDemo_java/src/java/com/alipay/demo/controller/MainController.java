package com.alipay.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.demo.entites.ApiInfoModel;
import com.alipay.demo.entites.ApiParamModel;

@Controller
public class MainController {

    @RequestMapping(value="/main.htm")
    public String main(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		return "api/main";
    }
    
    @RequestMapping(value="/getApiInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public Object getApiInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        List<ApiInfoModel> list = new ArrayList<ApiInfoModel>();
        
		ApiInfoModel alipayFundTransToaccountTransferInfoModel = new ApiInfoModel();
		list.add(alipayFundTransToaccountTransferInfoModel);
		alipayFundTransToaccountTransferInfoModel.setApiName("alipay.fund.trans.toaccount.transfer");
		alipayFundTransToaccountTransferInfoModel.setApiZhName("单笔转账到支付宝账户接口");
		alipayFundTransToaccountTransferInfoModel.setInvokeType(ApiInfoModel.INVOKE_TYPE_REQUEST);
		List<ApiParamModel> alipayFundTransToaccountTransferApiInParamChilds = new ArrayList<ApiParamModel>();
		alipayFundTransToaccountTransferInfoModel.setApiInParam(alipayFundTransToaccountTransferApiInParamChilds);
        ApiParamModel alipayFundTransToaccountTransferApiInParam_0 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_0);
        alipayFundTransToaccountTransferApiInParam_0.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_0.setTitle("商户转账唯一订单号");
        alipayFundTransToaccountTransferApiInParam_0.setDesc("发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。  不同来源方给出的ID可以重复，同一个来源方必须保证其ID的唯一性。  只支持半角英文、数字，及“-”、“_”。");
        alipayFundTransToaccountTransferApiInParam_0.setDescription("商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。  不同来源方给出的ID可以重复，同一个来源方必须保证其ID的唯一性。  只支持半角英文、数字，及“-”、“_”。");
        alipayFundTransToaccountTransferApiInParam_0.setIsMust(1);
        alipayFundTransToaccountTransferApiInParam_0.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_0.setFullParamName("outBizNo");
        alipayFundTransToaccountTransferApiInParam_0.setEnName("out_biz_no");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_1 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_1);
        alipayFundTransToaccountTransferApiInParam_1.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_1.setTitle("收款方账户类型");
        alipayFundTransToaccountTransferApiInParam_1.setDesc("可取值：  1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。  2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。");
        alipayFundTransToaccountTransferApiInParam_1.setDescription("收款方账户类型。可取值：  1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。  2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。");
        alipayFundTransToaccountTransferApiInParam_1.setIsMust(1);
        alipayFundTransToaccountTransferApiInParam_1.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_1.setFullParamName("payeeType");
        alipayFundTransToaccountTransferApiInParam_1.setEnName("payee_type");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_2 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_2);
        alipayFundTransToaccountTransferApiInParam_2.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_2.setTitle("收款方账户");
        alipayFundTransToaccountTransferApiInParam_2.setDesc("与payee_type配合使用。付款方和收款方不能是同一个账户。");
        alipayFundTransToaccountTransferApiInParam_2.setDescription("收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。");
        alipayFundTransToaccountTransferApiInParam_2.setIsMust(1);
        alipayFundTransToaccountTransferApiInParam_2.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_2.setFullParamName("payeeAccount");
        alipayFundTransToaccountTransferApiInParam_2.setEnName("payee_account");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_3 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_3);
        alipayFundTransToaccountTransferApiInParam_3.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_3.setTitle("转账金额");
        alipayFundTransToaccountTransferApiInParam_3.setDesc("单位：元。  只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。   最大转账金额以实际签约的限额为准。");
        alipayFundTransToaccountTransferApiInParam_3.setDescription("转账金额，单位：元。  只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。   最大转账金额以实际签约的限额为准。");
        alipayFundTransToaccountTransferApiInParam_3.setIsMust(1);
        alipayFundTransToaccountTransferApiInParam_3.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_3.setFullParamName("amount");
        alipayFundTransToaccountTransferApiInParam_3.setEnName("amount");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_4 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_4);
        alipayFundTransToaccountTransferApiInParam_4.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_4.setTitle("付款方真实姓名（最长支持100个英文/50个汉字）");
        alipayFundTransToaccountTransferApiInParam_4.setDesc("  如果本参数不为空，则会校验该账户在支付宝登记的实名是否与付款方真实姓名一致。");
        alipayFundTransToaccountTransferApiInParam_4.setDescription("付款方真实姓名（最长支持100个英文/50个汉字）。  如果本参数不为空，则会校验该账户在支付宝登记的实名是否与付款方真实姓名一致。");
        alipayFundTransToaccountTransferApiInParam_4.setIsMust(3);
        alipayFundTransToaccountTransferApiInParam_4.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_4.setFullParamName("payerRealName");
        alipayFundTransToaccountTransferApiInParam_4.setEnName("payer_real_name");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_5 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_5);
        alipayFundTransToaccountTransferApiInParam_5.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_5.setTitle("付款方姓名（最长支持100个英文/50个汉字）");
        alipayFundTransToaccountTransferApiInParam_5.setDesc("显示在收款方的账单详情页。如果该字段不传，则默认显示付款方的支付宝认证姓名或单位名称。");
        alipayFundTransToaccountTransferApiInParam_5.setDescription("付款方姓名（最长支持100个英文/50个汉字）。显示在收款方的账单详情页。如果该字段不传，则默认显示付款方的支付宝认证姓名或单位名称。");
        alipayFundTransToaccountTransferApiInParam_5.setIsMust(3);
        alipayFundTransToaccountTransferApiInParam_5.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_5.setFullParamName("payerShowName");
        alipayFundTransToaccountTransferApiInParam_5.setEnName("payer_show_name");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_6 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_6);
        alipayFundTransToaccountTransferApiInParam_6.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_6.setTitle("收款方真实姓名（最长支持100个英文/50个汉字）");
        alipayFundTransToaccountTransferApiInParam_6.setDesc("  如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。");
        alipayFundTransToaccountTransferApiInParam_6.setDescription("收款方真实姓名（最长支持100个英文/50个汉字）。  如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。");
        alipayFundTransToaccountTransferApiInParam_6.setIsMust(3);
        alipayFundTransToaccountTransferApiInParam_6.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_6.setFullParamName("payeeRealName");
        alipayFundTransToaccountTransferApiInParam_6.setEnName("payee_real_name");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_7 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_7);
        alipayFundTransToaccountTransferApiInParam_7.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_7.setTitle("转账备注（支持200个英文/100个汉字）");
        alipayFundTransToaccountTransferApiInParam_7.setDesc("  当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。收款方可见，会展示在收款用户的收支详情中。");
        alipayFundTransToaccountTransferApiInParam_7.setDescription("转账备注（支持200个英文/100个汉字）。  当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。收款方可见，会展示在收款用户的收支详情中。");
        alipayFundTransToaccountTransferApiInParam_7.setIsMust(3);
        alipayFundTransToaccountTransferApiInParam_7.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_7.setFullParamName("remark");
        alipayFundTransToaccountTransferApiInParam_7.setEnName("remark");

        
        ApiParamModel alipayFundTransToaccountTransferApiInParam_8 = new ApiParamModel();
        alipayFundTransToaccountTransferApiInParamChilds.add(alipayFundTransToaccountTransferApiInParam_8);
        alipayFundTransToaccountTransferApiInParam_8.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiInParam_8.setTitle("扩展参数");
        alipayFundTransToaccountTransferApiInParam_8.setDesc("json字符串格式，目前仅支持的key=order_title，表示收款方的转账账单标题，value可以根据自己的业务定制。");
        alipayFundTransToaccountTransferApiInParam_8.setDescription("扩展参数，json字符串格式，目前仅支持的key=order_title，表示收款方的转账账单标题，value可以根据自己的业务定制。");
        alipayFundTransToaccountTransferApiInParam_8.setIsMust(3);
        alipayFundTransToaccountTransferApiInParam_8.setIsListType(false);
        alipayFundTransToaccountTransferApiInParam_8.setFullParamName("extParam");
        alipayFundTransToaccountTransferApiInParam_8.setEnName("ext_param");

        

		List<ApiParamModel> alipayFundTransToaccountTransferApiOutParamChilds = new ArrayList<ApiParamModel>();
		alipayFundTransToaccountTransferInfoModel.setApiOutParam(alipayFundTransToaccountTransferApiOutParamChilds);
        ApiParamModel alipayFundTransToaccountTransferApiOutParam_0 = new ApiParamModel();
        alipayFundTransToaccountTransferApiOutParamChilds.add(alipayFundTransToaccountTransferApiOutParam_0);
        alipayFundTransToaccountTransferApiOutParam_0.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiOutParam_0.setTitle("商户转账唯一订单号：发起转账来源方定义的转账单据号");
        alipayFundTransToaccountTransferApiOutParam_0.setDesc("请求时对应的参数，原样返回。");
        alipayFundTransToaccountTransferApiOutParam_0.setDescription("商户转账唯一订单号：发起转账来源方定义的转账单据号。请求时对应的参数，原样返回。");
        alipayFundTransToaccountTransferApiOutParam_0.setIsMust(1);
        alipayFundTransToaccountTransferApiOutParam_0.setIsListType(false);
        alipayFundTransToaccountTransferApiOutParam_0.setFullParamName("outBizNo");
        alipayFundTransToaccountTransferApiOutParam_0.setEnName("out_biz_no");

        
        ApiParamModel alipayFundTransToaccountTransferApiOutParam_1 = new ApiParamModel();
        alipayFundTransToaccountTransferApiOutParamChilds.add(alipayFundTransToaccountTransferApiOutParam_1);
        alipayFundTransToaccountTransferApiOutParam_1.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiOutParam_1.setTitle("支付宝转账单据号");
        alipayFundTransToaccountTransferApiOutParam_1.setDesc("成功一定返回，失败可能不返回也可能返回。");
        alipayFundTransToaccountTransferApiOutParam_1.setDescription("支付宝转账单据号，成功一定返回，失败可能不返回也可能返回。");
        alipayFundTransToaccountTransferApiOutParam_1.setIsMust(2);
        alipayFundTransToaccountTransferApiOutParam_1.setIsListType(false);
        alipayFundTransToaccountTransferApiOutParam_1.setFullParamName("orderId");
        alipayFundTransToaccountTransferApiOutParam_1.setEnName("order_id");

        
        ApiParamModel alipayFundTransToaccountTransferApiOutParam_2 = new ApiParamModel();
        alipayFundTransToaccountTransferApiOutParamChilds.add(alipayFundTransToaccountTransferApiOutParam_2);
        alipayFundTransToaccountTransferApiOutParam_2.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransToaccountTransferApiOutParam_2.setTitle("支付时间：格式为yyyy-MM-dd");
        alipayFundTransToaccountTransferApiOutParam_2.setDesc("HH:mm:ss，仅转账成功返回。");
        alipayFundTransToaccountTransferApiOutParam_2.setDescription("支付时间：格式为yyyy-MM-dd HH:mm:ss，仅转账成功返回。");
        alipayFundTransToaccountTransferApiOutParam_2.setIsMust(2);
        alipayFundTransToaccountTransferApiOutParam_2.setIsListType(false);
        alipayFundTransToaccountTransferApiOutParam_2.setFullParamName("payDate");
        alipayFundTransToaccountTransferApiOutParam_2.setEnName("pay_date");

        

		ApiInfoModel alipayFundTransOrderQueryInfoModel = new ApiInfoModel();
		list.add(alipayFundTransOrderQueryInfoModel);
		alipayFundTransOrderQueryInfoModel.setApiName("alipay.fund.trans.order.query");
		alipayFundTransOrderQueryInfoModel.setApiZhName("查询转账订单接口");
		alipayFundTransOrderQueryInfoModel.setInvokeType(ApiInfoModel.INVOKE_TYPE_REQUEST);
		List<ApiParamModel> alipayFundTransOrderQueryApiInParamChilds = new ArrayList<ApiParamModel>();
		alipayFundTransOrderQueryInfoModel.setApiInParam(alipayFundTransOrderQueryApiInParamChilds);
        ApiParamModel alipayFundTransOrderQueryApiInParam_0 = new ApiParamModel();
        alipayFundTransOrderQueryApiInParamChilds.add(alipayFundTransOrderQueryApiInParam_0);
        alipayFundTransOrderQueryApiInParam_0.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiInParam_0.setTitle("商户转账唯一订单号：发起转账来源方定义的转账单据ID");
        alipayFundTransOrderQueryApiInParam_0.setDesc("  和支付宝转账单据号不能同时为空。当和支付宝转账单据号同时提供时，将用支付宝转账单据号进行查询，忽略本参数。");
        alipayFundTransOrderQueryApiInParam_0.setDescription("商户转账唯一订单号：发起转账来源方定义的转账单据ID。  和支付宝转账单据号不能同时为空。当和支付宝转账单据号同时提供时，将用支付宝转账单据号进行查询，忽略本参数。");
        alipayFundTransOrderQueryApiInParam_0.setIsMust(3);
        alipayFundTransOrderQueryApiInParam_0.setIsListType(false);
        alipayFundTransOrderQueryApiInParam_0.setFullParamName("outBizNo");
        alipayFundTransOrderQueryApiInParam_0.setEnName("out_biz_no");

        
        ApiParamModel alipayFundTransOrderQueryApiInParam_1 = new ApiParamModel();
        alipayFundTransOrderQueryApiInParamChilds.add(alipayFundTransOrderQueryApiInParam_1);
        alipayFundTransOrderQueryApiInParam_1.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiInParam_1.setTitle("支付宝转账单据号：和商户转账唯一订单号不能同时为空");
        alipayFundTransOrderQueryApiInParam_1.setDesc("当和商户转账唯一订单号同时提供时，将用本参数进行查询，忽略商户转账唯一订单号。");
        alipayFundTransOrderQueryApiInParam_1.setDescription("支付宝转账单据号：和商户转账唯一订单号不能同时为空。当和商户转账唯一订单号同时提供时，将用本参数进行查询，忽略商户转账唯一订单号。");
        alipayFundTransOrderQueryApiInParam_1.setIsMust(3);
        alipayFundTransOrderQueryApiInParam_1.setIsListType(false);
        alipayFundTransOrderQueryApiInParam_1.setFullParamName("orderId");
        alipayFundTransOrderQueryApiInParam_1.setEnName("order_id");

        

		List<ApiParamModel> alipayFundTransOrderQueryApiOutParamChilds = new ArrayList<ApiParamModel>();
		alipayFundTransOrderQueryInfoModel.setApiOutParam(alipayFundTransOrderQueryApiOutParamChilds);
        ApiParamModel alipayFundTransOrderQueryApiOutParam_0 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_0);
        alipayFundTransOrderQueryApiOutParam_0.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_0.setTitle("支付宝转账单据号");
        alipayFundTransOrderQueryApiOutParam_0.setDesc("查询失败不返回。");
        alipayFundTransOrderQueryApiOutParam_0.setDescription("支付宝转账单据号，查询失败不返回。");
        alipayFundTransOrderQueryApiOutParam_0.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_0.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_0.setFullParamName("orderId");
        alipayFundTransOrderQueryApiOutParam_0.setEnName("order_id");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_1 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_1);
        alipayFundTransOrderQueryApiOutParam_1.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_1.setTitle("转账单据状态");
        alipayFundTransOrderQueryApiOutParam_1.setDesc("  	SUCCESS：成功（配合\"单笔转账到银行账户接口\"产品使用时, 同一笔单据多次查询有可能从成功变成退票状态）；  	FAIL：失败（具体失败原因请参见error_code以及fail_reason返回值）；  	INIT：等待处理；  	DEALING：处理中；  	REFUND：退票（仅配合\"单笔转账到银行账户接口\"产品使用时会涉及, 具体退票原因请参见fail_reason返回值）；  	UNKNOWN：状态未知。");
        alipayFundTransOrderQueryApiOutParam_1.setDescription("转账单据状态。  	SUCCESS：成功（配合\"单笔转账到银行账户接口\"产品使用时, 同一笔单据多次查询有可能从成功变成退票状态）；  	FAIL：失败（具体失败原因请参见error_code以及fail_reason返回值）；  	INIT：等待处理；  	DEALING：处理中；  	REFUND：退票（仅配合\"单笔转账到银行账户接口\"产品使用时会涉及, 具体退票原因请参见fail_reason返回值）；  	UNKNOWN：状态未知。");
        alipayFundTransOrderQueryApiOutParam_1.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_1.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_1.setFullParamName("status");
        alipayFundTransOrderQueryApiOutParam_1.setEnName("status");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_2 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_2);
        alipayFundTransOrderQueryApiOutParam_2.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_2.setTitle("支付时间");
        alipayFundTransOrderQueryApiOutParam_2.setDesc("格式为yyyy-MM-dd HH:mm:ss，转账失败不返回。");
        alipayFundTransOrderQueryApiOutParam_2.setDescription("支付时间，格式为yyyy-MM-dd HH:mm:ss，转账失败不返回。");
        alipayFundTransOrderQueryApiOutParam_2.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_2.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_2.setFullParamName("payDate");
        alipayFundTransOrderQueryApiOutParam_2.setEnName("pay_date");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_3 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_3);
        alipayFundTransOrderQueryApiOutParam_3.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_3.setTitle("预计到账时间");
        alipayFundTransOrderQueryApiOutParam_3.setDesc("转账到银行卡专用，格式为yyyy-MM-dd HH:mm:ss，转账受理失败不返回。     注意：  此参数为预计时间，可能与实际到账时间有较大误差，不能作为实际到账时间使用，仅供参考用途。");
        alipayFundTransOrderQueryApiOutParam_3.setDescription("预计到账时间，转账到银行卡专用，格式为yyyy-MM-dd HH:mm:ss，转账受理失败不返回。     注意：  此参数为预计时间，可能与实际到账时间有较大误差，不能作为实际到账时间使用，仅供参考用途。");
        alipayFundTransOrderQueryApiOutParam_3.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_3.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_3.setFullParamName("arrivalTimeEnd");
        alipayFundTransOrderQueryApiOutParam_3.setEnName("arrival_time_end");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_4 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_4);
        alipayFundTransOrderQueryApiOutParam_4.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_4.setTitle("预计收费金额（元）");
        alipayFundTransOrderQueryApiOutParam_4.setDesc("转账到银行卡专用，数字格式，精确到小数点后2位，转账失败或转账受理失败不返回。");
        alipayFundTransOrderQueryApiOutParam_4.setDescription("预计收费金额（元），转账到银行卡专用，数字格式，精确到小数点后2位，转账失败或转账受理失败不返回。");
        alipayFundTransOrderQueryApiOutParam_4.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_4.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_4.setFullParamName("orderFee");
        alipayFundTransOrderQueryApiOutParam_4.setEnName("order_fee");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_5 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_5);
        alipayFundTransOrderQueryApiOutParam_5.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_5.setTitle("查询到的订单状态为FAIL失败或REFUND退票时");
        alipayFundTransOrderQueryApiOutParam_5.setDesc("返回具体的原因。");
        alipayFundTransOrderQueryApiOutParam_5.setDescription("查询到的订单状态为FAIL失败或REFUND退票时，返回具体的原因。");
        alipayFundTransOrderQueryApiOutParam_5.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_5.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_5.setFullParamName("failReason");
        alipayFundTransOrderQueryApiOutParam_5.setEnName("fail_reason");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_6 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_6);
        alipayFundTransOrderQueryApiOutParam_6.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_6.setTitle("发起转账来源方定义的转账单据号");
        alipayFundTransOrderQueryApiOutParam_6.setDesc("   该参数的赋值均以查询结果中 的 out_biz_no 为准。   如果查询失败，不返回该参数。");
        alipayFundTransOrderQueryApiOutParam_6.setDescription("发起转账来源方定义的转账单据号。   该参数的赋值均以查询结果中 的 out_biz_no 为准。   如果查询失败，不返回该参数。");
        alipayFundTransOrderQueryApiOutParam_6.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_6.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_6.setFullParamName("outBizNo");
        alipayFundTransOrderQueryApiOutParam_6.setEnName("out_biz_no");

        
        ApiParamModel alipayFundTransOrderQueryApiOutParam_7 = new ApiParamModel();
        alipayFundTransOrderQueryApiOutParamChilds.add(alipayFundTransOrderQueryApiOutParam_7);
        alipayFundTransOrderQueryApiOutParam_7.setBaseType(ApiParamModel.TYPE_BASETYPE);
        alipayFundTransOrderQueryApiOutParam_7.setTitle("查询失败时");
        alipayFundTransOrderQueryApiOutParam_7.setDesc("本参数为错误代 码。   查询成功不返回。 对于退票订单，不返回该参数。");
        alipayFundTransOrderQueryApiOutParam_7.setDescription("查询失败时，本参数为错误代 码。   查询成功不返回。 对于退票订单，不返回该参数。");
        alipayFundTransOrderQueryApiOutParam_7.setIsMust(2);
        alipayFundTransOrderQueryApiOutParam_7.setIsListType(false);
        alipayFundTransOrderQueryApiOutParam_7.setFullParamName("errorCode");
        alipayFundTransOrderQueryApiOutParam_7.setEnName("error_code");

        


        return list;
        
    }
}
