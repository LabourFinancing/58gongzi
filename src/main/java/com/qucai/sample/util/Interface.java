package com.qucai.sample.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.service.FinanceProductService;
import com.qucai.sample.service.HistoricalTxnQueryService;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.PersonalInfoService;
import com.qucai.sample.service.ResourceService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.service.TreasuryDBInfoService;
import com.qucai.sample.service.TreasuryInfoService;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;

@Service
@Lazy(false)
public class Interface {
	
	String YKYToken;
	String secretid;
	
	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
	
    @Autowired
    private ManagerService managerService;// 申明一个对象

	@Autowired
	private PersonalInfoService personalInfoService; // 申明一个对象
	
    @Autowired
    private HistoricalTxnQueryService historicalTxnQueryService; //申明一个对象

	@Autowired
	private FinanceProductService FinanceProductService; // 申明一个对象
	
    @Autowired
    private RoleService roleService;// 申明一个对象

    @Autowired
    private ResourceService resourceService;// 申明一个对象
    
	@Autowired
    private TreasuryInfoService treasuryInfoService; //申明一个对象
    
    @Autowired
    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象


	
	private static Logger logger = LoggerFactory
			.getLogger(Interface.class);
	private StaffPrepayApplicationPayment staffPrepayApplicationPay;

	/**
	 * 每天0点1分执行
	 * 生产表达式：cron = "0 1 0 * * ?"
	 * 测试表达式（每分钟）：cron = "0 0/1 * * * ?"
	 * @throws Exception 
	 * 
	 * 
	 * 
* CRON表达式    含义 
0 0 10,14,16 * * ? 每天上午10点，下午2点，4点 
0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时 
0 0 12 ? * WED 表示每个星期三中午12点 
"0 0 12 * * ?" 每天中午12点触发 
"0 15 10 ? * *" 每天上午10:15触发 
"0 15 10 * * ?" 每天上午10:15触发 
"0 15 10 * * ? *" 每天上午10:15触发 
"0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
"0 15 10 15 * ?" 每月15日上午10:15触发 
"0 15 10 L * ?" 每月最后一日的上午10:15触发 
"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
*/
	@Scheduled(cron = "0 15 9 * * ?")
	public void SMSgroupSend() throws Exception {
		logger.info("定时发送短信开始：" + System.currentTimeMillis());
		Map<String, Object> paramSearchMap = new HashMap<String, Object>();
	   	paramSearchMap.put("t_P_Company", "云开源人力资源");
		List<PersonalInfo> personalInfo = personalInfoService.findSearchList(paramSearchMap);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
	    for(int i=0;i<personalInfo.size();i++){
	    	
	        String t_P_Company = personalInfo.get(i).getT_P_Company();
			String t_P_Name = personalInfo.get(i).getT_P_Name();
	        String t_P_Mobile = personalInfo.get(i).getT_P_Mobil();
			String t_SMS_Detail = "已充值5000元，";
	    	String RC = HttpJsonExample.SMSgroupSend(t_SMS_Detail, t_P_Mobile, t_P_Name, t_P_Company);
	    	System.out.print("Get SMSFresh RC: ");
	    	System.out.print(RC);
	       	}
		logger.info("定时月预支失败交易单处理开始结束：" + System.currentTimeMillis());
	       }
}

	


