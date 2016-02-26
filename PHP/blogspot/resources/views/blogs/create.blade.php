@extends('layouts.master')

@section('content')
<!-- Error Message -->
@if($errors->any())
    <div class="alert alert-danger">
        @foreach($errors->all() as $error)
            <p>{{ $error }}</p>
        @endforeach
    </div>
@endif

<!-- Form to create a new Blog in database -->
{!! Form::open([
    'route' => 'blogs.store'
]) !!}

<div class="form-group">
    {!! Form::label('title', 'Title:', ['class' => 'control-label']) !!}
    {!! Form::text('title', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('description', 'Description:', ['class' => 'control-label']) !!}
    {!! Form::textarea('description', null, ['class' => 'form-control']) !!}
</div>

{!! Form::submit('Create New Blog', ['class' => 'btn btn-primary']) !!}
<a href="{{ route('blogs.index') }}" class="btn btn-primary">Cancel</a>

{!! Form::close() !!}

@stop