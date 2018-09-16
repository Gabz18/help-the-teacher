var studentId = null;
var student = null;

$(".student-row").click(function () {

    var id = $(this).attr("id");
    window.location.href = "/student/" + id;
});

$("#filter-student-input").keyup(function () {

    var input = $(this).val().toLowerCase();
    $(".student-row").filter(function () {
       $(this).toggle($(this).text().toLowerCase().indexOf(input) > -1);
    });
});

$(".notes-button").click(function () {

    studentId = $(this).attr("id");
    $.get("/student/get/" + studentId, function (data) {

        student = data;
        $("#notesModalTitle").text("Notes pour " + student.firstname + " " + student.lastname);
        if (student.learningNotes == null || student.learningNotes == "") {
            $("#notesModalBody").text("Pas de notes");
        } else {
            $("#notesModalBody").text(student.learningNotes)
        }
    })
});

$("#edit-notes-button").click(function () {

    $(this).toggle();
    $("#submit-notes-button").toggle();
    $("#notes-input").toggle();
    $("#notesModalBody").toggle();

    $("#notes-input").val(student.learningNotes);
});

$("#submit-notes-button").click(function () {

    $(this).toggle()
    $("#edit-notes-button").toggle();
    $("#notes-input").toggle();
    $("#notesModalBody").toggle();

    var notes = $("#notes-input").val();
    
    if (notes == "") {
        notes = null;
    }

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: "POST",
        url: "/student/set-notes/" + studentId,
        data: { notes: notes },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            location.reload();
        },
        error: function (request, status, error) {
            alert(status);
        }
    });
})