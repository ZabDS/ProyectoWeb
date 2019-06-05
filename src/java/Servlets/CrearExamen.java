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
        HttpSession session = request.getSession();
        String USER, PASS, Preguntas, NombreExamen;
        String[] NPreguntas;
        int i = 0;
        USER = (String) session.getAttribute("User");
        PASS = (String) session.getAttribute("Pass");
        Preguntas = (String) request.getParameter("Quests");
        NombreExamen = (String) request.getParameter("TestName");
        String path = request.getRealPath("/");

        NPreguntas = Preguntas.split(",");
        List<String> ANPreguntas = Arrays.asList(NPreguntas);

        Element raiz = generarRaizXML(NombreExamen, ANPreguntas, USER);

        crearArchivoXML(raiz, path, NombreExamen);
        response.sendRedirect("Login");

        PrintWriter out = response.getWriter();
        for (i = 0; i < ANPreguntas.size(); i++) {
            out.println(ANPreguntas.get(i));
        }
    }

    protected void crearArchivoXML(Element raiz, String path, String nombre) {
        Document newdoc = new Document(raiz); //Convierte la raiz a un objeto de tipo document para hacerlo persistente
        XMLOutputter fmt = new XMLOutputter();
        File file = new File(path + "/XML/EXAMENES/" + nombre + ".xml");
        try {
            FileWriter writer = new FileWriter(file); //Crea el archivo xml
            fmt.output(newdoc, writer); //escribe en el documento xml
            writer.flush(); //Vacia todo el buffer en el xml
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String USER, PASS, Preguntas, NombreExamen;
        String[] NPreguntas;
        int i = 0;
        USER = (String) session.getAttribute("User");
        PASS = (String) session.getAttribute("Pass");
        String[] preguntas = request.getParameterValues("checkbox");
        NombreExamen = (String) request.getParameter("Nombre");
        String path = request.getRealPath("/");

        Element raiz = generarRaizXML(NombreExamen, preguntas, USER);

        crearArchivoXML(raiz, path, NombreExamen);
        response.sendRedirect("Login");

    }

    protected Element generarRaizXML(String Nombre, List<String> Preguntas, String Autor) {
        Element raiz = new Element("Examen"); //permite declarar los element dentro del xml
        Element autor = new Element("autor");
        autor.setText(Autor); //Valor del atributo <x>Atributo</x>
        raiz.addContent(autor);
        for (int i = 0; i < Preguntas.size(); i++) {
            raiz.addContent(new Element("Pregunta").setText(Preguntas.get(i)));
        }
        return raiz;
    }
    
        protected Element generarRaizXML(String Nombre, String[] Preguntas, String Autor) {
        Element raiz = new Element("Examen"); //permite declarar los element dentro del xml
        Element autor = new Element("autor");
        autor.setText(Autor); //Valor del atributo <x>Atributo</x>
        raiz.addContent(autor);
        for (int i = 0; i < Preguntas.length; i++) {
            raiz.addContent(new Element("Pregunta").setText(Preguntas[i]));
        }
        return raiz;
    }
}
