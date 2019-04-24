package Servlets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

    public class AltaPregunta extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session= request.getSession();
            String Nombre,Texto,Formula,Tolerancia,Puntuacion,Autor;
            ArrayList<String> Variables = new ArrayList<String>();

            Pattern regex = Pattern.compile("\\{(.*?)\\}");

            Nombre=(String)request.getParameter("Nombre");
            Texto=(String)request.getParameter("Texto");
            Formula=(String)request.getParameter("Formula");
            Puntuacion=(String)request.getParameter("Puntuacion");
            Autor=(String)session.getAttribute("User");

            Matcher regexMatcher = regex.matcher(Texto);
            while (regexMatcher.find()) {
                Variables.add(regexMatcher.group(1));
            }

            try{
                   String path = request.getRealPath("/");
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

                   for(int i=0; i<Variables.size();i++)
                     raiz.addContent(new Element("variable").setText(Variables.get(i)));

                   raiz.addContent(formula);

                   //DocType dtype = new DocType(path+"/XML/DTDPREGUNTA.dtd");
			             Document newdoc = new Document(raiz); //Convierte la raiz a un objeto de tipo document para hacerlo persistente
			             XMLOutputter fmt = new XMLOutputter();
			             FileWriter writer = new FileWriter(path+"/XML/"+Nombre); //Crea el archivo xml
			             fmt.output(newdoc, writer); //escribe en el documento xml
                   writer.flush(); //Vacia todo el buffer en el xml
                   writer.close(); // Cierra el filewriter
	              }catch (Exception e){
			            e.printStackTrace();
		            }


            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>\n" +
            "<html lang=\"en\" dir=\"ltr\">\n" +
            "  <head>\n" +
            "    <title>Inicio</title>\n" +
            "    <meta charset=\"utf-8\" />\n" +
            "    <meta name=\"viewport\" content=\"minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no\" />\n" +
            "    <script src=\"https://unpkg.com/react@latest/umd/react.development.js\" crossorigin=\"anonymous\"></script>\n" +
            "    <script src=\"https://unpkg.com/react-dom@latest/umd/react-dom.development.js\"></script>\n" +
            "    <script src=\"https://unpkg.com/@material-ui/core@latest/umd/material-ui.development.js\" crossorigin=\"anonymous\"></script>\n" +
            "    <script src=\"https://unpkg.com/babel-standalone@latest/babel.min.js\" crossorigin=\"anonymous\"></script>\n" +
            "    \n" +
            "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500\" />\n" +
            "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" />\n" +
            "    <link rel=\"stylesheet\" href=\"components/App.css\" />"
            + "</head>\n" +
            "  <body>");
            for(int j=0;j<Variables.size();j++)
                out.println("var"+j+": "+Variables.get(j));
            out.println("<script type=\"text/babel\">const User = '"+Texto+"';</script>");
            out.println("</body>");
            out.println("</html>");
    }
}
