package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public final class Empresa {

    private String nombre;
    private String direccion;
    private int NIT;


    private List<Envio> listPedidos = new ArrayList<>();
    private List<Producto> listProductos = new ArrayList<>();
    private List<Usuario> listUsuarios = new ArrayList<>();
    private List<Repartidor> listRepartidors = new ArrayList<>();


    private static Empresa instance;

    private Empresa(){}

    /**
     * Obtiene la instancia única de la empresa.
     * Si no existe, se crea una nueva instancia.
     * @return instancia única de {Empresa}
     */
    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }

    // --- Getters y Setters ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }


}

