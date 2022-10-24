package id.co.bca.spring.hellospring.service;

import id.co.bca.spring.hellospring.model.Department;
import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.repository.DepartmentSJDRepository;
import id.co.bca.spring.hellospring.repository.EmployeeSDJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentAndEmployeeService {

    @Autowired
    DepartmentSJDRepository departmentSJDRepository;
    @Autowired
    EmployeeSDJRepository employeeSDJRepository;

    public void addEmployeeToDepartment(EmployeeModel employeeModel, int did){
        employeeSDJRepository.save(employeeModel);
        Department department = departmentSJDRepository.findDepartmentById(Integer.valueOf(did));
        employeeModel.setDepartment(department);
        employeeSDJRepository.save(employeeModel);
    }

    @Transactional
    public void addEmployeeToDepartmentWithTransactional(EmployeeModel employeeModel, int did){
        employeeSDJRepository.save(employeeModel);
        Department department = departmentSJDRepository.findDepartmentById(Integer.valueOf(did));
        employeeModel.setDepartment(department);
        employeeSDJRepository.save(employeeModel);
    }
}
