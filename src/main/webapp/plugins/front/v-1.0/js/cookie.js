//è¯¥å‡½æ•°æŽ¥æ”¶3ä¸ªå‚æ•°ï¼šcookieåç§°ï¼Œcookieå€¼ï¼Œä»¥åŠåœ¨å¤šå°‘å°æ—¶åŽè¿‡æœŸã€‚è¿™é‡Œçº¦å®šexpireHoursä¸º0æ—¶ä¸è®¾å®šè¿‡æœŸæ—¶é—´ï¼Œå³å½“æµè§ˆå™¨å…³é—­æ—¶cookieè‡ªåŠ¨æ¶ˆå¤±
function addcookie(name,value,expireHours,domain){
    var cookieString=name+"="+escape(value)+";path=/";
    if(domain!=undefined){
        cookieString = cookieString+";domain="+domain;
    }
    //åˆ¤æ–­æ˜¯å¦è®¾ç½®è¿‡æœŸæ—¶é—´
    if(expireHours>0){
        var date=new Date();
        date.setTime(date.getTime()+expireHours*3600*1000);
        cookieString=cookieString+";expires="+date.toGMTString();
    }
    document.cookie=cookieString;
}
//è¯¥å‡½æ•°è¿”å›žåç§°ä¸ºnameçš„cookieå€¼ï¼Œå¦‚æžœä¸å­˜åœ¨åˆ™è¿”å›žç©º
function getcookie(name){
    var strcookie=document.cookie;
    var arrcookie=strcookie.split("; ");
    for(var i=0;i<arrcookie.length;i++){
        var arr=arrcookie[i].split("=");
        if(arr[0]==name){
            return arr[1];
        }
    }
    return null;
}
//è¯¥å‡½æ•°å¯ä»¥åˆ é™¤æŒ‡å®šåç§°çš„cookie
function deletecookie(name,domain){
    var date=new Date();
    date.setTime(date.getTime()-10000);
    var cookieString = name+"=null;path=/; expire="+date.toGMTString();
    if(domain!=undefined){
        cookieString = cookieString+";domain="+domain;
    }

    document.cookie=cookieString;
}