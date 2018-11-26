package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;






public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (this.loginModel.isDbConnected()) {
            status.setText("Connected");
        } else {
            status.setText("Not connected");
        }
    }

    public void Login(ActionEvent event) throws IOException {
        try {
            if (this.loginModel.isLogin(username.getText(), password.getText())) {
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();

                Pane root = loader.load(getClass().getResource("/dashboard/dashboard.fxml").openStream());
                Scene scene = new Scene(root);
                primaryStage.setTitle("Dashboard");
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            } else {
                status.setText("Incorrect password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
