package id.co.bca.spring.hellospring.service;

import id.co.bca.spring.hellospring.model.EmployeeModel;

import java.util.List;

public interface IEmployeeService {
    void insert(EmployeeModel employee);
    void update(EmployeeModel employee);
    void delete(EmployeeModel employee);
    EmployeeModel findTheEmployee(EmployeeModel employee);
    List<EmployeeModel> allEmployees();
    List<EmployeeModel> allEmployeesPage(int page, int size);
}








