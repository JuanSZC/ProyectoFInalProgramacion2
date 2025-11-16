package co.uniquindio.edu.co.pfp2;

import co.uniquindio.edu.co.pfp2.model.*;
import co.uniquindio.edu.co.pfp2.viewController.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class App extends Application {

   public Stage stage;
    public Administrador admin;
    public ObservableList<Usuario> listGlobalUsuarios = FXCollections.observableArrayList();
    public ObservableList<Repartidor> listGlobalRepartidores = FXCollections.observableArrayList();
    public ObservableList<Producto> listGlobalProductos = FXCollections.observableArrayList();
    public int idUsuario = 1111;
    public int idProducto = 1111;
    public int idDireccion = 1111;
    public int idEnvio = 1111;
    public int idRepartidor = 1111;



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
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaUsuarioConfiguracion.fxml")
            );
            AnchorPane rootLayout = loader.load();

            // Controlador de la pantalla de configuración
            PantallaUsuarioConfiguracionViewController configController = loader.getController();
            configController.setApp(this);
            configController.initialize();

            // Crear la ventana modal
            Stage ventanaConfig = new Stage();
            ventanaConfig.setTitle("Configuración");
            ventanaConfig.setScene(new Scene(rootLayout));
            ventanaConfig.initModality(Modality.WINDOW_MODAL);
            ventanaConfig.initOwner(stage); // el dueño es la ventana principal
            ventanaConfig.setResizable(false);
            ventanaConfig.centerOnScreen();

            // Mostrar la ventana y esperar a que se cierre
            ventanaConfig.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void openPantallaUsuarioPago() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaUsuarioPago.fxml"));
            AnchorPane rootLayout = loader.load();

            PantallaUsuarioPagoViewController controller = loader.getController();
            controller.setApp(this);

            // Crear ventana modal correctamente sin tocar la principal
            Stage ventanaPago = new Stage();
            ventanaPago.setTitle("Pago");
            ventanaPago.setScene(new Scene(rootLayout));
            ventanaPago.initModality(Modality.WINDOW_MODAL);
            ventanaPago.initOwner(stage); // dueño = principal
            ventanaPago.setResizable(false);
            ventanaPago.centerOnScreen();

            // Mostrar sin cerrar la ventana principal
            ventanaPago.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPantallaDireccionUsuario(Stage ownerStage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaDireccionUsuario.fxml")
            );
            AnchorPane rootLayout = loader.load();

            PantallaDireccionUsuarioViewController controller = loader.getController();
            controller.setApp(this);

            Stage ventanaDireccion = new Stage();
            ventanaDireccion.setTitle("Dirección");
            ventanaDireccion.setScene(new Scene(rootLayout));
            ventanaDireccion.initModality(Modality.WINDOW_MODAL);
            ventanaDireccion.initOwner(ownerStage); // la ventana que la llamó
            ventanaDireccion.setResizable(false);
            ventanaDireccion.centerOnScreen();

            ventanaDireccion.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void cerrarVentanaModular() {
        Stage stageActual = (Stage) this.stage.getScene().getWindow();
        stageActual.close();
    }
    public void cerrarMod(Button bt){
        Stage stageActual = (Stage) bt.getScene().getWindow();
        stageActual.close();
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
public void openPantallaAdministrador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/edu/co/pfp2/PantallaAdministrador.fxml"));

            AnchorPane rootLayout = loader.load();

            PantallaAdministradorViewController controller = loader.getController();
            controller.setApp(this);
            controller.initialize();

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

        // ==================== REPARTIDORES ====================

// Repartidor 1
        Repartidor repartidor = new Repartidor(idRepartidor,"Manuel Perez","123456789","mp@hotmail.com","mprep",987654321, ZonaCobertura.NORTE, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor);
        idRepartidor++;

// Repartidor 2
        Repartidor repartidor1 = new Repartidor(idRepartidor,"Andrea Camargo","113412121","AC@gmail.com","rep321",123121212, ZonaCobertura.CENTRO, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor1);
        idRepartidor++;

// Repartidor 3
        Repartidor repartidor2 = new Repartidor(idRepartidor,"Juan Pablo Ramirez","123421234","JpRamirez@hotmail.com","jprPass",121212121, ZonaCobertura.SUR, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor2);
        idRepartidor++;

// Repartidor 4
        Repartidor repartidor3 = new Repartidor(idRepartidor,"Felipe felipe felipe","12121212","CSCol@outlook.es","ccsgo",98787898, ZonaCobertura.MUNICIPIO_CERCANO, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor3);
        idRepartidor++;

// Repartidor 5
        Repartidor repartidor4 = new Repartidor(idRepartidor,"Sofía Morales","112233445","sofia.m@gmail.com","sofiapass",123456780, ZonaCobertura.NORTE, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor4);
        idRepartidor++;

// Repartidor 6
        Repartidor repartidor5 = new Repartidor(idRepartidor,"Felipe Torres","109876543","ftorres@gmail.com","felipet",987654320, ZonaCobertura.CENTRO, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor5);
        idRepartidor++;

// Repartidor 7
        Repartidor repartidor6 = new Repartidor(idRepartidor,"Laura Sofia Sanchez","101234567","laura.j@gmail.com","lajim",1123581321, ZonaCobertura.SUR, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor6);
        idRepartidor++;

// Repartidor 8
        Repartidor repartidor7 = new Repartidor(idRepartidor,"Camilo Camilo Camilo","102345678","andres.g@gmail.com","andresG",22334455, ZonaCobertura.MUNICIPIO_CERCANO, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor7);
        idRepartidor++;
// Repartidor 9

        Repartidor repartidor8 = new Repartidor(idRepartidor,"Andres Felipe Hernandez","103456789","valentina.r@gmail.com","valenR",33445566, ZonaCobertura.NORTE, DisponibilidadRepartidor.DISPONIBLE);
        listGlobalRepartidores.add(repartidor8);
        idRepartidor++;


        // ==================== USUARIOS ====================

// Usuario 0 (ya creado)
        Usuario usuario = new Usuario("Juan Carlos Ceballos","123456789","jcarlosc@gmail.com","jcc",123456789,this.idUsuario);
        listGlobalUsuarios.add(usuario);
        this.idUsuario ++;
        Direccion direccion = new Direccion(idDireccion,"Carrera 17 #7-21",4.620278,-75.635833,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario.getListDireccionesUsuario().add(direccion);

// Usuario 1 (ya creado)
        Usuario usuario1 = new Usuario("Diana Paola Rodriguez","1077865892","dpr@gmail.com","dpr",3789456,this.idUsuario);
        listGlobalUsuarios.add(usuario1);
        this.idUsuario ++;

// Usuario 2 (ya creado)
        Usuario usuario2 = new Usuario("Juan Manuel Velásquez","115187043","vivamemento@si.com","hola",6745,this.idUsuario);
        listGlobalUsuarios.add(usuario2);
        this.idUsuario ++;

        // Usuario 1 (ya creado) - agregando dirección
        Direccion direccion1 = new Direccion(idDireccion, "Calle 10 #5-22", 4.621500, -75.634200, ZonaCobertura.NORTE);
        idDireccion++;
        usuario1.getListDireccionesUsuario().add(direccion1);

// Usuario 2 (ya creado) - agregando dirección
        Direccion direccion2 = new Direccion(idDireccion, "Carrera 15 #7-30", 4.619800, -75.636500, ZonaCobertura.CENTRO);
        idDireccion++;
        usuario2.getListDireccionesUsuario().add(direccion2);


// Usuario 3
        Usuario usuario3 = new Usuario("Andrea López","1023456789","andrea.lopez@gmail.com","andreaL",99887766,this.idUsuario);
        listGlobalUsuarios.add(usuario3);
        this.idUsuario ++;
        Direccion direccion3 = new Direccion(idDireccion,"Calle 7 #6-20",4.618900,-75.637000,ZonaCobertura.SUR);
        idDireccion++;
        usuario3.getListDireccionesUsuario().add(direccion3);

// Usuario 4
        Usuario usuario4 = new Usuario("Carlos Ramírez","1102345698","carlosr@gmail.com","carlr",55667788,this.idUsuario);
        listGlobalUsuarios.add(usuario4);
        this.idUsuario ++;
        Direccion direccion4 = new Direccion(idDireccion,"Carrera 12 #5-18",4.622800,-75.636200,ZonaCobertura.CENTRO);
        idDireccion++;
        usuario4.getListDireccionesUsuario().add(direccion4);

// Usuario 5
        Usuario usuario5 = new Usuario("María Fernanda Suárez","1098765432","mfsuarez@gmail.com","mfs",33445566,this.idUsuario);
        listGlobalUsuarios.add(usuario5);
        this.idUsuario ++;
        Direccion direccion5 = new Direccion(idDireccion,"Calle 15 #8-45",4.624500,-75.635000,ZonaCobertura.NORTE);
        idDireccion++;
        usuario5.getListDireccionesUsuario().add(direccion5);

// Usuario 6
        Usuario usuario6 = new Usuario("Andrés Felipe Torres","1087654321","aftorres@gmail.com","andresFT",22334455,this.idUsuario);
        listGlobalUsuarios.add(usuario6);
        this.idUsuario ++;
        Direccion direccion6 = new Direccion(idDireccion,"Carrera 20 #10-30",4.627800,-75.632500,ZonaCobertura.CENTRO);
        idDireccion++;
        usuario6.getListDireccionesUsuario().add(direccion6);

// Usuario 7
        Usuario usuario7 = new Usuario("Valentina Gómez","1076543210","valentinaG@gmail.com","valenG",11223344,this.idUsuario);
        listGlobalUsuarios.add(usuario7);
        this.idUsuario ++;
        Direccion direccion7 = new Direccion(idDireccion,"Calle 5 #3-22",4.619500,-75.638500,ZonaCobertura.SUR);
        idDireccion++;
        usuario7.getListDireccionesUsuario().add(direccion7);

// Usuario 8
        Usuario usuario8 = new Usuario("Sebastián Martínez","1065432109","sebastianM@gmail.com","sebasM",66778899,this.idUsuario);
        listGlobalUsuarios.add(usuario8);
        this.idUsuario ++;
        Direccion direccion8 = new Direccion(idDireccion,"Carrera 18 #9-12",4.623000,-75.634000,ZonaCobertura.NORTE);
        idDireccion++;
        usuario8.getListDireccionesUsuario().add(direccion8);

// Usuario 9
        Usuario usuario9 = new Usuario("Lorena Castillo","1054321098","lorenaC@gmail.com","lorenaC",44556677,this.idUsuario);
        listGlobalUsuarios.add(usuario9);
        this.idUsuario ++;
        Direccion direccion9 = new Direccion(idDireccion,"Calle 11 #7-35",4.621500,-75.636500,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario9.getListDireccionesUsuario().add(direccion9);
// Usuario 10
        Usuario usuario10 = new Usuario("Carlos Gómez","1012345678","carlosG@gmail.com","carlosG",11223344,this.idUsuario);
        listGlobalUsuarios.add(usuario10);
        this.idUsuario++;
        Direccion direccion10 = new Direccion(idDireccion,"Carrera 15 #8-45",4.625000,-75.635000,ZonaCobertura.SUR);
        idDireccion++;
        usuario10.getListDireccionesUsuario().add(direccion10);

// Usuario 11
        Usuario usuario11 = new Usuario("Ana Rodríguez","1023456789","anaR@gmail.com","anaR",22334455,this.idUsuario);
        listGlobalUsuarios.add(usuario11);
        this.idUsuario++;
        Direccion direccion11 = new Direccion(idDireccion,"Calle 9 #5-20",4.622500,-75.637000,ZonaCobertura.NORTE);
        idDireccion++;
        usuario11.getListDireccionesUsuario().add(direccion11);

// Usuario 12
        Usuario usuario12 = new Usuario("Miguel Torres","1034567890","miguelT@gmail.com","miguelT",33445566,this.idUsuario);
        listGlobalUsuarios.add(usuario12);
        this.idUsuario++;
        Direccion direccion12 = new Direccion(idDireccion,"Carrera 20 #10-15",4.624000,-75.633000,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario12.getListDireccionesUsuario().add(direccion12);

// Usuario 13
        Usuario usuario13 = new Usuario("Laura Pérez","1045678901","lauraP@gmail.com","lauraP",44556677,this.idUsuario);
        listGlobalUsuarios.add(usuario13);
        this.idUsuario++;
        Direccion direccion13 = new Direccion(idDireccion,"Calle 12 #6-18",4.621800,-75.638000,ZonaCobertura.NORTE);
        idDireccion++;
        usuario13.getListDireccionesUsuario().add(direccion13);

// Usuario 14
        Usuario usuario14 = new Usuario("Diego Ramírez","1056789012","diegoR@gmail.com","diegoR",55667788,this.idUsuario);
        listGlobalUsuarios.add(usuario14);
        this.idUsuario++;
        Direccion direccion14 = new Direccion(idDireccion,"Carrera 22 #11-25",4.626000,-75.632500,ZonaCobertura.SUR);
        idDireccion++;
        usuario14.getListDireccionesUsuario().add(direccion14);

// Usuario 15
        Usuario usuario15 = new Usuario("Sofía Martínez","1067890123","sofiaM@gmail.com","sofiaM",66778899,this.idUsuario);
        listGlobalUsuarios.add(usuario15);
        this.idUsuario++;
        Direccion direccion15 = new Direccion(idDireccion,"Calle 14 #7-30",4.623500,-75.635500,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario15.getListDireccionesUsuario().add(direccion15);

// Usuario 16
        Usuario usuario16 = new Usuario("Andrés López","1078901234","andresL@gmail.com","andresL",77889900,this.idUsuario);
        listGlobalUsuarios.add(usuario16);
        this.idUsuario++;
        Direccion direccion16 = new Direccion(idDireccion,"Carrera 17 #9-10",4.624500,-75.634500,ZonaCobertura.NORTE);
        idDireccion++;
        usuario16.getListDireccionesUsuario().add(direccion16);

// Usuario 17
        Usuario usuario17 = new Usuario("Camila Herrera","1089012345","camilaH@gmail.com","camilaH",88990011,this.idUsuario);
        listGlobalUsuarios.add(usuario17);
        this.idUsuario++;
        Direccion direccion17 = new Direccion(idDireccion,"Calle 16 #8-12",4.622800,-75.636800,ZonaCobertura.SUR);
        idDireccion++;
        usuario17.getListDireccionesUsuario().add(direccion17);

// Usuario 18
        Usuario usuario18 = new Usuario("Fernando Ruiz","1090123456","fernandoR@gmail.com","fernandoR",99001122,this.idUsuario);
        listGlobalUsuarios.add(usuario18);
        this.idUsuario++;
        Direccion direccion18 = new Direccion(idDireccion,"Carrera 19 #10-40",4.625500,-75.633500,ZonaCobertura.NORTE);
        idDireccion++;
        usuario18.getListDireccionesUsuario().add(direccion18);

// Usuario 19
        Usuario usuario19 = new Usuario("Isabella Jiménez","1101234567","isabellaJ@gmail.com","isabellaJ",10111213,this.idUsuario);
        listGlobalUsuarios.add(usuario19);
        this.idUsuario++;
        Direccion direccion19 = new Direccion(idDireccion,"Calle 18 #9-15",4.623800,-75.634800,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario19.getListDireccionesUsuario().add(direccion19);

// Usuario 20
        Usuario usuario20 = new Usuario("Jorge Medina","1112345678","jorgeM@gmail.com","jorgeM",12131415,this.idUsuario);
        listGlobalUsuarios.add(usuario20);
        this.idUsuario++;
        Direccion direccion20 = new Direccion(idDireccion,"Carrera 21 #12-20",4.626500,-75.632000,ZonaCobertura.SUR);
        idDireccion++;
        usuario20.getListDireccionesUsuario().add(direccion20);

// Usuario 21
        Usuario usuario21 = new Usuario("Valentina Castillo","1123456789","valentinaC@gmail.com","valentinaC",13141516,this.idUsuario);
        listGlobalUsuarios.add(usuario21);
        this.idUsuario++;
        Direccion direccion21 = new Direccion(idDireccion,"Calle 20 #11-25",4.624200,-75.635200,ZonaCobertura.NORTE);
        idDireccion++;
        usuario21.getListDireccionesUsuario().add(direccion21);

// Usuario 22
        Usuario usuario22 = new Usuario("Ricardo Vargas","1134567890","ricardoV@gmail.com","ricardoV",14151617,this.idUsuario);
        listGlobalUsuarios.add(usuario22);
        this.idUsuario++;
        Direccion direccion22 = new Direccion(idDireccion,"Carrera 23 #13-30",4.627000,-75.631500,ZonaCobertura.SUR);
        idDireccion++;
        usuario22.getListDireccionesUsuario().add(direccion22);

// Usuario 23
        Usuario usuario23 = new Usuario("Natalia Rojas","1145678901","nataliaR@gmail.com","nataliaR",15161718,this.idUsuario);
        listGlobalUsuarios.add(usuario23);
        this.idUsuario++;
        Direccion direccion23 = new Direccion(idDireccion,"Calle 22 #12-18",4.625200,-75.634200,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario23.getListDireccionesUsuario().add(direccion23);

// Usuario 24
        Usuario usuario24 = new Usuario("Luis Fernández","1156789012","luisF@gmail.com","luisF",16171819,this.idUsuario);
        listGlobalUsuarios.add(usuario24);
        this.idUsuario++;
        Direccion direccion24 = new Direccion(idDireccion,"Carrera 24 #14-25",4.628000,-75.630500,ZonaCobertura.SUR);
        idDireccion++;
        usuario24.getListDireccionesUsuario().add(direccion24);

// Usuario 25
        Usuario usuario25 = new Usuario("Daniela Morales","1167890123","danielaM@gmail.com","danielaM",17181920,this.idUsuario);
        listGlobalUsuarios.add(usuario25);
        this.idUsuario++;
        Direccion direccion25 = new Direccion(idDireccion,"Calle 24 #15-30",4.626500,-75.633000,ZonaCobertura.NORTE);
        idDireccion++;
        usuario25.getListDireccionesUsuario().add(direccion25);

// Usuario 26
        Usuario usuario26 = new Usuario("Sebastián Torres","1178901234","sebastianT@gmail.com","sebastianT",18192021,this.idUsuario);
        listGlobalUsuarios.add(usuario26);
        this.idUsuario++;
        Direccion direccion26 = new Direccion(idDireccion,"Carrera 25 #16-20",4.629000,-75.632500,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario26.getListDireccionesUsuario().add(direccion26);

// Usuario 27
        Usuario usuario27 = new Usuario("Mariana Salazar","1189012345","marianaS@gmail.com","marianaS",19202122,this.idUsuario);
        listGlobalUsuarios.add(usuario27);
        this.idUsuario++;
        Direccion direccion27 = new Direccion(idDireccion,"Calle 26 #17-35",4.627500,-75.634500,ZonaCobertura.SUR);
        idDireccion++;
        usuario27.getListDireccionesUsuario().add(direccion27);

// Usuario 28
        Usuario usuario28 = new Usuario("Javier Herrera","1190123456","javierH@gmail.com","javierH",20212223,this.idUsuario);
        listGlobalUsuarios.add(usuario28);
        this.idUsuario++;
        Direccion direccion28 = new Direccion(idDireccion,"Carrera 27 #18-10",4.630000,-75.631000,ZonaCobertura.NORTE);
        idDireccion++;
        usuario28.getListDireccionesUsuario().add(direccion28);

// Usuario 29
        Usuario usuario29 = new Usuario("Paula Vargas","1201234567","paulaV@gmail.com","paulaV",21222324,this.idUsuario);
        listGlobalUsuarios.add(usuario29);
        this.idUsuario++;
        Direccion direccion29 = new Direccion(idDireccion,"Calle 28 #19-12",4.628200,-75.632800,ZonaCobertura.MUNICIPIO_CERCANO);
        idDireccion++;
        usuario29.getListDireccionesUsuario().add(direccion29);

// Usuario 30
        Usuario usuario30 = new Usuario("Andrés Morales","1212345678","andresM@gmail.com","andresM",22232425,this.idUsuario);
        listGlobalUsuarios.add(usuario30);
        this.idUsuario++;
        Direccion direccion30 = new Direccion(idDireccion,"Carrera 29 #20-15",4.631000,-75.630500,ZonaCobertura.SUR);
        idDireccion++;
        usuario30.getListDireccionesUsuario().add(direccion30);



// Productos iniciales
        Producto producto = new Producto(this.idProducto, "Mouse", "Para jugar mucho.", 140000, 10, 60);
        listGlobalProductos.add(producto);
        usuario.getListProductosUsuario().add(producto);
        this.idProducto++;

        Producto producto1 = new Producto(this.idProducto, "Audífonos", "Inalámbricos con cancelación de ruido.", 60000, 5, 6);
        listGlobalProductos.add(producto1);
        usuario.getListProductosUsuario().add(producto1);
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
        usuario1.getListProductosUsuario().add(producto4);
        this.idProducto++;

        Producto producto5 = new Producto(this.idProducto, "Disco SSD 1TB", "Alta velocidad NVMe.", 350000, 12, 25);
        listGlobalProductos.add(producto5);
        usuario2.getListProductosUsuario().add(producto5);
        this.idProducto++;

        Producto producto6 = new Producto(this.idProducto, "Memoria RAM 16GB", "DDR4 3200MHz.", 250000, 10, 30);
        listGlobalProductos.add(producto6);
        usuario2.getListProductosUsuario().add(producto6);
        this.idProducto++;

        Producto producto7 = new Producto(this.idProducto, "Fuente de poder 650W", "Certificación 80+ Bronze.", 220000, 6, 9);
        listGlobalProductos.add(producto7);
        usuario2.getListProductosUsuario().add(producto7);
        this.idProducto++;

        Producto producto8 = new Producto(this.idProducto, "Tarjeta madre", "Compatibilidad Intel 12va Gen.", 500000, 5, 10);
        listGlobalProductos.add(producto8);
        usuario3.getListProductosUsuario().add(producto8);
        this.idProducto++;

        Producto producto9 = new Producto(this.idProducto, "Cámara web HD", "Ideal para videollamadas y streaming.", 90000, 8, 14);
        listGlobalProductos.add(producto9);
        usuario3.getListProductosUsuario().add(producto9);
        this.idProducto++;

        Producto producto10 = new Producto(this.idProducto, "Micrófono USB", "Calidad de estudio para grabación.", 160000, 6, 12);
        listGlobalProductos.add(producto10);
        usuario3.getListProductosUsuario().add(producto10);
        this.idProducto++;

        Producto producto11 = new Producto(this.idProducto, "Mousepad XL", "Antideslizante y de superficie suave.", 40000, 15, 50);
        listGlobalProductos.add(producto11);
        usuario4.getListProductosUsuario().add(producto11);
        this.idProducto++;

        // Producto 12
        Producto producto12 = new Producto(this.idProducto, "Auriculares Gaming RGB", "Con micrófono ajustable.", 75000, 6, 7);
        listGlobalProductos.add(producto12);
        usuario4.getListProductosUsuario().add(producto12);
        this.idProducto++;

// Producto 13
        Producto producto13 = new Producto(this.idProducto, "Teclado inalámbrico", "Compacto y silencioso.", 120000, 8, 12);
        listGlobalProductos.add(producto13);
        usuario4.getListProductosUsuario().add(producto13);
        this.idProducto++;

// Producto 14
        Producto producto14 = new Producto(this.idProducto, "Silla de oficina", "Ergonómica con soporte lumbar.", 300000, 4, 10);
        listGlobalProductos.add(producto14);
        usuario5.getListProductosUsuario().add(producto14);
        this.idProducto++;

// Producto 15
        Producto producto15 = new Producto(this.idProducto, "Monitor 27\" 4K", "Alta resolución para diseño.", 950000, 3, 10);
        listGlobalProductos.add(producto15);
        usuario5.getListProductosUsuario().add(producto15);
        this.idProducto++;

// Producto 16
        Producto producto16 = new Producto(this.idProducto, "SSD 512GB", "Alta velocidad NVMe.", 220000, 10, 20);
        listGlobalProductos.add(producto16);
        usuario5.getListProductosUsuario().add(producto16);
        this.idProducto++;

// Producto 17
        Producto producto17 = new Producto(this.idProducto, "Memoria RAM 8GB", "DDR4 3200MHz.", 140000, 12, 25);
        listGlobalProductos.add(producto17);
        usuario6.getListProductosUsuario().add(producto17);
        this.idProducto++;

// Producto 18
        Producto producto18 = new Producto(this.idProducto, "Fuente de poder 500W", "Certificación 80+ Bronze.", 180000, 5, 8);
        listGlobalProductos.add(producto18);
        usuario6.getListProductosUsuario().add(producto18);
        this.idProducto++;

// Producto 19
        Producto producto19 = new Producto(this.idProducto, "Tarjeta madre Mini-ITX", "Compatibilidad Intel y AMD.", 420000, 4, 8);
        listGlobalProductos.add(producto19);
        usuario6.getListProductosUsuario().add(producto19);
        this.idProducto++;

// Producto 20
        Producto producto20 = new Producto(this.idProducto, "Webcam 1080p", "Para videollamadas y streaming.", 85000, 6, 10);
        listGlobalProductos.add(producto20);
        usuario7.getListProductosUsuario().add(producto20);
        this.idProducto++;

// Producto 21
        Producto producto21 = new Producto(this.idProducto, "Micrófono condensador", "Calidad profesional.", 180000, 5, 9);
        listGlobalProductos.add(producto21);
        usuario7.getListProductosUsuario().add(producto21);
        this.idProducto++;

// Producto 22
        Producto producto22 = new Producto(this.idProducto, "Mousepad XL Gaming", "Superficie suave y antideslizante.", 50000, 10, 40);
        listGlobalProductos.add(producto22);
        usuario7.getListProductosUsuario().add(producto22);
        this.idProducto++;

// Producto 23
        Producto producto23 = new Producto(this.idProducto, "Auriculares Bluetooth", "Ligero y portátil.", 70000, 8, 5);
        listGlobalProductos.add(producto23);
        usuario8.getListProductosUsuario().add(producto23);
        this.idProducto++;

// Producto 24
        Producto producto24 = new Producto(this.idProducto, "Teclado mecánico compacto", "Switches silenciosos.", 160000, 6, 10);
        listGlobalProductos.add(producto24);
        usuario8.getListProductosUsuario().add(producto24);
        this.idProducto++;

// Producto 25
        Producto producto25 = new Producto(this.idProducto, "Monitor ultrawide 29\"", "Curvo, 75Hz.", 750000, 3, 9);
        listGlobalProductos.add(producto25);
        usuario8.getListProductosUsuario().add(producto25);
        this.idProducto++;

// Producto 26
        Producto producto26 = new Producto(this.idProducto, "Disco duro 2TB", "Almacenamiento adicional.", 300000, 12, 30);
        listGlobalProductos.add(producto26);
        usuario9.getListProductosUsuario().add(producto26);
        this.idProducto++;

// Producto 27
        Producto producto27 = new Producto(this.idProducto, "Memoria RAM 32GB", "DDR4 3600MHz.", 450000, 6, 35);
        listGlobalProductos.add(producto27);
        usuario9.getListProductosUsuario().add(producto27);
        this.idProducto++;

// Producto 28
        Producto producto28 = new Producto(this.idProducto, "Fuente de poder 750W", "Certificación 80+ Gold.", 350000, 4, 10);
        listGlobalProductos.add(producto28);
        usuario9.getListProductosUsuario().add(producto28);
        this.idProducto++;

// Producto 29
        Producto producto29 = new Producto(this.idProducto, "Tarjeta de video GTX 1660", "6GB GDDR5 para gaming.", 1200000, 2, 8);
        listGlobalProductos.add(producto29);
        usuario1.getListProductosUsuario().add(producto29);
        this.idProducto++;



// =================== ENVIOS PARA usuario (usuario) ===================
        Paquete paqueteU0_1 = new Paquete(0,0,0);
        paqueteU0_1.getProductos().add(producto);
        paqueteU0_1.getProductos().add(producto1);
        paqueteU0_1.getProductos().add(producto2);
        double precioU0_1=0, pesoU0_1=0;
        for (Producto p : paqueteU0_1.getProductos()) { precioU0_1 += p.getPrecio() * p.getCantidad(); pesoU0_1 += p.getPeso() * p.getCantidad(); }
        paqueteU0_1.setPrecio(precioU0_1); paqueteU0_1.setPeso(pesoU0_1);
        Envio envioU0_1 = new Envio(idEnvio++, direccion, direccion1, paqueteU0_1, LocalDate.of(2025,6,12), EstadoEnvio.ENTREGADO, repartidor);
        usuario.getListEnviosUsuario().add(envioU0_1);

        Paquete paqueteU0_2 = new Paquete(0,0,0);
        paqueteU0_2.getProductos().add(producto3);
        paqueteU0_2.getProductos().add(producto4);
        double precioU0_2=0, pesoU0_2=0;
        for (Producto p : paqueteU0_2.getProductos()) { precioU0_2 += p.getPrecio() * p.getCantidad(); pesoU0_2 += p.getPeso() * p.getCantidad(); }
        paqueteU0_2.setPrecio(precioU0_2); paqueteU0_2.setPeso(pesoU0_2);
        Envio envioU0_2 = new Envio(idEnvio++, direccion1, direccion2, paqueteU0_2, LocalDate.of(2025,6,15), EstadoEnvio.CANCELADO, repartidor1);
        usuario.getListEnviosUsuario().add(envioU0_2);

        Paquete paqueteU0_3 = new Paquete(0,0,0);
        paqueteU0_3.getProductos().add(producto5);
        paqueteU0_3.getProductos().add(producto6);
        paqueteU0_3.getProductos().add(producto7);
        double precioU0_3=0, pesoU0_3=0;
        for (Producto p : paqueteU0_3.getProductos()) { precioU0_3 += p.getPrecio() * p.getCantidad(); pesoU0_3 += p.getPeso() * p.getCantidad(); }
        paqueteU0_3.setPrecio(precioU0_3); paqueteU0_3.setPeso(pesoU0_3);
        Envio envioU0_3 = new Envio(idEnvio++, direccion2, direccion3, paqueteU0_3, LocalDate.of(2025,6,18), EstadoEnvio.CANCELADO, repartidor2);
        usuario.getListEnviosUsuario().add(envioU0_3);

        Paquete paqueteU0_4 = new Paquete(0,0,0);
        paqueteU0_4.getProductos().add(producto8);
        paqueteU0_4.getProductos().add(producto9);
        double precioU0_4=0, pesoU0_4=0;
        for (Producto p : paqueteU0_4.getProductos()) { precioU0_4 += p.getPrecio() * p.getCantidad(); pesoU0_4 += p.getPeso() * p.getCantidad(); }
        paqueteU0_4.setPrecio(precioU0_4); paqueteU0_4.setPeso(pesoU0_4);
        Envio envioU0_4 = new Envio(idEnvio++, direccion3, direccion4, paqueteU0_4, LocalDate.of(2025,6,21), EstadoEnvio.ENTREGADO, repartidor3);
        usuario.getListEnviosUsuario().add(envioU0_4);

        Paquete paqueteU0_5 = new Paquete(0,0,0);
        paqueteU0_5.getProductos().add(producto10);
        paqueteU0_5.getProductos().add(producto11);
        double precioU0_5=0, pesoU0_5=0;
        for (Producto p : paqueteU0_5.getProductos()) { precioU0_5 += p.getPrecio() * p.getCantidad(); pesoU0_5 += p.getPeso() * p.getCantidad(); }
        paqueteU0_5.setPrecio(precioU0_5); paqueteU0_5.setPeso(pesoU0_5);
        Envio envioU0_5 = new Envio(idEnvio++, direccion4, direccion, paqueteU0_5, LocalDate.of(2025,6,25), EstadoEnvio.CANCELADO, repartidor4);
        usuario.getListEnviosUsuario().add(envioU0_5);


// =================== ENVIOS PARA usuario1 ===================
        Paquete paqueteU1_1 = new Paquete(0,0,0);
        paqueteU1_1.getProductos().add(producto2);
        paqueteU1_1.getProductos().add(producto4);
        paqueteU1_1.getProductos().add(producto6);
        double precioU1_1=0, pesoU1_1=0;
        for (Producto p : paqueteU1_1.getProductos()) { precioU1_1 += p.getPrecio() * p.getCantidad(); pesoU1_1 += p.getPeso() * p.getCantidad(); }
        paqueteU1_1.setPrecio(precioU1_1); paqueteU1_1.setPeso(pesoU1_1);
        Envio envioU1_1 = new Envio(idEnvio++, direccion1, direccion2, paqueteU1_1, LocalDate.of(2025,6,11), EstadoEnvio.CANCELADO, repartidor1);
        usuario1.getListEnviosUsuario().add(envioU1_1);

        Paquete paqueteU1_2 = new Paquete(0,0,0);
        paqueteU1_2.getProductos().add(producto3);
        paqueteU1_2.getProductos().add(producto5);
        double precioU1_2=0, pesoU1_2=0;
        for (Producto p : paqueteU1_2.getProductos()) { precioU1_2 += p.getPrecio() * p.getCantidad(); pesoU1_2 += p.getPeso() * p.getCantidad(); }
        paqueteU1_2.setPrecio(precioU1_2); paqueteU1_2.setPeso(pesoU1_2);
        Envio envioU1_2 = new Envio(idEnvio++, direccion2, direccion3, paqueteU1_2, LocalDate.of(2025,6,14), EstadoEnvio.CANCELADO, repartidor2);
        usuario1.getListEnviosUsuario().add(envioU1_2);

        Paquete paqueteU1_3 = new Paquete(0,0,0);
        paqueteU1_3.getProductos().add(producto7);
        paqueteU1_3.getProductos().add(producto8);
        paqueteU1_3.getProductos().add(producto9);
        double precioU1_3=0, pesoU1_3=0;
        for (Producto p : paqueteU1_3.getProductos()) { precioU1_3 += p.getPrecio() * p.getCantidad(); pesoU1_3 += p.getPeso() * p.getCantidad(); }
        paqueteU1_3.setPrecio(precioU1_3); paqueteU1_3.setPeso(pesoU1_3);
        Envio envioU1_3 = new Envio(idEnvio++, direccion3, direccion4, paqueteU1_3, LocalDate.of(2025,6,17), EstadoEnvio.CANCELADO, repartidor3);
        usuario1.getListEnviosUsuario().add(envioU1_3);

        Paquete paqueteU1_4 = new Paquete(0,0,0);
        paqueteU1_4.getProductos().add(producto10);
        paqueteU1_4.getProductos().add(producto1);
        double precioU1_4=0, pesoU1_4=0;
        for (Producto p : paqueteU1_4.getProductos()) { precioU1_4 += p.getPrecio() * p.getCantidad(); pesoU1_4 += p.getPeso() * p.getCantidad(); }
        paqueteU1_4.setPrecio(precioU1_4); paqueteU1_4.setPeso(pesoU1_4);
        Envio envioU1_4 = new Envio(idEnvio++, direccion4, direccion1, paqueteU1_4, LocalDate.of(2025,6,20), EstadoEnvio.ENTREGADO, repartidor4);
        usuario1.getListEnviosUsuario().add(envioU1_4);

        Paquete paqueteU1_5 = new Paquete(0,0,0);
        paqueteU1_5.getProductos().add(producto11);
        paqueteU1_5.getProductos().add(producto2);
        double precioU1_5=0, pesoU1_5=0;
        for (Producto p : paqueteU1_5.getProductos()) { precioU1_5 += p.getPrecio() * p.getCantidad(); pesoU1_5 += p.getPeso() * p.getCantidad(); }
        paqueteU1_5.setPrecio(precioU1_5); paqueteU1_5.setPeso(pesoU1_5);
        Envio envioU1_5 = new Envio(idEnvio++, direccion1, direccion3, paqueteU1_5, LocalDate.of(2025,6,23), EstadoEnvio.ENTREGADO, repartidor);
        usuario1.getListEnviosUsuario().add(envioU1_5);


// =================== ENVIOS PARA usuario2 ===================
        Paquete paqueteU2_1 = new Paquete(0,0,0);
        paqueteU2_1.getProductos().add(producto12);
        paqueteU2_1.getProductos().add(producto13);
        double precioU2_1=0, pesoU2_1=0;
        for (Producto p : paqueteU2_1.getProductos()) { precioU2_1 += p.getPrecio() * p.getCantidad(); pesoU2_1 += p.getPeso() * p.getCantidad(); }
        paqueteU2_1.setPrecio(precioU2_1); paqueteU2_1.setPeso(pesoU2_1);
        Envio envioU2_1 = new Envio(idEnvio++, direccion2, direccion4, paqueteU2_1, LocalDate.of(2025,6,13), EstadoEnvio.ENTREGADO, repartidor2);
        usuario2.getListEnviosUsuario().add(envioU2_1);

        Paquete paqueteU2_2 = new Paquete(0,0,0);
        paqueteU2_2.getProductos().add(producto14);
        paqueteU2_2.getProductos().add(producto10);
        paqueteU2_2.getProductos().add(producto11);
        double precioU2_2=0, pesoU2_2=0;
        for (Producto p : paqueteU2_2.getProductos()) { precioU2_2 += p.getPrecio() * p.getCantidad(); pesoU2_2 += p.getPeso() * p.getCantidad(); }
        paqueteU2_2.setPrecio(precioU2_2); paqueteU2_2.setPeso(pesoU2_2);
        Envio envioU2_2 = new Envio(idEnvio++, direccion3, direccion1, paqueteU2_2, LocalDate.of(2025,6,16), EstadoEnvio.CANCELADO, repartidor3);
        usuario2.getListEnviosUsuario().add(envioU2_2);

        Paquete paqueteU2_3 = new Paquete(0,0,0);
        paqueteU2_3.getProductos().add(producto5);
        paqueteU2_3.getProductos().add(producto7);
        double precioU2_3=0, pesoU2_3=0;
        for (Producto p : paqueteU2_3.getProductos()) { precioU2_3 += p.getPrecio() * p.getCantidad(); pesoU2_3 += p.getPeso() * p.getCantidad(); }
        paqueteU2_3.setPrecio(precioU2_3); paqueteU2_3.setPeso(pesoU2_3);
        Envio envioU2_3 = new Envio(idEnvio++, direccion4, direccion2, paqueteU2_3, LocalDate.of(2025,6,19), EstadoEnvio.CANCELADO, repartidor4);
        usuario2.getListEnviosUsuario().add(envioU2_3);

        Paquete paqueteU2_4 = new Paquete(0,0,0);
        paqueteU2_4.getProductos().add(producto8);
        paqueteU2_4.getProductos().add(producto9);
        paqueteU2_4.getProductos().add(producto1);
        double precioU2_4=0, pesoU2_4=0;
        for (Producto p : paqueteU2_4.getProductos()) { precioU2_4 += p.getPrecio() * p.getCantidad(); pesoU2_4 += p.getPeso() * p.getCantidad(); }
        paqueteU2_4.setPrecio(precioU2_4); paqueteU2_4.setPeso(pesoU2_4);
        Envio envioU2_4 = new Envio(idEnvio++, direccion1, direccion3, paqueteU2_4, LocalDate.of(2025,6,22), EstadoEnvio.ENTREGADO, repartidor);
        usuario2.getListEnviosUsuario().add(envioU2_4);

        Paquete paqueteU2_5 = new Paquete(0,0,0);
        paqueteU2_5.getProductos().add(producto2);
        paqueteU2_5.getProductos().add(producto3);
        double precioU2_5=0, pesoU2_5=0;
        for (Producto p : paqueteU2_5.getProductos()) { precioU2_5 += p.getPrecio() * p.getCantidad(); pesoU2_5 += p.getPeso() * p.getCantidad(); }
        paqueteU2_5.setPrecio(precioU2_5); paqueteU2_5.setPeso(pesoU2_5);
        Envio envioU2_5 = new Envio(idEnvio++, direccion2, direccion4, paqueteU2_5, LocalDate.of(2025,6,26), EstadoEnvio.CANCELADO, repartidor1);
        usuario2.getListEnviosUsuario().add(envioU2_5);


// =================== ENVIOS PARA usuario3 ===================
        Paquete paqueteU3_1 = new Paquete(0,0,0);
        paqueteU3_1.getProductos().add(producto15);
        paqueteU3_1.getProductos().add(producto16);
        double precioU3_1=0, pesoU3_1=0;
        for (Producto p : paqueteU3_1.getProductos()) { precioU3_1 += p.getPrecio() * p.getCantidad(); pesoU3_1 += p.getPeso() * p.getCantidad(); }
        paqueteU3_1.setPrecio(precioU3_1); paqueteU3_1.setPeso(pesoU3_1);
        Envio envioU3_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU3_1, LocalDate.of(2025,6,14), EstadoEnvio.CANCELADO, repartidor3);
        usuario3.getListEnviosUsuario().add(envioU3_1);

        Paquete paqueteU3_2 = new Paquete(0,0,0);
        paqueteU3_2.getProductos().add(producto17);
        paqueteU3_2.getProductos().add(producto18);
        paqueteU3_2.getProductos().add(producto19);
        double precioU3_2=0, pesoU3_2=0;
        for (Producto p : paqueteU3_2.getProductos()) { precioU3_2 += p.getPrecio() * p.getCantidad(); pesoU3_2 += p.getPeso() * p.getCantidad(); }
        paqueteU3_2.setPrecio(precioU3_2); paqueteU3_2.setPeso(pesoU3_2);
        Envio envioU3_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU3_2, LocalDate.of(2025,6,17), EstadoEnvio.CANCELADO, repartidor4);
        usuario3.getListEnviosUsuario().add(envioU3_2);

        Paquete paqueteU3_3 = new Paquete(0,0,0);
        paqueteU3_3.getProductos().add(producto20);
        paqueteU3_3.getProductos().add(producto21);
        double precioU3_3=0, pesoU3_3=0;
        for (Producto p : paqueteU3_3.getProductos()) { precioU3_3 += p.getPrecio() * p.getCantidad(); pesoU3_3 += p.getPeso() * p.getCantidad(); }
        paqueteU3_3.setPrecio(precioU3_3); paqueteU3_3.setPeso(pesoU3_3);
        Envio envioU3_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU3_3, LocalDate.of(2025,6,20), EstadoEnvio.ENTREGADO, repartidor);
        usuario3.getListEnviosUsuario().add(envioU3_3);

        Paquete paqueteU3_4 = new Paquete(0,0,0);
        paqueteU3_4.getProductos().add(producto22);
        paqueteU3_4.getProductos().add(producto23);
        paqueteU3_4.getProductos().add(producto24);
        double precioU3_4=0, pesoU3_4=0;
        for (Producto p : paqueteU3_4.getProductos()) { precioU3_4 += p.getPrecio() * p.getCantidad(); pesoU3_4 += p.getPeso() * p.getCantidad(); }
        paqueteU3_4.setPrecio(precioU3_4); paqueteU3_4.setPeso(pesoU3_4);
        Envio envioU3_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU3_4, LocalDate.of(2025,6,23), EstadoEnvio.ENTREGADO, repartidor1);
        usuario3.getListEnviosUsuario().add(envioU3_4);

        Paquete paqueteU3_5 = new Paquete(0,0,0);
        paqueteU3_5.getProductos().add(producto1);
        paqueteU3_5.getProductos().add(producto5);
        double precioU3_5=0, pesoU3_5=0;
        for (Producto p : paqueteU3_5.getProductos()) { precioU3_5 += p.getPrecio() * p.getCantidad(); pesoU3_5 += p.getPeso() * p.getCantidad(); }
        paqueteU3_5.setPrecio(precioU3_5); paqueteU3_5.setPeso(pesoU3_5);
        Envio envioU3_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU3_5, LocalDate.of(2025,6,27), EstadoEnvio.CANCELADO, repartidor2);
        usuario3.getListEnviosUsuario().add(envioU3_5);


// =================== ENVIOS PARA usuario4 ===================
        Paquete paqueteU4_1 = new Paquete(0,0,0);
        paqueteU4_1.getProductos().add(producto2);
        paqueteU4_1.getProductos().add(producto11);
        double precioU4_1=0, pesoU4_1=0;
        for (Producto p : paqueteU4_1.getProductos()) { precioU4_1 += p.getPrecio() * p.getCantidad(); pesoU4_1 += p.getPeso() * p.getCantidad(); }
        paqueteU4_1.setPrecio(precioU4_1); paqueteU4_1.setPeso(pesoU4_1);
        Envio envioU4_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU4_1, LocalDate.of(2025,6,12), EstadoEnvio.ENTREGADO, repartidor3);
        usuario4.getListEnviosUsuario().add(envioU4_1);

        Paquete paqueteU4_2 = new Paquete(0,0,0);
        paqueteU4_2.getProductos().add(producto3);
        paqueteU4_2.getProductos().add(producto4);
        paqueteU4_2.getProductos().add(producto6);
        double precioU4_2=0, pesoU4_2=0;
        for (Producto p : paqueteU4_2.getProductos()) { precioU4_2 += p.getPrecio() * p.getCantidad(); pesoU4_2 += p.getPeso() * p.getCantidad(); }
        paqueteU4_2.setPrecio(precioU4_2); paqueteU4_2.setPeso(pesoU4_2);
        Envio envioU4_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU4_2, LocalDate.of(2025,6,15), EstadoEnvio.CANCELADO, repartidor4);
        usuario4.getListEnviosUsuario().add(envioU4_2);

        Paquete paqueteU4_3 = new Paquete(0,0,0);
        paqueteU4_3.getProductos().add(producto7);
        paqueteU4_3.getProductos().add(producto8);
        double precioU4_3=0, pesoU4_3=0;
        for (Producto p : paqueteU4_3.getProductos()) { precioU4_3 += p.getPrecio() * p.getCantidad(); pesoU4_3 += p.getPeso() * p.getCantidad(); }
        paqueteU4_3.setPrecio(precioU4_3); paqueteU4_3.setPeso(pesoU4_3);
        Envio envioU4_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU4_3, LocalDate.of(2025,6,18), EstadoEnvio.CANCELADO, repartidor);
        usuario4.getListEnviosUsuario().add(envioU4_3);

        Paquete paqueteU4_4 = new Paquete(0,0,0);
        paqueteU4_4.getProductos().add(producto9);
        paqueteU4_4.getProductos().add(producto10);
        paqueteU4_4.getProductos().add(producto12);
        double precioU4_4=0, pesoU4_4=0;
        for (Producto p : paqueteU4_4.getProductos()) { precioU4_4 += p.getPrecio() * p.getCantidad(); pesoU4_4 += p.getPeso() * p.getCantidad(); }
        paqueteU4_4.setPrecio(precioU4_4); paqueteU4_4.setPeso(pesoU4_4);
        Envio envioU4_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU4_4, LocalDate.of(2025,6,21), EstadoEnvio.ENTREGADO, repartidor1);
        usuario4.getListEnviosUsuario().add(envioU4_4);

        Paquete paqueteU4_5 = new Paquete(0,0,0);
        paqueteU4_5.getProductos().add(producto13);
        paqueteU4_5.getProductos().add(producto14);
        double precioU4_5=0, pesoU4_5=0;
        for (Producto p : paqueteU4_5.getProductos()) { precioU4_5 += p.getPrecio() * p.getCantidad(); pesoU4_5 += p.getPeso() * p.getCantidad(); }
        paqueteU4_5.setPrecio(precioU4_5); paqueteU4_5.setPeso(pesoU4_5);
        Envio envioU4_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU4_5, LocalDate.of(2025,6,24), EstadoEnvio.ENTREGADO, repartidor2);
        usuario4.getListEnviosUsuario().add(envioU4_5);


// =================== ENVIOS PARA usuario5 ===================
        Paquete paqueteU5_1 = new Paquete(0,0,0);
        paqueteU5_1.getProductos().add(producto15);
        paqueteU5_1.getProductos().add(producto18);
        double precioU5_1=0, pesoU5_1=0;
        for (Producto p : paqueteU5_1.getProductos()) { precioU5_1 += p.getPrecio() * p.getCantidad(); pesoU5_1 += p.getPeso() * p.getCantidad(); }
        paqueteU5_1.setPrecio(precioU5_1); paqueteU5_1.setPeso(pesoU5_1);
        Envio envioU5_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU5_1, LocalDate.of(2025,6,13), EstadoEnvio.CANCELADO, repartidor3);
        usuario5.getListEnviosUsuario().add(envioU5_1);

        Paquete paqueteU5_2 = new Paquete(0,0,0);
        paqueteU5_2.getProductos().add(producto16);
        paqueteU5_2.getProductos().add(producto17);
        paqueteU5_2.getProductos().add(producto19);
        double precioU5_2=0, pesoU5_2=0;
        for (Producto p : paqueteU5_2.getProductos()) { precioU5_2 += p.getPrecio() * p.getCantidad(); pesoU5_2 += p.getPeso() * p.getCantidad(); }
        paqueteU5_2.setPrecio(precioU5_2); paqueteU5_2.setPeso(pesoU5_2);
        Envio envioU5_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU5_2, LocalDate.of(2025,6,16), EstadoEnvio.CANCELADO, repartidor4);
        usuario5.getListEnviosUsuario().add(envioU5_2);

        Paquete paqueteU5_3 = new Paquete(0,0,0);
        paqueteU5_3.getProductos().add(producto20);
        paqueteU5_3.getProductos().add(producto21);
        double precioU5_3=0, pesoU5_3=0;
        for (Producto p : paqueteU5_3.getProductos()) { precioU5_3 += p.getPrecio() * p.getCantidad(); pesoU5_3 += p.getPeso() * p.getCantidad(); }
        paqueteU5_3.setPrecio(precioU5_3); paqueteU5_3.setPeso(pesoU5_3);
        Envio envioU5_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU5_3, LocalDate.of(2025,6,19), EstadoEnvio.ENTREGADO, repartidor);
        usuario5.getListEnviosUsuario().add(envioU5_3);

        Paquete paqueteU5_4 = new Paquete(0,0,0);
        paqueteU5_4.getProductos().add(producto22);
        paqueteU5_4.getProductos().add(producto23);
        double precioU5_4=0, pesoU5_4=0;
        for (Producto p : paqueteU5_4.getProductos()) { precioU5_4 += p.getPrecio() * p.getCantidad(); pesoU5_4 += p.getPeso() * p.getCantidad(); }
        paqueteU5_4.setPrecio(precioU5_4); paqueteU5_4.setPeso(pesoU5_4);
        Envio envioU5_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU5_4, LocalDate.of(2025,6,22), EstadoEnvio.CANCELADO, repartidor1);
        usuario5.getListEnviosUsuario().add(envioU5_4);

        Paquete paqueteU5_5 = new Paquete(0,0,0);
        paqueteU5_5.getProductos().add(producto24);
        paqueteU5_5.getProductos().add(producto1);
        double precioU5_5=0, pesoU5_5=0;
        for (Producto p : paqueteU5_5.getProductos()) { precioU5_5 += p.getPrecio() * p.getCantidad(); pesoU5_5 += p.getPeso() * p.getCantidad(); }
        paqueteU5_5.setPrecio(precioU5_5); paqueteU5_5.setPeso(pesoU5_5);
        Envio envioU5_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU5_5, LocalDate.of(2025,6,26), EstadoEnvio.ENTREGADO, repartidor2);
        usuario5.getListEnviosUsuario().add(envioU5_5);


// =================== ENVIOS PARA usuario6 ===================
        Paquete paqueteU6_1 = new Paquete(0,0,0);
        paqueteU6_1.getProductos().add(producto3);
        paqueteU6_1.getProductos().add(producto5);
        double precioU6_1=0, pesoU6_1=0;
        for (Producto p : paqueteU6_1.getProductos()) { precioU6_1 += p.getPrecio() * p.getCantidad(); pesoU6_1 += p.getPeso() * p.getCantidad(); }
        paqueteU6_1.setPrecio(precioU6_1); paqueteU6_1.setPeso(pesoU6_1);
        Envio envioU6_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU6_1, LocalDate.of(2025,6,13), EstadoEnvio.ENTREGADO, repartidor3);
        usuario6.getListEnviosUsuario().add(envioU6_1);

        Paquete paqueteU6_2 = new Paquete(0,0,0);
        paqueteU6_2.getProductos().add(producto6);
        paqueteU6_2.getProductos().add(producto7);
        paqueteU6_2.getProductos().add(producto8);
        double precioU6_2=0, pesoU6_2=0;
        for (Producto p : paqueteU6_2.getProductos()) { precioU6_2 += p.getPrecio() * p.getCantidad(); pesoU6_2 += p.getPeso() * p.getCantidad(); }
        paqueteU6_2.setPrecio(precioU6_2); paqueteU6_2.setPeso(pesoU6_2);
        Envio envioU6_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU6_2, LocalDate.of(2025,6,16), EstadoEnvio.CANCELADO, repartidor4);
        usuario6.getListEnviosUsuario().add(envioU6_2);

        Paquete paqueteU6_3 = new Paquete(0,0,0);
        paqueteU6_3.getProductos().add(producto9);
        paqueteU6_3.getProductos().add(producto10);
        double precioU6_3=0, pesoU6_3=0;
        for (Producto p : paqueteU6_3.getProductos()) { precioU6_3 += p.getPrecio() * p.getCantidad(); pesoU6_3 += p.getPeso() * p.getCantidad(); }
        paqueteU6_3.setPrecio(precioU6_3); paqueteU6_3.setPeso(pesoU6_3);
        Envio envioU6_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU6_3, LocalDate.of(2025,6,19), EstadoEnvio.CANCELADO, repartidor);
        usuario6.getListEnviosUsuario().add(envioU6_3);

        Paquete paqueteU6_4 = new Paquete(0,0,0);
        paqueteU6_4.getProductos().add(producto11);
        paqueteU6_4.getProductos().add(producto12);
        double precioU6_4=0, pesoU6_4=0;
        for (Producto p : paqueteU6_4.getProductos()) { precioU6_4 += p.getPrecio() * p.getCantidad(); pesoU6_4 += p.getPeso() * p.getCantidad(); }
        paqueteU6_4.setPrecio(precioU6_4); paqueteU6_4.setPeso(pesoU6_4);
        Envio envioU6_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU6_4, LocalDate.of(2025,6,22), EstadoEnvio.ENTREGADO, repartidor1);
        usuario6.getListEnviosUsuario().add(envioU6_4);

        Paquete paqueteU6_5 = new Paquete(0,0,0);
        paqueteU6_5.getProductos().add(producto13);
        paqueteU6_5.getProductos().add(producto14);
        double precioU6_5=0, pesoU6_5=0;
        for (Producto p : paqueteU6_5.getProductos()) { precioU6_5 += p.getPrecio() * p.getCantidad(); pesoU6_5 += p.getPeso() * p.getCantidad(); }
        paqueteU6_5.setPrecio(precioU6_5); paqueteU6_5.setPeso(pesoU6_5);
        Envio envioU6_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU6_5, LocalDate.of(2025,6,26), EstadoEnvio.CANCELADO, repartidor2);
        usuario6.getListEnviosUsuario().add(envioU6_5);


// =================== ENVIOS PARA usuario7 ===================
        Paquete paqueteU7_1 = new Paquete(0,0,0);
        paqueteU7_1.getProductos().add(producto15);
        paqueteU7_1.getProductos().add(producto16);
        double precioU7_1=0, pesoU7_1=0;
        for (Producto p : paqueteU7_1.getProductos()) { precioU7_1 += p.getPrecio() * p.getCantidad(); pesoU7_1 += p.getPeso() * p.getCantidad(); }
        paqueteU7_1.setPrecio(precioU7_1); paqueteU7_1.setPeso(pesoU7_1);
        Envio envioU7_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU7_1, LocalDate.of(2025,6,14), EstadoEnvio.CANCELADO, repartidor3);
        usuario7.getListEnviosUsuario().add(envioU7_1);

        Paquete paqueteU7_2 = new Paquete(0,0,0);
        paqueteU7_2.getProductos().add(producto17);
        paqueteU7_2.getProductos().add(producto18);
        paqueteU7_2.getProductos().add(producto19);
        double precioU7_2=0, pesoU7_2=0;
        for (Producto p : paqueteU7_2.getProductos()) { precioU7_2 += p.getPrecio() * p.getCantidad(); pesoU7_2 += p.getPeso() * p.getCantidad(); }
        paqueteU7_2.setPrecio(precioU7_2); paqueteU7_2.setPeso(pesoU7_2);
        Envio envioU7_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU7_2, LocalDate.of(2025,6,17), EstadoEnvio.CANCELADO, repartidor4);
        usuario7.getListEnviosUsuario().add(envioU7_2);

        Paquete paqueteU7_3 = new Paquete(0,0,0);
        paqueteU7_3.getProductos().add(producto20);
        paqueteU7_3.getProductos().add(producto21);
        double precioU7_3=0, pesoU7_3=0;
        for (Producto p : paqueteU7_3.getProductos()) { precioU7_3 += p.getPrecio() * p.getCantidad(); pesoU7_3 += p.getPeso() * p.getCantidad(); }
        paqueteU7_3.setPrecio(precioU7_3); paqueteU7_3.setPeso(pesoU7_3);
        Envio envioU7_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU7_3, LocalDate.of(2025,6,20), EstadoEnvio.ENTREGADO, repartidor);
        usuario7.getListEnviosUsuario().add(envioU7_3);

        Paquete paqueteU7_4 = new Paquete(0,0,0);
        paqueteU7_4.getProductos().add(producto22);
        paqueteU7_4.getProductos().add(producto23);
        double precioU7_4=0, pesoU7_4=0;
        for (Producto p : paqueteU7_4.getProductos()) { precioU7_4 += p.getPrecio() * p.getCantidad(); pesoU7_4 += p.getPeso() * p.getCantidad(); }
        paqueteU7_4.setPrecio(precioU7_4); paqueteU7_4.setPeso(pesoU7_4);
        Envio envioU7_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU7_4, LocalDate.of(2025,6,23), EstadoEnvio.ENTREGADO, repartidor1);
        usuario7.getListEnviosUsuario().add(envioU7_4);

        Paquete paqueteU7_5 = new Paquete(0,0,0);
        paqueteU7_5.getProductos().add(producto24);
        paqueteU7_5.getProductos().add(producto1);
        double precioU7_5=0, pesoU7_5=0;
        for (Producto p : paqueteU7_5.getProductos()) { precioU7_5 += p.getPrecio() * p.getCantidad(); pesoU7_5 += p.getPeso() * p.getCantidad(); }
        paqueteU7_5.setPrecio(precioU7_5); paqueteU7_5.setPeso(pesoU7_5);
        Envio envioU7_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU7_5, LocalDate.of(2025,6,27), EstadoEnvio.CANCELADO, repartidor2);
        usuario7.getListEnviosUsuario().add(envioU7_5);


// =================== ENVIOS PARA usuario8 ===================
        Paquete paqueteU8_1 = new Paquete(0,0,0);
        paqueteU8_1.getProductos().add(producto2);
        paqueteU8_1.getProductos().add(producto9);
        double precioU8_1=0, pesoU8_1=0;
        for (Producto p : paqueteU8_1.getProductos()) { precioU8_1 += p.getPrecio() * p.getCantidad(); pesoU8_1 += p.getPeso() * p.getCantidad(); }
        paqueteU8_1.setPrecio(precioU8_1); paqueteU8_1.setPeso(pesoU8_1);
        Envio envioU8_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU8_1, LocalDate.of(2025,6,12), EstadoEnvio.CANCELADO, repartidor3);
        usuario8.getListEnviosUsuario().add(envioU8_1);

        Paquete paqueteU8_2 = new Paquete(0,0,0);
        paqueteU8_2.getProductos().add(producto3);
        paqueteU8_2.getProductos().add(producto11);
        paqueteU8_2.getProductos().add(producto5);
        double precioU8_2=0, pesoU8_2=0;
        for (Producto p : paqueteU8_2.getProductos()) { precioU8_2 += p.getPrecio() * p.getCantidad(); pesoU8_2 += p.getPeso() * p.getCantidad(); }
        paqueteU8_2.setPrecio(precioU8_2); paqueteU8_2.setPeso(pesoU8_2);
        Envio envioU8_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU8_2, LocalDate.of(2025,6,15), EstadoEnvio.CANCELADO, repartidor4);
        usuario8.getListEnviosUsuario().add(envioU8_2);

        Paquete paqueteU8_3 = new Paquete(0,0,0);
        paqueteU8_3.getProductos().add(producto6);
        paqueteU8_3.getProductos().add(producto7);
        double precioU8_3=0, pesoU8_3=0;
        for (Producto p : paqueteU8_3.getProductos()) { precioU8_3 += p.getPrecio() * p.getCantidad(); pesoU8_3 += p.getPeso() * p.getCantidad(); }
        paqueteU8_3.setPrecio(precioU8_3); paqueteU8_3.setPeso(pesoU8_3);
        Envio envioU8_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU8_3, LocalDate.of(2025,6,18), EstadoEnvio.ENTREGADO, repartidor);
        usuario8.getListEnviosUsuario().add(envioU8_3);

        Paquete paqueteU8_4 = new Paquete(0,0,0);
        paqueteU8_4.getProductos().add(producto8);
        paqueteU8_4.getProductos().add(producto10);
        double precioU8_4=0, pesoU8_4=0;
        for (Producto p : paqueteU8_4.getProductos()) { precioU8_4 += p.getPrecio() * p.getCantidad(); pesoU8_4 += p.getPeso() * p.getCantidad(); }
        paqueteU8_4.setPrecio(precioU8_4); paqueteU8_4.setPeso(pesoU8_4);
        Envio envioU8_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU8_4, LocalDate.of(2025,6,21), EstadoEnvio.ENTREGADO, repartidor1);
        usuario8.getListEnviosUsuario().add(envioU8_4);

        Paquete paqueteU8_5 = new Paquete(0,0,0);
        paqueteU8_5.getProductos().add(producto12);
        paqueteU8_5.getProductos().add(producto13);
        double precioU8_5=0, pesoU8_5=0;
        for (Producto p : paqueteU8_5.getProductos()) { precioU8_5 += p.getPrecio() * p.getCantidad(); pesoU8_5 += p.getPeso() * p.getCantidad(); }
        paqueteU8_5.setPrecio(precioU8_5); paqueteU8_5.setPeso(pesoU8_5);
        Envio envioU8_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU8_5, LocalDate.of(2025,6,25), EstadoEnvio.CANCELADO, repartidor2);
        usuario8.getListEnviosUsuario().add(envioU8_5);


// =================== ENVIOS PARA usuario9 ===================
        Paquete paqueteU9_1 = new Paquete(0,0,0);
        paqueteU9_1.getProductos().add(producto14);
        paqueteU9_1.getProductos().add(producto15);
        double precioU9_1=0, pesoU9_1=0;
        for (Producto p : paqueteU9_1.getProductos()) { precioU9_1 += p.getPrecio() * p.getCantidad(); pesoU9_1 += p.getPeso() * p.getCantidad(); }
        paqueteU9_1.setPrecio(precioU9_1); paqueteU9_1.setPeso(pesoU9_1);
        Envio envioU9_1 = new Envio(idEnvio++, direccion3, direccion4, paqueteU9_1, LocalDate.of(2025,6,13), EstadoEnvio.CANCELADO, repartidor3);
        usuario9.getListEnviosUsuario().add(envioU9_1);

        Paquete paqueteU9_2 = new Paquete(0,0,0);
        paqueteU9_2.getProductos().add(producto16);
        paqueteU9_2.getProductos().add(producto17);
        paqueteU9_2.getProductos().add(producto18);
        double precioU9_2=0, pesoU9_2=0;
        for (Producto p : paqueteU9_2.getProductos()) { precioU9_2 += p.getPrecio() * p.getCantidad(); pesoU9_2 += p.getPeso() * p.getCantidad(); }
        paqueteU9_2.setPrecio(precioU9_2); paqueteU9_2.setPeso(pesoU9_2);
        Envio envioU9_2 = new Envio(idEnvio++, direccion4, direccion, paqueteU9_2, LocalDate.of(2025,6,16), EstadoEnvio.CANCELADO, repartidor4);
        usuario9.getListEnviosUsuario().add(envioU9_2);

        Paquete paqueteU9_3 = new Paquete(0,0,0);
        paqueteU9_3.getProductos().add(producto19);
        paqueteU9_3.getProductos().add(producto20);
        double precioU9_3=0, pesoU9_3=0;
        for (Producto p : paqueteU9_3.getProductos()) { precioU9_3 += p.getPrecio() * p.getCantidad(); pesoU9_3 += p.getPeso() * p.getCantidad(); }
        paqueteU9_3.setPrecio(precioU9_3); paqueteU9_3.setPeso(pesoU9_3);
        Envio envioU9_3 = new Envio(idEnvio++, direccion, direccion1, paqueteU9_3, LocalDate.of(2025,6,19), EstadoEnvio.ENTREGADO, repartidor);
        usuario9.getListEnviosUsuario().add(envioU9_3);

        Paquete paqueteU9_4 = new Paquete(0,0,0);
        paqueteU9_4.getProductos().add(producto21);
        paqueteU9_4.getProductos().add(producto22);
        double precioU9_4=0, pesoU9_4=0;
        for (Producto p : paqueteU9_4.getProductos()) { precioU9_4 += p.getPrecio() * p.getCantidad(); pesoU9_4 += p.getPeso() * p.getCantidad(); }
        paqueteU9_4.setPrecio(precioU9_4); paqueteU9_4.setPeso(pesoU9_4);
        Envio envioU9_4 = new Envio(idEnvio++, direccion1, direccion2, paqueteU9_4, LocalDate.of(2025,6,22), EstadoEnvio.ENTREGADO, repartidor1);
        usuario9.getListEnviosUsuario().add(envioU9_4);

        Paquete paqueteU9_5 = new Paquete(0,0,0);
        paqueteU9_5.getProductos().add(producto23);
        paqueteU9_5.getProductos().add(producto24);
        double precioU9_5=0, pesoU9_5=0;
        for (Producto p : paqueteU9_5.getProductos()) { precioU9_5 += p.getPrecio() * p.getCantidad(); pesoU9_5 += p.getPeso() * p.getCantidad(); }
        paqueteU9_5.setPrecio(precioU9_5); paqueteU9_5.setPeso(pesoU9_5);
        Envio envioU9_5 = new Envio(idEnvio++, direccion2, direccion3, paqueteU9_5, LocalDate.of(2025,6,26), EstadoEnvio.CANCELADO, repartidor2);
        usuario9.getListEnviosUsuario().add(envioU9_5);


    }
}
