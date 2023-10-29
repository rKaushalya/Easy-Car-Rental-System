initiateUI();

function initiateUI() {
    clearAll();
    $("#indexForm").css("display", "block");
    setTheLastView();
}

function saveLastView(clickedID) {
    switch (clickedID) {
        case "indexForm":
            localStorage.setItem("view", "HOME");
            break;
        case "customerBookingViewForm":
            localStorage.setItem("view", "BOOKING");
            break;
        case "loginForm":
            localStorage.setItem("view", "LOGIN");
            break;
        case "customerForm":
            localStorage.setItem("view", "REGISTER");
            break;
    }
}

function setTheLastView() {
    let view = localStorage.getItem("view");
    switch (view) {
        case "HOME":
            setView($("#indexForm"));
            break;
        case "BOOKING":
            setView($("#customerBookingViewForm"));
            break;
        case "LOGIN":
            setView($("#loginForm"));
            break;
        case "REGISTER":
            setView($("#customerForm"));
            break;
        default:
            setView($("#indexForm"));
    }
}

function clearAll() {
    $("#indexForm,#customerForm,#bookingForm,#loginForm,#customerBookingViewForm,#forgetPasswordForm").css('display', 'none');
}

function setView(viewOb) {
    clearAll();
    viewOb.css("display", "block");
    saveLastView(viewOb.get(0).id);
    console.log(viewOb.get(0).id);
}

//bind events
$("#lnkHome").click(function () {
    setView($("#indexForm"));
});

$("#lnkBooking").click(function () {
    setView($("#customerBookingViewForm"));
});

$("#lnkSingIn").click(function () {
    setView($("#loginForm"));
});

$("#lnkRegister").click(function () {
    setView($("#customerForm"));
});
$("#lnkForget").click(function () {
    setView($("#forgetPasswordForm"));
});