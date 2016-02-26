<!-- Rahul Mittal
 Final Project ITMD 562-->

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Blogs</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    </head>
    <?php if(isset($path)): ?>
        <body background="{{$path}}business.jpg" style="background-repeat:no-repeat;background-size: 1400px 900px"; >
    <?php else: ?>
        <body background="business.jpg" style="background-repeat:no-repeat;background-size: 1400px 900px">
    <?php endif; ?>

    <nav class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="{{ route('home') }}" style="color: #843534";>Blogs</a>
        </div>
        <div class="nav navbar-nav navbar-right">
            <li><a href="{{ route('home') }}" style="color: #843534";>Home</a></li>
            <?php if(!isset($_SESSION['user'])) { ?>
            <li><a href="{{ route('login') }}" style="color: #843534";>Login</a></li>
            <?php } else { ?>
                <li><a href="{{ route('logout') }}" style="color: #843534";>Logout</a></li>
                <?php } ?>
        </div>
      </div>
    </nav>
    <main>
    <div class="container">
        @if(Session::has('flash_message'))
            <div class="alert alert-success">
                {{Session::get('flash_message')}}
            </div>
        @endif
        @yield('content')
    </div>
    </main>
        </body>
</html>