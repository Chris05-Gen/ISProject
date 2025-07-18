package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Libro;
import model.LibroDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
;

@WebServlet("/AutocompleteServlet")
public class AutocompleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String term = request.getParameter("term");
        if (term == null || term.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            LibroDAO dao = new LibroDAO();
            List<Libro> libri = dao.cercaPerTitolo(term);

            Gson gson = new Gson();
            String json = gson.toJson(libri);
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

