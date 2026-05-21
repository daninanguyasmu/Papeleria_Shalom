package Clases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Arrays;

public class Venta {
    int folio;
    String nombreVendedor;
    LocalDateTime fecha;
    ArrayList<String> productos = new ArrayList<>();
    double total;
    //Ticket ticket;

    String rutaContandorVentas = "src/Archivos/contador_ventas.txt";
    String rutaProductos = "src/Archivos/inventario.txt";


    public Venta(String nv) {
        this.folio = this.getContadorVentas() + 1;
        this.nombreVendedor = nv;
        this.fecha = LocalDateTime.now();
        this.total = 0;
    }

    public void menuVenta() {
        String menu = """
                1. Agregar Producto
                2. Eliminar Producto
                3. Buscar Producto
                4. Finalizar Venta
                5. Salir""";
        int opcion;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    String cod = JOptionPane.showInputDialog(null, "Ingrese el código del producto que desea buscar");
                    JOptionPane.showMessageDialog(null, buscarProducto(cod));
                    break;
                case 4:
                    if (productos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "¡Agregue productos a la venta!");
                        break;
                    }else{
                        guardarVenta();
                        break;
                    }

            }
        } while (opcion != 5);

    }

    public void agregarProducto() {
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del producto que desea agregar");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto"));
        String fndProd = buscarProducto(codigo, cantidad);

        if (fndProd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado o cantidad insuficiente", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            productos.add(fndProd);
            Inventario.actualizarStock(codigo, cantidad, true);
        }
        mostrarProductos();
    }

    public void eliminarProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos para eliminar.", "Eliminar producto", JOptionPane.WARNING_MESSAGE);
        } else {
            mostrarVenta();
            boolean fndProd = false;
            int index = 0;
            int cantProd = 0;
            String codigoBuscar = JOptionPane.showInputDialog(null, "Ingrese el código del producto que desee eliminar");
            for (int i = 0; i < productos.size(); i++) {
                String prod = productos.get(i);
                IO.println(prod);
                String[] codigoProd = prod.split("\\|");
                IO.println(Arrays.toString(codigoProd));
                if (codigoBuscar.equals(codigoProd[0])) {
                    fndProd = true;
                    index = i;
                    cantProd = Integer.parseInt(codigoProd[3]);
                    break;
                }
            }
            if (fndProd) {
                productos.remove(index);
                Inventario.actualizarStock(codigoBuscar, cantProd, false);
            } else {
                JOptionPane.showMessageDialog(null, "Producto con el código: " + codigoBuscar + " no encontrado");
            }
        }
    }


    public void guardarVenta() {
        this.total = 0;
        String productosTexto = "";
        for (int i = 0; i < productos.size(); i++) {
            String prod = productos.get(i);
            String[] spltProd = prod.split("\\|");
            productosTexto += spltProd[1] + " x" + spltProd[3];
            if (i < productos.size() - 1) {
                productosTexto += " - ";
            }
            this.total += Double.parseDouble(spltProd[4]);
        }

        try (FileWriter fw = new FileWriter("src/Archivos/ventas.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(
                            this.folio + "," +
                            this.fecha + "," +
                            this.nombreVendedor + "," +
                            productosTexto + "," +
                            this.total
            );

            JOptionPane.showMessageDialog(null,
                    "Venta '" + this.folio +
                            "' registrada correctamente.");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Error al guardar la venta: " + e);
        }
        setContadorVentas();
        productos.clear();
    }

    private int getContadorVentas() {
        try {

            FileReader fr = new FileReader(rutaContandorVentas);
            BufferedReader br = new BufferedReader(fr);

            String cont = br.readLine();
            return Integer.parseInt(cont);

        } catch (Exception e) {
            IO.print(e);
        }
        return 0;
    }

    private void setContadorVentas() {
        try (PrintWriter pw = new PrintWriter(rutaContandorVentas)) {
            pw.print(folio);
        } catch (IOException e) {
            IO.println("Error:" + e);
        }
    }

    private String buscarProducto(String id, int cant) {
        try (FileReader fr = new FileReader(rutaProductos);
             BufferedReader br = new BufferedReader(fr);){

            String pr;

            while ((pr = br.readLine()) != null) {
                String[] prod = pr.split(",");
                if (prod[0].equals(id)) {
                    if (Integer.parseInt(prod[4]) > 0 && Integer.parseInt(prod[4]) >= cant) {
                        String fndProducto = pr;
                        return prod[0] + "|" + prod[1] + "|" + prod[3] + "|" + cant + "|" + cant * Double.parseDouble(prod[3]);
                    }
                }
            }
            return "";
        } catch (IOException e) {
            IO.println(e);
            return "";
        }
    }

    private String buscarProducto(String id) {
        try(FileReader fr = new FileReader(rutaProductos);
             BufferedReader br = new BufferedReader(fr)){

            String pr;

            while ((pr = br.readLine()) != null) {
                String[] prod = pr.split(",");
                if (prod[0].equals(id)) {
                    String fndProducto = pr;
                    return prod[0] + "|" + prod[1] + "|" + prod[3] + "|" + prod[4] + "|";
                }
            }
            return "";
        } catch (IOException e) {
            IO.println(e);
            return "";
        }
    }

    private void mostrarProductos() {
        StringBuilder txt = new StringBuilder();
        if(!productos.isEmpty()){
            for (int i = 0; i < productos.size(); i++) {
                IO.println(i);
                String linea = productos.get(i);
                String[] spltProd = linea.split("\\|");
                linea = "Código: " + spltProd[0] + " Nombre: " + spltProd[1] + " Precio: $" + spltProd[2] + " Cantidad: " + spltProd[3] + " Total Unitario: $" + spltProd[4];
                txt.append("---------------------------------\n").append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(null, txt, "PRODUCTOS", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void mostrarHistorialVentas() {

        try {

            BufferedReader lector = new BufferedReader(new FileReader("src/Archivos/ventas.txt"));
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    contenido.append(
                            "══════════════════════\n"
                    );
                    contenido.append("Folio: ").append(datos[0]).append("\n");
                    contenido.append("Fecha: ").append(datos[1]).append("\n");
                    contenido.append("Vendedor: ").append(datos[2]).append("\n");
                    contenido.append("Productos: ").append(datos[3]).append("\n");
                    contenido.append("Total: $").append(datos[4]).append("\n\n");

                }

            }

            lector.close();

            if (contenido.isEmpty()) {

                contenido.append(
                        "No hay ventas registradas."
                );

            }

            JOptionPane.showMessageDialog(
                    null,
                    contenido.toString(),
                    "HISTORIAL DE VENTAS",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al leer historial: "
                            + e.getMessage()
            );

        }

    }

    private void mostrarVenta(){
        String txt = String.format("""
                Folio: %d
                N° Productos: %d
                Total: %f
                """, this.folio, productos.size(), this.total);
        JOptionPane.showMessageDialog(null,txt);
    }

    public static void reporteFinanciero() {

        double totalVendido = 0;
        int cantidadVentas = 0;
        double ventaMayor = 0;
        double ventaMenor = Double.MAX_VALUE;

        try {

            BufferedReader lector = new BufferedReader(
                    new FileReader("src/Archivos/ventas.txt")
            );

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 5) {
                    double totalVenta = Double.parseDouble(datos[4]);
                    totalVendido += totalVenta;
                    cantidadVentas++;
                    if (totalVenta > ventaMayor) {
                        ventaMayor = totalVenta;
                    }

                    if (totalVenta < ventaMenor) {
                        ventaMenor = totalVenta;

                    }

                }

            }

            lector.close();
            double promedio = 0;
            if (cantidadVentas > 0) {
                promedio = totalVendido / cantidadVentas;
            } else {
                ventaMenor = 0;
            }

            JOptionPane.showMessageDialog(
                    null,
                    """
                    ╔══════ REPORTE FINANCIERO ══════╗
                    
                    Ventas realizadas: """ + cantidadVentas +
                            "\nTotal vendido: $" + totalVendido +
                            "\nPromedio por venta: $" + promedio +
                            "\nVenta mayor: $" + ventaMayor +
                            "\nVenta menor: $" + ventaMenor,
                    "FINANZAS",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al generar reporte: "
                            + e.getMessage()
            );

        }

    }
}