script
function replaceNonAlphanumericWithUnderscore(inputString) {
    // Use a regular expression to match non-alphanumeric characters and replace them with underscores
    return inputString.replace(/[^a-zA-Z0-9]/g, '_');
}

// Example usage:
const helloWorld = "Hello World";
const result = replaceNonAlphanumericWithUnderscore(helloWorld);
console.log(result); // Output: Hello_World