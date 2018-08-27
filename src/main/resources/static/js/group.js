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
    $.post( "group/edit/" + groupId, { editedGroupName: input }, function () {
        location.reload();
    });
});