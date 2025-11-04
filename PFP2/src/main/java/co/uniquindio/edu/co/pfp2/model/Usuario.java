package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private int idUsuario;
    private List<Direccion> listDireccionesUsuario;
    private List<Usuario> listUsuarios;
    private List<Producto> listProductosUsuario;
    private List<Envio> listEnviosUsuario;
    private List<Producto> listCarritosUsuario;


    public Usuario(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono, int idUsuario) {
        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idUsuario = idUsuario;
        this.listDireccionesUsuario = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listProductosUsuario = new ArrayList<>();
        this.listEnviosUsuario = new ArrayList<>();
        this.listCarritosUsuario = new ArrayList<>();
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Producto> getListCarritosUsuario() {
        return listCarritosUsuario;
    }

    public void setListCarritosUsuario(List<Producto> listCarritosUsuario) {
        this.listCarritosUsuario = listCarritosUsuario;
    }

    public List<Envio> getListEnviosUsuario() {
        return listEnviosUsuario;
    }

    public void setListEnviosUsuario(List<Envio> listEnviosUsuario) {
        this.listEnviosUsuario = listEnviosUsuario;
    }

    public List<Producto> getListProductosUsuario() {
        return listProductosUsuario;
    }

    public void setListProductosUsuario(List<Producto> listProductosUsuario) {
        this.listProductosUsuario = listProductosUsuario;
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

    //Clase para iniciar sesion
    public Usuario iniciarSesion(String correo, String contrasenia) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo) && u.getContrasenia().equals(contrasenia))
                .findFirst()
                .orElse(null);
    }

    public boolean agregarDireccion(Direccion direccion) {
        boolean centinela = false;
        if (!verificarDireccion(direccion.getIdDireccion())) {
            listDireccionesUsuario.add(direccion);
            centinela = true;
        }
        return centinela;
    }
    public boolean verificarDireccion(int idDireccion) {
        boolean centinela = false;
        for (Direccion direccion : listDireccionesUsuario) {
            if (direccion.getIdDireccion() == (idDireccion)) {
                centinela = true;
            }
        }
        return centinela;
    }
    public List<Direccion> listarDirecciones() {
        return new ArrayList<>(listDireccionesUsuario);
    }

    public boolean actualizarDireccion(int idDireccion, Direccion actualizado) {
        boolean centinela = false;
        for (Direccion direccion : listDireccionesUsuario) {
            if (direccion.getIdDireccion() == (idDireccion)) {
                direccion.setIdDireccion(actualizado.getIdDireccion());
                direccion.setDescripcion(actualizado.getDescripcion());
                direccion.setLatitud(actualizado.getLatitud());
                direccion.setLongitud(actualizado.getLongitud());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean eliminarDireccion(int idDireccion) {
        boolean centinela = false;
        for (Direccion direccion : listDireccionesUsuario) {
            if (direccion.getIdDireccion() == (idDireccion)) {
                listDireccionesUsuario.remove(direccion);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public Direccion buscarDireccionPorID(int idDireccion) {
        for (Direccion direccion : listDireccionesUsuario) {
            if (direccion.getIdDireccion() == (idDireccion)) {
                return direccion;
            }
        }
        return null;
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

    // Crear -->ConsultarHistorialEnvio /

    //Crear -->  RastrearEstadoEnvio /

    //Crear, modificar y cancelar solicitudes de envío antes de ser asignadas.

}
