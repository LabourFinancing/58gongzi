package com.qucai.sample.chinapay.demo.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinapay.secss.SecssConstants;
import com.chinapay.secss.SecssUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.qucai.sample.chinapay.demo.comm.Constants;
import com.qucai.sample.chinapay.demo.util.HttpUtils;
import com.qucai.sample.chinapay.service.DictCache;
import com.qucai.sample.chinapay.service.FieldDefine;
import com.qucai.sample.chinapay.service.InterfaceCache;
import com.qucai.sample.chinapay.util.StringUtil;

/**
 * cp接口入口.
 */
public class CPInterfaceServlet extends HttpServlet {
    /**
     * .
     */
    private static final long serialVersionUID = 1L;
    /**
     * 日志.
     */
    private static Logger logger = Logger.getLogger(CPInterfaceServlet.class);

    /**
     * 配置文件根路径.
     */
    private static String certBasePath = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CPInterfaceServlet() {
        super();
    }

    /**
     * 同步应答需要encoding的交易类型.
     */
    private static Set<String> encodingTransTypes = new HashSet<String>();
    
    static {
        encodingTransTypes.add("0004");
        encodingTransTypes.add("0504");
        encodingTransTypes.add("0505");
        encodingTransTypes.add("0606");
        encodingTransTypes.add("0608");
        encodingTransTypes.add("9904");
        encodingTransTypes.add("9905");
        
        encodingTransTypes.add("0006");
        encodingTransTypes.add("0505");
        encodingTransTypes.add("0601");
        encodingTransTypes.add("0607");
        encodingTransTypes.add("9901");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig) .
     * 
     * @param config
     * 
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // 初始化字段和数据字典
        String basePath = config.getServletContext().getRealPath(
                Constants.FILE_SPLIT_STR);
        try {
            // 配置文件根路径
            certBasePath = String.format("%s/WEB-INF/classes", basePath);

            // 字段路径
            String path = String.format(
                    "%s/WEB-INF/classes/interface/field.properties", basePath);

            FieldDefine.init(path);

            // 数据字典路径
            path = String.format(
                    "%s/WEB-INF/classes/interface/dict.properties", basePath);
            DictCache.init(path);

            // 请求路径
            path = String.format(
                    "%s/WEB-INF/classes/interface/url.properties", basePath);
            InterfaceCache.initUrl(path);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * .
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * .
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(Constants.ENCODING);
        response.setCharacterEncoding(Constants.ENCODING);

        String method = request.getParameter(Constants.SPEC_METHOD);
        if (Constants.METHOD_GEN_REQUEST_PAGE.equals(method)) {
            // 生成请求页面
            doGenRequestPage(request, response);
        } else if (Constants.METHOD_PACK.equals(method)) {
            // 组包并跳转提交页面
            doPack(request, response);
        } else if (Constants.METHOD_SEND.equals(method)) {
            // 发送并跳转响应页面
            doSend(request, response);
        } else if (Constants.METHOD_UNPACK.equals(method)) {
            // 解包并跳转响应页面
            doUnPack(request, response);
        } else {
            request.setAttribute(Constants.RESP_CODE,
                    Constants.DEFAULT_ERROR_CODE);
            request.setAttribute(Constants.RESP_MSG, "不支持的方法，method=" + method);
            request.getRequestDispatcher("/page/showError.jsp").forward(
                    request, response);
        }
    }

    /**
     * 生成请求页面.
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected void doGenRequestPage(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String interfaceType = request
                .getParameter(Constants.SPEC_INTERFACE_TYPE);
        String tranType = request.getParameter(Constants.SPEC_TRAN_TYPE);
        String subTranType = request.getParameter(Constants.SPEC_SUB_TRAN_TYPE);
        String basePath = request.getSession().getServletContext()
                .getRealPath(Constants.FILE_SPLIT_STR)
                + "/WEB-INF/classes/interface";
        logger.info(String
                .format("生成页面开始,interfaceType=%s,tranType=%s,subTranType=%s,basePath=%s",
                        interfaceType, tranType, subTranType, basePath));
        InterfaceCache interfaceCache = InterfaceCache.getInstance(basePath,
                interfaceType, tranType, subTranType);
        request.setAttribute(Constants.INTERFACE_CACHE, interfaceCache);

        request.getRequestDispatcher("/page/showInput.jsp").forward(request,
                response);
        logger.info(String
                .format("生成页面结束,interfaceType=%s,tranType=%s,subTranType=%s,basePath=%s",
                        interfaceType, tranType, subTranType, basePath));

    }

    /**
     * 组包.
     * 
     * @param request .
     * @param response .
     */
    protected void doPack(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 请求map
            Map<String, String> sendMap = new TreeMap<String, String>();
            // 风控保留域
            JSONObject riskParamsJson = new JSONObject();
            // 交易保留域
            JSONObject transParamsJson = new JSONObject();
            // 卡信息保留域
            JSONObject cardParamsJson = new JSONObject();
            Enumeration<String> paraNames = request.getParameterNames();
            while (paraNames.hasMoreElements()) {
                String key = paraNames.nextElement();
                // 过滤特殊字段
                if (key.startsWith(Constants.SPEC_PRIFEX)) {
                    continue;
                }
                // 过滤空字段
                String value = request.getParameter(key);
                if (StringUtil.isEmpty(value)) {
                    continue;
                }
                // 解析保留域字段
                if (key.indexOf(Constants.FILE_SPLIT_STR) != -1) {
                    // 保留域
                    String[] strs = key.split(Constants.FILE_SPLIT_STR, -1);
                    if (Constants.TRAN_RESERVED.equals(strs[0])) {
                        // 交易保留域
                        transParamsJson.put(strs[1], value);
                    } else if (Constants.CARD_TRAN_DATA.equals(strs[0])) {
                        // 卡保留域
                        cardParamsJson.put(strs[1], value);
                    } else if (Constants.RISK_DATA.equals(strs[0])) {
                        // 风控保留域
                        riskParamsJson.put(strs[1], value);
                    }
                } else {
                    // 普通字段
                    sendMap.put(key, value);
                }
            }

            // 机构或商户接入
            String instuId = sendMap.get(Constants.INSTU_ID);
            String merId = sendMap.get(Constants.MER_ID);
            String ownerId = null;
            if (StringUtil.isNotEmpty(instuId)) {
                ownerId = instuId;
            } else {
                ownerId = merId;
            }
            // 获得签名加密类
            SecssUtil secssUtil = getSecssUtil(ownerId);

            // 构建交易保留域
            String strTranReserved = null;
            if (transParamsJson.length() > 0) {
                strTranReserved = transParamsJson.toString();
            }
            
            // 构建卡信息保留域
            String strCardTranData = null;
            if (cardParamsJson.length() > 0) {
                strCardTranData = cardParamsJson.toString();
                logger.info(String.format("加密前的卡保留域=%s", cardParamsJson));
                // 加密
                strCardTranData = Base64.encodeBase64String(strCardTranData
                        .getBytes(Constants.ENCODING));
                secssUtil.encryptData(strCardTranData);
                if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                    strCardTranData = secssUtil.getEncValue();
                } else {
                    request.setAttribute(Constants.RESP_CODE,
                            secssUtil.getErrCode());
                    request.setAttribute(Constants.RESP_MSG,
                            secssUtil.getErrMsg());
                    request.getRequestDispatcher("/page/showError.jsp")
                            .forward(request, response);
                    return;
                }
            }

            // 构建风控保留域
            String strRiskData = null;
            if (riskParamsJson.length() > 0) {
                strRiskData = riskParamsJson.toString();
                logger.info(String.format("加密前的风控保留域=%s", riskParamsJson));
                // 加密
                strRiskData = Base64.encodeBase64String(strRiskData
                        .getBytes(Constants.ENCODING));
                secssUtil.encryptData(strRiskData);
                if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                    strRiskData = secssUtil.getEncValue();
                } else {
                    request.setAttribute(Constants.RESP_CODE,
                            secssUtil.getErrCode());
                    request.setAttribute(Constants.RESP_MSG,
                            secssUtil.getErrMsg());
                    request.getRequestDispatcher("/page/showError.jsp")
                            .forward(request, response);
                    return;
                }
            }

            
            if (strTranReserved != null) {
                sendMap.put(Constants.TRAN_RESERVED, strTranReserved);
            }
            if (strCardTranData != null) {
                sendMap.put(Constants.CARD_TRAN_DATA, strCardTranData);
            }
            if (strRiskData != null) {
                sendMap.put(Constants.RISK_DATA, strRiskData);
            }

            // 签名
            secssUtil.sign(sendMap);
            if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                sendMap.put(Constants.SIGNATURE, secssUtil.getSign());
            } else {
                request.setAttribute(Constants.RESP_CODE,
                        secssUtil.getErrCode());
                request.setAttribute(Constants.RESP_MSG, secssUtil.getErrMsg());
                request.getRequestDispatcher("/page/showError.jsp").forward(
                        request, response);
                return;
            }

            // 将请求map放到下个页面准备提交
            request.setAttribute(Constants.SEND_MAP, sendMap);

            // 获得接口提交地址
            String requestUrl = request
                    .getParameter(Constants.SPEC_REQUEST_URL);
            request.setAttribute(Constants.REQUEST_URL, requestUrl);
            if (requestUrl.indexOf("/page/nref") == -1) {
                // 后台地址非浏览器跳转需要通过后台转发，前台地址直接页面提交即可
                requestUrl = request.getContextPath()
                        + "/cPInterfaceServlet.do?__method=send&__requestUrl="
                        + URLEncoder.encode(requestUrl, Constants.ENCODING);
            }
            request.setAttribute(Constants.SPEC_REQUEST_URL, requestUrl);

            // 展示请求参数页面
            request.getRequestDispatcher("/page/sendRequest.jsp").forward(
                    request, response);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 
     * .
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected void doSend(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> sendMap = new TreeMap<String, String>();
        Enumeration<String> paraNames = request.getParameterNames();
        while (paraNames.hasMoreElements()) {
            String key = paraNames.nextElement();
            // 排除特殊字段
            if (key.startsWith(Constants.SPEC_PRIFEX)) {
                continue;
            }
            // 排除空字段
            String value = request.getParameter(key);
            if (StringUtil.isEmpty(value)) {
                continue;
            }
            sendMap.put(key, value);
        }

        String requestUrl = request.getParameter(Constants.SPEC_REQUEST_URL);

        String resp = HttpUtils.send(requestUrl, sendMap);
        request.setAttribute(Constants.PACKET, resp);

        String transType = sendMap.get("TranType");
        // 解析同步应答字段
        String[] strs = resp.split("&", -1);
        Map<String, String> resultMap = new TreeMap<String, String>();
        for (String str : strs) {
            String[] keyValues = str.split("=", -1);
            if (keyValues.length < 2) {
                continue;
            }
            String key = keyValues[0];
            String value = keyValues[1];
            if (StringUtil.isEmpty(value)) {
                continue;
            }
            
            if (encodingTransTypes.contains(transType)) {// 加交易类型判断
                value = URLDecoder.decode(value, Constants.ENCODING);
            }
            resultMap.put(key, value);
        }
        // 机构或商户接入
        String instuId = sendMap.get(Constants.INSTU_ID);
        String merId = sendMap.get(Constants.MER_ID);
        String ownerId = null;
        if (StringUtil.isNotEmpty(instuId)) {
            ownerId = instuId;
        } else {
            ownerId = merId;
        }
        SecssUtil secssUtil = this.getSecssUtil(ownerId);
        //验签
        String sign = resultMap.get(Constants.SIGNATURE);
        if (StringUtil.isNotEmpty(sign)) {
            secssUtil.verify(resultMap);
        } else {
            //TODO:抛异常
        }
        if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtil.isEmpty(value)) {
                    continue;
                }

                // 解密交易保留域
                if (Constants.CARD_TRAN_DATA.equals(key)) {
                    secssUtil.decryptData(value);
                    if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                        value = secssUtil.getDecValue();
                        // value = new String(Base64.decodeBase64(value),
                        // Constants.ENCODING);
                    } else {
                        request.setAttribute(Constants.RESP_CODE,
                                secssUtil.getErrCode());
                        request.setAttribute(Constants.RESP_MSG,
                                secssUtil.getErrMsg());
                        request.getRequestDispatcher("/page/showError.jsp")
                                .forward(request, response);
                        return;
                    }
                    resultMap.put(key, value);
                }
                // 解密风控保留域
                if (Constants.RISK_DATA.equals(key)) {
                    secssUtil.decryptData(value);
                    if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                        value = secssUtil.getDecValue();
                        // value = new String(Base64.decodeBase64(value),
                        // Constants.ENCODING);
                    } else {
                        request.setAttribute(Constants.RESP_CODE,
                                secssUtil.getErrCode());
                        request.setAttribute(Constants.RESP_MSG,
                                secssUtil.getErrMsg());
                        request.getRequestDispatcher("/page/showError.jsp")
                                .forward(request, response);
                        return;
                    }
                    resultMap.put(key, value);
                }
            }
            
            // 显示结果
            request.setAttribute(Constants.RESULT_MAP, resultMap);
            request.getRequestDispatcher("/page/showResult.jsp").forward(
                    request, response);
        } else {
            request.setAttribute(Constants.RESP_CODE, secssUtil.getErrCode());
            request.setAttribute(Constants.RESP_MSG, secssUtil.getErrMsg());
            request.getRequestDispatcher("/page/showError.jsp").forward(
                    request, response);
        }

    }

    /**
     * 
     * .
     * @param request .
     * @param response .
     * @throws ServletException .
     * @throws IOException .
     */
    protected void doUnPack(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // 通知类型
        String notifyType = request.getParameter(Constants.SPEC_NOTIFY_TYPE);
        if(StringUtil.isEmpty(notifyType)) {
            notifyType = Constants.NOTIFY_TYPE_BACK;
        }
        
        Map<String, String> resultMap = new TreeMap<String, String>();
        Enumeration<String> paraNames = request.getParameterNames();
        while (paraNames.hasMoreElements()) {
            String key = paraNames.nextElement();

            // 跳过自定义字段
            if (key.startsWith(Constants.SPEC_PRIFEX)) {
                continue;
            }
            // 跳过空字段
            String value = request.getParameter(key);
            if (StringUtil.isEmpty(value)) {
                continue;
            }
            
            // 后台通知需要解码,正式使用建议前后台接收通知地址分开
            if(Constants.NOTIFY_TYPE_BACK.equals(notifyType)) {
                value = URLDecoder.decode(value, Constants.ENCODING);
            }
            resultMap.put(key, value);
        }
        
        // 按机构或商户验签
        String instuId = resultMap.get(Constants.INSTU_ID);
        String merId = resultMap.get(Constants.MER_ID);
        String ownerId = null;
        if (StringUtil.isNotEmpty(instuId)) {
            ownerId = instuId;
        } else {
            ownerId = merId;
        }
        SecssUtil secssUtil = this.getSecssUtil(ownerId);
        secssUtil.verify(resultMap);
        if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtil.isEmpty(value)) {
                    continue;
                }
                // 解析卡保留域
                if (Constants.CARD_TRAN_DATA.equals(key)) {
                    secssUtil.decryptData(value);
                    if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                        value = secssUtil.getDecValue();
                        // value = new String(Base64.decodeBase64(value),
                        // Constants.ENCODING);
                    } else {
                        request.setAttribute(Constants.RESP_CODE,
                                secssUtil.getErrCode());
                        request.setAttribute(Constants.RESP_MSG,
                                secssUtil.getErrMsg());
                        request.getRequestDispatcher("/page/showError.jsp")
                                .forward(request, response);
                        return;
                    }
                    resultMap.put(key, value);
                }
                if (Constants.RISK_DATA.equals(key)) {
                    secssUtil.decryptData(value);
                    if (SecssConstants.SUCCESS.equals(secssUtil.getErrCode())) {
                        value = secssUtil.getDecValue();
                        // value = new String(Base64.decodeBase64(value),
                        // Constants.ENCODING);
                    } else {
                        request.setAttribute(Constants.RESP_CODE,
                                secssUtil.getErrCode());
                        request.setAttribute(Constants.RESP_MSG,
                                secssUtil.getErrMsg());
                        request.getRequestDispatcher("/page/showError.jsp")
                                .forward(request, response);
                        return;
                    }
                    resultMap.put(key, value);
                }
            }
            logger.info(String.format("resultMap=%s", resultMap));
            request.setAttribute(Constants.RESULT_MAP, resultMap);
            request.getRequestDispatcher("/page/showResult.jsp").forward(
                    request, response);
        } else {
            request.setAttribute(Constants.RESP_CODE, secssUtil.getErrCode());
            request.setAttribute(Constants.RESP_MSG, secssUtil.getErrMsg());
            request.getRequestDispatcher("/page/showError.jsp").forward(
                    request, response);
        }

    }

    /**
     * 加载安全秘钥 .
     * 
     * @param ownerId
     *            所有者id
     * @return SecssUtil .
     */
    protected SecssUtil getSecssUtil(String ownerId) {
        String path = String.format("%s/%s.properties", certBasePath, ownerId);
        SecssUtil secssUtil = new SecssUtil();
        secssUtil.init(path);
        return secssUtil;
    }

}
