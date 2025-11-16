package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Direccion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaUsuarioConfiguracionViewController {
    App app;
    @FXML
    TextField tfNombreDP;
    @FXML
    TextField tfCorreoDP;
    @FXML
    TextField tfConDP;
    @FXML
    TableView<Direccion> tbDirecciones;
    @FXML
    TableColumn<Direccion,String> colDireccion;
    @FXML
    TableColumn<Direccion,String> colLon;
    @FXML
    TableColumn<Direccion,String> colLat;
    @FXML
    TableColumn<Direccion,String> colZona;
    @FXML
    Button btAr;
    @FXML
    Button btModi;



    public void setApp(App app) {
        this.app = app;
    }

    public void agregarDireccion(){
        app.openPantallaDireccionUsuario((Stage) this.app.stage.getScene().getWindow());
        app.cerrarMod(btAr);


    }

    public void initialize(){
        inicializarData();



    }
    public void inicializarData() {
        if (app != null) {
            tfNombreDP.setText(app.usuarioSesion.getNombreCompleto());
            tfCorreoDP.setText(app.usuarioSesion.getCorreo());
            tfConDP.setText(app.usuarioSesion.getContrasenia());

            tbDirecciones.setItems(FXCollections.observableList(app.usuarioSesion.getListDireccionesUsuario()));

            colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
            colLon.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLongitud())));
            colLat.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLatitud())));
            colZona.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getZonaCobertura())));

        }
    }
    public void eliminarDireccion(){
        Direccion dir = tbDirecciones.getSelectionModel().getSelectedItem();
        if (dir != null){
            if (app.usuarioSesion.getListDireccionesUsuario().size() >1) {
                app.usuarioSesion.getListDireccionesUsuario().remove(dir);
                tbDirecciones.refresh();
            }
            else {
                DialogUtils.mostrarError("No se puede eliminar la dirección.");
            }
        }
        else {
            DialogUtils.mostrarError("Seleccione una direccion");
        }


    }

    public void modificarDatos(){
        String nombre = tfNombreDP.getText();
        String correo = tfCorreoDP.getText();
        String contrasenia = tfConDP.getText();

        if (nombre.isEmpty() || correo.isEmpty() || contrasenia.isEmpty()){
            DialogUtils.mostrarError("Hay Campos Vacios");
        }
        if (app.usuarioSesion.getNombreCompleto().equals(nombre)&&app.usuarioSesion.getCorreo().equals(correo)&&app.usuarioSesion.getContrasenia().equals(contrasenia)){
            DialogUtils.mostrarError("No cambiaste nada campeón.");
            return;
        }
        app.usuarioSesion.setNombreCompleto(nombre);
        app.usuarioSesion.setCorreo(correo);
        app.usuarioSesion.setContrasenia(contrasenia);

        DialogUtils.mostrarMensaje("Datos modificados correctamente");

        app.openPantallaUsuario();
    }

    public void limpiarSeleccionDirecciones() {
        tbDirecciones.getSelectionModel().clearSelection();
    }
}
