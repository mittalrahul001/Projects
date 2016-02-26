<?php
/**
 * Rahul Mittal
 * Final Project ITMD 562
 */

namespace App;

use Illuminate\Database\Eloquent\Model;

class Blog extends Model
{
     protected $fillable = [
        'title',
        'description'
    ];
}
