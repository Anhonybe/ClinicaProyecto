package Procesos;

import Vista.VentanaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author NITRO 5
 */
public class PresentarVentanaPrincipal {
    public static void MostrarMenu(VentanaPrincipal vp){
       vp.setVisible(true);
       vp.setTitle("Clinica Veterinaria");
       vp.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
