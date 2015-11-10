<?php
	$host='127.0.0.1';
	$uname='root';
	$pwd='laka';
	$db="rest";

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	
		 
	$password=$_REQUEST['password'];
	 
	$r=mysql_query("select * from user where password='$password'",$con);

	while($row=mysql_fetch_array($r))
	{
		$flag[name]=$row[name];
		$flag[email]=$row[email];
	}
	 
	print(json_encode($flag));
	mysql_close($con);
?>
