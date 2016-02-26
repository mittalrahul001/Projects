<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 11/3/2015
 * Time: 4:05 PM
 */

namespace rmittal6\as3;


interface INoteRepository
{
    public function saveNote($note);
    public function getAllNotes();
    public function getNoteById($id);
    public function deleteNote($noteId);
}