script
function mergeAndRemoveDuplicates(array1, array2) {
    // Merge the two arrays
    let mergedArray = array1.concat(array2);
    
    // Remove duplicates by converting to a Set (which automatically removes duplicates)
    let uniqueArray = Array.from(new Set(mergedArray));
    
    return uniqueArray;
}

// Provided arrays
let array1 = [1, 5, 6];
let array2 = [4, 5, 8];

// Call the function and log the result
let result = mergeAndRemoveDuplicates(array1, array2);
console.log(result); // Output: [1, 5, 6, 4, 8]