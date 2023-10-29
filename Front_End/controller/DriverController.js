$("#btnDriverAdd").click(function () {
    saveDriver();
});

function saveDriver() {
    var driverData = {
        driverId: $("#driverId").val(),
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
        },
        error: function (error) {
            console.log(error)
        }
    });
}