function reloadOccupiedPlaces() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cinema/places?sessionId='+$('#iSession').val(),
        dataType: 'json'
    }).done(function (data) {
        for (let ticket of data) {
            $('#cell-r' + ticket.row + 'c' + ticket.col).addClass('occupied');
        }
        setTimeout(function () {
            reloadOccupiedPlaces();
        }, 10000);
    }).fail(function (err) {
        console.log(err);
    });
}

$(document).ready(function () {
    reloadOccupiedPlaces();
});

$('#hall th, #hall td').click(function () {
    if (!$(this).hasClass('occupied')) {
        $('#hall td').removeClass('selected');
        $(this).addClass('selected');
    }
});

$('#choose-place').submit(function () {
    let element = $('td.selected');
    let cid = element[0].id.substring(6);
    let n = cid.split('c');
    $('#iRow').val(n[0]);
    $('#iCol').val(n[1]);
    return !isNaN(n[0]) && !isNaN(n[1]);
});