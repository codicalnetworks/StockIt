package employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import dbUtils.SqliteConnection;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {

    @FXML
    private TextField employeeName;

    @FXML
    private TextField employeePhone;

    @FXML
    private TextField employeeAddress;

    @FXML
    private PasswordField employeePassword;

    @FXML
    private PasswordField employeeConfirmPassword;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<EmployeeData> employeeTable;


    @FXML
    private TableColumn<EmployeeData, String> colName;

    @FXML
    private TableColumn<EmployeeData, String> colTel;

    @FXML
    private TableColumn<EmployeeData, String> colPassword;

    @FXML
    private TableColumn<EmployeeData, String> colAddress;

    private ObservableList<EmployeeData> data;

    private String sqlQuery = "SELECT * FROM employee";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployees();
    }

    public void addEmployee(ActionEvent event) {
        String insertQuery = "INSERT INTO employee (sn,name,tel,address,password) VALUES(?,?,?,?,?)";

        try {

            Connection connection = SqliteConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, this.employeeName.getText());
            preparedStatement.setString(3, this.employeePhone.getText().toString());
            preparedStatement.setString(4, this.employeeAddress.getText());
            preparedStatement.setString(5, this.employeePassword.getText());

            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadEmployees() {
        try {

            Connection connection = SqliteConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);

            while (resultSet.next()) {
                data.add(new EmployeeData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5)));
            }


        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }

//        this.colSn.setCellValueFactory(new PropertyValueFactory<EmployeeData, Integer>("sn"));
        this.colName.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("name"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("tel"));
        this.colAddress.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("address"));
        this.colPassword.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("password"));

        this.employeeTable.setItems(null);
        this.employeeTable.setItems(this.data);

    }

    public void refresh(ActionEvent event) {
        try {

            Connection connection = SqliteConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);

            while (resultSet.next()) {
                data.add(new EmployeeData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5)));
            }


        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }

        this.colName.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("name"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("tel"));
        this.colAddress.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("address"));
        this.colPassword.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("password"));

        this.employeeTable.setItems(null);
        this.employeeTable.setItems(this.data);

    }

    public void updateEmployee(ActionEvent event) {

    }

    public void removeEmployee(ActionEvent event){

        EmployeeData selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove employee");

            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to remove this employee?");

            Optional<ButtonType> optionalType = alert.showAndWait();
            if (optionalType.get() == ButtonType.OK) {
                String deleteQuery = "DELETE FROM employee where name = ?";
                Connection connection = SqliteConnection.getConnection();

                try {
                    PreparedStatement statement = connection.prepareStatement(deleteQuery);
                    statement.setString(1, selectedEmployee.getName());

                    statement.executeUpdate();
                    connection.close();

                    employeeTable.getItems().remove(selectedEmployee);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (optionalType.get() == ButtonType.CANCEL) {
                alert.hide();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");

            alert.setHeaderText(null);
            alert.setContentText("You must select an employee first");

            alert.showAndWait();
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

    public void clearEntries(ActionEvent event) {
        this.employeeName.setText("");
        this.employeePhone.setText("");
        this.employeePassword.setText("");
        this.employeeAddress.setText("");
    }

}
