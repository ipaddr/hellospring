package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.datasource.MySQLDataSource;
import id.co.bca.spring.hellospring.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Repository
public class EmployeeRepoImplWithJDBCTemplate
        implements IEmployeeRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void create(EmployeeModel employee) {
        jdbcTemplate.execute(MySQLDataSource.STM_CREATE, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setDate(4, new Date(System.currentTimeMillis()));
                ps.setTime(5, new Time(System.currentTimeMillis()));
                ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                return ps.execute();
            }
        });
    }

    @Override
    public List<EmployeeModel> retrieveAll() {
        return jdbcTemplate.query(MySQLDataSource.STM_RETRIEVE_ALL, new ResultSetExtractor<List<EmployeeModel>>() {
            @Override
            public List<EmployeeModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EmployeeModel> employees = new ArrayList<>();
                while (rs.next()){
                    EmployeeModel employee = new EmployeeModel();
                    employee.setId(rs.getInt("id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employees.add(employee);
                }
                return employees;
            }
        });
    }

    @Override
    public EmployeeModel retrieveUnique(EmployeeModel employee) {
        List<EmployeeModel> employeeModels = jdbcTemplate.query(MySQLDataSource.STM_RETRIEVE_BY_ID
                , new PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, employee.getId());
            }
        }, new RowMapper<EmployeeModel>(){
            @Override
            public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                EmployeeModel model = new EmployeeModel();
                model.setId(rs.getInt("id"));
                model.setFirstName(rs.getString("first_name"));
                model.setLastName(rs.getString("last_name"));
                model.setEmail(rs.getString("email"));
                return model;
            }
        });
        return employeeModels.get(0);
    }

    @Override
    public void update(EmployeeModel employee) {
        String STM_UPDATE = "UPDATE employee SET first_name=:firstName" +
                ", last_name=:lastName, email=:email, date=:date, time=:time, timestamp=:timestamp " +
                "WHERE id=:id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", employee.getId());
        map.put("firstName", employee.getFirstName());
        map.put("lastName", employee.getLastName());
        map.put("email", employee.getEmail());
        map.put("date", new Date(System.currentTimeMillis()));
        map.put("time", new Time(System.currentTimeMillis()));
        map.put("timestamp", new Timestamp(System.currentTimeMillis()));

        namedParameterJdbcTemplate.execute(STM_UPDATE, map, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                return ps.execute();
            }
        });
    }

    @Override
    public void deleteUnique(EmployeeModel employee) {
        jdbcTemplate.update(MySQLDataSource.STM_DELETE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, employee.getId());
            }
        });
    }
}