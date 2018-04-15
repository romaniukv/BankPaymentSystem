function validate(form) {
    var elems = form.elements;
    resetError(elems.firstName.parentNode);
    if (elems.firstName.value == "") {
        showError(elems.firstName.parentNode, ' Укажите от кого.');
    }

    resetError(elems.lastName.parentNode);
    if (!elems.lastName.value) {
        showError(elems.lastName.parentNode, ' Укажите от кого.');
    }

    resetError(elems.email.parentNode);
    if (!elems.email.value) {
        showError(elems.email.parentNode, ' Укажите от кого.');
    }

    resetError(elems.username.parentNode);
    if (!elems.username.value) {
        showError(elems.username.parentNode, ' Укажите от кого.');
    }

    resetError(elems.password.parentNode);
    if (!elems.password.value) {
        showError(elems.password.parentNode, ' Укажите пароль.');
    } else if (elems.password.value !== elems.password2.value) {
        showError(elems.password.parentNode, ' Пароли не совпадают.');
    }
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
