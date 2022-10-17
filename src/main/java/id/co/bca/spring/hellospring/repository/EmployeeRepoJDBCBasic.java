package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.datasource.MySQLDataSource;
import id.co.bca.spring.hellospring.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepoJDBCBasic implements IEmployeeRepository {

    @Autowired
    MySQLDataSource mySQLDataSource;

    @Override
    public void create(EmployeeModel employee) {mySQLDataSource.insertEmployee(employee);}

    @Override
    public List<EmployeeModel> retrieveAll() {return mySQLDataSource.getAllEmployee();}

    @Override
    public EmployeeModel retrieveUnique(EmployeeModel employee) {return mySQLDataSource.getAllEmployeeWithId(employee);}

    @Override
    public void update(EmployeeModel employee) {mySQLDataSource.updateEmployee(employee);}

    @Override
    public void deleteUnique(EmployeeModel employee) {mySQLDataSource.deleteEmployee(employee);}
}
