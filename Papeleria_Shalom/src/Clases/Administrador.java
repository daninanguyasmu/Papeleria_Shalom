package Clases;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {

    public Administrador (String nombre, String usuario, String contrasena){

        super (nombre, usuario, contrasena, 1); // mando directo el tipo 1, porque ya sabemos que es admin

    }

    public void Menu(){

        JOptionPane.showMessageDialog(null,
                "------- Opciones Para Administrador ------- \n" +
                        "1. Finanzas\n" +
                        "2. Inventario\n" +
                        "3. Nueva Venta");

    }

}


