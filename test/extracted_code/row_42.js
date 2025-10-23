script
// Define the function to calculate factorial
function factorial(n) {
    if (n === 0 || n === 1) return 1;
    let result = 1;
    for (let i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

// Define the number
const number = 932;

// Convert the number to a string, then split it into individual digits
const digits = number.toString().split('').map(Number);

// Calculate the factorial of each digit and store in an array
const factorials = digits.map(digit => factorial(digit));

console.log(factorials); // Output: [362880, 6, 2]