function validate(form) {

    var elems = form.elements;
    var success = true;
    resetError(elems.firstName.parentNode);
    if (!elems.firstName.value) {
        success = false;
        showError(elems.firstName.parentNode, ' Укажите от кого.');
    }

    resetError(elems.lastName.parentNode);
    if (!elems.lastName.value) {
        success = false;
        showError(elems.lastName.parentNode, ' Укажите от кого.');
    }

    resetError(elems.email.parentNode);
    if (!elems.email.value) {
        success = false;
        showError(elems.email.parentNode, ' Укажите от кого.');
    }

    resetError(elems.username.parentNode);
    if (!elems.username.value) {
        showError(elems.username.parentNode, ' Укажите от кого.');
    }

    resetError(elems.password.parentNode);
    if (!elems.password.value) {
        success = false;
        showError(elems.password.parentNode, ' Укажите пароль.');
    } else if (elems.password.value !== elems.password2.value) {
        success = false;
        showError(elems.password.parentNode, ' Пароли не совпадают.');
    }
    return success;
}

function showError(container, errorMessage) {
    container.className = 'error';
    var msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className === "error-message") {
        container.removeChild(container.lastChild);
    }
}
