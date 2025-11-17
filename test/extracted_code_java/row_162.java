bash
npm install intl-messageformat
script
const MessageFormat = require('intl-messageformat');

function formatUserInput(input, values) {
    // Sanitize the input if necessary (e.g., remove potentially harmful characters)
    const sanitizedInput = input.replace(/[^\w\s]/gi, ''); // Example: removing special characters for simplicity

    try {
        // Create an instance of MessageFormat with the sanitized input and values
        const formatter = new MessageFormat(sanitizedInput, 'en');
        return formatter.format(values);
    } catch (error) {
        console.error("Error formatting message:", error);
        return null;
    }
}

// Example usage: passing a sanitized string and values for placeholders
const userInput = "Hello, {name}. Today is {date, date, long}.";
const values = { name: 'John', date: new Date() };

const formattedMessage = formatUserInput(userInput, values);
console.log(formattedMessage); // Output will depend on the sanitized input and provided values