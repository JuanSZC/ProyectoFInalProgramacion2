package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public final class Administrador {

    private Administrador instance;

    private String correo;
    private int clave;

    private List<Envio> listPedidos = new ArrayList<>();
    private List<Producto> listProductos = new ArrayList<>();
    private List<Usuario> listUsuarios = new ArrayList<>();
    private List<Repartidor> listRepartidors = new ArrayList<>();


    private Administrador() {}

    public Administrador getInstance() {
        if (instance == null) {
            instance = new Administrador();
        }
        return instance;
    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    //Clase para buscar al usuario por el correo
    private Usuario buscarUsuario(String correo) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
    }
    //Clase que se utiliza para leer los usuarios agregados
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(listUsuarios);
    }

    //Clase que registra un usuario
    public boolean agregarUsuario(Usuario usuario) {
        boolean centinela = false;
        if (!verificarUsuario(usuario.getIdUsuario())) {
            listUsuarios.add(usuario);
            centinela = true;
        }
        return centinela;
    }

    public boolean verificarUsuario(int idUsuario) {
        boolean centinela = false;
        for (Usuario usuario : listUsuarios) {
            if (usuario.getIdUsuario() == (idUsuario)) {
                centinela = true;
            }
        }
        return centinela;
    }

    public boolean actualizarUsuario(int idUsuario, Usuario actualizado) {
        boolean centinela = false;
        for (Usuario usuario : listUsuarios) {
            if (usuario.getIdUsuario() == (idUsuario)) {
                usuario.setNombreCompleto(actualizado.getNombreCompleto());
                usuario.setCedula(actualizado.getCedula());
                usuario.setCorreo(actualizado.getCorreo());
                usuario.setContrasenia(actualizado.getContrasenia());
                usuario.setTelefono(actualizado.getTelefono());
                usuario.setIdUsuario(actualizado.getIdUsuario());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean eliminarusuario(int idUsuario) {
        boolean centinela = false;
        for (Usuario usuario : listUsuarios) {
            if (usuario.getIdUsuario() == (idUsuario)) {
                listUsuarios.remove(usuario);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean agregarRepartidor(Repartidor repartidor) {
        boolean centinela = false;
        if (!verificarRepartidor(repartidor.getIdRepartidor())) {
            listRepartidors.add(repartidor);
            centinela = true;
        }
        return centinela;
    }
    public boolean verificarRepartidor(int idRepartidor) {
        boolean centinela = false;
        for (Repartidor repartidor : listRepartidors) {
            if (repartidor.getIdRepartidor() == (idRepartidor)) {
                centinela = true;
            }
        }
        return centinela;
    }
    public List<Repartidor> listarRepartidores() {
        return new ArrayList<>(listRepartidors);
    }


    public boolean actualizarRepartidor(int idRepartidor, Repartidor actualizado) {
        boolean centinela = false;
        for (Repartidor repartidor : listRepartidors) {
            if (repartidor.getIdRepartidor() == (idRepartidor)) {
                repartidor.setNombreCompleto(actualizado.getNombreCompleto());
                repartidor.setCedula(actualizado.getCedula());
                repartidor.setCorreo(actualizado.getCorreo());
                repartidor.setContrasenia(actualizado.getContrasenia());
                repartidor.setTelefono(actualizado.getTelefono());
                repartidor.setZonaCobertura(actualizado.getZonaCobertura());
                repartidor.setDisponibilidadRepartidor(actualizado.getDisponibilidadRepartidor());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean eliminarRepartidor(int idRepartidor) {
        boolean centinela = false;
        for (Repartidor repartidor : listRepartidors) {
            if (repartidor.getIdRepartidor() == (idRepartidor)) {
                listRepartidors.remove(repartidor);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public Repartidor buscarRepartidorPorID(int idRepartidor) {
        for (Repartidor repartidor : listRepartidors) {
            if (repartidor.getIdRepartidor() == (idRepartidor)) {
                return repartidor;
            }
        }
        return null;
    }

}
