package com.epam.konstantin_frolov.java.lesson7.task1.DB;

import com.epam.konstantin_frolov.java.lesson7.task1.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBEmployee {
    private Connection connection;

    public DBEmployee(DBConnector connector) {
        this.connection = connector.getConnection();
    }

    public boolean createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS employees(" +
                    "        id SERIAL PRIMARY KEY UNIQUE NOT NULL," +
                    "        first_name varchar(50) NOT NULL," +
                    "        last_name varchar(50) NOT NULL," +
                    "        salary INTEGER NOT NULL)");
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        try {
            List<Employee> employees = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer salary = Integer.parseInt(resultSet.getString("salary"));
                employees.add(new Employee(firstName, lastName, salary));
            }
            statement.close();
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(Employee employee) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO employees (first_name, last_name, salary) VALUES(?, ?, ?)");
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
