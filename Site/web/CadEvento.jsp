<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.nabalada.model.Evento, br.com.nabalada.persistence.EventoDAO, br.com.nabalada.model.Usuario" %>
<%@page import="java.util.*"%>
<%
    if(session.getAttribute("user")!=null){
    Usuario user = (Usuario)session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Nabalada - Cadastro de Eventos</title>
	<meta charset="utf-8">

	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/grid.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">

        <script src="js/modernizr-2.6.2.min.js"></script>
</head>
<body>

<header>
	<div class="container_12">
		<div id="topo" class="grid_12">
		</div>
	<div class="container_12">
		<div id="login" class="grid_12">
                    
                    <span class="msg">${msg}</span>
                    <form action="ControleMain?action=logoff" method="POST">
                        <input type="submit" class="logoff" value="Logoff" />
                    </form>
                </div>
	</div>
	</div>
	<div class="container_12">
		<div id="menu" class="grid_12">
			<nav>
				<ul>
					<li><a href="ControleMain">Inicial</a></li>
					<li><a href="ControleEvento">Eventos</a></li>
				</ul>
			</nav>
		</div>
	</div>

</header>

<article>
	<div id="main" class="container_12">
		<div id="main_esquerdo" class="grid_2">
		</div>
		<div id="main_centro" class="grid_8">
                    <div id="txt_centro">
                        <h2>Cadastro de Evento:</h2>
                        <form action="ControleEvento?action=cadastrar" method="POST">
                            <label for="titulo">Titulo:</label><input type="text" id="titulo" name="titulo" /><br>
                            <label for="descricao">Descrição:</label><input type="text" id="descricao" name="descricao" /><br>
                            <label for="local">Local:</label><input type="text" id="local" name="local" /><br>
                            <label for="data">Data:</label><input type="text" id="data" name="data" /><br>
                            <label for="hora">Hora:</label><input type="text" id="hora" name="hora" /><br>
                            <input type="submit" value="Cadastrar"/>
                        </form>
                    </div>
		</div>
		<div id="main_direito" class="grid_2">
		</div>
	</div>
	
</article>

<footer>
	<div class="container_12">
		<div id="rodape" class="grid_12">
		</div>
	</div>
</footer>

</body>
</html>
<%
    }else{
        request.setAttribute("msg", "Acesso não permitido!");
    }
%>