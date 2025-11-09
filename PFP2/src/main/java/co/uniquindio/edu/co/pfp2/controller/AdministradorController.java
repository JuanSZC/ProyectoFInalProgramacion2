package co.uniquindio.edu.co.pfp2.controller;

import co.uniquindio.edu.co.pfp2.model.Administrador;
import co.uniquindio.edu.co.pfp2.model.Envio;
import co.uniquindio.edu.co.pfp2.model.Repartidor;
import co.uniquindio.edu.co.pfp2.model.Usuario;

public class AdministradorController {

    private Administrador administrador;

    public AdministradorController(Administrador administrador) {
        this.administrador = administrador;
    }


    // =================== CONTROLLER DE USUARIOS =================== //
    /*
    Todo este bloque es la comunicación del modelo con el controlador de vista
    para gestionar los usuarios
     */

    public boolean agregarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        return administrador.agregarUsuario(usuario);
    }

    public boolean verificarUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return administrador.verificarUsuario(idUsuario);
    }

    public boolean actualizarUsuario(int idUsuario, Usuario actualizado) {
        if (idUsuario <= 0 || actualizado == null) {
            return false;
        }
        return administrador.actualizarUsuario(idUsuario, actualizado);
    }

    public boolean eliminarUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return administrador.eliminarRepartidor(idUsuario);
    }

    // =================== CONTROLLER DE REPARTIDORES =================== //
    /*
    Todo este bloque es la comunicación del modelo con el controlador de vista
    para gestionar los repartidores
     */

    public boolean agregarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            return false;
        }
        return administrador.agregarRepartidor(repartidor);
    }

    public boolean verificarRepartidor(int idRepartidor) {
        if (idRepartidor <= 0) {
            return false;
        }
        return administrador.verificarRepartidor(idRepartidor);
    }

    public boolean actualizarRepartidor(int idRepartidor, Repartidor actualizado) {
        if (idRepartidor <= 0 || actualizado == null) {
            return false;
        }
        return administrador.actualizarRepartidor(idRepartidor, actualizado);
    }

    public boolean eliminarRepartidor(int idRepartidor) {
        if (idRepartidor <= 0) {
            return false;
        }
        return administrador.eliminarRepartidor(idRepartidor);
    }

    public Repartidor buscarRepartidorPorID(int idRepartidor) {
        if (idRepartidor <= 0) {
            return null;
        }
        return administrador.buscarRepartidorPorID(idRepartidor);
    }

    // =================== CONTROLLER DE ASIGNACIÓN =================== //
        /*
    Todo este bloque es la comunicación del modelo con el controlador de vista
    para gestionar las asignación de pedidos a un repartidor
     */

    public boolean asignarRepartidor(Envio envio, Repartidor repartidor){
        if (envio == null ||  repartidor == null) {
            return false;
        }
        return administrador.asignarRepartidor(envio, repartidor);
    }

}
