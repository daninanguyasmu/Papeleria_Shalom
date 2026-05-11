package Clases;

import javax.swing.JOptionPane;

public class Cajero extends Usuario{

    public Cajero (String nombre, String usuario, String contrasena ){

        super (nombre, usuario, contrasena, 2);

    }

    public void Menu(){

        JOptionPane.showMessageDialog(null,
                "------- Realizar Acciones - Cajero -------\n" +
                        "1. Nueva Venta\n" +
                        "2. Finanzas (solo tickets)");

    }
}
