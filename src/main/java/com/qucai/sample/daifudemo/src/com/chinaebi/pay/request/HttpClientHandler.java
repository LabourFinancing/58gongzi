package com.qucai.sample.daifudemo.src.com.chinaebi.pay.request;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);
    private static String charset = "UTF-8";

    /**
     * http post json 请求
     */
    public static String onRequest(String strEntity,String postUrl,String ContentType,String charset) throws IOException {

        String respResult = null;
        //https 信任证书
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            logger.error("ssl error",e);
        }
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        RequestConfig.Builder reqConfigBuilder = RequestConfig.custom();
        reqConfigBuilder.setSocketTimeout(12000);
        reqConfigBuilder.setConnectTimeout(12000);
        reqConfigBuilder.setConnectionRequestTimeout(12000);
        RequestConfig reqConfig = reqConfigBuilder.build();
        httpClientBuilder.setDefaultRequestConfig(reqConfig);
        httpClientBuilder.setSSLSocketFactory(sslsf);
        HttpClient client = httpClientBuilder.build();
        HttpPost httPost = new HttpPost(postUrl);
        StringEntity entity = new StringEntity(strEntity, charset);
        entity.setContentEncoding(charset);
        entity.setContentType(ContentType);
        httPost.setEntity(entity);
        HttpResponse resp = client.execute(httPost);
        if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httEntity = resp.getEntity();
            respResult = EntityUtils.toString(httEntity);
        }
        HttpClientUtils.closeQuietly(client);

        return respResult;
    }
}
