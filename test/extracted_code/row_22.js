script
function fetchData() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://www.example.com/data', true);
    
    // Set up event listeners for different states of the request
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            console.log('Request succeeded:', xhr.responseText);
            // Parse and handle the response data as needed
            var data = JSON.parse(xhr.responseText);
            processData(data);
        } else {
            console.log('Request failed with status:', xhr.status);
        }
    };
    
    xhr.onerror = function () {
        console.log('There was a network error.');
    };
    
    // Set up a timeout to prevent indefinite waiting for the response
    xhr.timeout = 5000; // Timeout set to 5 seconds
    xhr.ontimeout = function () {
        console.log('The request took too long.');
    };
    
    // Send the request
    xhr.send();
}

// Function to process the fetched data
function processData(data) {
    if (data && typeof data === 'object') {
        console.log('Processing data:', data);
        // Here you can manipulate or use the data as needed
        // For example, let's say we want to display some of it in the browser:
        var outputDiv = document.getElementById('output');
        if (outputDiv) {
            outputDiv.innerHTML = '<pre>' + JSON.stringify(data, null, 2) + '</pre>';
        } else {
            console.log('No element with id "output" found to display data.');
        }
    } else {
        console.log('Invalid or unexpected data format:', typeof data);
    }
}

// Call the function to fetch data when needed, e.g., on a button click event:
document.getElementById('fetchButton').addEventListener('click', fetchData);