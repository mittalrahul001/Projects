<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 11/3/2015
 * Time: 4:15 PM
 */

session_start();

if (isset($_GET['logout'])) {
    session_destroy();
}

if(isset($_SESSION['user']))
{
    header("Location: index.php");
    exit;
}

//require_once 'Logout.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Shorten Request Variables if they are set
    $username = isset($_POST['username']) ? trim($_POST['username']) : '';
    $password = isset($_POST['password']) ? trim($_POST['password']) : '';

    function test_input($data)
    {
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

    $valid_user = test_input($valid_user);
    $valid_hash = test_input($valid_hash);

    $valid_user = 'admin';
    $valid_hash = '$2y$10$tvKXv57wFWSeECg2ALkh3uQE.F6z7cSjQT/A.3CzfHIVYQtp2/YFe';

    if ($username == $valid_user && password_verify($password, $valid_hash)) {
        $_SESSION['user'] = $valid_user;
        header("Location:index.php");
        exit;
    }
}


?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div class="container">
    <section id = "content">
        <?php function error($error) { ?>
        <label><?php print $error ?></label>
        <?php } ?>
        <form action="#" method="POST">
            <h1>Login</h1>
            <div>
                <input type="text" placeholder="Username" name="username" required="" />
            </div>
            <div>
                <input type="password" placeholder="Password" name="password" required="" />
            </div>
            <div>
            <input type="submit" value="Submit">
            </div>
        </form>
        </section>
    </div>


</body>
</html>