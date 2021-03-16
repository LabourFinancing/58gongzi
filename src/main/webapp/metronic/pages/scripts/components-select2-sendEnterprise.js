var ComponentsSelect2 = function() {

    var handleDemo = function() {
        // 获取时间
        function getMyDate(str){  
            var oDate = new Date(str),  
            oYear = oDate.getFullYear(),  
            oMonth = oDate.getMonth()+1,  
            oDay = oDate.getDate(),  
            oHour = oDate.getHours(),  
            oMin = oDate.getMinutes(),  
            oSen = oDate.getSeconds(),  
            oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);//最后拼接时间  
            return oTime;  
        };  
        //补0操作  
        function getzf(num){  
            if(parseInt(num) < 10){  
                num = '0'+num;  
            }  
            return num;  
        }  
        // @see https://select2.github.io/examples.html#data-ajax
        function formatRepo(repo) {
        	if(repo.name != undefined){
            	var markup = "<div class='select2-result-repository clearfix'>" +
   			 				 "<div class='select2-result-repository__title'>" + repo.name + "</div>";
            	return markup;
        	}
        	return "";
        }

        function formatRepoSelection(repo) {
        	if(repo.id != undefined){
            	$("#se_id").val(repo.id);
            	$("#se_name").html(repo.name);
            	$("#se_regNum").html(repo.regNum);
            	$("#se_legalPerson").html(repo.legalPerson);
            	if(repo.validPeriod != undefined){
            		$("#se_validPeriod").html(getMyDate(repo.validPeriod));
            	} else {
            		$("#se_validPeriod").html("长期");
            	}
            	$("#se_address").html(repo.address);
            	$("#se_capital").html(repo.capital);
	   			$("#last").show();
				$("#last").stop().animate({height: 320},function(){
					parent.iFrameHeight();
				});
	   			$("#responseName").val(repo.name);
                return repo.name;
        	}
            return "无同名用工单位";
        }

        $(".js-data-example-ajax").select2({
            width: "off",
            ajax: {
            	type: 'POST',
                url: "sendEnterpriseList",
                dataType: 'json',
                delay: 250,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                data: function(params) {
                	$(".select2-selection__rendered").html(params.term);
                	$("#responseName").val(params.term);
                    return {
                    	eName: params.term
                    };
                },
                processResults: function(data) {
                	var status = 0;
                	$.each(data.seList,function(idx,obj){
                		if(obj.name == $("#responseName").val()){
                        	$("#se_id").val(obj.id);
                        	$("#se_name").html(obj.name);
                        	$("#se_regNum").html(obj.regNum);
                        	$("#se_legalPerson").html(obj.legalPerson);
                        	if(obj.validPeriod != undefined){
                        		$("#se_validPeriod").html(getMyDate(obj.validPeriod));
                        	} else {
                        		$("#se_validPeriod").html("长期");
                        	}
                        	$("#se_address").html(obj.address);
                        	$("#se_capital").html(obj.capital);
            	   			$("#last").show();
            				$("#last").stop().animate({height: 320},function(){
            					parent.iFrameHeight();
            				});
            	   			status = 1;
                		}
                	  });
                	if(status == 0){
                		$("#last").stop().animate({height: 0},function(){
    		            	$("#se_id").val("");       
    						$("#last").hide();
        					parent.iFrameHeight();
    					});
                	}
                    // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to
                    // alter the remote JSON data
                    return {
                        results: data.seList
                    };
                },
                cache: true
            },
            escapeMarkup: function(markup) {
                return markup;
            }, // let our custom formatter work
            minimumInputLength: 1,
            templateResult: formatRepo,
            templateSelection: formatRepoSelection
        });
    }
    return {
        init: function() {
            handleDemo();
        }
    };

}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        ComponentsSelect2.init();
    });
}