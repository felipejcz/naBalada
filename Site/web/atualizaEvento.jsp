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
                        <form action="ControleEvento?action=atualizar" method="POST">
                            <%
                            Evento evento = (Evento) request.getAttribute("eventoEvento");
                            %>
                            <input type="hidden" id="id" name="id" value="<%=evento.getId()%>"/>
                            <label for="titulo">Titulo:</label><input type="text" id="titulo" name="titulo" value="<%=evento.getTitulo()%>" /><br>
                            <label for="descricao">Descrição:</label><input type="text" id="descricao" name="descricao" value="<%=evento.getDescricao()%>" /><br>
                            <label for="local">Local:</label><input type="text" id="local" name="local" value="<%=evento.getLocal()%>" /><br>
                            <label for="data">Data:</label><input type="text" id="data" name="data" value="<%=evento.getData()%>" /><br>
                            <label for="hora">Hora:</label><input type="text" id="hora" name="hora" value="<%=evento.getHora()%>" /><br>
                            <%
                            if(user.getNivelAcesso()==3){
                            %>
                            <label for="foto">Foto:</label><input type="text" id="foto" name="foto" value="<%=evento.getFoto()%>" /><br>
                            <label for="localizacao">Localização:</label><input type="text" id="localizacao" name="localizacao" value="<%=evento.getLocalizacao()%>" /><br>
                            <label for="criador">Criador:</label><input type="text" id="criador" name="criador" value="<%=evento.getCriador()%>" /><br>
                            <label for="autorizado">Autorizado:</label><input type="text" id="autorizado" name="autorizado" value="<%=evento.getAutorizado()%>" /><br>
                            <label for="moderador">Moderador:</label><input type="text" id="moderador" name="moderador" value="<%=evento.getModerador()%>" /><br>
                            <label for="comentario">Comentario:</label><input type="text" id="comentario" name="comentario" value="<%=evento.getComentario()%>" /><br>
                            <%
                            }
                            %>
                            <input type="submit" value="Atualizar"/>
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