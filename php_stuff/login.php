<?php 

mysql_connect("localhost","root","laka"); 
$db= mysql_select_db("rest"); 


$email=$_POST["email"]; 
$password=$_POST["password"];
$response=array(); 
 
if (!empty($_POST)) { 
if (empty($_POST['email']) || empty($_POST['password'])) { 

$response["success"] = 0; 
$response["message"] = "One or both of the fields are empty ."; 

die(json_encode($response)); 
} 

$query = "SELECT * FROM user WHERE email = '$email' and password = '$password' "; 
$sql1=mysql_query($query); 
$row = mysql_fetch_assoc($sql1); 

if (!empty($row)) { 

$response["success"] = 1; 
$response["message"] = "You have been sucessfully logged in"; 
$response["cname"] = $row['name'];
$response["cemail"] = $row['email'];

die(json_encode($response)); 
}

else{ 
$response["success"] = 0; 
$response["message"] = "Invalid username or password"; 
die(json_encode($response)); 
} 
} 

else{ 
$response["success"] = 0; 
$response["message"] = " One or both of the fields are empty "; 
die(json_encode($response)); 
} 
mysql_close(); ?>


