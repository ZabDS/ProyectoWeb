/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nexus
 */
public class ModificaPreguntas extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRealPath("/") + "XML/PREGUNTAS/";
        ArrayList<String> nombreDePreguntas = generarListaDePreguntas(path);
        RequestDispatcher rd = request.getRequestDispatcher("ModificarPregunta.jsp");
        request.setAttribute("preguntasDisponibles", convertirAArregloJS(nombreDePreguntas.toArray(new String[0])));
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRealPath("/") + "XML/PREGUNTAS/";
        String nombreDePregunta = request.getParameter("Nombre");
        String accion = request.getParameter("submitButton");
        if ("Modificar".equals(accion)) {
                        eliminarPregunta(path, nombreDePregunta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaPregunta");
            dispatcher.forward(request, response);

        } else if ("Borrar".equals(accion)) {
            eliminarPregunta(path, nombreDePregunta);
            response.sendRedirect("Login");

        }
    }

    protected void eliminarPregunta(String path, String nombreDePregunta) {
        File file = new File(path + nombreDePregunta + ".xml");
        if (file.delete()) {
            System.out.println("200");
        } else {
            System.out.println("500");
        }
    }

    protected void modificarXML(String nombrePregunta, String enunciado, String puntuacion, String formula) {
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
