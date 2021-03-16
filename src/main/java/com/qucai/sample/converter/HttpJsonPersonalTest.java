package com.qucai.sample.converter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
@SuppressWarnings("unused")
public class HttpJsonPersonalTest {
  public static String main() throws Exception {
	URL url = new URL("https://www.cspower.net/bigcenter/index.php?s=/addon/Bigc/SyAPI/SyncToken/secretcode/2039ER-WK3SW2-DO2XML-PI92EB");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setConnectTimeout(7200);
    conn.setRequestMethod("GET");
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setRequestProperty("Accept-Charset", "UTF-8");

    // 输出返回结果
    InputStream Token = conn.getInputStream();
    System.out.println("Token:");
    System.out.println(Token);
    
    int resLen =0;
    byte[] res = new byte[1024];
    StringBuilder sb=new StringBuilder();
    while((resLen=Token.read(res))!=-1){
      sb.append(new String(res, 0, resLen));
    }
    String jsonStr = sb.toString();
    
//    StringTokenizer YKYToken = new StringTokenizer(jsonStr,",",false);
    
//    System.out.print(YKYToken);
//    
//     while (YKYToken.hasMoreElements()){
//    	 System.out.println("TOKEN:"+YKYToken.nextToken());
//     }
//    
    String RCconnect = jsonStr.substring(9, 10);
    System.out.print("RC:");
    System.out.print(RCconnect);

    if (RCconnect.equals("0")) {
      System.out.print("GToken:");
      String GetToken = jsonStr.substring(20,52);
      System.out.print(GetToken);
 	  Token.close();
 	  conn.disconnect();
      return GetToken;
     }else {
    	 System.out.print("Fujian API connect get Mobile Error");
    	 Token.close();
    	 conn.disconnect();
    	 return "1";
     }
  }
public static String UserMobil(String userMobilurl, String gid, String TokenKey) throws Exception {
	      BufferedReader bufferedReader = null;
	      String data = null;
//	      try {
			URL url = new URL(userMobilurl);
			System.out.print(url);
		    HttpURLConnection conn1 = (HttpURLConnection) url.openConnection();
		    conn1.setConnectTimeout(7200);
		    conn1.setRequestMethod("GET");
		    conn1.setDoOutput(true);
		    conn1.setDoInput(true);
		    conn1.setUseCaches(false);
		    
		    InputStream result = conn1.getInputStream();
		    System.out.println("result:");
		    System.out.println(result);
		    
		    int resLen =0;
		    byte[] res = new byte[1024];
		    StringBuilder sb=new StringBuilder();
		    while((resLen=result.read(res))!=-1){
		      sb.append(new String(res, 0, resLen));
		    }
		    String jsonStr = sb.toString();
		    
		    String RCconnect = jsonStr.substring(9, 10);
		    System.out.print("RC:");
		    System.out.print(RCconnect);

		    if (RCconnect.equals("0")) {
		        System.out.print("GToken:");
			    String[] split = jsonStr.split(",");
		        for (int i = 0; i < split.length; i++) {
		            System.out.println(split[i]);
		        }
		      String MobileAPI = split[6].substring(7, 18);
		      System.out.print(MobileAPI);
		      result.close();
		 	  conn1.disconnect();
		      return MobileAPI;
		     }else {
		    	 System.out.print("Fujian API connect get Mobile Error");
		    	 result.close();
		    	 conn1.disconnect();
		    	 return "1";
		     }
  }
}