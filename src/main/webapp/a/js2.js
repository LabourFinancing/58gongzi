	var scale= 0;
    $(document).ready(function() {        
        $('.ad-image-wrapper').smartZoom({'containerClass':'zoomableContainer'});        
        $('#topPositionMap,#leftPositionMap,#rightPositionMap,#bottomPositionMap').bind("click", moveButtonClickHandler);
        $('#zoomInButton,#zoomOutButton').bind("click", zoomButtonClickHandler);
        
        function zoomButtonClickHandler(e){
            var scaleToAdd = 0.8;
            if(e.target.id == 'zoomOutButton')
			{
				scaleToAdd = -scaleToAdd;
			}
            scale += scaleToAdd;
            $('.ad-image-wrapper').smartZoom('zoom', scaleToAdd);
        }        
        function moveButtonClickHandler(e){
            var pixelsToMoveOnX = 0;
            var pixelsToMoveOnY = 0;
    
            switch(e.target.id){
                case "leftPositionMap":
                    pixelsToMoveOnX = 50;	
                break;
                case "rightPositionMap":
                    pixelsToMoveOnX = -50;
                break;
                case "topPositionMap":
                    pixelsToMoveOnY = 50;	
                break;
                case "bottomPositionMap":
                    pixelsToMoveOnY = -50;	
                break;
            }
            $('.ad-image-wrapper').smartZoom('pan', pixelsToMoveOnX, pixelsToMoveOnY);
        } 
    });
	
 document.onkeydown = function (w){  
// 兼容IE和Firefox获得keyBoardEvent对象
w = (w)? w : ((window.event)? window.event :"")   
  // 兼容IE和Firefox获得keyBoardEvent对象的键值
var key= w.keyCode?w.keyCode:w.which;   
if(key== 87){   
 　　　　　　// 按下
 document.getElementById("zoomInButton").click();
 				 
}

if(key== 83){   
 　　　　　　// 按下
 document.getElementById("zoomOutButton").click();
                return false;
				// do something;
}   
if(key== 81){   
 　　　　　　// 按下
 document.getElementById("Radio2").click();
                return false;
				// do something;
}   
if(key== 69){   
 　　　　　　// 按下
 document.getElementById("Radio1").click();
                return false;
				// do something;
}   
 
 } 

 
 
 
 
 
 var deg = 0;
$(function(){
    $('.ad-gallery').adGallery();
});





 			function test(obj) {
    if (obj.id == "Radio1") {
		  deg += 90;
        document.querySelector(".ad-active2 img").style.transform = "rotate(" + deg + "deg)";
    }
 }
  				function test2(obj) {
    if (obj.id == "Radio2") {
		  deg += -90;
        document.querySelector(".ad-active2 img").style.transform = "rotate(" + deg + "deg)";
    }
 }
 
 
 



  				$(document).ready(function(){
                    $('.user-sex input').each(function(){
                        var self = $(this),
                                label = self.next(),
                                label_disabled= self.attr("label_disabled"),
                                label_text = label.text();
                        label.remove();
                        if(label_disabled=='true'){
                            self.iCheck({
                                checkboxClass: 'icheckbox_sm-no',
                                radioClass: 'radio_sm-blue',
                                insert: label_text
                            });
                        }else{
                            self.iCheck({
                                checkboxClass: 'icheckbox_sm-blue',
                                radioClass: 'radio_sm-blue',
                                insert: label_text
                            });
                        }
                    });
                    $("#claimAttachListSize").trigger("func");
                });
					
					
					
					
      function focusNextInput(thisInput){
          var inputs = document.getElementsByTagName("input");
          for(var i = 0;i<inputs.length;i++){
            // 回车切换，如果是最后一个，则焦点回到第一个
            if(i==(inputs.length-1)){
              inputs[0].focus();
              break;
            }else if(thisInput == inputs[i]){
              inputs[i+1].focus();
              break;
            }
          }
      }  
	  
