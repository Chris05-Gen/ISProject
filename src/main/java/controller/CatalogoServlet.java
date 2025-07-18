package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Genere;
import model.GenereDAO;
import model.Libro;
import model.LibroDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;



@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String genere = request.getParameter("genere");
        Integer idGenere = genere != null && !genere.isEmpty() ? Integer.parseInt(genere) : null;
        Integer minPagine = parseIntSafe(request.getParameter("minPagine"));
        Integer anno = parseIntSafe(request.getParameter("anno"));
        BigDecimal prezzoMin = parseBD(request.getParameter("prezzoMin"));
        BigDecimal prezzoMax = parseBD(request.getParameter("prezzoMax"));
        LibroDAO dao = new LibroDAO();
        try {
            List<Libro> lista = dao.cercaConFiltri(idGenere, anno, prezzoMin, prezzoMax, minPagine );
            GenereDAO gdao = new GenereDAO();
            List<Genere> generi = gdao.findAll();

            request.setAttribute("generi", generi);
            request.setAttribute("selectedGenere", idGenere);
            request.setAttribute("minPagine", minPagine);
            request.setAttribute("libri", lista);
            request.setAttribute("anno", anno);
            request.setAttribute("prezzoMin", prezzoMin);
            request.setAttribute("prezzoMax", prezzoMax);

            request.getRequestDispatcher("catalogo.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private Integer parseIntSafe(String value) {
        try { return value != null && !value.isEmpty() ? Integer.parseInt(value) : null; }
        catch (Exception e) { return null; }
    }

    private BigDecimal parseBD(String value) {
        try { return value != null && !value.isEmpty() ? new BigDecimal(value) : null; }
        catch (Exception e) { return null; }
    }
}

