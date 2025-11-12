package co.uniquindio.edu.co.pfp2.viewController;

import co.uniquindio.edu.co.pfp2.App;
import co.uniquindio.edu.co.pfp2.Extra.DialogUtils;
import co.uniquindio.edu.co.pfp2.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @FXML
    TextField txNombreProducto;
    @FXML
    TextArea txDescripcionProducto;
    @FXML
    TextField txPrecioProducto;
    @FXML
    TextField txCantidadProducto;
    @FXML
    TextField txPesoProducto;

    @FXML
    Button btAM;

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

                listarTablas();
                tbCatalogoDisponible.refresh();
                tbCatalogoDisponible.getSelectionModel().select(null);
                cbUnidadesPedir.setValue(null);
                DialogUtils.mostrarMensaje("Producto agregado al carrito.");
            }
        }
    }
    public void agregarProducto() {
        String nombre = txNombreProducto.getText();
        String descripcion = txDescripcionProducto.getText();
        String precioTxt = txPrecioProducto.getText();
        String cantidadTxt = txCantidadProducto.getText();
        String pesoTxt = txPesoProducto.getText();


        if (nombre.isEmpty() || descripcion.isEmpty() ||
                precioTxt.isEmpty() || cantidadTxt.isEmpty() || pesoTxt.isEmpty()) {
            DialogUtils.mostrarError("Hay campos vacíos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioTxt);
            int cantidad = Integer.parseInt(cantidadTxt);
            double peso = Double.parseDouble(pesoTxt);

            if (precio <= 0 || cantidad < 0 || peso < 0) {
                DialogUtils.mostrarError("Los valores numéricos deben ser mayores que cero.");
                return;
            }

            Producto nuevo = new Producto(app.idProducto,nombre, descripcion, precio, cantidad, peso);
            app.idProducto ++;


            app.usuarioSesion.getListProductosUsuario().add(nuevo);
            app.listGlobalProductos.add(nuevo);

            tbMiCatalogo.refresh();
            tbCatalogoDisponible.refresh();

            DialogUtils.mostrarMensaje("Producto agregado correctamente.");

        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("Precio, cantidad o peso inválidos.");
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
    public void accionAgregarModificar() {
        Producto seleccionado = tbMiCatalogo.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            modificarProducto();
        } else {
            agregarProducto();
        }
    }

    public void modificarProducto() {
        Producto seleccionado = tbMiCatalogo.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            DialogUtils.mostrarError("Debe seleccionar un producto para modificar.");
            return;
        }

        String nombre = txNombreProducto.getText();
        String descripcion = txDescripcionProducto.getText();
        String precioTxt = txPrecioProducto.getText();
        String cantidadTxt = txCantidadProducto.getText();
        String pesoTxt = txPesoProducto.getText();

        if (nombre.isEmpty() || descripcion.isEmpty() ||
                precioTxt.isEmpty() || cantidadTxt.isEmpty() || pesoTxt.isEmpty()) {
            DialogUtils.mostrarError("Hay campos vacíos.");
            tbMiCatalogo.getSelectionModel().clearSelection();
            return;
        }

        try {
            double precio = Double.parseDouble(precioTxt);
            int cantidad = Integer.parseInt(cantidadTxt);
            double peso = Double.parseDouble(pesoTxt);

            if (precio <= 0 || cantidad < 0 || peso < 0) {
                DialogUtils.mostrarError("Los valores numéricos deben ser mayores que cero.");
                tbMiCatalogo.getSelectionModel().clearSelection();
                return;
            }

            seleccionado.setNombre(nombre);
            seleccionado.setDescripcion(descripcion);
            seleccionado.setPrecio(precio);
            seleccionado.setCantidad(cantidad);
            seleccionado.setPeso(peso);

            tbMiCatalogo.refresh();
            tbCatalogoDisponible.refresh();

            DialogUtils.mostrarMensaje("Producto modificado correctamente.");
            tbMiCatalogo.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            DialogUtils.mostrarError("Precio, cantidad o peso inválidos.");
            tbMiCatalogo.getSelectionModel().clearSelection();
        }
    }

    public void hacerPedido() {
        if (usuarioSesion.getListCarritosUsuario().isEmpty()) {
            DialogUtils.mostrarError("Debe tener por lo menos un producto para realizar el pedido.");
            return;
        }

        double precio = 0;
        double peso = 0;


        for (Producto producto : usuarioSesion.getListCarritosUsuario()) {
            precio += producto.getPrecio();
            peso += producto.getPeso();
        }


        Paquete paquete = new Paquete(precio, peso, peso);
        paquete.setProductos(new ArrayList<>(usuarioSesion.getListCarritosUsuario()));

        usuarioSesion.getListCarritosUsuario().clear();
        listarTablas();

        List<Repartidor> repartidores = app.listGlobalRepartidores.stream()
                .filter(r -> r.getDisponibilidadRepartidor() == DisponibilidadRepartidor.DISPONIBLE)
                .toList();

        if (repartidores.isEmpty()) {
            DialogUtils.mostrarError("No hay repartidores disponibles para realizar el pedido.");
            return;
        }

        Random random = new Random();
        Repartidor repartidor = repartidores.get(random.nextInt(repartidores.size()));
        repartidor.setDisponibilidadRepartidor(DisponibilidadRepartidor.EN_RUTA);

        DialogUtils.mostrarMensaje("Pedido realizado con éxito. Repartidor asignado: " + repartidor.getNombreCompleto());
        app.openPantallaUsuarioPago();
    }


    public void initialize(){

        tbMiCatalogo.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                btAM.setText("Modificar Producto");
            } else {
                btAM.setText("Añadir Producto");
            }
        });

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
        tbMiCatalogo.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txNombreProducto.setText(newSel.getNombre());
                txDescripcionProducto.setText(newSel.getDescripcion());
                txPrecioProducto.setText(String.valueOf(newSel.getPrecio()));
                txCantidadProducto.setText(String.valueOf(newSel.getCantidad()));
                txPesoProducto.setText(String.valueOf(newSel.getPeso()));
            } else {
                txNombreProducto.clear();
                txDescripcionProducto.clear();
                txPrecioProducto.clear();
                txCantidadProducto.clear();
                txPesoProducto.clear();
            }
        });





    }

}
