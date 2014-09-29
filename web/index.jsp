
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Logar:</h1>
        <form action="controle.jsp?action=logar" method="POST">
            <label for="txtUsuario">Usuario:</label><input type="text" name="txtUsuario" /><br>
            <label for="txtSenha">Senha:</label><input type="password" name="txtSenha" /><br>
            <input type="submit" value="Entrar" />
        </form><br>
        <font color="#FF0000">
        ${msg}
        <font>    
    </body>
</html>
