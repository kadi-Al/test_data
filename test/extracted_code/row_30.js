html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details Form</title>
</head>
<body>
    <form id="userForm">
        Full Name: <input type="text" id="fullName" required><br><br>
        Gender: 
        <select id="gender" required>
            <option value="">Select Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select><br><br>
        Birth Date: <input type="date" id="birthDate" required><br><br>
        Mobile Number: <input type="tel" id="mobileNumber" required><br><br>
        Address: <input type="text" id="address" required><br><br>
        Email: <input type="email" id="email" required><br><br>
        Social Security Number: <input type="text" id="socialSecurityNumber" required><br><br>
        <button type="submit">Submit</button>
    </form>

    <script src="validateForm.js"></script>
</body>
</html>
script
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