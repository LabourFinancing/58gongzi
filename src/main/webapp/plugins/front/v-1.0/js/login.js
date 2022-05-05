var Login = (function(win, $, pub, g_path, Base64, g_templ ) {
    var Login = {};
    var _isNeedCheckcode = false;//æ˜¯å¦éœ€è¦éªŒè¯ç 
    var _parseLginRet = function(ret) {
//		console.log("ç‚¹å‡»ç™»å½•æŒ‰é’®ï¼ŒæŽ¥æ”¶è¿”å›žç å¼€å§‹ï¼Œæ—¶é—´ï¼š"+new Date());
        var index = ret.indexOf("|");
        var sid = "";
        var errorCount = 0;
        var isChannelAgent = 0;//æ˜¯å¦æ˜¯ä»£ç†äººï¼ˆ1ï¼šæ˜¯ï¼›0ï¼šå¦ï¼‰
        if (index > 0) {
            var array = ret.split("|");
            data = array[0];
            sid = array[1];
            errorCount = array[2];
            isChannelAgent = array[3];
        }
        if (data == "viewResetPwd21") {
            var url = g_path + "/user/forgetPwd";
            win.location.href = url;
            return;
        }

        if (errorCount >= 3) {
            _isNeedCheckcode = true;
            Login.renderCheckcodeAreaHtml();//åˆ·æ–°éªŒè¯ç åŒºåŸŸ
        }
        // ç”¨æˆ·åé”™è¯¯
        if (data == "1") {
            Login.showLoginRet("è´¦å·æˆ–å¯†ç é”™è¯¯");
            $(".js-login-content input[name='userName']").focus();
        }
        // å¯†ç é”™è¯¯
        else if (data == "2") {
            if(errorCount <=3){
                Login.showLoginRet("è´¦å·æˆ–å¯†ç é”™è¯¯");
            }else if (errorCount > 3 && errorCount<7){
                Login.showLoginRet("å¯†ç å·²é”™è¯¯"+errorCount+"æ¬¡ï¼Œ7æ¬¡åŽå°†è¢«é”å®š");
            }else if (errorCount >= 7){
                Login.showLoginRet("è´¦å·å·²è¢«é”å®šï¼Œè¯·è”ç³»å®¢æœ");
            }
        }
        // æœªæ¿€æ´»
        else if (data == "3") {
            Login.showLoginRet("ç”¨æˆ·æœªæ¿€æ´»");
            $(".js-login-content input[name='userName']").focus();
            post(path_fo_js + '/user/sendverifyMail', {
                sid : sid
            }, "");
        }
        // ç”¨æˆ·å·²è¿‡æœŸ
        else if (data == "4") {
            Login.showLoginRet("ç”¨æˆ·å·²å…³é—­,è¯·è”ç³»å®¢æœ");
            $(".js-login-content input[name='userName']").focus();
        } else if (data == "5") {
            Login.showLoginRet("è´¦å·å·²è¢«é”å®šï¼Œè¯·è”ç³»å®¢æœ");
        } else if(data == "6"){
            var url = g_path + errorCount;
            win.location.href = url;
        }else if(data =="7"){
            Login.showLoginRet("è´¦å·æˆ–å¯†ç é”™è¯¯ï¼Œè¯·è”ç³»å®¢æœ");
        }else {
            // è¯·æ±‚åˆ°ç”¨æˆ·ç•Œé¢
            // è°ƒç”¨æ–¹æ³• å¦‚
            var fr = $("#fr").val();
            if (null != fr && "" != fr) {
                location.href = path_fo_js + "/rd.jsp?fr=" + fr;
                return;
            } else {
                var host = array[2];
                var indx = host.indexOf("www");
                var indx1 = host.indexOf(".");
                if (indx != -1) {
                    host = host.substring(indx1);
                }
                addcookie("user_log_cookie", sid, 0.5, host);
                addcookie("sid", sid, 1);

                try {
                    clab_tracker.ready(function(){
                        this.push({userId:sid,channelType:"ucpaas"});
                        this.track("open_page",{});
                    });

                } catch (e) {
                }

                if(getcookie("sid") == null || getcookie("sid") == 'null'){
                    addcookie("sid", sid, 1);
                }
                /*-------2017-9-25 ä»£ç†äººç™»å½•çš„è¯ç›´æŽ¥è¿›å…¥é‚€è¯·è¿”åˆ©ç•Œé¢-------------*/
                var url ;
                if(IsPC()){
                    url = g_path + "/controlHtmls/pages/account/account.html";
                    addcookie('menuId', 1, 24 * 30);
                    addcookie('menuChildId', 11, 24 * 30);
                }else{
                    url = g_path + "/controlHtmls/pages/account/account.html";
                }
                // 2017-4-17 å¢žåŠ   æ ¹æ®åŽå°è¿”å›žå€¼è·³è½¬åˆ°æŒ‡å®šé¡µé¢
                if (array.length == 5) {
                    url = array[4];
                }
                win.location.href = url;

//				post(path_fo_js + '/user/account', {
//					sid : sid
//				}, "");
            }
        }
    };

    Login.isRemmberUserName = function(){
        var checkbox = $(".js-check-box");
        var selected = checkbox.attr("data-selected");
        return selected === "true";
    };

    //æ£€æŸ¥éªŒè¯ç æ˜¯å¦æ­£ç¡®
    Login.checkImgCode  = function(code, callback, error){
        var url = "/page/user/checkcode.jsp";
        var data = "checkCode=" + code +"&checkCodeId="+$("#checkCodeId").val();
        var dataType = "text";
        var type = "post";
        Public.fetch(url, data, type, function(result){
            callback && callback(result);
        }, function(){
            error && error();
        }, dataType, false);
    };

    Login.init = function() {
        var userName = this.getUserNameFromLocal();// èŽ·å–æœ¬åœ°ç”¨æˆ·åï¼Œå¦‚æžœä¸Šæ¬¡è®°å½•äº†ç”¨æˆ·åï¼Œåˆ™å­˜åœ¨ä¸”æ˜¾ç¤ºåœ¨ç”¨æˆ·åè¾“å…¥æ¡†ä¸­
        if (userName) {
            this.setUserName(userName);
            this.checkRememberAcountCheckBox();// é€‰æ‹©è®°ä½è´¦å·checkbox
        }

        // 2017-4-17 å¢žåŠ  å¦‚æžœæœ‰fromå‚æ•°ï¼Œåˆ™åœ¨ç™»å½•å‚æ•°ä¸­åŠ ä¸Šæ¥æº,ç”¨äºŽç™»å½•åŽè·³è½¬é¡µé¢
        var reg = new RegExp("(^|&)from=([^&]*)(&|$)");
        var from = window.location.search.substr(1).match(reg);
        if (from != null) {
            var regUrl = $("#regUrl").attr("href");
            if (regUrl.indexOf('?') != -1) {
                $("#regUrl").attr("href", regUrl + "&from=" + from[2]);
            } else {
                $("#regUrl").attr("href", regUrl + "?from=" + from[2]);
            }
        }

        var isLogining = false;
        $("body").delegate(".js-login", "click", function() {
//			console.log("ç‚¹å‡»ç™»å½•æŒ‰é’®ï¼Œæ—¶é—´ï¼š"+new Date());
            if(isLogining === true){
                return ;
            }
            Login.showLoginRet("");
            var lgoinBtn = $(this);
            var content = $(".js-login-content");
            var validateInfors = [ "userName", "password" ];
            if(_isNeedCheckcode === true){
                validateInfors[2] = "imgCode";
            }
            var ret = Public.validate(content, validateInfors);
            if (ret.ret === false) {
                return;
            }
            var paras = ret.paras;
            var userName = paras.userName;
            if(Login.isRemmberUserName()){
                Login.setUserNameToLocal(userName);
            }else{
                Login.removeUserNameFromLocal();
            }

            var base64 = new Base64();
            var userName = base64.encode(userName);
            var password = paras.password;
            password = hex_md5(password);
            password = encodeURIComponent(password);
            var url = g_path + "/user/login";
            var data = {
                userid : userName,
                password : password,
                authType : $("#authType").val(),
                authId : $("#authId").val()
            };
            var type = "post";
            var dataType = "text";
            lgoinBtn.text("æ­£åœ¨ç™»å½•...");
            isLogining = true;
            addcookie("certifyTips","1");


            // 2017-4-17 å¢žåŠ  å¦‚æžœæœ‰fromå‚æ•°ï¼Œåˆ™åœ¨ç™»å½•å‚æ•°ä¸­åŠ ä¸Šæ¥æº,ç”¨äºŽç™»å½•åŽè·³è½¬é¡µé¢
            var reg = new RegExp("(^|&)from=([^&]*)(&|$)");
            var from = window.location.search.substr(1).match(reg);
            if (from != null) {
                data.from = from[2];
            }

            if(_isNeedCheckcode === true){//éœ€è¦éªŒè¯éªŒè¯ç 
                //æ£€æŸ¥éªŒè¯ç 
                var isCodeRight = false;//éªŒè¯ç æ˜¯å¦æ­£ç¡®
                var imgCode = paras.imgCode;
                Login.checkImgCode(imgCode, function(result){
                    if(result!=2){
                        isCodeRight = true;
                        addcookie('menuId', 1, 24 * 30);
                    }else{
                        isCodeRight = false;
                        var msg = "éªŒè¯ç é”™è¯¯";
                        Login.showLoginRet(msg);
                        lgoinBtn.text("ç™» å½•");
                        isLogining = false;
                        Login.renderCheckcodeAreaHtml();//åˆ·æ–°éªŒè¯ç åŒºåŸŸ
                    }
                }, function(){
                    Login.showLoginRet("æœåŠ¡å™¨å¼‚å¸¸");
                    isLogining = false;
                    lgoinBtn.text("ç™» å½•");
                    Login.renderCheckcodeAreaHtml();//åˆ·æ–°éªŒè¯ç åŒºåŸŸ
                });
                //éªŒè¯ç é”™è¯¯
                if(isCodeRight === false){
                    return;
                }
            }

            pub.fetch(url, data, type, function(ret) {
                isLogining = false;
                try {
                    _parseLginRet(ret);
//					console.log("ç‚¹å‡»ç™»å½•æŒ‰é’®ï¼Œç­‰å¾…æŽ¥æ”¶è¿”å›žç ï¼Œæ—¶é—´ï¼š"+new Date());
                } catch (e) {
                    alert(e);
                }
                lgoinBtn.text("ç™» å½•");
            }, function() {
                isLogining = false;
                lgoinBtn.text("ç™» å½•");
            }, dataType);

        });

    };

    Login.showLoginRet = function(ret) {
        $(".js-sumbit-msg").text(ret).show();
    };

    Login.renderCheckcodeAreaHtml = function() {
        var content = $("#checkCodeContent");
        var data  =  {date: new Date().getTime()};
        var html = g_templ("checkCodeContentTempl", data);
        content.html(html);
        content.show();
    };

    Login.checkRememberAcountCheckBox = function() {// é€‰æ‹©è®°ä½è´¦å·checkbox
        var checkBox = $(".js-check-box");
        checkBox.addClass("selected");
        checkBox.attr("data-selected", true);
    };

    Login.setUserName = function(userName) {
        $(".js-login-content input[name='userName']").val(userName);
    };

    Login.getUserNameFromLocal = function() {
        var key = pub.localMsgNames.userName;
        var userName = pub.getMsgFromLocal(key);
        return userName;
    };

    Login.setUserNameToLocal = function(userName) {
        var key = pub.localMsgNames.userName;
        pub.addMsgToLocal(key, userName);
    };

    Login.removeUserNameFromLocal = function() {
        var key = pub.localMsgNames.userName;
        pub.removeMsgFromLocal(key);
    };

    return Login;

})(window, $, Public, g_context_path, Base64, template);

$(function() {
    Login.init();
});

/**
 * åˆ¤æ–­æ˜¯å¦æ˜¯æ‰‹æœº
 * @returns {Boolean}
 */
function IsPC() {
    var flag = true;
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];

    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }

    /*if(window.location.toString().indexOf('pref=padindex') != -1){
    	
    }else{
        if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent))){  
          if(window.location.href.indexOf("?mobile")<0){
            try{
                if(/Android|Windows Phone|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
                	flag = false;
                }else if(/iPad/i.test(navigator.userAgent)){
                  //window.location.href="http://www.qq.com/pad/"
                }else{
                    //window.location.href="http://xw.qq.com/simple/s/index/"
                }
            }catch(e){}
        }
        }
    }*/
    return flag;
}

