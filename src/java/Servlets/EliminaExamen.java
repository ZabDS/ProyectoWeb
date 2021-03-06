/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nexus
 */

public class EliminaExamen extends HttpServlet {

  
    String path;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        path = request.getRealPath("/") + "XML/EXAMENES/";
        String nombreDePregunta = (String) request.getParameter("nombre");
        eliminarPregunta(nombreDePregunta);
         response.sendRedirect("IrExamen");

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        path = request.getRealPath("/") + "XML/EXAMENES/";
        String nombreDePregunta = (String) request.getParameter("nombreOriginal");
        eliminarPregunta(nombreDePregunta);
    }

    protected void eliminarPregunta(String nombreDePregunta) {
        File file = new File(path + nombreDePregunta + ".xml");
        if (file.delete()) {
            System.out.println("200");
        } else {
            System.out.println("500");
        }
    }
}
