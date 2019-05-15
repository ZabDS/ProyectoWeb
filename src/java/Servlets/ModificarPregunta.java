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
public class ModificarPregunta extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRealPath("/");
        ArrayList<String> nombreDePreguntas = generarListaDeXML(path);
        RequestDispatcher rd = request.getRequestDispatcher("ModificarPregunta.jsp");
        request.setAttribute("preguntasDisponibles", convertirAArregloJS(nombreDePreguntas.toArray(new String[0])));
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
        String nombrePregunta = request.getParameter("id_select");
        String enunciado = request.getParameter("QuestText");
        String puntuacion = request.getParameter("Points");
        String formula = request.getParameter("FormText");

        rd.forward(request, response);
    }
    
    protected void modificarXML(String nombrePregunta,String enunciado,String puntuacion,String formula){
    }
    
    
    protected ArrayList<String> generarListaDeXML(String path) {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        try {
            File fXmlFile = new File(path + "/XML/Preguntas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("pregunta");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);				
                
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			nombrePreguntas.add( eElement.getElementsByTagName("nombreDePregunta").item(0).getTextContent());
		}
	}
        } catch (Exception e) {}
        return nombrePreguntas;
    }
    
    public static String convertirAArregloJS(String[] arr){
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for(int i=0; i<arr.length; i++){
        sb.append("\"").append(arr[i]).append("\"");
        if(i+1 < arr.length){
            sb.append(",");
        }
    }
    sb.append("]");
    return sb.toString();
}
}
