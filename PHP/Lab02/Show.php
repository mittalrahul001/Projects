<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/9/2015
 * Time: 2:00 PM
 */
error_reporting(E_ALL);
require_once 'NoteRepository.php';
require_once 'Notes.php';

$noteRepo = new \rmittal6\AS2\NoteRepository();
$noteId = isset($_GET['id']) ? $_GET['id'] : '';
$note = $noteRepo->getNoteById($noteId);
?>
<?php if ($note): ?>
    <!doctype html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Display Note <?php print $note->getTitle(); ?></title>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
            <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        </head>
        <body>
            <div class="container-fluid">
                <div class="column">
                    <h1><b></u><i>Note Detail</i></b></h1>
                </div>
                <div class="row">
                    <div class = "col-sm-2"><h4>Subject Line:</h4></div>
                    <div class = "col-sm-2"><h4><span class="label label-default"><?php print $note->getTitle(); ?>
                    </span></h4></div>
                </div>
                <?php $noteBody = $note->getBody();
                if (!empty($noteBody)) {?>
                    <div class="row">
                        <div class = "col-sm-2"><h4>Note Body:</h4></div>
                        <div class = "col-sm-2">
                        <h4><span class="label label-default"><?php print $noteBody; ?></span></h4></div>
                    </div>
                    <div class="row">
                        <div class = "col-sm-2"><h4>Count:</h4> </div>
                        <div class = "col-sm-2"><h4><span class="label label-default"><?php print $note->getCount(); ?>
                        </span></h4></div>
                    </div>
                <?php } ?>
                    <div class="row">
                        <div class = "col-sm-2"><h4>Author name: </h4></div>
                        <div class = "col-sm-2"><h4><span class="label label-default"><?php print $note->getCreatedBy(); ?>
                        </span></h4></div>
                    </div>
                    <div class = "row">
                        <div  class = "col-sm-2"><h4>Last Updated At:</h4></div>
                        <div class = "col-sm-2"><h4><span class="label label-default"><?php print $note->getCreatedAt(); ?>
                        </span></h4></div>
                    </div>
                    <div class="row">
                        <div class = "col-sm-1">
                        <form action="Edit.php" method="POST" onclick="">
                            <input type="hidden" name="id" value="<?php print $note->getId();?>">
                            <input type="submit" class="btn btn-primary" value="Edit Note">
                        </form>
                        </div>
                        <div class="col-sm-1">
                        <form action="Delete.php" method="POST">
                            <input type="hidden" name="id" value="<?php print $note->getId();?>">
                            <input type="submit" class="btn btn-primary" value="Delete Note">
                        </form>
                          </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class = "col-sm-4">
                        <p><a href="index.php"><mark>Back to Note List</mark></a></p>
                        </div>
                    </div>
            </div>
        </body>
    </html>

<?php else: ?>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>No Note To Display</title>
    </head>
    <body>
        <h1>No Note Dound</h1>
        <a href="index.php">Back to Note List</a>
    </body>
    </html>
<?php endif; ?>

