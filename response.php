<?php
global $userid;
global $emergid;
$con = mysql_connect('mysql1.000webhost.com', 'a3785520_umair', 'password') or die ('MySQL Error.');
mysql_select_db('a3785520_mydb', $con) or die('MySQL Error.');

if (isset($_POST["myqueery"]) && $_POST["myqueery"]==9)
{



if (isset($_POST["email"]) && isset($_POST["password"]))   // checking if user exist and also finding the user's id
{
	$e= $_POST["email"];
	$p= $_POST["password"];

	$userinfo=mysql_query("SELECT * FROM users WHERE email = '$e' AND password = '$p'",$con) or die ('invlaid email or password');
	
	//$userinfo=mysql_query("SELECT * FROM users where email='$e' and password='$p' ",$con) or die ('invlaid email or password');

	if ($userinfo) 
	{
    $user = mysql_fetch_array($userinfo, MYSQL_ASSOC);
	$userid = $user['id'];
	$response["query"]=1;
	$response["data"]=$userid;
	}

	else
	{

	$user = mysql_fetch_array($userinfo, MYSQL_ASSOC);
	$userid = $user['id'];
	$response["query"]=0;
	$response["data"]=$userid;
	}


	//echo $userid;
	//echo "this is working fine";
	echo json_encode($response);
}

}



if (isset($_POST["myqueery"]))
{


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	if (($_POST["myqueery"])==0) // queery for viewing the emergency
	{
	$uid=mysql_query("SELECT * FROM users ORDER BY id DESC LIMIT 1",$con);
	if ($uid ==FALSE)
			{

		echo die (mysql_error());
			}
			$insid = mysql_fetch_array($uid, MYSQL_ASSOC);
			$result = $insid['id'];
			$theid=$result+1;
	if (isset($_POST["username"]) && isset($_POST["gender"]) && isset($_POST["password"]) && isset($_POST["email"]) && isset($_POST["cnic"]) && isset($_POST["phone"]))
	{
		$a=$theid;
		$b=$_POST["username"];
		$c=$_POST["gender"];
		$d=$_POST["password"];
		$e=$_POST["email"];
		$f=$_POST["cnic"];
		$g=$_POST["phone"];

		$res=mysql_query("INSERT INTO users(id, username, password, gender, email, cnic, phone) VALUES ('$a','$b','$d','$c','$e','$f','$g')",$con);
			if ($res ==FALSE)
				{

					echo die (mysql_error());
				}
$response['query']=1;
$response['data']="Database Updated";

echo json_encode($response);
	}			
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	elseif (($_POST["myqueery"])==1) // queery for viewing the emergency
	{
		//echo "user id is ".$userid;
	$emergencies=mysql_query('SELECT * FROM emergency',$con) or die ('invlaid email or password');
	$emergencydata = array();
       
	while($emergencyptr = mysql_fetch_array($emergencies, MYSQL_ASSOC)) 
		{
		$emergencydata[] = $emergencyptr;
		}
	
	//$output = json_encode(array($emergencydata));
	$respose['query']=1;
	$respose['data']=$emergencydata;
	//$respose['data']=$emergencydata;
	// $v['ds']=$output;
	echo json_encode($respose);

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	elseif (($_POST["myqueery"])==2) // for repoeting the emergency 
	{

echo "queery passed success";
		//http://localhost:8080/access.php?myqueery=2&email=umairmindfreak&password=1234&type=fire&description=bad&locationname=lahore&lattitude=1&longitude=2&edate=2014-01-01&etime=01%3A00
	if (isset($_POST["userid"]) && isset($_POST["type"])&& isset($_POST["description"])&& isset($_POST["locationname"])&& isset($_POST["lattitude"])&& isset($_POST["longitude"])&& isset($_POST["edate"]) && isset($_POST["peopleeffected"]))

		{

//echo "all parameter passed success";

			$a=$_POST["type"];
			$b=$_POST["description"];
			$c=$_POST["locationname"];
			$d=$_POST["lattitude"];
			$e=$_POST["longitude"];
			$f=$_POST["edate"];
			//$g=$_POST["etime"];
			$h=$_POST["userid"];
			$i=$_POST["peopleeffected"];
			
	$eid=mysql_query("SELECT * FROM emergency ORDER BY emergencyid DESC LIMIT 1",$con);
	if ($eid ==FALSE)
			{

		echo die (mysql_error());
			}
			$insid = mysql_fetch_array($eid, MYSQL_ASSOC);
			$result = $insid['emergencyid'];
			$emergid=$result+1;

//echo "get emgid success";
//echo $emergid;
		

			$insertion = mysql_query("INSERT INTO emergency(userid, emergencyid,emergencytype,description, locationname, lattitude, longitude,peopleeffected, edate) VALUES ('$h','$emergid','$a','$b','$c','$d','$e','$i','$f')",$con);
				if ($insertion ==FALSE)
				{

					echo die (mysql_error());
				}	
		}
$response['query']=1;
$response['data']="Database updated";
echo json_encode($response);

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	elseif (isset($_POST["myqueery"]) && $_POST["myqueery"]==3 && isset( $_POST["id"]))	// queery for viewing update/delete 
	 {
		//echo "hello";
$userid = $_POST["id"];
//echo $userid;
	$emergencies=mysql_query("SELECT * FROM emergency where userid='$userid'",$con) or die ('invlaid email or password');
	$emergencydata = array();
	while($emergencyptr = mysql_fetch_array($emergencies, MYSQL_ASSOC)) 
		{
		$emergencydata[] = $emergencyptr;
		}
	
         $respose['query']=1;
	$respose['data']=$emergencydata;

	
	echo json_encode($respose);

	if (isset($_POST["deleteid"]))
	{
		$del=$_POST["deleteid"];
		$deletion = mysql_query("DELETE FROM emergency WHERE emergencyid = '$del'",$con);

	}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}


?>					