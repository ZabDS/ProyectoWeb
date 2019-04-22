package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session= request.getSession();
            String USER,PASS;
            USER= (String)session.getAttribute("User");
            PASS= (String)session.getAttribute("Pass");
            
            PrintWriter out = response.getWriter(); 
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>"); 
                out.println("<title>Logear</title>");            
                out.println("</head>");
                out.println("<body>"); 
                out.println("USUARIO: "+USER); 
                out.println("<br>CONTRASEÑA: "+PASS); 
                out.println("</body>");
                out.println("</html>");
            session.setAttribute("A",USER);
            session.setAttribute("B",PASS);
    }
}