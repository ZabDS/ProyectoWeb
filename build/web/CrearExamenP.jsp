<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <title>Crear Examen</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no" />
    <script src="https://unpkg.com/react@latest/umd/react.development.js" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/react-dom@latest/umd/react-dom.development.js"></script>
    <script src="https://unpkg.com/@material-ui/core@latest/umd/material-ui.development.js" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/babel-standalone@latest/babel.min.js" crossorigin="anonymous"></script>
    
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" href="components/App.css" />
  </head>
  <body>
    <div id="HacerExamen"></div>
 

<script type="text/javascript">
var preguntasDisponibles = <%= request.getAttribute("preguntasDisponibles") %>;</script>
    <script type="text/babel" src="components/HacerExamen.js"></script>
  </body>
</html>
