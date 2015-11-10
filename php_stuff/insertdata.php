<?php
        $host='127.0.0.1';
        $uname='root';
        $pwd='laka';
        $db="rest";

        $con = mysql_connect($host,$uname,$pwd) or die("connection failed");
        mysql_select_db($db,$con) or die("db selection failed");
         
        $user=$_REQUEST['user'];
        $dish=$_REQUEST['dish'];
	$quantity=$_REQUEST['quantity'];
	$pno=$_REQUEST['pno'];


        $flag['code']=0;

        if($r=mysql_query("insert into orders values('$user','$dish','$quantity',$pno') ",$con))
        {
                $flag['code']=1;
                echo "Order obtained";

	}

	print(json_encode($flag));
	mysql_close($con);
?>
