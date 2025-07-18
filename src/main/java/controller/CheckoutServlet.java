package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            resp.sendRedirect("home");
            return;
        }

        CarrelloDAO carrelloDAO = new CarrelloDAO();
        ContieneDAO contieneDAO = new ContieneDAO();
        LibroDAO libroDAO = new LibroDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAO();

        try {
            Carrello carrello = carrelloDAO.findByUtenteId(utente.getId());
            if (carrello == null) {
                carrello = carrelloDAO.createCarrello(utente.getId()); // crea uno nuovo se assente
            }

            List<Contiene> contenuti = contieneDAO.getContenuto(carrello.getId());


            List<Map<String, Object>> dettagliCarrello = new ArrayList<>();
            BigDecimal totale = BigDecimal.ZERO;

            for (Contiene c : contenuti) {
                Libro libro = libroDAO.findByISBN(c.getIsbn());

                BigDecimal subTotaleBD = libro.getPrezzo().multiply(BigDecimal.valueOf(c.getQuantita()));
                double subTotale = subTotaleBD.doubleValue();
                totale = totale.add(subTotaleBD);

                Map<String, Object> item = new HashMap<>();
                item.put("libro", libro);
                item.put("quantita", c.getQuantita());
                item.put("subTotale", subTotale);

                dettagliCarrello.add(item);
            }

            List<Indirizzo> indirizzi = indirizzoDAO.getIndirizziByUtente(utente.getId());

            List<MetodoPagamento> metodiPagamento = metodoPagamentoDAO.getAll();

            req.setAttribute("metodiPagamento", metodiPagamento);
            req.setAttribute("carrelloItems", dettagliCarrello);
            req.setAttribute("totale", totale.doubleValue());
            req.setAttribute("indirizzi", indirizzi);

            req.getRequestDispatcher("checkout.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il caricamento del checkout");
        }
    }
}
