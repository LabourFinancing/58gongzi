package com.qucai.sample.job;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class MysqlBatchUtil {

    public boolean doStore(String sql,String connectStr,String username,String password) throws ClassNotFoundException, SQLException, IOException {
    	int count = 0;
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection conn = DriverManager.getConnection(connectStr, username, password);
		Statement stmt = null;
		stmt = (Statement) conn.createStatement();
//        long start = System.currentTimeMillis();
        boolean RS = stmt.execute(sql);
        System.out.println(RS+"  "+sql);
//        for (int i = 1; i <= 5000000; i++) {
//            psts.setString(1, "t_Txn_ID" + i);
//            psts.setString(2, "t_Txn_Num" + i);
//            psts.setString(3, "t_Txn_ClearNum" + i);
//            psts.setString(4, "t_Txn_ClearOrg" + i);
//            psts.setString(5, "t_Txn_PrepayApplierName" + i);
//            psts.setString(6, "t_Txn_PrepayApplierPID" + i);
//            psts.setString(7, "t_Txn_Mobil" + i);
//            psts.setString(8, "t_Txn_PrepayDate" + i);
//            psts.setString(9, "t_Txn_ProdName" + i);
//            psts.setString(10, "t_Txn_PrepayDays" + i);
//            psts.setString(11, "t_Txn_CreditPrepayCurrentNum" + i);
//            psts.setString(12, "t_Txn_ApplyPrepayAmount" + i);
//            psts.setString(13, "t_Txn_CreditPrepayBalanceNum" + i);
//            psts.setString(14, "t_Txn_TotalPrepayNum" + i);
//            psts.setString(15, "t_Txn_TotalInterestDays" + i);
//            psts.setString(16, "t_Txn_PrepayCounts" + i);
//            psts.setString(17, "t_Txn_Interest" + i);
//            psts.setString(18, "t_Txn_BalanceCreditNum" + i);
//            psts.setString(19, "t_Txn_OverdueRepaymentDate" + i);
//            psts.setString(20, "t_Txn_PrepayClear" + i);
//            psts.setString(21, "t_Txn_Overdue" + i);
//            psts.setString(22, "t_Txn_OverdueDays" + i);
//            psts.setString(23, "t_Txn_OverdueTotalAmount" + i);
//            psts.setString(24, "t_Txn_FinancialInterest" + i);
//            psts.setString(25, "t_Txn_ServiceFee" + i);
//            psts.setString(26, "t_Txn_Poundage" + i);
//            psts.setString(27, "t_Txn_InterestMargin" + i);
//            psts.setString(28, "t_Txn_BankAccName" + i);
//            psts.setString(29, "t_Txn_BankAcc" + i);
//            psts.setString(30, "t_Txn_SysUpdateDate" + i);
//            psts.setString(31, "t_Txn_paystatus" + i);
//            psts.setString(32, "t_Txn_SMS" + i);
//            psts.setString(33, "t_Txn_SMSRec" + i);
//            psts.setString(34, "platform" + i);
//            psts.setString(35, "remark" + i);
//            psts.setString(36, "creator" + i);
//            psts.setString(37, "create_time" + i);
//            psts.setString(38, "agreement" + i);
//            psts.setString(39, "modify_time" + i);
//            psts.addBatch();   // 加入批量处理
//            count++;
//        }
//        psts.executeBatch(); // 执行批量处理
//        conn.commit(); // 提交
//        long end = System.currentTimeMillis();
//        System.out.println("数量=" + count);
//        System.out.println("运行时间=" + (end - start));
        conn.close();
		stmt.close();
        return RS;
    }

    public static boolean SQLDataPatch(String sql,String connectStr,String username,String password) {
        boolean RS = false;
        try {
            new MysqlBatchUtil().doStore(sql,connectStr,username,password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
		return RS;
    }
}