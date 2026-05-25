package Clases;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {

    public Administrador (String nombre, String usuario, String contrasena){

        super (nombre, usuario, contrasena, 1);

    }

    public void menu() {

        int opcion;

        do {

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            """
                            ╔════════ ADMINISTRADOR ════════╗
                            
                            1. Finanzas
                            2. Inventario
                            3. Nueva Venta
                            4. Cerrar Sesión
                            
                            Seleccione una opción:
                            """
                    )
            );

            switch (opcion) {

                case 1:

                    finanzas();

                    break;

                case 2:

                    inventario();

                    break;

                case 3:

                    Venta venta = new Venta(getNombre());

                    venta.menuVenta();

                    break;

                case 4:

                    JOptionPane.showMessageDialog(null,
                            "Cerrando sesión...");

                    break;

                default:

                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");

            }

        } while (opcion != 4);

    }


    public void inventario() {

        int opcion;

        do {

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            """
                            ╔════════ INVENTARIO ════════╗
                            
                            1. Registrar Producto
                            2. Ver productos
                            3. Actualizar Precio De Compra (producto)
                            4. Actualizar Precio De Venta (producto)
                            5. Agregar Stock
                            6. Volver
                            
                            Seleccione una opción:
                            """
                    )
            );

            switch (opcion) {

                case 1:

                    Producto.registrarProducto();

                    break;

                case 2:

                    Producto.mostrarProductos();

                    break;

                case 3:

                    Producto.actualizarPrecio("c");

                    break;

                case 4:

                    Producto.actualizarPrecio("v");

                    break;

                case 5:

                    Producto.agregarStock();

                    break;

                case 6:

                    JOptionPane.showMessageDialog(null,
                            "Regresando...");

                    break;

                default:

                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");

            }

        } while (opcion !=6);

    }

    public void finanzas() {

        int opcion;

        do {

            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            """
                            ╔════════ FINANZAS ════════╗
                            
                            1. Reporte Financiero
                            2. Ver Historial de Ventas
                            3. Volver
                            
                            Seleccione una opción:
                            """
                    )
            );

            switch (opcion) {

                case 1:

                    Venta.reporteFinanciero();

                    break;

                case 2:

                    Venta.mostrarHistorialVentas();

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

