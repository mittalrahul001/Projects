<?php
/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is reques*/
/**
 * Rahul Mittal
 * Final Project ITMD 562
 */

Route::get('/', function () {
    return view('welcome');
});


Route::post('check','BlogsController@login_check');

 Route::get('index', function () {
     return view('blogs.index');
 });


Route::get('/', [
    'as' => 'home',
    'uses' => 'PagesController@home'
]);

Route::get('/', [
    'as' => 'home',
    'uses' => 'BlogsController@index'
    ]);

Route::resource('blogs', 'BlogsController');


Route::get('logout', [
    'as' => 'logout',
    'uses' => 'BlogsController@logout'
]);


Route::get('login', [
   'as' => 'login',
    'uses' => 'BlogsController@login'
]);

