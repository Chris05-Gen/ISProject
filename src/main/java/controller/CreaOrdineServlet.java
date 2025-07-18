package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/CreaOrdineServlet")
public class CreaOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        if (u == null) {
            response.sendRedirect("home");
            return;
        }

        String indirizzoRaw = request.getParameter("idIndirizzo");
        String metodoRaw = request.getParameter("metodoPagamento");

        if (indirizzoRaw == null || metodoRaw == null) {
            session.setAttribute("errore", "Seleziona indirizzo e metodo di pagamento.");
            response.sendRedirect("CheckoutServlet");
            return;
        }

        int idIndirizzo;
        int idMetodo;
        try {
            idIndirizzo = Integer.parseInt(indirizzoRaw);
            idMetodo = Integer.parseInt(metodoRaw);
        } catch (NumberFormatException e) {
            session.setAttribute("errore", "Valori non validi per indirizzo o metodo di pagamento.");
            response.sendRedirect("CheckoutServlet");
            return;
        }
        try {
            IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
            if (!indirizzoDAO.AppartieneA(idIndirizzo, u.getId())) {
                session.setAttribute("errore", "Indirizzo non valido.");
                response.sendRedirect("CheckoutServlet");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore DB indirizzo");
            return;
        }
        try {
            MetodoPagamentoDAO mpDAO = new MetodoPagamentoDAO();
            if (!mpDAO.Esiste(idMetodo)) {
                session.setAttribute("errore", "Metodo di pagamento non valido.");
                response.sendRedirect("CheckoutServlet");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errore", "Errore DB indirizzo");
            response.sendRedirect("CheckoutServlet");
            return;
        }


        try {
            int idUtente = u.getId();

            Carrello carrello = new CarrelloDAO().findByUtenteId(idUtente);
            List<Contiene> items = new ContieneDAO().getContenuto(carrello.getId());

            if (items.isEmpty()) {
                session.setAttribute("errore", "Carrello vuoto.");
                response.sendRedirect("VisualizzaCarrelloServlet");
                return;
            }

            BigDecimal totale = BigDecimal.ZERO;
            for (Contiene c : items) {
                BigDecimal prezzo = new LibroDAO().getPrezzoByIsbn(c.getIsbn());
                BigDecimal sub = prezzo.multiply(BigDecimal.valueOf(c.getQuantita()));
                totale = totale.add(sub);
            }

            Ordine ordine = new Ordine(0, idUtente, idIndirizzo, idMetodo,
                    new Timestamp(System.currentTimeMillis()), totale);
            int nuovoId = new OrdineDAO().creaOrdine(ordine);

            new ContieneDAO().svuotaCarrello(carrello.getId());

            session.setAttribute("successo", "Acquisto effettuato! Ordine #" + nuovoId);
            response.sendRedirect("home");

        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errore", "Errore DB indirizzo");
            response.sendRedirect("home");
            return;
        }

    }
}


