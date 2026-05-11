/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author jorlo
 */
public class Productos {
    private String codigo;
    private String nombre;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private String tipo;

    public Productos(String codigo, String nombre, double precioCompra, double precioVenta, int stock, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.tipo = tipo;
    }

    // Método para checar disponibilidad de artículos
    public boolean verificarStock() {
        if (this.tipo.equalsIgnoreCase("servicio")) {
            return true; // Los servicios no suelen tener límite de stock de la misma forma
        }
        return this.stock > 0;
    }
    // Método para guardar el .txt
    /**
     *
     */
    public void guardarEnTxt() {
        try (FileWriter archivo = new FileWriter("inventario_productos.txt", true);
             PrintWriter escritor = new PrintWriter(archivo)) {
            
            // Guardamos los datos separados por comas
            escritor.println(codigo + "," + nombre + "," + precioCompra + "," + precioVenta + "," + stock + "," + tipo);
            
            // Mensaje de confirmación usando JOptionPane
            JOptionPane.showMessageDialog(null, "Producto '" + nombre + "' guardado correctamente en el archivo.");
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- GETTERS Y SETTERS ---
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    // --- MÉTODO MAIN DE PRUEBA INTERACTIVA ---
    public static void main(String[] args) {
        
        // 1. Pedimos cada dato al usuario usando JOptionPane
        String codigo = JOptionPane.showInputDialog("Ingresa el código del producto:");
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del producto:");
        
        // Convertimos los textos a double para los precios
        String textoPrecioCompra = JOptionPane.showInputDialog("Ingresa el precio de compra:");
        double precioCompra = Double.parseDouble(textoPrecioCompra);
        
        String textoPrecioVenta = JOptionPane.showInputDialog("Ingresa el precio de venta:");
        double precioVenta = Double.parseDouble(textoPrecioVenta);
        
        // Convertimos el texto a int para el stock
        String textoStock = JOptionPane.showInputDialog("Ingresa el stock inicial:");
        int stock = Integer.parseInt(textoStock);
        
        String tipo = JOptionPane.showInputDialog("Ingresa el tipo (articulo o servicio):");

        // 2. Creamos el objeto instanciando tu clase 'Productos'
        Productos productoNuevo = new Productos(codigo, nombre, precioCompra, precioVenta, stock, tipo);
        
        // 3. Armamos el mensaje de confirmación usando los getters
        String mensaje = "--- PRODUCTO CREADO EXITOSAMENTE ---\n\n"
                       + "Código: " + productoNuevo.getCodigo() + "\n"
                       + "Nombre: " + productoNuevo.getNombre() + "\n"
                       + "Precio de Compra: $" + productoNuevo.getPrecioCompra() + "\n"
                       + "Precio de Venta: $" + productoNuevo.getPrecioVenta() + "\n"
                       + "Stock: " + productoNuevo.getStock() + " unidades\n"
                       + "Tipo: " + productoNuevo.getTipo();
        
        // 4. Mostramos el resumen
        JOptionPane.showMessageDialog(null, mensaje, "Información del Producto", JOptionPane.INFORMATION_MESSAGE);
        
        // 5. Guardamos en el TXT usando tu método
        productoNuevo.guardarEnTxt();
    }
}