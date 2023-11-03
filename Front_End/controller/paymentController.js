getPaymentId();

$("#btnPay").click(function () {
    makeAPayment();
});

function getPaymentId() {
    $.ajax({
        url: BASE_URL + 'payment',
        dataType: "json",
        success: function (response) {
            let paymentId = response.data;
            $("#txtPId").val(paymentId);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function makeAPayment() {
    let paymentData = {
        paymentId: $("#txtPId").val(),
        bookingId: bookingId,
        damagePrice: $("#txtDamagePrice").val(),
        extraKMPrice: $("#txtExtraKM").val(),
        forTheCar: $("#txtForCar").val(),
        lateFee: $("#txtLateFee").val(),
        driverStatus: driverIdForPayment
    }

    $.ajax({
        url: BASE_URL + 'payment',
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(paymentData),
        success: function (resp) {
            getAllBookingForAdmin();
            getBookingDetailsForCustomer();
            alert(resp.message);
            setView($("#viewBookingForm"));
        },
        error: function (error) {
            console.log(error);
        }
    });
}

$(function () {
    $("#txtDamagePrice").change(function () {
        let tot = Math.round($("#txtForCar").val());
        let km = Math.round($("#txtExtraKM").val());
        let damage = Math.round($("#txtDamagePrice").val());
        let late = Math.round($("#txtLateFee").val());
        $("#txtTotal").val(tot+km+damage+late);
    });
})

$(function () {
    $("#txtExtraKM").change(function () {
        let tot = Math.round($("#txtForCar").val());
        let km = Math.round($("#txtExtraKM").val());
        let damage = Math.round($("#txtDamagePrice").val());
        let late = Math.round($("#txtLateFee").val());
        $("#txtTotal").val(tot+km+damage+late);
    });
})

$(function () {
    $("#txtLateFee").change(function () {
        let tot = Math.round($("#txtForCar").val());
        let km = Math.round($("#txtExtraKM").val());
        let damage = Math.round($("#txtDamagePrice").val());
        let late = Math.round($("#txtLateFee").val());
        $("#txtTotal").val(tot+km+damage+late);
    });
})