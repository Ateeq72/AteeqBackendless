<?php
        $host='127.0.0.1';
        $uname='root';
        $pwd='laka';
        $db="rest";


        $con = mysql_connect($host,$uname,$pwd) or die("connection failed" . mysql_error());
        mysql_select_db($db,$con) or die("db selection failed");
         
        $user=$_POST['user'];
        $dish=$_POST['dish'];
	$quantity=$_POST['quantity'];
	$pno=$_POST['pno'];
	

       


        if($r=mysql_query("insert into orders(user,dish,quantity,pno) values('$user','$dish','$quantity','$pno') ",$con))
        {
                $flag["code"]=1;
                echo "ordered";
		$flag["message"]="Ordered successfully";

	}
	else
	{
		$flag["code"]=0;
		$flag["message"]="Order failed";
		die("insertion failed  : " . mysql_error());
	}

	print(json_encode($flag));
	mysql_close($con);
	
?>
