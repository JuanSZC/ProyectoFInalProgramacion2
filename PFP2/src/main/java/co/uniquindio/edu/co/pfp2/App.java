package co.uniquindio.edu.co.pfp2;

import co.uniquindio.edu.co.pfp2.Extra.*;
import co.uniquindio.edu.co.pfp2.model.*;
import co.uniquindio.edu.co.pfp2.viewController.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.lang.model.element.ModuleElement;
import java.io.IOException;

public class App extends Application {

    private Stage stage;
    public Administrador admin;
    public ObservableList<Usuario> listGlobalUsuarios = FXCollections.observableArrayList();
    public ObservableList<Repartidor> listGlobalRepartidores = FXCollections.observableArrayList();
    public ObservableList<Producto> listGlobalProductos = FXCollections.observableArrayList();
    public int idUsuario = 1111;
    public int idProducto = 1111;
    public Usuario usuarioSesion;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Proyecto Final Programación");
        openPantallaBienvenida();
        inicializarData();
    }


    public void openPantallaBienvenida() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaBienvenida.fxml"));

            AnchorPane rootLayout = loader.load();

            PantallaBienvenidaViewController pantallaBienvenidaViewController = loader.getController();
            pantallaBienvenidaViewController.setApp(this);

            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void openPantallaSesionUsuario() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaSesionUsuario.fxml"));

            AnchorPane rootLayout = loader.load();

            PantallaSesionUsuarioViewController pantallaSesionUsuarioViewController= loader.getController();
            pantallaSesionUsuarioViewController.setApp(this);
            pantallaSesionUsuarioViewController.setListGlobalUsuarios(listGlobalUsuarios);

            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void openPantallaUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaUsuario.fxml"));
            AnchorPane rootLayout = loader.load();
            PantallaUsuarioViewController pantallaUsuarioViewController= loader.getController();
            pantallaUsuarioViewController.setApp(this);
            if (this.usuarioSesion != null) {
                pantallaUsuarioViewController.setUsuarioSesion(this.usuarioSesion);
                pantallaUsuarioViewController.gettUsuario().setText(this.usuarioSesion.getNombreCompleto());
            }
            pantallaUsuarioViewController.initialize();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene); stage.setResizable(false);
            stage.centerOnScreen(); stage.show();
        } catch (IOException ex)
        { ex.printStackTrace();
        }
    }
    public void openPantallaUsuarioConfiguracion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaUsuarioConfiguracion.fxml"));
            AnchorPane rootLayout = loader.load();

            // Controlador de la pantalla de configuración
            PantallaUsuarioConfiguracionViewController configController = loader.getController();
            configController.setApp(this); // si necesitás acceso a la App principal

            // Nueva ventana (Stage)
            Stage ventanaConfig = new Stage();
            ventanaConfig.setTitle("Configuración");
            ventanaConfig.setScene(new Scene(rootLayout));
            ventanaConfig.initModality(Modality.WINDOW_MODAL);
            ventanaConfig.initOwner(stage); // bloquea la principal mientras está abierta
            ventanaConfig.setResizable(false);
            ventanaConfig.centerOnScreen();

            // Espera hasta que se cierre
            ventanaConfig.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




    public void openPantallaSesionRepartidor() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/pantallaSesionRepartidor.fxml"));

            AnchorPane rootLayout = loader.load();

            pantallaSesionRepartidorViewController pantallaSesionRepartidorViewController= loader.getController();
            pantallaSesionRepartidorViewController.setApp(this);
            pantallaSesionRepartidorViewController.setListGlobalRepartidores(listGlobalRepartidores);

            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void openPantallaSesionAdministrador() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/pantallaSesionAdministrador.fxml"));

            AnchorPane rootLayout = loader.load();

            PantallaSesionAdministradorViewController pantallaSesionAdministradorViewController= loader.getController();
            pantallaSesionAdministradorViewController.setApp(this);
            pantallaSesionAdministradorViewController.setAdministrador(admin);

            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void openPantallaRegistroUsuario() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaRegistroUsuario.fxml"));

            AnchorPane rootLayout = loader.load();

            PantallaRegistroUsuarioViewController pantallaRegistroUsuarioViewController= loader.getController();
            pantallaRegistroUsuarioViewController.app = this;
            pantallaRegistroUsuarioViewController.usuarios = listGlobalUsuarios;

            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void inicializarData(){

        Administrador admin = Administrador.getInstance();
        admin.setCorreo("admin@admin.com");
        admin.setClave(1234);
        this.admin = admin;

        Usuario usuario = new Usuario("Juan Carlos Ceballos","123456789","jcarlosc@gmail.com","jcc",123456789,this.idUsuario+1);
        listGlobalUsuarios.add(usuario);
        this.idUsuario ++;
        Repartidor repartidor = new Repartidor("Manuel Perez","123456789","mp@hotmail.com","mprep",987654321, ZonaCobertura.NORTE, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor);
        Usuario usuario1 = new Usuario("Diana Paola Rodriguez","1077865892","dpr@gmail.com","dpr",3789456,this.idUsuario+1);
        this.idUsuario ++;
        listGlobalUsuarios.add(usuario1);
        //Usuarios VIP
        Usuario usuario2 = new Usuario("Juan Manuel Velásquez","115187043","vivamemento@si.com","hola",6745,this.idUsuario+1);
        this.idUsuario ++;
        listGlobalUsuarios.add(usuario2);



// Productos iniciales
        Producto producto = new Producto(this.idProducto, "Mouse", "Para jugar mucho.", 140000, 10, 60);
        listGlobalProductos.add(producto);
        usuario.getListProductosUsuario().add(producto);
        this.idProducto++;

        Producto producto1 = new Producto(this.idProducto, "Audífonos", "Inalámbricos con cancelación de ruido.", 60000, 5, 6);
        listGlobalProductos.add(producto1);
        usuario1.getListProductosUsuario().add(producto1);
        this.idProducto++;

// 10 productos más
        Producto producto2 = new Producto(this.idProducto, "Teclado mecánico", "Retroiluminado RGB.", 180000, 7, 15);
        listGlobalProductos.add(producto2);
        usuario1.getListProductosUsuario().add(producto2);
        this.idProducto++;

        Producto producto3 = new Producto(this.idProducto, "Monitor 24\"", "Full HD 144Hz para gaming.", 650000, 4, 8);
        listGlobalProductos.add(producto3);
        usuario1.getListProductosUsuario().add(producto3);
        this.idProducto++;

        Producto producto4 = new Producto(this.idProducto, "Silla gamer", "Ergonómica con soporte lumbar.", 480000, 3, 5);
        listGlobalProductos.add(producto4);
        usuario.getListProductosUsuario().add(producto4);
        this.idProducto++;

        Producto producto5 = new Producto(this.idProducto, "Disco SSD 1TB", "Alta velocidad NVMe.", 350000, 12, 25);
        listGlobalProductos.add(producto5);
        usuario.getListProductosUsuario().add(producto5);
        this.idProducto++;

        Producto producto6 = new Producto(this.idProducto, "Memoria RAM 16GB", "DDR4 3200MHz.", 250000, 10, 30);
        listGlobalProductos.add(producto6);
        usuario1.getListProductosUsuario().add(producto6);
        this.idProducto++;

        Producto producto7 = new Producto(this.idProducto, "Fuente de poder 650W", "Certificación 80+ Bronze.", 220000, 6, 9);
        listGlobalProductos.add(producto7);
        usuario1.getListProductosUsuario().add(producto7);
        this.idProducto++;

        Producto producto8 = new Producto(this.idProducto, "Tarjeta madre", "Compatibilidad Intel 12va Gen.", 500000, 5, 10);
        listGlobalProductos.add(producto8);
        usuario.getListProductosUsuario().add(producto8);
        this.idProducto++;

        Producto producto9 = new Producto(this.idProducto, "Cámara web HD", "Ideal para videollamadas y streaming.", 90000, 8, 14);
        listGlobalProductos.add(producto9);
        usuario.getListProductosUsuario().add(producto9);
        this.idProducto++;

        Producto producto10 = new Producto(this.idProducto, "Micrófono USB", "Calidad de estudio para grabación.", 160000, 6, 12);
        listGlobalProductos.add(producto10);
        usuario1.getListProductosUsuario().add(producto10);
        this.idProducto++;

        Producto producto11 = new Producto(this.idProducto, "Mousepad XL", "Antideslizante y de superficie suave.", 40000, 15, 50);
        listGlobalProductos.add(producto11);
        usuario1.getListProductosUsuario().add(producto11);
        this.idProducto++;


    }
}
