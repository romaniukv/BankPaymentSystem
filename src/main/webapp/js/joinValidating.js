function validate(form) {

    var elems = form.elements;
    resetError();
    var emailPattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!emailPattern.test(String(elems.email.value).toLowerCase())) {
        showError("Enter correct e-mail!");
        return false;
    } else if (elems.password.value !== elems.password2.value) {
        showError("Passwords do not match!");
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
