var Common = {

     progress:function(options) {
        $("body").append("<!-- loading -->" +
            "<div class='modal fade' id='loading' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' data-backdrop='static'>" +
            "<div class='modal-dialog' role='document'>" +
            "<div class='modal-content' style='width:65px;margin-left:40%;margin-top:50%'>" +
            "<div class='modal-body'>" +
            "<img src='../img/loading.gif' />" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
        );
        if (options == 'open')
            $("#loading").modal("show");
        else if (options == 'close')
            $("#loading").modal("hide");
    }
}