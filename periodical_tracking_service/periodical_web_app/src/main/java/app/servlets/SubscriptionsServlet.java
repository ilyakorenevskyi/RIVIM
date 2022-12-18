package app.servlets;

import app.DAOs.ClientDAO;
import app.DAOs.SubscriptionDAO;
import app.entities.Client;
import app.entities.Subscription;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "subscriptions", value = "/subscriptions")
public class SubscriptionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            List<Subscription> subs = new ArrayList<>();
            String login = (String) session.getAttribute("login");
            Client user = null;
            user = ClientDAO.getUserInfo(login);
            if (user != null) {
                subs = SubscriptionDAO.getUserSubscriptions(user.getId());
            }
            req.setAttribute("subscriptionList", subs);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/subscriptionPage.jsp");
            dispatcher.include(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
