package co.uniquindio.edu.co.proyectofinalprogramacion2.model;

public class Persona {
    private String nombre;
    private String correo;
    private int telefono;
    private String cedula;

    public Persona(String nombre, String correo, int telefono,String cedula) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getCedula(){
        return cedula;
    }
    public void setCedula(String cedula){
        this.cedula = cedula;
    }
}
