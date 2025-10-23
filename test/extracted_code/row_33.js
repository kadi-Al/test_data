script
function countSubstrings(mainString, substring) {
    // Normalize strings by removing all whitespaces and converting to lowercase
    const normalizedMainString = mainString.replace(/\s+/g, '').toLowerCase();
    const normalizedSubstring = substring.replace(/\s+/g, '').toLowerCase();

    // Use a regular expression to find the non-overlapping occurrences of the substring
    let count = 0;
    let startIndex = 0;
    while ((startIndex = normalizedMainString.indexOf(normalizedSubstring, startIndex)) !== -1) {
        count++;
        startIndex += normalizedSubstring.length; // Move start index by the length of the substring to avoid overlapping
    }

    return count;
}

// Example usage:
const main_string = "HeLlO helloHello hELlohelLO";
const substring = "hello";
console.log(countSubstrings(main_string, substring)); // Output should be 3