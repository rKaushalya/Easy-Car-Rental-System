
getAllMaintenance();

function getAllMaintenance() {
    $("#tblMaintenance").empty();

    $.ajax({
        url: BASE_URL + 'maintenance',
        dataType: "json",
        success: function (response) {
            let maintenance = response.data;
            for (let i in maintenance) {
                let m = maintenance[i];
                let id = m.maintenanceId;
                let km = m.runKm;
                let regNo = m.regNo;

                let btn = "<button class=\"btn btn-outline-warning\">Mark as Maintenance</button>";
                let row = `<tr><td>${id}</td><td>${regNo}</td><td>${km}</td><td>${btn}</td></tr>`;
                $("#tblMaintenance").append(row);
            }
            mBindTrEvents();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

function mBindTrEvents() {
    $("#tblMaintenance tr button").click(function () {
        let id = $(this).parent().parent().children().eq(0).text();
        let regNo = $(this).parent().parent().children().eq(1).text();
        updateMaintenance(id,regNo);
    });
}

function updateMaintenance(id,regNo) {
    $.ajax({
        url: BASE_URL + "maintenance?maintenanceId="+id+"&regNo="+regNo,
        method: 'post',
        success: function (res) {
            console.log(res.message);
            alert(res.message);
            getAllMaintenance();
        },
        error: function (error) {
            console.log(error)
        }
    });
}