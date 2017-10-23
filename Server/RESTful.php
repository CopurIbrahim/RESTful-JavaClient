<?php

$option = $_GET['option'];

switch ($option) {
  case 1:
    $data = file_get_contents("./Modell.json");
    break;
  case 2:
      $data = file_get_contents("./Pakete.json");
      break;
  case 3:
    $data = file_get_contents("./Modell.xml");
    break;
  case 4:
    $data = file_get_contents("./Paket.xml");
    break;
  default:
    $data = "Fehler";
    break;
}


header('Content-type: application/json');
echo $data ;

 ?>
