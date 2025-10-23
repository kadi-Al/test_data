script
function toTitleCase(str, ignore_words) {
    // Split the string into an array of words
    let words = str.split(' ');
    
    // Process each word
    for (let i = 0; i < words.length; i++) {
        if (!ignore_words.includes(words[i].toLowerCase())) {
            // Capitalize the first letter and make the rest lowercase
            words[i] = words[i][0].toUpperCase() + words[i].slice(1).toLowerCase();
        } else {
            // If the word is in ignore_words, keep it as is (in lowercase)
            words[i] = words[i].toLowerCase();
        }
    }
    
    // Join the words back into a single string with spaces and return
    return words.join(' ');
}

// Example usage:
let str = "This is a sample sentence.";
let ignore_words = ['a', 'is'];
console.log(toTitleCase(str, ignore_words)); // Outputs: "This is A Sample Sentence."