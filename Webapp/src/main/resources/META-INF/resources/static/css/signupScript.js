const form = document.querySelector("form");

form.addEventListener('submit', (event) => {
    event.preventDefault();
    const nameInput = document.getElementById("username");
    const name = nameInput.value;
    const passwordInput = document.getElementById("password");
    const password = passwordInput.value;
    const firstNameInput = document.getElementById("firstName");
    const firstName = firstNameInput.value;
    const lastNameInput = document.getElementById("lastName");
    const lastName = lastNameInput.value;
    const confirmationInput = document.getElementById("matchingPassword");
    const confirmation = confirmationInput.value;

    const nameError = document.getElementById("nameError");
    const passwordError = document.getElementById("passwordError");
    const firstNameError = document.getElementById("firstNameError");
    const lastNameError = document.getElementById("lastNameError");
    const confirmationError = document.getElementById("confirmationError");

    nameError.style.display = 'none';
    passwordError.style.display = 'none';
    firstNameError.style.display = 'none';
    lastNameError.style.display = 'none';
    confirmationError.style.display = 'none';

    if(firstName.length > 30) {
        firstNameError.innerHTML = 'First name must contain no more than 30 characters.'
        firstNameError.style.display = 'block';
        return;
    }

    if(firstName.trim().length < 1) {
        firstNameError.innerHTML = 'First name must contain at least 1 non-space character.'
        firstNameError.style.display = 'block';
        return;
    }

    if(firstName.split('').some((value) => !isNaN(value) && value != 0)){
        firstNameError.innerHTML = 'First name cannot contain numbers.'
        firstNameError.style.display = 'block';
        return;
    }

    if(lastName.length > 30) {
        lastNameError.innerHTML = 'Last name must contain no more than 30 characters.'
        lastNameError.style.display = 'block';
        return;
    }

    if(lastName.trim().length < 1) {
        lastNameError.innerHTML = 'Last name must contain at least 1 non-space character.'
        lastNameError.style.display = 'block';
        return;
    }

    if(lastName.split('').some((value) => !isNaN(value) && value != 0)){
        lastNameError.innerHTML = 'Last name cannot contain numbers.'
        lastNameError.style.display = 'block';
        return;
    }

    if(password.length < 5) {
        passwordError.innerHTML = 'Password must contain at least 5 characters.'
        passwordError.style.display = 'block';
        return;
    }

    if(password.length > 50) {
        passwordError.innerHTML = 'Password must contain no more than 50 characters.'
        passwordError.style.display = 'block';
        return;
    }

    if(name.length > 30) {
        nameError.innerHTML = 'Username must contain no more than 30 characters.'
        nameError.style.display = 'block';
        return;
    }

    if(name.trim().length < 1) {
        nameError.innerHTML = 'Username must contain at least 1 non-space character.'
        nameError.style.display = 'block';
        return;
    }

    if(name.split('').some((value) => value==0 && value!=='0')){
        nameError.innerHTML = 'Username cannot contain whitespaces.'
        nameError.style.display = 'block';
        return;
    }

    if(confirmation !== password) {
        confirmationError.style.display = 'block';
        return;
    }

    form.submit();
})