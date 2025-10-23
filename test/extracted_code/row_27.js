script
function countTokens(str) {
    // Normalize the string by removing punctuation and converting to lowercase
    const tokens = str.replace(/[^\w\s]|_/g, "").replace(/\s+/g, " ").toLowerCase().split(" ");

    // Initialize an empty object to store token frequencies
    const frequency = {};

    // Iterate over the tokens and update their counts in the frequency object
    for (const token of tokens) {
        if (token !== "") {
            if (!frequency[token]) {
                frequency[token] = 0;
            }
            frequency[token]++;
        }
    }

    return frequency;
}

// Example usage:
const str = "What is the answer to this question? What is the answer to that question?";
console.log(countTokens(str));
script
function countTokens(str) {
    const tokens = str.split(/[^\w\s]+/); // This regex splits by non-word and non-space characters
    const frequency = {};

    for (const token of tokens) {
        if (token !== "") {
            if (!frequency[token]) {
                frequency[token] = 0;
            }
            frequency[token]++;
        }
    }

    return frequency;
}
script
function countTokens(str) {
    const words = str.match(/\b\w+\b/g); // Using word boundary to ensure whole words are matched
    const frequency = {};

    for (const word of words) {
        if (!frequency[word]) {
            frequency[word] = 0;
        }
        frequency[word]++;
    }

    return frequency;
}