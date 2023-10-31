getAllVehicles();

$("#btnVehicleAdd").click(function () {
    addVehicle();
});

$("#btnVehicleDelete").click(function () {
    deleteVehicle();
});

$("#btnVehicleSearch").click(function () {
    searchByRegisterNo();
});

$("#btnVehicleUpdate").click(function () {
    updateVehicle();
});

$(function () {
    $("#vFront").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgFront").attr("src", x);
    });
});

$(function () {
    $("#vBack").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgBack").attr("src", x);
    });
});

$(function () {
    $("#vSide").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgSide").attr("src", x);
    });
});

$(function () {
    $("#vInterior").change(function (event) {
        let x = URL.createObjectURL(event.target.files[0]);
        $("#imgInterior").attr("src", x);
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
            getAllVehicles();
        },
        error: function (error) {
            console.log(error)
        }
    });
}

function deleteVehicle() {
    let registerNo = $("#vRegisterNo").val();
    $.ajax({
        url: BASE_URL + "vehicle?id=" + registerNo,
        method: "delete",
        success: function (response) {
            getAllVehicles();
            alert(response.message);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function getAllVehicles() {
    $("#tblVehicle").empty();

    $.ajax({
        url: BASE_URL + 'vehicle',
        dataType: "json",
        success: function (response) {
            let vehicle = response.data;
            for (let i in vehicle) {
                let veh = vehicle[i];
                let registerNo = veh.registerNo;
                let brand = veh.brand;
                let color = veh.color;
                let noOfPassenger = veh.noOfPassenger;
                let type = veh.type;
                let state = veh.state;
                let frontView = veh.filePath;
                console.log(frontView);

                let row = `<tr><td>${registerNo}</td><td>${brand}</td><td>${color}</td><td>${noOfPassenger}</td><td>${type}</td><td>${state}</td>
                            <td><img src="${"../img/uploads/" + frontView}" width="100px" height="80px"></td></tr>`;
                $("#tblVehicle").append(row);
            }
            vBindEvent();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

function vBindEvent() {
    $("#tblVehicle>tr").click(function () {
        let id = $(this).children().eq(0).text();
        $("#vRegisterNo").val(id);
    });
}

function searchByRegisterNo() {
    let registerNo = $("#vRegisterNo").val();

    $.ajax({
        url: BASE_URL + 'vehicle/id?id=' + registerNo,
        dataType: "json",
        success: function (response) {
            let veh = response.data;

                $("#vRegisterNo").val(veh.registerNo);
                $("#vBrand").val(veh.brand);
                $("#type").val(veh.type);
                $("#FuelType").val(veh.fuelType);
                $("#vTransmissionType").val(veh.transmissionType);
                $("#vDailyRate").val(veh.dailyRate);
                $("#vMonthlyRate").val(veh.monthlyRate);
                $("#vNoOfPassenger").val(veh.noOfPassenger);
                $("#vFreeMileage").val(veh.freeMileage);
                $("#vFreePrice").val(veh.freePrice);
                $("#vExtraKMPrice").val(veh.priceForExtraKM);
                $("#vColor").val(veh.color);
                $("#vState").val(veh.state);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function updateVehicle() {
    var VehicleUpdatedData = {
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
    data.append('data', JSON.stringify(VehicleUpdatedData));

    $.ajax({
        url: BASE_URL + "vehicle",
        method: 'put',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert(res.message);
            getAllVehicles();
        },
        error: function (error) {
            console.log(error)
        }
    });
}