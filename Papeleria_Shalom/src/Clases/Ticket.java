package Clases;

import java.time.LocalDate;
public class Ticket {
   private String folio;
   private LocalDate fecha;
   private double monto;
   private String tipo;
public Ticket(String folio, LocalDate fecha, double monto, String tipo) {
    this.folio = folio;
    this.fecha = LocalDate.now();
    this.monto = monto;
    this.tipo = tipo;
}
    public String getFolio()
    {
        return this.folio;
    }
public void setFolio(String folio)
    {
        this.folio = folio;
    }
public LocalDate getFecha()
    {
        return this.fecha;
    }
public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }
public double getMonto()
    {
        return this.monto;
    }
public void setMonto(double monto)
    {
        this.monto = monto;
    }
public String getTipo()
    {
        return this.tipo;
    }
public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
  /**ublic String toString()
    {String cad= folio+ " "+fecha+" "+monto+" "+tipo;
        return cad;
    ***/
}