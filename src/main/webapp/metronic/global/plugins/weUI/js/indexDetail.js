(function (doc, win) {
    var queryStr = location.search.replace('?', '');
    var queryArr = queryStr.split('&');

    var queryObj = {};
    queryArr.forEach(function (query) {
        query = query.split('=');

        queryObj[query[0]] = query[1];
    });
    
    console.log('url参数组：', queryObj);
})(document, window);
