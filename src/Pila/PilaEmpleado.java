package Pila;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import Persistencia.*;
import Procesos.*;

/**
 *
 * @author FAMILIA
 */
public class PilaEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    LinkedList<Empleado> pila;
    HashMap<String, Empleado> tablaHash;
    TreeSet<Empleado> arbolBinario;

    public PilaEmpleado() {
        pila = new LinkedList<>();
        tablaHash = new HashMap<>();
        arbolBinario = new TreeSet<>();
        //pila = DatosOperario.RecuperaDeArchivo().getPila();
    }

    // Método que agrega datos a la pila
    public void Agregar(Empleado obj) {
        pila.push(obj);
        tablaHash.put(obj.getCod(), obj);
        arbolBinario.add(obj);
        DatosEmpleado.GuardarEnArchivo(this);
    }

    // Método que verifica el primer elemento de la pila
    public void VerPrimerElemento() {
        Mensajes.MostrarTexto(pila.peekFirst().toString());
    }

    // Método que verifica el último elemento de la pila
    public void VerUltimoElemento() {
        Mensajes.MostrarTexto(pila.peekLast().toString());
    }

    // Método para eliminar un elemento de la pila
    public void Eliminar() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del empleado a eliminar:");
        if (codigo == null || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código no ingresado. Operación cancelada.");
            return;
        }

        Empleado eliminado = tablaHash.remove(codigo);
        if (eliminado == null) {
            JOptionPane.showMessageDialog(null, "El código " + codigo + " no existe en la pila.");
            return;
        }

        pila.remove(eliminado);
        arbolBinario.remove(eliminado);
        DatosEmpleado.GuardarEnArchivo(this);
        JOptionPane.showMessageDialog(null, "Empleado con código " + codigo + " eliminado correctamente.");
    }

    // Método que busca un dato en la pila usando la tabla hash
    public void BuscarPorCodigo(String codbuscado) {
        Empleado encontrado = tablaHash.get(codbuscado);
        if (encontrado != null) {
            Mensajes.MostrarTexto(encontrado.toString());
        } else {
            Mensajes.MostrarTexto("El código " + codbuscado + " no existe en la pila");
        }
    }

    // Getter y setter
    public LinkedList<Empleado> getPila() {
        return pila;
    }

    public void setPila(LinkedList<Empleado> pila) {
        this.pila = pila;
    }
}
