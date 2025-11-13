package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Direccion;
import co.uniquindio.edu.co.pfp2.model.ZonaCobertura;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class PantallaDireccionUsuarioViewController {

    App app;


    @FXML
    TextField tfDireccion;
    @FXML
    TextField tfLatitud;
    @FXML
    TextField tfLongitud;
    @FXML
    ComboBox<ZonaCobertura> cbZona;
    @FXML
    Button btDir;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
    public void agregarDireccion() {
        String descripcion = tfDireccion.getText();
        String latTxt = tfLatitud.getText();
        String lonTxt = tfLongitud.getText();
        ZonaCobertura zona = cbZona.getValue();


        if (descripcion.isEmpty() || latTxt.isEmpty() || lonTxt.isEmpty()) {
            DialogUtils.mostrarError("Todos los campos son obligatorios.");
            return;
        }


        if (zona == null) {
            DialogUtils.mostrarError("Debe seleccionar una zona de cobertura.");
            return;
        }

        try {
            double latitud = Double.parseDouble(latTxt);
            double longitud = Double.parseDouble(lonTxt);

            Direccion direccion = new Direccion(app.idDireccion+1, descripcion, latitud, longitud, zona);
            app.idDireccion += 1;

            app.usuarioSesion.getListDireccionesUsuario().add(direccion);
            DialogUtils.mostrarMensaje("Dirección agregada correctamente.");

            tfDireccion.clear();
            tfLatitud.clear();
            tfLongitud.clear();
            cbZona.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("Latitud y longitud deben ser números válidos.");
        }


        app.cerrarMod(btDir);
    }


    public void initialize(){

        cbZona.getItems().setAll(ZonaCobertura.values());
    }
}
