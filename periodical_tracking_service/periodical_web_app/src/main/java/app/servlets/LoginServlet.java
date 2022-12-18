package app.servlets;

import app.DAOs.ClientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n = request.getParameter("login");
        String p = request.getParameter("password");

        if (ClientDAO.validate(n, p)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login", n);
            String role = ClientDAO.getUserRole(n);
            System.out.println(role);
            if (role == null) {
                out.print("<div class=\"content\">Sorry username or password error</div>");
                RequestDispatcher rd = request.getRequestDispatcher("views/loginView.jsp");
                rd.include(request, response);
            }
            else {
                session.setAttribute("role",role);
                if (role.equals("admin")) {
                    response.sendRedirect("admin");
                } else {
                    response.sendRedirect("userInfo");
                }
            }
        } else {
            out.print("<div class=\"content\">Sorry username or password error</div>");
            RequestDispatcher rd = request.getRequestDispatcher("views/loginView.jsp");
            rd.include(request, response);
        }

        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/loginView.jsp");
        rd.include(request, response);
    }
}