package striker.studing.database;

import striker.studing.collections.ArrayList;

import java.sql.*;
import java.util.List;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/sql_stream_test?serverTimeZone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "fastumgel";

    public User createUser(User user){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
             "INSERT INTO user_with_dep(user_with_dep.name, department)" +
                 "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setString(1, user.getName());
            statement.setInt(2, user.getDepartment().getId());
            statement.execute();
            try(ResultSet resultSet = statement.getGeneratedKeys()){
                while (resultSet.next()){
                    user.setId(resultSet.getInt(1));
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
    public List<User> readUsers(){
        List<User> users = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
             "SELECT user_with_dep.id, user_with_dep.name, department.id, department.name, country.id, country.name " +
                 "FROM user_with_dep " +
                 "INNER JOIN department ON user_with_dep.department = department.id " +
                 "INNER JOIN country ON department.country = country.id");
        ){
            users = new ArrayList<>();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                Department department = new Department();
                department.setId(resultSet.getInt(3));
                department.setName(resultSet.getString(4));
                Country country = new Country();
                country.setId(resultSet.getInt(5));
                country.setName(resultSet.getString(6));
                department.setCountry(country);
                user.setDepartment(department);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    public void updateUser(User user){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
             "UPDATE user_with_dep " +
                 "SET user_with_dep.name = ?, department = ? " +
                 "WHERE id = ?;")
        ){
            statement.setString(1, user.getName());
            statement.setInt(2, user.getDepartment().getId());
            statement.setInt(3, user.getId());
            statement.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
