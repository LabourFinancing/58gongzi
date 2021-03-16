<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%
	//设置请求字符集 避免产生乱码	
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>支付结果处理</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link rel="stylesheet" type="text/css" href="../css/cts.css" />
<link rel="stylesheet" type="text/css" href="../css/newdef.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/css.css" />
</head>
<body>
	<form name="inputForm" method="post" action="../payResult" >

		<table class="BlueTable" cellspacing="0" cellpadding="3" width="90%"
			align="center">
			<tr>
				<td style="background-color:#2b8ad0" height="30" colspan="2" align="center">
					<strong>
						<font color="#ffffff">支付结果处理（后台模式通知）</font>
					</strong>
				</td>
			</tr>
			<tr>
				<td width="45%" align="left" bgcolor="#d9dfee">
					<div align="right">字符集：</div>
				</td>
				<td width="55%" align="left">
					<select name="charset">
						<option value="00">GBK</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">商户私有域(backParam)：</div>
				</td>
				<td align="left">
					<input name="backParam" type="text" id="backParam" value="" />
				</td>
			</tr>
			<tr>
				<td width="45%" align="left" bgcolor="#d9dfee">
					<div align="right">版本号：</div>
				</td>
				<td width="55%" align="left">
					<input type="text" name="version" value="1.0"/>
				</td>
			</tr>
			<tr>
				<td width="45%" align="left" bgcolor="#d9dfee">
					<div align="right">签名类型：</div>
				</td>
				<td width="55%" align="left">
					<input type="text" name="signType" value="RSA"/> 
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">服务证书(serverCert)：</div>
				</td>
				<td align="left">
					<textarea rows="3" cols="50" name="serverCert"></textarea>
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">签名(serverSign)：</div>
				</td>
				<td align="left">
					<textarea rows="3" cols="50" name="serverSign"></textarea>
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">订单号(orderId)：</div>
				</td>
				<td align="left">
					<span id="lblbalanceOk">
						<input name="orderId" type="text" id="orderId" value="" />
					</span>
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">支付流水号(tradeNo)：</div>
				</td>
				<td align="left">
					<input name="tradeNo" type="text" id="tradeNo" value="" />
				</td>
			</tr>
			<tr>
				<td width="45%" align="left" bgcolor="#d9dfee">
					<div align="right">商户号：</div>
				</td>
				<td width="55%" align="left">
					<input name="merchantId" type="text" id="merchantId" value=""/>
				</td>
			</tr>
			
			
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">交易金额(transAmt)：</div>
				</td>
				<td align="left">
					<input name="transAmt" type="text" id="transAmt" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">交易状态：</div>
				</td>
				<td align="left">
					<input name="transState" type="text" id="transState" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">支付完成时间(payTime)：</div>
				</td>
				<td align="left">
					<input name="payTime" type="text" id="payTime" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">清算日期(settleDate)：</div>
				</td>
				<td align="left">
					<input name="settleDate" type="text" id="settleDate" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">账户名称(userName)：</div>
				</td>
				<td align="left">
					<input name="userName" type="text" id="userName" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">银行卡号(cardNo)：</div>
				</td>
				<td align="left">
					<input name="cardNo" type="text" id="cardNo" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">卡类型(cardType)：</div>
				</td>
				<td align="left">
					<input name="cardType" type="text" id="cardType" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">手续费扣除方(feePayer)：</div>
				</td>
				<td align="left">
					<input name="feePayer" type="text" id="feePayer" value="" />
				</td>
			</tr>
			<tr>
				<td align="left" bgcolor="#d9dfee">
					<div align="right">卡简称(bankCode:BOC)：</div>
				</td>
				<td align="left">
					<input name="bankCode" type="text" id="bankCode" value="" />
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<br/>
					<input type="submit" name="dosubmit" value=" 提交 "/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>