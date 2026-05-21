package Clases;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null,
                """
                ╔════════════════════════╗
                         PAPELERÍA SHALOM
                ╚════════════════════════╝
                        Bienvenido al sistema
                """);

        Administrador jefe = new Administrador("Daniel", "admin", "admin123");
        Cajero empleado = new Cajero("Stephania", "cajero", "cajera29");

        boolean acceso = false;

        while (!acceso) {
            String usuarioEntrada = JOptionPane.showInputDialog(
                    "Ingrese su usuario:");

            String contrasenaEntrada = JOptionPane.showInputDialog(
                    "Ingrese su contraseña:");

            if (usuarioEntrada == null || contrasenaEntrada == null) {

                JOptionPane.showMessageDialog(null,
                        "Sesión cancelada.");

                return;
            }

            if (usuarioEntrada.equals(jefe.getUsuario())
                    && contrasenaEntrada.equals(jefe.getContrasena())) {

                JOptionPane.showMessageDialog(null,
                        "Bienvenido Administrador: "
                                + jefe.getNombre());
                acceso = true;
                jefe.menu();
            } else if (usuarioEntrada.equals(empleado.getUsuario())
                    && contrasenaEntrada.equals(empleado.getContrasena())) {

                JOptionPane.showMessageDialog(null,
                        "Bienvenido Cajero: "
                                + empleado.getNombre());
                acceso = true;
                empleado.menu();
            } else {

                JOptionPane.showMessageDialog(null,
                        "Usuario o contraseña incorrectos.\n"
                                + "Intente nuevamente.");
            }
        }

    }
}