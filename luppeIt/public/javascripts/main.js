$(document).ready(function() {

    $('input[name=tag]').keyup(function() {
        clearTimeout($.data(this, 'timer'));
        var wait = setTimeout(search, 1000);
        $(this).data('timer', wait);
    });

    function search() {
        alert($('input[name=tag]').val());
    }

});