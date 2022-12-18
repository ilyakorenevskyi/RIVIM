package app.servlets;

import app.DAOs.ClientDAO;
import app.DAOs.PaymentDAO;
import app.DAOs.PeriodicalsDAO;
import app.DAOs.SubscriptionDAO;
import app.entities.Client;
import app.entities.Payment;
import app.entities.Periodical;
import app.entities.Subscription;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "subscribe", value = "/subscribe")
public class SubscribeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String login = (String) session.getAttribute("login");
            System.out.println(login);
            Client user = null;
            user = ClientDAO.getUserInfo(login);
            if (user != null) {
                int periodicalID = Integer.parseInt(req.getParameter("id"));
                Periodical periodical = null;
                periodical = PeriodicalsDAO.getPeriodicalById(periodicalID);
                if (periodical != null) {
                    req.getSession().setAttribute("user", user);
                    req.getSession().setAttribute("periodical", periodical);
                    req.setAttribute("periodical", periodical);
                    req.setAttribute("user", user);
                    RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/subscribePage.jsp");
                    dispatcher.include(req, resp);
                }
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("login");
                rd.forward(req, resp);
            }
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Client user = (Client) session.getAttribute("user");
            Periodical periodical = (Periodical) session.getAttribute("periodical");
            Subscription sub = null;
            boolean done = false;
            if (user != null && periodical != null) {
                int period = Integer.parseInt(req.getParameter("period"));
                sub = SubscriptionDAO.subscribe(user.getId(), periodical.getId(), period);
            }
            if (sub == null) {
                RequestDispatcher dispatcher = req.getServletContext()
                        .getRequestDispatcher("/views/errorPage.jsp");
                dispatcher.forward(req, resp);
            } else {
                Payment res = null;
                try {
                    res = PaymentDAO.createPayment(sub);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (res != null) {
                    req.setAttribute("payment", res);
                    RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/paymentPage.jsp");
                    dispatcher.include(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getServletContext()
                            .getRequestDispatcher("/views/errorPage.jsp");
                    dispatcher.forward(req, resp);
                }
            }
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }
    }
}
