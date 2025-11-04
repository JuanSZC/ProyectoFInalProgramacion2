package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private int idUsuario;
    private List<Direccion> listDireccionesUsuario;
    private List<Producto> listProductosUsuario;
    private List<Envio> listEnviosUsuario;
    private List<Producto> listCarritosUsuario;

    public List<Producto> getListCarritosUsuario() {
        return listCarritosUsuario;
    }

    public void setListCarritosUsuario(List<Producto> listCarritosUsuario) {
        this.listCarritosUsuario = listCarritosUsuario;
    }

    public Usuario(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono, int idUsuario) {
        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idUsuario = idUsuario;
        this.listDireccionesUsuario = new ArrayList<>();
        this.listProductosUsuario = new ArrayList<>();
        this.listEnviosUsuario = new ArrayList<>();
        this.listCarritosUsuario = new ArrayList<>();
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

    public List<Producto> getListProductosUsuario() {
        return listProductosUsuario;
    }

    public void setListProductosUsuario(List<Producto> listProductosUsuario) {
        this.listProductosUsuario = listProductosUsuario;
    }

    public List<Envio> getListEnviosUsuario() {
        return listEnviosUsuario;
    }

    public void setListEnviosUsuario(List<Envio> listEnviosUsuario) {
        this.listEnviosUsuario = listEnviosUsuario;
    }
}
