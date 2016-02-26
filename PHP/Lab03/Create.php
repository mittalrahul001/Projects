<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/8/2015
 * Time: 2:52 PM
 */
date_default_timezone_set('America/Chicago');
error_reporting(E_ALL);
require_once 'Notes.php';
require_once 'sqliteNoteRepository.php';
require_once 'Logout.php';
?>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Note</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
    <?php if ($_SERVER['REQUEST_METHOD'] == 'POST'): ?>
        <?php
        $noteTitle = isset($_POST['title'])     ? trim($_POST['title']) : '';
        $noteBody  = isset($_POST['body'])      ? trim($_POST['body']) : '';
        $createdBy = isset($_POST['createdBy']) ? trim($_POST['createdBy']) : '';

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

        if (empty($noteTitle)){
        $formIsValid = false;
        $titleErr    = '<span style="color: #f00;">Subject Line is required!</span>';
        }

       if (empty($createdBy)){
       $formIsValid  = false;
       $createdByErr = '<span style="color: #f00;">Author Name is Required!</span>';
        }
        ?>
        <?php if ($formIsValid): ?>
            <?php
              $notesRepo = new \rmittal6\AS3\sqliteNoteRepository();
              $note     = new \rmittal6\AS3\Notes();
              $count     = strlen($noteBody);
              $createdAt     = date("F j, Y, g:i a"); //October 09, 2015, 1:16 pm
              $note->setTitle($noteTitle);
              $note->setBody($noteBody);
              $note->setCreatedBy($createdBy);
              $note->setCount($count);
              $note->setCreatedAt($createdAt);
              $notesRepo->saveNote($note);
            ?>
    <div class="container">
        <section id = "content">
            <form>
            <h1>New Note Created</h1>
            <div>
            Subject Line:
                <input type="text" name="title" value="<?php print $noteTitle; ?>" readonly>
            </div>
            <?php if(!empty ($noteBody)) { ?>
                <div>
            Note Body:
            <textarea name="body" cols="30" rows="5" readonly><?php print $noteBody; ?></textarea>
                </div>
            <div>
            Count:
            <input type="text" name="count" value="<?php print $count;?>" readonly>
                </div>
            <?php } ?>
            <div>
                Author name:
                <input type="text" name="createdBy" value="<?php print $createdBy ?>" readonly>
            </div>
            <div>
            Last Modified At:
                <input type="text" name="date" value="<?php print $createdAt?>" readonly>
            </div>
            <div>
            <p><a href="index.php">Back to Note List</a></p>
            </div>
                </form>
        </section>
        </div>
        <?php else: ?>
            <h1>Create New Note</h1>
            <form method="post" action="Create.php">
                <label>Subject Line: <input type="text" name="title" value="<?php print $noteTitle; ?>">
                </label><br>
                <?php print $titleErr; ?><br>
                <label>Note Body:    <input type="text" name="body" value="<?php print $noteBody; ?>">
                </label><br>
                <label>Author Name: <input type="text" name="createdBy" value="<?php print $createdBy; ?>">
                </label><br>
                <?php print $createdByErr; ?><br>
                <input type="submit" value="Save Note">
            </form>
        <?php endif; ?>
    <?php else: ?>
    <div class="container">
        <section id="content">
            <form action="<?php echo $_SERVER["PHP_SELF"]; ?>" method = "POST" enctype="multipart/form-data">
                <h1>Create New Note</h1>
                <div>
                    <input type="text" placeholder="Subject Title" required="" name="title" />
                </div>
                <div>
                    <textarea placeholder="Note Body" cols="30" rows="5" name="body"></textarea>
                </div>
                <div>
                    <input type="text" placeholder="Author Name" required="" name="createdBy" />
                </div>
                <div>
                    <input type="submit" value="Save Note" />
                </div>
            </form>
        </section><!-- content -->
    </div><!-- container -->
    <?php endif;?>
</body>
</html>


