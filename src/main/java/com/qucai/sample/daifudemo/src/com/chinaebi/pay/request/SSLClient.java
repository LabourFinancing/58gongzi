package com.qucai.sample.daifudemo.src.com.chinaebi.pay.request;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLClient extends DefaultHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);
    public SSLClient() throws Exception{
//        super();
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
//        SSLContext ctx = SSLContext.getInstance("TLS");
//        X509TrustManager tm = new X509TrustManager() {
//                @Override
//                public void checkClientTrusted(X509Certificate[] chain,
//                        String authType) throws CertificateException {
//                }
//                @Override
//                public void checkServerTrusted(X509Certificate[] chain,
//                        String authType) throws CertificateException {
//                }
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//        };
//        ctx.init(null, new TrustManager[]{tm}, null);
//        SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        ClientConnectionManager ccm = this.getConnectionManager();
//        SchemeRegistry sr = ccm.getSchemeRegistry();
//        sr.register(new Scheme("https", 443, ssf));
    }
}
