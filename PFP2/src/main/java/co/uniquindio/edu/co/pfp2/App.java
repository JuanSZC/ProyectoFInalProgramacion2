package co.uniquindio.edu.co.pfp2;

import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.*;
import co.uniquindio.edu.co.pfp2.viewController.PantallaBienvenidaViewController;
import co.uniquindio.edu.co.pfp2.viewController.PantallaSesionAdministradorViewController;
import co.uniquindio.edu.co.pfp2.viewController.PantallaSesionUsuarioViewController;
import co.uniquindio.edu.co.pfp2.viewController.pantallaSesionRepartidorViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage stage;
    public Administrador admin;
    public ObservableList<Usuario> listGlobalUsuarios = FXCollections.observableArrayList();
    public ObservableList<Repartidor> listGlobalRepartidores = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Mercado Libre");
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
    public void inicializarData(){

        Administrador admin = Administrador.getInstance();
        admin.setCorreo("admin@admin.com");
        admin.setClave(1234);
        this.admin = admin;

        Usuario usuario = new Usuario("Juan Carlos Ceballos","123456789","jcarlosc@gmail.com","jcc",123456789,1111);
        listGlobalUsuarios.add(usuario);
        Repartidor repartidor = new Repartidor("Manuel Perez","123456789","mp@hotmail.com","mprep",987654321, ZonaCobertura.NORTE, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor);




    }
}
