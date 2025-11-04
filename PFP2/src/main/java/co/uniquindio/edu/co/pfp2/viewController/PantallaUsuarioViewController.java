package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.model.Producto;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PantallaUsuarioViewController {
    App app;
    Usuario usuarioSesion;
    @FXML
    TableView<Producto> tbCatalogoDisponible;
    @FXML
    TableColumn<Producto,String> tbCDNombre;
    @FXML
    TableColumn<Producto,String> tbCDDescripcion;
    @FXML
    TableColumn<Producto,String> tbCDPrecio;
    @FXML
    TableColumn<Producto,String> tbCDCantidad;


    public Text gettUsuario() {
        return tUsuario;
    }

    public void settUsuario(Text tUsuario) {
        this.tUsuario = tUsuario;
    }

    @FXML
    Text tUsuario;

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void openPantallaUsuarioConfiguracion(){
        this.app.openPantallaUsuarioConfiguracion();
        System.out.println("SI");
    }

    public void initialize(){
        tbCDNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbCDDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        tbCDCantidad.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad()))
        );
        tbCDPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
       if (this.app != null) {
           tbCatalogoDisponible.setItems(app.listGlobalProductos);
           System.out.println(app.listGlobalProductos.size());
       }

    }

}
