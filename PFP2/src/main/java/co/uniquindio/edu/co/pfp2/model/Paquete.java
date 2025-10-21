package co.uniquindio.edu.co.pfp2.model;

import java.util.ArrayList;
import java.util.List;

public class Paquete {

    private List<Producto> productos;
    private double precio;
    private double peso;
    private double dimension;

    public Paquete(double precio, double peso, double dimension) {
        this.productos = new ArrayList<>();
        this.precio = precio;
        this.peso = peso;
        this.dimension = dimension;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }
}
