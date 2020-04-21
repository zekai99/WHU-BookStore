/************ 分页js继承jquery *************/
$.fn.pageBar = function(options) {
    var configs = {
        PageIndex: 1,  //设置当前页码
        PageSize: 20,  //设置分页大小
        TotalPage: 0,   //设置总页数
        RecordCount: 0,  //设置数据总数
        showPageCount: 4,   //设置连续页数的个数
        onPageClick: function(pageIndex) {
            return false;   //默认的翻页事件
        }
    }
    $.extend(configs, options)
    var tmp = "",
        i = 0,
        j = 0,
        a = 0,
        b = 0,
        totalpage = parseInt(configs.RecordCount / configs.PageSize);
    totalpage = configs.RecordCount % configs.PageSize > 0 ? totalpage + 1 : totalpage;
    tmp += "<span>总计：" + configs.RecordCount + "</span > ";
    tmp += " <span>页数：" + totalpage + "</span>";
    if (configs.PageIndex > 1) {
        tmp += "<a>上一页</a>"
    } else {
        tmp += "<a class=\"no\">上一页</a>"
    }
    tmp += "<a>1</a>";
    if (totalpage > configs.showPageCount + 1) {
        if (configs.PageIndex <= configs.showPageCount) {
            i = 2;
            j = i + configs.showPageCount;
            a = 1;
        } else if (configs.PageIndex > totalpage - configs.showPageCount) {
            i = totalpage - configs.showPageCount;
            j = totalpage;
            b = 1;
        } else {
            var k = parseInt((configs.showPageCount - 1) / 2);
            i = configs.PageIndex - k;
            j = configs.PageIndex + k + 1;
            a = 1;
            b = 1;
            if ((configs.showPageCount - 1) % 2) {
                i -= 1
            }
        }
    }
    else {
        i = 2;
        j = totalpage;
    }
    if (b) {
        tmp += "..."
    }
    for (; i < j; i++) {
        tmp += "<a>" + i + "</a>"
    }
    if (a) {
        tmp += " ... "
    }
    if (totalpage > 1) {
        tmp += "<a>" + totalpage + "</a>"
    }
    if (configs.PageIndex < totalpage) {
        tmp += "<a>下一页</a>"
    } else {
        tmp += "<a class=\"no\">下一页</a>"
    }
    tmp += "<input type=\"text\" /><a>GO</a>";
    var pager = this.html(tmp);
    var index = pager.children('input')[0];
    pager.children('a').click(function() {
        var cls = $(this).attr('class');
        if (this.innerHTML == '上一页') {
            if (cls != 'no') {
                configs.onPageClick(configs.PageIndex - 2)
            }
        } else if (this.innerHTML == '下一页') {
            if (cls != 'no') {
                configs.onPageClick(configs.PageIndex)
            }
        } else if (this.innerHTML == 'GO') {
            if (!isNaN(index.value) && index.value != "") {
                var indexvalue = parseInt(index.value);
                indexvalue = indexvalue < 1 ? 1 : indexvalue
                indexvalue = indexvalue > totalpage ? totalpage : indexvalue
                configs.onPageClick(indexvalue - 1)
            }
        } else {
            if (cls != 'cur') {
                configs.onPageClick(parseInt(this.innerHTML) - 1)
            }
        }
    }).each(function() {
        if (configs.PageIndex == parseInt(this.innerHTML)) {
            $(this).addClass('cur')
        }
    })
}
