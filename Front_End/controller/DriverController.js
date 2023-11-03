getNewDriverId();
getAllDrivers();

$("#btnDriverAdd").click(function () {
    saveDriver();
});

$("#btnDriverSearch").click(function () {
    findDriverByName();
});

$("#btnDriverDelete").click(function () {
    deleteDriver();
});

$("#btnUpdateDriver").click(function () {
    updateDriver();
});
$("#lnkDriverLogin").click(function () {
    checkDriverLogin();
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
            getAllDrivers();
        },
        error: function (error) {
            console.log(error)
        }
    });
}

function getNewDriverId() {
    $.ajax({
        url: 'http://localhost:8080/Back_End_war/driver/id',
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
        url: 'http://localhost:8080/Back_End_war/driver',
        dataType: "json",
        success: function (response) {
            let driver = response.data;
            for (let i in driver) {
                let dri = driver[i];
                let id = dri.driverId;
                let name = dri.name;
                let city = dri.city;

                let dob = dri.dob;
                var dateObj = new Date(dob);
                var formattedDate = dateObj.toISOString().split('T')[0];

                let row = `<tr><td>${id}</td><td>${name}</td><td>${city}</td><td>${formattedDate}</td></tr>`;
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

function deleteDriver(){
    let driverId = $("#driverId").html();

    $.ajax({
        url: BASE_URL + "driver?id="+driverId,
        method: 'delete',
        success: function (res) {
            alert(res.message);
            getAllDrivers();
            getNewDriverId();
        },
        error: function (error) {
            console.log(error)
        }
    });
}

function updateDriver() {
    var driverUpdateData = {
        driverId: $("#driverId").html(),
        name: $("#dName").val(),
        address: $("#dAddress").val(),
        dob: $("#dDOB").val(),
        city: $("#dCity").val(),
        licenseNo: $("#dLicenseNo").val()
    }

    var data = new FormData();
    data.append('driver', JSON.stringify(driverUpdateData));
    let image = $("#dLicensePhoto")[0].files[0];
    data.append("file", image);

    $.ajax({
        url: BASE_URL + "driver",
        method: 'put',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert(res.message);
            getNewDriverId();
            getAllDrivers();
        },
        error: function (error) {
            console.log(error)
        }
    });
}

function checkDriverLogin() {
    let dId = $("#logDriverId").val();

    $.ajax({
        url: 'http://localhost:8080/Back_End_war/driver/check?driverId='+dId,
        dataType: "json",
        success: function (resp) {
            setView($("#driverForm"));
        },
        error: function (error) {
            console.log(error);
            alert("Wrong id.!");
        }
    });
}