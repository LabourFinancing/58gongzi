/* 图片上传验证参数：
this,
file的id,
图片的id, 
 最大限制maxSize,
回显位置id, 
回显位置高, 
回显位置宽,
回显错误位置id,
模态框放大myModal,
模态框的div的bigImage,
图片的a标签的class, 
模态框图片的id
*/
    function alonePreview(object, fileDomId, imgDomId, maxSize, previewDomId, previewDomHeight, previewDomWidth, previewErrorDomId, modalDomId, modalImgDivDomId, aDomClass,modalImgDomId){
    	// 清除错误消息
    	$("#" + previewErrorDomId).html("");
    	$("#" + imgDomId + "_new").remove();
    	$("#" + modalImgDomId + "_new").remove();
    	
        var file = $("#" + fileDomId).val();
        var fileName = file.substring(file.lastIndexOf(".")+1,file.length);
        fileName = fileName.toLowerCase();//大小写处理  
        // 点击取消
        if(fileName == ""){
        	return qingchu(imgDomId,modalImgDomId,fileDomId);
        }
        // 选择不是图片类型
        if(fileName != "jpeg" && fileName != "jpg" && fileName != "bmp" && fileName != "png"){
        	$("#" + previewErrorDomId).html("<span style='color:Red'>错误提示:格式不正确,支持的图片格式为：JPEG、JPG、BMP、PNG！</span>");
        	return qingchu(imgDomId,modalImgDomId,fileDomId);
        }
        
        // 图片大小
        var maxSizeStr = maxSize * 1048576;
        if(object.files[0].size > maxSizeStr){
        	$("#" + previewErrorDomId).html("<span style='color:Red'>错误提示:所选择的图片太大，图片大小最多支持"+ maxSize +"M!</span>");
        	return qingchu(imgDomId,modalImgDomId,fileDomId);
        }
        
        // 预览图片
        var reader = new FileReader();
        reader.onload = function(evt){
        	var pHtml = '';
        	
        	var aDomClassStr = '';
        	if(aDomClass != ''){
        		aDomClassStr = ' class=' + aDomClass;
        	}
        	// 模态框
        	if(modalDomId != ''){
        		pHtml = '<a' + aDomClassStr + ' href="#" data-toggle="modal" data-target="#' + modalDomId 
        				+ '"><img class="col-sm-12" id="' + imgDomId + '_new" src="' + evt.target.result + '"/></a>';
                $("#" + modalImgDomId).hide(); 
                $("#" + modalImgDivDomId).append('<img id="' + modalImgDomId + '_new" src="' + evt.target.result + '">');
        	} else {
        		// 固定大小
        		if(previewDomHeight != '' && previewDomWidth != ''){
        			pHtml = '<div style="height:' + previewDomHeight + 'px; width:' + previewDomWidth 
        					+ 'px; display:inline-block"><img id="' + imgDomId + '_new" style="max-width: 100%; max-height: 100%;" src="' + evt.target.result + '"/></div>';
        		} else {
            		pHtml = '<img class="col-sm-12" id="' + imgDomId + '_new" src="' + evt.target.result + '"/>';
        		}
        	}
            $("#" + imgDomId).hide(); 
            $("#" + previewDomId).append(pHtml); 
            
            // 图片加载完成后调整大小
            picLoad(imgDomId + '_new', previewDomId, '1', aDomClass, modalImgDivDomId);
            picLoad(modalImgDomId + '_new', modalDomId, '1', '', '');
        }
        reader.readAsDataURL(object.files[0]);
    }

	// 已存在图片加载完成时改变样式picLoad(图片Id，div的Id,模态框的id)
	function picLoad(id, previewDomId, proportion, aDomClass, modalImgDivDomId){
		var imgtemp = document.getElementById(id);//创建一个image对象
    	imgtemp.onload = function (){
        	if(parseInt(imgtemp.width) < parseInt(imgtemp.height)){
                $("#" + id).css("height", parseInt($('#' + previewDomId).innerWidth() * proportion));
                $("#" + id).css("width", "auto");
        	} else {
                $("#" + id).css("width", parseInt($('#' + previewDomId).innerWidth() * proportion));
                $("#" + id).css("height", "auto");
        	}

        	if(aDomClass != ''){
        		$("."+ aDomClass).click(function(){
        			$("#"+ modalImgDivDomId).show();
        		});
        	}
    		parent.iFrameHeight();
    	 }
	}
	
	// 输入框清空
	function qingchu(imgDomId,modalImgDomId,fileDomId){
        $("#" + modalImgDomId).show(); 
        $("#" + imgDomId).show(); 
    	$("#" + fileDomId).val("");
		parent.iFrameHeight();
        return false;
	}
