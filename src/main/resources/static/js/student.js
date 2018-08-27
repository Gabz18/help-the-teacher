var studentId = null;
var student = null;

$(".notes-button").click(function () {

    studentId = $(this).attr("id");
    $.get("student/get/" + studentId, function (data) {

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

    $.post("student/set-notes/" + studentId, { notes: notes }, function (data) {
        location.reload();
    });
})