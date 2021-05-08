/**
 * 项目名称(中文)
 * 项目名称(英文)
 * Copyright (c) 2016 ChinaPay Ltd. All Rights Reserved.
 */
package com.qucai.sample.chinapay.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.qucai.sample.chinapay.util.StringUtil;

/**
 * 接口缓存.
 * 
 * @author hrtc .
 */
public class InterfaceCache {
    /**
     * 日志.
     */
    private static Logger logger = Logger.getLogger(InterfaceCache.class);
    /**
     * 接口map.
     */
    public static Map<String, InterfaceCache> interfaceCacheMap = new HashMap<String, InterfaceCache>();
    /**
     * 交易类型.
     */
    private String tranType;
    /**
     * 请求字段.
     */
    private List<InterfaceField> reqField = new ArrayList<InterfaceField>();
    /**
     * 响应字段.
     */
    private List<InterfaceField> resField = new ArrayList<InterfaceField>();
    /**
     * 前台请求地址.
     */
    private String frontRequestUrl;
    /**
     * 后台请求地址.
     */
    private String backRequestUrl;
    /**
     * 后台异步转同步请求地址.
     */
    private String backAsyncToSyncRequestUrl;

    /**
     * 请求url map.
     */
    private static Map<String, String> urlMap = new HashMap<String, String>();

    /**
     * 初始化url .
     * 
     * @param path
     *            路径
     * @throws IOException
     *             业务异常
     */
    public static void initUrl(String path) throws IOException {
        Properties p = LoadConfService.loadProperties(path);

        Enumeration enumeration = p.keys();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            if (StringUtil.isEmpty(key)) {
                continue;
            }
            String value = p.getProperty(key);

            if (StringUtil.isEmpty(value)) {
                continue;
            }
            urlMap.put(key, value);
        }
    }

    /**
     * 获得接口 .
     * 
     * @param basePath
     *            配置文件根路径
     * @param interfaceType
     *            接口类型
     * @param tranType
     *            交易类型
     * @param subTranType
     *            交易子类型
     * @return 接口
     * @throws IOException io异常
     */
    public static InterfaceCache getInstance(String basePath,
            String interfaceType, String tranType, String subTranType)
            throws IOException {
        if (StringUtil.isEmpty(tranType)) {
            return null;
        }

        String key = null;
        if (StringUtil.isEmpty(interfaceType)) {
            interfaceType = "comm";
        }
        if (StringUtil.isEmpty(subTranType)) {
            key = String.format("%s_%s", interfaceType, tranType);
            subTranType = "";
        } else {
            key = String.format("%s_%s_%s", interfaceType, tranType,
                    subTranType);
        }

        InterfaceCache interfaceCache = interfaceCacheMap.get(key);
        if (interfaceCache == null) {
            synchronized (interfaceCacheMap) {
                interfaceCache = interfaceCacheMap.get(key);
                if (interfaceCache == null) {
                    String path = String.format("%s/%s/%s.properties",
                            basePath, interfaceType, tranType + subTranType);
                    Properties p = LoadConfService.loadProperties(path);

                    String frontRequestUrl = p.getProperty("frontRequestUrl");
                    if (frontRequestUrl != null && frontRequestUrl.startsWith("${")) {
                        String urlKey = frontRequestUrl.substring(2,
                                frontRequestUrl.length() - 1);
                        frontRequestUrl = urlMap.get(urlKey);
                    }

                    String backRequestUrl = p.getProperty("backRequestUrl");
                    if (backRequestUrl != null && backRequestUrl.startsWith("${")) {
                        String urlKey = backRequestUrl.substring(2,
                                backRequestUrl.length() - 1);
                        backRequestUrl = urlMap.get(urlKey);
                    }
                    
                    String backAsyncToSyncRequestUrl = p.getProperty("backAsyncToSyncRequestUrl");
                    if (backAsyncToSyncRequestUrl != null && backAsyncToSyncRequestUrl.startsWith("${")) {
                        String urlKey = backAsyncToSyncRequestUrl.substring(2,
                                backAsyncToSyncRequestUrl.length() - 1);
                        backAsyncToSyncRequestUrl = urlMap.get(urlKey);
                    }

                    String req = p.getProperty("reqField");
                    if (StringUtil.isEmpty(req)) {
                        return null;
                    }
                    String res = p.getProperty("resField");
                    if (StringUtil.isEmpty(res)) {
                        return null;
                    }
                    interfaceCache = new InterfaceCache();
                    interfaceCache.tranType = tranType;
                    interfaceCache.frontRequestUrl = frontRequestUrl;
                    interfaceCache.backRequestUrl = backRequestUrl;
                    interfaceCache.backAsyncToSyncRequestUrl = backAsyncToSyncRequestUrl;

                    buildField(req, interfaceCache.reqField);
                    buildField(res, interfaceCache.resField);

                    interfaceCacheMap.put(key, interfaceCache);
                }
            }
        }

        return interfaceCache;
    }

    /**
     * .
     * 
     * @param fieldStr 字段
     * @param fieldList 字段列表
     */
    private static void buildField(String fieldStr,
            List<InterfaceField> fieldList) {
        FieldDefine fd = FieldDefine.getInstance();
        String[] strs = fieldStr.split("\\|", -1);
        for (String str : strs) {
            String[] fieldStrs = str.split(",", -1);
            if (fieldStrs.length < 2) {
                continue;
            }
            String key = fieldStrs[0];
            String defaultValue = fieldStrs[1];
            InterfaceField field = (InterfaceField) fd.getField(key);
            if (field == null) {
                throw new RuntimeException(String.format("%s not config.", key));
            }
            field = (InterfaceField) field.clone();
            if (StringUtil.isNotEmpty(defaultValue)) {
                field.setDefaultValue(defaultValue);
            }
            fieldList.add(field);

            logger.info("field==========" + field);
        }
    }

    /**
     * get tranType.
     * 
     * @return tranType
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * set tranType.
     * 
     * @param tranType
     *            tranType
     */
    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    /**
     * get reqField.
     * 
     * @return reqField
     */
    public List<InterfaceField> getReqField() {
        return reqField;
    }

    /**
     * set reqField.
     * 
     * @param reqField
     *            reqField
     */
    public void setReqField(List<InterfaceField> reqField) {
        this.reqField = reqField;
    }

    /**
     * get resField.
     * 
     * @return resField
     */
    public List<InterfaceField> getResField() {
        return resField;
    }

    /**
     * set resField.
     * 
     * @param resField
     *            resField
     */
    public void setResField(List<InterfaceField> resField) {
        this.resField = resField;
    }

    /**
     * get frontRequestUrl.
     * 
     * @return frontRequestUrl
     */
    public String getFrontRequestUrl() {
        return frontRequestUrl;
    }

    /**
     * set frontRequestUrl.
     * 
     * @param frontRequestUrl
     *            frontRequestUrl
     */
    public void setFrontRequestUrl(String frontRequestUrl) {
        this.frontRequestUrl = frontRequestUrl;
    }

    /**
     * get backRequestUrl.
     * 
     * @return backRequestUrl
     */
    public String getBackRequestUrl() {
        return backRequestUrl;
    }

    /**
     * set backRequestUrl.
     * 
     * @param backRequestUrl
     *            backRequestUrl
     */
    public void setBackRequestUrl(String backRequestUrl) {
        this.backRequestUrl = backRequestUrl;
    }

    /**
     * get backAsyncToSyncRequestUrl.
     * @return backAsyncToSyncRequestUrl
     */
    public String getBackAsyncToSyncRequestUrl() {
        return backAsyncToSyncRequestUrl;
    }

    /**
     * set backAsyncToSyncRequestUrl.
     * @param backAsyncToSyncRequestUrl backAsyncToSyncRequestUrl
     */
    public void setBackAsyncToSyncRequestUrl(String backAsyncToSyncRequestUrl) {
        this.backAsyncToSyncRequestUrl = backAsyncToSyncRequestUrl;
    }

    
}
