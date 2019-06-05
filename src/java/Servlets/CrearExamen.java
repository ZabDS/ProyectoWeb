package Servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
            String USER,PASS,Preguntas;
            String[] NPreguntas;
            int i=0;
            USER= (String)session.getAttribute("User");
            PASS= (String)session.getAttribute("Pass");
            Preguntas= (String)request.getParameter("Quests");
            
            NPreguntas=Preguntas.split(",");
            List<String> ANPreguntas = Arrays.asList(NPreguntas);
            PrintWriter out = response.getWriter();
            for(i=0;i<ANPreguntas.size();i++)
            out.println(ANPreguntas.get(i));
    }
}