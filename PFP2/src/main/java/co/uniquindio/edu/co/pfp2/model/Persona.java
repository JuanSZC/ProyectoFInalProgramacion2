package co.uniquindio.edu.co.pfp2.model;

/**
 * Representa una persona dentro del sistema.
 *
 * <p>Esta clase funciona como una clase base (superclase) para otros tipos de usuarios
 * como {@code Usuario} y {@code Repartidor}, proporcionando atributos comunes como
 * nombre, cédula, correo, contraseña y teléfono.</p>
 *
 * <p>Su propósito es evitar duplicación de código y promover la reutilización mediante
 * herencia.</p>
 */
public class Persona {

    /** Nombre completo de la persona. */
    private String nombreCompleto;

    /** Número de identificación (cédula) de la persona. */
    private String cedula;

    /** Correo electrónico asociado a la persona. */
    private String correo;

    /** Contraseña utilizada para autenticación. */
    private String contrasenia;

    /** Número de teléfono de contacto. */
    private int telefono;

    /**
     * Constructor principal que inicializa todos los atributos de la persona.
     *
     * @param nombreCompleto nombre completo de la persona
     * @param cedula número de identificación
     * @param correo correo electrónico
     * @param contrasenia contraseña para el inicio de sesión
     * @param telefono número telefónico
     */
    public Persona(String nombreCompleto, String cedula, String correo, String contrasenia, int telefono) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
    }

    /**
     * Obtiene el nombre completo de la persona.
     *
     * @return nombre completo
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Modifica el nombre completo de la persona.
     *
     * @param nombreCompleto nuevo nombre completo
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene la cédula de la persona.
     *
     * @return cédula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica la cédula de la persona.
     *
     * @param cedula nueva cédula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo electrónico de la persona.
     *
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña registrada para la persona.
     *
     * @return contraseña
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Modifica la contraseña de la persona.
     *
     * @param contrasenia nueva contraseña
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el número telefónico de la persona.
     *
     * @return teléfono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Modifica el número de teléfono.
     *
     * @param telefono nuevo número telefónico
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
