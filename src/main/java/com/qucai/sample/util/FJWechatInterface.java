//package com.qucai.sample.util;
//
//import java.net.URL;
//
//import com.qucai.sample.converter.HttpJsonPersonalTest;
//import com.qucai.sample.entity.Manager;
//import com.qucai.sample.service.ManagerService;
//
//import org.apache.shiro.SecurityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.*;
//
//import net.sf.json.JSONObject;
//
//import java.util.Properties;
//import java.io.IOException;
//
//@RequestMapping(value="index")
//@ResponseBody
//    public Map<String, Object> openMemberRights(
//            HttpServletResponse response,
//            @RequestParam(value="phone", required=true) String phone,
//            @RequestParam(value="productId", required=true) String productId
//    ){
//        String urlString = "https://XXXXXXXXXXXXXXXX";
//        Map<String, Object> resultMap = new HashMap();
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        try {
//            //获取位置服务的地址
//            URL url = new URL(urlString);
//            //打开连接
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("POST");
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setConnectTimeout(CONNECT_TIME_OUT);
//            connection.setReadTimeout(CONNECT_TIME_OUT);
//            //设置请求方式
//            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//            connection.connect();
//            //电话号码加密
//            String mobile = AesUtil.encrypt(phone,APPSCRET.substring(0,16));
//            //唯一随机数
//            String once = new SimpleDateFormat("ssSSSS").format(new Date()) + String.valueOf(Math.random()).substring(2, 8);
//            //参数签名
//            HashMap<String,String> hashMap = new HashMap<>();
//            hashMap.put("app_key",APP_KEY);
//            hashMap.put("version", "1.0");
//            hashMap.put("sdk_from", "java");
//            hashMap.put("channel", CHANNEL);
//            hashMap.put("once", once);
//            hashMap.put("productId",productId);
//            hashMap.put("mobile", mobile);
//            String signatur = SignUtil.getNornmalSignature(hashMap,APPSCRET);
//            // 表单参数与get形式一样,拼接form参数
//            StringBuffer params = new StringBuffer();
//            params.append("app_key").append("=").append(APP_KEY).append("&")
//                    .append("version").append("=").append("1.0").append("&")
//                    .append("sdk_from").append("=").append("java").append("&")
//                    .append("channel").append("=").append(CHANNEL).append("&")
//                    .append("once").append("=").append(once).append("&")
//                    .append("productId").append("=").append(productId).append("&")
//                    .append("mobile").append("=").append(mobile).append("&")
//                    .append("signature").append("=").append(signatur);          
//            byte[] bypes = params.toString().getBytes();
//            // 输入参数
//            outputStream = connection.getOutputStream();
//            outputStream.write(bypes);
//            //从输入流中读取数据
//            inputStream = connection.getInputStream();
//            String result = new String(StreamTool.readInputStream(inputStream), "UTF-8");
//            //关闭连接
//            connection.disconnect();
//            System.out.println("返回报文是：");
//            System.out.println(JsonUtil.getBeanMap(result));
//            return JsonUtil.getBeanMap(result);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            resultMap.put("error_msg","服务器错误!");
//            return resultMap;
//        }
//        finally {
//            try {
//                inputStream.close();
//                outputStream.close();
//            }
//            catch (Exception e) {
//            }
//        }
//    }