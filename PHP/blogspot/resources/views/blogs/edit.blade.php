@extends('layouts.master')

@section('content')

    <h3 style="color: #843534 ;text-decoration: underline;">Edit this Blog below.</h3>
    <hr>

    <!-- Form to update the blogs -->
    {!! Form::model($Blog, [
    'method' => 'PATCH',
    'route' => ['blogs.update', $Blog->id]
]) !!}

    <div class="form-group">
        {!! Form::label('title', 'Title:', ['class' => 'control-label']) !!}
        {!! Form::text('title', null, ['class' => 'form-control']) !!}
    </div>

    <div class="form-group">
        {!! Form::label('description', 'Description:', ['class' => 'control-label']) !!}
        {!! Form::textarea('description', null, ['class' => 'form-control']) !!}
    </div>

    {!! Form::submit('Update Blog', ['class' => 'btn btn-danger']) !!}
    <a href="{{ route('blogs.index') }}" class="btn btn-danger">Go back to Blogs</a>

    {!! Form::close() !!}

@stop