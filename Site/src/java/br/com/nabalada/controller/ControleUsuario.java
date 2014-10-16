
package br.com.nabalada.controller;

import br.com.nabalada.model.Usuario;
import br.com.nabalada.persistence.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            if(request.getParameter("action").equalsIgnoreCase("atualizar")){
                int id = Integer.parseInt(request.getParameter("idUsuario"));
                UsuarioDAO dao = new UsuarioDAO();
                List<Usuario> lista;
                try {
                    lista = dao.listar();
                    for(Usuario usuario : lista){
                    if(usuario.getId() == id){
                        request.setAttribute("usuarioUsuario", usuario);
                        request.getRequestDispatcher("atualizaUsuario.jsp").forward(request, response);
                    }
                }
                } catch (Exception ex) {
                    request.setAttribute("msg", "Erro ao abrir pagina atualizaUsuario");
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
                
            }else if(request.getParameter("action").equalsIgnoreCase("excluir")){
                    UsuarioDAO dao = new UsuarioDAO();
                    try {
                        dao.deletar(Integer.parseInt(request.getParameter("idUsuario")));
                        request.setAttribute("msg", "Usuario excluido com sucesso!");
                        request.getRequestDispatcher("usuario.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("msg", "Usuario não excluido "+e.getMessage());
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
                    
                    
            }
        } catch (Exception e) {
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        }
    
        
            
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            if(request.getParameter("action").equalsIgnoreCase("cadastrar")){
                Usuario usu = new Usuario();
                
                usu.setNome(request.getParameter("nome"));
                usu.setUsuario(request.getParameter("usuario"));
                usu.setSenha(request.getParameter("senha"));
                usu.setEmail("email");
                
                try {
                    UsuarioDAO dao = new UsuarioDAO();
                    dao.cadastrar(usu);
                    request.setAttribute("msg", "Usuario cadastrado com sucesso");
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("msg", "Usuario não cadastrado");
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
            }else if(request.getParameter("action").equalsIgnoreCase("atualizar")){
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usu = new Usuario();
                
                usu.setId(Integer.parseInt(request.getParameter("id")));
                usu.setNome(request.getParameter("nome"));
                usu.setUsuario(request.getParameter("usuario"));
                usu.setSenha(request.getParameter("senha"));
                usu.setEmail(request.getParameter("email"));
                usu.setNivelAcesso(Integer.parseInt(request.getParameter("nivelAcesso")));
                
                try {
                    dao.atualizar(usu);
                    request.setAttribute("msg", "Usuario atualizado com sucesso!");
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                } catch (Exception ex) {
                    request.setAttribute("msg", "Usuario não atualizado "+ex.getMessage());
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        }
             
            
       
            
        
    }

}
