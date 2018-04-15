// $(document).ready(function() {

    $('.tab').click(function () {
        $('.tab').removeClass('tab-open');
        $($(this).attr('href')).addClass('tab-open');
    });

// });

