package Clases;

public class Usuario {

    String contrasena;
    String nombre;
    String usuario;
    String Contrasena;
    int tipo; //aqui es 1 Admin y 2 Cajero


    public Usuario (String nombre, String usuario, String contrasena, int tipo){
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public Usuario() {
    }
}