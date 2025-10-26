document.getElementById('userForm').addEventListener('submit', function(event) {
    let isValid = true;
    const fullName = document.getElementById('fullName').value;
    const gender = document.getElementById('gender').value;
    const birthDate = document.getElementById('birthDate').value;
    const mobileNumber = document.getElementById('mobileNumber').value;
    const address = document.getElementById('address').value;
    const email = document.getElementById('email').value;
    const socialSecurityNumber = document.getElementById('socialSecurityNumber').value;

    // Clear previous error messages
    clearErrors();

    if (!fullName.match(/^[A-Za-z ]+$/)) {
        displayError("fullName", "Full name should only contain letters and spaces.");
        isValid = false;
    }
    if (!gender) {
        displayError("gender", "Please select a gender.");
        isValid = false;
    }
    if (!isValidDate(birthDate)) {
        displayError("birthDate", "Please enter a valid birth date.");
        isValid = false;
    }
    if (!mobileNumber.match(/^\d{10}$/)) {
        displayError("mobileNumber", "Mobile number should be 10 digits long.");
        isValid = false;
    }
    if (!address) {
        displayError("address", "Please enter a valid address.");
        isValid = false;
    }
    if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
        displayError("email", "Please enter a valid email address.");
        isValid = false;
    }
    if (!socialSecurityNumber.match(/^\d{3}-\d{2}-\d{4}$/)) {
        displayError("socialSecurityNumber", "Social Security Number should be in the format: ###-##-####");
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault(); // Prevent form submission if there are errors
    }
});

function displayError(field, message) {
    const errorDiv = document.getElementById(`error_${field}`);
    if (!errorDiv) {
        const div = document.createElement('div');
        div.id = `error_${field}`;
        div.style.color = 'red';
        document.body.appendChild(div);
    }
    document.getElementById(`error_${field}`).innerText = message;
}

function clearErrors() {
    const errorDivs = document.querySelectorAll('.error');
    errorDivs.forEach(div => div.innerText = '');
}

function isValidDate(date) {
    return !isNaN(Date.parse(date));
}