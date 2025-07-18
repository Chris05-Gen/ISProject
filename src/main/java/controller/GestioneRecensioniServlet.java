package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Recensione;
import model.RecensioneDAO;
import model.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/GestioneRecensioniServlet")
public class GestioneRecensioniServlet extends HttpServlet {
    private final RecensioneDAO recensioneDAO = new RecensioneDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente admin = (Utente) request.getSession().getAttribute("utente");
        if (admin == null || !"Admin".equals(admin.getTipo())) {
            session.setAttribute("errore","Account non autorizzato");
            response.sendRedirect("home");
            return;
        }

        try {
            List<Recensione> recensioni = recensioneDAO.getAllRecensioniConNomeUtente();
            request.setAttribute("recensioni", recensioni);
            request.getRequestDispatcher("gestioneRecensioni.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errore","Errore nell'accesso alle recensioni");
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("idRecensione"));
        try {
            recensioneDAO.deleteRecensioneById(id);
        } catch (SQLException e) {
            session.setAttribute("errore","Errore nell'eliminazione della recensione");
            e.printStackTrace();
        }
        response.sendRedirect("GestioneRecensioniServlet");
    }
}
