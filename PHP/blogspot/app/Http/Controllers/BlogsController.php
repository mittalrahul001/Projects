<?php
/**
 * Rahul Mittal
 * Final Project ITMD 562
 */
namespace App\Http\Controllers;

use App\Blog;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\View;
use Illuminate\Support\Facades\Session;

class BlogsController extends Controller
{
    /**
     * Start the session and checks if the
     * user is logged in, if not, it will
     * redirect the user to Login Page
     *
     * @return \Illuminate\Http\Response
     */

    public function index()
    {
        session_start();
        if(isset($_SESSION['user']))
        {
            $Blogs = Blog::all();
            return View::make('blogs.index')->with('Blogs',$Blogs);
        }
        else
        {
            $path = "../public/";
            return View::make('blogs.login')->with('path',$path);
        }
    }

    /**
     * Redirect to login page
     * @return mixed
     */
    public function login()
    {
        $path = "../public/";
        return View::make('blogs.login')->with('path',$path);
    }

    /**
     * Check if the user is valid, it will redirect
     * to Index page else it wi show the login
     * page again
     * @param Request $request
     * @return mixed
     */
    public function login_check(Request $request)
    {
        $this->validate($request,
            [
                'username' => 'required',
                'password' => 'required'
            ]);

        $username = $request->username;
        $password = $request->password;
        $valid_user = "admin";
        $valid_hash = '$2y$10$tvKXv57wFWSeECg2ALkh3uQE.F6z7cSjQT/A.3CzfHIVYQtp2/YFe';

        if ($username == $valid_user && password_verify($password, $valid_hash))
        {
            session_start();
            $_SESSION['user'] = $valid_user;
            $Blogs = Blog::all();
            return View::make('blogs.index')->with('Blogs',$Blogs);
        }
        else
        {
            $path = "../public/";
            return View::make('blogs.login')->with('path',$path);
        }
//
    }

    /**
     * This will destroy the session and redirect the page to Login
     * @return mixed
     */
    public function logout()
    {
        session_start();
        session_destroy();
        $_SESSION['user'] = null;
        $path = "../public/";
        return View::make('blogs.login')->with('path',$path);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
            session_start();
            $path = "../../public/";
            return View::make('blogs.create')->with('path', $path);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        session_start();
        $this->validate($request,
            [
                'title' => 'required',
                'description' => 'required'
            ]);
        $input = $request->all();

        Blog::create($input);

        Session::flash('flash_message', 'Blog successfully added!');

        return redirect()->back();
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        session_start();
        $path = "../../public/";
        $Blog = Blog::findOrFail($id);
        return View::make('blogs.show')->with('Blog',$Blog)->with('path',$path);

    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        session_start();
        $path = "../../../public/";
        $Blog = Blog::findOrFail($id);
        return View::make('blogs.edit')->with('Blog', $Blog)->with('path', $path);

    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        session_start();
        $Blog = Blog::findOrFail($id);
        $this->validate($request, [
            'title' => 'required',
            'description' => 'required'
        ]);

        $input = $request->all();
        $Blog->fill($input)->save();

        Session::flash('flash_message', 'Blog updated Successfully!');
        return redirect()->back();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        session_start();
        $path = "../../public/";
        $Blog = Blog::findOrFail($id);

        $Blog->delete();

        return redirect()->route('blogs.index')->with('path',$path);
    }
}
