package co.uniquindio.edu.co.pfp2;

import co.uniquindio.edu.co.pfp2.viewController.PantallaBienvenidaViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Mercado Libre");
        openPantallaBienvenida();

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
}
