<?php
include '../../utilities/database.php';

  session_start();
  //Check if user is logged in
  if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
    if($_SESSION['role'] != 2) {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Not Permitted";
      header("Location: ../../login.php");
      die("oops");
    }
  } else {
    $_SESSION['error'] = true;
    $_SESSION['errorCode'] = "Session Expired";
    header("Location: ../../login.php?event=logout");
  }

  //Get the db Referance
  $db = getDB();

  //If specified, add a TA
  $taAdd = false;
  if(!("" == trim($_POST['newTA']))) {
    $ta = $_POST['newTA'];
    $query = "SELECT ID FROM users WHERE username = '$ta'";
    $result = $db->query($query) or die($db->error);
    $ta = $result->fetch_assoc();
    $taID = $ta['ID'];

    $addClass = "INSERT INTO userClasses
                  (relationship,
                  user_id,
                  class_id)
                  VALUES
                  (3,
                  " . $taID . ",
                  " . $_GET['class'] . ");
                  ";

    $result = $db->query($addClass) or die($db->error);
    $taAdd = true;
  }


  //Make sure everything is there and then create the class

  //Our update string
  $updateString = "UPDATE classes SET ";

  //First change marker
  $first = 1;

  //Build thr string based on things that were changed
  if(!("" == trim($_POST['name']))) {
    $updateString = $updateString . "class_name = '" . $_POST['name'] . "'";
    $first = 0;
  }
  if(!("" == trim($_POST['startDate']))) {
    if($first) {
      $updateString = $updateString . "class_start = '" . $_POST['startDate'] . "'";
      $first = 0;
    }
    else {
      $updateString = $updateString . ", class_start = '" . $_POST['startDate'] . "'";
    }
  }
  if(!("" == trim($_POST['endDate']))) {
    if($first) {
      $updateString = $updateString . "class_end = '" . $_POST['endDate'] . "'";
      $first = 0;
    }
    else {
      $updateString = $updateString . ", class_end = '" . $_POST['endDate'] . "'";
    }
  }
  if(!("" == trim($_POST['startTime']))) {
    if($first) {
      $updateString = $updateString . "class_time_start = '" . $_POST['startTime'] . "'";
      $first = 0;
    }
    else {
      $updateString = $updateString . ", class_time_start = '" . $_POST['startTime'] . "'";
    }
  }
  if(!("" == trim($_POST['endTime']))) {
    if($first) {
      $updateString = $updateString . "class_end_time = '" . $_POST['endTime'] . "'";
      $first = 0;
    }
    else {
      $updateString = $updateString . ", class_end_time = '" . $_POST['endTime'] . "'";
    }
  }
  if(!("" == trim($_POST['meetingsPerWeek']))) {
    if($first) {
      $updateString = $updateString . "times_met_per_week = " . $_POST['meetingsPerWeek'];
      $first = 0;
    }
    else {
      $updateString = $updateString . ", times_met_per_week = " . $_POST['meetingsPerWeek'];
    }
  }
  if(!("" == trim($_POST['description']))) {
    if($first) {
      $updateString = $updateString . "description= '" . $_POST['description'] . "'";
      $first = 0;
    }
    else {
      $updateString = $updateString . ", description = '" . $_POST['description'] . "'";
    }
  }
  if($first) {
    if($taAdd) {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "Teaching Assistant Added";
      header("Location: ../pages.php?class=" . $_GET['class'] . "&page=classSettings");
      die("done");
    }
    else {
      $_SESSION['error'] = true;
      $_SESSION['errorCode'] = "No Updates";
      header("Location: ../pages.php?class=" . $_GET['class'] . "&page=classSettings");
      die("done");
    }
  }

  //Cap off the query
  $updateString = $updateString . " WHERE ID = " . $_GET['class'];

  //Send update to db
  $result = $db->query($updateString) or die($db->error);



  $_SESSION['error'] = true;
  $_SESSION['errorCode'] = "Class Updated";
  header("Location: ../pages.php?class=" . $_GET['class'] . "&page=classSettings");
  die("done");

?>