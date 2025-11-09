package co.uniquindio.edu.co.pfp2.controller;

import co.uniquindio.edu.co.pfp2.model.Direccion;
import co.uniquindio.edu.co.pfp2.model.Usuario;

public class UsuarioController {
    private Usuario usuario;

    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
    }

    // =================== CONTROLLER DE DATA =================== //
    /*
    Todo este bloque es la comunicación del modelo con el controlador de vista
    sobre toda la validación y gestión de información del usuario
     */

    public Usuario iniciarSesion(String correo, String contrasenia) {
        if (correo == null || correo.isBlank() || contrasenia == null || contrasenia.isBlank()) {
            return null;
        }
        return usuario.iniciarSesion(correo, contrasenia);
    }

    public boolean agregarDireccion(Direccion direccion) {
        if (direccion == null) {
            return false;
        }
        return usuario.agregarDireccion(direccion);
    }

    public boolean verificarDireccion(int idDireccion) {
        if (idDireccion <= 0) {
            return false;
        }
        return usuario.verificarDireccion(idDireccion);
    }

    public boolean actualizarDireccion(int idDireccion, Direccion actualizado) {
        if (idDireccion <= 0 || actualizado == null) {
            return false;
        }
        return usuario.actualizarDireccion(idDireccion, actualizado);
    }

    public boolean eliminarDireccion(int idDireccion) {
        if (idDireccion <= 0) {
            return false;
        }
        return usuario.eliminarDireccion(idDireccion);
    }

    public Direccion buscarDireccionPorID(int idDireccion) {
        if (idDireccion <= 0) {
            return null;
        }
        return usuario.buscarDireccionPorID(idDireccion);
    }

}
