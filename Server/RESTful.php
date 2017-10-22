<?php

$option = $_GET['option'];

if( $option == 1 ) {
  $data = [ 'a', 'b', 'c' ];
}
else {
  $data = [ 'name' => 'Golf 3', 'preis' => 61 ];
}


header('Content-type: application/json');
echo json_encode( $data );

 ?>
