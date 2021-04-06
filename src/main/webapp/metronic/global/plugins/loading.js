var _PageHeight = document.documentElement.clientHeight,

_PageWidth = document.documentElement.clientWidth;

var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,

_LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;

var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 50px; line-height: 50px; padding-left: 20px; padding-right: 20px; background: #fff url(http://www.58gongzi.com.cn/metronic/layouts/layout4/img/loading-spinner-blue.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">页面加载中，请等待...</div></div>';

document.write(_LoadingHtml);

document.onreadystatechange = completeLoading;

function completeLoading() {

if (document.readyState == "complete") {

   var loadingMask = document.getElementById('loadingDiv');

    loadingMask.parentNode.removeChild(loadingMask);

 }

}