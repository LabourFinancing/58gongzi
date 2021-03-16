// 修改色系
function indexStyle(obj) {
    var indeSt = {
        bodyColor: obj.bodyColor || "#EDF3F9",
        loginColor: obj.loginColor + "!important" || "#1490FF!important",
        logoSrc: obj.logoSrc || "./imgs/login-banner.png",
        formTitleColor: obj.formTitleColor + "!important" || "#3E4A59!important",
        loginBtnBg: obj.loginBtnBg + "!important" || "linear-gradient(to right, #1490FF 0%,#007AE8 100%)!important",
        loginBtnCl: obj.loginBtnCl || "#fff",
        copyRightCl: obj.copyRightCl || "#242424",
        iconUserImg: obj.iconUserImg || "./imgs/user-name.png",
        iconPWImg: obj.iconPWImg || "./imgs/pwd.png",
    }
    $('body').css("background", indeSt.bodyColor);
    $('login').css("background", indeSt.loginColor);
    $('.login .content .form-title ').css("color", indeSt.formTitleColor)
    document.getElementById("logo_login").src = indeSt.logoSrc;
    document.getElementById("l-img1").src = indeSt.iconUserImg;
    document.getElementById("l-img2").src = indeSt.iconPWImg;
    $('.login .content .form-actions .btn').css({ "background": indeSt.loginBtnBg, "color": indeSt.loginBtnCl })
    $('.login .copyright').css("color", indeSt.copyRightCl)

}
function mainconStyle(obj) {
    var mainSt = {
        bodyColor: obj.bodyColor || "#EDF3F9",
        headColor: obj.headColor + "!important" || "#1490FF!important",
        headLinkCl: obj.headLinkCl || "#fff",
        userImg: obj.userImg || "./imgs/ghs.png",
        prePayButton: obj.prePayButton || "linear-gradient(to right, #1490FF 0%,#007AE8 100%)",
        prePayButtonCl: obj.prePayButtonCl || "#fff",
        prePayCancel: obj.prePayCancel || "#C5C5C5",
        prePayCancelCl: obj.prePayCancelCl || "#fff",

    }
    $('body').css("background", mainSt.bodyColor);
    $('.weui-head').css("background", mainSt.headColor);
    $('.weui-head__link').css("color", mainSt.headLinkCl)
    document.getElementById("user-img").src = mainSt.userImg;
    $('#Prepaybutton').css({ "background": mainSt.prePayButton, "color": mainSt.prePayButtonCl })
    $('#PrepayCancel').css({ "background": mainSt.prePayCancel, "color": mainSt.prePayCancelCl })

}
function detailStyle(obj) {
    var detaSt = {
        bodyColor: obj.bodyColor || "#EDF3F9",
        headColor: obj.headColor + "!important" || "#1490FF!important",
        headLinkCl: obj.headLinkCl || "#fff",
        totalNumsFt: obj.totalNumsFt || "43px",
        totalNumsCl: obj.totalNumsCl || "#fff",
        wCHDFt: obj.wCHDFt || "15px",
        wCHDCl: obj.wCHDCl || "#000",
        wCBDFt: obj.wCBDFt || "15px",
        wCBDCl: obj.wCBDCl || "#A5A5A5",

    }
    $('body').css("background", detaSt.bodyColor);
    $('.weui-head').css("background", detaSt.headColor);
    $('.weui-head__link').css("color", detaSt.headLinkCl)
    $('.totalmoney .totalnums').css({ "font-size": detaSt.totalNumsFt, "color": detaSt.totalNumsCl })
    $('.weui-cell__hd').css({ "font-size": detaSt.wCHDFt, "color": detaSt.wCHDCl })
    $('.weui-cell__bd').css({ "font-size": detaSt.wCBDFt, "color": detaSt.wCBDCl })
}
// function querylogStyle(obj) {
//     var qLogSt = {
//         bodyColor: obj.bodyColor || "#EDF3F9",
//         headColor: obj.headColor || "#fff",
//         headLinkCl: obj.headLinkCl || "#fff",
//         selectDateBtn: obj.selectDateBtn || "#1490FF",
//         selectDateBtnCl: obj.selectDateBtnCl || "#fff",
//         dataLImg: obj.dataLImg || "./imgs/hostory-item.png",
//         //wCHDCl : obj.wCHDCl || "#000",

//     }
//     $('body').css("background", qLogSt.bodyColor);
//     $('.weui-head').css("background", qLogSt.headColor);
//     $('.weui-head__link').css("color", qLogSt.headLinkCl)
//     $('.selectdate .selectdate_btn').css({ "background": qLogSt.selectDateBtn, "color": qLogSt.selectDateBtnCl })
//     $('.data-list-img>img').attr("src", qLogSt.dataLImg);


// }