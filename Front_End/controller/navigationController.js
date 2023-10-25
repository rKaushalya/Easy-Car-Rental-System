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
        case "bookingForm":
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
            setView($("#bookingForm"));
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
    $("#indexForm,#customerForm,#bookingForm,#loginForm").css('display', 'none');
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
    setView($("#bookingForm"));
});

$("#lnkSingIn").click(function () {
    setView($("#loginForm"));
});

$("#lnkRegister").click(function () {
    setView($("#customerForm"));
});