package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.model.Producto;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    TableView<Producto> tbMiCatalogo;
    @FXML
    TableColumn<Producto,String> colMCNombre;
    @FXML
    TableColumn<Producto,String> colMCDescripcion;
    @FXML
    TableColumn<Producto,String> colMCPrecio;
    @FXML
    TableColumn<Producto,String> colMCCantidad;

    @FXML
    TableView<Producto> tbCarrito;
    @FXML
    TableColumn<Producto,String> colCNombre;
    @FXML
    TableColumn<Producto,String> colCDescripcion;
    @FXML
    TableColumn<Producto,String> colCCantidad;
    @FXML
    TableColumn<Producto,String> colCSubTotal;

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

        colMCNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colMCDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colMCCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        colMCPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));

        colCNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colCDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colCCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        colCSubTotal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio()*cellData.getValue().getCantidad())));

       if (this.app != null) {
           ObservableList<Producto> obs = FXCollections.observableArrayList();
           obs.setAll(app.listGlobalProductos);
           obs.removeAll(app.usuarioSesion.getListProductosUsuario());
           tbCatalogoDisponible.setItems(obs);
           tbMiCatalogo.setItems(FXCollections.observableList(usuarioSesion.getListProductosUsuario()));
           tbCarrito.setItems(FXCollections.observableList(usuarioSesion.getListCarritosUsuario()));

       }



    }

}
