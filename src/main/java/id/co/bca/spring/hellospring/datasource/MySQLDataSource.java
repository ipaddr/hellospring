package id.co.bca.spring.hellospring.datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MySQLDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDataSource.class.getPackageName());

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_directory";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "";

    public static final String STM_CREATE = "INSERT INTO employee (first_name, last_name, email, date, time, timestamp) VALUES (?,?,?,?,?,?)";
    public static final String STM_RETRIEVE_ALL = "SELECT * FROM employee";
    public static final String STM_RETRIEVE_BY_ID = "SELECT * FROM employee WHERE id=? ";
    public static final String STM_UPDATE = "UPDATE employee SET first_name=?, last_name=?, email=?, date=?, time=?, timestamp=? WHERE id=?";
    public static final String STM_DELETE = "DELETE FROM employee WHERE id=?";

    public MySQLDataSource(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL
                    , JDBC_USERNAME
                    , JDBC_PASSWORD);
            LOGGER.info("connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("connection unsuccessful");
        } finally {
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }

    public void insertEmployee(EmployeeModel employee){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL
                    , JDBC_USERNAME
                    , JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_CREATE
                    , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.setTime(5, new Time(System.currentTimeMillis()));
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.execute();
            set = ps.getGeneratedKeys();
            if (set.next()) {
                int id = set.getInt(1);
                LOGGER.info("Successful insert data with id "+ String.valueOf(id));
            }
        } catch (SQLException e) {
            LOGGER.info("unSuccessful insert data");
            e.printStackTrace();
        } finally {
            try { if (set != null) set.close(); } catch (Exception e) {};
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }

    public void updateEmployee(EmployeeModel employee){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL
                    , JDBC_USERNAME
                    , JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_UPDATE);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.setTime(5, new Time(System.currentTimeMillis()));
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, employee.getId());
            ps.execute();
            LOGGER.info("Successful update data");
        } catch (SQLException e) {
            LOGGER.info("unSuccessful update data");
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }

    public void deleteEmployee(EmployeeModel employee){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL
                    , JDBC_USERNAME
                    , JDBC_PASSWORD);
            ps = connection.prepareStatement(STM_DELETE);
            ps.setInt(1, employee.getId());
            ps.execute();
            LOGGER.info("Successful delete data");
        } catch (SQLException e) {
            LOGGER.info("unSuccessful delete data");
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
    }

    public List<EmployeeModel> getAllEmployee(){
        List<EmployeeModel> employees = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL
                    , JDBC_USERNAME
                    , JDBC_PASSWORD);
            statement = connection.createStatement();
            set = statement.executeQuery(STM_RETRIEVE_ALL);
            while (set.next()){
                EmployeeModel employee = new EmployeeModel();
                employee.setId(set.getInt("id"));
                employee.setFirstName(set.getString("first_name"));
                employee.setLastName(set.getString("last_name"));
                employee.setEmail(set.getString("email"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (set != null) set.close(); } catch (Exception e) {};
            try { if (statement != null) statement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
        return employees;
    }

    public EmployeeModel getAllEmployeeWithId(EmployeeModel emp){
        EmployeeModel employee = new EmployeeModel();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL
                    , JDBC_USERNAME
                    , JDBC_PASSWORD);
            statement = connection.createStatement();
            set = statement.executeQuery(STM_RETRIEVE_ALL);
            while (set.next()){
                if (emp.getId() == set.getInt("id")){
                    employee.setId(set.getInt("id"));
                    employee.setFirstName(set.getString("first_name"));
                    employee.setLastName(set.getString("last_name"));
                    employee.setEmail(set.getString("email"));
                    LOGGER.info("date : "+set.getDate("date"));
                    LOGGER.info("time : "+set.getTime("time"));
                    LOGGER.info("timestamp : "+set.getTimestamp("timestamp"));
                    break;
                } else {
                    continue;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (set != null) set.close(); } catch (Exception e) {};
            try { if (statement != null) statement.close(); } catch (Exception e) {};
            try { if (connection != null) connection.close(); } catch (Exception e) {};
        }
        return employee;
    }

}
