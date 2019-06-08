/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author nexus
 */
public class PreguntaAlgebraica extends Pregunta {

    String nombre;
    String enunciado;
    String autor;
    int puntuacion;
    String solucion;

    public PreguntaAlgebraica(String nombre, String enunciado, String autor, int puntuacion, String solucion) {
        this.nombre = nombre;
        this.enunciado = enunciado;
        this.autor = autor;
        this.puntuacion = puntuacion;
        this.solucion = solucion;
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

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }


}
