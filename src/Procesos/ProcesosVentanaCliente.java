package Procesos;

import Modelo.*;
import EstructurasListas.*;
import Persistencia.DatosClientes;
import Vista.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ProcesosVentanaCliente {

    public static void LimpiarEntradas(VentanaCliente vcl) {
        vcl.txtCodigo.setText("");
        vcl.txtNombre.setText("");
        vcl.cbxNroMascota.setSelectedIndex(0);
        vcl.txtCelular.setText("");
        vcl.txtCodigo.requestFocus();
    }//fin del metodo

    public static Cliente LeerCliente(VentanaCliente vcl) {
        Cliente cli = new Cliente();
        cli.setCodigo(vcl.txtCodigo.getText());
        cli.setNombre(vcl.txtNombre.getText());
        cli.setNroMasc(vcl.cbxNroMascota.getSelectedItem().toString());
        cli.setNroCel(vcl.txtCelular.getText());
        return cli;
    }//fin de mÃ©todo

    public static ArrayList<Cliente> MostrarDatos(VentanaCliente vcl, ListaEnlazada Lista) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String titulos[] = {"Num", "Código", "Nombre", "Número de Mascotas", "Celular"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        vcl.tblDatos.setModel(modelo);
        //variables
        Nodo aux = Lista.getIni();
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0, numeracion = 0;
        while (aux != null) {
            numeracion++;
            listaClientes.add(aux.getCli());
            modelo.addRow(aux.getCli().Registro(numeracion));
//            System.out.println(aux.cli.getCodigo());
            switch (aux.getCli().getNroMasc()) {
                case "1":
                    c1++;
                    break;
                case "2":
                    c2++;
                    break;
                case "3":
                    c3++;
                    break;
                case "4":
                    c4++;
                    break;
            }//fin switch
            aux = aux.getSig();
        }//fin while
        vcl.txaResumen.setText("\nPersonas con 1 mascota  : " + c1
                + "\nPersonas con 2 mascotas : " + c2
                + "\nPersonas con 3 mascotas : " + c3
                + "\nPersonas con 4 mascotas : " + c4);
        return listaClientes;
    }//fin mostrar

    public static ArrayList<Cliente> getListaCliente(ListaEnlazada Lista) {
        ArrayList<Cliente> lista = new ArrayList<>();
        //variables
        Nodo aux = Lista.getIni();
        while (aux != null) {
            lista.add(aux.getCli());
            aux = aux.getSig();
        }//fin while
        return lista;
    }//fin mostrar

    public static void MostrarDatosNodo(Nodo actual, VentanaCliente vcl) {
        if (actual != null) {
            vcl.txtCodigo.setText(actual.getCli().getCodigo());
            vcl.txtNombre.setText(actual.getCli().getNombre());
            vcl.txtCelular.setText(actual.getCli().getNroCel());
            switch (actual.getCli().getCodigo()) {
                case "1":
                    vcl.cbxNroMascota.setSelectedIndex(0);
                    break;
                case "2":
                    vcl.cbxNroMascota.setSelectedIndex(1);
                    break;
                case "3":
                    vcl.cbxNroMascota.setSelectedIndex(2);
                    break;
                case "4":
                    vcl.cbxNroMascota.setSelectedIndex(3);
                    break;
            }//fin switch
        }//if
    }//fin mostrar datos nodo

}//fin de la clase

