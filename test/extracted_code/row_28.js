script
function isValidCreditCardNumber(cardNumber) {
    // Remove non-digit characters
    let cleanedNumber = cardNumber.replace(/\D/g, '');

    if (cleanedNumber.length !== 16) {
        return false;
    }

    // Reverse the credit card number
    let reversedDigits = cleanedNumber.split('').reverse().map(Number);

    // Apply the Luhn algorithm
    let sum = 0;
    for (let i = 0; i < reversedDigits.length; i++) {
        if (i % 2 === 1) {
            let doubledDigit = reversedDigits[i] * 2;
            if (doubledDigit > 9) {
                doubledDigit -= 9;
            }
            sum += doubledDigit;
        } else {
            sum += reversedDigits[i];
        }
    }

    // Check if the sum is a multiple of 10
    return sum % 10 === 0;
}

// Example usage:
let cardNumber = "4111 1111 1111 1111";
if (isValidCreditCardNumber(cardNumber)) {
    console.log("Valid credit card number");
} else {
    console.log("Invalid credit card number");
}