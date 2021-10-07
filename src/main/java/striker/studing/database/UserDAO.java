package striker.studing.database;

import striker.studing.collections.ArrayList;

import java.sql.*;
import java.util.List;

public class UserDAO implements DAO<User>{

    @Override
    public User create(User user){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
             "INSERT INTO user_with_dep(user_with_dep.name, department)" +
                 "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setString(1, user.getName());
            statement.setLong(2, user.getDepartment().getId());
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
    @Override
    public List<User> readAll(){
        List<User> users = null;
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
             "SELECT user_with_dep.id, user_with_dep.name, department.id, department.name, country.id, country.name " +
                 "FROM user_with_dep " +
                 "INNER JOIN department ON user_with_dep.department = department.id " +
                 "INNER JOIN country ON department.country = country.id " +
                 "ORDER BY user_with_dep.id");
        ){
            users = new ArrayList<>();
            while (resultSet.next()){
                users.add(extractUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    @Override
    public User read(long id){
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
         "SELECT user_with_dep.id, user_with_dep.name, department.id, department.name, country.id, country.name " +
             "FROM user_with_dep " +
             "INNER JOIN department ON user_with_dep.department = department.id " +
             "INNER JOIN country ON department.country = country.id " +
             "WHERE user_with_dep.id = ?");
        ){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = extractUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
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
        return user;
    }

    @Override
    public void update(User user){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
             "UPDATE user_with_dep " +
                 "SET user_with_dep.name = ?, department = ? " +
                 "WHERE id = ?;")
        ){
            statement.setString(1, user.getName());
            statement.setLong(2, user.getDepartment().getId());
            statement.setLong(3, user.getId());
            statement.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void delete(User user){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
             "DELETE FROM user_with_dep " +
                 "WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setLong(1, user.getId());
            statement.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
