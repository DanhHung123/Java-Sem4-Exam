package com.example.employeeManagerment.dao;

import com.example.employeeManagerment.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/employeeDb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_EMPLOYEE = "INSERT INTO employees" + " (fullName, birthDay, address,position,department) VALUES " + " (?, ?, ?, ?,?)";
    private static final String SELECT_EMPLOYEE_BY_ID = "select * from employees where id=?";
    private static final String SEARCH_EMPLOYEE = "select * from employees where fullName LIKE''%?%;";
    private static final String SELECT_ALL_EMPLOYEE = "select * from employees";
    private static final String DELETE_EMPLOYEE = "delete from employees where id = ? ;";
    private static final String UPDATE_EMPLOYEE = "update employees set fullName = ?, birthDay=?, address=?,position=?,department=? where id = ? ;";

    public EmployeeDao() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //Insert employee
    public void insertEmployee(Employee employee) throws SQLException {
        System.out.println(INSERT_EMPLOYEE);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)
        ) {
            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setString(2, employee.getBirthDay());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getDepartment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Select employee by id
    public Employee selectEmployee(int id) throws SQLException {
        Employee employee = null;
        System.out.println(SELECT_EMPLOYEE_BY_ID);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String birthDay = resultSet.getString("birthDay");
                String address = resultSet.getString("address");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                employee = new Employee(id,fullName,birthDay,address,position,department);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  employee;
    }

    // Search employee
    public List<Employee> searchEmployee(String search) throws SQLException {
        List<Employee> employees = null;
        System.out.println(SELECT_EMPLOYEE_BY_ID);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPLOYEE)
        ) {
            preparedStatement.setString(1, search);
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                String birthDay = resultSet.getString("birthDay");
                String address = resultSet.getString("address");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                employees.add(new Employee(id, fullName,birthDay,address,position,department));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  employees;
    }

    //Select all employee
    public List<Employee> selectAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        System.out.println(SELECT_ALL_EMPLOYEE);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                String birthDay = resultSet.getString("birthDay");
                String address = resultSet.getString("address");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                employees.add(new Employee(id, fullName,birthDay,address,position,department));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return  employees;
    }

    //Update employee
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdate;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
        ) {
//            System.out.println("Update employee: " + preparedStatement);

            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setString(2, employee.getBirthDay());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getDepartment());
            preparedStatement.setInt(6, employee.getId());

            rowUpdate = preparedStatement.executeUpdate() > 0;

        }
        return  rowUpdate;
    }

    //Delete employee

    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);)
        {
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return  rowDeleted;
    }
}
