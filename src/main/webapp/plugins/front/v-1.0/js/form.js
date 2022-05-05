// JavaScript Document

$(function(){
    $("#logout").click(function(){
        logout();
    });
    $("#toExtEmail").click(function(){
        var mail = $("#email").val();
        var begin = mail.indexOf("@");
        var end = mail.lastIndexOf(".");
        mail = mail.substring(begin+1,end);
        var url = "http://mail."+mail+".com";
        post(url, null, "_blank");
    });
});
function popupBox(title,content,callback,cancel){
    $("#popup_smt").click(function(){
        eval(callback);
    });
    $("#popup_box").find("h1").html("&nbsp;&nbsp;"+title);
    $("#popup_box .float_ctn .float_field").html(content);
    if(cancel==2){
        $("#popup_smt").hide();
        $("#cancel").hide();
        setTimeout(function(){
            $("#popup_box").fadeOut();
            $(".background_box").fadeOut();
            $("body").removeClass("float_body");
        },2000);
    }
    $("#popup_box").show();
    $(".background_box").show();
    $("body").addClass("float_body");
}
function v(obj,pattern){
    if(pattern.test(obj)){
        return true;
    }
    return false;
}
var aCity={11:"åŒ—äº¬",12:"å¤©æ´¥",13:"æ²³åŒ—",14:"å±±è¥¿",15:"å†…è’™å¤",21:"è¾½å®",22:"å‰æž—",23:"é»‘é¾™æ±Ÿ",31:"ä¸Šæµ·",32:"æ±Ÿè‹",33:"æµ™æ±Ÿ",34:"å®‰å¾½",35:"ç¦å»º",36:"æ±Ÿè¥¿",37:"å±±ä¸œ",41:"æ²³å—",42:"æ¹–åŒ—",43:"æ¹–å—",44:"å¹¿ä¸œ",45:"å¹¿è¥¿",46:"æµ·å—",50:"é‡åº†",51:"å››å·",52:"è´µå·ž" ,53:"äº‘å—",54:"è¥¿è—",61:"é™•è¥¿",62:"ç”˜è‚ƒ",63:"é’æµ·",64:"å®å¤",65:"æ–°ç–†",71:"å°æ¹¾",81:"é¦™æ¸¯",82:"æ¾³é—¨",91:"å›½å¤–"};
function cidInfo(sId){
    var boo = true ;
    var iSum=0 ;
    if(!/^\d{17}(\d|x)$/i.test(sId))return false;
    sId=sId.replace(/x$/i,"a");
    if(aCity[parseInt(sId.substr(0,2))]==null){
        boo = false;
    }
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
    var d=new Date(sBirthday.replace(/-/g,"/")) ;
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
        boo = false;
    }
    for(var i = 17;i>=0;i --){
        iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
    }
    if(iSum%11!=1){
        boo = false;
    }
    return boo;
}
//èº«ä»½è¯éªŒè¯
function vIDCard(id){
    if(cidInfo(id)){
        return true;
    }
    return false ;
}
//æŠ¤ç…§éªŒè¯
function vPCard(opt){
    var pattern = /^[a-zA-Z0-9]+$/;
    var isright= v(opt,pattern);
    return isright;
}
//éžç©ºåˆ¤æ–­
function vEmpty(id){
    var c = $("#"+id).val();
    if(c==null){
        return false;
    }
    return true;
}

//éªŒè¯è¶…é“¾æŽ¥
function checkURL(URL){
    var str=URL;
    //ä¸‹é¢çš„ä»£ç ä¸­åº”ç”¨äº†è½¬ä¹‰å­—ç¬¦"\"è¾“å‡ºä¸€ä¸ªå­—ç¬¦"/"
    var Expression=/^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
    var objExp=new RegExp(Expression);
    if(objExp.test(str)){
        return true;
    }else{
        return false;
    }
}
//éªŒè¯ç‰¹æ®Šå­—ç¬¦ä¸²çš„æ–¹æ³•
function validationSymbol(str){   //strè¢«éªŒè¯çš„å­—ç¬¦ä¸²
    var a = new Array("@","#","ï¿¥","$","%","<", ">","/","!","~","`","^","&","*","(",")","=","+","{","}","?","ã€Š","ã€‹",",",".","ã€‚",":",";") ;  //aä¸ºéªŒè¯å­—ç¬¦ä¸²çš„è§„åˆ™
    for (i=0; i < a.length; i++) {
        if (str.indexOf(a[i]) >= 0) { //æ­¤å¤„è¿›è¡ŒéªŒè¯åˆ¤æ–­æ˜¯å¦å­˜åœ¨æŒ‡å®šçš„å­—ç¬¦
            return true;    //è¿”å›žå€¼
        }
    }
    return false;
}
//éªŒè¯ç‰¹æ®Šå­—ç¬¦ä¸²çš„æ–¹æ³•
function validationSymbolWl(str){   //strè¢«éªŒè¯çš„å­—ç¬¦ä¸²
    var a = new Array("#","ï¿¥","$","<", ">","!","~","`","^","*","(",")","+","{","}","ã€Š","ã€‹",",","ã€‚",":"," ") ;  //aä¸ºéªŒè¯å­—ç¬¦ä¸²çš„è§„åˆ™
    for (i=0; i < a.length; i++) {
        if (str.indexOf(a[i]) >= 0) { //æ­¤å¤„è¿›è¡ŒéªŒè¯åˆ¤æ–­æ˜¯å¦å­˜åœ¨æŒ‡å®šçš„å­—ç¬¦
            return true;    //è¿”å›žå€¼
        }
    }
    return false;
}
//éªŒè¯ç‰¹æ®Šå­—ç¬¦ä¸²çš„æ–¹æ³•
function validationBracket(str){   //strè¢«éªŒè¯çš„å­—ç¬¦ä¸²
    var a = new Array("ã€","ã€‘","[","]") ;  //aä¸ºéªŒè¯å­—ç¬¦ä¸²çš„è§„åˆ™
    for (i=0; i < a.length; i++) {
        if (str.indexOf(a[i]) >= 0) { //æ­¤å¤„è¿›è¡ŒéªŒè¯åˆ¤æ–­æ˜¯å¦å­˜åœ¨æŒ‡å®šçš„å­—ç¬¦
            return true;    //è¿”å›žå€¼
        }
    }
    return false;
}
//å…¬å…±æäº¤è¡¨å•
function handSubmit(id){
    $("#"+id).submit();
}
//å‘é€æ™®é€šçš„postè¯·æ±‚
function post(URL, PARAMS,target) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "get";
    if(target!=""){
        temp.target=target;
    }
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}
//åŠ è½½éªŒè¯ç 
function loadPic(id){
    var oldSrc = $("#"+id).attr("src");
    var str = "&nocache=" +new Date().getTime();
    var newSrc = oldSrc.indexOf("?") === -1 ? oldSrc + "?"+str : oldSrc + str;
//	$("#"+id).attr("src",src+"?nocache=" + new Date().getTime());
    $("#"+id).attr("src",newSrc);
}

//åº§æœºéªŒè¯
function validationTelephone(opt){
    var pattern = /^0\d{2,3}[-]?\d{8}|\(0\d{2,3}\)[-]?\d{8}$/;
    var isright= v(opt,pattern);
    return isright;
}
//éªŒè¯é‚®ç®±verifyMbOrMail
function verifyMail(opt){
    var pattern = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
    var isright= v(opt,pattern);
    return isright;
}
//éªŒè¯æ‰‹æœº
function verifyMobile(opt){
    var pattern = /^1[3|4|5|6|7|8|9]\d{9}$/;
    var isright= v(opt,pattern);
    return isright;
}
//æ•°å­—éªŒè¯
function verifyNumber(numStr){
    var pattern = /^\+?[1-9][0-9]*$/ ;
    var isright= v(numStr,pattern);
    return isright;
}
//æ•°å­—+â€œ-â€
function verifyNum_(numStr){
    var pattern = /^[0-9\\-]+$/ ;
    var isright= v(numStr,pattern);
    return isright;
}
//æ•°å­—+å­—æ¯éªŒè¯
function verifyStr(numStr){
    var pattern = /^[A-Za-z0-9]+$/ ;
    var isright= v(numStr,pattern);
    return isright;
}
//æ•°å­—é‡‘é¢
function verfiyMoney(money){
//	var pattern = /^(\d|[1-9]\d+)(\.\d+)?$/ ; //åŒ…å«å°æ•°
    var pattern = /^[1-9]\d*$/;
    var isright= v(money,pattern);
    return isright;
}

//æ•°å­—é‡‘é¢ï¼ˆæœ‰å°æ•°ç‚¹ï¼‰
function verfiyMoneySmall(money) {
    if (money==null || money=="" || Number(money)==0) {
        return false;
    }
    var pattern =/^[0-9]+([.]{1}[0-9]{0,4}){0,1}$/;
    var isright= v(money,pattern);
    return isright;
}

//æ•°å­—é‡‘é¢ï¼ˆæœ‰å°æ•°ç‚¹ï¼‰ï¼Œä½™é¢æé†’å¯ä¸ºè´Ÿæ•°
function verfiyMoneySmall_(money) {
    var pattern =/^-?[0-9]+([.]{1}[0-9]{0,4}){0,1}$/;
    var isright= v(money,pattern);
    return isright;
}

//éªŒè¯ç»„ç»‡æœºæž„å·
function verifyOrgId(orgNum){
    var pattern = /^[a-zA-Z0-9]{8}(-)?[a-zA-Z0-9]$/;
    var isright= v(orgNum,pattern);
    return isright;
}
//éªŒè¯ç¨ŽåŠ¡ç™»è®°è¯
function verifyItt(ittNum){
    var pattern = /^[a-zA-Z0-9]{15,20}$/;
    var isright= v(ittNum,pattern);
    return isright;
}
//éªŒè¯è¥ä¸šæ‰§ç…§å·
function bsNum(bsNum){
    var pattern = /^\d{15}$/;
    var isright= v(bsNum,pattern);
    return isright;
}
function verifyNumAndLiter(orgNum){
    var pattern = /^[a-zA-Z0-9]{4,15}$/;
    var isright= v(orgNum,pattern);
    return isright;
}
//æ³¨å†Œæ—¶ç”¨æˆ·æ˜µç§°éªŒè¯
function verifyNicheng(userName){
    var pattern = /^[a-zA-Z\d\u4e00-\u9fa5]+$/;
    var isright= v(userName,pattern);
    return isright;
}

//400å’Œåº§æœºéªŒè¯
function verify400So(phone){
    var pattern = /(^400\d{7}$)|(^0\d{2,3}\d{8}$)/;
    var isright= v(phone,pattern);
    return isright;
}
//é“¶è¡Œå¡æ ¡éªŒ
function verifyBankNum(phone){
    var pattern = /^[0-9]+$/;
    var isright= v(phone,pattern);
    return isright;
}
//çº³ç¨Žäººè¯†åˆ«å·
function verifyIdentificationnum(phone){
    var pattern = /(\d{15})|(\d{17})|(\d{18})|(\d{20})/;
    var isright= v(phone,pattern);
    return isright;
}
//é‚®ç¼–
function verifyPostNum(phone){
    var pattern = /^[0-9][0-9]{5,6}$/;
    var isright= v(phone,pattern);
    return isright;
}
//éªŒè¯åº§æœº
function verifyFixPhone(phone){
    var pattern = /(^0\d{2,3}\d{8}$)/;
    var isright= v(phone,pattern);
    return isright;
}
//éªŒè¯æ‰‹æœºå’Œåº§æœº
function verifyMobileAndFixPhone(phone){
    var pattern = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^0\d{2,3}\d{8}$)/;
    var isright= v(phone,pattern);
    return isright;
}

//éªŒè¯æ˜¯å¦å­˜åœ¨2ç»„{}
function verifyTwoBraces(str){
    var pattern = /^([^\{\}]*\{[^\{\}]*\}[^\{\}]*){0,2}$/;
    var isright= v(str,pattern);
    return isright;
}

//å¼ºè¡Œåˆ·æ–°é¡µé¢
function refresh(){
    if(window.name != "bencalie"){
        location.reload();
        window.name = "bencalie";
    }
    else{
        window.name = "";
    }
}
//åŽ»æŽ‰ç©ºæ ¼
function trim(value){
    return value.replace(/[ ]/g,"");
}
//æ˜¯å¦å­˜åœ¨ç©ºæ ¼
function spaceExists(value){
    var isright = false;
    if(value.indexOf(" ")>=0){
        isright =  true;
    }
    return isright;

}
//éªŒè¯å¯†ç æ ¼å¼
function vPwd(value){
    var pattern = /^([A-Za-z]+\d+|\d+[A-Za-z]+)[A-Za-z\d]*$/;
    var isRight = v(value,pattern);
    return isRight;
}
function verifyHtml(value){
    var pattern = /<(S*?)[^>]*>.*?|<.*?\/>/;
    var isRight = v(value,pattern);
    return isRight;
}
function bindLoginBox(id){
    $("#"+id).bind("click",function(){
        $(".log_box").show();
        $("#background_box").show();
    });
}

/**
 * è®¡ç®—å­—ä¸²é•¿åº¦ã€‚
 */
function getByteLen(val) {
    if(!val){
        return 0;
    }
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //å…¨è§’,ä¸­æ–‡
            len += 3;
        else
            len += 1;
    }
    return len;
};

/**
 * è®¡ç®—å­—ä¸²é•¿åº¦ã€‚
 */
function getByteLen2(val) {
    if(!val){
        return 0;
    }
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //å…¨è§’,ä¸­æ–‡
            len += 2;
        else
            len += 1;
    }
    return len;
};

function logout(){
    $.ajax({
        url:"/user/logout",
        type:"post",
        dataType: "text",
        success: function (data) {
            deletecookie("user_log_cookie",".ucpaas.com");
            location.href="/" ;
        },
        error: function (msg) {
        }
    });
}



var time1 = function(id,id1,cc,phone,activeDisplay){
    $.ajax({
        url:"/checkcode/getSessionCountdown",
        type:"post",
        data:"phone="+phone,
        dataType: "text",
        success: function (data) {
            if(data!="0"){
                $("#"+id).attr("cursor", "pointer");
                if(typeof(activeDisplay) != "undefined"){
                    $("#"+id).val( data + activeDisplay);
                }else{
                    $("#"+id).val( data + "ç§’");
                    $("#voicecodeinput").hide();
                    $("#smscodeinput").hide();
                    $("#timecheck").show();
                    $("#timecheck").val("é‡æ–°å‘é€"+data+"ç§’");
                }
                setTimeout(function () {
                        time1(id,id1,cc,phone,activeDisplay);
                    },
                    1000);
            }else{
                $("#"+id).removeAttr("disabled");
                $("#"+id1).removeAttr("disabled");
                $("#"+id).val(cc);
                $("#voicecodeinput").show();
                $("#smscodeinput").show();
                $("#timecheck").hide();

            }
        },
        error: function (msg) {
            $("#movecode").val("");
        }
    });
};


var countDownTimer;
var timeSolider = function(id,id1,cc,phone,activeDisplay){
    $.ajax({
        url:"/checkcode/getSessionCountdown",
        type:"post",
        data:"phone="+phone,
        dataType: "text",
        async:true,
        success: function (data) {
            if(data!="0"){
                $("#"+id).attr("cursor", "pointer");
                if(typeof(activeDisplay) != "undefined"){
                    $("#"+id).val( data + activeDisplay);
                }else{
                    $("#"+id).val( data + "ç§’");
                    $("#voicecodeinput").hide();
                    $("#smscodeinput").hide();
                    $("#timecheck").show();
                    $("#timecheck").val("é‡æ–°å‘é€"+data+"ç§’");
                }
                countDownTimer = setTimeout(function () {
                        timeSolider(id,id1,cc,phone,activeDisplay);
                    },
                    1000);
            }else{
                $("#"+id).removeAttr("disabled");
//        	   $("#"+id1).removeAttr("disabled");
                $("#"+id).val(cc);
                $("#voicecodeinput").show();
                $("#smscodeinput").show();
                $("#timecheck").hide();
            }
        },
        error: function (msg) {
            $("#movecode").val("");
        }
    });
};


var time_out;
var voiceCodeAjax = function(phone,sid){
    $.ajax({
        url:"/checkcode/voiceCheckCode",
        type:"post",
        async:false,
        data:"phone="+phone+"&sid="+sid,
        dataType: "text",
        success: function (data) {
            if(data == 'imgCode-error'){
                $(".imgCode-error").text("éªŒè¯ç é”™è¯¯").show();
            }else if(data == 'ip-error'){
                $(".imgCode-error").text("åŒä¸€ipæ¯å¤©æœ€å¤šå‘é€10æ¡çŸ­ä¿¡").show();
            }else{
                $(".imgCode-error").text("").hide();
            }
            $("#movecode").val(data);

            time_out = setTimeout(function(){
                $("#movecode").val("");
            },180000);

        },
        error: function (msg) {
            $("#movecode").val("");
        }
    });
};
var smsCodeAjax = function(phone,type,sid,code){
    var checkCodeId = $("#checkCodeId").val();
    $.ajax({
        url:"/checkcode/smsCheckCode",
        type:"post",
        async:false,
        data:"phone="+phone+"&smsTempType="+type+"&sid="+sid+"&code="+code+"&checkCodeId=" + checkCodeId,
        dataType: "text",
        success: function (data) {
            if(data == 'imgCode-error'){
                $(".imgCode-error").text("éªŒè¯ç é”™è¯¯").show();
            }else if(data == 'ip-error'){
                $(".imgCode-error").text("åŒä¸€ipæ¯å¤©æœ€å¤šå‘é€10æ¡çŸ­ä¿¡").show();
            }else{
                $(".imgCode-error").text("").hide();
            }
            if(data!=''){
                $("#movecode").val(data);

                time_out = setTimeout(function(){
                    $("#movecode").val("");
                },180000);


            }
        },
        error: function (msg) {
            $("#movecode").val("");
        }
    });
};


var smsCodeAjaxSolider = function(phone,type,token,id1,id2){
    $.ajax({
        url:"/checkcode/smsCheckCodeSolider",
        type:"post",
        async:false,
        data:"phone="+phone+"&smsTempType="+type+"&token="+token,
        dataType: "text",
        success: function (data) {
            if(data == 'token-error'){
                $("#solider_error").text("éªŒè¯å¤±è´¥").show();
            }else if(data == 'ip-error'){
                $("#solider_error").text("åŒä¸€ipæ¯å¤©æœ€å¤šå‘é€10æ¡çŸ­ä¿¡").show();
            }else{
                $("#imgCode-error").text("").hide();
            }
            if(data!=''){
                $("#movecode").val(data);

                time_out = setTimeout(function(){
                    $("#movecode").val("");
                    $("#solider_error").hide();
                    $("#smscodeinput").val("èŽ·å–éªŒè¯ç ");
                },180000);


            }
            timeSolider(id1,id2,"èŽ·å–éªŒè¯ç ",phone);
        },
        error: function (msg) {
            $("#movecode").val("");
        }
    });
};

//æ£€æŸ¥éªŒè¯ç æ˜¯å¦æ­£ç¡®
var ajaxCheckCode = function(codeObj,errorObj){
    var url = "/page/user/checkcode.jsp";
    var data = "checkCode=" + $(codeObj).val() ;
    var result = true;
    $.ajax({
        type: "post",
        url: url,
        data: data,
        async:false,//è¿™ä¸ªä¸€å®šè¦æœ‰
        dataType: "json",
        success: function(data){
            if(data!=2){
                $(obj).hide();
                result=true;
            }else{
                $(obj).show().text("éªŒè¯ç é”™è¯¯");
                result=false;
            }
        }
    });

    return result;
};

var isExistsPhone = function(type,id1,id2,type1,sid){
    var value = $("#phone").val();
    var code = $(".imgCode").val();
    $.ajax({
        url:"/user/ckMobileAndmailEnable",
        type:"post",
        data:"vmobile="+value,
        dataType: "text",
        async: true,
        success: function (data) {
            if(data=="1"){
                $("#phone_error").fadeIn();
                $("#phone_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
                $("#phone_error").html("è¯¥æ‰‹æœºå·å·²è¢«æ³¨å†Œ");
                $("#vp").val("2");
                disabl(id1, id2);
            }else{
                $("#phone_error").hide();
                if(!ipCheckCountAjax()){
                    $("#phone_error").fadeIn();
                    $("#phone_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
                    $("#phone_error").html("å·²è¶…è¿‡æ¯å¤©çŸ­ä¿¡å‘é€ä¸Šé™ï¼Œè¯·æ˜Žå¤©å†è¯•ï¼Œæˆ–è€…è”ç³»å®¢æœ4007776698");
                    disabl(id1, id2);
                    return;
                }
                if(type==1){
                    voiceCodeAjax(value,sid);
                    time1(id2,id1,"è¯­éŸ³éªŒè¯ç ",value);
                }else if(type==2){
                    smsCodeAjax(value,type1,sid,code);
                    time1(id1,id2,"èŽ·å–éªŒè¯ç ",value);
                }
            }
        },
        error: function (msg) {
            $("#phone_error").hide();
            $("#vp").val("2");
        }
    });
};


var isExistsPhoneSolider = function(type,id1,id2,type1,token){
    var value = $("#phone").val();
    if(type == 2){
        smsCodeAjaxSolider(value,type1,token,id1,id2);
    }
};


//æ»‘å—éªŒè¯ç éªŒè¯æ‰‹æœºå·æ˜¯å¦æ³¨å†Œ
var isExistsPhoneSlider = function(){
    var result = false;
    var value = $("#phone").val();
    $.ajax({
        url:"/user/ckMobileAndmailEnable",
        type:"post",
        data:"vmobile="+value,
        dataType: "text",
        async:false,
        success: function (data) {
            if(data=="1"){
                $("#solider_error").fadeIn();
                $("#solider_error").html("è¯¥æ‰‹æœºå·å·²è¢«æ³¨å†Œ");
                $("#vp").val("2");
                result = false;
            }else{
                result = true;
            }
        }
    });
    return result;
};

//å‘çŸ­ä¿¡å‰å…ˆæ ¡éªŒIPæ˜¯å¦è¿˜èƒ½å†å‘çŸ­ä¿¡
var checkSoliderIp = function(){
    var result = false;
    if(!ipCheckCountAjax()){
        $("#solider_error").fadeIn();
        $("#solider_error").html("å·²è¶…è¿‡æ¯å¤©çŸ­ä¿¡å‘é€ä¸Šé™ï¼Œè¯·æ˜Žå¤©å†è¯•ï¼Œæˆ–è€…è”ç³»å®¢æœ4007776698");
        result = false;
    }else{
        result = true;
    }
    return result;
}


var ipCheckCountAjax = function(){
    var result = true;
    $.ajax({
        url:"/checkcode/verifyRegByIp",
        type:"post",
        async:false,
//		data:,
        dataType: "text",
        success: function (data) {
            if(data=='success'){
                result=true;
            }else{
                result = false;
            }
        },
        error: function (msg) {
            result = false;
        }
    });
    return result;
}
//è¯­éŸ³éªŒè¯ç 
var voiceCode = function(id1,id2,sid){
    if(frm.phone(id1,id2)){
        isExistsPhone(1,id2,id1,"",sid);
    }
};
//æ³¨å†ŒçŸ­ä¿¡éªŒè¯ç 
var smsCode = function(id1,id2,type,sid){
    clearTimeout(time_out);
    if(frm.imgCode() && frm.phone(id1,id2)){
        isExistsPhone(2,id1,id2,type,sid);
    }
};

//æ»‘å—éªŒè¯ç çŸ­ä¿¡
var smsCodeSolider = function(id1,id2,type,token){
    clearTimeout(time_out);
    clearTimeout(countDownTimer);
    if(frm.phone(id1,id2)){
        isExistsPhoneSolider(2,id1,id2,type,token);
    }
};

var smsCode_forgetPwd = function(id1,id2,type,sid){
    if(frm.phone(id1,id2)){
        var value = $("#phone").val();
        var code = $("#imgCode").val();
        $.ajax({
            url:"/user/ckMobileAndmailEnable",
            type:"post",
            data:"vmobile="+value,
            dataType: "text",
            success: function (data) {
                if(data!="1"){
                    $(".error").hide();
                    $("#phone_error").fadeIn();
                    $("#phone_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
                    $("#phone_error").html("æ‰‹æœºå·ç è¿˜æ²¡è¢«ç»‘å®š");
                    disabl(id1, id2);
                }else{
                    if(!ipCheckCountAjax()){
                        $(".error").hide();
                        $(".title").hide();
                        $("#phone_error").fadeIn();
                        $("#phone_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
                        $("#phone_error").html("å·²è¶…è¿‡æ¯å¤©ä¸Šé™ï¼Œå¦‚æœªæ”¶åˆ°éªŒè¯ç è¯·è”ç³»4000970020");
                        disabl(id1, id2);
                        return;
                    }
                    $(".title").show();
                    $("#phone_error").hide();
                    smsCodeAjax(value,type,'',code);
                    time1(id1,id2,"ç‚¹æ­¤é‡å‘çŸ­ä¿¡",value,"ç§’åŽç‚¹æ­¤é‡å‘çŸ­ä¿¡");
                }
            },
            error: function (msg) {
                $("#phone_error").hide();
                $("#vp").val("2");
            }
        });
    }
};


var frm = {
    imgCode:function(){
        var imgCode = $(".imgCode").val();
        if(imgCode == ''){
            $(".imgCode-error").text("è¯·è¾“å…¥éªŒè¯ç ").show();
            return false;
        }else{
            $(".imgCode-error").text("").hide();
        }
        return true;
    },
    phone:function(id1,id2){
        var phone = $("#phone").val();
        if(phone == 'è¯·è¾“å…¥æ‰‹æœºå·'){
            $("#phone_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px').hide();
            $("#phone_error").text("æ‰‹æœºå·ä¸èƒ½ä¸ºç©º");
            $("#phone_error").fadeIn();
            disabl(id1, id2);
            $("#phone_error_flag").val("0");
            return false;
        }

        if(!verifyMobileAndFixPhone(phone)){
            $("#phone_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px').hide();
            $("#phone_error").text("æ‰‹æœºå·æ ¼å¼é”™è¯¯");
            $("#phone_error").fadeIn();
            disabl(id1, id2);
            $("#phone_error_flag").val("0");
            return false;
        }else{
            $("#phone_error").hide().siblings('input').removeClass('border-red').siblings('.close').css('background-position', '2px -265px');
            $("#phone_error").hide();
            $("#phone_error_flag").val("1");
        }
        noDisabl(id1,id2);
        return true;
    },
    phoneCode:function(){
        var phone = $("#phone").val();
        var movecode = $("#movecode").val();
        var inputmovecode = $("#inputmovecode").val();
        if(inputmovecode == 'çŸ­ä¿¡éªŒè¯ç '){
            $("#move_phone_code_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
            $("#move_phone_code_error").text("è¯·å¡«å†™çŸ­ä¿¡éªŒè¯ç ");
            $("#move_phone_code_error").fadeIn();
            $("#move_phone_code_error_flag").val("0");
            return false ;
        }

        if(inputmovecode==''){
            $("#move_phone_code_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
            $("#move_phone_code_error").text("è¯·å¡«å†™éªŒè¯ç ");
            $("#move_phone_code_error").fadeIn();
            $("#move_phone_code_error_flag").val("0");
            return false ;
        }
        value = hex_md5(phone+inputmovecode);
        if(movecode!=value){
            $("#move_phone_code_error").fadeIn().siblings('input').addClass('border-red').siblings('.close').css('background-position', '2px -195px');
            $("#move_phone_code_error").text("çŸ­ä¿¡éªŒè¯ç é”™è¯¯");
            $("#move_phone_code_error").fadeIn();
            $("#move_phone_code_error_flag").val("0");
            return false ;
        }
        $("#move_phone_code_error").hide();
        $("#move_phone_code_error").hide().siblings('input').removeClass('border-red').siblings('.close').css('background-position', '2px -229px');
        $("#move_phone_code_error_flag").val("1");
        return true;
    }
};

var disabl = function(id1,id2){
    $("#"+id1).removeAttr("disabled");
    $("#"+id2).removeAttr("disabled");
};

var noDisabl = function(id1,id2){
    $("#"+id1).attr("disabled", true);
    $("#"+id2).attr("disabled", true);
};

//æ˜¾ç¤ºbox
function showBox(boxId,callbefore){
    if(callbefore){
        var ret = callbefore();
        if(ret){
            return ;
        }
    }
    $("#"+boxId).show();
    $(".background_box").show();
}