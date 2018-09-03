$("#filter-course-input").keyup(function () {

    var input = $(this).val().toLowerCase();
    $(".course-card").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(input) > -1);
    });
});

