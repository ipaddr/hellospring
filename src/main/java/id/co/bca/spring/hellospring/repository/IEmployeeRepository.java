package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import java.util.List;

public interface IEmployeeRepository {
    void create(EmployeeModel employee);
    List<EmployeeModel> retrieveAll();
    EmployeeModel retrieveUnique(EmployeeModel employee);
    void update(EmployeeModel employee);
    void deleteUnique(EmployeeModel employee);
}

