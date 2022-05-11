package homework.lesson27;

import util.database.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUserService {
    public boolean validateCredentials(String login, String password) {
        String sqlLogin = String.format("SELECT * FROM user WHERE login like '%s'", login);
        ResultSet resultSet = DbUtils.doRequest(sqlLogin);
        try {
            if (resultSet.next()) {
                return password.equals(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isLoginExist(String loginToCheck) {
        String sqlLogin = String.format("SELECT * FROM user WHERE login like '%s'", loginToCheck);
        try {
            return DbUtils.doRequest(sqlLogin).next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void createNewUser(User user) {
        String query = String.format("Insert into user(login, name, password, age) values('%s','%s', '%s', %d)", user.getLogin(),
                user.getName(), user.getPassword(), user.getAge());
        if (DbUtils.executeUpdate(query) > 0) {
            System.out.println("User was registered");
        } else {
            System.out.println("User was not registered");
        }
    }

    public List<User> getUserData() {
        String query = "Select * from user";
        List<User> users = new ArrayList<>();
        try {
            ResultSet rs = DbUtils.doRequest(query);
            while (rs.next()) {
                User user = new User();
                user.setAge(rs.getInt("age"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User getUserData(String login) {
        String sqlLogin = String.format("SELECT * FROM user WHERE login like '%s'",
                login);
        ResultSet resultSet = DbUtils.doRequest(sqlLogin);
        User user = new User();
        try {
            if (resultSet.next()) {
                user.setAge(resultSet.getInt("age"));
                user.setLogin(login);
                user.setRoot(resultSet.getInt("isRoot") == 1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
