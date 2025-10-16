package co.uniquindio.edu.co.proyectofinalprogramacion2.model;

import java.util.*;

public class Usuario extends  Persona{

    private int idUnico;
    private List<Direccion> listDirecciones;


    public Usuario(String nombre, String correo, int telefono, String cedula,int idUnico) {
        super(nombre, correo, telefono, cedula);
        this.idUnico = idUnico;
        this.listDirecciones = new ArrayList<>();

    }

}
