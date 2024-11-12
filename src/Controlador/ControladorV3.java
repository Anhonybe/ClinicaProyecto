package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import EstructurasListas.*;
import Vista.*;
import Procesos.*;
import Persistencia.*;
import javax.swing.JOptionPane;
import Ordenamientos.OrdenarListaEnlazada;

public class ControladorV3 implements ActionListener {//controlador ventana cliente

    ListaEnlazada Lista;
    VentanaCliente vista;
    Nodo actual;

    public ControladorV3(VentanaCliente vcl) {
        vista = vcl;
        vista.btnGuardarIni.addActionListener(this);
        vista.btnGuardarFin.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnOrdenar.addActionListener(this);
        vista.cbxOrdenar.addActionListener(this);
        Lista = DatosClientes.RecuperarDeArchivo();
        ProcesosVentanaCliente.MostrarDatos(vista, Lista);
        clickTabla();
    }

    //Metodo para escuchar los eventos de los componentes de Ventana Registro Cliente
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarIni) {
            Nodo nuevo = new Nodo(ProcesosVentanaCliente.LeerCliente(vista));
            Lista.AgregarNodoInicio(nuevo);
            System.out.println("AGREGANDO NODO");
            ProcesosVentanaCliente.MostrarDatos(vista, Lista);
            DatosClientes.GuardarEnArchivo(Lista);
            ProcesosVentanaCliente.LimpiarEntradas(vista);
        }//fin de btnAgregarInicio
        if (e.getSource() == vista.btnGuardarFin) {
            Nodo nuevo = new Nodo(ProcesosVentanaCliente.LeerCliente(vista));
            Lista.AgregarNodoFinal(nuevo);
            System.out.println("AGREGANDO FINAL");
            ProcesosVentanaCliente.MostrarDatos(vista, Lista);
            DatosClientes.GuardarEnArchivo(Lista);
            ProcesosVentanaCliente.LimpiarEntradas(vista);
        }//fin de btnAgregarInicio
        if (e.getSource() == vista.btnBuscar) {
            String codbuscado = JOptionPane.showInputDialog(
                    "Ingrese el codigo a buscar...");
            actual = Lista.BuscarCliente(codbuscado);
            if (actual == null) {
                JOptionPane.showMessageDialog(null,
                        "El código " + codbuscado + " no existe en la lista..");
            } else {
                ProcesosVentanaCliente.MostrarDatosNodo(actual, vista);
                vista.txtCodigo.requestFocus();
            }
        }//fin de buscar
        if (e.getSource() == vista.btnEliminar) {
            int respuesta = JOptionPane.showConfirmDialog(null,
                    "Â¿Desea eliminar a " + actual.getCli().getCodigo() + "- " + actual.getCli().getNombre() + "?",
                    "Confirmar!!!!", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {//0: se seleccione YES
                Lista.EliminarCliente(actual);
                ProcesosVentanaCliente.MostrarDatos(vista, Lista);
                DatosClientes.GuardarEnArchivo(Lista);
                ProcesosVentanaCliente.LimpiarEntradas(vista);
            }
        }//fin eliminar
        if (e.getSource() == vista.btnOrdenar) {
            ListaEnlazada listaParaMostrar;
            System.out.println("ORDENANDO LISTA");
            switch (vista.cbxOrdenar.getSelectedIndex()) {
                case 0:
                    listaParaMostrar = Lista;
                    break;
                case 1:
                    listaParaMostrar = OrdenarListaEnlazada.OrdenarListaPorCodigo(Lista);
                    break;
                case 2:
                    listaParaMostrar = OrdenarListaEnlazada.OrdenarListaPorNombres(Lista);
                    break;
                default:
                    listaParaMostrar = Lista;
                    break;
            }

            ProcesosVentanaCliente.MostrarDatos(vista, listaParaMostrar);
            ProcesosVentanaCliente.LimpiarEntradas(vista);
        }

    }//fin del action

    private void clickTabla() {
        vista.tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {                            
                int filaSeleccionada = vista.tblDatos.getSelectedRow();
                String codbuscado = vista.tblDatos.getValueAt(filaSeleccionada, 1).toString();
                actual = Lista.BuscarCliente(codbuscado);
            }
        });

    }

}//fin de la clase
