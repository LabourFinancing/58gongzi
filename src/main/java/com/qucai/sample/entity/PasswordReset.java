package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-05
 */
public class PasswordReset implements Serializable {
	 
	
	   private static final long serialVersionUID = -1876948347463745808L;
	/**
     * id
     * @return 
     */
	   private String prt_id;

	    /**
	     * 用户名
	     */
	    private String prt_user_name;

	    /**
	     * 密码
	     */
	    private String prt_ResetPassword;

	    /**
	     * 真实姓名
	     */
	    private String prt_real_name;

	    /**
	     * 手机
	     */
	    private String prt_company_name;

	    /**
	     * 座机
	     */
	    private String prt_PersonaID;

	    /**
	     * 邮箱
	     */
	    private String prt_mobile;

	    /**
	     * 状态
	     */
	    private Integer prt_status;

	    /**
	     * 创建时间
	     */
	    private Date create_time;
	    /**
	     * 更改时间
	     */
	    private Date modify_time;
	    
	    
		public String getPrt_id() {
			return prt_id;
		}
		public String getPrt_user_name() {
			return prt_user_name;
		}
		public String getPrt_ResetPassword() {
			return prt_ResetPassword;
		}
		public String getPrt_real_name() {
			return prt_real_name;
		}
		public String getPrt_company_name() {
			return prt_company_name;
		}
		public String getPrt_PersonaID() {
			return prt_PersonaID;
		}
		public String getPrt_mobile() {
			return prt_mobile;
		}
		public Integer getPrt_status() {
			return prt_status;
		}
		public Date getCreate_time() {
			return create_time;
		}
		public Date getModify_time() {
			return modify_time;
		}
		public void setPrt_id(String prt_id) {
			this.prt_id = prt_id;
		}
		public void setPrt_user_name(String prt_user_name) {
			this.prt_user_name = prt_user_name;
		}
		public void setPrt_ResetPassword(String prt_ResetPassword) {
			this.prt_ResetPassword = prt_ResetPassword;
		}
		public void setPrt_real_name(String prt_real_name) {
			this.prt_real_name = prt_real_name;
		}
		public void setPrt_company_name(String prt_company_name) {
			this.prt_company_name = prt_company_name;
		}
		public void setPrt_PersonaID(String prt_PersonaID) {
			this.prt_PersonaID = prt_PersonaID;
		}
		public void setPrt_mobile(String prt_mobile) {
			this.prt_mobile = prt_mobile;
		}
		public void setPrt_status(Integer prt_status) {
			this.prt_status = prt_status;
		}
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public void setModify_time(Date modify_time) {
			this.modify_time = modify_time;
		}

}
