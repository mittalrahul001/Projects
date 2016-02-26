<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/8/2015
 * Time: 3:15 PM
 */

namespace rmittal6\AS2;


interface INoteRepository
{
    public function saveNote($note);
    public function getAllNotes();
    public function getNoteById($id);
    public function deleteNote($noteId);
}