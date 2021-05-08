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

import org.apache.log4j.Logger;

/**
 * @author hrtc .
 */
public class DictCache {
    /**
     * 日志.
     */
    private static Logger logger = Logger.getLogger(DictCache.class);
    /**
     * 字典缓存.
     */
    public static Map<String, DictCache> dictCacheMap;
    /**
     * 数据字典类型.
     */
    private String dictType;
    /**
     * 字典代码map.
     */
    private Map<String, DictCode> dictCodeMap = new HashMap<String, DictCode>();

    /**
     * 初始化数据字典.
     * 
     * @param path
     *            数据字典路径
     * @throws IOException
     *             io异常
     */
    public static void init(String path) throws IOException {
        synchronized (DictCache.class) {
            Properties p = LoadConfService.loadProperties(path);
            dictCacheMap = new HashMap<String, DictCache>();

            Enumeration enumeration = p.keys();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = p.getProperty(key);

                DictCache dictCache = new DictCache();
                dictCache.dictType = key;

                String[] strs = value.split("\\|", -1);
                for (String str : strs) {
                    String[] strValues = str.split(",", -1);
                    if (strValues.length == 2) {
                        DictCode dictCode = new DictCode();
                        dictCode.setValue(strValues[0]);
                        dictCode.setName(strValues[1]);
                        dictCache.dictCodeMap
                                .put(dictCode.getValue(), dictCode);
                    }
                }

                dictCacheMap.put(dictCache.dictType, dictCache);
            }
        }
    }

    /**
     * 获得字典.
     * 
     * @param dictType
     *            字典类型
     * @return 字典缓存
     */
    public static DictCache getDictCache(String dictType) {
        return dictCacheMap.get(dictType);
    }

    /**
     * 获得字典代码.
     * 
     * @param dictValue
     *            字典值
     * @return 字典代码
     */
    public DictCode getDictCode(String dictValue) {
        return this.dictCodeMap.get(dictValue);
    }

    /**
     * get dictCodeMap.
     * 
     * @return dictCodeMap
     */
    public Map<String, DictCode> getDictCodeMap() {
        return dictCodeMap;
    }

}
