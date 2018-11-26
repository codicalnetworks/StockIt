package stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import dbUtils.SqliteConnection;

public class StockController implements Initializable {
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemQuantity;
    @FXML
    private TextField itemPrice;
    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private TableView<StockData> stockTable;
    @FXML
    private TableColumn<StockData, String> colAvailableItems;
    @FXML
    private TableColumn<StockData, Integer> colQuantity;
    @FXML
    private TableColumn<StockData, Double> colPrice;


    private ObservableList<StockData> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    public void goBack(ActionEvent event) {
        // This methods returns the current scene to the previous scene
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Pane root = null;
        try {
            root = loader.load(getClass().getResource("/dashboard/dashboard.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void addStock(ActionEvent event) {
        // This method adds new item into the stock table
        String insertQuery = "INSERT INTO stock (name,quantity,price) VALUES (?,?,?)";
        Connection connection = SqliteConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            if (itemName.getText().isEmpty() || itemQuantity.getText().isEmpty() || itemPrice.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");

                alert.setHeaderText(null);
                alert.setContentText("Fields cannot be empty");

                alert.showAndWait();
            } else {
                statement.setString(1, itemName.getText());
                statement.setInt(2, Integer.valueOf(itemQuantity.getText()));
                statement.setInt(3, Integer.valueOf(itemPrice.getText()));

                statement.executeUpdate();
                connection.close();

                Alert messageAlert = new Alert(Alert.AlertType.INFORMATION);
                messageAlert.setTitle("Stock");

                messageAlert.setHeaderText(null);
                messageAlert.setContentText("Item successfully added to stock");

                messageAlert.showAndWait();
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        stockTable.refresh();

    }

    public void loadData() {
        // This loads the data into the stock table
        String sqlQuery = "SELECT * FROM stock";

        try {
            Connection connection  = SqliteConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);

            while (resultSet.next()) {
                data.add(new StockData(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3)));
            }

            this.colAvailableItems.setCellValueFactory(new PropertyValueFactory<StockData, String>("name"));
            this.colQuantity.setCellValueFactory(new PropertyValueFactory<StockData, Integer>("quantity"));
            this.colPrice.setCellValueFactory(new PropertyValueFactory<StockData, Double>("price"));

            this.stockTable.setItems(null);
            this.stockTable.setItems(this.data);

        } catch (SQLException e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void refresh(ActionEvent event) {
        //Refresh the table to load new datas
        String sqlQuery = "SELECT * FROM stock";
        try {
            Connection connection  = SqliteConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);

            while (resultSet.next()) {
                data.add(new StockData(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3)));
            }

            this.colAvailableItems.setCellValueFactory(new PropertyValueFactory<StockData, String>("name"));
            this.colQuantity.setCellValueFactory(new PropertyValueFactory<StockData, Integer>("quantity"));
            this.colPrice.setCellValueFactory(new PropertyValueFactory<StockData, Double>("price"));

            this.stockTable.setItems(null);
            this.stockTable.setItems(this.data);

        } catch (SQLException e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void deleteItem(ActionEvent event) {
        //Remove item from database and table
        StockData selectedItem = stockTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            Alert removeAlert = new Alert(Alert.AlertType.CONFIRMATION);
            removeAlert.setTitle("Remove item");
            removeAlert.setHeaderText("Are you sure you want to remove this product?");
            removeAlert.setContentText(selectedItem.getName());

            Optional<ButtonType> option = removeAlert.showAndWait();

            if (option.get() == null) {
                removeAlert.hide();
            } else if (option.get() == ButtonType.OK) {
                Connection connection = SqliteConnection.getConnection();
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM stock where name = ?");
                    statement.setString(1, selectedItem.getName());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stockTable.getItems().remove(selectedItem);
            } else if (option.get() == ButtonType.CANCEL) {
                removeAlert.hide();
            }

        } else {
            // Error alert for stockTable
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Select item you want to delete from the table");

            alert.showAndWait();
    }




    }

    public void clearEntry(ActionEvent event) {
        // This method clears all entries in the textfields
        this.itemName.setText("");
        this.itemPrice.setText("");
        this.itemQuantity.setText("");
    }
}
