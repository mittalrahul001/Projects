<!DOCTYPE html>
	<?php
	date_default_timezone_set('America/Chicago');
	ini_set('display_errors', 1);
	error_reporting(E_ALL);
	?>

	<head>
	<meta charset="utf-8">
	<title>Login Form</title>
	<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>

		<?php
		//define variables and set to empty values
			$firstname = $lastname = $emailid = $comment = "";
			$firsterr = "";

			if ($_SERVER["REQUEST_METHOD"] == "POST") 
			{
			    function test_input($data) 
				{
				   $data = trim($data);
				   $data = stripslashes($data);
				   $data = htmlspecialchars($data);
				   return $data;
				}
				
			   $firstname = test_input($_POST["first"]);
			   $lastname  = test_input($_POST["last"]);
			   $emailid   = test_input($_POST["emailid"]);
			   $comment   = test_input($_POST["comment"]);
			   $sex       = test_input($_POST["sex"]);
			   $name      = $_FILES['pic']['name'];
			   $tmp_name  = $_FILES['pic']['tmp_name'];
			
				print "<h4>Your data was submitted on: ".date("d M Y - h:i:s A")."</h4><br>";
				print "<h2>First Name:    $firstname</h2><br>";
				print "<h2>Last  Name:    $lastname </h2><br>";
				print "<h2>Email Address: $emailid  </h2><br>";  
				print "<h2>Gender: $sex </h2><br>";
	 		    if (empty($comment)){}
	 		    else
					{ echo "<h5>Comment: $comment </h5><br>";
					}
				if(isset($name))
				{
					if(!empty($name))
					{
					    $location = 'uploads/';
				        $path = $location.$name;
						move_uploaded_file($tmp_name, $location.$name);
						echo "<a href= $path >Download file</a><br>";
							
					}
				}
			}
		    else
		    {
		?>
	 
		<div class="container">
			<section id="content">
				<form action="<?php echo $_SERVER["PHP_SELF"]; ?>" method = "POST" enctype="multipart/form-data">
					<h1>Login Form</h1>
					<div>
						<input type="text" placeholder="First Name" required="" name="first" />
					</div>
					<div>
						<input type="text" placeholder="Last Name" required="" name="last" />
					</div>
					<div>
						<input type="text" placeholder="Email Address" required="" name="emailid" />
					</div>
					<div>
						<textarea placeholder="Tell us more about you.." cols="30" rows="5" name="comment"></textarea>
					</div>
					<div>
						<input type="radio" name="sex" value="Male" checked>Male
						<input type="radio" name="sex" value="Female">Female
					</div> <br>
					<div>
						<input type="file" accept="image/*" name="pic" id="pic" >
				    </div>
					<div>
						<input type="submit" value="Submit" />
					</div>
				</form>
			</section><!-- content -->
		</div><!-- container -->
		<?php } ?>
	</body>
</html>