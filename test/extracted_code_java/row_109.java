html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Filter</title>
</head>
<body>
    <h1>Search with SQL WHERE Clause</h1>
    <form id="searchForm">
        <input type="text" id="filterText" name="filterText" placeholder="Enter filter criteria">
        <button type="submit">Search</button>
    </form>
    <div id="result"></div>

    <script src="app.js"></script>
</body>
</html>
script
// app.js
document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting in the traditional way

    const filterText = document.getElementById('filterText').value;
    
    if (!filterText) {
        alert("Please enter some search criteria.");
        return;
    }

    fetch('/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ filter: filterText })
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('result').innerText = data;
    });
});
script
// server.js (or app.js if using express generator)
const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.json());

app.post('/search', (req, res) => {
    const filterText = req.body.filter;
    if (!filterText) {
        return res.send("Please provide a valid filter.");
    }

    // Example SQL query with simple WHERE clause based on the filter text
    let sqlQuery = "SELECT * FROM your_table";
    if (filterText) {
        sqlQuery += ` WHERE column_name LIKE '%${filterText}%'`;
    }

    res.send(`Generated SQL Query: ${sqlQuery}`);
});

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});