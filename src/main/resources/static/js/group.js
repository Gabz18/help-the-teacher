$("#edit-button").click(function () {

   $("#group-title").toggle();
   $("#group-edition").toggle();
   $(this).toggle();
});

$("#cancel-edit").click(function () {

   $("#edit-button").toggle();
    $("#group-edition").toggle();
    $("#group-title").toggle();
});

$("#submit-edit").click(function () {

    var input = $("#editedGroupName").val();
    var groupId= $("#groupId").val();

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