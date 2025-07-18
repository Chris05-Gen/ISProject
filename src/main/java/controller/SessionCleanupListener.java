package controller;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import model.CarrelloDAO;

import java.sql.SQLException;

@WebListener
public class SessionCleanupListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Integer guestCarrelloId = (Integer) session.getAttribute("carrelloId");

        if (guestCarrelloId != null) {
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            try {
                carrelloDAO.eliminaCarrelloGuest(guestCarrelloId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
