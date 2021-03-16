var ComponentsSelect2 = function() {
    var handleDemo = function() {
        function formatRepo(repo) {
        	if(repo.entName != undefined){
            	var markup = "<div class='select2-result-repository clearfix'>" +
   			 				 "<div class='select2-result-repository__title'>" + repo.entName + "</div>";
            	return markup;
        	}
        	return "";
        }

        function formatRepoSelection(repo) {
        	if(repo.id != undefined){
        		$("#ownerEnterpriseId").val(repo.id);
                return repo.entName;
        	}
        	 return "";
        }

        $(".js-data-example-ajax").select2({
            width: "off",
            ajax: {
            	type: 'POST',
                url: "enterpriseList",
                dataType: 'json',
                delay: 250,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                data: function(params) {
                    return {
                    	eName: params.term
                    };
                },
                processResults: function(data) {
                    return {
                        results: data.enList
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