package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    int folio;
    int idVendedor;
    LocalDate fecha;
    ArrayList<Producto> productos;
    double total;
    Pago pago;

    public Venta(int f){
        this.folio = f;
    }
}