package com.qucai.sample.vo;

import com.qucai.sample.entity.Role;

public class RoleGrant extends Role {

	/**
	 * 是否已选择，0：否；1：是
	 */
    private Integer selected;

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

}
