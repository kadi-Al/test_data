script
function extractUniqueCharacters(characterSequence, quantity) {
    let uniqueChars = new Set();
    
    for (let char of characterSequence) {
        // Check if the character is alphanumeric and not already in the set
        if (/^[a-zA-Z0-9]$/.test(char) && !uniqueChars.has(char)) {
            uniqueChars.add(char);
        }
        
        // Break the loop once we have collected the required number of unique characters
        if (uniqueChars.size === quantity) {
            break;
        }
    }
    
    return Array.from(uniqueChars).join('');
}

// Example usage:
const characterSequence = "hello world";
const designatedQuantity = 5;
console.log(extractUniqueCharacters(characterSequence, designatedQuantity)); // Output: "helowr"