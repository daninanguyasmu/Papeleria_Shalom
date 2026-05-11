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


    public Venta(String nv){
            this.folio = this.getContadorVentas() + 1;
            this.nombreVendedor = nv;
            this.fecha = LocalDateTime.now();
            this.total = 500;
    }

    public void menuVenta (){
        String menu = """
                1. Agregar Producto
                2. Eliminar Producto
                3. Buscar Producto
                4. Finalizar Venta
                5. Salir""";
        int opcion;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
            switch (opcion){
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

            }
        }while(opcion != 5);

    }

    public void agregarProducto(){
        String codigo = JOptionPane.showInputDialog(null, "Ingrese el código del producto que desea agregar");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto"));
        String fndProd = buscarProducto(codigo, cantidad);

        if (fndProd.isEmpty()){
            JOptionPane.showMessageDialog(null, "Producto no encontrado o cantidad insuficiente", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
            productos.add(fndProd);
        }
        mostrarVenta();
    }

    public void eliminarProducto() {
        if (productos.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay productos para eliminar.","Eliminar producto", JOptionPane.WARNING_MESSAGE);
        }else{
            mostrarVenta();
            boolean fndProd = false;
            int index = 0;
            String codigoBuscar = JOptionPane.showInputDialog(null, "Ingrese el código del producto que desee eliminar");
            for (int i = 0; i < productos.size(); i++) {
                String prod = productos.get(i);
                IO.println(prod);
                String[] codigoProd = prod.split("\\|");
                IO.println(Arrays.toString(codigoProd));
                if (codigoBuscar.equals(codigoProd[0])) {
                    fndProd = true;
                    index = i;
                } else {
                    fndProd = false;
                }
            }
            if (fndProd){
                productos.remove(index);
            }else{
                JOptionPane.showMessageDialog(null, "Producto con el código: " + codigoBuscar + " no encontrado");
            }
        }
    }



    public void guardarVenta(){
        try(FileWriter fw = new FileWriter("src/Archivos/ventas.txt", true);
        PrintWriter pw = new PrintWriter(fw)){
            pw.println(this.folio + "," + this.fecha + "," + this.nombreVendedor + "," + this.productos + "," + this.total);
            JOptionPane.showMessageDialog(null, "Venta '" + this.folio + "' registrada correctamente.");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error en guardar la venta'" + e);
        }
    }

    private int getContadorVentas(){
        try {

            FileReader fr = new FileReader(rutaContandorVentas);
            BufferedReader br = new BufferedReader(fr);

            String cont = br.readLine();
            return Integer.parseInt(cont);

        }catch(Exception e){
            IO.print(e);
        }
        return 0;
    }

    private void setContadorVentas(){
        try(PrintWriter pw = new PrintWriter(rutaContandorVentas)) {
            pw.print(folio);
        }catch (IOException e){
            IO.println("Error:" + e);
        }
    }

    private String buscarProducto(String id, int cant){
        try{
            FileReader fr = new FileReader(rutaProductos);
            BufferedReader br = new BufferedReader(fr);

            String pr;

            while((pr = br.readLine()) != null){
                String[] prod = pr.split(",");
                if (prod[0].equals(id)){
                    if (Integer.parseInt(prod[4]) > 0 && Integer.parseInt(prod[4]) >= cant){
                        String fndProducto = pr;
                        return prod[0] + "|" + prod[1] + "|" + prod[3] + "|" + cant + "|" + cant*Double.parseDouble(prod[3]);
                    }
                }
            }
            return "";
        }catch(IOException e){
            IO.println(e);
            return "";
        }
    }

    private String buscarProducto(String id){
        try{
            FileReader fr = new FileReader(rutaProductos);
            BufferedReader br = new BufferedReader(fr);

            String pr;

            while((pr = br.readLine()) != null){
                String[] prod = pr.split(",");
                if (prod[0].equals(id)){
                        String fndProducto = pr;
                        return prod[0] + "|" + prod[1] + "|" + prod[3] + "|" + prod[4] + "|";
                }
            }
            return "";
        }catch(IOException e){
            IO.println(e);
            return "";
        }
    }

    private void mostrarVenta(){
        StringBuilder txt = new StringBuilder();
        for(int i = 0; i < productos.size();i++){
            IO.println(i);
            String linea = productos.get(i);
            String[] spltProd = linea.split("\\|");
            linea = "Código: " + spltProd[0] + " Nombre: " + spltProd[1] + " Precio: $" + spltProd[2] + " Cantidad: " + spltProd[3] + " Total Unitario: $" + spltProd[4];
            txt.append("---------------------------------\n").append(linea).append("\n");
        }
        JOptionPane.showMessageDialog(null, txt, "PRODUCTOS", JOptionPane.INFORMATION_MESSAGE);
    }
}