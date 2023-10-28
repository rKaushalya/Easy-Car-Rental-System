var BASE_URL = "http://localhost:8080/Back_End_war/";

getAllCustomers();

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

$(function (){
    $("#txtCusNICPhoto").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#preview").attr("src",x);
    });
})

function getAllCustomers() {
    $("#tblViewCustomer").empty();

    $.ajax({
        url: BASE_URL + 'customer',
        dataType: "json",
        success: function (response) {
            let customers = response.data;
            for (let i in customers) {
                let cus = customers[i];
                let id = cus.cusId;
                let name = cus.cusName;
                let address = cus.cusAddress;
                let contact = cus.cusContact;
                let email = cus.cusEmail;
                let nic = cus.cusNIC;
                let row = `<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${contact}</td><td>${email}</td><td>${nic}</td></tr>`;
                $("#tblViewCustomer").append(row);
            }
            // bindTrEvents();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}
