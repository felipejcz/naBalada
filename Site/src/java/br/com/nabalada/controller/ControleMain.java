
package br.com.nabalada.controller;

import br.com.nabalada.model.Evento;
import br.com.nabalada.model.Usuario;
import br.com.nabalada.persistence.EventoDAO;
import br.com.nabalada.persistence.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControleMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
            EventoDAO ed = new EventoDAO();
            List<Evento> lista = new ArrayList<Evento>();
            lista = ed.listar();
            request.setAttribute("listaEvento", lista);
        } catch (Exception e) {
            System.out.print("Lista de eventos não carregada");
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);   
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
            
        try{
        
            if(action.equalsIgnoreCase("logar")){
                
                HttpSession session = request.getSession(); //obtem a sessao do usuario, caso exista
 
                Usuario user = null;
                String login = request.getParameter("txtUser"); // Pega o Login vindo do formulario
                String senha = request.getParameter("txtPass"); //Pega a senha vinda do formulario

                try {
                    UsuarioDAO dao = new UsuarioDAO(); //cria uma instancia do DAO usuario
                    user = dao.getUser(login, senha);
                }
                catch ( Exception e ){

                }

                //se nao encontrou usuario no banco, redireciona para a pagina de erro!
                if ( user == null ) {
                    session.invalidate();
                    request.setAttribute("msg", "Erro ao logar");
                    request.getRequestDispatcher("index.jsp" ).forward(request, response);
                }
                else{
                    //se o dao retornar um usuario, coloca o mesmo na sessao
                    session.setAttribute("user", user);
                    
                    if(user.getNivelAcesso()==1){
                        request.setAttribute("msg", "Bem vindo(a) "+user.getNome());
                    }else if(user.getNivelAcesso()==2){
                        request.setAttribute("msg", "Bem vindo(a) Mod "+user.getNome());
                    }else if(user.getNivelAcesso()==3){
                        request.setAttribute("msg", "Bem vindo(a) Admin "+user.getNome());
                    }
                    
                    request.getRequestDispatcher("index.jsp" ).forward(request, response);
                }
            
            
            }else if(action.equalsIgnoreCase("logoff")){
                HttpSession session = request.getSession();
                session.invalidate();
                request.setAttribute("msg", "Usuario deslogado!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
    
    }catch(NullPointerException e){
        request.setAttribute("msg", "Acesso negado");
        request.getRequestDispatcher("index.jsp" ).forward(request, response);
    }
    }


}
