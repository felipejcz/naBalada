<%@page import="br.com.nabalada.model.Usuario,br.com.nabalada.persistence.UsuarioDAO" %>

<%
    try{
        String action = request.getParameter("action");
            
            if(action.equalsIgnoreCase("logar")){
                
                String login_form = request.getParameter("txtUser"); // Pega o Login vindo do formulario
                String senha_form = request.getParameter("txtPass"); //Pega a senha vinda do formulario
                out.print(login_form);
                Usuario user = null;
                
                    UsuarioDAO dao = new UsuarioDAO(); //cria uma instancia do DAO usuarios                    
                    user = dao.getUser(login_form, senha_form);
               
                //se nao encontrou usuario no banco, redireciona para a pagina de erro!
                if ( user == null ) {
                    session.invalidate();
                    request.setAttribute("msg", "Usuario não cadastrado");
                    request.getRequestDispatcher("index.jsp" ).forward(request, response);
                }
                else{
                    //se o dao retornar um usuario, coloca o mesmo na sessao
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("index.jsp" ).forward(request, response);
                }
                    
            }
    
    }catch(NullPointerException e){
        request.setAttribute("msg", "Acesso negado");
        request.getRequestDispatcher("index.jsp" ).forward(request, response);
    }
    
%>
      
