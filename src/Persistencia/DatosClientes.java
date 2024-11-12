package Persistencia;

import EstructurasListas.*;
import Procesos.*;
import java.io.*;
import Modelo.Cliente;
import java.util.ArrayList;

public class DatosClientes {

    public static void GuardarEnArchivo(ListaEnlazada Lista) {
        try {
            FileOutputStream fos = new FileOutputStream("InfoClientes.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Lista);
            oos.close();
        } catch (IOException ex) {
            Mensajes.MostrarTexto("ERROR no se puede guardar Lista Enlazada" + ex);
        }
    }//fin guardar

    public static ListaEnlazada RecuperarDeArchivo() {
        ListaEnlazada Lista = new ListaEnlazada();
        try {
            FileInputStream fis = new FileInputStream("InfoClientes.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Lista = (ListaEnlazada) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            Mensajes.MostrarTexto("ERROR no se puede guardar Lista Enlazada" + ex);
        }
        return Lista;
    }//fin recuperar
    
    
    
    private static final String URL_GUARDADO = "InfoClientes.bin";

    private ArrayList<Cliente> listaClientes;

    public DatosClientes() {
        listaClientes = new ArrayList<>();
        cargarCitasDesdeArchivo();
    }

    public ArrayList<Cliente> obtenerTodasLasCitas() {
        return new ArrayList<>(listaClientes);
    }

    private void cargarCitasDesdeArchivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(URL_GUARDADO))) {
            listaClientes = (ArrayList<Cliente>) in.readObject();
        } catch (FileNotFoundException e) {
            listaClientes = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
            listaClientes = new ArrayList<>();
        }
    }

}//fin de la clase

