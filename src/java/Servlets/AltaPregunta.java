package Servlets;

import Objetos.Pregunta;
import Objetos.PreguntaAlgebraica;
import Objetos.PreguntaCalculada;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class AltaPregunta extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    String path;

    protected ArrayList<String> generarListaDeVariables(String Texto) {
        ArrayList<String> variables = new ArrayList<String>();
        Pattern regex = Pattern.compile("\\{(.*?)\\}");
        Matcher regexMatcher = regex.matcher(Texto);
        while (regexMatcher.find()) {
            variables.add(regexMatcher.group(1));
        }
        return variables;
    }

    protected void inicializar(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
        this.path = request.getRealPath("/") + "XML/PREGUNTAS/";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        inicializar(request, response, session);
        crearPregunta();
        response.sendRedirect("IrPreguntas");
    }

    protected void crearPregunta() {
        String tipoDePregunta = request.getParameter("tipoDePregunta");;
        Element raiz;
        if ("algebraica".equals(tipoDePregunta)) {
            PreguntaAlgebraica pregunta = crearPreguntaAlgebraica();
            raiz = generarRaizXML(pregunta);
            crearArchivoXML(raiz, pregunta.getNombre());

        } else if ("calculada".equals(tipoDePregunta)) {
            PreguntaCalculada pregunta = crearPreguntaCalculada();
            raiz = generarRaizXML(pregunta);
            crearArchivoXML(raiz, pregunta.getNombre());
        }
    }

    protected PreguntaCalculada crearPreguntaCalculada() {
        String nombre = (String) request.getParameter("Nombre");
        String enunciado = (String) request.getParameter("Texto");
        int puntuacion = Integer.parseInt((String) request.getParameter("Puntuacion"));
        String autor = (String) session.getAttribute("User");
        String formula = (String) request.getParameter("Formula");
        ArrayList<String> variablesTemp = generarListaDeVariables(enunciado);
        return new PreguntaCalculada(nombre, enunciado, autor, puntuacion, formula, variablesTemp);
    }

    protected PreguntaAlgebraica crearPreguntaAlgebraica() {
        String nombre = (String) request.getParameter("Nombre");
        String enunciado = (String) request.getParameter("Texto");
        int puntuacion = Integer.parseInt((String) request.getParameter("Puntuacion"));
        String autor = (String) session.getAttribute("User");
        String solucion = (String) request.getParameter("solucion");

        return new PreguntaAlgebraica(nombre, enunciado, autor, puntuacion, solucion);
    }

    protected Element generarRaizXML(PreguntaCalculada pregunta) {
        Element raiz = new Element("pregunta"); //permite declarar los element dentro del xml
        Element autor = new Element("autor").setText(pregunta.getAutor());
        Element nombre = new Element("nombreDePregunta").setText(pregunta.getNombre());
        Element enunciado = new Element("enunciado").setText(pregunta.getEnunciado());
        Element puntuacion = new Element("puntuacion").setText(String.valueOf(pregunta.getPuntuacion()));
        Element formula = new Element("formula").setText(pregunta.getFormula());
        Element tipoDePregunta = new Element("tipoDePregunta").setText("Calculada");

        raiz.addContent(autor);
        raiz.addContent(nombre);
        raiz.addContent(enunciado);
        raiz.addContent(puntuacion);
        raiz.addContent(tipoDePregunta);
        ArrayList<String> variablesTemp = pregunta.getVariables();
        for (int i = 0; i < variablesTemp.size(); i++) {
            raiz.addContent(new Element("variable").setText(variablesTemp.get(i)));
        }
        raiz.addContent(formula);
        return raiz;
    }

    protected Element generarRaizXML(PreguntaAlgebraica pregunta) {
        Element raiz = new Element("pregunta"); //permite declarar los element dentro del xml
        Element autor = new Element("autor").setText(pregunta.getAutor());
        Element nombre = new Element("nombreDePregunta").setText(pregunta.getNombre());
        Element enunciado = new Element("enunciado").setText(pregunta.getEnunciado());
        Element puntuacion = new Element("puntuacion").setText(String.valueOf(pregunta.getPuntuacion()));
        Element solucion = new Element("solucion").setText(pregunta.getSolucion());
        Element tipoDePregunta = new Element("tipoDePregunta").setText("Algebraica");
        raiz.addContent(autor);
        raiz.addContent(nombre);
        raiz.addContent(enunciado);
        raiz.addContent(puntuacion);
        raiz.addContent(tipoDePregunta);
        raiz.addContent(solucion);
        return raiz;
    }

    protected void crearArchivoXML(Element raiz, String nombre) {
        Document newdoc = new Document(raiz); //Convierte la raiz a un objeto de tipo document para hacerlo persistente
        XMLOutputter fmt = new XMLOutputter();
        File file = new File(path + nombre + ".xml");
        try {
            FileWriter writer = new FileWriter(file); //Crea el archivo xml
            fmt.output(newdoc, writer); //escribe en el documento xml
            writer.flush(); //Vacia todo el buffer en el xml
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
