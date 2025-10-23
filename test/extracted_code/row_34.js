script
function detectXSequence(text) {
    // Check if the input is a string
    if (typeof text !== 'string') {
        return "Error: Input must be a string.";
    }

    // Check if the string is empty
    if (text.length === 0) {
        return "Error: String is empty.";
    }

    // Iterate through the string and check for the sequence 'xyy'
    for (let i = 0; i < text.length - 2; i++) {
        if (text[i] === 'x' && text[i + 1] === 'y' && text[i + 2] === 'y') {
            return "Sequence found: 'xyy'";
        }
    }

    // If no sequence is found, return the appropriate message
    return "No sequence found.";
}

// Example usage:
console.log(detectXSequence("hello world")); // No sequence found.
console.log(detectXSequence("xyy")); // Sequence found: 'xyy'
console.log(detectXSequence("x y y")); // No sequence found.
console.log(detectXSequence("")); // Error: String is empty.
console.log(detectXSequence(123)); // Error: Input must be a string.