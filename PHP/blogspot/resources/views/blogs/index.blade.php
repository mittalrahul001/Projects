@extends('layouts.master')

@section('content')

<h1 style="color: #843534; text-decoration: underline";>Business Blogs</h1>
<p class="lead">How to grow your business? Check out the business blogs written by the experts.
<a href="{{ route('blogs.create') }}" style="color: #843534";>Add a new one?</a></p>
<hr>

<!-- Loop the list of blogs and display each blog
 if the blog lenth is more than 500 words then just display
 500 words and show read more-->
@foreach($Blogs as $blog)
    <h3 style="color:#843534;display:inline-block;text-decoration: underline;">{{ $blog->title }}</h3>
    <p>{{"Posted on:"}} {{$blog->created_at}}</p>
    <?php
        $string = strip_tags($blog->description);

        if (strlen($string) > 500)
        {
            // truncate string
            $stringCut = substr($string, 0, 500);

            // make sure it ends in a word so assassinate doesn't become ass...
            $string = substr($stringCut, 0, strrpos($stringCut, ' ')).'...  ';
        }
    ?>

    <p style="color:#8B0000">{{ $string}}</p>
    <p>
        <a href="{{ route('blogs.show', $blog->id) }}" class="btn btn-danger">Show More</a>
    </p>
    <hr>
@endforeach
@stop