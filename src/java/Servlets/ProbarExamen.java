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

    HttpServletRequest request;
    HttpServletResponse response;
    String pathExamen;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("ProbarExamen.jsp");
        request.setAttribute("preguntasDisponibles", "d");
        rd.forward(request, response);
    }

    protected void inicializar(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        String nombreDeExamen = (String) request.getParameter("nombre");
        this.pathExamen = request.getRealPath("/") + "XML/EXAMENES/" + nombreDeExamen + ".xml";

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

    protected ArrayList<String> FiltrarPreguntas(ArrayList<String> Disponibles, String XMLName) {
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
