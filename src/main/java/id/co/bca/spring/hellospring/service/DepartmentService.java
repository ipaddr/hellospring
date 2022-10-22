package id.co.bca.spring.hellospring.service;

import id.co.bca.spring.hellospring.model.Department;
import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.repository.DepartmentSJDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentSJDRepository repository;

    public void insert(Department department){repository.save(department);}
    public void update(Department department){repository.save(department);}
    public void delete(Department department){repository.deleteById(department.getId());}
    public List<Department> all(){return repository.findAll();}
}














