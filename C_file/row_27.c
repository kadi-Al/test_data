php
<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (isset($_GET['query'])) {
        $query = $_GET['query'];
        printf("<h1>Search: %s</h1>", htmlspecialchars($query)); // Outputting the query safely
    } else {
        echo "<p>No search term provided.</p>";
    }
}
?>
"<script>alert('XSS')</script>"
html
<h1>Search: <script>alert('XSS');</script></h1>
php
<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (isset($_GET['query'])) {
        $query = htmlspecialchars($_GET['query']); // Sanitizing and escaping the input
        printf("<h1>Search: %s</h1>", $query);
    } else {
        echo "<p>No search term provided.</p>";
    }
}
?>