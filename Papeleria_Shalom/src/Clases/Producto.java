package Clases;
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
public class Producto {
    private String codigo;
   private String nombre;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private String tipo;

    public Producto(String codigo, String nombre, double precioCompra, double precioVenta, int stock, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.tipo=tipo;
        
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
    public void guardarEnTxt()
    {
        
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
    

    // --- MÉTODO DE PRUEBA TEMPORAL ---
    public static void main(String[] args) {
        // 1. Creamos un producto de prueba (como si lo estuviera metiendo el cajero)
        Producto productoPrueba = new Producto("001", "Coca Cola 600ml", 12.50, 18.00, 24, "articulo");
        
        // 2. Probamos si nos dice correctamente si hay stock
        System.out.println("¿Hay stock disponible? " + productoPrueba.verificarStock());
        
        // 3. Probamos el guardado en el archivo de texto
        productoPrueba.guardarEnTxt();
    }
}
