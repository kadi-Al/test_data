script
function convertToCamelCase(inputString) {
    // Validate the input string to ensure it contains no numbers
    if (/\d/.test(inputString)) {
        throw new Error("Input string contains numerical values.");
    }

    // Split the input string by spaces, hyphens, and underscores
    let words = inputString.split(/[\s-_]+/);

    // Convert each word to lowercase except for the first one
    for (let i = 1; i < words.length; i++) {
        words[i] = words[i][0].toUpperCase() + words[i].slice(1).toLowerCase();
    }

    // Join the words back together and return the camel case string
    return words.join('');
}

// Example usage:
try {
    console.log(convertToCamelCase("hello world")); // Outputs: helloWorld
    console.log(convertToCamelCase("foo-bar_baz")); // Outputs: fooBarBaz
    console.log(convertToCamelCase("this_is a test string")); // Outputs: thisIsATestString
} catch (error) {
    console.error(error.message);
}