package Clases;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;

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
    public boolean verificarStock() {
        if (this.tipo.equalsIgnoreCase("servicio")) {
            return true; 
        }
        return this.stock > 0;
    }

    public void guardarEnTxt()
    {

        try (FileWriter archivo = new FileWriter("src/Archivos/inventario.txt", true);
             PrintWriter escritor = new PrintWriter(archivo)) {
            
            // Guardamos los datos separados por comas
            escritor.println(codigo + "," + nombre + "," + precioCompra + "," + precioVenta + "," + stock + "," + tipo);
            
            // Mensaje de confirmación usando JOptionPane
            JOptionPane.showMessageDialog(null, "Producto '" + nombre + "' guardado correctamente en el archivo.");
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public String getCodigo() { return codigo;}
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


    public static void registrarProducto() {

        String codigo = JOptionPane.showInputDialog(
                "Ingrese código:");

        String nombre = JOptionPane.showInputDialog(
                "Ingrese nombre:");

        double precioCompra = Double.parseDouble(
                JOptionPane.showInputDialog(
                        "Ingrese precio de compra:")
        );

        double precioVenta = Double.parseDouble(
                JOptionPane.showInputDialog(
                        "Ingrese precio de venta:")
        );

        int stock = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Ingrese stock:")
        );

        String tipo = JOptionPane.showInputDialog(
                "Ingrese tipo (articulo/servicio):");

        Producto producto = new Producto(
                codigo,
                nombre,
                precioCompra,
                precioVenta,
                stock,
                tipo
        );

        producto.guardarEnTxt();

    }


    public static void mostrarProductos() {

        try {

            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/inventario.txt"));
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    contenido.append(
                            "══════════════════════\n"
                    );

                    contenido.append(
                            "Código: "
                                    + datos[0] + "\n"
                    );

                    contenido.append(
                            "Nombre: "
                                    + datos[1] + "\n"
                    );

                    contenido.append(
                            "Precio Compra: $"
                                    + datos[2] + "\n"
                    );

                    contenido.append(
                            "Precio Venta: $"
                                    + datos[3] + "\n"
                    );

                    contenido.append(
                            "Stock: "
                                    + datos[4] + "\n"
                    );

                    contenido.append(
                            "Tipo: "
                                    + datos[5] + "\n\n"
                    );

                }

            }

            lector.close();
            if (contenido.length() == 0) {
                contenido.append("No hay productos registrados.");

            }
            JOptionPane.showMessageDialog(
                    null,
                    contenido.toString(),
                    "INVENTARIO",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al leer archivo: "
                            + e.getMessage()
            );

        }

    }

    public static void Stock() {

        String codigoBuscar = JOptionPane.showInputDialog("Ingrese código del producto:");
        try {

            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/inventario.txt"));
            String linea;
            boolean encontrado = false;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equalsIgnoreCase(codigoBuscar)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(
                            null,
                            """
                            ═════ PRODUCTO ═════
                            
                            Código: """ + datos[0] +
                                    "\nNombre: " + datos[1] +
                                    "\nStock: " + datos[4] +
                                    "\nPrecio Venta: $" + datos[3]
                    );

                    break;
                }
            }
            lector.close();

            if (!encontrado) {
                JOptionPane.showMessageDialog(
                        null,
                        "Producto no encontrado."
                );

            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al leer archivo: "
                            + e.getMessage()
            );

        }

    }

}