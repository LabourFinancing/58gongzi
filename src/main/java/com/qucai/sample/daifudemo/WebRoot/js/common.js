	function isNumber(oNum) {
		if (!oNum)
			return false;
		var strP = /^\d+(\.\d+)?$/;
		if (!strP.test(oNum))
			return false;
		try {
			if (parseFloat(oNum) != oNum)
				return false;
		} catch (ex) {
			return false;
		}
		return true;
	}
	function isFigure(data) {
		var reg = /^\d*$/;
		return reg.test(data);
	}
	function judgeDate(date_begin,date_end) {
		var date_begin_b;
//		var date_end_e;
		if (date_begin != undefined && date_end != undefined){
		    date_begin_b = date_begin.replace(/-/g,'');
		    date_begin_e = date_end.replace(/-/g,'');		   
		    if (date_begin_b > date_begin_e && date_begin_e != '') {
		        return false;
		    }
		}
		return true;
	}
	
	function checkPhone(v) {
		var a = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|14\d{9}|15\d{9}|17\d{9}|18\d{9}$/;
		if (v.length != 11 || !v.match(a)) {
			return false;
		} else {
			return true;
		}
	}