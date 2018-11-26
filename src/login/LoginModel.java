package login;

import dbUtils.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public Connection connection;

    public LoginModel() {

        try {

            connection = SqliteConnection.getConnection();

        } catch (Exception e) {

        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();

        } catch (SQLException exc) {
            exc.printStackTrace();
            return false;

        }
    }

    public boolean isLogin(String user, String pass) throws SQLException {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String query = "SELECT * FROM users where username = ? and password = ?";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            return false;

        } finally {
            preparedStatement.close();
            resultSet.close();
        }

    }

}
