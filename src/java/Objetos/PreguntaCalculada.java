/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author nexus
 */
public class PreguntaCalculada extends Pregunta {

    String nombre;
    String enunciado;
    String autor;
    int puntuacion;
    String formula;
    ArrayList<String> variables;

    public PreguntaCalculada(String nombre, String enunciado, String autor, int puntuacion, String formula, ArrayList<String> variables) {
        this.nombre = nombre;
        this.enunciado = enunciado;
        this.autor = autor;
        this.puntuacion = puntuacion;
        this.formula = formula;
        this.variables = variables;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<String> variables) {
        this.variables = variables;
    }


}
