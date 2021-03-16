<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	//取得商户当前系统时间
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	//商户订单号
	String orderId = sdf.format(new Date());
	String requestId = String.valueOf(System.currentTimeMillis());
	//商户订单日期
	String orderTime = orderId.substring(0, 8);
%>

<html>
<head>
<title>余额查询</title>
<link rel="stylesheet" type="text/css" href="../css/cts.css" />
<link rel="stylesheet" type="text/css" href="../css/newdef.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/css.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<form name="form1" method="post" action="../amtQuery">
		<table class="BlueTable" cellspacing="0" cellpadding="3" width="90%"
			align="center">
			<tr>
				<td style="background-color:#2b8ad0" height="30" colspan="2"
					align="center"><strong> <font color="#ffffff">帐户余额查询</font>
				</strong></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">字符集：</td>
				<td width="55%" align="left"><select name="charset">
						<option value="00">GBK</option>
				</select></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">接口版本：</td>
				<td><input type="text" name="version" value="1.0"> <font
					color="red">*</font></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">签名类型：</td>
				<td><input type="text" name="signType" value="RSA"> <font
					color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">接口类型：</td>
				<td><input type="text" name="service"
					value="AccQuery"> <font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">商户号：</td>
				<td><input type="text" name="merchantId" value=""> <font
					color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">请求号：</td>
				<td><input type="text" name="requestId" value="<%=requestId%>">
					<font color="red">*</font></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br/>
					<input type="Submit" value="提交" id="Submit" name="submit" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>