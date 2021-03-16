function TodayClick(start_time, end_time){
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
}

function OneWeekBeforeClick(start_time, end_time){
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
}

function OneMonthBeforeClick(start_time, end_time) {    
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
}

function ThreeMonthBeforeClick(start_time, end_time) {
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
}