var BASE_URL = "http://localhost:8080/Back_End_war/";

$("#btnCustomerRegister").click(function (){
    saveCustomer();
});

function saveCustomer() {
    var jsonData = {
        cusId : $("#txtCusId").val(),
        cusName : $("#txtCusName").val(),
        cusAddress : $("#txtCusAddress").val(),
        cusEmail : $("#txtCusEmail").val(),
        cusPassword : $("#txtCusPassword").val(),
        cusContact : $("#txtCusContact").val(),
        cusNIC : $("#txtCusNIC").val(),
    }

    var data = new FormData();
    data.append('cusDetails', JSON.stringify(jsonData));
    let image = $("#txtCusNICPhoto")[0].files[0];
    data.append("cusNICPhoto", image);

    console.log(image);

    $.ajax({
        url: BASE_URL + "customer",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert(res.message);
        },
        error: function (error) {
            // alert(error.responseJSON.message);
            console.log(error)
        }
    });
}

function searchCustomer(id){

}