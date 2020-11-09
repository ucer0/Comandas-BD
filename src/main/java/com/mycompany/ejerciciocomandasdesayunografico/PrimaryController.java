package com.mycompany.ejerciciocomandasdesayunografico;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelos.Pedidos;

/**
 * @author Pablo Martínez y Santiago Ucero. Cesur 2ªDAM, Diseño de Interfaces
 */
public class PrimaryController implements Initializable {

    private Connection con;
    private Statement stat;
    private ObservableList<Pedidos> pedidosList;
    private Pedidos pedidoSeleccionado;
    private Integer seleccion;
    
    private int id = 1; // Contador de id de cada cliente
    
    // LocalDate para coger la hora a la que se ha hecho el pedido 
    LocalDate currentDate = LocalDate.now();
    
    private App app = new App();
    private Functions f1 = new Functions();

    @FXML
    private MenuItem menuFileClose;
    @FXML
    private TableView<Pedidos> tablaId;
    @FXML
    private TableColumn<Pedidos, Integer> idColumn;
    @FXML
    private TableColumn<Pedidos, String> bebidaColumn;
    @FXML
    private TableColumn<Pedidos, String> comidaColumn;
    @FXML
    private TableColumn<Pedidos, String> clienteColumn;
    @FXML
    private TableColumn<Pedidos, Double> precioColumn;
    @FXML
    private TableColumn<Pedidos, LocalDate> fechaColumn;
    @FXML
    private TableColumn<Pedidos, Boolean> entregaColumn1;
    @FXML
    private MenuItem fullscreenItem;
    @FXML
    private TextField clientName;
    @FXML
    private ChoiceBox<String> comidaBox;
    @FXML
    private ChoiceBox<String> bebidaBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private CheckBox entregadoCheck;
    @FXML
    private Label elementoLabel;
    @FXML
    private Button controlPanelButton;
    
    private void clearElement(){
        
        clientName.setText("");
        bebidaBox.getSelectionModel().select("Nada");
        comidaBox.getSelectionModel().select("Nada");
        entregadoCheck.setSelected(false);
        
    }
    
    private void editData(){
        
        elementoLabel.setText("Editar Elemento");
        
        f1.resetPrecioPedido();
        
        f1.setBebida(bebidaBox.getValue());
        f1.setComida(comidaBox.getValue());

        pedidoSeleccionado = new Pedidos(
            pedidoSeleccionado.getId(), bebidaBox.getValue(), comidaBox.getValue(), clientName.getText(), 
            f1.getPrecioPedido(), currentDate, entregadoCheck.isSelected()
        ); 
   
        pedidosList.set(seleccion, pedidoSeleccionado);
        tablaId.refresh(); // Actualiza la tabla automáticamente
        tablaId.getSelectionModel().select(null); // Deselecciona la fila que se acaba de editar
        elementoLabel.setText("Nuevo Elemento");
    }
    
    // Guarda los pedidos nuevos / Actualiza los pedidos seleccionados(con editData())
    @FXML
    private void save(ActionEvent event) throws IOException, SQLException {
       
        f1.setBebida(bebidaBox.getValue());
        f1.setComida(comidaBox.getValue());
        
        Pedidos p = new Pedidos(
            id, bebidaBox.getValue(), comidaBox.getValue(), clientName.getText(), 
            f1.getPrecioPedido(), currentDate, entregadoCheck.isSelected()
        );
        
        if(pedidoSeleccionado == null){
            if(!pedidosList.contains(p)){
                pedidosList.add(p);
                id++;
            } 
        } else {
            editData();
        }
        
        f1.resetPrecioPedido();
        clearElement();
    }
    
    // Pone en pantalla los datos editables del pedido seleccionado
    @FXML
    private void seleccionar(MouseEvent event) {
        
        pedidoSeleccionado = tablaId.getSelectionModel().getSelectedItem();
        seleccion = tablaId.getSelectionModel().getSelectedIndex();
        
        clientName.setText(pedidoSeleccionado.getCliente());
        bebidaBox.getSelectionModel().select(pedidoSeleccionado.getBebida());
        comidaBox.getSelectionModel().select(pedidoSeleccionado.getComida());
        entregadoCheck.setSelected(pedidoSeleccionado.getEntregado());

    }
    
    @FXML
    private void delete(ActionEvent event) {

        Pedidos p = tablaId.getSelectionModel().getSelectedItem();
        pedidosList.remove(p);
    }
    
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void goFullscreen(ActionEvent event) {
        app.fullscreen();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        pedidosList = FXCollections.observableArrayList();
        tablaId.setItems(pedidosList);
        
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory("cliente"));
        bebidaColumn.setCellValueFactory(new PropertyValueFactory("bebida"));
        comidaColumn.setCellValueFactory(new PropertyValueFactory("comida"));
        precioColumn.setCellValueFactory(new PropertyValueFactory("precio"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory("fecha"));
        entregaColumn1.setCellValueFactory(new PropertyValueFactory("entregado"));
        
        // Lista completa de bebidas
        bebidaBox.getItems().addAll(
                "Nada", "Café Solo - 0.8€", "Café con Leche - 1€",
                "Refresco - 1.5€", "Colacao - 1.5€");
        bebidaBox.getSelectionModel().select("Nada");
        
        // Lista completa de comidas
        comidaBox.getItems().addAll(
                "Nada", "Pitufo Mixto - 1.5€", "Pitufo Bacon - 1.7€", "Pitufo Lomo - 2€",
                "Bocata Paté - 2.5€", "Bocata Bacon - 3€", "Bocata Chorizo - 3.5€");
        comidaBox.getSelectionModel().select("Nada");
    }    

    @FXML
    private void openControlPanel(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource("secondary.fxml")); 
        Parent root = fxmlloader.load();

        SecondaryController controlPanel = fxmlloader.getController();
        System.out.print(controlPanel);
        
        controlPanel.setData(tablaId.getItems().size(), f1.getPrecioTotal());
        
        Scene scene = new Scene(root);
        Stage newWindow = new Stage();
        newWindow.setTitle("Panel de Control");
        newWindow.setResizable(false);
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }
}
