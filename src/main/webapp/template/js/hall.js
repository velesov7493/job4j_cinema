$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cinema/places?sessionId='+$('#iSession').val(),
        dataType: 'json'
    }).done(function (data) {
        for (var ticket of data) {
            $('#cell-r' + ticket.row + 'c' + ticket.col).addClass('occupied');
        }
    }).fail(function (err) {
        console.log(err);
    });
    $('#hall th, #hall td').click(function () {
        $('#hall td').removeClass('selected');
        $(this).addClass('selected');
    });
    $('#choose-place').submit(function () {
        let element = $('td.selected');
        let cid = element[0].id.substring(6);
        let n = cid.split('c');
        $('#iRow').val(n[0]);
        $('#iCol').val(n[1]);
    });
});