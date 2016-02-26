<?php
/**
 * Created by PhpStorm.
 * User: Rahul
 * Date: 11/3/2015
 * Time: 4:13 PM
 */

namespace rmittal6\as3;


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
       // $this->id = uniqid();
    }

    public function getId()
    {
        return $this->id;
    }

    public function setId($id)
    {
        $this->id = $id;
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