package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
    public class GetUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session= request.getSession();
            String A,B,C,Det;
            A=(String)request.getParameter("User");
            B=(String)request.getParameter("Pass");
            
            PrintWriter out = response.getWriter(); 
            session.setAttribute("User",A);
            session.setAttribute("Pass",B);
            response.sendRedirect("Login");
    }
}