
const CUS_NAME_REGEX = /^[A-Za-z ]{5,}$/;
const CUS_EMAIL_REGEX = /^[a-z0-9]{2,}[@][a-z]{3,}([.][a-z]{2,})?$/;
const CUS_ADDRESS_REGEX = /^[A-Za-z0-9 ]{5,}$/;
const CUS_CONTACT_REGEX = /^([+][0-9]{2})?[0-9]{9,}$/;

let c_vArray = new Array();
c_vArray.push({field: $("#txtCusName"), regEx: CUS_NAME_REGEX});
c_vArray.push({field: $("#txtCusAddress"), regEx: CUS_ADDRESS_REGEX});
c_vArray.push({field: $("#txtCusEmail"), regEx: CUS_EMAIL_REGEX});
c_vArray.push({field: $("#txtCusContact"), regEx: CUS_CONTACT_REGEX});

function clearCustomerInputFields() {
    $("#txtCusName,#txtCusAddress,#txtCusEmail,#txtCusContact,#txtCusNIC,#txtCusPassword").val("");
    $("#txtCusName,#txtCusAddress,#txtCusEmail,#txtCusContact,#txtCusNIC,#txtCusPassword").css("border", "1px solid #ced4da");
    $("#txtCusName").focus();
}

$("#txtCusName,#txtCusEmail,#txtCusAddress,#txtCusContact").on("keydown keyup", function (e) {
    //get the index number of data input fields indexNo
    let indexNo = c_vArray.indexOf(c_vArray.find((c) => c.field.attr("id") == e.target.id));

    //Disable tab key
    if (e.key == "Tab") {
        e.preventDefault();
    }

    //check validations
    checkValidations(c_vArray[indexNo]);

    //If the enter key pressed cheque and focus
    if (e.key == "Enter") {

        if (e.target.id != c_vArray[c_vArray.length - 1].field.attr("id")) {
            //check validation is ok
            if (checkValidations(c_vArray[indexNo])) {
                c_vArray[indexNo + 1].field.focus();
            }
        }
    }
});

function checkValidations(object) {
    if (object.regEx.test(object.field.val())) {
        setBorder(true, object)
        return true;
    }
    setBorder(false, object)
    return false;
}

function setBorder(bol, ob) {
    if (!bol) {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid red");
        } else {
            ob.field.css("border", "1px solid #ced4da");
        }
    } else {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid green");
        } else {
            ob.field.css("border", "1px solid #ced4da");
        }
    }

}