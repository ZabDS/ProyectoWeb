package Servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
 
public class CrearExamen extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session= request.getSession();
            String USER,PASS;
            int validar=0;
            USER= (String)session.getAttribute("User");
            PASS= (String)session.getAttribute("Pass");
            
            PrintWriter out = response.getWriter(); 
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>"); 
                out.println("<title>MainMenu</title>");            
                out.println(""
                        + "<meta charset=\"utf-8\" />\n" +
                      "    <meta name=\"viewport\" content=\"minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no\" />\n" +
                      "    <script src=\"https://unpkg.com/react@latest/umd/react.development.js\" crossorigin=\"anonymous\"></script>\n" +
                      "    <script src=\"https://unpkg.com/react-dom@latest/umd/react-dom.development.js\"></script>\n" +
                      "    <script src=\"https://unpkg.com/@material-ui/core@latest/umd/material-ui.development.js\" crossorigin=\"anonymous\"></script>\n" +
                      "    <script src=\"https://unpkg.com/babel-standalone@latest/babel.min.js\" crossorigin=\"anonymous\"></script>\n" +
                      "    \n" +
                      "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500\" />\n" +
                      "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" />"
                        + "<link rel=\"stylesheet\" href=\"components/App.css\" />");            
                out.println("</head>");
                out.println("<body>");
                
              
                out.println("<script type=\"text/babel\">const User = '"+USER+"';</script>");
                out.println("<script type=\"text/babel\">const Pass = '"+PASS+"';</script>");
                out.println("<div id=\"AppBar\"></div>");                
                out.println("<div id=\"HacerExamen\"></div>");                
               
                out.println("<script type=\"text/babel\" src=\"components/HacerExamen.js\"></script>");
                out.println("<script type=\"text/babel\" src=\"components/Login.js\"></script>");
                out.println("<script type=\"text/babel\" src=\"components/LoginE.js\"></script>");
                out.println("<script type=\"text/babel\" src=\"components/AppBar.js\"></script>");
                out.println("</body>");
                out.println("</html>");
            session.setAttribute("user",USER);
            session.setAttribute("pass",PASS);
    }
}