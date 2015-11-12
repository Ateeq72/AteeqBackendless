<?php
         $host='mysql8.000webhost.com';
        $uname='a4387670_ateeq';
        $pwd='khader6';
        $db="a4387670_ateeq";
	$flag = array();

        $con = mysql_connect($host,$uname,$pwd) or die("connection failed");
        mysql_select_db($db,$con) or die("db selection failed");
         
        $name=$_POST['name'];
        $email=$_POST['email'];
	$password=$_POST['password'];

	$query = " SELECT * FROM user WHERE email = '$email'"; 
	$sql1=mysql_query($query); 
	$row = mysql_fetch_array($sql1); 

        if(!empty($row))
	{
		$flag['success']=0;
                $flag['message']="Registration failed User Exist!";
                die(json_encode($flag));
			
	}

   
        if($r=mysql_query("insert into user(name,email,password) values('$name','$email','$password') ",$con))
        {
                $flag['success']=1;
                $flag['message']="Registered successfully";
		die(json_encode($flag));

	}

	
	else
	{
		$flag['success']=0;
		$flag['message']="Registration failed";
		die(json_encode($flag));
	}

	die(json_encode($flag));
	mysql_close($con);
	
?>
