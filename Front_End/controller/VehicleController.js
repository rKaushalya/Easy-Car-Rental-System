$("#btnVehicleAdd").click(function () {
    addVehicle();
});

$("#btnVehicleDelete").click(function () {
    deleteVehicle();
});

$(function (){
    $("#vFront").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgFront").attr("src",x);
    });
});

$(function (){
    $("#vBack").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgBack").attr("src",x);
    });
});

$(function (){
    $("#vSide").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgSide").attr("src",x);
    });
});

$(function (){
    $("#vInterior").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgInterior").attr("src",x);
    });
});


function addVehicle() {
    var VehicleData = {
        registerNo: $("#vRegisterNo").val(),
        brand: $("#vBrand").val(),
        type: $("#type").val(),
        fuelType: $("#FuelType").val(),
        transmissionType: $("#vTransmissionType").val(),
        dailyRate: $("#vDailyRate").val(),
        monthlyRate: $("#vMonthlyRate").val(),
        noOfPassenger: $("#vNoOfPassenger").val(),
        freeMileage: $("#vFreeMileage").val(),
        freePrice: $("#vFreePrice").val(),
        priceForExtraKM: $("#vExtraKMPrice").val(),
        color: $("#vColor").val(),
        state: $("#vState").val()
    }

    var data = new FormData();
    let frontImage = $("#vFront")[0].files[0];
    let backImage = $("#vBack")[0].files[0];
    let sideImage = $("#vSide")[0].files[0];
    let interiorImage = $("#vInterior")[0].files[0];

    data.append("front", frontImage);
    data.append("back", backImage);
    data.append("side", sideImage);
    data.append("interior", interiorImage);
    data.append('data', JSON.stringify(VehicleData));

    $.ajax({
        url: BASE_URL + "vehicle",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert(res.message);
            // getAllVehicles();
        },
        error: function (error) {
            console.log(error)
        }
    });
}

function deleteVehicle() {
    let registerNo = $("#vRegisterNo").val();
    $.ajax({
        url: BASE_URL + "vehicle?id="+registerNo,
        method: "delete",
        success: function (response) {
            alert(response.message);
        },
        error: function (error) {
            console.log(error);
        }
    });
}