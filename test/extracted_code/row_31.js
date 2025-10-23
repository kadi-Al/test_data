script
function isValidPhoneNumber(phoneNumber) {
    const regex = /^\(?(\d{3})\)?[-.\s]?(\d{3})[-.\s]?(\d{4})$/;
    return regex.test(phoneNumber);
}

// Example usage:
console.log(isValidPhoneNumber("123-456-7890")); // true
console.log(isValidPhoneNumber("123.456.7890")); // true
console.log(isValidPhoneNumber("123 456 7890")); // true
console.log(isValidPhoneNumber("1234567890"));   // false (no formatting)
console.log(isValidPhoneNumber("(123)-456-7890")); // true