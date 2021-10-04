package striker.studing.database;

import striker.studing.collections.ArrayList;

import java.sql.*;
import java.util.List;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/sql_stream_test?serverTimeZone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "fastumgel";

    public void s(){
        try (Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/sql_stream_test&serverTimeZone=UTC", "root", "fastumgel")){

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public List<User> readUsers(){
        List<User> users = null;
        try (ResultSet resultSet = executeStatement(
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
    private ResultSet executeStatement(String query){
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
