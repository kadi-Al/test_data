script
function isValidCreditCard(number) {
    // Convert number to string for easier manipulation
    let numStr = number.toString();

    // Check if the card number is a valid length for Visa or Mastercard
    if ((numStr.length < 13 || numStr.length > 16) || (numStr[0] !== '4' && !['51', '52', '53', '54', '55'].includes(numStr.substring(0, 2)))) {
        return false;
    }

    // Validate using the Luhn algorithm
    let sum = 0;
    let isSecond = false;
    for (let i = numStr.length - 1; i >= 0; i--) {
        let digit = parseInt(numStr[i], 10);

        if (isSecond == true)
            digit = digit * 2;

        // Add two digits to handle cases that make two digits after multiplication by 2
        sum += Math.floor(digit / 10) + (digit % 10);

        isSecond = !isSecond;
    }

    return (sum % 10 == 0);
}

function getCardType(number) {
    let numStr = number.toString();

    // Check for Visa
    if (numStr[0] === '4' && [13, 16].includes(numStr.length)) {
        return 'Visa';
    }
    // Check for Mastercard
    else if ([14, 15].includes(numStr.length) && ['51', '52', '53', '54', '55'].includes(numStr.substring(0, 2))) {
        return 'Mastercard';
    } else {
        return 'Unknown';
    }
}

// Example usage:
let cardNumber = "4111111111111111"; // Visa example
console.log(getCardType(cardNumber)); // Should print "Visa"
console.log(isValidCreditCard(cardNumber)); // Should validate the number and return true or false