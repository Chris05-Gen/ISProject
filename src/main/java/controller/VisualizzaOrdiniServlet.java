package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/VisualizzaOrdiniServlet")
public class VisualizzaOrdiniServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null) {
            session.setAttribute("errore", "Utente inesistente");
            resp.sendRedirect("home");
            return;
        }

        int idUtente = u.getId();
        OrdineDAO ordineDAO = new OrdineDAO();
        MetodoPagamentoDAO pagamentoDAO = new MetodoPagamentoDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

        try {
            List<Ordine> ordini = ordineDAO.getOrdiniByUtente(idUtente);

            // Mappa Ordine ID → MetodoPagamento e Indirizzo
            Map<Integer, MetodoPagamento> mappaPagamenti = new HashMap<>();
            Map<Integer, Indirizzo> mappaIndirizzi = new HashMap<>();

            for (Ordine ordine : ordini) {
                MetodoPagamento pagamento = pagamentoDAO.getMetodoById(ordine.getIdMetodoPagamento());
                Indirizzo indirizzo = indirizzoDAO.getIndirizzoById(ordine.getIdIndirizzo());

                mappaPagamenti.put(ordine.getId(), pagamento);
                mappaIndirizzi.put(ordine.getId(), indirizzo);
            }

            req.setAttribute("ordini", ordini);
            req.setAttribute("mappaPagamenti", mappaPagamenti);
            req.setAttribute("mappaIndirizzi", mappaIndirizzi);

            RequestDispatcher dispatcher = req.getRequestDispatcher("ordini.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "Errore durante il recupero degli ordini");
        }
    }
}

