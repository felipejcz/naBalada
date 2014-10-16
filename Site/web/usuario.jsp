<%@page import="br.com.nabalada.persistence.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.nabalada.model.Evento, br.com.nabalada.persistence.EventoDAO, br.com.nabalada.model.Usuario" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Nabalada - Usuarios</title>
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
                        <table>
                            <caption>Usuarios:</caption>
                            <tr>
                                <th>Nome</th>
                                <th>Usuario</th>
                                <th>Email</th>
                                <th>Atualizar</th>
                                <th>Excluir</th>
                            </tr>
                            <%
                            UsuarioDAO dao = new UsuarioDAO();
                            List<Usuario> lista = (List<Usuario>) dao.listar();
                            for(Usuario usuario : lista){
                            %>
                            <tr>
                                <td><%=usuario.getNome()%></td>
                                <td><%=usuario.getUsuario()%></td>
                                <td><%=usuario.getEmail()%></td>
                                <td><a href="ControleUsuario?action=atualizar&idUsuario=<%=usuario.getId()%>">Atualizar</a></td>
                                <td><a href="ControleUsuario?action=excluir&idUsuario=<%=usuario.getId()%>">Excluir</a></td>
                            </tr>
                            
                            <%
                            }
                            %>
                            
                        </table>
                    
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
