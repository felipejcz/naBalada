<%@page import="br.com.nabalada.model.Usuario,br.com.nabalada.persistence.UsuarioDAO" %>

<%
    try{
        String action = request.getParameter("action");
            if(action.equalsIgnoreCase("cadastrar")){
                Usuario usu = new Usuario();
                usu.setUsuario(request.getParameter("txtUsuario"));
                usu.setSenha(request.getParameter("txtSenha"));
                UsuarioDAO ud = new UsuarioDAO();
                try{
                    ud.cadastrar(usu);
                    request.setAttribute("msg", "Usuario cadastrado");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }catch(Exception e){
                    out.print("Erro: "+e.getMessage());
                }
            }
            
            if(action.equalsIgnoreCase("logar")){
                Usuario usu = new Usuario();
                usu.setUsuario(request.getParameter("txtUser"));
                usu.setSenha(request.getParameter("txtPass"));
                UsuarioDAO ud = new UsuarioDAO();
                try{
                    if(ud.validar(usu))
                    request.getRequestDispatcher("index2.jsp").forward(request, response);
                    else
                        request.setAttribute("msg", "Falha ao logar");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                }catch(Exception e){                   
                    out.print("Erro: "+e.getMessage());
                }
            }
    }catch(NullPointerException e){
        out.print("Acesso negado");
    }
    
%>
      
