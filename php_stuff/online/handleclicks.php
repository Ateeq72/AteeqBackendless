<?php

        $host='mysql8.000webhost.com';
        $uname='a4387670_ateeq';
        $pwd='khader6';
        $db="a4387670_ateeq";
	$flag = array();
        


        $con = mysql_connect($host,$uname,$pwd) or die("connection failed" . mysql_error());
        mysql_select_db($db,$con) or die("db selection failed");
         
	if($r=mysql_query("truncate table orders",$con))
{
echo "\nOrders were Cleared! :)";
}
else {
echo "error occurred" . mysql_error();
}
 
?>

