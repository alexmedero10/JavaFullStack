const buttonInstructions = document.getElementById('btnInstructions');
const paragraphInstructions = document.getElementById('prgInstructions');
const formData = document.getElementById('frmData');

function pressButtonInstructions() {
    let paragraphIsVisible = paragraphInstructions.classList.contains('d-none');

    if (!paragraphIsVisible) {
        paragraphInstructions.classList.add('d-none');
        buttonInstructions.textContent = "Show";
    } else {
        paragraphInstructions.classList.remove('d-none');
        buttonInstructions.textContent = "Hide";
    }

}

function validateForm() {
    let email = document.getElementById('inputEmail').value;
    let password = document.getElementById('inputPassword').value;
    let errorMessage = null;

    if (!email) {
        errorMessage = document.getElementById('emailError');
        errorMessage.textContent = "Email is required";
        return;
    } else {
        errorMessage = document.getElementById('emailError');
        errorMessage.textContent = "";
    }

    if (!password) {
        errorMessage = document.getElementById('passwordError');
        errorMessage.textContent = "Password is required";
        return;
    } else {
        errorMessage = document.getElementById('passwordError');
        errorMessage.textContent = "";
    }
}


buttonInstructions.addEventListener('click', function(event) {
    event.preventDefault();
    pressButtonInstructions();
});

formData.addEventListener('submit', function(event) {
    event.preventDefault();
    validateForm();
});
