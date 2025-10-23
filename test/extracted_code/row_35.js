script
function checkVowels(inputString) {
    if (!inputString) {
        return { error: "Input string is empty." };
    }

    const vowels = 'aeiouAEIOU';
    let vowelCount = { a: 0, e: 0, i: 0, o: 0, u: 0 };
    let hasVowel = false;

    for (let char of inputString) {
        if (vowels.includes(char)) {
            vowelCount[char.toLowerCase()]++;
            hasVowel = true;
        }
    }

    if (!hasVowel) {
        return { error: "The string contains no vowels." };
    }

    return vowelCount;
}

// Test cases
console.log(checkVowels("Hello World!")); // { a: 0, e: 3, i: 1, o: 2, u: 1 }
console.log(checkVowels("rhythm")); // { error: "The string contains no vowels." }
console.log(checkVowels("")); // { error: "Input string is empty." }
console.log(checkVowels("sky")); // { a: 0, e: 0, i: 0, o: 0, u: 0, error: "The string contains no vowels." }