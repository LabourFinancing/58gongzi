<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	System.out.println("==================== 支付结果通知 ====================");
	System.out.println("pay_ret ---> " + request.getRemoteAddr());
	Map<String, Object> map = (Map<String, Object>) request.getParameterMap();
	for(String key : map.keySet()){
		Object value = map.get(key);
		String valueStr = null;
		if(value instanceof String){
			valueStr = "[" + value + "]";
		} else if(value instanceof String[]){
			valueStr = ((String[]) value)[0];
		} else {
			valueStr="";
		}
		System.out.println("param ---> [" + key + "] = " + valueStr);
	}
	
	String responseStr = "{'result' : 'success'}";
	response.getWriter().print(responseStr);
%>