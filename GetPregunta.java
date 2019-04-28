package Servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

    public class GetPregunta extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session= request.getSession();
            String Nombre,Texto,Formula,Tolerancia,Puntuacion,Autor;

            Nombre=(String)request.getParameter("Nombre");
            Texto=(String)request.getParameter("Texto");
            Formula=(String)request.getParameter("Formula");
            Puntuacion=(String)request.getParameter("Puntuacion");
            Autor=(String)session.getAttribute("User");
            
            PrintWriter out = response.getWriter();
            session.setAttribute("Nombre",Nombre);
            session.setAttribute("Texto",Texto);
            session.setAttribute("Formula",Formula);
            session.setAttribute("Puntuacion",Puntuacion);

                
            response.sendRedirect("AltaPregunta");   
    
    }
    }