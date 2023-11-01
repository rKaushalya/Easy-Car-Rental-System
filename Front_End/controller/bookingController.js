
$("#btnSentRequest").click(function () {
    bookingACar();
});

function bookingACar() {
    let bookingData = {
        bookId : $("#bookingId").val(),
        customerName : $("#showCusId").text(),
        registerNo : sampleRegNo,
        location : $("#bLocation").val(),
        lossDamagePrice : $("#bLossDamagePrice").val(),
        onlineOrPhysical : $("#inputState").val(),
        carPrice : $("#inputPrice").val(),
        carBookDate : $("#bDate").val(),
        pickupDate : $("#bPickupDate").val(),
        driverState : $("#inputStat").val()
    }

    var data = new FormData();
    data.append('book', JSON.stringify(bookingData));
    let image = $("#inputZip")[0].files[0];
    data.append("slip", image);

    console.log(image);

    $.ajax({
        url: BASE_URL + "booking",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert(res.message);
        },
        error: function (error) {
            console.log(error.responseJSON.message);
        }
    });
}