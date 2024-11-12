package Modelo;

import java.io.Serializable;

public class Mascota implements Comparable<Mascota>, Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    private String codigo;
    private String nombre;
    private String raza;
    private int edad;
    private String propietario;
    private String fecha;

    public Mascota(String codigo, String nombre, String raza, int edad, String propietario, String fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.propietario = propietario;
        this.fecha = fecha;
    }

    // Crea un array de objetos con el número proporcionado y los atributos de la Mascota
    public Object[] Registro(int num) {
        Object fila[] = {num, codigo, nombre, raza, edad, propietario, fecha};
        return fila;
    }

    //Constructor sin parametros
    public Mascota() {
    }

    //Sobreescritura del metodo toString
    @Override
    public String toString() {
        return "Mascota{" + "codigo=" + codigo + ", nombre=" + nombre + ", raza=" + raza + ", edad=" + edad
                + ", propietario=" + propietario + ", fecha=" + fecha + '}';
    }

    public String imprimir() {
        String datos = "";
        datos += " MASCOTA";
        datos += "\n - Codigo     : " + codigo;
        datos += "\n - Nombre     : " + nombre;
        datos += "\n - Raza       : " + raza;
        datos += "\n - Edad       : " + edad;
        datos += "\n - Propietario: " + propietario;
        datos += "\n - Fecha      : " + fecha;
        return datos;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Mascota other) {
        return this.codigo.compareTo(other.codigo);
    }
}
