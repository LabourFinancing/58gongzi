package com.qucai.sample.vo;
//package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class CompanyTxnAmtWlyStatic implements Serializable {

   private static final long serialVersionUID = 1;

    private String Static_date;

    private BigDecimal t_CTxnAmt_Static_day1;

    public String getStatic_date() {
        return Static_date;
    }

    public void setStatic_date(String static_date) {
        Static_date = static_date;
    }

    public BigDecimal getT_CTxnAmt_Static_day1() {
        return t_CTxnAmt_Static_day1;
    }

    public void setT_CTxnAmt_Static_day1(BigDecimal t_CTxnAmt_Static_day1) {
        this.t_CTxnAmt_Static_day1 = t_CTxnAmt_Static_day1;
    }

}