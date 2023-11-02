
getPaymentId();

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

}