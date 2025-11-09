script
const express = require('express');
const app = express();

// Sample array to access
let sampleArray = [10, 20, 30, 40, 50];

app.get('/access', (req, res) => {
    // Read the index parameter from the request query string or body
    let index = req.query.index || req.body.index;

    if (index === undefined) {
        return res.status(400).send('Index parameter is missing.');
    }

    try {
        // Attempt to access the array at the given index
        let value = sampleArray[index];
        res.send(`Value at index ${index} is ${value}`);
    } catch (error) {
        // Handle any potential errors, such as invalid index types or out-of-bounds access
        if (error instanceof TypeError || error instanceof RangeError) {
            return res.status(400).send('Invalid index provided.');
        } else {
            throw error; // Re-throw unexpected errors
        }
    }
});

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
bash
  curl -X POST http://localhost:3000/access -H "Content-Type: application/json" -d '{"index": 2}'