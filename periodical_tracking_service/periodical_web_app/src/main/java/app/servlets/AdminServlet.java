package app.servlets;

import app.DAOs.ClientDAO;
import app.DAOs.PeriodicalsDAO;
import app.DAOs.StatisticDAO;
import app.DAOs.SubscriptionDAO;
import app.entities.Client;
import app.entities.Periodical;
import app.entities.Statistic;
import app.entities.Subscription;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ind"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Periodical periodical = new Periodical(id, name, price);
        boolean success = false;
        try {
            success = PeriodicalsDAO.addPeriodical(periodical);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        if (success) {
            out.write("<div class=\"content\">Added successfuly </div>");
        } else {
            out.write("<div class=\"content\">Something went wrong</div>");
        }
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login") != null && session.getAttribute("role").equals("admin")) {
            String operation = request.getParameter("data");
            if (operation == null || operation.equals("periodicals")) {
                PrintWriter out = response.getWriter();
                String errorString = null;
                List<Periodical> list = null;
                list = PeriodicalsDAO.getPeriodicalList();
                request.setAttribute("periodicalList", list);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/adminPage.jsp");
                dispatcher.include(request, response);
            } else if (operation.equals("users")) {
                PrintWriter out = response.getWriter();
                String errorString = null;
                List<Client> list = null;
                list = ClientDAO.getUserList();
                request.setAttribute("userList", list);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/adminUserPage.jsp");
                dispatcher.include(request, response);
            } else if (operation.equals("manageUser")) {
                String userId = request.getParameter("id");
                if (userId == null) {
                    response.sendRedirect(request.getContextPath() + "/admin?data=users");
                } else {
                    PrintWriter out = response.getWriter();
                    String errorString = null;
                    List<Subscription> subs = new ArrayList<>();
                    subs = SubscriptionDAO.getUserSubscriptions(Integer.parseInt(userId));
                    request.setAttribute("subsList", subs);
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/adminManageSubsPage.jsp");
                    dispatcher.include(request, response);
                }
            } else if (operation.equals("statistic")) {
                PrintWriter out = response.getWriter();
                Statistic statistic = null;
                try {
                    statistic = StatisticDAO.getSystemStat();
                } catch (ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("stat", statistic);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/adminStatistic.jsp");
                dispatcher.include(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login");
            rd.forward(request, response);
        }
    }
}
