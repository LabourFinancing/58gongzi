package com.qucai.sample.daifudemo.src.com.chinaebi.pay.request;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpsUtil {

	public static String doPostWithJson(String url, Map<String, Object> map) throws Exception {
		String jsonStrParams = JSONObject.fromObject(map).toString();
		System.out.println("requestStr	----> " + jsonStrParams);
		return doPost(url, jsonStrParams);
	}

	public static String doPost(String url, String jsonstr) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		HttpResponse response = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(jsonstr, Common.CHAR_SET_GBK);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpPost.setEntity(se);
			response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, Common.CHAR_SET_GBK);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return result;
	}
}
