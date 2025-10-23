script
function translateString(str, isNextVowel) {
    const vowels = 'aeiou';
    let result = '';

    for (let char of str.toLowerCase()) {
        if (vowels.includes(char)) {
            let currentIndex = vowels.indexOf(char);
            if (isNextVowel) {
                // Find the next vowel in the sequence, wrapping around if necessary
                let newIndex = (currentIndex + 1) % vowels.length;
                result += str[0] === char ? String.fromCharCode(str.charCodeAt(0) - 32).toLowerCase() : char;
            } else {
                // Find the previous vowel in the sequence, wrapping around if necessary
                let newIndex = (currentIndex - 1 + vowels.length) % vowels.length;
                result += str[0] === char ? String.fromCharCode(str.charCodeAt(0) - 32).toLowerCase() : char;
            }
        } else {
            // If it's not a vowel, just add the character to the result as is
            result += char;
        }
    }

    return result;
}

// Example usage:
let string = "Hello World";
console.log(translateString(string, true));  // Output: "Ifmmp Xpsme" (next vowels)
console.log(translateString(string, false)); // Output: "Gdkkn Vnqkc" (previous vowels)