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

    /**
     * Permite al usuario crear una nueva solicitud de envío antes de ser asignada.
     * @param envio Envío que se desea crear.
     * @return true si se agregó correctamente, false si ya existía un envío con el mismo ID.
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
     * Permite modificar una solicitud de envío antes de que sea asignada a un repartidor.
     * Solo se puede modificar si su estado es SOLICITADO.
     * @param idEnvio ID del envío a modificar.
     * @param nuevoEnvio Datos nuevos del envío.
     * @return true si se modificó correctamente, false si no se pudo.
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
     * Cancela una solicitud de envío si aún no ha sido asignada.
     * @param idEnvio ID del envío a cancelar.
     * @return true si se canceló correctamente, false si no se puede cancelar.
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
     * Consulta el historial de envíos del usuario (todos los estados).
     * @return lista de envíos registrados por el usuario.
     */
    public List<Envio> consultarHistorialEnvio() {
        if (listEnviosUsuario.isEmpty()) {
            System.out.println("No hay envíos registrados en el historial.");
        }
        return new ArrayList<>(listEnviosUsuario);
    }

    /**
     * Permite rastrear el estado actual de un envío específico.
     * @param idEnvio ID del envío a rastrear.
     * @return estado actual del envío o null si no se encuentra.
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
