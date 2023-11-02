initiateUI();

function initiateUI() {
    clearAll();
    $("#dashBoardForm").css("display", "block");
    setTheLastView();
}

function saveLastView(clickedID) {
    switch (clickedID) {
        case "dashBoardForm":
            localStorage.setItem("view", "DASHBOARD");
            break;
        case "reportForm":
            localStorage.setItem("view", "REPORT");
            break;
        case "vehicleForm":
            localStorage.setItem("view", "VEHICLE");
            break;
        case "viewBookingForm":
            localStorage.setItem("view", "BOOKING");
            break;
        case "viewCustomerForm":
            localStorage.setItem("view", "CUSTOMER");
            break;
        case "viewDriverForm":
            localStorage.setItem("view", "DRIVER");
            break;
    }
}

function setTheLastView() {
    let view = localStorage.getItem("view");
    switch (view) {
        case "DASHBOARD":
            setView($("#dashBoardForm"));
            break;
        case "REPORT":
            setView($("#reportForm"));
            break;
        case "VEHICLE":
            setView($("#vehicleForm"));
            break;
        case "BOOKING":
            setView($("#viewBookingForm"));
            break;
        case "CUSTOMER":
            setView($("#viewCustomerForm"));
            break;
        case "DRIVER":
            setView($("#viewDriverForm"));
            break;
        default:
            setView($("#dashBoardForm"));
    }
}

function clearAll() {
    $("#dashBoardForm,#reportForm,#vehicleForm,#viewBookingForm,#viewCustomerForm,#viewDriverForm,#paymentForm").css('display', 'none');
}

function setView(viewOb) {
    clearAll();
    viewOb.css("display", "block");
    saveLastView(viewOb.get(0).id);
    console.log(viewOb.get(0).id);
}

//bind events
$("#lnkDashBoard").click(function () {
    setView($("#dashBoardForm"));
});

$("#lnkCustomer").click(function () {
    setView($("#viewCustomerForm"));
});

$("#lnkVehicle").click(function () {
    setView($("#vehicleForm"));
});

$("#lnkBooking").click(function () {
    setView($("#viewBookingForm"));
});

$("#lnkDrivers").click(function () {
    setView($("#viewDriverForm"));
});

$("#lnkIncome").click(function () {
    setView($("#reportForm"));
});