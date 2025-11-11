package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.Producto;
import co.uniquindio.edu.co.pfp2.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class PantallaUsuarioViewController {
    App app;
    Usuario usuarioSesion;
    ObservableList<Integer> cbLista = FXCollections.observableArrayList();
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
    @FXML
    ComboBox<Integer> cbUnidadesPedir;


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
    public void cerrarSesion(){
        app.openPantallaBienvenida();
    }

    public void pedirProducto(){
        Producto p = tbCatalogoDisponible.getSelectionModel().getSelectedItem();
        Integer cantidad = cbUnidadesPedir.getValue();

        if (p == null){
            DialogUtils.mostrarError("Debe seleccionar un producto.");
            return;
        }
        if (cbUnidadesPedir.getValue() == null){
            DialogUtils.mostrarError("Debe seleccionar una cantidad.");
            return;
        }
        for (Producto pr : app.listGlobalProductos){
            if (pr == p){
                Producto nuevo = new Producto(pr.getIdProducto(),pr.getNombre(),pr.getDescripcion(),pr.getPrecio(),cantidad,pr.getPeso());
                pr.setCantidad(pr.getCantidad() - cantidad);
                app.usuarioSesion.getListCarritosUsuario().add(nuevo);
                zz
                listarTablas();
                tbCatalogoDisponible.refresh();
                tbCatalogoDisponible.getSelectionModel().select(null);
                cbUnidadesPedir.setValue(null);
            }
        }
    }


    public void listarTablas() {
        if (this.app != null) {
            ObservableList<Producto> obs = FXCollections.observableArrayList();
            obs.setAll(app.listGlobalProductos);
            obs.removeAll(app.usuarioSesion.getListProductosUsuario());


            FilteredList<Producto> filtrados = new FilteredList<>(obs);
            filtrados.setPredicate(p -> p.getCantidad() > 0);

            tbCatalogoDisponible.setItems(filtrados);

            tbMiCatalogo.setItems(FXCollections.observableList(usuarioSesion.getListProductosUsuario()));
            tbCarrito.setItems(FXCollections.observableList(usuarioSesion.getListCarritosUsuario()));
        }
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
        listarTablas();

        tbCatalogoDisponible.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            cbUnidadesPedir.getItems().clear();
            cbUnidadesPedir.setValue(null);

            if (newSel != null) {
                ObservableList<Integer> lista = FXCollections.observableArrayList();
                for (int i = 1; i <= newSel.getCantidad(); i++) {
                    lista.add(i);
                }
                cbUnidadesPedir.setItems(lista);
            }
        });




    }

}
