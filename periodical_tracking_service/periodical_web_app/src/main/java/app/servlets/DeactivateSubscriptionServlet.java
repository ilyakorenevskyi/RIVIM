package app.servlets;

import app.DAOs.SubscriptionDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "deactivate", value = "/deactivate")
public class DeactivateSubscriptionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            SubscriptionDAO.deactivateSubscription(id);
            if (request.getParameter("type") != null) {
                response.sendRedirect(request.getContextPath() + "/admin?data=users");
            }
            response.sendRedirect(request.getContextPath() + "/subscriptions");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login");
            rd.forward(request, response);
        }
    }
}
