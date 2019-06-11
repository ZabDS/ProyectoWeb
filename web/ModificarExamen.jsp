<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <title>Modificar Examen</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no" />
        <script src="https://unpkg.com/react@latest/umd/react.development.js" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/react-dom@latest/umd/react-dom.development.js"></script>
        <script src="https://unpkg.com/@material-ui/core@latest/umd/material-ui.development.js" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/babel-standalone@latest/babel.min.js" crossorigin="anonymous"></script>
        <script type="text/babel">const User = "<%=(String) session.getAttribute("User")%>";</script>
        <script type="text/babel">const Pass = "<%=(String) session.getAttribute("Pass")%>";</script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
        <link rel="stylesheet" href="components/App.css" />
        <script type="text/babel" src="components/AppBar.js"></script>
    </head>
    <body>
        <div id="AppBar"></div>

        <div id="ModificarExamen"></div>


        <script type="text/javascript">
            var nombreDeExamen = "<%= (String) request.getAttribute("nombreDeExamen")%>";
            var preguntasEnExamen = <%= request.getAttribute("preguntasEnExamen")%>;
            var preguntasDisponibles = <%= request.getAttribute("preguntasDisponibles")%>;</script>
        <script type="text/babel" src="components/ModificarExamen.js"></script>
    </body>
</html>
