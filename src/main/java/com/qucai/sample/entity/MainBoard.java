//package com.qucai.sample.entity;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
///**
// * 
// * 
// * @version 1.0 2016-08-09
// */
//public class MainBoard implements Serializable {
//    /**
//	 * 
//	 */
//   private static final long serialVersionUID = -1876948347463745808L;
//   /**
//    * 创建人
//    */
//   private String creator;
//   /**
//    * 创建时间
//    */
//   private Date createTime;
//   /**
//    * 修改人
//    * @return 
//    */
//   private String modifier;
//   
//   /*
//    * 修改时间
//    */
//   private Date modifyTime;
//	
//   /**
//    * 产品ID
//    */
//   private String t_FProd_ID;
//
//   /**
//    * 产品名
//    */
//   private String t_FProd_Name;
//
///**
//    * 产品利率
//    */
//   private BigDecimal t_FProd_Interest;
//
//   /**
//    * 产品超期利率
//    */
//   private BigDecimal t_FProd_OverdueInt;
//
//   /**
//    *产品状态
//    */
//   private String t_FProd_Status;
//   /**
//    *产品更新时间
//    */
//   private Date t_FProd_SysupdateDate;
//   
//   private String remark;
//   
//   private String platform;
//   
//   private Date create_time;
//   
//   private Date modify_time;
//   
//
////----------------------------------------------
//   
//   /**
//    * 创建人
//    * @return 
//    */
//   public String getCreator() {
//       return creator;
//   }
//   /**
//    * 创建时间
//    * @return 
//    */
//   public Date getCreateTime() {
//       return createTime;
//   }
//   /**
//    * 修改人
//    * @return 
//    */
//   public String getModifier() {
//       return modifier;
//   }
//
//   /**
//    * 修改时间
//    * @return 
//    */
//   public Date getModifyTime() {
//       return modifyTime;
//   }
//
//   
//    public String getT_FProd_ID() {
//    	return t_FProd_ID;
//    }
//
//    public void setT_FProd_ID(String t_FProd_ID) {
//    	this.t_FProd_ID = t_FProd_ID;
//    }
//
//    public String getT_FProd_Name() {
//    	return t_FProd_Name;
//    }
//
//    public void setT_FProd_Name(String t_FProd_Name) {
//    	this.t_FProd_Name = t_FProd_Name;
//    }
//
//    public BigDecimal getT_FProd_Interest() {
//    	return t_FProd_Interest;
//    }
//
//    public void setT_FProd_Interest(BigDecimal t_FProd_Interest) {
//    	this.t_FProd_Interest = t_FProd_Interest;
//    }
//
//    public BigDecimal getT_FProd_OverdueInt() {
//    	return t_FProd_OverdueInt;
//    }
//
//    public void setT_FProd_OverdueInt(BigDecimal t_FProd_OverdueInt) {
//    	this.t_FProd_OverdueInt = t_FProd_OverdueInt;
//    }
//
//    public String getT_FProd_Status() {
//    	return t_FProd_Status;
//    }
//    
//    public String getPlatform() {
//    	return platform;
//    }
//    
//    public String getRemark() {
//    	return remark;
//    }
//    
//    public Date getCreate_time() {
//    	return create_time;
//    }
//    
//    public Date getModify_time() {
//    	return modify_time;
//    }
//    
//    
//
//    public void setT_FProd_Status(String t_FProd_Status) {
//    	this.t_FProd_Status = t_FProd_Status;
//    }
//
//	public Date getT_FProd_SysupdateDate() {
//		return t_FProd_SysupdateDate;
//	}
//
//	public void setT_FProd_SysupdateDate(Date t_FProd_SysupdateDate) {
//		this.t_FProd_SysupdateDate = t_FProd_SysupdateDate;
//	}
//	
//    /**
//     * 创建人
//     * @param creator
//     */
//    public void setCreator(String creator) {
//        this.creator = creator;
//    }
//    /**
//     * 创建时间
//     * @param createTime
//     */
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//    /**
//     * 修改人
//     * @param modifier
//     */	
//    public void setModifier(String modifier) {
//        this.modifier = modifier;
//    }
//    /**
//     * 修改人
//     * @param modifyTime
//     */
//    public void setModifyTime(Date modifyTime) {
//        this.modifyTime = modifyTime;
//    }
//    
//    public void setPlatform(String platform) {
//    	this.platform = platform;
//    }
//    
//    public void setRemark(String remark) {
//    	this.remark = remark;
//    }
//    
//    public void setCreate_time(Date create_time) {
//    	this.create_time = create_time;
//    }
//    
//    public void setModify_time(Date modify_time) {
//    	this.modify_time = modify_time;
//    }
//}