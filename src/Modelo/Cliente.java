package Modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nombre;
    private String nroMasc;
    private String nroCel;

    public Cliente(String codigo, String nombre, String nroMasc, String nroCel) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nroMasc = nroMasc;
        this.nroCel = nroCel;
    }
    
    
    public Cliente() {

    }

    public Object[] Registro(int num) {
        Object fila[] = {num, codigo, nombre, nroMasc, nroCel};
        return fila;
    }

    //getter y setter
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

    public String getNroMasc() {
        return nroMasc;
    }

    public void setNroMasc(String nroMasc) {
        this.nroMasc = nroMasc;
    }

    public String getNroCel() {
        return nroCel;
    }

    public void setNroCel(String nroCel) {
        this.nroCel = nroCel;
    }
}//fin de la clase
