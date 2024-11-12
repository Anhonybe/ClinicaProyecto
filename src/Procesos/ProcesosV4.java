package Procesos;

import Vista.*;
import Pila.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FAMILIA
 */
public class ProcesosV4 {

    public static Empleado LeerEmpleado(VentanaEmpleado vem) {
        String cod = vem.txtCodigoEmp.getText();
        String nombre = vem.txtNomEmp.getText();
        String condicion = vem.cbxCondicion.getSelectedItem().toString();
        String area = vem.comboArea.getSelectedItem().toString();
        return new Empleado(cod, nombre, condicion, area);
    }

    public static void LimpiarEntradas(VentanaEmpleado vem) {
        vem.txtCodigoEmp.setText("");
        vem.txtNomEmp.setText("");
        vem.cbxCondicion.setSelectedIndex(0);
        vem.txtCodigoEmp.requestFocus();
    }

    public static void Presentacion(VentanaEmpleado vem) {
        vem.setTitle("Registro de Empleados - Uso de Pilas");
        vem.cbxCondicion.removeAllItems();
        vem.cbxCondicion.addItem("DOCTOR");
        vem.cbxCondicion.addItem("ASISTENTE");
        vem.cbxCondicion.addItem("SECRETARIO/A");
        vem.cbxCondicion.addItem("LIMPIEZA");
    }

    public static void MostrarDatosEnTabla(VentanaEmpleado vem, PilaEmpleado lista) {
        String[] titulos = {"Nro", "Codigo", "Nombre", "Condicion", "Area"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        vem.tblDatosEmp.setModel(mt);
        for (int i = 0; i < lista.getPila().size(); i++) {
            Empleado emp = lista.getPila().get(i);
            String[] rowData = {
                String.valueOf(i + 1),
                emp.getCod(),
                emp.getNombre(),
                emp.getCondicion(),
                emp.getArea()
            };
            mt.addRow(rowData);
        }
    }
}
