<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 10/8/2015
 * Time: 3:29 PM
 */

namespace rmittal6\AS2;


class Notes
{
    private $id;
    private $title = '';
    private $body  = '';
    private $count;
    private $createdBy = '';
    private $createdAt = '';

   public function __construct()
   {
       $this->id = uniqid();
   }

    public function getID()
    {
        return $this->id;
    }

    public function getTitle()
    {
        return $this->title;
    }

    public function setTitle($title)
    {
        $this->title = $title;
    }

    public function getCount()
    {
        return $this->count;
    }

    public function getBody()
    {
        return $this->body;
    }

    public function setBody($body)
    {
        $this->body = $body;
    }

    public function getCreatedBy()
    {
        return $this->createdBy;
    }

    public function getCreatedAt()
    {
        return $this->createdAt;
    }

    public function setCreatedBy($createdBy)
    {
        $this->createdBy = $createdBy;
    }

    public function setCreatedAt($createdAt)
    {
        $this->createdAt = $createdAt;
    }

    public function setCount($count)
    {
        $this->count = $count;
    }
}