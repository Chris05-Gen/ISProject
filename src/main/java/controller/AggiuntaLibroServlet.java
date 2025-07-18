package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.GenereDAO;
import model.Libro;
import model.LibroDAO;
import model.Utente;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/AggiuntaLibro")
public class AggiuntaLibroServlet extends HttpServlet {
    private final LibroDAO libroDAO = new LibroDAO();
    private final GenereDAO genereDAO = new GenereDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente admin = (Utente) req.getSession().getAttribute("utente");
        if (admin == null || !"Admin".equals(admin.getTipo())) {
            resp.sendRedirect("unauthorized.jsp");
            return;
        }

        try {
            req.setAttribute("generi", genereDAO.findAll());
            req.getRequestDispatcher("aggiuntaLibro.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("errore.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");
        String titolo = req.getParameter("titolo");
        String autore = req.getParameter("autore");
        String casaEditrice = req.getParameter("casaEditrice");
        String pagineRaw = req.getParameter("pagine");
        String copertina = req.getParameter("copertina");
        String annoRaw = req.getParameter("annoPubblicazione");
        String prezzoRaw = req.getParameter("prezzo");
        String genereRaw = req.getParameter("idGenere");

        String errore = null;

        if (isbn == null || !isbn.matches("\\d{10,13}")) {
            errore = "ISBN non valido.";
        } else if (titolo == null || titolo.isBlank()) {
            errore = "Titolo mancante.";
        } else if (autore == null || autore.isBlank()) {
            errore = "Autore mancante.";
        } else if (pagineRaw == null || !pagineRaw.matches("\\d+")) {
            errore = "Numero di pagine non valido.";
        } else if (annoRaw == null || !annoRaw.matches("\\d{4}")) {
            errore = "Anno di pubblicazione non valido.";
        } else if (prezzoRaw == null || !prezzoRaw.matches("\\d+(\\.\\d{1,2})?")) {
            errore = "Prezzo non valido.";
        } else if (genereRaw == null || !genereRaw.matches("\\d+")) {
            errore = "Genere non selezionato.";
        }
    try {
        if (errore != null) {
            req.setAttribute("errore", errore);
            req.setAttribute("generi", genereDAO.findAll()); // Riporta i generi nella pagina
            req.getRequestDispatcher("aggiuntaLibro.jsp").forward(req, resp);
            return;
        }
    }catch (SQLException e) {
        e.printStackTrace();
        resp.sendRedirect("home.jsp");
        return;
    }

    Libro l = new Libro();
        l.setIsbn(req.getParameter("isbn"));
        l.setTitolo(req.getParameter("titolo"));
        l.setAutore(req.getParameter("autore"));
        l.setCasaEditrice(req.getParameter("casaEditrice"));
        l.setPagine(Integer.parseInt(req.getParameter("pagine")));
        l.setCopertina(req.getParameter("copertina"));
        l.setAnnoPubblicazione(Integer.parseInt(req.getParameter("annoPubblicazione")));
        l.setPrezzo(new BigDecimal(req.getParameter("prezzo")));
        l.setIdGenere(Integer.parseInt(req.getParameter("idGenere")));



        try {
            libroDAO.create(l);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("home");
            return;
        }
        resp.sendRedirect("AggiuntaLibro?success=1");
    }
}
