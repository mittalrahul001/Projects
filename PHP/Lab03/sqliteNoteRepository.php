<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 11/3/2015
 * Time: 4:10 PM
 */

namespace rmittal6\as3;

require_once 'INoteRepository.php';
require_once 'Notes.php';
class sqliteNoteRepository implements INoteRepository
{
    private $dbfile = 'data/notes_db_pdo.sqlite';
      private $db;

    public function __construct(){
        //open the database
        $this->db = new \PDO('sqlite:' . $this->dbfile);

        //create the table if not exists
        $this->db->exec("CREATE TABLE IF NOT EXISTS Notes (id INTEGER PRIMARY KEY, title TEXT, body TEXT, count INTEGER ,
                        createdBy TEXT, createdAt DATE )");
    }

    public function saveNote($note)
    {
        $title = $note->getTitle();
        $body  = $note->getBody();
        $count = $note->getCount();
        $createdBy = $note->getCreatedBy();
        $createdAt = $note->getCreatedAt();
        if ($note->getId() != '') {
            //Update
            $Id = $note->getId();
            $stmh = $this->db->prepare("UPDATE Notes SET title = :title, body = :body, count = :count,
            createdBy = :createdBy, createdAt = :createdAt WHERE id = :id");
            $stmh->bindParam(':title', $title);
            $stmh->bindParam(':body', $body);
            $stmh->bindParam(':id', $Id);
            $stmh->bindParam(':count', $count);
            $stmh->bindParam(':createdBy',$createdBy);
            $stmh->bindParam(':createdAt', $createdAt);
            $stmh->execute();

        } else {
            //Insert
            $stmh = $this->db->prepare("Insert into Notes (title, body,count,createdBy,createdAt)
                    values (:title, :body, :count, :createdBy, :createdAt)");
            $stmh->bindParam(':title', $title);
            $stmh->bindParam(':body', $body);
            //$stmh->bindParam(':id', $note->getId());
            $stmh->bindParam(':count', $count);
            $stmh->bindParam(':createdBy', $createdBy);
            $stmh->bindParam(':createdAt', $createdAt);
            $stmh->execute();

        }
    }

    public function getAllNotes()
    {
        $noteList = array();
        $result = $this->db->query('SELECT * FROM Notes');
        foreach($result as $row) {
            $aNote = new Notes();
            $aNote->setTitle($row['title']);
            $aNote->setBody($row['body']);
            $aNote->setCount($row['count']);
            $aNote->setCreatedBy($row['createdBy']);
            $aNote->setCreatedAt($row['createdAt']);
            $aNote->setId($row['id']);
            $noteList[$aNote->getId()] = $aNote;
        }
        return $noteList;
    }

    public function getNoteById($id)
    {
        $stmh = $this->db->prepare("SELECT * from Notes WHERE id = :id");
        $sid = intval($id);
        $stmh->bindParam(':id', $sid);
        $stmh->execute();
        $stmh->setFetchMode(\PDO::FETCH_ASSOC);

        if ($row = $stmh->fetch()) {
            $aNote = new Notes();
            $aNote->setId($row['id']);
            $aNote->setTitle($row['title']);
            $aNote->setBody($row['body']);
            $aNote->setCount($row['count']);
            $aNote->setCreatedBy($row['createdBy']);
            $aNote->setCreatedAt($row['createdAt']);
            return $aNote;
        } else {
            return new Notes();
        }
    }

    public function deleteNote($noteId)
    {
        $stmh = $this->db->prepare("DELETE FROM Notes WHERE id = :id");
        $stmh->bindParam(':id', $noteId);
        $stmh->execute();
    }
}