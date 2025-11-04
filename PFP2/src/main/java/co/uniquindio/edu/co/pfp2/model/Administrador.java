package co.uniquindio.edu.co.pfp2.model;

public final  class Administrador {

    private static Administrador instance;

    private String correo;
    private int clave;

    private Administrador() {}

    public static Administrador getInstance() {
        if (instance == null) {
            instance = new Administrador();
        }
        return instance;
    }



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
}
