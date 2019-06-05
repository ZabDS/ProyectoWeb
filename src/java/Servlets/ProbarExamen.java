package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
 
    public class ProbarExamen extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session= request.getSession();
            String PreguntasDisponibles,PreguntasExamen;
            String USER= (String)session.getAttribute("User");
            //OBTENER LISTA DE PREGUNTAS
            String path = request.getRealPath("/") + "XML/PREGUNTAS/";
            ArrayList<String> nombreDePreguntas = generarListaDePreguntas(path);
            //FILTRAR PREGUNTAS
            
            //LLAMAR A COMPONENTE
            PrintWriter out = response.getWriter(); 
            PreguntasDisponibles=convertirAArregloJS(nombreDePreguntas.toArray(new String[0]));    
                
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
                out.println("<script type=\"text/babel\">var PreguntasE = "+PreguntasDisponibles+";</script>");
                out.println("<div id=\"AppBar\"></div>");                
                out.println("<div id=\"ProbarExamen\"></div>");                
               
                out.println("<script type=\"text/babel\" src=\"components/ProbarExamen.js\"></script>");
                out.println("<script type=\"text/babel\" src=\"components/AppBar.js\"></script>");
                out.println("</body>");
                out.println("</html>");
    }
    
    protected ArrayList<String> generarListaDePreguntas(String path) {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        final File carpeta = new File(path);
        for (final File fileEntry : carpeta.listFiles()) {
            String nombre = fileEntry.getName();
            String nombreSinExtension = nombre.substring(0, nombre.length() - 4);
            nombrePreguntas.add(nombreSinExtension);
        }
        return nombrePreguntas;
    }
    
     protected ArrayList<String> FiltrarPreguntas(ArrayList<String> Disponibles,String XMLName) {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        
        return nombrePreguntas;
    }
    
    public static String convertirAArregloJS(String[] arr) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("\"").append(arr[i]).append("\"");
            if (i + 1 < arr.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}