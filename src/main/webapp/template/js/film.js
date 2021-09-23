$('.filmList li').click(function () {
    $('.filmList li').removeClass('active');
    $(this).addClass('active');
});

$('#choose-film').submit(function () {
    let element = $('li.active');
    $('#iSession').val(element[0].id);
});