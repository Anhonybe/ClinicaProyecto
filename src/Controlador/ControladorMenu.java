package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Procesos.PresentarVentanaPrincipal;
import Procesos.*;
import Vista.*;

/**
 *
 * @author NITRO 5
 */
public class ControladorMenu implements ActionListener {

    //Declaracion de instancia VentanaPrincipal (vista)
    VentanaPrincipal vista;

    //Constructor
    public ControladorMenu(VentanaPrincipal vp) {
        vista = vp;
        PresentarVentanaPrincipal.MostrarMenu(vp); //se muestra el Desktop
        //Cada Opcion se registra como un Action Listener
        vista.opcion01.addActionListener(this);
        vista.opcion02.addActionListener(this);
        vista.opcion03.addActionListener(this);
        vista.opcion04.addActionListener(this);
        vista.opcion05.addActionListener(this);
        vp.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/_Icons/logotipo.png")));
    }

    //Metodo para escuchar los eventos de los componentes de VentanaPrincipal
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.opcion01) {
            //ActionEvent que ejecuta la opcion01 abre (VentanaRegistro)
            VentanaRegistroMascota vr = new VentanaRegistroMascota();
            ControladorV1 cv1 = new ControladorV1(vr);
            vr.setTitle("Registro de Datos de la Mascota"); //-- Titulo de Ventana
            PresentarMenu.Centrar(vr, vista.dpnEscritorio);
        }

        if (e.getSource() == vista.opcion02) {
            //ActionEvent que ejecuta la opcion02 abre (VentanaCitas)
            VentanaCitas vc = new VentanaCitas();
//           ControladorV2 cv2 =  new ControladorV2(vc); 
            vc.setTitle("Registro de Cita de la Mascota"); //-- Titulo de Ventana
            PresentarMenu.Centrar(vc, vista.dpnEscritorio);
        }

        //Ventana 3 (opcion03)
        if (e.getSource() == vista.opcion03) {

            VentanaCliente vcl = new VentanaCliente();
            ControladorV3 cv3 = new ControladorV3(vcl);
            vcl.setTitle("Registro de Datos de Cliente");
            PresentarMenu.Centrar(vcl, vista.dpnEscritorio);
        }
        //Ventana 4 (opcion04)
        if (e.getSource() == vista.opcion04) {
            //ActionEvent que ejecuta la opcion04 abre (VentanaEmpleado)
            VentanaEmpleado vem = new VentanaEmpleado();
            ControladorV4 cv4 = new ControladorV4(vem);
            vem.setTitle("Registro de Datos de Empleado");
            PresentarMenu.Centrar(vem, vista.dpnEscritorio);
        }
        if (e.getSource() == vista.opcion05) {
            //ActionEvent que ejecuta la opcion04 abre (VentanaEmpleado)
            VentanaServicios servi = new VentanaServicios();
//            ControladorV5 cv5 = new ControladorV5(servi);
            servi.setTitle("Registro de Datos de Servicios");
            PresentarMenu.Centrar(servi, vista.dpnEscritorio);
        }
    }
}
