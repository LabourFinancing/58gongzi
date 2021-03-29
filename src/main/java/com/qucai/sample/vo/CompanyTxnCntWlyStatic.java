package com.qucai.sample.vo;
//package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class CompanyTxnCntWlyStatic implements Serializable {

   private static final long serialVersionUID = 1;

    public int getStatic_date_cnt() {
        return Static_date_cnt;
    }

    public void setStatic_date_cnt(int static_date_cnt) {
        Static_date_cnt = static_date_cnt;
    }

    public int getT_CTxnCnt_Static_day1() {
        return t_CTxnCnt_Static_day1;
    }

    public void setT_CTxnCnt_Static_day1(int t_CTxnCnt_Static_day1) {
        this.t_CTxnCnt_Static_day1 = t_CTxnCnt_Static_day1;
    }

    private int Static_date_cnt;

    private int t_CTxnCnt_Static_day1;



}