package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Libro;
import model.LibroDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

            JSONArray array = new JSONArray();

            for (Libro l : libri) {
                JSONObject obj = new JSONObject();
                obj.put("titolo", l.getTitolo());
                obj.put("isbn", l.getIsbn());
                array.add(obj);
            }

            response.getWriter().write(array.toJSONString());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

