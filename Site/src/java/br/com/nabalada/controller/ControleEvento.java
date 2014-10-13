
package br.com.nabalada.controller;

import br.com.nabalada.model.Evento;
import br.com.nabalada.model.Usuario;
import br.com.nabalada.persistence.EventoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControleEvento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            if(request.getParameter("action").equalsIgnoreCase("excluir")){
            EventoDAO dao = new EventoDAO(); 
            dao.deletar(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("msg", "Evento excluido!");
            }else{
                request.setAttribute("msg", "Falha ao excluir evento!");
            }
        } catch (Exception e) {
        }
        
        request.getRequestDispatcher("evento.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
        
        String action = request.getParameter("action");
            
            if(action.equalsIgnoreCase("cadastrar")){
                Evento ev = new Evento();
                EventoDAO dao = new EventoDAO();
                
                HttpSession session = request.getSession();
                Usuario usu = (Usuario) session.getAttribute("user");

                ev.setId(0);
                ev.setTitulo(request.getParameter("titulo"));
                ev.setDescricao(request.getParameter("descricao"));
                ev.setLocal(request.getParameter("local"));
                ev.setData(request.getParameter("data"));
                ev.setHora(request.getParameter("hora"));
                ev.setCriador(usu.getUsuario());
                ev.setAutorizado(false);

                try {
                    dao.cadastrar(ev);
                    request.setAttribute("msg", "Evento cadastrado com sucesso");
                    request.getRequestDispatcher("cadEvento.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("msg", "Evento não cadastrado");
                    request.getRequestDispatcher("ControleEvento").forward(request, response);
                }
                
                
            } 
            
        } catch (Exception e) {
            request.setAttribute("msg", "Erro: "+e.getMessage());
            request.getRequestDispatcher("cadEvento.jsp").forward(request, response);
        }
    }

}
