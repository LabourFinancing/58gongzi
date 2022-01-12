function appendTable(sb) {
    const table = document.querySelector('#StaticList');
    const tbody = table.querySelector('tbody');
    sb.map(item=>{
        let str = `
    <td name="ClearPaid" id="ClearPaid" value="${item.t_Txn_Static_Clear_his}" >${item.t_Txn_Static_Clear_his}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;mso-number-format:'\\@';"><div id="printID" value="${item.t_Txn_Static_PerName}">${item.t_Txn_Static_PerName}</div> </td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">${item.t_Txn_Static_PerID}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">${item.t_Txn_Static_PerCreditCard}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_TotTxnAmt}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_TotTxnActAmt}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">${item.t_Txn_Static_TotInterest}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_TotServiceFee}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_TotPoundageFee}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_TotTierFee}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_TotChargeFee}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;mso-number-format:'\\@';">${item.t_Txn_Static_TotTxnCount}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">&yen${item.t_Txn_Static_CurrentCredit}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;mso-number-format:'\\@';">&yen${item.t_Txn_Static_CurrentBalance}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;mso-number-format:'\\@';">${item.t_Txn_Static_Company}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">${item.t_Txn_Static_VendorCompany}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">${item.t_Txn_Static_BeginDate}</td>
    <td class="col-xs-push-1" style="word-wrap:break-word;word-break:break-all;">${item.t_Txn_Static_EndDate}</td>
        `
        const tr = document.createElement('tr');
        tr.innerHTML=str;
        tbody.appendChild(tr);
    });
}