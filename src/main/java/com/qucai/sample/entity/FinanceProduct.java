package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class FinanceProduct implements Serializable {
    /**
	 * 
	 */
   private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */
   private String creator;
   /**
    * 创建时间
    */
   private Date createTime;
   /**
    * 修改人
    * @return 
    */
   private String modifier;
   
   /*
    * 修改时间
    */
   private Date modifyTime;
	
   /**
    * 产品ID
    */
   private String t_FProd_ID;

   /**
    * 产品名
    */
   private String t_FProd_Name;

/**
    * 产品利率
    */
   private BigDecimal t_FProd_Interest;

   /**
    * 产品超期利率
    */
   private BigDecimal t_FProd_OverdueInt;

   /**
    * 服务费(融资成本)
    */
   private BigDecimal t_FProd_ServiceFee;

   /**
    * 平台使用费(笔)
    */
   private BigDecimal t_FProd_Poundage;
   
   /**
    * 单笔限额
    */
   private BigDecimal t_FProd_ETxnAmtLimit;
   
   /**
    * 区间平台使用费(笔)
    */
   private String t_FProd_TierPoundage;


   /**
    *产品状态
    */
   private String t_FProd_Status;
   /**
    *产品更新时间
    */
   private Date t_FProd_SysupdateDate;
   
   private String t_FProd_category;
   
   private String t_FProd_CorpPool;
   
   private String t_FProd_PersPool;
   
   private String remark;
   
   private String platform;
   
   private Date create_time;
   
   private Date modify_time;
   
   private String Page_count;
   

//----------------------------------------------
   
   /**
    * 创建人
    * @return 
    */
   public String getCreator() {
       return creator;
   }
   /**
    * 创建时间
    * @return 
    */
   public Date getCreateTime() {
       return createTime;
   }
   /**
    * 修改人
    * @return 
    */
   public String getModifier() {
       return modifier;
   }

   /**
    * 修改时间
    * @return 
    */
   public Date getModifyTime() {
       return modifyTime;
   }

   
    public String getT_FProd_ID() {
    	return t_FProd_ID;
    }

    public void setT_FProd_ID(String t_FProd_ID) {
    	this.t_FProd_ID = t_FProd_ID;
    }

    public String getT_FProd_Name() {
    	return t_FProd_Name;
    }

    public void setT_FProd_Name(String t_FProd_Name) {
    	this.t_FProd_Name = t_FProd_Name;
    }

    public BigDecimal getT_FProd_Interest() {
    	return t_FProd_Interest;
    }

    public void setT_FProd_Interest(BigDecimal t_FProd_Interest) {
    	this.t_FProd_Interest = t_FProd_Interest;
    }

    public BigDecimal getT_FProd_OverdueInt() {
    	return t_FProd_OverdueInt;
    }

    public void setT_FProd_OverdueInt(BigDecimal t_FProd_OverdueInt) {
    	this.t_FProd_OverdueInt = t_FProd_OverdueInt;
    }

    public String getT_FProd_Status() {
    	return t_FProd_Status;
    }
    
    public String getPlatform() {
    	return platform;
    }
    
    public String getRemark() {
    	return remark;
    }
    
    public Date getCreate_time() {
    	return create_time;
    }
    
    public Date getModify_time() {
    	return modify_time;
    }
    
    public String Page_count() {
    	return Page_count;
    }
    
    

    public void setT_FProd_Status(String t_FProd_Status) {
    	this.t_FProd_Status = t_FProd_Status;
    }

	public Date getT_FProd_SysupdateDate() {
		return t_FProd_SysupdateDate;
	}

	public void setT_FProd_SysupdateDate(Date t_FProd_SysupdateDate) {
		this.t_FProd_SysupdateDate = t_FProd_SysupdateDate;
	}
	
    /**
     * 创建人
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * 创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 修改人
     * @param modifier
     */	
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    /**
     * 修改人
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public void setPlatform(String platform) {
    	this.platform = platform;
    }
    
    public void setRemark(String remark) {
    	this.remark = remark;
    }
    
    public void setCreate_time(Date create_time) {
    	this.create_time = create_time;
    }
    
    public void setModify_time(Date modify_time) {
    	this.modify_time = modify_time;
    }
    
    public void setPage_count(String Page_count) {
    	this.Page_count = Page_count;
    }
	public BigDecimal getT_FProd_ServiceFee() {
		return t_FProd_ServiceFee;
	}
	public BigDecimal getT_FProd_Poundage() {
		return t_FProd_Poundage;
	}
	public BigDecimal getT_FProd_ETxnAmtLimit() {
		return t_FProd_ETxnAmtLimit;
	}
	public String getT_FProd_TierPoundage() {
		return t_FProd_TierPoundage;
	}
	public void setT_FProd_ServiceFee(BigDecimal t_FProd_ServiceFee) {
		this.t_FProd_ServiceFee = t_FProd_ServiceFee;
	}
	public void setT_FProd_Poundage(BigDecimal t_FProd_Poundage) {
		this.t_FProd_Poundage = t_FProd_Poundage;
	}
	public void setT_FProd_ETxnAmtLimit(BigDecimal t_FProd_ETxnAmtLimit) {
		this.t_FProd_ETxnAmtLimit = t_FProd_ETxnAmtLimit;
	}
	public void setT_FProd_TierPoundage(String t_FProd_TierPoundage) {
		this.t_FProd_TierPoundage = t_FProd_TierPoundage;
	}
	public String getT_FProd_category() {
		return t_FProd_category;
	}
	public String getT_FProd_CorpPool() {
		return t_FProd_CorpPool;
	}
	public String getT_FProd_PersPool() {
		return t_FProd_PersPool;
	}
	public void setT_FProd_category(String t_FProd_category) {
		this.t_FProd_category = t_FProd_category;
	}
	public void setT_FProd_CorpPool(String t_FProd_CorpPool) {
		this.t_FProd_CorpPool = t_FProd_CorpPool;
	}
	public void setT_FProd_PersPool(String t_FProd_PersPool) {
		this.t_FProd_PersPool = t_FProd_PersPool;
	}
}