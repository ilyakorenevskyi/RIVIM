package app.servlets;

import app.DAOs.ClientDAO;
import app.entities.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/registrationPage.jsp");
        dispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String error = null;
        int newId = -1;
        newId = ClientDAO.getMaxID() + 1;
        if (newId != -1) {
            Client newClient = new Client(newId, name);
            ClientDAO.addClient(newClient, login, password);
        }
        if (newId == -1) {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            out.write("<div class=\"content\">Something went wrong</div>");
            doGet(req, resp);
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", login);
            resp.sendRedirect("userInfo");
        }
    }
}
