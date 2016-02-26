<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/8/2015
 * Time: 3:11 PM
 */

namespace rmittal6\AS2;

require_once 'INoteRepository.php';
require_once 'Notes.php';

class NoteRepository implements INoteRepository
{
    private $fileName = 'data\notes.txt';

    public function saveNote($note)
    {
        $dataArray = $this->getAllNotes();
        $dataArray[$note->getId()] = $note;
        $serialData = serialize($dataArray);
        file_put_contents($this->fileName, $serialData);
    }

    public function getAllNotes()
    {
        $data = file_get_contents($this->fileName);
        if ($data)
        {
            $dataArray = unserialize($data);
            return $dataArray;
        }
        else
        {
            return array();
        }
    }

    public function getNoteById($id)
    {
        $noteList = $this->getAllNotes();
        if (array_key_exists($id, $noteList)) {
            return $noteList[$id];
        }
    }

    public function deleteNote($noteId)
    {
        $noteList = $this->getAllNotes();
        if (array_key_exists($noteId, $noteList))
        {
            unset($noteList[$noteId]);
            $serialData = serialize($noteList);
            file_put_contents($this->fileName, $serialData);
        }
    }
}