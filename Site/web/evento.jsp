<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.nabalada.model.Evento, br.com.nabalada.persistence.EventoDAO, br.com.nabalada.model.Usuario" %>
<%@page import="java.util.*"%>
<%  
if (session.getAttribute("user") != null){
    Usuario user = (Usuario)session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Nabalada - Eventos</title>
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
                        <h2><a href="ControleUsuario">Cad Usuario</a></h2>
                        
                    <table>
                            <legend>Eventos:</legend>
                             <tr>
                                 <%
                                    if(user.getNivelAcesso() == 1){//titulo,descricao,local,data,hora,criador,autorizado
                                 %>
                                <th>Titulo</th>
                                <th>Descrição</th>
                                <th>Local</th>
                                <th>Data</th>
                                <th>Hora</th>
                                <th>Criador</th>
                                <%}else if(user.getNivelAcesso() == 2){%>
                                <th>Titulo</th>
                                <th>Descrição</th>
                                <th>Local</th>
                                <th>Data</th>
                                <th>Hora</th>
                                <th>Criador</th>
                                <th>Autorizado</th>
                                <th>Moderador</th>
                                <th>Editar</th>
                                <%}else if(user.getNivelAcesso() == 3){%>
                                <th>Titulo</th>
                                <th>Descrição</th>
                                <th>Local</th>
                                <th>Data</th>
                                <th>Hora</th>
                                <th>Criador</th>
                                <th>Foto</th>
                                <th>Localização</th>
                                <th>Autorizado</th>
                                <th>Moderador</th>
                                <th>Comentario</th>
                                <th>Editar</th>
                                <th>Excluir</th>
                                <%
                                }
                                %>
                            </tr>
                            <%
                            EventoDAO dao = new EventoDAO();        
                            List<Evento> lista = (List<Evento>)dao.listar();
                            for(Evento evento : lista){
                            %>
                            <tr>
                            <input type="hidden" value="<%=evento.getId()%>" id="id" name="id" />
                                <td><%= evento.getTitulo()%></td>
                                <td><%= evento.getDescricao()%></td>
                                <td><%= evento.getLocal()%></td>
                                <td><%= evento.getData()%></td>
                                <td><%= evento.getHora()%></td>
                                <td><%= evento.getCriador()%></td>
                                <% 
                                if(user.getNivelAcesso()==2){
                                %>
                                <td><% if(evento.getAutorizado()==true){out.print("Sim");}else{out.print("Não");}%></td>
                                <td><%= evento.getModerador()%></td>
                                <td><a href="#">Atualizar</a></td>
                                <%
                                }
                                %>
                                
                                <% 
                                if(user.getNivelAcesso()==3){
                                %>
                                <td><%= evento.getFoto()%></td>
                                <td><%= evento.getLocalizacao()%></td>
                                <td><% if(evento.getAutorizado()==true){out.print("Sim");}else{out.print("Não");}%></td>
                                <td><%= evento.getModerador()%></td>
                                <td><%= evento.getComentario()%></td>
                                <td><a href="ControleEvento?action=atualizar&id=id">Atualizar</a></td>
                                <td><a href="ControleEvento?action=excluir&id=<%=evento.getId()%>">Excluir</a></td>
                                <%
                                }
                                %>
                                
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
<%
}else{
    request.setAttribute("msg", "Sem permissão de acesso");
    request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>