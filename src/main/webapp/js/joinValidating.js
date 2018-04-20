function validate(form) {

    var elems = form.elements;
    resetError(elems.firstName.parentNode);
    if (!elems.firstName.value) {
        showError(elems.firstName.parentNode, "Enter first name!");
        return false;
    }

    resetError(elems.lastName.parentNode);
    if (!elems.lastName.value) {
        showError(elems.lastName.parentNode, "Enter last name!");
        return false;
    }

    resetError(elems.email.parentNode);
    if (!elems.email.value) {
        showError(elems.email.parentNode, "Enter e-mail!");
        return false;
    }

    resetError(elems.username.parentNode);
    if (!elems.username.value) {
        showError(elems.username.parentNode, "Enter username");
        return false;
    }

    resetError(elems.password.parentNode);
    if (!elems.password.value) {
        showError(elems.password.parentNode, ' Укажите пароль.');
        return false;
    } else if (elems.password.value !== elems.password2.value) {
        showError(elems.password.parentNode, ' Пароли не совпадают.');
        return false;
    }
    return true;
}

function showError(container, errorMessage) {
    // container.className = 'errorMsg';
    // var msgElem = document.createElement('span');
    // msgElem.className = "error-message";
    // msgElem.innerHTML = errorMessage;
    // container.appendChild(msgElem);
    document.getElementById("error").innerHTML=errorMessage;
}

function resetError(container) {
    document.getElementById("error").innerHTML="";
    // container.className = '';
    // if (container.lastChild.className === "error-message") {
    //     container.removeChild(container.lastChild);
    // }
}
