/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class EliminarPregunta extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nodoAEliminar = (String) request.getParameter("preguntaAEliminar");
        String path = request.getRealPath("/");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        try{
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new FileInputStream(new File(path+ "/XML/Preguntas.xml")));

        Element element = (Element) doc.getElementsByTagName(nodoAEliminar).item(0);

        element.getParentNode().removeChild(element);
        doc.normalize();
        }
        catch(Exception e){}
    }
}
