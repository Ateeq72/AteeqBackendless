<?php
        $host='mysql8.000webhost.com';
        $uname='a4387670_ateeq';
        $pwd='khader6';
        $db="a4387670_ateeq";
	$flag = array();


        $con = mysql_connect($host,$uname,$pwd) or die("connection failed" . mysql_error());
        mysql_select_db($db,$con) or die("db selection failed");
         
        $user=$_POST['user'];
        $dish=$_POST['dish'];
	$quantity=$_POST['quantity'];
	$pno=$_POST['pno'];
	

       


        if($r=mysql_query("insert into orders(user,dish,quantity,pno) values('$user','$dish','$quantity','$pno') ",$con))
        {
                $flag["success"] = 1;
                $flag["message"] = "Ordered successfully";
		die(json_encode($flag));

	}
	else
	{
		$flag["success"] = 0;
		$flag["message"] = "Order failed";
		echo json_encode($flag);
		die("insertion failed  : " . mysql_error());
	}

	die(json_encode($flag));

	mysql_close($con);
	
?>