package Clases;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;

public class Producto {
    private String codigo;
    private String nombre;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private String tipo;
    final private static String ruta = "src/Archivos/inventario.txt";
    final private static String temp = "src/Archivos/temp_inventario.txt";

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

        try (FileWriter archivo = new FileWriter(ruta, true);
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

        try {

            String codigo = JOptionPane.showInputDialog(
                    "Ingrese código:");

            if (codigo == null || codigo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Código inválido.");
                return;
            }
            if(existeProducto(codigo)){
                JOptionPane.showMessageDialog(null,
                        "El código del producto ya existe.");
                return;
            }

            String nombre = JOptionPane.showInputDialog(
                    "Ingrese nombre:");

            if (nombre == null || nombre.trim().isEmpty()) {JOptionPane.showMessageDialog(null, "Nombre inválido.");
                return;
            }
            double precioCompra = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio de compra:"));

            double precioVenta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio de venta:"));

            int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese stock:"));

            if(precioCompra < 0 || precioVenta < 0 || stock < 0){

                JOptionPane.showMessageDialog(null,
                        "No se permiten valores negativos.");
                return;
            }

            String tipo = JOptionPane.showInputDialog(
                    "Ingrese tipo (articulo/servicio):");
            if (tipo == null || tipo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Tipo inválido.");
                return;
            }
            Producto producto = new Producto(codigo, nombre, precioCompra, precioVenta, stock, tipo);
            producto.guardarEnTxt();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Ingrese valores numéricos válidos."
            );

        }
    }


    public static void mostrarProductos() {

        try {

            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    contenido.append(
                            "══════════════════════\n"
                    );
                    contenido.append("Código: ").append(datos[0]).append("\n");
                    contenido.append("Nombre: ").append(datos[1]).append("\n");
                    contenido.append("Precio Compra: $").append(datos[2]).append("\n");
                    contenido.append("Precio Venta: $").append(datos[3]).append("\n");
                    contenido.append("Stock: ").append(datos[4]).append("\n");
                    contenido.append("Tipo: ").append(datos[5]).append("\n\n");
                }

            }

            lector.close();
            if (contenido.isEmpty()) {
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

    public static void stock() {

        String codigoBuscar = JOptionPane.showInputDialog("Ingrese código del producto:");
        try {

            BufferedReader lector = new BufferedReader(new FileReader(ruta));
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
                           \s
                            Código:\s""" + datos[0] +
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

    public static boolean existeProducto(String codigoBuscar) {

        try {

            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/inventario.txt"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equalsIgnoreCase(codigoBuscar)) {
                    lector.close();
                    return true;
                }
            }
            lector.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e.getMessage());
        }
        return false;
    }

    public static void actualizarPrecio(String tipo){
        String cod = JOptionPane.showInputDialog(null, "Introduce el código del producto", "Cambiar Precio Compra", JOptionPane.WARNING_MESSAGE);
        String linea;
        double p = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduce el nuevo precio", "Cambiar Precio Compra", JOptionPane.WARNING_MESSAGE));
        boolean fndProd = false;

        try(BufferedReader br = new BufferedReader(new FileReader(ruta));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp))){
            while((linea = br.readLine()) != null){
                String[] prod = linea.split(",");
                if (cod.equals(prod[0])){
                    switch (tipo){
                        case "c":
                            prod[2] = String.valueOf(p);
                            fndProd = true;
                            break;
                        case "v":
                            prod[3] = String.valueOf(p);
                            fndProd = true;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "OPERACION NO RECONOCIDA");
                    }
                }
                linea = String.join(",", prod);
                bw.write(linea);
                bw.newLine();
            }

            if (!fndProd){
                JOptionPane.showMessageDialog(null, "Producto no Encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }catch (IOException e){
            IO.println(e);
        }
        try{
            Path archivoOriginal = Paths.get(ruta);
            Path archivoTemp = Paths.get(temp);

            Files.move(archivoTemp, archivoOriginal, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            IO.println(e);
        }
    }

}