package app.servlets;

import app.DAOs.PeriodicalsDAO;
import app.entities.Periodical;

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

@WebServlet(name = "viewPeriodicals", value = "/viewPeriodicals")
public class PeriodicalsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            List<Periodical> periodicals = new ArrayList<>();
            String error = null;
            periodicals = PeriodicalsDAO.getPeriodicalList();
            System.out.println(periodicals);
            System.out.println(error);
            if (error == null) {
                req.setAttribute("periodicalList", periodicals);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/periodicalsPage.jsp");
                dispatcher.include(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getServletContext()
                        .getRequestDispatcher("/views/errorPage.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
