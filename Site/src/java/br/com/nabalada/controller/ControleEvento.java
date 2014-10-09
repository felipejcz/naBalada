/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nabalada.controller;

import br.com.nabalada.model.Evento;
import br.com.nabalada.persistence.EventoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class ControleEvento extends HttpServlet {

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
        
        request.getRequestDispatcher("evento.jsp").forward(request, response);       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
