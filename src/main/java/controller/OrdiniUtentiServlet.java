package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



@WebServlet("/OrdiniUtentiServlet")
public class OrdiniUtentiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente admin = (Utente) request.getSession().getAttribute("utente");
        if (admin == null || !"Admin".equals(admin.getTipo())) {
            session.setAttribute("errore", "Indirizzo non valido.");
            response.sendRedirect("home");
            return;
        }

        OrdineDAO ordineDAO = new OrdineDAO();
        MetodoPagamentoDAO pagamentoDAO = new MetodoPagamentoDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

        try {
            List<Map<String, Object>> ordini = ordineDAO.getOrdiniConUtente();

            for (Map<String, Object> ordine : ordini) {
                int idMetodo = (int) ordine.get("idMetodoPagamento");
                int idIndirizzo = (int) ordine.get("idIndirizzo");

                MetodoPagamento mp = pagamentoDAO.getMetodoById(idMetodo);
                Indirizzo indirizzo = indirizzoDAO.getIndirizzoById(idIndirizzo);

                ordine.put("metodoPagamento", mp);
                ordine.put("indirizzo", indirizzo);
            }

            request.setAttribute("ordiniUtenti", ordini);
            request.getRequestDispatcher("ordiniUtenti.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("errore.jsp");
        }
    }
}
