package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario del sistema. Extiende de {@link Persona}
 * e incluye la gestión de direcciones, productos, carritos y solicitudes de envío.
 *
 * También proporciona funcionalidades como iniciar sesión, modificar direcciones,
 * crear y gestionar solicitudes de envío, y consultar historial.
 */
public class Usuario extends Persona {

    /** Identificador único del usuario dentro del sistema */
    private int idUsuario;

    /** Lista de direcciones asociadas al usuario */
    private List<Direccion> listDireccionesUsuario;

    /** Lista global de usuarios (utilizada para validar inicios de sesión) */
    private List<Usuario> listUsuarios;

    /** Lista de productos pertenecientes al usuario */
    private List<Producto> listProductosUsuario;

    /** Lista de envíos creados por el usuario */
    private List<Envio> listEnviosUsuario;

    /** Lista de productos añadidos al carrito del usuario */
    private List<Producto> listCarritosUsuario;

    /**
     * Constructor principal que inicializa los datos básicos del usuario
     * y las listas necesarias para su funcionamiento.
     *
     * @param nombreCompleto nombre completo del usuario
     * @param cedula número de identificación
     * @param correo correo electrónico
     * @param contrasenia contraseña del usuario
     * @param telefono teléfono de contacto
     * @param idUsuario identificador único del usuario
     */
    public Usuario(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono, int idUsuario) {
        super(nombreCompleto, cedula, correo, contrasenia, telefono);
        this.idUsuario = idUsuario;
        this.listDireccionesUsuario = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listProductosUsuario = new ArrayList<>();
        this.listEnviosUsuario = new ArrayList<>();
        this.listCarritosUsuario = new ArrayList<>();
    }

    /** @return lista de usuarios registrados */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /** @param listUsuarios asigna la lista de usuarios */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /** @return lista de productos en el carrito del usuario */
    public List<Producto> getListCarritosUsuario() {
        return listCarritosUsuario;
    }

    /** @param listCarritosUsuario asigna la lista del carrito del usuario */
    public void setListCarritosUsuario(List<Producto> listCarritosUsuario) {
        this.listCarritosUsuario = listCarritosUsuario;
    }

    /** @return lista de envíos del usuario */
    public List<Envio> getListEnviosUsuario() {
        return listEnviosUsuario;
    }

    /** @param listEnviosUsuario asigna la lista de envíos */
    public void setListEnviosUsuario(List<Envio> listEnviosUsuario) {
        this.listEnviosUsuario = listEnviosUsuario;
    }

    /** @return lista de productos del usuario */
    public List<Producto> getListProductosUsuario() {
        return listProductosUsuario;
    }

    /** @param listProductosUsuario asigna la lista de productos del usuario */
    public void setListProductosUsuario(List<Producto> listProductosUsuario) {
        this.listProductosUsuario = listProductosUsuario;
    }

    /** @return ID del usuario */
    public int getIdUsuario() {
        return idUsuario;
    }

    /** @param idUsuario establece el ID del usuario */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** @return lista de direcciones del usuario */
    public List<Direccion> getListDireccionesUsuario() {
        return listDireccionesUsuario;
    }

    /** @param listDireccionesUsuario asigna la lista de direcciones */
    public void setListDireccionesUsuario(List<Direccion> listDireccionesUsuario) {
        this.listDireccionesUsuario = listDireccionesUsuario;
    }

    /**
     * Permite iniciar sesión validando correo y contraseña dentro de la lista de usuarios.
     *
     * @param correo correo ingresado
     * @param contrasenia contraseña ingresada
     * @return usuario correspondiente o null si no coincide
     */
    public Usuario iniciarSesion(String correo, String contrasenia) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreo().equals(correo) && u.getContrasenia().equals(contrasenia))
                .findFirst()
                .orElse(null);
    }

    /**
     * Agrega una nueva dirección al usuario si no existe otra con el mismo ID.
     *
     * @param direccion dirección a agregar
     * @return true si se agrega correctamente, false si ya existe
     */
    public boolean agregarDireccion(Direccion direccion) {
        boolean centinela = false;
        if (!verificarDireccion(direccion.getIdDireccion())) {
            listDireccionesUsuario.add(direccion);
            centinela = true;
        }
        return centinela;
    }

    /**
     * Verifica si existe una dirección con el ID dado.
     *
     * @param idDireccion ID a verificar
     * @return true si existe, false si no
     */
    public boolean verificarDireccion(int idDireccion) {
        boolean centinela = false;
        for (Direccion direccion : listDireccionesUsuario) {
            if (direccion.getIdDireccion() == (idDireccion)) {
                centinela = true;
            }
        }
        return centinela;
    }

    /**
     * Retorna una copia de la lista de direcciones.
     *
     * @return lista de direcciones del usuario
     */
    public List<Direccion> listarDirecciones() {
        return new ArrayList<>(listDireccionesUsuario);
    }

    /**
     * Actualiza los datos de una dirección existente.
     *
     * @param idDireccion ID de la dirección a modificar
     * @param actualizado nuevo objeto dirección con datos actualizados
     * @return true si se actualiza correctamente
     */
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

    /**
     * Elimina una dirección por su ID.
     *
     * @param idDireccion ID de la dirección a eliminar
     * @return true si se elimina correctamente
     */
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

    /**
     * Busca y retorna una dirección por su ID.
     *
     * @param idDireccion ID de la dirección buscada
     * @return dirección encontrada o null si no existe
     */
    public Direccion buscarDireccionPorID(int idDireccion) {
        for (Direccion direccion : listDireccionesUsuario) {
            if (direccion.getIdDireccion() == (idDireccion)) {
                return direccion;
            }
        }
        return null;
    }

    /**
     * Genera un reporte del envío según el tipo solicitado.
     *
     * @param tipo formato del reporte (pdf o csv)
     */
    public void generarReporte(String tipo) {
        if (tipo.equalsIgnoreCase("pdf"))
            System.out.println("Generando reporte PDF...");
        else if (tipo.equalsIgnoreCase("csv"))
            System.out.println("Generando reporte CSV...");
        else
            System.out.println("Tipo no soportado");
    }

    /**
     * Permite al usuario crear una nueva solicitud de envío antes de ser asignada.
     *
     * @param envio Envío que se desea registrar
     * @return true si se crea exitosamente, false si ya existe un ID duplicado
     */
    public boolean crearSolicitudEnvio(Envio envio) {
        boolean existe = listEnviosUsuario.stream()
                .anyMatch(e -> e.getIdEnvio() == envio.getIdEnvio());
        if (!existe) {
            listEnviosUsuario.add(envio);
            System.out.println("Solicitud de envío creada correctamente: " + envio.getIdEnvio());
            return true;
        }
        System.out.println("Ya existe una solicitud con ese ID.");
        return false;
    }

    /**
     * Modifica una solicitud de envío si aún está en estado SOLICITADO.
     *
     * @param idEnvio ID del envío a modificar
     * @param nuevoEnvio objeto con datos actualizados
     * @return true si la modificación se realiza correctamente
     */
    public boolean modificarSolicitudEnvio(int idEnvio, Envio nuevoEnvio) {
        for (Envio envio : listEnviosUsuario) {
            if (envio.getIdEnvio() == idEnvio && envio.getEstadoEnvio() == EstadoEnvio.SOLICITADO) {
                envio.setOrigen(nuevoEnvio.getOrigen());
                envio.setDestino(nuevoEnvio.getDestino());
                envio.setPaquete(nuevoEnvio.getPaquete());
                envio.setFechaCreacion(nuevoEnvio.getFechaCreacion());
                System.out.println("Solicitud de envío modificada correctamente.");
                return true;
            }
        }
        System.out.println("No se encontró el envío o ya fue asignado.");
        return false;
    }

    /**
     * Cancela una solicitud de envío solo si está en estado SOLICITADO.
     *
     * @param idEnvio ID del envío a cancelar
     * @return true si se cancela correctamente
     */
    public boolean cancelarSolicitudEnvio(int idEnvio) {
        for (Envio envio : listEnviosUsuario) {
            if (envio.getIdEnvio() == idEnvio && envio.getEstadoEnvio() == EstadoEnvio.SOLICITADO) {
                envio.cambiarEstado(EstadoEnvio.CANCELADO);
                System.out.println("Solicitud cancelada correctamente.");
                return true;
            }
        }
        System.out.println("No se pudo cancelar el envío (ya está en proceso o no existe).");
        return false;
    }

    /**
     * Retorna el historial completo de envíos del usuario.
     *
     * @return lista de objetos Envío registrados
     */
    public List<Envio> consultarHistorialEnvio() {
        if (listEnviosUsuario.isEmpty()) {
            System.out.println("No hay envíos registrados en el historial.");
        }
        return new ArrayList<>(listEnviosUsuario);
    }

    /**
     * Permite rastrear el estado actual de un envío según su ID.
     *
     * @param idEnvio ID del envío a consultar
     * @return estado actual del envío o null si no existe
     */
    public EstadoEnvio rastrearEstadoEnvio(int idEnvio) {
        for (Envio envio : listEnviosUsuario) {
            if (envio.getIdEnvio() == idEnvio) {
                System.out.println("Estado actual del envío " + idEnvio + ": " + envio.getEstadoEnvio());
                return envio.getEstadoEnvio();
            }
        }
        System.out.println("No se encontró el envío con ID " + idEnvio);
        return null;
    }
}
