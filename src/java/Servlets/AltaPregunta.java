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

    protected void conseguirVariablea(ArrayList<String> Variables, String Texto) {
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
        String Nombre, Texto, Formula, Puntuacion, Autor;
        ArrayList<String> Variables = new ArrayList<String>();
        Nombre = (String) request.getParameter("Nombre");
        Texto = (String) request.getParameter("Texto");
        Formula = (String) request.getParameter("Formula");
        Puntuacion = (String) request.getParameter("Puntuacion");
        Autor = (String) session.getAttribute("User");
        String path = request.getRealPath("/");
        conseguirVariablea(Variables, Texto);
        crearXML(Nombre, Texto, Formula, Puntuacion, Autor, path, Variables);

        response.sendRedirect("Login");
    }

    protected void crearXML(String Nombre, String Texto, String Formula, String Puntuacion, String Autor, String path, ArrayList<String> Variables) {

        try {
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

            //DocType dtype = new DocType(path+"/XML/DTDPREGUNTA.dtd");
            Document newdoc = new Document(raiz); //Convierte la raiz a un objeto de tipo document para hacerlo persistente
            XMLOutputter fmt = new XMLOutputter();
            File file = new File(path + "/XML/" + Nombre + ".xml");
            FileWriter writer = new FileWriter(file); //Crea el archivo xml
            fmt.output(newdoc, writer); //escribe en el documento xml
            writer.flush(); //Vacia todo el buffer en el xml
            writer.close(); // Ci           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
