package Clases;

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
        this.tipo = tipo;
    }

    public boolean verificarStock() {

        if (this.tipo.equalsIgnoreCase("servicio")) {
            return true;
        }

        return this.stock > 0;
    }

    public void guardarEnTxt() {

        try (FileWriter archivo = new FileWriter(ruta, true);
             PrintWriter escritor = new PrintWriter(archivo)) {

            escritor.println(codigo + "," + nombre + "," + precioCompra + "," + precioVenta + "," + stock + "," + tipo);

            JOptionPane.showMessageDialog(null, "Producto '" + nombre + "' guardado correctamente.");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getCodigo() {return codigo;}

    public void setCodigo(String codigo) {this.codigo = codigo;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public double getPrecioCompra() {return precioCompra;}

    public void setPrecioCompra(double precioCompra) {this.precioCompra = precioCompra;}

    public double getPrecioVenta() {return precioVenta;}

    public void setPrecioVenta(double precioVenta) {this.precioVenta = precioVenta;}

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public static void registrarProducto() {

        try {

            String codigo = JOptionPane.showInputDialog("Ingrese código:"
            );

            if (codigo == null || codigo.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Código inválido."
                );

                return;
            }

            if (existeProducto(codigo)) {

                JOptionPane.showMessageDialog(null, "El código del producto ya existe."
                );

                return;
            }

            String nombre = JOptionPane.showInputDialog("Ingrese nombre:"
            );

            if (nombre == null || nombre.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Nombre inválido.");

                return;
            }

            double precioCompra = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio de compra:"));

            double precioVenta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio de venta:"));

            int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese stock:"));

            if (precioCompra < 0 || precioVenta < 0 || stock < 0) {JOptionPane.showMessageDialog(null, "No se permiten valores negativos.");

                return;
            }

            if (precioVenta < precioCompra) {JOptionPane.showMessageDialog(null, "El precio de venta no puede ser menor al de compra.");

                return;
            }

            String tipo = JOptionPane.showInputDialog("Ingrese tipo (articulo/servicio):");

            if (tipo == null || tipo.trim().isEmpty()) {JOptionPane.showMessageDialog(null, "Tipo inválido.");

                return;
            }

            if (!tipo.equalsIgnoreCase("articulo") && !tipo.equalsIgnoreCase("servicio"))
                {JOptionPane.showMessageDialog(null, "Tipo inválido.");

                return;
            }

            Producto producto = new Producto(codigo, nombre, precioCompra, precioVenta, stock, tipo);

            producto.guardarEnTxt();

        } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos.");
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

                    contenido.append("══════════════════════\n");
                    contenido.append("Código: " + datos[0] + "\n");
                    contenido.append("Nombre: " + datos[1] + "\n");
                    contenido.append("Precio Compra: $" + datos[2] + "\n");
                    contenido.append("Precio Venta: $" + datos[3] + "\n");
                    contenido.append("Stock: " + datos[4] + "\n");
                    contenido.append("Tipo: " + datos[5] + "\n\n");
                }
            }

            lector.close();

            if (contenido.isEmpty()) {
                contenido.append("No hay productos registrados."
                );
            }

            JOptionPane.showMessageDialog(null, contenido.toString(), "INVENTARIO", JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Error al leer archivo: " + e.getMessage()
            );
        }
    }

    public static void stock() {

        String codigoBuscar = JOptionPane.showInputDialog("Ingrese código del producto:");

        if (codigoBuscar == null || codigoBuscar.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código inválido."
            );

            return;
        }

        try {

            BufferedReader lector = new BufferedReader(new FileReader(ruta));

            String linea;

            boolean encontrado = false;

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equalsIgnoreCase(codigoBuscar)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(null,
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
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo: " + e.getMessage()
            );
        }
    }

    public static boolean existeProducto(
            String codigoBuscar) {

        try {

            BufferedReader lector = new BufferedReader(new FileReader(ruta));

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
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()
            );
        }

        return false;
    }

    public static void actualizarPrecio(
            String tipo) {

        String cod = JOptionPane.showInputDialog(null, "Introduce el código del producto", "Actualizar Precio", JOptionPane.WARNING_MESSAGE);

        if (cod == null || cod.trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Código inválido.");

            return;
        }

        String linea;

        try {

            double p = Double.parseDouble(
                    JOptionPane.showInputDialog(null, "Introduce el nuevo precio", "Actualizar Precio", JOptionPane.WARNING_MESSAGE));

            if (p < 0) {

                JOptionPane.showMessageDialog(null, "No se permiten precios negativos."
                );

                return;
            }

            boolean fndProd = false;

            try (BufferedReader br = new BufferedReader(new FileReader(ruta));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
                while ((linea = br.readLine()) != null) {
                    String[] prod = linea.split(",");
                    if (cod.equals(prod[0])) {

                        switch (tipo) {
                            case "c":

                                prod[2] =
                                        String.valueOf(p);

                                fndProd = true;

                                break;

                            case "v":

                                prod[3] =
                                        String.valueOf(p);

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

                if (!fndProd) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Producto no encontrado",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } catch (IOException e) {

                IO.println(e);
            }

            try {

                Path archivoOriginal =
                        Paths.get(ruta);

                Path archivoTemp =
                        Paths.get(temp);

                Files.move(
                        archivoTemp,
                        archivoOriginal,
                        StandardCopyOption.REPLACE_EXISTING
                );

            } catch (IOException e) {

                IO.println(e);
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Ingrese un número válido."
            );
        }
    }

    public static void agregarStock() {

        String codigo = JOptionPane.showInputDialog("Ingrese código del producto:");

        if (codigo == null || codigo.trim().isEmpty()) {JOptionPane.showMessageDialog(null, "Código inválido.");

            return;
        }

        try {

            int cantidadAgregar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad a agregar:"));

            if (cantidadAgregar <= 0) {JOptionPane.showMessageDialog(null, "Cantidad inválida.");

                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(ruta));

            BufferedWriter escritor = new BufferedWriter(new FileWriter(temp)
                    );

            String linea;

            boolean encontrado = false;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos[0].equalsIgnoreCase(codigo)) {

                    encontrado = true;

                    int stockActual =
                            Integer.parseInt(datos[4]);

                    stockActual += cantidadAgregar;

                    datos[4] =
                            String.valueOf(stockActual);

                    JOptionPane.showMessageDialog(
                            null,
                            "Stock actualizado.\nNuevo stock: "
                                    + stockActual
                    );
                }

                escritor.write(
                        String.join(",", datos)
                );

                escritor.newLine();
            }

            lector.close();

            escritor.close();

            if (!encontrado) {

                JOptionPane.showMessageDialog(null, "Producto no encontrado."
                );

                return;
            }

            Path original = Paths.get(ruta);

            Path temporal = Paths.get(temp);

            Files.move(
                    temporal,
                    original,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Ingrese números válidos."
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error: " + e.getMessage()
            );
        }
    }
}