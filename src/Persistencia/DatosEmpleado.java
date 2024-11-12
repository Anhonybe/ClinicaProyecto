package Persistencia;
//libreria

import java.io.*;
import Pila.*;
import Procesos.*;

/**
 *
 * @author FAMILIA
 */
public class DatosEmpleado {

    public static void GuardarEnArchivo(PilaEmpleado Lista) {
        try {
            FileOutputStream fos = new FileOutputStream("InfoEmpleado.bin");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(Lista);
            }
        } catch (IOException ex) {
            Mensajes.MostrarTexto("ERROR no se puede guardar Pila.." + ex);
        }
    }//fin guardar   

    public static PilaEmpleado RecuperaDeArchivo() {
        PilaEmpleado Lista = new PilaEmpleado();
        try {
            FileInputStream fis = new FileInputStream("InfoEmpleado.bin");
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                Lista = (PilaEmpleado) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Mensajes.MostrarTexto("ERROR no se puede recuperar Pila.." + ex);
        }
        return Lista;
    }//fin recuperar
}
