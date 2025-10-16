package co.uniquindio.edu.co.proyectofinalprogramacion2.model;

public final class EmpresaEnvios {
    private static EmpresaEnvios instance;

    private EmpresaEnvios(){
    }

    public static EmpresaEnvios getInstance() {
        if (instance == null) {
            instance = new EmpresaEnvios();
        }
        return instance;
    }

}
