/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nexus
 */
public class VerExamenes extends HttpServlet {
    HttpServletRequest request;
    HttpServletResponse response;
    String path;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        inicializar(request, response);
        ArrayList<String> examenesDisponibles = generarListaDeExamenes(path);
        RequestDispatcher rd = request.getRequestDispatcher("VerExamenes.jsp");
        String USER = (String) session.getAttribute("User");
        String PASS = (String) session.getAttribute("Pass");
        request.setAttribute("examenesDisponibles", convertirAArregloJS(examenesDisponibles.toArray(new String[0])));
        rd.forward(request, response);
    }

    protected void inicializar(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.path = request.getRealPath("/") + "XML/EXAMENES/";
    }

    protected ArrayList<String> generarListaDeExamenes(String path) {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        final File carpeta = new File(path);
        try{
        for (final File fileEntry : carpeta.listFiles()) {
            String nombre = fileEntry.getName();
            String nombreSinExtension = nombre.substring(0, nombre.length() - 4);
            nombrePreguntas.add(nombreSinExtension);
        }
        }catch(Exception e){}
        return nombrePreguntas;
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
