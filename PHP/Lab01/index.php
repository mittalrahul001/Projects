<!DOCTYPE html>
<html>
<head>
	<title>Get Demo</title>
	<style>
body {background-color:lightblue;
	  border:1px solid black}
h1   {color:blue;
	   font-size:200%;}
h2   {color:darkgreen}
h3   {color:maroon;}
p    {color:green;
       font-size: 120%}
a    {color:darkviolet;
	   font-size: 150%}
</style>
</head> 
<body>

<?php
date_default_timezone_set('America/Chicago');

if(isset($_GET['prof']))
{
	$text = 'Hello '  . $_GET['prof'];
	print "<h1><i>$text</i></h1>";
}
else 
{
	print '<h1><i>Hey Welcome!!</i></h1>';
}
print "<h2> ITMD 562_01 LAB 01</h2>";
print "<h3>Rahul Mittal</h3>";
print "<p>".date("l F d, Y h:i:s a"). "</p>";
    
?>
<a href="form.php">Get me to a Submit Form</a>
</body>
</html>