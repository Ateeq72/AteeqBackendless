<?php
        $host='127.0.0.1';
        $uname='root';
        $pwd='laka';
        $db="rest";
	$flag = array();

        $con = mysql_connect($host,$uname,$pwd) or die("connection failed");
        mysql_select_db($db,$con) or die("db selection failed");
         
        $name=$_POST['name'];
        $email=$_POST['email'];
	$password=$_POST['password'];

        $flag['code']=0;
	$flag['message']="";

        if($r=mysql_query("insert into user(name,email,password) values('$name','$email','$password') ",$con))
        {
                $flag['code']=1;
                echo "Registered";
		$flag['message']="Registered successfully";

	}
	else
	{
		$flag['code']=0;
		$flag['message']="Registration failed";
	}

	print(json_encode($flag));
	mysql_close($con);
	
?>
