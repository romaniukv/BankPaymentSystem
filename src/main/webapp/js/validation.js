function validateJoinFrom(form) {

    var elements = form.elements;
    resetError();
    var emailPattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!emailPattern.test(String(elements.email.value).toLowerCase())) {
        showError("Enter correct e-mail!");
        return false;
    } else if (elements.password.value !== elements.password2.value) {
        showError("Passwords do not match!");
        return false;
    }
    return true;
}

function validateTransferMoneyFrom(form, creditBalance) {

    var elements = form.elements;
    resetError();

    if (elements.amount.value >= creditBalance) {
        showError("Not enough money on credit account!");
        return false;
    }

    return true;
}

function showError(errorMessage) {
    document.getElementById("error").innerHTML=errorMessage;
}

function resetError() {
    document.getElementById("error").innerHTML="";
}
