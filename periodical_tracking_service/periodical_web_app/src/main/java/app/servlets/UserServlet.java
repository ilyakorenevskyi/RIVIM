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

@WebServlet(name = "userInfo", value = "/userInfo")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String login = (String) session.getAttribute("login");
            System.out.println(login);
            Client client = new Client();
            client = ClientDAO.getUserInfo(login);
            String name = client.name;
            req.setAttribute("client", name);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/userPage.jsp");
            dispatcher.include(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }
    }
}
