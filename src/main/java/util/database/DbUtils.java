package util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

    public static ResultSet doRequest(String query) {
        ResultSet resultSet = null;
        Connection connection = DatabaseConnection.getMysqlConnection();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultSet;
    }

    public static int executeUpdate(String query) {
        Connection connection = DatabaseConnection.getMysqlConnection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }
}
