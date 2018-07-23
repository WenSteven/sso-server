function loadData(page) {
    $('tbody tr').remove();
    $('#currentPage').val();
    Common.progress('open');
    $.ajax({
        url: '/resources/list',
        method: 'get',
        dateType: 'json',
        data: {
            pageNo: page
        },
        success: function (data) {
            Common.progress('close');
            if (data != null) {
                data.data.forEach(function (item, index) {
                    $('table').append("<tr>" +
                        "<td>" + (index + 1) + "</td>" +
                        "<td>" + item.ext + "</td>" +
                        "<td>" + item.createTime + "</td>" +
                        "<td><a href='/resources/detail/"+item.rid+"?ext="+item.ext+"'>查看</a></td>"+
                        "</tr>")
                });

                var pager = localStorage.getItem('page');
                $('#show').text('共' + data.totalPages + '页,' + data.totalCount + '条记录');
                $('#currentPage').val(pager);
                if (data.totalPages == pager) {
                    $('.next').parent().addClass('disabled');
                } else
                    $('.next').parent().removeClass('disabled');

                if (pager == '1') {
                    $('.pre').parent().addClass('disabled');
                } else
                    $('.pre').parent().removeClass('disabled');
            } else {
                $('.next').parent().addClass('disabled');
                $('.pre').parent().addClass('disabled');
            }
        },
        error: function () {
            Common.progress('close');
            alert('请求出错');
        }
    });

}

$(document).ready(function () {
    localStorage.setItem('page','1');
    loadData(1);
});

$('.pre').on('click', function () {
    if (!$(this).parent().hasClass('disabled')) {
        var page = parseInt($('#currentPage').val()) - 1;
        localStorage.setItem('page',page+'')
        loadData(page);
    }

});

$('.next').on('click', function () {
    if (!$(this).parent().hasClass('disabled')) {
        var page = parseInt($('#currentPage').val()) + 1;
        localStorage.setItem('page',page+'')
        loadData(page);
    }
});