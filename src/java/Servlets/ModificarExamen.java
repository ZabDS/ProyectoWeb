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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author nexus
 */
public class ModificarExamen extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    String pathPreguntas;
    String pathExamen;
    Document doc;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        String nombreDeExamen = (String) request.getParameter("nombre");
        pathExamen = request.getRealPath("/") + "XML/EXAMENES/" + nombreDeExamen + ".xml";
        doc = generarDocumento();
        
        establecerAtributos();
        RequestDispatcher rd = request.getRequestDispatcher("ModificarExamen.jsp");

        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        reemplazarExamen();
    }

    protected void reemplazarExamen() {
        try {
            eliminarExamen(request.getParameter("nombreOriginal"));
            RequestDispatcher rs = request.getRequestDispatcher("CrearExamen");
            rs.forward(request, response);
        } catch (Exception e) {
        }
    }

    protected void establecerAtributos() {
        ArrayList<String> nombreDePreguntas = generarListaDePreguntas();
        ArrayList<String> preguntasEnExamen = conseguirPreguntasEnExamen();
        request.setAttribute("preguntasEnExamen", convertirAArregloJS(preguntasEnExamen.toArray(new String[0])));
        request.setAttribute("preguntasDisponibles", convertirAArregloJS(nombreDePreguntas.toArray(new String[0])));
        request.setAttribute("nombreDeExamen", request.getParameter("nombre"));

    }

    protected ArrayList<String> conseguirPreguntasEnExamen() {
        ArrayList<String> preguntasEnExamen = new ArrayList<String>();
        Element elementoRoot = doc.getDocumentElement();
        NodeList listaDeNodosDePreguntas = elementoRoot.getElementsByTagName("Pregunta");
        if (listaDeNodosDePreguntas != null && listaDeNodosDePreguntas.getLength() > 0) {
            for (int i = 0; i < listaDeNodosDePreguntas.getLength(); i++) {
                Node nodoTemp = listaDeNodosDePreguntas.item(i);
                preguntasEnExamen.add(nodoTemp.getTextContent());
            }
        }
        return preguntasEnExamen;
    }

    protected Document generarDocumento() {
        try {
            File archivoXML = new File(pathExamen);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    protected void inicializar(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.pathPreguntas = request.getRealPath("/") + "XML/PREGUNTAS/";
    }

    protected ArrayList<String> generarListaDePreguntas() {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        final File carpeta = new File(this.pathPreguntas);
        for (final File fileEntry : carpeta.listFiles()) {
            String nombre = fileEntry.getName();
            String nombreSinExtension = nombre.substring(0, nombre.length() - 4);
            nombrePreguntas.add(nombreSinExtension);
        }
        return nombrePreguntas;
    }

    protected void eliminarExamen(String nombreDeExamen) {
        File file = new File(request.getRealPath("/") + "XML/EXAMENES/" + nombreDeExamen + ".xml");
        if (file.delete()) {
            System.out.println("200");
        } else {
            System.out.println("500");
        }
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
