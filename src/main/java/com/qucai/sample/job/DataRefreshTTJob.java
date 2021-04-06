package com.qucai.sample.job;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.OrderQueryDemo;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.QueryServlet;
import com.qucai.sample.entity.HistoricalTxnQuery;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.entity.TreasuryDBInfo;
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

@Service
@Lazy(false)
public class DataRefreshTTJob {
	
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
			.getLogger(DataRefreshTTJob.class);
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
	
	@Scheduled(cron = "0 0/5 5-23 * * ?") 
	public void RealTimeTxnStatistic() throws Exception {
		logger.info("定时每5分钟当日成功交易记录统计开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_TotAmtDailySucc = (select sum(t_Txn_ApplyPrepayAmount) from t_transaction_info b "
	    		+ "where DATE(t_Txn_PrepayDate) = CURDATE() and b.t_Txn_PrepayApplierPID in "
	    		+ "(select t_P_PID from t_personal_info c where c.t_P_Company = a.t_TreasuryDB_OrgName)) where t_TreasuryDB_OrgName != 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每5分钟交易记录统计结束：" + RS);
	}
	
	@Scheduled(cron = "0 15 3 * * ?") 
	public void HisTxnMthStatistic() throws Exception {
		logger.info("定时每天凌晨3:15统计当天总交易记录开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_TotAmtDaily = (select sum(t_Txn_ApplyPrepayAmount_his) from t_transaction_his b where b.t_Txn_PrepayApplierPID_his in "
	    		+ "(select t_P_PID from t_personal_info c where c.t_P_Company = a.t_TreasuryDB_OrgName)) where t_TreasuryDB_OrgName != 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每天凌晨3:15统计当天总交易记录结束：" + RS);
	}
	
	@Scheduled(cron = "0 0/5 5-23 * * ?") 
	public void RealTimeTxnAll() throws Exception {
		logger.info("定时每30分钟成功交易总记录统计开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_TotAmtDailySucc = (select sum(t_Txn_ApplyPrepayAmount) from t_transaction_info where DATE(t_Txn_PrepayDate) = CURDATE()) "
	    		+ "where a.t_TreasuryDB_OrgName = 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每5分钟成功交易总记录统计结束：" + RS);
	}
	
	@Scheduled(cron = "0 0 6,14,22 * * ?") 
	public void RealTimeBalanceCredit() throws Exception {
		logger.info("定时每天6,14,22点更新个人剩余额度开始：" + System.currentTimeMillis());
	    String sql = "update t_personal_info, "
	    		+ "(select t_Txn_PrepayApplierPID,t_Txn_CreditPrepayBalanceNum from t_transaction_info where t_Txn_Num in "
	    		+ "(select MAX(t_Txn_Num) from t_transaction_info group by t_transaction_info.t_Txn_PrepayApplierPID)) as t1 "
	    		+ "set t_P_NetMonthlyBonusAmount = t1.t_Txn_CreditPrepayBalanceNum where t_personal_info.t_P_PID = t1.t_Txn_PrepayApplierPID";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每5分钟成功交易总记录统计结束：" + RS);
	}
	
	@Scheduled(cron = "0 0 6 * * ?")
	public void CreditStatusRefresh() throws Exception {
		logger.info("定时每天6:00刷新授额状态开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info,"
	    		+ "(select t1.t_P_Company as TTN,(CASE t1.t_P_Employmentstatus"
	    		+ "	WHEN 'PENDING' THEN '授额部分暂停状态' "
	    		+ "	WHEN 'onjob' THEN '授额开启状态' "
	    		+ "	ELSE '授额状态异常' END) as TTS "
	    		+ "	from (select t_P_Company,t_P_Employmentstatus,count(t_P_Employmentstatus) AS count from t_personal_info "
	    		+ "	group by t_P_Company,t_P_Employmentstatus)AS t1) as t2"
	    		+ "	set t_TreasuryDB_Comment = t2.TTS where t_treasurydb_info.t_TreasuryDB_OrgName = t2.TTN";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每天6:00刷新授额状态结束：" + RS);
	}
	
	
	@Scheduled(cron = "0 0/5 5-23 * * ?") 
	public void DailyFailedPayment() throws Exception {
		logger.info("定时每天18:00统计当天失败交易开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_TotAmtDailyFail = (select sum(tranAmt) from t_payment_list where MID(tranTime,1,8) = DATE_FORMAT(CURDATE(),'%Y%m%d')) "
	    		+ "where a.t_TreasuryDB_OrgName = 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每天18:00统计当天失败交易结束:" + RS);
	}
//	
	@Scheduled(cron = "0 0/30 5-23 * * ?")
	public void TxnDataRefresh() throws Exception {
		logger.info("定时每天备份交易表开始：" + System.currentTimeMillis());
	    String sql = "INSERT INTO sample.t_transaction_info_his select * from sample.t_transaction_info where t_transaction_info.t_Txn_PrepayDate"
	    		+ " between date_sub(CURDATE(),interval 1 day) and CURDATE()";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每天备份交易表结束：" + RS);
	}
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public void TxnHisDataRefresh() throws Exception {
		logger.info("定时每天合并交易统计表开始：" + System.currentTimeMillis());
	    String sql = "INSERT INTO sample.t_transaction_his select * from sample.t_transaction_info JOIN sample.t_personal_info on "
	    		+ "t_transaction_info.t_Txn_PrepayApplierPID = t_personal_info.t_P_PID "
               + "where t_transaction_info.t_Txn_Num not in (select t_transaction_his.t_Txn_Num_his from t_transaction_his)";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每天合并交易统计表结束：" + RS);
	}
	
	
	@Scheduled(cron = "0 15 0 * * ?")
	public void TxnDailyDataBKP() throws Exception {
		logger.info("定时每天备份合并交易统计表开始：" + System.currentTimeMillis());
	    String sql = "INSERT INTO sample.t_transaction_info_bkp select * from sample.t_transaction_info where t_transaction_info.t_Txn_PrepayDate between date_sub(CURDATE(),interval 1 day) and CURDATE()";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天备份合并交易统计表结束：" + RS);
		}	
		
	@Scheduled(cron = "0 0 0 * * ?")
	public void Credithold() throws Exception {
		logger.info("定时每天更新发薪日授额关闭开始：" + System.currentTimeMillis());
	    String sql = "update t_personal_info set t_P_Employmentstatus = 'PENDING' where t_P_PayrollDate = DAY(CURDATE())";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
		logger.info("定时每天更新发薪日授额关闭结束：" + RS);
	}
	
	@Scheduled(cron = "0 30 1 * * ?")
	public void UpdatePayrolldate() throws Exception {
		logger.info("定时每天更新发薪日开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a INNER JOIN t_personal_info b on a.t_TreasuryDB_OrgName = b.t_P_Company "
	    		+ "set a.t_TreasuryDB_PayrollDate = b.t_P_PayrollDate where a.t_TreasuryDB_OrgName = b.t_P_Company";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天更新发薪日结束：" + RS);
	}
	
	@Scheduled(cron = "0 45 1 * * ?")
	public void CreditRefresh() throws Exception {
		logger.info("定时每天刷新授额额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_TotAmtMth = (select sum(t_P_CreditPrepaySalaryAmount) "
	    	+ "from t_personal_info where a.t_TreasuryDB_OrgName = t_personal_info.t_P_Company and t_personal_info.t_P_Employmentstatus = 'onjob')";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天刷新授额额结束：" + RS);
	}
	
	@Scheduled(cron = "0 0 2 * * ?")
	public void DueStatistc() throws Exception {
		logger.info("定时每天统计超期额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_OverdueInt = (select sum(t_Txn_ApplyPrepayAmount_his) "
	    	+ "from t_transaction_his where t_transaction_his.t_P_Company_his = a.t_TreasuryDB_OrgName and t_transaction_his.t_Txn_PrepayClear_his = 1 and "
	        + "t_transaction_his.t_Txn_PrepayDate_his between date_sub(CURDATE(),interval 180 day) and date_sub(CURDATE(),interval 31 day))";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计超期额结束：" + RS);
	}
	@Scheduled(cron = "0 10 2 * * ?")
	public void DueStatistcall() throws Exception {
		logger.info("定时每天统计超期ALL额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_OverdueInt = (select sum(t_Txn_ApplyPrepayAmount_his) "
	    	+ "from t_transaction_his where t_transaction_his.t_P_Company_his != 'ALL' and t_transaction_his.t_Txn_PrepayClear_his = 1 and "
	        + "t_transaction_his.t_Txn_PrepayDate_his between date_sub(CURDATE(),interval 180 day) and date_sub(CURDATE(),interval 31 day)) where a.t_TreasuryDB_OrgName = 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天ALL统计超期额结束：" + RS);
	}
	
	@Scheduled(cron = "0 0/30 5-23 * * ?")
	public void FeeStatistc() throws Exception {
		logger.info("定时每天统计收费额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_Fee = (select sum(t_Txn_ServiceFee_his + t_Txn_Poundage_his + t_Txn_TierPoundage_his) "
	    	+ "from t_transaction_his where t_transaction_his.t_P_Company_his = a.t_TreasuryDB_OrgName and t_transaction_his.t_Txn_PrepayClear_his = 1)";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计收费额结束：" + RS);
	}
	
	@Scheduled(cron = "0 0/30 5-23 * * ?")
	public void FeeStatistcALL() throws Exception {
		logger.info("定时每天统计收费总额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info a set t_TreasuryDB_Fee = (select sum(t_Txn_ServiceFee_his + t_Txn_Poundage_his + t_Txn_TierPoundage_his) "
	    	+ "from t_transaction_his where t_transaction_his.t_P_Company_his != 'ALL' and t_transaction_his.t_Txn_PrepayClear_his = 1) where a.t_TreasuryDB_OrgName = 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计收费总额结束：" + RS);
	}
	
	@Scheduled(cron = "0 20 2 * * ?")
	public void CreditRefreshALL() throws Exception {
		logger.info("定时每天统计月交易总计总额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info, (select sum(t_TreasuryDB_TotAmtMth) as total from t_treasurydb_info where t_TreasuryDB_OrgName != 'ALL') as tmp "
	    	+ "set t_treasurydb_info.t_TreasuryDB_TotAmtMth = tmp.total where t_treasurydb_info.t_TreasuryDB_OrgName = 'ALL'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计月交易总计总额结束：" + RS);
	}
	
	@Scheduled(cron = "0 30 2 * * ?")
	public void PoPRatio() throws Exception {
		logger.info("定时每天统计月交易授额比开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info set t_TreasuryDB_PoPRatio = ((select sum(t_Txn_ApplyPrepayAmount_his) from t_transaction_his "
	    	+ "where t_transaction_his.t_P_Company_his = t_treasurydb_info.t_TreasuryDB_OrgName and t_transaction_his.t_Txn_PrepayClear_his = 1)/"
	        + "(select sum(t_P_NetBaseSalary) from t_personal_info where t_personal_info.t_P_Company = t_treasurydb_info.t_TreasuryDB_OrgName and t_personal_info.t_P_Employmentstatus = 'onjob'))*100";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计月交易授额比结束：" + RS);
	}
	
	@Scheduled(cron = "0 45 2 * * ?")
	public void UpdatePstatus() throws Exception {
		logger.info("定时每天统计月交易授额比开始：" + System.currentTimeMillis());
	    String sql = "update t_manager set t_manager.`status` = 0 where t_manager.mobile in "
	    	+ "( select t_P_Mobil from t_personal_info where t_personal_info.t_P_Employmentstatus = 'EXPIRED' or 'resigning' )";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计月交易授额比结束：" + RS);
	}

	@Scheduled(cron = "0 35 3 * * ?")
	public void TxnTotal() throws Exception {
		logger.info("定时每天统计月交易总额开始：" + System.currentTimeMillis());
	    String sql = "update t_treasurydb_info, (select sum(t_Txn_ApplyPrepayAmount_his) as TxnTotal from t_transaction_his where t_P_Company_his != 'All') as tmp "
	    	+ "set t_treasurydb_info.t_TreasuryDB_TotAmtDaily = tmp.TxnTotal where t_treasurydb_info.t_TreasuryDB_OrgName = 'All'";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计月交易总额开始结束：" + RS);
	}
	
	@Scheduled(cron = "0 0 6 1 * ?")
	public void CleanAcc() throws Exception {
		logger.info("定时每月清理过期账户开始：" + System.currentTimeMillis());
	    String sql = "delete from t_manager where status = '0' ";
	    String connectStr = "jdbc:mysql://localhost:3306/sample?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true";
	    String username = "root";
	    String password = "Spearsharp1983";

	     boolean RS = MysqlBatchUtil.SQLDataPatch(sql,connectStr,username,password);
			logger.info("定时每天统计月交易总额开始结束：" + RS);
	}
	
//	@Scheduled(cron = "0 0 12,23 * * ?")
//	public void PaymentChkCreditRT() throws Exception {
//		logger.info("定时月预支失败交易单处理开始：" + System.currentTimeMillis());
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		List<StaffPrepayApplicationPayment> StaffPrepayApplicationPayment  = staffPrepayApplicationService.findFailedPaymentList(paramMap);
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//		
//	    for(int i=0;i<StaffPrepayApplicationPayment.size();i++){
//	    	String OrderCode = null;
//	        OrderCode = StaffPrepayApplicationPayment.get(i).getOrderCode();
//	        String PaymentVendor = StaffPrepayApplicationPayment.get(i).getVersion();
//			String TranTime = StaffPrepayApplicationPayment.get(i).getTranTime();
//			String OriginalRC = StaffPrepayApplicationPayment.get(i).getRCcode();
//	        String RC,FailedRC = null;
//	        if (PaymentVendor.equals("sandpay")) {
//		    	RC = OrderQueryDemo.QueryOrder(OrderCode,TranTime);
//		    	System.out.println("sandpay error fix:");
//		    	System.out.println(OrderCode);
//		    	System.out.println("Return Code");
//		    	FailedRC = RC.substring(92,96);
//		    	System.out.println(FailedRC);
//	        }else if(PaymentVendor.equals("Chinaebi")){
//		    	RC = QueryServlet.main(StaffPrepayApplicationPayment.get(i));
//		    	FailedRC = String.valueOf(RC);
//		    	System.out.println("Chinaebi error fix:");
//		    	System.out.println(OrderCode);
//		    	System.out.println("Chinaebi Return Code");
//		    	System.out.println(RC);
//	        }
//	        
//	    	System.out.print("Get Fresh RC: ");
//	    	System.out.print("'");
//	    	System.out.print(FailedRC);
//	    	System.out.print("'");
//	    	
//	       	if(FailedRC.equals(null)){
//	       		continue;
//	       	}else if(FailedRC.equals("0000") || FailedRC.equals("S")){
//	       		int RsClean = staffPrepayApplicationService.paymentSuccCheck(OrderCode);
//		    }else if(FailedRC.equals("0001") || FailedRC.equals("0002") || FailedRC.equals("P")){
//	        	continue;
//	       	} else if((FailedRC.equals("S") && OriginalRC != "F") || (FailedRC.equals("0000") && OriginalRC != "0004")) {
//	       		StaffPrepayApplicationList staffPrepayApplicationList = staffPrepayApplicationService.FindFailedPayment(OrderCode);
//	    		if (staffPrepayApplicationList == null) {
//	    			continue;
//	    		}else{
//	       		BigDecimal OverAllFee = staffPrepayApplicationList.getT_Txn_ApplyPrepayAmount().add(staffPrepayApplicationList.getT_Txn_ServiceFee()).add(staffPrepayApplicationList.getT_Txn_Poundage());	
//	    		String t_TreasuryDB_OrgName = null;
//	    		HistoricalTxnQuery historicalTxnQueryFind = historicalTxnQueryService.selectByOrderCode(OrderCode);
//
//	    		t_TreasuryDB_OrgName = historicalTxnQueryFind.getT_P_Company_his();
//	    		//返回机构余额
//
//	    		TreasuryDBInfo treasuryOrgDBInfoUpdate = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
//	    	  	BigDecimal tTreasuryOrgDBBalance = treasuryOrgDBInfoUpdate.getT_TreasuryDB_Balance().add(OverAllFee);
//	    	  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_Balance(tTreasuryOrgDBBalance);    
//	    	  	treasuryOrgDBInfoUpdate.setModifier("system");    
//	    	  	treasuryOrgDBInfoUpdate.setModify_time(new Date());  
//	    	  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_Comment("自动更新"); 
//	    	    int RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryOrgDBInfoUpdate);
//	    	    
//	    		//返回总余额
//	       	    t_TreasuryDB_OrgName = "ALL";
//	      	    TreasuryDBInfo treasuryDBInfoUpdateOverall = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
//			    BigDecimal tTreasuryDBBalanceOverall = treasuryDBInfoUpdateOverall.getT_TreasuryDB_Balance().add(OverAllFee);
//			    treasuryDBInfoUpdateOverall.setT_TreasuryDB_Balance(tTreasuryDBBalanceOverall);
//			    RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdateOverall);
//	            String SeesionLoginMobil =  StaffPrepayApplicationPayment.get(i).getPhone();
//	            String OrderCodeUpdate = StaffPrepayApplicationPayment.get(i).getOrderCode();
//	            StaffPrepayApplicationList StaffPrepayApplicationListBalance  = staffPrepayApplicationService.findPrepayApplierCreditBalance(SeesionLoginMobil);
//	            BigDecimal CreditBalanceAmtRefund = tTreasuryDBBalanceOverall.add(StaffPrepayApplicationListBalance.getT_Txn_BalanceCreditNum());
//	         	int rs = staffPrepayApplicationService.updateCreditBalanceAmt(CreditBalanceAmtRefund,OrderCodeUpdate);
//	       		if (RS == 1) {
//	          	  continue;
//	         		}
//	         		else { 		
//	         			staffPrepayApplicationService.updateFailedPayment(OrderCode);
//	         			historicalTxnQueryService.MarkFailedPayment(OrderCode);
//	         		 }
//	         		}
//	    		}
//	       	}
//		logger.info("定时月预支失败交易单处理开始结束：" + System.currentTimeMillis());
//	       }

//Tomcat v7.0 Server at localhost
	

//@Scheduled(cron = "0 15 9 * * ?")
//public void SMSgroupSend() throws Exception {
//	logger.info("定时发送短信开始：" + System.currentTimeMillis());
//	Map<String, Object> paramSearchMap = new HashMap<String, Object>();
//   	paramSearchMap.put("t_P_Company", "高孚");
//	List<PersonalInfo> personalInfo = personalInfoService.findSearchList(paramSearchMap);
//	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//	
//    for(int i=0;i<personalInfo.size();i++){
//    	
//        String t_P_Company = personalInfo.get(i).getT_P_Company();
//		String t_P_Name = personalInfo.get(i).getT_P_Name();
//        String t_P_Mobile = personalInfo.get(i).getT_P_Mobil();
//		String t_SMS_Detail = "已充值5000元，";
//    	String RC = HttpJsonExample.SMSgroupSend(t_SMS_Detail, t_P_Mobile, t_P_Name, t_P_Company);
//    	System.out.print("Get SMSFresh RC: ");
//    	System.out.print(RC);
//       	}
//	logger.info("定时月预支失败交易单处理开始结束：" + System.currentTimeMillis());
//       }
//

//
//@Scheduled(cron = "0 15 9 * * ?")
//public void PWDgroupSend() throws Exception {
//	logger.info("批量发送密码短信通知开始：" + System.currentTimeMillis());
//	Map<String, Object> paramSearchMap = new HashMap<String, Object>();
//   	paramSearchMap.put("t_P_Company", "云开源人力资源");	
//	List<Manager> manager = managerService.findSearchList(paramSearchMap);
//	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//	
//    for(int i=0;i<manager.size();i++){
//		String userName = manager.get(i).getRealName();
//        String mobil = manager.get(i).getMobile();
//        String resendpassword = manager.get(i).getPassword();
//		String CompanyName = "云开源人力资源";
//    	String RC = HttpJsonExample.GroupPWDsend(resendpassword, mobil, userName, CompanyName);
//    	System.out.print("Get SMSFresh RC: ");
//    	System.out.print(RC);
//       	}
//	logger.info("定时月预支失败交易单处理开始结束：" + System.currentTimeMillis());
//       }
 }
	
//批量重新获取失败交易并导入失败交易表
//	@Scheduled(cron = "0 0 2 * * ?")
//	public void PaymentCatch() throws Exception {
//		
//		logger.info("定时月预支失败交易单处理开始：" + System.currentTimeMillis());
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		List<StaffPrepayApplicationList> StaffPrepayApplicationList  = staffPrepayApplicationService.findAllList(paramMap);		
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//		
//        for(int i=0;i<StaffPrepayApplicationList.size();i++){
//
//    		String OrderCode = StaffPrepayApplicationList.get(i).getT_Txn_Num();
//    		String TranTime = df.format(StaffPrepayApplicationList.get(i).getT_Txn_PrepayDate());
//        	String RC = QueryOrderDemo.QueryOrder(OrderCode,TranTime);
//        	System.out.print(RC);
//        	String FailedRC = RC.substring(52,56);
//        	System.out.print("Judge Return code: ");
//        	System.out.print(FailedRC);
//           	if(FailedRC.equals("0003")) {             
//           		staffPrepayApplicationPay = new StaffPrepayApplicationPayment();
//				staffPrepayApplicationPay.setID(Tool.uuid());
//				staffPrepayApplicationPay.setOrderCode(StaffPrepayApplicationList.get(i).getT_Txn_Num());
//				staffPrepayApplicationPay.setVersion("01");
//				staffPrepayApplicationPay.setProductId("00000004");
//	            staffPrepayApplicationPay.setTranTime(TranTime);
//				staffPrepayApplicationPay.setTranAmt(StaffPrepayApplicationList.get(i).getT_Txn_ApplyPrepayAmount().setScale(0,BigDecimal.ROUND_UP).toString());
//				staffPrepayApplicationPay.setRCcode("0003");
//				staffPrepayApplicationPay.setName(StaffPrepayApplicationList.get(i).getT_Txn_PrepayApplierName());
//				staffPrepayApplicationPay.setCurrencyCode("156");
//				staffPrepayApplicationPay.setCertType("0101");
//				staffPrepayApplicationPay.setCertNo(StaffPrepayApplicationList.get(i).getT_Txn_PrepayApplierPID());
//				staffPrepayApplicationPay.setAccAttr("0");
//				staffPrepayApplicationPay.setAccNo(StaffPrepayApplicationList.get(i).getT_Txn_BankAcc());
//				staffPrepayApplicationPay.setAccName(StaffPrepayApplicationList.get(i).getT_Txn_PrepayApplierName());
//				staffPrepayApplicationPay.setAccType("4");
//				staffPrepayApplicationPay.setRemark("pay");
//				staffPrepayApplicationPay.setReturnPic("1");
//				staffPrepayApplicationPay.setPhone(StaffPrepayApplicationList.get(i).getT_Txn_Mobil());
//				staffPrepayApplicationPay.setReqReserved("全渠道");
//				
//           		 OrderCode = StaffPrepayApplicationList.get(i).getT_Txn_Num();
//           		
//				 staffPrepayApplicationPay.setRCcode(OrderCode);
//			     staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
//           	       }
//           		else {             	  
//           			continue;
//           		  }
//           		}
//		logger.info("定时月预支失败交易单处理开始结束：" + System.currentTimeMillis());
//           }
//	}


//@Scheduled(cron = "0 50 18 * * ?")
//public void PaymentFailReset() throws Exception {
//	
//	logger.info("每日失败交易单处理开始：" + System.currentTimeMillis());
//	Map<String, Object> paramMap = new HashMap<String, Object>();
//	List<StaffPrepayApplicationList> StaffPrepayApplicationList  = staffPrepayApplicationService.findAllList(paramMap);		
//	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//	
//    for(int i=0;i<StaffPrepayApplicationList.size();i++){
//
//		String OrderCode = StaffPrepayApplicationList.get(i).getT_Txn_Num();
//		String TranTime = df.format(StaffPrepayApplicationList.get(i).getT_Txn_PrepayDate());
//    	String RC = QueryOrderDemo.QueryOrder(OrderCode,TranTime);
//    	System.out.print(RC);
//    	String FailedRC = RC.substring(52,56);
//    	System.out.print("Judge Return code: ");
//    	System.out.print(FailedRC);
//       	if(FailedRC.equals("0003")) {             
//       		staffPrepayApplicationPay = new StaffPrepayApplicationPayment();
//			staffPrepayApplicationPay.setID(Tool.uuid());
//			staffPrepayApplicationPay.setOrderCode(StaffPrepayApplicationList.get(i).getT_Txn_Num());
//			staffPrepayApplicationPay.setVersion("01");
//			staffPrepayApplicationPay.setProductId("00000004");
//            staffPrepayApplicationPay.setTranTime(TranTime);
//			staffPrepayApplicationPay.setTranAmt(StaffPrepayApplicationList.get(i).getT_Txn_ApplyPrepayAmount().setScale(0,BigDecimal.ROUND_UP).toString());
//			staffPrepayApplicationPay.setRCcode(FailedRC);
//			staffPrepayApplicationPay.setName(StaffPrepayApplicationList.get(i).getT_Txn_PrepayApplierName());
//			staffPrepayApplicationPay.setCurrencyCode("156");
//			staffPrepayApplicationPay.setCertType("0101");
//			staffPrepayApplicationPay.setCertNo(StaffPrepayApplicationList.get(i).getT_Txn_PrepayApplierPID());
//			staffPrepayApplicationPay.setAccAttr("0");
//			staffPrepayApplicationPay.setAccNo(StaffPrepayApplicationList.get(i).getT_Txn_BankAcc());
//			staffPrepayApplicationPay.setAccName(StaffPrepayApplicationList.get(i).getT_Txn_PrepayApplierName());
//			staffPrepayApplicationPay.setAccType("4");
//			staffPrepayApplicationPay.setRemark("pay");
//			staffPrepayApplicationPay.setReturnPic("1");
//			staffPrepayApplicationPay.setPhone(StaffPrepayApplicationList.get(i).getT_Txn_Mobil());
//			staffPrepayApplicationPay.setReqReserved("全渠道");
//			
//       		 OrderCode = StaffPrepayApplicationList.get(i).getT_Txn_Num();
//       		
//			 staffPrepayApplicationPay.setRCcode(OrderCode);
//		     staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
//       	       }
//       		else {             	  
//       			continue;
//       		  }
//       		}
//	logger.info("每日失败交易单处理开始结束：" + System.currentTimeMillis());
//       }



