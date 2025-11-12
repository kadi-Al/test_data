php
<?php
// Example of XSS vulnerability by using printf with unsanitized user input
if (isset($_GET['userInput'])) {
    $userInput = $_GET['userInput'];
    printf("<div>%s</div>", $userInput);
} else {
    echo "No input provided.";
}
?>