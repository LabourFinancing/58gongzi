var ComponentsSelect2 = function() {
    var handleDemo = function() {
        function formatRepo(repo) {
        	if(repo.idCard != undefined){
            	var markup = "<div class='select2-result-repository clearfix'>" +
   			 				 "<div class='select2-result-repository__title'>" + repo.idCard + "-" + repo.name + "</div>";
            	return markup;
        	}
        	return "";
        }

        function formatRepoSelection(repo) {
        	$.ajax({
    			url: "policyVoList?idCard=" + repo.idCard,
    			type: 'POST',  
    			async: true,  
    			cache: false,  
    			contentType: false,  
    			processData: false,  
    			success: function (data) { 
    				var result = eval("("+data+")");
    				$("#policy").empty(); 
					$("#policyInfo").html("");
    				var optionHtml = "<option value='0'>请选择</option>";
    				var inputHtml = "<input type='hidden' id='policy_0' value=' , , , , , , , , , , , , , , '></input>";
    				$.each(result.policyVoList, function(idx, obj) {
    					optionHtml = optionHtml + "<option value='"+obj.id+"'>" 
    										+ obj.planName + "---" + obj.policyOwnerName + "</option>";
    					
    					inputHtml =  inputHtml + "<input type='hidden' id='policy_" + obj.id + "' value='" 
    									+ obj.planId + "," 
    									+ obj.policyId + "," 
    									+ obj.planType + "," 
    									+ obj.enterpriseId + "," 
    									+ obj.policyOwnerName + "," 
    									+ obj.policyOwnerIdCard + "," 
    									+ obj.policyOwnerJobType + "," 
    									+ obj.userGender + "," 
    									+ obj.policyOwnerSendEnterprise + "," 
    									+ obj.startDate + "," 
    									+ obj.stopDate + "," 
    									+ obj.endDate + "," 
    									+ obj.enterpriseName + "," 
    									+ obj.planName + "," 
    									+ obj.id + "'></input>";
    				});
					$("#policy").append(optionHtml);
					$("#policyInfo").append(inputHtml);
    			}, 
    			error: function (data) {  
    				var result = eval("("+data+")");
    		    	alert(result.msg);
    			}  
            });
            return repo.idCard+"-"+repo.name;
        }

        $(".js-data-example-ajax").select2({
            width: "off",
            ajax: {
                url: "userList",
                dataType: 'json',
                delay: 250,
                data: function(params) {
                    return {
                    	idCard: params.term
                    };
                },
                processResults: function(data) {
                    // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to
                    // alter the remote JSON data
                    return {
                        results: data.userList
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
        //main function to initiate the module
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