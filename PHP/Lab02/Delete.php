<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/9/2015
 * Time: 2:01 PM
 */
error_reporting(E_ALL);
require_once 'NoteRepository.php';
require_once 'Notes.php';

$noteRepo = new rmittal6\AS2\NoteRepository();
?>
<?php if($_SERVER['REQUEST_METHOD'] == 'POST' && !empty($_POST['id'])): {?>
    <!doctype html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <link rel="stylesheet" type="text/css" href="style.css" /><title>Delete Note</title>
        </head>
        <body>
            <div class="container">
                <section id="content">
                    <form action="Delete.php" method="get">
                       <div class="row">
                        <p class="col-lg-8"><h3>Are you sure you want to delete this note?</h3></p></div>
                        <div class="row">
                        <p class=""col-lg-6">
                        <p class="text-center"><input type="submit" class="btn btn-primary" name="confirm" value="Yes">
                        <input type="submit" class="btn btn-primary" name="confirm" value="Cancel"></p></p></div>
                        <input type = "hidden" name="id" value="<?php print $_POST['id']; ?>">
                    </form>
                </section>
             </div>
        </body>
    </html>
<?php } ?>
<?php elseif($_SERVER['REQUEST_METHOD'] == 'GET' && !empty($_GET["id"])):
        $id = $_GET["id"];
        $confirm = $_GET["confirm"];
        if($confirm == "Yes")
            {
                $noteRepo->deleteNote($_GET["id"]);
            ?>
            <!doctype html>
            <html lang="en">
              <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" type="text/css" href="style.css" />
                <!--<meta name="viewport" content="width=device-width, initial-scale=1">
                <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
                <title>Delete Note</title>
              </head>
              <body>
                <div class="container">
                    <section id="content">
                        <div class="well well-sm">
                        <h1><b><i>Note Deleted</i></b></h1>
                        </div>
                    <p><a href="index.php"><mark>Back to Note List</mark></a></p>
                     </section>
                 </div>
              </body>
            </html>
        <?php }
        else
        {
            header('Location: Show.php?id='.$id,true);
         } ?>

<?php else: ?>
    <!doctype html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Note</title>
        </head>
        <body>
            <h1>No Note Selected for deletion</h1>
            <p><a href="index.php">Back to Note List</a></p>
        </body>
    </html>
<?php endif; ?>




