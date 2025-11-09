html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <form action="handle_registration.php" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>
        <input type="submit" value="Register">
    </form>
</body>
</html>
php
<?php
// Database configuration
$host = "localhost"; // e.g., localhost or IP address of your MySQL server
$username = "your_db_user";
$password = "your_db_pass";
$database = "your_db_name";

// Create a connection to the database
$conn = new mysqli($host, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Handle form submission
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Collect and sanitize input data
    $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);

    // SQL query to insert the email into the database
    $sql = "INSERT INTO users (email) VALUES ('$email')";

    if ($conn->query($sql) === TRUE) {
        echo "Email saved successfully.";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

$conn->close();
?>
sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE
);