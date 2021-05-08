/**
 * 项目名称(中文)
 * 项目名称(英文)
 * Copyright (c) 2016 ChinaPay Ltd. All Rights Reserved.
 */
package com.qucai.sample.chinapay.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 加载属性服务.
 * 
 * @author hrtc .
 */
public class LoadConfService {
    /**
     * 日志.
     */
    private static Logger logger = Logger.getLogger(LoadConfService.class);

    /**
     * 加载配置文件 .
     * @param path
     *            路径
     * @return 属性
     * @throws IOException
     *             io异常
     */
    public static Properties loadProperties(String path) throws IOException {
        InputStream inStream = null;
        try {
            logger.info(String.format("path=%s", path));
            inStream = new FileInputStream(path);
            Properties p = new Properties();
            p.load(inStream);
            return p;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e1) {
                    // ignore
                }
            }
        }
    }
}
