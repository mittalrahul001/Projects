@extends('layouts.master')

@section('content')

        <!-- Display the complete blog based on the id-->
    <h1 style="color:#843534;display:inline-block;text-decoration: underline;">{{ $Blog->title }}</h1>
    <br>
    <br>
    <p style="color:#843534";>{{ $Blog->description }}</p>
    <hr>

    <a href="{{ route('blogs.index') }}" class="btn btn-danger">Back to all blogs</a>
    <a href="{{ route('blogs.edit', $Blog->id) }}" class="btn btn-danger">Edit Blog</a>

    <div class="col-md-6 text-right">
        {!! Form::open([
            'method' => 'DELETE',
            'route' => ['blogs.destroy', $Blog->id]
        ]) !!}
        {!! Form::submit('Delete this Blog?', ['class' => 'btn btn-danger']) !!}
        {!! Form::close() !!}
    </div>

@stop