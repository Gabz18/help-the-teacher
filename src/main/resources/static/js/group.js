$(".edit-name-button").click(function () {

   var groupId = $(this).attr("id").slice(11);
   $("#group-title" + groupId).toggle();
   $("#group-edition" + groupId).toggle();
   $(this).toggle();
});

$(".cancel-edit").click(function () {

    var groupId = $(this).attr("id").slice(11);

    $("#edit-button" + groupId).toggle();
    $("#group-edition" + groupId).toggle();
    $("#group-title" + groupId).toggle();
});

$(".submit-edit").click(function () {

    var groupId = $(this).attr("id").slice(11);
    var input = $("#editedGroupName" + groupId).val();

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: "POST",
        url: "group/edit/" + groupId,
        data: { editedGroupName: input },
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
});

//supprimer groupe
$(".deleteGroupButton").click(function () {

    var groupId = $(this).attr("id").slice(17);
    $("#confirmGroupDelete").attr("href", "/group/remove/" + groupId);
});