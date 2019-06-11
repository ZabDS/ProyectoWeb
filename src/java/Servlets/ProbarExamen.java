package Servlets;

import Interfaces.IPregunta;
import Objetos.PreguntaAlgebraica;
import Objetos.PreguntaCalculada;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class ProbarExamen extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    String pathExamen;
    Document doc;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        cargarPreguntas();
        RequestDispatcher rd = request.getRequestDispatcher("ProbarExamen.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        evaluarExamen();
        RequestDispatcher rs = request.getRequestDispatcher("VerCalificacion.jsp");
        rs.forward(request, response);
    }

    protected void evaluarExamen() {
        int numeroDePreguntas = Integer.valueOf(request.getParameter("numeroDePreguntas"));
        int maxPuntuacion = 0;
        int puntosObtenidos = 0;
        int correctas = 0;
        String retroalimentacion = "";
        for (int i = 0; i < numeroDePreguntas; i++) {
            String solucion = request.getParameter("solucion_" + i);
            String entrada = request.getParameter("entrada_" + i);
            
            int puntos = Integer.valueOf(request.getParameter("puntuacion_" + i));
            maxPuntuacion += puntos;
            if (entrada.toLowerCase().equals(solucion)) {
                puntosObtenidos += puntos;
                correctas += 1;
            }
        }
        request.setAttribute("retroalimentacion",correctas + " de " + numeroDePreguntas);

        request.setAttribute("texto", puntosObtenidos + " de " + maxPuntuacion);
    }

    protected void cargarPreguntas() {
        String pathPregunta = request.getRealPath("/") + "XML/PREGUNTAS/";
        ArrayList<String> listaNombreDePreguntas = obtenerNombreDePreguntas();
        ArrayList<String> listaDeEnunciados = new ArrayList<String>();
        ArrayList<String> listaDeSoluciones = new ArrayList<String>();
        ArrayList<String> listaDePuntuaciones = new ArrayList<String>();

        for (String preguntaS : listaNombreDePreguntas) {
            IPregunta preguntaTemp = instanciarPregunta(pathPregunta + preguntaS + ".xml");
            listaDeEnunciados.add(preguntaTemp.getEnunciado());
            listaDeSoluciones.add(preguntaTemp.getSolucion());
            listaDePuntuaciones.add(String.valueOf(preguntaTemp.getPuntuacion()));
        }
        request.setAttribute("listaDeEnunciados", convertirAArregloJS(listaDeEnunciados));
        request.setAttribute("listaDeSoluciones", convertirAArregloJS(listaDeSoluciones));
        request.setAttribute("listaDePuntuaciones", convertirAArregloJS(listaDePuntuaciones));

    }

    protected void inicializar(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        String nombreDeExamen = (String) request.getParameter("nombre");
        this.pathExamen = request.getRealPath("/") + "XML/EXAMENES/" + nombreDeExamen + ".xml";
        this.doc = generarDocumento(this.pathExamen);
    }

    protected Document generarDocumento(String path) {
        try {
            File archivoXML = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    protected IPregunta instanciarPregunta(String pathPregunta) {
        Document preguntaXML = generarDocumento(pathPregunta);
        String nombreDePregunta = atributoAString(preguntaXML, "nombreDePregunta");
        String tipoDePregunta = atributoAString(preguntaXML, "tipoDePregunta");
        String enunciado = atributoAString(preguntaXML, "enunciado");
        int puntuacion = Integer.parseInt(atributoAString(preguntaXML, "puntuacion"));
        switch (tipoDePregunta) {
            case "Algebraica":
                String solucion = atributoAString(preguntaXML, "solucion");
                return new PreguntaAlgebraica(nombreDePregunta, enunciado, puntuacion, solucion);
            case "Calculada":
                String formula = atributoAString(preguntaXML, "formula");
                ArrayList<String> variables = obtenerVariablesDePreguntaXML(preguntaXML);
                return new PreguntaCalculada(nombreDePregunta, enunciado, puntuacion, formula, variables);
        }

        return null;
    }

    protected String atributoAString(Document doc, String atributo) {
        try{
        return doc.getElementsByTagName(atributo).item(0).getTextContent();
        }catch(Exception e){}
        return "";
    }

    protected ArrayList<String> obtenerVariablesDePreguntaXML(Document doc) {
        ArrayList<String> variables = new ArrayList<String>();
        Element elementoRoot = doc.getDocumentElement();
        NodeList listaDeNodosDePreguntas = elementoRoot.getElementsByTagName("variable");
        if (listaDeNodosDePreguntas != null && listaDeNodosDePreguntas.getLength() > 0) {
            for (int i = 0; i < listaDeNodosDePreguntas.getLength(); i++) {
                Node nodoTemp = listaDeNodosDePreguntas.item(i);
                variables.add(nodoTemp.getTextContent());
            }
        }
        return variables;
    }

    protected ArrayList<String> obtenerNombreDePreguntas() {
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

    public static String convertirAArregloJS(ArrayList<String> arr) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < arr.size(); i++) {
            sb.append("\"").append(arr.get(i)).append("\"");
            if (i + 1 < arr.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
