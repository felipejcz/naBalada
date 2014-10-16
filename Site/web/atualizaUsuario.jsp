<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.nabalada.model.Evento, br.com.nabalada.persistence.EventoDAO, br.com.nabalada.model.Usuario" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Nabalada - Atualizar Usuario</title>
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
		<div id="main_centro" class="grid_12">
                    <div id="txt_centro">
                        <h2>Atualizar Usuario:</h2>
                        <form action="ControleUsuario?action=atualizar" method="POST">
                            <% 
                            Usuario usu = (Usuario)request.getAttribute("usuarioUsuario");
                            %>
                            <label for="id">ID:</label><input type="text" id="id" name="id" value="<%=usu.getId()%>" /><br>
                            <label for="nome">Nome:</label><input type="text" id="nome" name="nome" value="<%=usu.getNome()%>" /><br>
                            <label for="usuario">Login:</label><input type="text" id="usuario" name="usuario" value="<%=usu.getUsuario()%>" /><br>
                            <label for="senha">Senha:</label><input type="text" id="senha" name="senha" value="<%=usu.getSenha()%>" /><br>
                            <label for="email">Email:</label><input type="text" id="email" name="email" value="<%=usu.getEmail()%>" /><br>
                            <label for="nivelAcesso">Nivel de Acesso:</label><input type="text" id="nivelAcesso" name="nivelAcesso" value="<%=usu.getNivelAcesso()%>" /><br>
                            <input type="submit" value="Atualizar" />
                        </form>
                    </div>
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
