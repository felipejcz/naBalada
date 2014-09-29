
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Usuario logado!</h1>
        <br>
        <h3>Cadastrar:</h3>
        <form action="controle.jsp?action=cadastrar" method="POST">
            <label for="txtUsuario">Usuario:</label><input type="text" name="txtUsuario" /><br>
            <label for="txtSenha">Senha:</label><input type="password" name="txtSenha" /><br>
            <input type="submit" value="Entrar" />
        </form><br>
    </body>
</html>
