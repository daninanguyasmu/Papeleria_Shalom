package Clases;

import javax.swing.JOptionPane;

public class Cajero extends Usuario {

    public Cajero(String nombre, String usuario, String contrasena) {

        super(nombre, usuario, contrasena, 2);
    }

    public void Menu() {

        int opcion;

        do {

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            """
                            ╔═════════ CAJERO ═════════╗
                            
                            1. Nueva Venta
                            2. Inventario
                            3. Cerrar Sesión
                            
                            Seleccione una opción:
                            """
                    )
            );

            switch (opcion) {

                case 1:

                    Venta venta = new Venta(getNombre());

                    venta.menuVenta();

                    break;

                case 2:

                    inventario();

                    break;

                case 3:

                    JOptionPane.showMessageDialog(null,
                            "Cerrando sesión...");

                    break;

                default:

                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");

            }

        } while (opcion != 3);

    }

    public void inventario() {

        int opcion;

        do {

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            """
                            ╔════════ INVENTARIO ════════╗
                            
                            1. Consultar Productos
                            2. Verificar Stock
                            3. Volver
                            
                            Seleccione una opción:
                            """
                    )
            );

            switch (opcion) {

                case 1:

                    Producto.mostrarProductos();

                    break;

                case 2:

                    Producto.Stock();

                    break;

                case 3:

                    JOptionPane.showMessageDialog(null,
                            "Regresando...");

                    break;

                default:

                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");

            }

        } while (opcion != 3);

    }
}