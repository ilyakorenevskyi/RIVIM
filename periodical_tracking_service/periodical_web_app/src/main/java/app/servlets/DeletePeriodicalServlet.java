package app.servlets;

import app.DAOs.PeriodicalsDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "deletePeriodical", value = "/deletePeriodical")
public class DeletePeriodicalServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("role").equals("admin")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String errorString = null;
            PeriodicalsDAO.deletePeriodical(id);
            if (errorString != null) {
                request.setAttribute("errorString", errorString);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/deletePeriodicalErrorView.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin");
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login");
            rd.forward(request, response);
        }
    }
}

