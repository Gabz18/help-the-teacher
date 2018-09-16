$("#filter-course-input").keyup(function () {

    var input = $(this).val().toLowerCase();
    $(".course-card").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(input) > -1);
    });
});

$(".group-list-link").click(function () {

    var id = $(this).attr("id").slice(10);

    $.get("/course/" + id, function (data) {

        var participants = data.participants;
        var missingStudents = data.missingStudents;

        $("#student-list").html("");

        missingStudents.forEach(function (missing) {
            $("#student-list").append('<li class="list-group-item list-group-item-danger present-absent" id="student-' + missing.studentId + '">' + missing.firstname + ' ' + missing.lastname + '</li>\n');
        });

        participants.forEach(function (participant) {
            $("#student-list").append('<li class="list-group-item list-group-item-success present-absent" id="student-' + participant.studentId + '">' + participant.firstname + ' ' + participant.lastname + '</li>\n');
        });
    });

});

$(document).on('click', '.present-absent', function () {

    var id = $(this).attr("id").slice(8);
    window.location.href="/student/" + id;

});