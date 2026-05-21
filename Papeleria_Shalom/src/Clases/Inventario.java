package Clases;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class Inventario {
    static String ruta = "src/Archivos/inventario.txt";
    static String temp = "src/Archivos/temp_inventario.txt";

    static File fileRuta = new File(ruta);
    static File fileTemp = new File(temp);

    static String nuevaLineaProd;

    public static void actualizarStock(String cod, int cant, boolean esVenta){
        try(BufferedReader br = new BufferedReader(new FileReader(ruta));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp))){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] prod = linea.split(",");

                if (cod.equals(prod[0])){
                    int stockActual = Integer.parseInt(prod[4]);
                    if (esVenta){
                        prod[4] = String.valueOf(stockActual-cant);
                        IO.println(Arrays.toString(prod));
                    } else{
                        prod[4] = String.valueOf(stockActual+cant);
                        IO.println(Arrays.toString(prod));
                    }
                    linea = String.join(",", prod);
                }
                bw.write(linea);
                bw.newLine();
            }
        }catch(IOException e){
            IO.println(e);
            return;
        }

        try {
            Path archivoOriginal = Paths.get(ruta);
            Path archivoTemporal = Paths.get(temp);

            Files.move(archivoTemporal, archivoOriginal, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            IO.println(e);
        }
    }

}
