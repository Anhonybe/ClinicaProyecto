package Pila;

import java.io.Serializable;

public class Empleado implements Comparable<Empleado>, Serializable {

    private static final long serialVersionUID = 1L;
    private String cod;
    private String nombre;
    private String condicion; // Parámetro existente
    private String area;

    // Constructor predeterminado
    public Empleado() {
    }

    // Constructor con parámetros
    public Empleado(String cod, String nombre, String condicion, String area) {
        this.cod = cod;
        this.nombre = nombre;
        this.condicion = condicion;
        this.area = area;
    }

    @Override
    public int compareTo(Empleado other) {
        return this.cod.compareTo(other.cod);
    }

    @Override
    public String toString() {
        return "Empleado \n"
                + "cod: " + cod + "\nombre: " + nombre + "\ncondicion: " + condicion + "area";
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
