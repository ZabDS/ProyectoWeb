package Objetos;

import Interfaces.IPregunta;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author nexus
 */
public class PreguntaCalculada implements IPregunta {

    String nombre;
    String enunciado;
    String autor;
    int puntuacion;
    String formula;
    String solucion;
    ArrayList<String> variables;

    public PreguntaCalculada(String nombre, String enunciado, int puntuacion, String formula, ArrayList<String> variables) {
        this.nombre = nombre;
        this.enunciado = enunciado;
        this.puntuacion = puntuacion;
        this.formula = formula;
        this.variables = variables;
        generarValores();
    }

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

    public String generarValores() {
        String nuevoEnunciado = this.enunciado;
        String formulaConValores = this.formula;
        for (String variable : variables) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
            nuevoEnunciado = nuevoEnunciado.replace("{"+variable+"}", String.valueOf(randomNum));
            formulaConValores = formulaConValores.replace("{"+variable+"}", String.valueOf(randomNum));
        }
        setEnunciado(nuevoEnunciado);
        setFormula(formulaConValores);
        evaluarOperacion();
        
        return nuevoEnunciado;
    }

    public void evaluarOperacion() {
        try{
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        setSolucion(String.valueOf(engine.eval(getFormula())));
        }
        catch (Exception e){}
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

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
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
