/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Stephania Roblero
 */
import javax.swing.JOptionPane;

public class PapeleriaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   
        Administrador jefe = new Administrador("Daniel", "admin", "admin123");
        Cajero empleado = new Cajero("stephania", "cajero", "cajera29");

        String userEntrada = JOptionPane.showInputDialog("Usuario:");
        String passEntrada = JOptionPane.showInputDialog("Contraseña:");
        
    if (userEntrada == null || passEntrada == null) {
            JOptionPane.showMessageDialog(null, "Sesión cancelada.", "SALIDA", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

                if (userEntrada.equals(jefe.getUsername()) && passEntrada.equals(jefe.getPassword())) {
            JOptionPane.showMessageDialog(null, "¡Bienvenido Administrador: " + jefe.getNombreReal() + "!");
            mostrarMenuAdmin();
        } 
        else if (userEntrada.equals(empleado.getUsername()) && passEntrada.equals(empleado.getPassword())) {
            JOptionPane.showMessageDialog(null, "¡Bienvenido Cajero: " + empleado.getNombreReal() + "!");
            mostrarMenuCajero();
        } 
        else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "ERROR DE ACCESO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void mostrarMenuAdmin() {
        String[] opciones = {"Finanzas", "Inventario", "Nueva Venta", "Cerrar Sesión"};
        int seleccionar;
        do {
            seleccionar = (Integer)JOptionPane.showOptionDialog(null, "Seleccione una categoría de gestión", 
                    "PANEL DE CONTROL - ADMINISTRADOR", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            switch (seleccionar) {
                case 0: 
                    String[] f = {"Cortes de caja", "Historial", "Ver Tickets", "Volver"};
                    JOptionPane.showOptionDialog(null, "Módulo de Finanzas", "FINANZAS", 0, 1, null, f, f[0]);
                    break;
                case 1: // Inventario
                    String[] i = {"Registrar Producto", "Actualizar Stock", "Quitar Producto", "Volver"};
                    JOptionPane.showOptionDialog(null, "Gestión de Almacén", "INVENTARIO", 0, 1, null, i, i[0]);
                    break;
                case 2:
                    nuevaVenta();
                    break;
            }
        } while (seleccionar != 3 && seleccionar != -1);
    }

  
    public static void mostrarMenuCajero() {
        String[] opciones = {"Nueva Venta", "Ver Tickets", "Cerrar Sesión"};
        int selec;
        do {
            selec =(Integer)JOptionPane.showOptionDialog(null, "Operaciones de Venta", 
                    "PANEL DE CAJERO", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            if (selec == 0) nuevaVenta();
            if (selec == 1) JOptionPane.showMessageDialog(null, "Consultando historial de tickets...");
            
        } while (selec != 2 && selec != -1);
    }

  
    public static void nuevaVenta() {
        String[] metodos = {"Efectivo", "Transferencia"};
        int metpago =(Integer) JOptionPane.showOptionDialog(null, "Seleccione método de pago", "NUEVA VENTA", 
                0, JOptionPane.QUESTION_MESSAGE, null, metodos, metodos[0]);
        
        if (metpago != -1) {
            JOptionPane.showMessageDialog(null, "Venta pagada con: " + metodos[metpago]);
        }
    }
}