<?php
  function getDB() {
    //TODO Grab all this from a file
    //Define sql database information
    $host="mysql.cs.iastate.edu";
    $port=3306;
    $socket="";
    $user="dbu309sab3";
    $password="SD0wFGqd";
    $dbname="db309sab3";

    //Connect to database
    $db = new mysqli($host, $user, $password, $dbname, $port, $socket) or die ('Could not connect to the database server' . mysqli_connect_error());

    //Pass to parent
    return $db;
  }
 ?>