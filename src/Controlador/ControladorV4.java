package Controlador;

import Modelo.ArregloMascotas;
import Modelo.Servicio;
import Persistencia.DataServicio;
import Pila.*;
import Vista.*;
import Procesos.*;
import Persistencia.DatosEmpleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.VentanaEmpleado;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author FAMILIA
 */
public class ControladorV4 implements ActionListener {

    VentanaEmpleado vista;
    PilaEmpleado Pila;

    public ControladorV4(VentanaEmpleado vem) {
        vista = vem;

        vista.btnAgregar.addActionListener(this);
        vista.btnRetirar.addActionListener(this);
        vista.btnVerPrimero.addActionListener(this);
        vista.btnVerUltimo.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        Pila = new PilaEmpleado();
        Pila = DatosEmpleado.RecuperaDeArchivo();
        ProcesosV4.Presentacion(vista);
        ProcesosV4.MostrarDatosEnTabla(vista, Pila);
    }

    //Metodo para escuchar los eventos de los componentes de Ventana Registro Empleado
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            Pila.Agregar(ProcesosV4.LeerEmpleado(vista));
            ProcesosV4.LimpiarEntradas(vista);
            ProcesosV4.MostrarDatosEnTabla(vista, Pila);
        }
        if (e.getSource() == vista.btnRetirar) {
            Pila.Eliminar();
            ProcesosV4.LimpiarEntradas(vista);
            ProcesosV4.MostrarDatosEnTabla(vista, Pila);
        }
        if (e.getSource() == vista.btnVerPrimero) {
            Pila.VerPrimerElemento();
        }
        if (e.getSource() == vista.btnVerUltimo) {
            Pila.VerUltimoElemento();
        }
        if (e.getSource() == vista.btnBuscar) {
            String codbuscado = Mensajes.LeerTexto("Ingrese el "
                    + "codigo del empleado a buscar");
            Pila.BuscarPorCodigo(codbuscado);
        }
    }//fin action 

    public static Empleado[] listaEmpleados() {
        PilaEmpleado Pil = new PilaEmpleado();
        Pil = DatosEmpleado.RecuperaDeArchivo();
        int cantDatos = Pil.getPila().size();
        Empleado arrayEmpleado[] = new Empleado[cantDatos];
        for (int i = 0; i < cantDatos; i++) {
            arrayEmpleado[i] = Pil.getPila().get(i);
        }
        Empleado[] empleadosFiltrados = Arrays.stream(arrayEmpleado)
                .filter(pet -> pet != null)
                .toArray(Empleado[]::new);
        return empleadosFiltrados;
    }

    public static ArrayList<Empleado> listaDoctores(String condicion) {
        ArrayList<Empleado> listaDoctores = new ArrayList<>();
        for (Empleado emp : listaEmpleados()) {
            String tempCondicion = emp.getCondicion();
            if (tempCondicion.equalsIgnoreCase(condicion)) {
                listaDoctores.add(emp);
//                break;
            }
        }
        return listaDoctores;
    }

}//fin controlador
