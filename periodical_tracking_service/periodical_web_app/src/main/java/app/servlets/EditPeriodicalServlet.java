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

@WebServlet(name = "editPeriodical", value = "/editPeriodical")
public class EditPeriodicalServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Periodical periodical = new Periodical(id, name, price);
        String errorString = null;
        try {
            PeriodicalsDAO.editPeriodical(periodical);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/deletePeriodicalErrorView.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("admin");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("role").equals("admin")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String errorString = null;
            Periodical periodical = null;
            periodical = PeriodicalsDAO.getPeriodicalById(id);
            request.setAttribute("periodical", periodical);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/editPeriodical.jsp");
            dispatcher.include(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login");
            rd.forward(request, response);
        }
    }
}