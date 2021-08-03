package com.qucai.sample.util;

import com.qucai.sample.controller.PersonalMainController;
import com.qucai.sample.controller.PersonalTreasuryCtrlController;
import com.qucai.sample.controller.ProductMainController;
import com.qucai.sample.entity.PersonalTreasuryCtrl;
import com.qucai.sample.entity.ProductMain;
import com.qucai.sample.vo.MobilePersonalMain;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PersonalValueEst {
    
    public static Map<String, Object>  PersonalEvaluateRenew() {
        Map<String,Object> retPersonalEvalation = new HashMap<>();
        String newPersonalEvaluateNum = "999999.99";
        retPersonalEvalation.put("newPersonalEvaluateNum",new BigDecimal(newPersonalEvaluateNum));
        return retPersonalEvalation;
    }
    

    public static Map<String, Object> PersonalTreasuryChk(String action, String txnCat, BigDecimal txnAmt, String walletTxn_PayerPID, String walletTxn_ReceiverID,
                                                          String method, String paymentID, String paymentStatus, Connection conn) throws SQLException {
        Map<String,Object> PersonalTreasuryChk = new HashMap<String, Object>();
        PersonalMainController personalMainController = new PersonalMainController();
//https://blog.csdn.net/fgdfgasd/article/details/50534517  -- 改成三连left join提高效率 - 拿PID查出个人信息连接个人产品控制信息连接个人资金控制 同时获取个人当前交易资金统计信息
        MobilePersonalMain mobilePersonalMain = (MobilePersonalMain) personalMainController.findPersonalMainInfo(walletTxn_PayerPID,conn);
        ProductMainController productMainController = new ProductMainController();
        ProductMain MobileProductMain = (ProductMain) productMainController.findPersonalProduct(mobilePersonalMain.getT_mobilePersonalMain_productCat(),conn);
        if(MobileProductMain.getT_Product_Txt().equalsIgnoreCase("SQL-FindPersonalProduct-ErrorCode")){
            PersonalTreasuryChk.put("SQL","findPersonalProduct-error");
            PersonalTreasuryChk.put("SQL-CODE",MobileProductMain.getT_Product_Txt1());
            PersonalTreasuryChk.put("SQL-CAUSE",MobileProductMain.getT_Product_Txt2());
            return PersonalTreasuryChk;
        }else{
            PersonalTreasuryCtrlController personalTreasuryCtrlController = new PersonalTreasuryCtrlController();
            PersonalTreasuryCtrl MobilePersonalTreasuryCtrl = (PersonalTreasuryCtrl) personalTreasuryCtrlController.findPersonalTreasury(MobileProductMain.getT_Product_SeriesID(),conn);
            if(MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt().equalsIgnoreCase("SQL-FindPersonalProduct-ErrorCode")){
                PersonalTreasuryChk.put("SQL",MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt());
                PersonalTreasuryChk.put("SQL-CODE",MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt2());
                PersonalTreasuryChk.put("SQL-CAUSE",MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt3());
                return PersonalTreasuryChk;
            }else{
//                MobileProductMain.get
            }
        }
        
        return PersonalTreasuryChk;
    }

}
