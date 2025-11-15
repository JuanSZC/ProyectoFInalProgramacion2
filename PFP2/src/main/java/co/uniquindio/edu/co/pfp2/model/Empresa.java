package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase final que representa la entidad principal del sistema: la Empresa.
 *
 * Implementa el patrón de diseño <strong>Singleton</strong>, garantizando que
 * solo exista una instancia de {@code Empresa} en toda la aplicación. Esto permite
 * centralizar la información de usuarios, repartidores, productos y pedidos,
 * simulando un entorno empresarial real para la gestión de envíos.
 * La clase almacena:
 * Datos generales de la empresa (nombre, dirección, NIT).
 * Listas globales de envíos, productos, usuarios y repartidores.
 * Acceso centralizado a la información mediante la instancia única.
 * Es utilizada como repositorio general de datos durante la ejecución
 * del programa.
 */
public final class Empresa {

    /** Nombre de la empresa. */
    private String nombre;

    /** Dirección física o sede principal. */
    private String direccion;

    /** Número de identificación tributaria. */
    private int NIT;

    /** Lista de pedidos (envíos) registrados en la empresa. */
    private List<Envio> listPedidos = new ArrayList<>();

    /** Catálogo de productos registrados. */
    private List<Producto> listProductos = new ArrayList<>();

    /** Lista de usuarios del sistema. */
    private List<Usuario> listUsuarios = new ArrayList<>();

    /** Lista de repartidores asociados a la empresa. */
    private List<Repartidor> listRepartidors = new ArrayList<>();

    /** Instancia única de la empresa (patrón Singleton). */
    private static Empresa instance;

    /**
     * Constructor privado para evitar la creación de instancias externas.
     * Garantiza que la única forma de obtener un objeto Empresa sea mediante {@link #getInstance()}.
     */
    private Empresa(){}

    /**
     * Obtiene la instancia única de la empresa.
     * Si no existe, se crea una nueva instancia.
     *
     * @return instancia única de {@code Empresa}
     */
    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el nombre de la empresa.
     * @return nombre de la empresa
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la empresa.
     * @param nombre nombre de la empresa
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección de la empresa.
     * @return dirección registrada
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de la empresa.
     * @param direccion dirección física de la empresa
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el NIT de la empresa.
     * @return NIT
     */
    public int getNIT() {
        return NIT;
    }

    /**
     * Establece el NIT de la empresa.
     * @param NIT número de identificación tributaria
     */
    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    /**
     * Obtiene la lista de pedidos (envíos) registrados.
     * @return lista de envíos
     */
    public List<Envio> getListPedidos() {
        return listPedidos;
    }

    /**
     * Reemplaza la lista de envíos.
     * @param listPedidos nueva lista de pedidos
     */
    public void setListPedidos(List<Envio> listPedidos) {
        this.listPedidos = listPedidos;
    }

    /**
     * Obtiene la lista de productos registrados.
     * @return lista de productos
     */
    public List<Producto> getListProductos() {
        return listProductos;
    }

    /**
     * Establece la lista global de productos.
     * @param listProductos nueva lista de productos
     */
    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
    }

    /**
     * Obtiene la lista de usuarios registrados.
     * @return lista de usuarios
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * Reemplaza la lista actual de usuarios.
     * @param listUsuarios nueva lista de usuarios
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Obtiene la lista de repartidores registrados.
     * @return lista de repartidores
     */
    public List<Repartidor> getListRepartidors() {
        return listRepartidors;
    }

    /**
     * Establece la lista de repartidores.
     * @param listRepartidors nueva lista de repartidores
     */
    public void setListRepartidors(List<Repartidor> listRepartidors) {
        this.listRepartidors = listRepartidors;
    }
}

