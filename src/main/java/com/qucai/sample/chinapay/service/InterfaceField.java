/**
 * 项目名称(中文)
 * 项目名称(英文)
 * Copyright (c) 2016 ChinaPay Ltd. All Rights Reserved.
 */
package com.qucai.sample.chinapay.service;

import java.util.Map;

import com.qucai.sample.chinapay.util.StringUtil;

/**
 * 接口字段.<br>
 * 格式定义:字段名称,字段描述,是否必填1是0否,格式0文本1下拉列表,下拉列表的话关联的数据字典名称否则为空,默认值|...|...
 * 
 * @author hrtc .
 */
public class InterfaceField implements Cloneable {
    /**
     * 创建实例. .
     * 
     * @param str
     *            配置字符串
     * @return 接口字段
     */
    public static InterfaceField createInstance(String str) {
        if (StringUtil.isEmpty(str)) {
            throw new RuntimeException("格式错误");
        }
        String[] strs = str.split(",", -1);
        if (strs.length < 6) {
            throw new RuntimeException("格式错误");
        }
        InterfaceField interfaceField = new InterfaceField();
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                interfaceField.id = strs[i];
            } else if (i == 1) {
                interfaceField.desc = strs[i];
            } else if (i == 2) {
                interfaceField.isRequired = strs[i];
                if (StringUtil.isEmpty(interfaceField.isRequired)) {
                    interfaceField.isRequired = "0";
                }
            } else if (i == 3) {
                interfaceField.type = strs[i];
                if (StringUtil.isEmpty(interfaceField.type)) {
                    interfaceField.type = "0";
                }
            } else if (i == 4) {
                interfaceField.dictId = strs[i];
            } else if (i == 5) {
                interfaceField.defaultValue = strs[i];
            }
        }

        return interfaceField;
    }

    /**
     * 字段id.
     */
    private String id;
    /**
     * 描述.
     */
    private String desc;
    /**
     * 是否必填1是0否,默认为0.
     */
    private String isRequired;
    /**
     * 格式0文本1下拉列表，默认为0.
     */
    private String type;
    /**
     * 数据字典编号.
     */
    private String dictId;
    /**
     * 默认值.
     */
    private String defaultValue;

    /**
     * get id.
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * set id.
     * 
     * @param id
     *            id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get desc.
     * 
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * set desc.
     * 
     * @param desc
     *            desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * get isRequired.
     * 
     * @return isRequired
     */
    public String getIsRequired() {
        return isRequired;
    }

    /**
     * set isRequired.
     * 
     * @param isRequired
     *            isRequired
     */
    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * get type.
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * set type.
     * 
     * @param type
     *            type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get dictId.
     * 
     * @return dictId
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * set dictId.
     * 
     * @param dictId
     *            dictId
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    /**
     * get defaultValue.
     * 
     * @return defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * get defaultValue.
     * 
     * @param ctx
     *            上下文
     * @return defaultValue
     */
    public String getDefaultValue(Map<String, String> ctx) {
        if (StringUtil.isEmpty(defaultValue)) {
            return "";
        }
        if (defaultValue.indexOf("${") != -1) {
            String key = defaultValue.substring(2, defaultValue.length() - 1);
            String value = ctx.get(key);
            if (value == null) {
                value = "";
            }
            return value;
        }
        return defaultValue;
    }

    /**
     * set defaultValue.
     * 
     * @param defaultValue
     *            defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString() .
     * 
     * @return
     */
    @Override
    public String toString() {
        return String
                .format("InterfaceField [id=%s, desc=%s, isRequired=%s, type=%s, dictId=%s, defaultValue=%s]",
                        id, desc, isRequired, type, dictId, defaultValue);
    }

    /**
     * .
     * @return .
     */
    public Object clone() {
        InterfaceField o = null;
        try {
            o = (InterfaceField) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
