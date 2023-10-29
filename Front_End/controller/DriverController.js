getNewDriverId();
getAllDrivers();

$("#btnDriverAdd").click(function () {
    saveDriver();
});

$("#btnDriverSearch").click(function () {
    findDriverByName();
});

function saveDriver() {
    var driverData = {
        driverId: $("#driverId").html(),
        name: $("#dName").val(),
        address: $("#dAddress").val(),
        dob: $("#dDOB").val(),
        city: $("#dCity").val(),
        licenseNo: $("#dLicenseNo").val()
    }

    var data = new FormData();
    data.append('driver', JSON.stringify(driverData));
    let image = $("#dLicensePhoto")[0].files[0];
    data.append("file", image);

    $.ajax({
        url: BASE_URL + "driver",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert(res.message);
            getNewDriverId();
        },
        error: function (error) {
            console.log(error)
        }
    });
}

function getNewDriverId() {
    $.ajax({
        url: BASE_URL + 'driver/id',
        dataType: "json",
        success: function (response) {
            $("#driverId").html(response.data);
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

function getAllDrivers() {
    $("#tblDriverView").empty();

    $.ajax({
        url: BASE_URL + 'driver',
        dataType: "json",
        success: function (response) {
            let driver = response.data;
            for (let i in driver) {
                let dri = driver[i];
                let id = dri.driverId;
                let name = dri.name;
                let city = dri.city;
                let dob = dri.dob;
                let row = `<tr><td>${id}</td><td>${name}</td><td>${city}</td><td>${dob}</td></tr>`;
                $("#tblDriverView").append(row);
            }
            bindEvent();
        },
        error: function (error) {
            // alert(error.responseJSON.message);
            console.log(error);
        }
    });
}

function findDriverByName() {
    let name = $("#dName").val();

    $.ajax({
        url: BASE_URL + 'driver/name?name='+name,
        dataType: "json",
        success: function (response) {
            let driver = response.data;
                $("#dName").val(driver.name);
                $("#driverId").html(driver.driverId);
                $("#dAddress").val(driver.address);
                $("#dDOB").val(driver.dob);
                $("#dCity").val(driver.city);
                $("#dLicenseNo").val(driver.licenseNo);
        },
        error: function (error) {
            // alert(error.responseJSON.message);
            console.log(error);
        }
    });
}

function bindEvent() {
    $("#tblDriverView>tr").click(function () {
        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let city = $(this).children().eq(2).text();
        let dob = $(this).children().eq(3).text();

        $("#dName").val(name);
        $("#driverId").html(id);
        $("#dDOB").val(dob);
        $("#dCity").val(city);
    });
}