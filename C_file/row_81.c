php
<?php
$name = $_GET['name']; // Assuming 'name' is passed via GET parameter
$sql = sprintf("SELECT * FROM users WHERE name='%s'", $name);
// This is vulnerable to SQL injection
mysqli_query($conn, $sql);
?>
php
<?php
$name = $_GET['name']; // Assuming 'name' is passed via GET parameter
// Use prepared statement to prevent SQL injection
$stmt = $conn->prepare("SELECT * FROM users WHERE name=?");
$stmt->bind_param("s", $name); // "s" denotes a string argument
$stmt->execute();
$result = $stmt->get_result();
?>