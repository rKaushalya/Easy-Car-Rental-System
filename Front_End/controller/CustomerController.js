var BASE_URL = "http://localhost:8080/Back_End_war/";

getAllCustomers();
getNewCustomerId();
getVehiclesDetails();
generateNewBookingId();

$("#btnCustomerRegister").click(function () {
    saveCustomer();
});

$("#btnForget").click(function () {
    let email = $("#cusForgetEmail").val();
    let nPw = $("#cusForgetPassword").val();
    let cPw = $("#cusNewPassword").val();

    if (nPw === cPw) {
        forgetPassword(email, nPw);
    } else {
        alert("Password didn't match.!");
    }

});

function saveCustomer() {
    let cusId = $("#txtCusId").val();
    let cusName = $("#txtCusName").val();
    var jsonData = {
        cusId: cusId,
        cusName: cusName,
        cusAddress: $("#txtCusAddress").val(),
        cusEmail: $("#txtCusEmail").val(),
        cusPassword: $("#txtCusPassword").val(),
        cusContact: $("#txtCusContact").val(),
        cusNIC: $("#txtCusNIC").val(),
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

            $("#showCusId").text(cusId);
            $("#lnkSingIn>a").text(cusName);
            $("#lnkSingIn>i").css({
                "display": "none"
            });

            setView($("#indexForm"));
        },
        error: function (error) {
            // alert(error.responseJSON.message);
            console.log(error)
        }
    });
}

function searchCustomer(name) {

}

$(function () {
    $("#txtCusNICPhoto").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#preview").attr("src", x);
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
                let btn = "<button class=\"btn btn-outline-danger\">delete</button>";
                let row = `<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${contact}</td><td>${email}</td><td>${nic}</td><td>${btn}</td></tr>`;
                $("#tblViewCustomer").append(row);
            }
            bindTrEvents();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

function deleteCustomer(id) {
    $.ajax({
        url: BASE_URL + "customer?cusId=" + id,
        method: 'delete',
        success: function (res) {
            console.log(res.message);
            return true;
        },
        error: function (error) {
            console.log(error)
            return false;
        }
    });
}

function bindTrEvents() {
    $('#tblViewCustomer>tr button').click(function () {
        let id = $(this).parent().parent().children().eq(0).text();

        let consent = confirm("Do you want to delete.?");
        if (consent) {
            let response = deleteCustomer(id);
            if (response) {
                alert("Customer Deleted");
                getAllCustomers();
            } else {
                alert("Customer Not Removed..!");
                getAllCustomers();
            }
        }
    });
}

function forgetPassword(id, password) {
    var data = new FormData();
    data.append("id", id);
    data.append("password", password);

    $.ajax({
        url: BASE_URL + "customer/password",
        method: 'put',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            console.log(res.message);
            alert(res.message);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function getNewCustomerId() {
    $.ajax({
        url: BASE_URL + 'customer/id',
        dataType: "json",
        success: function (response) {
            $("#txtCusId").val(response.data);
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

function getVehiclesDetails() {
    $("#tblViewCusVehicle").empty();

    $.ajax({
        url: BASE_URL + 'vehicle',
        dataType: "json",
        success: function (response) {
            let vehicle = response.data;
            for (let i in vehicle) {
                let veh = vehicle[i];
                let brand = veh.brand;
                let color = veh.color;
                let noOfPassenger = veh.noOfPassenger;
                let type = veh.type;
                let frontView = veh.filePath;
                console.log(frontView);

                let row = `<tr><td>${brand}</td><td>${type}</td><td>${color}</td><td>${noOfPassenger}</td>
                <td><img src="${"asset/img/uploads/" + frontView}" width="100px" height="80px"></td></tr>`;
                $("#tblViewCusVehicle").append(row);
            }
            checkForBooking();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

//for book a car
let sampleRegNo;

function checkForBooking() {
    $("#tblViewCusVehicle>tr").click(function () {
        $("#tblCusBookingLoadData").empty();
        let text = $("#lnkSingIn>a").text();
        let name = $(this).children().eq(0).text();
        let type = $(this).children().eq(1).text();
        if (text!=="Sing in"){
            $.ajax({
                url: BASE_URL + 'vehicle/booking?brand=' + name,
                dataType: "json",
                success: function (response) {
                    let veh = response.data;
                    sampleRegNo = veh.registerNo;
                    let brand = veh.brand;
                    let mRate = veh.monthlyRate;
                    let dRate = veh.dailyRate;
                    let fMileage = veh.freeMileage;
                    let extraKM = veh.priceForExtraKM;
                    let transmission = veh.transmissionType;

                    let front = veh.frontView;
                    let back = veh.backView;
                    let side = veh.sideView;
                    let interior = veh.interiorView;
                    console.log(front+" / "+side);

                    let row = `<tr><td>${dRate}</td><td>${mRate}</td><td>${extraKM}</td><td>${fMileage}</td><td>${transmission}</td></tr>`;
                    $("#tblCusBookingLoadData").append(row);
                    $("#showBrand").text(brand);
                    if (type==="General"){
                        $("#bLossDamagePrice").val(10000);
                    }else if (type==="Premium"){
                        $("#bLossDamagePrice").val(15000);
                    }else if(type==="Luxury"){
                        $("#bLossDamagePrice").val(20000);
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
            setView($("#bookingForm"));
        }else {
            alert("login first");
        }
    });
}

function generateNewBookingId() {
    $.ajax({
        url: BASE_URL + 'booking/id',
        dataType: "json",
        success: function (response) {
            let bookingId = response.data;
            $("#bookingId").val(bookingId);
        },
        error: function (error) {
            console.log(error);
        }
    });
}
