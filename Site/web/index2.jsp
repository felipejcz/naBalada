<%@page import="br.com.nabalada.model.Evento, br.com.nabalada.persistence.EventoDAO" %>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Home2</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/main.css">

        <script src="js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
        
        <header id="topo">
                <div class="container_12">
                    <div class="grid_12">
                        <img id="player" src="http://placehold.it/940x250">
                    </div>

                </div>
                <div class="container_12">
                    <div id="formLogin" class="grid_12">
                        ${msg}
                        <table>
                            <tr>
                                <td>
                                    <form action="controle.jsp?action=logar" method="POST">
                                        <fieldset>
                                            <label for="txtUser">Usuário:</label><input type="text" name="txtUser" class="txtboxUser" >
                                            <label for="txtPass">Senha:</label><input type="password" name="txtPass" class="txtboxPass">
                                            <input type="submit" class="botao" value="Entrar">
                                            <input type="button" class="botao" value="Registrar">
                                        </fieldset>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>

        </header>
        <div id="main" class="container_12">
            <hr class="espaco">
            <div class="grid_12">
                <section class="evento">
                    <a href="CadUsuario.jsp">Usuario</a>
                    <a href="CadEvento.jsp">Evento</a>
                    <h1>Eventos:</h1>
                    <table>
                        <tr>
                            <td>Id</td>
                            <td>Titulo</td>
                            <td>Descrição</td>
                            <td>Local</td>
                            <td>Data</td>
                            <td>Hora</td>
                        </tr>
                        <% 
                        try{
                            EventoDAO ed = new EventoDAO();
                            List<Evento> lista = new ArrayList<Evento>();
                            lista = ed.listar();
                            for(Evento e : lista){
                            %>
                            
                            <tr>
                                <td><%= e.getId()%></td>
                                <td><%= e.getTitulo()%></td>
                                <td><%= e.getDescricao()%></td>
                                <td><%= e.getLocal()%></td>
                                <td><%= e.getData()%></td>
                                <td><%= e.getHora()%></td>
                            </tr>
                            <%
                            }
                        }catch(Exception e){
                            out.print("Erro:"+e.getMessage());
                        }
                        %>
                        
                    </table>
                    
                    
                </section>
                
            </div>
        </div>

        <footer id="rodape">
            <div class="container_12">
                <hr class="espaco">
                <div class="grid_12">
                    <img class="rodape" src="http://placehold.it/940x150">
                </div>
            </div>
        </footer>
        
    </body>
</html>
