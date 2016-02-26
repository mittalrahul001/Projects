<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/9/2015
 * Time: 3:03 PM
 */
error_reporting(E_ALL);
date_default_timezone_set('America/Chicago');

require_once 'NoteRepository.php';
require_once 'Notes.php';

$noteRepo = new rmittal6\AS2\NoteRepository();

if($_SERVER['REQUEST_METHOD'] == 'POST' && !empty($_POST['id'])): ?>

    <?php $note = $noteRepo->getNoteById($_POST['id']);?>
    <!doctype html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <link rel="stylesheet" type="text/css" href="style.css" />
        </head>
        <body>
            <div class="container">
                <section id = "content">
                    <form method="post" action="Edit.php">
                        <h1>Edit Note</h1>
                        <input type="hidden" name="noteId" value="<?php print $_POST['id']; ?>">
                        <div>
                            Subject Line: <input type="text" name="title" required="" value="<?php print $note->getTitle(); ?>">
                        </div>
                        <div>
                            Note Body: <textarea name="body" cols="30" rows="5"><?php print $note->getBody(); ?></textarea>
                         </div>
                         <div>
                            Author Name:  <input type="text" name="createdBy" required="" value="<?php print $note->getCreatedBy(); ?>">
                        </div>
                        <div>
                            <input type="submit" value="Save Note">
                        </div>
                    </form>
                </section>
            </div>
        </body>
    </html>
<?php elseif ($_SERVER['REQUEST_METHOD'] == 'POST' && !empty($_POST['noteId'])): ?>
    <?php
    $noteTitle = isset($_POST['title'])     ? trim($_POST['title'])     : '';
    $noteBody  = isset($_POST['body'])      ? trim($_POST['body'])      : '';
    $createdBy = isset($_POST['createdBy']) ? trim($_POST['createdBy']) : '';
    $count     = strlen($noteBody);
    $createdAt = date("F j, Y, g:i a"); //October 09, 2015, 1:16 pm

    function test_input($data)
        {
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }

        $noteTitle = test_input($noteTitle);
        $noteBody  = test_input($noteBody);
        $createdBy = test_input($createdBy);

    $formIsValid  = true;
    $titleErr     = '';
    $createdByErr = '';

    if (empty($noteTitle))
    {
        $formIsValid = false;
        $titleErr    = '<span style="color: #f00;">Subject Line is required!</span>';
    }

    if (empty($createdBy))
    {
        $formIsValid = false;
        $createdByErr    = '<span style="color: #f00;">Author Name is Required!</span>';
    }
    ?>
    <?php if ($formIsValid): ?>
        <?php
        //Process valid data and save note update
        $aNote = $noteRepo->getNoteById($_POST['noteId']);
        $aNote->setTitle($noteTitle);
        $aNote->setBody($noteBody);
        $aNote->setCreatedBy($createdBy);
        $aNote->setCount($count);
        $aNote->setCreatedAt($createdAt);
        $noteRepo->saveNote($aNote);
        ?>
        <!doctype html>
        <html lang="en">
            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" type="text/css" href="style.css" />
                <title>Update Note</title>
            </head>
            <body>
                <div class="container">
                    <section id="content">
                        <h1>Note Updated</h1>
                        <p><a href="index.php"><mark>Back to Note List</mark></a></p>
                    </section>
                </div>
            </body>
        </html>

     <?php else: ?>
        <!doctype html>
        <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Update Note</title>
            </head>
            <body>
                <h1>Edit Note</h1>
                <form method="post" action="Edit.php">
                    <input type="hidden" name="noteId" value="<?php print $_POST['noteId']; ?>">
                    <label>Subject Line: <input type="text" name="title" value="<?php print $noteTitle; ?>">
                    </label><?php print $titleErr; ?><br>
                    <label>Note Body:<input type="text" name="body" value="<?php print $noteBody; ?>">
                    </label><br>
                    <label>Author Name: <input type="text" name="createdBy" value="<?php print $createdBy; ?>">
                    </label><?php print $createdByErr; ?><br>
                    <input type="submit" value="Save Note">
                </form>
            </body>
        </html>
     <?php endif ?>
<?php else: ?>
    <!doctype html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Document</title>
        </head>
        <body>
          <h1>No Note Selected for Editing</h1>
          <p><a href="index.php">Back to Note List</a></p>
        </body>
    </html>
<?php endif;?>
