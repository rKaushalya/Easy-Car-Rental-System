initiateUI();

function initiateUI() {
    clearAll();
    $("#driverLogin").css("display", "block");
}

function clearAll() {
    $("#driverLogin,#driverForm").css('display', 'none');
}

function setView(viewOb) {
    clearAll();
    viewOb.css("display", "block");
    console.log(viewOb.get(0).id);
}

//bind events
/*$("#lnkDriverLogin").click(function () {
    setView($("#driverForm"));
});*/
