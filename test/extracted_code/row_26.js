script
function countOccurrences(mainStr, subStr) {
    let count = 0;
    let i = 0;

    // Convert both strings to lowercase to handle case insensitivity
    mainStr = mainStr.toLowerCase();
    subStr = subStr.toLowerCase();

    while (i < mainStr.length) {
        // Check if the substring starts at the current position in the main string
        let found = true;
        for (let j = 0; j < subStr.length; j++) {
            if (i + j >= mainStr.length || mainStr[i + j] !== subStr[j]) {
                found = false;
                break;
            }
        }

        // If a match is found, increment the count and move the index past this occurrence
        if (found) {
            count++;
            i += subStr.length;
        } else {
            i++;
        }
    }

    return count;
}

// Example usage:
const mainStr = "Hello World Hello hello";
const subStr = "hello";
console.log(countOccurrences(mainStr, subStr)); // Output: 3