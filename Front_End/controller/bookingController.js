
getAllBookingForAdmin();
getBookingDetailsForCustomer();

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
            getBookingDetailsForCustomer();
            alert(res.message);
        },
        error: function (error) {
            console.log(error.responseJSON.message);
        }
    });
}

function getAllBookingForAdmin() {
    $("#adminBookingView").empty();

    $.ajax({
        url: BASE_URL + 'booking',
        dataType: "json",
        success: function (response) {
            let booking = response.data;
            for (let i in booking) {
                let b = booking[i];

                let id = b.customerId;
                let email = b.customerEmail;
                let bookDate = b.carBookDate;
                let pickupDate = b.pickupDate;
                let driverState = b.driverState;
                let state = b.state;

                var dateObj1 = new Date(bookDate);
                var formattedBookDate = dateObj1.toISOString().split('T')[0];

                var dateObj2 = new Date(pickupDate);
                var formattedPickupDate = dateObj2.toISOString().split('T')[0];

                let btn = "<ul class=\"navbar-nav\">\n" +
                    "                            <li class=\"nav-item dropdown\">\n" +
                    "                                <button class=\"btn btn-outline-warning dropdown-toggle\" data-bs-toggle=\"dropdown\"\n" +
                    "                                        aria-expanded=\"false\">\n" +
                    "                                    Status\n" +
                    "                                </button>\n" +
                    "                                <ul class=\"dropdown-menu dropdown-menu-start\">\n" +
                    "                                    <li><a class=\"dropdown-item\" href=\"#\">Accept</a></li>\n" +
                    "                                    <li><a class=\"dropdown-item\" href=\"#\">Decline</a></li>\n" +
                    "                                    <li><a class=\"dropdown-item\" href=\"#\">Pending</a></li>\n" +
                    "                                    <li><a class=\"dropdown-item\" href=\"#\">Complete</a></li>\n" +
                    "                                </ul>\n" +
                    "                            </li>\n" +
                    "                        </ul>";

                let row = `<tr><td>${id}</td><td>${email}</td><td>${formattedBookDate}</td><td>${formattedPickupDate}</td><td>${driverState}</td><td>${state}</td><td>${btn}</td></tr>`;
                $("#adminBookingView").append(row);
            }
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}

function getBookingDetailsForCustomer() {
    $("#tblViewCusBooking").empty();
    let cusName = $("#lnkSingIn>a").text();

    $.ajax({
        url: BASE_URL + 'booking/name?name='+cusName,
        dataType: "json",
        success: function (response) {
            let booking = response.data;
            for (let i in booking) {
                let b = booking[i];

                let id = b.customerId;
                let name = b.customerName;
                let contact = b.customerContact;
                let address = b.customerAddress;
                let regNo = b.registerNo;
                let state = b.state;

                let row = `<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${contact}</td><td>${regNo}</td><td>${state}</td></tr>`;
                $("#tblViewCusBooking").append(row);
            }
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
}