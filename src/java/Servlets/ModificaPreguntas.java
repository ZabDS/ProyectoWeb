/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author nexus
 */
public class ModificaPreguntas extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    String path;
    String pathArchivo;
    Document doc;

    protected void inicializar(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.path = request.getRealPath("/") + "XML/PREGUNTAS/";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        String nombreDePregunta = (String) request.getParameter("nombre");
        pathArchivo = path + nombreDePregunta + ".xml";
        doc = generarDocumento();
        estructurarDeFormulario(nombreDePregunta);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        reemplazarPregunta();
    }

    protected void reemplazarPregunta() {
        try {
            eliminarPregunta(request.getParameter("nombreOriginal"));
            RequestDispatcher rs = request.getRequestDispatcher("AltaPregunta");
            rs.forward(request, response);
        } catch (Exception e) {
        }
    }

    protected void eliminarPregunta(String nombreDePregunta) {
        File file = new File(path + nombreDePregunta + ".xml");
        if (file.delete()) {
            System.out.println("200");
        } else {
            System.out.println("500");
        }
    }

    protected Document generarDocumento() {
        try {
            File archivoXML = new File(pathArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    protected void estructurarDeFormulario(String nombreDePregunta) {
        request.setAttribute("nombreDePregunta", atributoAString("nombreDePregunta"));
        request.setAttribute("enunciado", atributoAString("enunciado"));
        request.setAttribute("puntuacion", atributoAString("puntuacion"));
        String tipoDePregunta = atributoAString("tipoDePregunta");
        try {
            if (tipoDePregunta.equals("Algebraica")) {
                request.setAttribute("solucion", atributoAString("solucion"));
                RequestDispatcher rd = request.getRequestDispatcher("ModificarPreguntaAlgebraica.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("formula", atributoAString("formula"));
                RequestDispatcher rd = request.getRequestDispatcher("ModificarPreguntaCalculada.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
        }
        return;
    }

    protected String atributoAString(String atributo) {
        return doc.getElementsByTagName(atributo).item(0).getTextContent();
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
