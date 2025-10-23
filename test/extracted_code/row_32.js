script
function isPangram(str) {
    // Convert the string to lowercase to make the comparison case-insensitive
    str = str.toLowerCase();
    
    // Create a set of all letters in the alphabet
    const alphabetSet = new Set('abcdefghijklmnopqrstuvwxyz');
    
    // Iterate through each character in the string and remove it from the set if present
    for (let char of str) {
        alphabetSet.delete(char);
    }
    
    // If the set is empty, all letters were found; otherwise, it's not a pangram
    return alphabetSet.size === 0;
}

// Example usage:
console.log(isPangram("The quick brown fox jumps over the lazy dog")); // true
console.log(isPangram("Hello, world!")); // false