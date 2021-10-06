package striker.studing.database;

import striker.studing.collections.ArrayList;

import java.sql.*;
import java.util.List;

public class DepartmentDAO{

    public List<Department> readAll(){
        List<Department> departments = null;
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
             "SELECT department.id, department.name, country.id, country.name " +
                 "FROM department " +
                 "INNER JOIN country ON department.country = country.id");
        ){
            departments = new ArrayList<>();
            while (resultSet.next()){
                departments.add(extractDepartment(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return departments;
    }
    public Department read(long id){
        Department department = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
         "SELECT department.id, department.name, country.id, country.name " +
             "FROM department " +
             "INNER JOIN country ON department.country = country.id " +
             "WHERE department.id = ?");
        ){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                department = extractDepartment(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }

    private Department extractDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt(1));
        department.setName(resultSet.getString(2));
        Country country = new Country();
        country.setId(resultSet.getInt(3));
        country.setName(resultSet.getString(4));
        department.setCountry(country);
        return department;
    }
}
