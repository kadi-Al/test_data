script
function sanitizeString(inputStr) {
    // Use a regular expression to match non-alphabetic characters
    return inputStr.replace(/[^a-zA-Z]/g, '&');
}

// Example usage:
let inputStr = "Hello* World123!";
console.log(sanitizeString(inputStr)); // Output: "Hello& World&&&"