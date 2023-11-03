let URL = "http://localhost:8080/Back_End_war/";

$("#btnAdminLogin").click(function () {
    checkAdmin();
});

function checkAdmin() {
    let username = $("#adminUserName").val();
    let password = $("#adminPassword").val();

    $.ajax({
        url: URL + 'admin/check?username=' + username + "&password=" + password,
        success: function (res) {
            console.log(res.message);
            $("#adminUserName").empty();
            $("#adminPassword").empty();
            $("#content1").load('dashBoardForm.html');
        },
        error: function (error) {
            console.log(error);
        }
    });
}