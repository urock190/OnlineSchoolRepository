const form = document.querySelector("form");

form.addEventListener('submit', (event) => {
    event.preventDefault();
    const nameInput = document.getElementById("username");
    const name = nameInput.value;
    const passwordInput = document.getElementById("password");
    const password = passwordInput.value;

    const nameError = document.getElementById("nameError");
    const passwordError = document.getElementById("passwordError");

    nameError.style.display = 'none';
    passwordError.style.display = 'none';

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

    if(name.split('').some((value) => !isNaN(value))){
        nameError.innerHTML = 'Username cannot contain numbers or spaces.'
        nameError.style.display = 'block';
        return;
    }

    form.submit();
})