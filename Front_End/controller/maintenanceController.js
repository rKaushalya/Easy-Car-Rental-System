
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

}