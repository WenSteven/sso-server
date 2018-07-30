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
                        "<td>" + parse(item.ext) + "</td>" +
                        "<td>" + substring0(item.describe)+"</td>" +
                        "<td><a href='/resources/detail/"+item.rid+"?ext="+item.ext+"&desc="+encodeURI(item.describe)+"&t="+encodeURI(item.createTime)+"'>查看</a></td>"+
                        "</tr>")
                });
                var pager = localStorage.getItem('page');
                $('#show').text('共' + data.totalPages + '页,' + data.totalCount + '条记录,当前第'+pager+'页');
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
    $('#desc').val('')
    localStorage.setItem('page','1');
    loadData(1);
});

function parse(type){
  if(type=='1')
      return'图片';
  if(type=='2')
      return '视频';
}

function substring0(str){
    if(str!=null)
        return str.substring(0,10)+'...'
    return '暂未填写'
}

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

$('#upload').on('click', function () {

    if($("#choose_file").val()==''){
        alert("请选择要上传的文件")
        return
    }
    $.ajax({
        url: '/resources/upload',
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        name: 'file',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrf_name, csrf_token);
        },
        success: function () {
            // Handle upload success
            alert("上传成功");
            $('#desc').val('')
            $('#choose_file').val('')
            var page=parseInt(localStorage.getItem('page'))
            loadData(page)
        },
        error: function () {
            // Handle upload error
            $("#upload-file-message").text("File not uploaded ");
        }
    });
})