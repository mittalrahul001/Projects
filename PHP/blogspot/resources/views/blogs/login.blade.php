@extends('layouts.master')

@section('content')
    @if($errors->any())
        <div class="alert alert-danger">
            @foreach($errors->all() as $error)
                <p>{{ $error }}</p>
            @endforeach
        </div>
    @endif
    <div class="form-group">
        {!! Form::open(['url' => 'check']) !!}

        <label style="font-size: 14px; color: #751F1F">Username :</label>
        <input type="text" name="username" >

        <br><br>
        {!! Form::label('pass', 'Password:', ['class' => 'control-label','style'=>'color: #751F1F']) !!}&nbsp;
        {!! Form::password('password', null, ['class' => 'form-control', 'style' => 'width:200px;']) !!}
        <br>
        <br>
        {!! Form::submit('Login', ['class' => 'btn btn-primary','style'=>'border-color: #8B4513; background-color: #8B4513']) !!}

        {!! Form::close() !!}

    </div>


@stop
