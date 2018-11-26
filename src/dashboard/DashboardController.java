package dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class DashboardController implements Initializable{

    @FXML
    private Button openEmployee;

    @FXML
    private Label dateLabel;

    @FXML
    private Label currentTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        Date date = new Date();
//        Date presentTime = new java.sql.Time(date.getTime());
//        String time = currentTime.getText() + " " + presentTime.toString();
//        currentTime.setText(time);
//
//        Date presentDate = new java.sql.Date(date.getTime());
//        String currentDate = dateLabel.getText() + " " + presentDate.toString();
//        dateLabel.setText(currentDate);
    }


    public void openEmployees(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Pane root = null;
        try {
            root = loader.load(getClass().getResource("/employee/employee.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Employees");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void signOut(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Pane root = null;
        try {
            root = loader.load(getClass().getResource("/login/login.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login to your account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openStock(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Pane root = null;
        try {
            root = loader.load(getClass().getResource("/stock/stock.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stocks");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openSales(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Pane root = null;
        try {
            root = loader.load(getClass().getResource("/sales/sales.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = null;
        if (root != null) {
            scene = new Scene(root);
        }
        primaryStage.setTitle("Sales");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
