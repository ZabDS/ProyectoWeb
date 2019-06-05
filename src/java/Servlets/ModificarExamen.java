/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import static Servlets.ModificaPreguntas.convertirAArregloJS;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ModificarExamen extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;
    String pathPreguntas;
    String pathExamenes;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("ModificarExamen.jsp");
        establecerAtributos();
        rd.forward(request, response);
    }

    protected void establecerAtributos() {
        ArrayList<String> nombreDeExamenes = generarListaDeExamenes();
        ArrayList<String> nombreDePreguntas = generarListaDePreguntas();

        request.setAttribute("examenesDisponibles", convertirAArregloJS(nombreDeExamenes.toArray(new String[0])));
        request.setAttribute("preguntasDisponibles", convertirAArregloJS(nombreDePreguntas.toArray(new String[0])));

    }

    protected void inicializar(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.pathExamenes = request.getRealPath("/") + "XML/EXAMENES/";
        this.pathPreguntas = request.getRealPath("/") + "XML/PREGUNTAS/";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        inicializar(request, response);
        String nombreDePregunta = request.getParameter("Nombre");
        String accion = request.getParameter("submitButton");
        if ("Modificar".equals(accion)) {
            eliminarExamen(nombreDePregunta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaPregunta");
            dispatcher.forward(request, response);

        } else if ("Borrar".equals(accion)) {
            eliminarExamen(nombreDePregunta);
            response.sendRedirect("Login");

        }
    }

    protected ArrayList<String> generarListaDePreguntas() {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        final File carpeta = new File(this.pathPreguntas);
        for (final File fileEntry : carpeta.listFiles()) {
            String nombre = fileEntry.getName();
            String nombreSinExtension = nombre.substring(0, nombre.length() - 4);
            nombrePreguntas.add(nombreSinExtension);
        }
        return nombrePreguntas;
    }

    protected void eliminarExamen(String nombreDeExamen) {
        File file = new File(this.pathExamenes + nombreDeExamen + ".xml");
        if (file.delete()) {
            System.out.println("200");
        } else {
            System.out.println("500");
        }
    }

    protected ArrayList<String> generarListaDeExamenes() {
        ArrayList<String> nombrePreguntas = new ArrayList<String>();
        final File carpeta = new File(this.pathExamenes);
        for (final File fileEntry : carpeta.listFiles()) {
            String nombre = fileEntry.getName();
            String nombreSinExtension = nombre.substring(0, nombre.length() - 4);
            nombrePreguntas.add(nombreSinExtension);
        }
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
