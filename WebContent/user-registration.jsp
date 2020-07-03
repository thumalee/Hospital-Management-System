

<!DOCTYPE html>

<html>
<head>
<Title>Hospital managament System  </Title>
<link type="text/css" rel="stylesheet" href="css/registration.css">
<link type="text/css" rel="stylesheet" href="css/parsley.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="JavaScript/parsley.min.js"></script>


</head>
<body>

<div id="wrap">
	<div id="header">
	<h1>Hospital Management System</h1>
	</div>
</div>

<a href="index.jsp">
<img style="height:60px; margin-left:110px;position:absolute;margin-top:37px" src="images/home.png" alt="home">
</a>

<div class="regtop">
<h2>User Registration</h2>
</div>

	<form name="myform"   action="RegisterControllerServlet" method="post"  data-parsley-validate="">

	<input type="hidden" name="command" value="ADD">
	
  <div class="container">
   
    <p>Please fill in this form to create an account.</p>
    <hr>
    
     <label for="email"><b>Enter your Employee id</b></label>
    <input type="text"  name="de_eid" required >
   

    <label for="email"><b>Email</b></label>
    <input type="text"  name="email" required  data-parsley-type="email">

	  <Select required name="is" class="custom-select" style="width:500px; background-color:	#A0A0A0; height:40px;">
	<option value="">Choose Occupation</option>
	<option value="Doctor">Doctor</option>
  <option value="Receptionist">Receptionist</option>
  <option value="labassistant">Lab assistant</option>
  <option value="pharmacist">Pharmacist</option>
	</Select>
	
	<br><br>
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" id="ps1" name="psw" required  >

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" id="ps2" name="pswrepeat" required data-parsley-equalto="#ps1">
    <hr>

   
    <button type="submit" class="registerbtn" onclick=" matchpass();">Register</button>
  </div>

  <div class="container signin">
    <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
  </div>
</form>


</body>




</html>