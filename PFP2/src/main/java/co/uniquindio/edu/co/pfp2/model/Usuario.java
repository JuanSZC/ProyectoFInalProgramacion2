package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private int idUsuario;
    private List<Direccion> listDireccionesUsuario;


    public Usuario(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono, int idUsuario) {
        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idUsuario = idUsuario;
        this.listDireccionesUsuario = new ArrayList<>();
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
}
