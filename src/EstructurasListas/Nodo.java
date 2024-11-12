package EstructurasListas;

import Modelo.Cliente;
import java.io.Serializable;

public class Nodo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public Cliente cli;
    public Nodo sig;

    public Nodo(Cliente cli) {
        this.cli = cli;
        this.sig = null; // Inicializar sig como null
    }

    // Getter y Setter para cli y sig
    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}
