function validate(form) {

     var elems = form.elements;
     resetError();
    // if (!elems.firstName.value) {
    //     showError(elems.firstName.parentNode, "Enter first name!");
    //     return false;
    // }
    //
    // resetError(elems.lastName.parentNode);
    // if (!elems.lastName.value) {
    //     showError(elems.lastName.parentNode, "Enter last name!");
    //     return false;
    // }
    //
    // resetError(elems.email.parentNode);
    // if (!elems.email.value) {
    //     showError(elems.email.parentNode, "Enter e-mail!");
    //     return false;
    // }
    //
    // resetError(elems.username.parentNode);
    // if (!elems.username.value) {
    //     showError(elems.username.parentNode, "Enter username");
    //     return false;
    // }
    //
    // resetError(elems.password.parentNode);
    // if (!elems.password.value) {
    //     showError(elems.password.parentNode, "Enter password!");
    //     return false;
    // } else
        if (elems.password.value !== elems.password2.value) {
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
