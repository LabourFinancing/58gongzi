package com.qucai.sample.exception;


/**
 * 业务返回码定义规则如下：<br>
 * 模块名_方法名_返回码<br>
 * 每个方法至少保留一项默认返回码<br>
 * ret：如010101：01(模块)01(方法)01(异常)<br>
 * 
 * @author owner
 *
 */
public enum ExRetEnum implements RetEnumIntf {

	// 基本
	SUCCESS("0", "成功"), FAIL("-1", "失败"),

	SMSSUCCESS("0", "验证码短信已发送,请查看您的手机,15分钟有效!"),
    STATISICSUCCESS("0", "该企业本月交易统计信息"),
    Pullin_SuccNewPer("0", "成功上传并新增人员"),
	UPDATEPWDSUCCESS("0", "密码更新成功,登录时请注意大小写"), 
	SMSFAIL("-1", "短信发送失败"),
	LIMITAMT("-1", "单笔超额，请重新出入"),
	PAY_SUCCESS("0", "支取成功，请查看您的工资卡"), 
	PAY_FAIL_BALANCE("-1", "企业账户余额不足,请联系企业财务及人事"),
	PAY_FAIL("-1", "支取失败..请核查您的姓名,身份证,银行卡信息是否正确后再试。"),
	PAY_OUTOFBALANCE("-1", "支取失败..企业账户余额不足,请等待企业充值完成后再试。"),
	EXPREPAY_OPEN("-1", "您的上一笔支取尚未到付，请下拉刷新页面后稍后再试!"),
	EXPREPAY_WIP("-1", "您的操作过于频繁，请下拉刷新页面后再试!"),
	PAY_CONN_FAIL("-1", "网络异常,请稍后再试.."),
	PAY_ACC_FAIL("-1", "支付账户异常..请稍后再试"),
	Pullin_UserNameErr("-1", "文件为空，或用户名缺失"),
	Pullin_UserIdErr("-1", "用户身份证信息错误或缺失"),
	Pullin_UserDebitCardErr("-1", "用户银行卡信息错误或缺失"),
	Pullin_UserMobileErr("-1", "用户手机号码信息错误或缺失"),
	Pullin_UserCreditLineErr("-1", "文件为空，或用户授额数缺失"),
	Pullin_FailDupDebitCardErr("-1", "上传表记录银行卡号重复"),
	Pullin_FailDupPerMobileErr("-1", "上传表中手机号与个人信息表手机号码重复"),
	Pullin_FailDupMgrMobileErr("-1", "上传表中手机号与主信息表手机号码重复"),
	Pullin_FailDupBatchMobileErr("-1", "上传表中手机号存在重复"),
	Pullin_FailDupBatchPIDErr("-1", "上传表中身份证号存在重复"),
	Pullin_FailDupPIDErr("-1", "身份证号码重复"),
	PREPAY_APPFAIL("-1", "系统繁忙,请稍后再试"),
	PASSWORD_FAIL("-1", "两次输入的密码不一致"),
	PASSWORD_RESENT("0", "密码已发送，请注意查看"),
	PASSWORD_RESENT_FAIL("-1", "密码发送失败，请稍后再试"),
	
	CLEAR_SUCCESS("0", "清算成功"), 
	CLEAR_FAILED("-1", "清算失败"),
	
	DUE_SUCCESS("0", "解除结算成功"), 
	DUE_FAILED("-1", "解除结算失败"),

	API_IMS_RECIEVE_CLAIM_FEE_DATA_NO_STATUS_ERROR("-1", "未获取到状态数据（status）"),
	API_IMS_RECIEVE_CLAIM_FEE_DATA_NO_CLAIM_APPLY_ERROR("-1", "未找到对应赔案信息"),
	API_IMS_RECIEVE_CLAIM_FEE_DATA_NO_CLAIM_FEE_LIST_ERROR("-1", "未获取到费用列表数据（claimFeeList）"),
	JOBGRADE_ADD_ERROR("-1", "职业名称已存在"),
	CLAIM_ATTACH_ERROR("-1", "审核失败，审核规则不通过！"),

	LOGIN_ACCOUNT_PASSWORD_ERROR("-1", "账号或密码错误"),
	
	@Deprecated
	SAMPLE_NONE_USED("_010101", "废弃不再使用的"), SAMPLE_USED("_010102", "正在使用的");

	private String ret;
	private String msg;

	ExRetEnum(String ret, String msg) {
		this.ret = ret;
		this.msg = msg;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
