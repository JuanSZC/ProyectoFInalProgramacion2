package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase singleton que representa al administrador general del sistema.
 * El administrador tiene la responsabilidad de gestionar usuarios, repartidores,
   productos y pedidos (envíos) dentro de la plataforma.
 * Se aplica el patrón de diseño {@code Singleton} para garantizar
   que solo exista una única instancia de esta clase durante la ejecución del programa.
 */
public final class Administrador {

    private static Administrador instance;

    private String correo;
    private int clave;

    private List<Envio> listPedidos = new ArrayList<>();
    private List<Producto> listProductos = new ArrayList<>();
    private List<Usuario> listUsuarios = new ArrayList<>();
    private List<Repartidor> listRepartidors = new ArrayList<>();


    private Administrador() {}

    public static Administrador getInstance() {
        if (instance == null) {
            instance = new Administrador();
        }
        return instance;
    }
    // =================== GETTERS Y SETTERS =================== //

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

    // =================== GESTIÓN DE USUARIOS =================== //
    /**
     * Busca un usuario por su correo electrónico.
     * @param correo del usuario a buscar
     * @return el usuario encontrado o {@code null} si no existe
     */
    private Usuario buscarUsuario(String correo) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst()
                .orElse(null);
    }
    /**
     * Devuelve una lista con todos los usuarios registrados.
     * @return lista de usuarios
     */
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(listUsuarios);
    }
    /**
     * Agrega un nuevo usuario al sistema si no existe uno con el mismo ID.
     * @param usuario a registrar
     * @return {@code true} si se agregó correctamente, {@code false} si ya existía
     */
    public boolean agregarUsuario(Usuario usuario) {
        boolean centinela = false;
        if (!verificarUsuario(usuario.getIdUsuario())) {
            listUsuarios.add(usuario);
            centinela = true;
        }
        return centinela;
    }
    /**
     * Verifica si un usuario ya existe en el sistema.
     * @param idUsuario identificador del usuario
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    public boolean verificarUsuario(int idUsuario) {
        boolean centinela = false;
        for (Usuario usuario : listUsuarios) {
            if (usuario.getIdUsuario() == (idUsuario)) {
                centinela = true;
            }
        }
        return centinela;
    }
    /**
     * Actualiza los datos de un usuario existente.
     * @param idUsuario  ID del usuario a modificar
     * @param actualizado Usuario con los nuevos datos
     * @return {@code true} si la actualización fue exitosa
     */
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
    /**
     * Elimina un usuario del sistema según su ID.
     * @param idUsuario ID del usuario a eliminar
     * @return {@code true} si se eliminó correctamente
     */
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

    // =================== GESTIÓN DE REPARTIDORES =================== //
    /**
     * Registra un nuevo repartidor si no existe otro con el mismo ID.
     * @param repartidor repartidor a registrar
     * @return {@code true} si se agregó correctamente, {@code false} si ya existía
     */
    public boolean agregarRepartidor(Repartidor repartidor) {
        boolean centinela = false;
        if (!verificarRepartidor(repartidor.getIdRepartidor())) {
            listRepartidors.add(repartidor);
            centinela = true;
        }
        return centinela;
    }
    /**
     * Verifica si un repartidor ya existe.
     * @param idRepartidor identificador del repartidor
     * @return {@code true} si existe, {@code false} si no
     */
    public boolean verificarRepartidor(int idRepartidor) {
        boolean centinela = false;
        for (Repartidor repartidor : listRepartidors) {
            if (repartidor.getIdRepartidor() == (idRepartidor)) {
                centinela = true;
            }
        }
        return centinela;
    }
    /**
     * Lista todos los repartidores registrados.
     * @return lista de repartidores
     */
    public List<Repartidor> listarRepartidores() {
        return new ArrayList<>(listRepartidors);
    }
    /**
     * Actualiza los datos de un repartidor existente.
     * @param idRepartidor ID del repartidor a modificar
     * @param actualizado  repartidor con la nueva información
     * @return {@code true} si la actualización fue exitosa
     */
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
    /**
     * Elimina un repartidor del sistema por su ID.
     * @param idRepartidor ID del repartidor a eliminar
     * @return {@code true} si se eliminó correctamente
     */
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
    /**
     * Busca un repartidor según su identificador.
     * @param idRepartidor ID del repartidor a buscar
     * @return repartidor encontrado o {@code null} si no existe
     */
    public Repartidor buscarRepartidorPorID(int idRepartidor) {
        for (Repartidor repartidor : listRepartidors) {
            if (repartidor.getIdRepartidor() == (idRepartidor)) {
                return repartidor;
            }
        }
        return null;
    }
    // =================== GESTIÓN DE ASIGNACIÓN =================== //
    /**
     * Asigna un repartidor disponible a un envío.
     * Si el repartidor está disponible, el estado del envío cambia a {@code ASIGNADO}
      y la disponibilidad del repartidor pasa a {@code EN_RUTA}.
     * @param envio envío a asignar
     * @param repartidor repartidor que realizará el envío
     * @return {@code true} si se asignó correctamente, {@code false} si el repartidor no estaba disponible
     */
    public boolean asignarRepartidor(Envio envio, Repartidor repartidor) {

        if (repartidor.getDisponibilidadRepartidor() == DisponibilidadRepartidor.DISPONIBLE) {
            envio.cambiarEstado(EstadoEnvio.ASIGNADO);
            envio.setRepartidor(repartidor);
            repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);
            return true;
        }
        return false;
    }
}
