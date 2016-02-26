<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/14/2015
 * Time: 4:27 PM
 */
error_reporting(E_ALL);
require_once 'sqliteNoteRepository.php';
require_once 'Notes.php';

$notesRepo = new \rmittal6\AS3\sqliteNoteRepository();

$notesList = $notesRepo->getAllNotes();
require_once 'Logout.php';
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Notes</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="well well-sm">
        <h1><b><i>List of Notes</i></b></h1>
    </div>
        <a href="Create.php" class="btn btn-primary">Add New Note</a>
    <table class="table">
        <thead>
        <tr>
            <th>Subject Line</th>
            <th>Date/Time Created</th>
            <th>Number of Characters</th>
        </tr>
        </thead>
        <tbody>
        <?php
        foreach($notesList as $note )
        {
            print '<tr>';
            print '<td><a href="show.php?id=' .  $note->getId() . '">'. $note->getTitle() .'</a></td>';
            print '<td>' . $note->getcreatedAt() .'</td>';
            print '<p class="text-center">';
            print '<td>' . $note->getcount(). '</td>';
            print '</p>';
            print '</tr>';
        }
        ?>
        </tbody>
    </table>
</div>
</body>
</html>
