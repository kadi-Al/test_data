script
function customReplace(inputString, searchValue, replacement) {
    // Check if the search value is a regular expression
    if (searchValue instanceof RegExp) {
        // Use the global flag to replace all matches
        return inputString.replace(searchValue, replacement);
    } else {
        // Replace only the first occurrence if not a regex
        return inputString.split(searchValue).join(replacement);
    }
}

// Example usage:
let str = "The quick brown fox jumps over the lazy dog.";

// Replace 'the' with 'a' (case-insensitive)
let searchRegex = /the/gi; // 'g' for global, 'i' for case-insensitive
let replacementText = customReplace(str, searchRegex, 'a');
console.log(replacementText); // "A quick brown fox jumps over a lazy dog."