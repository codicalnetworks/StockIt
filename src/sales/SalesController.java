package sales;

import dbUtils.SqliteConnection;
import employee.EmployeeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stock.StockData;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalesController implements Initializable{


    @FXML
    private TextField itemName;

    @FXML
    private TextField itemPrice;

    @FXML
    private TextField itemQuantity;

    @FXML
    private ComboBox itemCombo;

    @FXML
    private TextField customerName;

    @FXML
    private TextField noOfItems;

    @FXML
    private TextField totalPrice;

    @FXML
    private TextField amountPaid;

    @FXML
    private ComboBox paymentCombo;

    @FXML
    private TextField discount;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnPrint;

    @FXML
    private TextField payable;

    @FXML
    private TextField payment;

    @FXML
    private ComboBox invoiceCombo;

    @FXML
    private Button btnPay;


    private ObservableList<EmployeeData> data;

    private ObservableList<StockData> stockData;

    private String sqlQuery = "SELECT * FROM stock";
    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    ResultSet resultSet;
    ResultSet rs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        payable.setText(randomString(5));

        try {

            Connection connection = SqliteConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            resultSet = connection.createStatement().executeQuery(sqlQuery);

            while (resultSet.next()) {
                itemCombo.getItems().addAll(resultSet.getString("name"));
            }

        } catch (SQLException e) {

        }


    }

    public void loadDetails(ActionEvent event) {
        String selectedItem = itemCombo.getSelectionModel().getSelectedItem().toString();
        String query = "SELECT * FROM stock" ;

        Connection connect = SqliteConnection.getConnection();
        this.data = FXCollections.observableArrayList();

        try {
            rs = connect.createStatement().executeQuery(query);

            itemName.setText(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void goBack(ActionEvent event) {

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

    public static String randomString(int count) {
        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {
            int character = (int) (Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }

        return builder.toString();
    }
}
