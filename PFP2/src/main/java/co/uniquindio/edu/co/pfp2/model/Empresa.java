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


    private Empresa instance;

    private Empresa(){}

    private Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }

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

    public boolean agregarRepartidor(Repartidor r) {
        if (buscarRepartidor(r.getIdRepartidor()) == null) {
            listRepartidors.add(r);
            return true;
        }
        return false;
    }
    public List<Repartidor> listarRepartidores() {
        return new ArrayList<>(listRepartidors);
    }

    public boolean actualizarRepartidor(int idRepartidor, Repartidor nuevo) {
        Repartidor r = buscarRepartidor(idRepartidor);
        if (r != null) {
            r.setDisponibilidadRepartidor(nuevo.isDisponible());
            return true;
        }
        return false;
    }

    public boolean eliminarRepartidor(int idRepartidor) {
        Repartidor r = buscarRepartidor(idRepartidor);
        if (r != null) {
            listRepartidors.remove(r);
            return true;
        }
        return false;
    }

    private Repartidor buscarRepartidor(int idRepartidor) {
        return listRepartidors.stream()
                .filter(rep -> rep.getIdRepartidor() == idRepartidor)
                .findFirst()
                .orElse(null);
    }


}

