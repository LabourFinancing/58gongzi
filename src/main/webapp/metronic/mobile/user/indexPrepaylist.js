(function (doc, win) {
    var startTime = null;
    var endTime = null;
    $('#startTime').calendar({
        onChange: function (p, values, displayValues) {
            startTime = values[0];
            console.log("起始时间：", values[0]);
            console.log("起始时间戳：", displayValues[0]);
        }
    });
    $('#endTime').calendar({
        onChange: function (p, values, displayValues) {
            endTime = values[0];
            console.log("截止时间：", values[0]);
            console.log("截止时间戳：", displayValues[0]);
        }
    });

    $('#HisPeriodSearch').on('click', function () {
        if(!startTime) {
            $.toptip('请先选择起始时间', 'error');
            return;
        }
        if(!endTime) {
            $.toptip('请先选择截止时间', 'error');
            return;
        }
        
        console.log("起始时间：", startTime, "截止时间：", endTime);

        // 查询中
        $('#HisPeriodSearch').addClass('weui-btn_loading').html('查询中...');
        $('#noData').hide();
        $('#loadMore').show();

        // 查询成功
        setTimeout(function () {
            $('#loadMore').hide();
            $('#dataLists').show();
            $('#HisPeriodSearch').removeClass('weui-btn_loading').html('查询');
        }, 1000);
    });
    
    // 滚动加载， 暂时不需要
     $(document.body).infinite();
     var loading = false;  //状态标记
     $(document.body).infinite().on("infinite", function() {
         if(loading) return;
         loading = true;
         console.log('数据开始加载');
         setTimeout(function() {
             console.log('数据加载成功');
             loading = false;
         }, 1500);
     });
})(document, window);
