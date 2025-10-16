package co.uniquindio.edu.co.proyectofinalprogramacion2.model;

import java.time.LocalDate;
import java.util.List;

public class Envio {
    private int idEnvio;
    private String origen;
    private String destino;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private Repartidor repartidor;
    private Usuario usuarioEnvia;
    private Usuario usuarioReceptor ;

    private Envio(Builder builder){
        this.idEnvio = builder.idEnvio;
        this.origen = builder.origen;
        this.destino = builder.destino;
        this.fechaCreacion = builder.fechaCreacion;
        this.fechaEntrega = builder.fechaEntrega;
        this.repartidor = builder.repartidor;
        this.usuarioEnvia = builder.usuarioEnvia;
        this.usuarioReceptor = builder.usuarioReceptor;

    }


    public static class Builder{

        private int idEnvio;
        private String origen;
        private String destino;
        private LocalDate fechaCreacion;
        private LocalDate fechaEntrega;
        private Repartidor repartidor;
        private Usuario usuarioEnvia;
        private Usuario usuarioReceptor ;

       public Builder idEnvio(int idEnvio){
            this.idEnvio = idEnvio;
            return this;
        }
       public Builder origen(String origen){
           this.origen = origen;
            return this;
        }
        public Builder destino(String destino){
           this.destino = destino;
           return this;
        }
        public Builder fechaCreacion(LocalDate fechaCreacion){
           this.fechaCreacion = fechaCreacion;
           return this;
        }
        public Builder fechaEntrega(LocalDate fechaEntrega){
           this.fechaEntrega = fechaEntrega;
           return this;
        }
        public Builder repartidor(Repartidor repartidor){
           this.repartidor = repartidor;
           return this;
        }
        public Builder usuarioEnvia(Usuario usuarioEnvia){
           this.usuarioEnvia = usuarioEnvia;
           return this;
        }
        public Builder usuarioReceptor(Usuario usuarioReceptor){
           this.usuarioReceptor = usuarioReceptor;
           return this;
        }
        public Envio build(){
           return new Envio(this);
        }
    }
}
