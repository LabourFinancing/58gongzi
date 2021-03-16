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
<title>网络支付:DowDirectPay</title>
<link rel="stylesheet" type="text/css" href="../css/cts.css" />
<link rel="stylesheet" type="text/css" href="../css/newdef.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/css.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<form name="form1" method="post" action="../pay">
		<table class="BlueTable" cellspacing="0" cellpadding="3" width="90%"
			align="center">
			<tr>
				<td style="background-color:#2b8ad0" height="30" colspan="2"
					align="center"><strong> <font color="#ffffff">统一下单接口</font>
				</strong>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">字符集：</td>
				<td><select name="charset">
						<option value="00">GBK</option>
				</select></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">接口版本：</td>
				<td><input type="text" name="version" value="1.0" /> <font
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
				<td><input type="text" name="service" value="AgencyPay">
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">交易类型：</td>
				<td>
					<select name="transType">
						<option value="AGENCY_PAY">代付</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">商户号：</td>
				<td><input type="text" name="merchantId" value="872566359655004"> <font
					color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">商户订单号：</td>
				<td><input type="text" name="orderId" value="<%=orderId%>">
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">订单日期：</td>
				<td><input type="text" name="orderTime" value="<%=orderTime%>">
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">订单金额：</td>
				<td><input type="text" name="transAmt" maxlength='20' value="1">
					<font color="red">*订单金额，以分为单位</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">币种：</td>
				<td><input type="text" name="currency" value="CNY"> <font
					color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">有效期单位：</td>
				<td><select name="validUnit">
				        <option value="" selected>无</option>
						<option value="00">有效期按分钟计</option>
						<option value="01">有效期按小时计</option>
						<option value="02">有效期按日计</option>
						<option value="03">有效期按月计</option>
				</select>
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">有效期数量：</td>
				<td><input type="text" name="validNum" value=""> <font
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">商户私有域：</td>
				<td><input type="text" name="backParam" value="保留数据1">
				</td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">后台回调：</td>
				<td><input type="text" name="offlineNotifyUrl"
<%--					value="http://192.168.28.113:8081/demo/jsp/pay_ret.jsp">--%>
						   value="http://localhost:8080/daifuwar/jsp/pay_ret.jsp">
					<font color="red">*</font></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">客户端IP：</td>
				<td><input type="text" name="clientIP" value="127.0.0.1">
					<font color="red">*</font></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">请求号：</td>
				<td><input type="text" name="requestId" value="<%=requestId%>">
					<font color="red">*</font></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">扩展信息：</td>
				<td><input type="text" name="extendInfo" value=""></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">账户名称：</td>
				<td><input type="text" name="userName" value="全渠道"></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">账户标识：</td>
				<td><select name="cardType">
						<option value="0">对私</option>
						<option value="1">对公</option>
				</select></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">银行卡号：</td>
				<td><input type="text" name="cardNo" value="6216261000000000018"></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">身份证号：</td>
				<td><input type="text" name="userNo" value="341126197709218366"></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">证件类型：</td>
				<td><input type="text" name="userType" value="01"></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">手续费扣除方：</td>
				<td><input type="text" name="feePayer" value="00"></td>
			</tr>
			<tr>
				<td width="45%" align="right" bgcolor="#d9dfee">银行简称（账户标识对公,必传）：</td>
				<td><input type="text" name="bankCode" value=""></td>
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
