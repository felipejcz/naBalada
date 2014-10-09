<%@page import="br.com.nabalada.model.Usuario, br.com.nabalada.persistence.UsuarioDAO" %>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  Usuario usu = (Usuario)session.getAttribute("user");
if (session.getAttribute("user") == null || usu.getNivelAcesso()==3){
    
%>
   
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Cadastro de Usuarios</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/main.css">

        <script src="js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
        
       
                <div class="container_12">
                    <div id="formLogin" class="grid_12">
                        <input type="button" value="Voltar" onclick="history.go(-1)"/>
                           
                    </div>
                </div>

       
    </body>
</html>
<% 
    }else{
    
    request.setAttribute("msg", "Sem permissão de acesso!");
    request.getRequestDispatcher("index.jsp").forward(request, response);
}

%>
