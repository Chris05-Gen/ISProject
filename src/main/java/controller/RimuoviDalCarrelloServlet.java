package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Carrello;
import model.CarrelloDAO;
import model.ContieneDAO;
import model.Utente;

import java.io.IOException;

@WebServlet("/RimuoviDalCarrelloServlet")
public class RimuoviDalCarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        CarrelloDAO carrelloDAO = new CarrelloDAO();
        ContieneDAO contieneDAO = new ContieneDAO();

        try {
            int carrelloId;

            if (utente != null) {
                Carrello carrello = carrelloDAO.findByUtenteId(utente.getId());
                if (carrello == null) {
                    response.sendRedirect("VisualizzaCarrelloServlet");
                    return;
                }
                carrelloId = carrello.getId();
            } else {
                Integer guestCarrelloId = (Integer) session.getAttribute("carrelloId");
                if (guestCarrelloId == null) {
                    response.sendRedirect("VisualizzaCarrelloServlet");
                    return;
                }
                carrelloId = guestCarrelloId;
            }

            int quantita = contieneDAO.getQuantitaLibro(carrelloId, isbn);

            if (quantita > 1) {
                contieneDAO.decrementaQuantitaLibro(carrelloId, isbn);
            } else {
                contieneDAO.rimuoviLibro(carrelloId, isbn);
            }
            session.setAttribute("successo", "Libro rimosso correttamente");
            response.sendRedirect("VisualizzaCarrelloServlet");

        } catch (Exception e) {
            e.printStackTrace();

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella rimozione dal carrello");
        }
    }
}