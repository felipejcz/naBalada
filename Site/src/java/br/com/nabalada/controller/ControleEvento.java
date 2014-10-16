
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
                try {
                    dao.deletar(Integer.parseInt(request.getParameter("idEvento")));
                    request.setAttribute("msg", "Evento excluido!");
                    request.getRequestDispatcher("evento.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("msg", "Evento não excluido "+e.getMessage());
                    request.getRequestDispatcher("evento.jsp").forward(request, response);
                }
            
            }else if(request.getParameter("action").equalsIgnoreCase("atualizar")){
                int id = Integer.parseInt(request.getParameter("idEvento"));
                EventoDAO dao = new EventoDAO();
                List<Evento> lista;
                try {
                    lista = dao.listar();
                    for(Evento evento : lista){
                    if(evento.getId() == id){
                        request.setAttribute("eventoEvento", evento);
                        request.getRequestDispatcher("atualizaEvento.jsp").forward(request, response);
                    }
                }
                } catch (Exception ex) {
                    request.setAttribute("msg", "Erro ao abrir pagina atualizaEvento");
                    request.getRequestDispatcher("evento.jsp").forward(request, response);
                }
            }
            
        } catch (Exception e) {
            request.getRequestDispatcher("evento.jsp").forward(request, response);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            
            
            if(request.getParameter("action").equalsIgnoreCase("cadastrar")){
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
                    request.getRequestDispatcher("evento.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("msg", "Evento não cadastrado "+e.getMessage());
                    request.getRequestDispatcher("evento").forward(request, response);
                }
                
                
            }else if(request.getParameter("action").equalsIgnoreCase("atualizar")){
                Evento ev = new Evento();
                ev.setId(Integer.parseInt(request.getParameter("id")));
                ev.setTitulo(request.getParameter("titulo"));
                ev.setDescricao(request.getParameter("descricao"));
                ev.setLocal(request.getParameter("local"));
                ev.setData(request.getParameter("data"));
                ev.setHora(request.getParameter("hora"));
                ev.setFoto(request.getParameter("foto"));
                ev.setLocalizacao(request.getParameter("localizacao"));
                ev.setCriador(request.getParameter("criador"));
                if(request.getParameter("autorizado").equals("false")){
                    ev.setAutorizado(false);
                }else{
                    ev.setAutorizado(true);
                }
                ev.setModerador(request.getParameter("moderador"));
                ev.setComentario(request.getParameter("comentario"));
                
                try {
                    EventoDAO dao = new EventoDAO();
                    dao.atualizar(ev);
                    request.setAttribute("msg", "Evento atualizado com sucesso");
                    request.getRequestDispatcher("evento.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("msg", "Evento não foi atualizado "+e.getMessage());
                    request.getRequestDispatcher("evento.jsp").forward(request, response);
                }
            }
            
        } catch (Exception e) {
            request.getRequestDispatcher("evento.jsp").forward(request, response);
        }
    }

}
