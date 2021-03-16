package com.qucai.sample.vo;

import com.qucai.sample.entity.Resource;

public class ResourceGrant extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 是否已选择，0：否；1：是	
	 */
    private Integer selected;
    
    /**
     * 是否叶子节点，0：否；1：是
     */
    private boolean isLeaf;

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

	public boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
