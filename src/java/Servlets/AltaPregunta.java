package Servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class AltaPregunta extends HttpServlet {

    protected void conseguirVariables(ArrayList<String> Variables, String Texto) {
        Pattern regex = Pattern.compile("\\{(.*?)\\}");
        Matcher regexMatcher = regex.matcher(Texto);
        while (regexMatcher.find()) {
            Variables.add(regexMatcher.group(1));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombre, texto, formula, puntuacion, autor;
        ArrayList<String> variables = new ArrayList<String>();
        nombre = (String) request.getParameter("Nombre");
        texto = (String) request.getParameter("Texto");
        formula = (String) request.getParameter("Formula");
        puntuacion = (String) request.getParameter("Puntuacion");
        autor = (String) session.getAttribute("User");
        String path = request.getRealPath("/");
        conseguirVariables(variables, texto);
        Element raiz = generarRaizXML(nombre, texto, formula, puntuacion, autor, path, variables);
        crearArchivoXML(raiz, path, nombre);
        response.sendRedirect("Login");
    }

    protected Element generarRaizXML(String Nombre, String Texto, String Formula, String Puntuacion, String Autor, 
                                        String path, ArrayList<String> Variables) {
        Element raiz = new Element("pregunta"); //permite declarar los element dentro del xml
        Element autor = new Element("autor");
        Element nombre = new Element("nombreDePregunta");
        Element enunciado = new Element("enunciado");
        Element puntuacion = new Element("puntuacion");
        Element formula = new Element("formula");
        autor.setText(Autor); //Valor del atributo <x>Atributo</x>
        nombre.setText(Nombre);
        enunciado.setText(Texto);
        puntuacion.setText(Puntuacion);
        formula.setText(Formula);
        raiz.addContent(autor);
        raiz.addContent(nombre);
        raiz.addContent(enunciado);
        raiz.addContent(puntuacion);
        for (int i = 0; i < Variables.size(); i++) {
            raiz.addContent(new Element("variable").setText(Variables.get(i)));
        }
        raiz.addContent(formula);
        return raiz;
    }

    protected void crearArchivoXML(Element raiz, String path, String nombre) {
        Document newdoc = new Document(raiz); //Convierte la raiz a un objeto de tipo document para hacerlo persistente
        XMLOutputter fmt = new XMLOutputter();
        File file = new File(path + "/XML/PREGUNTAS/" + nombre + ".xml");
        try {
            FileWriter writer = new FileWriter(file); //Crea el archivo xml
            fmt.output(newdoc, writer); //escribe en el documento xml
            writer.flush(); //Vacia todo el buffer en el xml
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }// Ci  
    }

}
