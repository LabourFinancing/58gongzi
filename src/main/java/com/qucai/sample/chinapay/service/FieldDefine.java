/**
 * 项目名称(中文)
 * 项目名称(英文)
 * Copyright (c) 2016 ChinaPay Ltd. All Rights Reserved.
 */
package com.qucai.sample.chinapay.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.qucai.sample.chinapay.util.StringUtil;

/**
 * 字段定义.
 * 
 * @author hrtc .
 */
public class FieldDefine {
    /**
     * 字段定义保器.
     */
    private static FieldDefine holder = null;
    /**
     * 字段map.
     */
    private Map<String, InterfaceField> fieldMap = new HashMap<String, InterfaceField>();

    /**
     * 初始化 .
     * 
     * @param path
     *            字段文件路径
     * @throws IOException
     *             io异常
     */
    public static void init(String path) throws IOException {
        synchronized (FieldDefine.class) {
            Properties p = LoadConfService.loadProperties(path);

            holder = new FieldDefine();

            Enumeration enumeration = p.keys();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                if (StringUtil.isEmpty(key)) {
                    continue;
                }
                String value = p.getProperty(key);

                InterfaceField field = InterfaceField.createInstance(key + ","
                        + value);

                holder.fieldMap.put(key, field);
            }
        }
    }

    /**
     * 获得实例 .
     * 
     * @return 字段定义
     */
    public static FieldDefine getInstance() {
        return holder;
    }

    /**
     * 获得字段 .
     * 
     * @param key
     *            键
     * @return 字段
     */
    public InterfaceField getField(String key) {
        return fieldMap.get(key);
    }
}
