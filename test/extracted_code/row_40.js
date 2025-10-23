script
function convertBinaryToDecimalAndModulo(binaryString, numeral) {
    // Check if the binary string contains only '0' or '1'
    for (let char of binaryString) {
        if (char !== '0' && char !== '1') {
            throw new Error("Invalid binary string: Contains characters other than 0 or 1.");
        }
    }

    // Convert the binary string to a decimal number
    let decimalNumber = parseInt(binaryString, 2);

    // Calculate the modulo result of the converted decimal number after division by the numeral
    if (numeral === 0) {
        throw new Error("Numeral cannot be zero as it is in the denominator of a division operation.");
    }
    let moduloResult = decimalNumber % numeral;

    return moduloResult;
}

// Example usage:
try {
    console.log(convertBinaryToDecimalAndModulo('1010', 5)); // Output will be the result of (1010 in binary) % 5
} catch (error) {
    console.error(error.message);
}