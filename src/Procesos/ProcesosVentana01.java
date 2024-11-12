package Procesos;

import Modelo.*;
import Vista.VentanaRegistroMascota;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NITRO 5
 */
public class ProcesosVentana01 {

    // Metodo para Enviar los datos de los txtField a la tabla
    public static void MostrarEnTabla(VentanaRegistroMascota f1, Mascota[] a) {
        String titulos[] = {"N� Orden", "Codigo", "Nombre", "Raza", "Edad", "Propietario", "Fecha de Registro"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        mt.setRowCount(0);
        int cont = 0;
        for (Mascota mas : a) {
//            System.out.println("cd: " + mas);
            if (mas != null) {
                cont++;
                mt.addRow(mas.Registro(cont));
            }
        }
        f1.tblDatos.setModel(mt);
    }

    //Muestra el Resumen de cantidad de Mascotas en txtArea
    public static void MostrarResumen(VentanaRegistroMascota f1, Mascota[] a) {
//        f1.txaResumenMascota.setText("Cantidad de Mascotas    : " + ArregloMascotas.getCantMascotas());
    }//fin método  

    //Configura Carcteristicas de la VentanaRegistro Mascotas
    public static void PresentarVentanaRegistroMascota(VentanaRegistroMascota f1) {
        f1.setTitle("Registro de Mascotas");
    }//fin método 

    //Este metodo recupera los datos de los componentes Mascota
    public static Mascota LeerMascota(VentanaRegistroMascota f1) {
        Mascota mascota = new Mascota();
        mascota.setCodigo(f1.txtID.getText());
        mascota.setNombre(f1.txtNombre.getText());
        mascota.setRaza(f1.txtRaza.getText());
        mascota.setEdad(Integer.parseInt(f1.txtEdad.getText()));
        mascota.setPropietario(f1.comboPropietario.getSelectedItem().toString());
        mascota.setFecha(f1.txtFecha.getText());
        return mascota;
    }//fin de método

    //Este metodo limpia todos los campos de datos de Registro
    public static void LimpiarEntradas(VentanaRegistroMascota f1) {
        f1.txtID.setText("");
        f1.txtNombre.setText("");
        f1.txtRaza.setText("");
        f1.txtEdad.setText("");
        f1.comboPropietario.setSelectedIndex(0);
        f1.txtFecha.setText("");
    }//fin del metodo

    public static void EliminarRegistro(VentanaRegistroMascota f1) {
        DefaultTableModel mt = (DefaultTableModel) f1.tblDatos.getModel();
        mt.setRowCount(0);
        f1.tblDatos.setModel(mt);
//        mt.removeRow(f1.tblDatos.getSelectedRow());

    }//fin del metodo

}
