package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private int idUsuario;
    private List<Direccion> listDireccionesUsuario;
    private List<Usuario> listUsuarios;


    public Usuario(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono, int idUsuario) {
        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idUsuario = idUsuario;
        this.listDireccionesUsuario = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Direccion> getListDireccionesUsuario() {
        return listDireccionesUsuario;
    }

    public void setListDireccionesUsuario(List<Direccion> listDireccionesUsuario) {
        this.listDireccionesUsuario = listDireccionesUsuario;
    }
    //Clase que registra un usuario
    public boolean registrarUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getCorreo()) == null) {
            listUsuarios.add(usuario);
            return true;
        }
        return false;
    }
    //Clase que se utiliza para leer los usuarios agregados
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(listUsuarios);
    }
    //Clase para iniciar sesion
    public Usuario iniciarSesion(String correo, String contrasenia) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo) && u.getContrasenia().equals(contrasenia))
                .findFirst()
                .orElse(null);
    }

    //Clase para buscar al usuario por el correo
    private Usuario buscarUsuario(String correo) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
    }


    //Clase que genera el reporte del envio
    public void generarReporte(String tipo) {
        if (tipo.equalsIgnoreCase("pdf"))
            System.out.println("Generando reporte PDF...");
        else if (tipo.equalsIgnoreCase("csv"))
            System.out.println("Generando reporte CSV...");
        else
            System.out.println("Tipo no soportado");
    }

}
