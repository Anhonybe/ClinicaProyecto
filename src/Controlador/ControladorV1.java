package Controlador;

import EstructurasListas.ListaEnlazada;
import Modelo.*;
import Ordenamientos.OrdenarArreglosObjetos;
import Persistencia.DatosClientes;
import Procesos.Busquedas;
import Procesos.ProcesosVentana01;
import Procesos.ProcesosVentanaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.VentanaRegistroMascota;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class ControladorV1 implements ActionListener {

    VentanaRegistroMascota vista;
    ArregloMascotas Lista;
    Mascota mascota;

    public ControladorV1(VentanaRegistroMascota vr) {
        vista = vr;
        //Cada componente se registra como un Action Listener
        vista.txtID.addActionListener(this);
        vista.txtNombre.addActionListener(this);
        vista.txtRaza.addActionListener(this);
        vista.txtEdad.addActionListener(this);
        vista.comboPropietario.addActionListener(this);
        vista.txtFecha.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnOrdenar.addActionListener(this);
        mostrarNumMascotas();
        //Faltaria la Opcion Subir Foto
        Lista = new ArregloMascotas();
        Lista.RecuperarDeArchivo(); //-- Recuperar Archivo Binario (Class: Arreglo Mascotas)
        Lista.ActualizarCantidadMascotas(); //-- Actualizar cant de Mascotas (Class: Arreglo Mascotas)
        ProcesosVentana01.MostrarEnTabla(vista, Lista.getLista()); //-- Mostar Datos en tabla
//        ProcesosVentana01.MostrarResumen(vista, Lista.getLista()); //-- Mostar Resumen de Datos
        ProcesosVentana01.PresentarVentanaRegistroMascota(vista); //--- Caracteristicas de la Ventana(titulo)
        ListaEnlazada listaEnlazada = DatosClientes.RecuperarDeArchivo();
        filaSeleccionada = -1;
        /*El combo propietarios se rellena gracias a la lista clientes que
        me retorna el método getListaClientes sacandolo del archivo.bin     
        */       
        listaClientes = ProcesosVentanaCliente.getListaCliente(listaEnlazada);
        rellenarComboPropietarios();
        eventoClickTabla();
        initBotones();
    }

    ArrayList<Cliente> listaClientes;
    
    private void rellenarComboPropietarios() {
        vista.comboPropietario.removeAllItems();
        for (Cliente cliente : listaClientes) {
            String idPropietario = cliente.getCodigo();
            vista.comboPropietario.addItem(idPropietario);
        }
    }
    int filaSeleccionada = -1;

    private void eventoClickTabla() {
        vista.tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filaSeleccionada = vista.tblDatos.getSelectedRow();
                String idPropietario = vista.tblDatos.getValueAt(filaSeleccionada, 5).toString();
                mostrarInfoPropietario(idPropietario);
            }
        });
    }

    private void mostrarNumMascotas() {
        vista.txtNumMascotas.setText("Mascotas: " + listaMascotas().length);
    }

    private void mostrarInfoPropietario(String idPropietario) {
        vista.txaResumenMascota.setText("");
        String nombre = getCliente(idPropietario).getNombre();
        String numeroMascota = getCliente(idPropietario).getNroMasc();
        String numeroCell = getCliente(idPropietario).getNroCel();
        vista.txaResumenMascota.append("\n\n\tDATOS DEL PROPIETARIO");
        vista.txaResumenMascota.append("\n\n");
        vista.txaResumenMascota.append("\n - Codigo         : " + idPropietario);
        vista.txaResumenMascota.append("\n - Nombre         : " + nombre);
        vista.txaResumenMascota.append("\n - Numero Mascota : " + numeroMascota);
        vista.txaResumenMascota.append("\n - Numero Celular : " + numeroCell);
    }

    private Cliente getCliente(String idPropietario) {
        for (Cliente cliente : listaClientes) {
            String tempId = cliente.getCodigo();
            if (idPropietario.equals(tempId)) {
                return cliente;
            }
        }
        return null;
    }

    private void initBotones() {
        eliminar();
    }

    private void eliminar() {
        vista.btnEliminar.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (filaSeleccionada >= 0) {
                String idPropietario = vista.tblDatos.getValueAt(filaSeleccionada, 1).toString();
                Lista.EliminarMascota(idPropietario);
                ProcesosVentana01.MostrarEnTabla(vista, Lista.getLista()); //--- Muestra los datos en tabla
                Lista.GuardarEnArchivo();
//                ProcesosVentana01.MostrarResumen(vista, Lista.getLista()); //--- Muestra el Resumen en txtArea
                Lista.GuardarEnArchivo();
                mostrarNumMascotas();
            } else {
                System.out.println("SELECCIONA");
                JOptionPane.showMessageDialog(null, "Selecciona una fila", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    //Metodo para escuchar los eventos de los componentes de Ventana Registro
    @Override
    public void actionPerformed(ActionEvent e) {
        //GUARDAR
        if (e.getSource() == vista.btnGuardar) {
            //---> continua aqui
            mascota = ProcesosVentana01.LeerMascota(vista);
            Lista.AgregarMascota(mascota);
            Lista.GuardarEnArchivo();
            Lista.RecuperarDeArchivo();
            mostrarNumMascotas();
            ProcesosVentana01.MostrarEnTabla(vista, Lista.getLista()); //--- Muestra los datos en tabla
//            ProcesosVentana01.MostrarResumen(vista, Lista.getLista()); //--- Muestra el Resumen en txtArea
            ProcesosVentana01.LimpiarEntradas(vista); //--- Limpia los campos de datos
        }

        //METODOS BUSCAR
        if (e.getSource() == vista.btnBuscar) {
            String codMascota = vista.txtBusqCod.getText();
            int opcCombo = vista.cbxOrdenar.getSelectedIndex();
            ArregloMascotas arregloMas = new ArregloMascotas();
            arregloMas.RecuperarDeArchivo();
            Mascota listaMascot[] = arregloMas.getLista();
            switch (opcCombo) {
                case 0:
                    Mascota mascotaEncontrada = Busquedas.buscarMascotaPorCodigo(listaMascot, codMascota);
                    if (mascotaEncontrada != null) {
                        JOptionPane.showMessageDialog(null, mascotaEncontrada.imprimir());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 1:
                    Mascota buSecuencial = Busquedas.buscarMascotaPorCodigo_SecuencialRecursiva(listaMascot, codMascota);
                    if (buSecuencial != null) {
                        JOptionPane.showMessageDialog(null, buSecuencial.imprimir());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        }

        //ACCION BOTON ORDENAR
        if (e.getSource() == vista.btnOrdenar) {           
            ArregloMascotas arregloMas = new ArregloMascotas();
            arregloMas.RecuperarDeArchivo();
            Mascota listaMascot[] = arregloMas.getLista();
            Mascota[] listaordenada = OrdenarArreglosObjetos.
                            OrdernarMascotasPorCodigo(listaMascot);
            System.out.println("EEEOOO");
                    ProcesosVentana01.MostrarEnTabla(vista, listaordenada);
                    ProcesosVentana01.MostrarResumen(vista, listaordenada);
        }
    }

    public static Mascota[] listaMascotas() {
        ArregloMascotas arregloMas = new ArregloMascotas();
        arregloMas.RecuperarDeArchivo();
        Mascota listaMascot[] = arregloMas.getLista();
        Mascota[] mascotasFiltradas = Arrays.stream(listaMascot)
                .filter(pet -> pet != null)
                .toArray(Mascota[]::new);
        return mascotasFiltradas;
    }

}
