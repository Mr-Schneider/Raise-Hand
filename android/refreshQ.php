<?php
    //require_once 'sql.php';
	$host="mysql.cs.iastate.edu";
	$port=3306;
	$socket="";
	$user="dbu309sab3";
	$password="SD0wFGqd";
	$dbname="db309sab3";
	//Connect to database
	$db = new mysqli($host, $user, $password, $dbname, $port, $socket) or die ('Could not connect to the database server' . mysqli_connect_error());
	$topic_id=$_GET['topicID'];
	$stmt = "SELECT t.* FROM threads t WHERE t.topic_id='$topic_id' AND (TIMESTAMPDIFF(MINUTE, t.creation, NOW())) <= 10";
	$stmt = $db->query($stmt) or die($db->error);
	while($r= $stmt->fetch_array()){
            Echo 'NEWQUESTION ';
            Echo 'QUESTIONTITLE ';
            Echo ''.$r['title'].' ';
            Echo 'QUESTIONDESCRIPTION ';
            Echo ''.$r['description'].' ';
            Echo 'QUESTIONUSER ';
            Echo ''.$r['user_name'].' ';
            Echo 'QUESTIONUSERID ';
            Echo ''.$r['owner_id'].' ';
            Echo 'POINTS ';
            Echo ''.$r['points'].' ';
            Echo 'ENDORSED ';
            if($r['endorsed']==1){
                Echo 'Yes ';
            }
            else{
                Echo 'No ';
            }
            Echo 'CREATION ';
            Echo ''.$r['creation'].' ';
            Echo 'QUESTIONID ';
            Echo ''.$r['ID'].' ';
	}

?>