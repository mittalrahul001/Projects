<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 11/8/2015
 * Time: 1:37 PM
 */

session_start();

if (isset($_GET['logout'])) {
    session_destroy();
    header("Location: Login.php");
    exit;
}

if(!isset($_SESSION['user']))
{
    header("Location: Login.php");
    exit;
}
else
{ ?>
    <div class="row-sm-10">
<form action= "Logout.php" method="get" >
    <input type="submit" value="Logout" class="btn btn-primary" name="logout">
</form>
        </div>
<?php } ?>