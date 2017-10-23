<?php

$option = $_GET['option'];
$modelle = file_get_contents("./Modell.json");
$pakete = file_get_contents("./Pakete.json");

if( $option == 1 ) {
  $data = $modelle;
}
else {
  $data = $pakete;
}


header('Content-type: application/json');
echo $data ;

 ?>
