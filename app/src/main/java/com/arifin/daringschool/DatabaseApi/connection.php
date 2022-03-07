<?php

$connection = null;

try{
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "app-login-register-android";
    $dbms = "mysql";

    $database = "$mysql:dbname=$dbname;host-$host";
    $connection = new PDO($database, $username, $password);
    $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e){
    echo "Error ! " . $e ->getMessage();
    die;

} catch (PDOException $e){
    echo "Error ! " . $e->getMessage();
    die;
}
?>
