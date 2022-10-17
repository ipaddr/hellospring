package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.TreeMap;

@Repository
public class EmployeeRepoMemory implements IEmployeeRepository{

    private TreeMap<Integer, EmployeeModel> employeeMap = new TreeMap<>();

    public EmployeeRepoMemory(){

        EmployeeModel employee = new EmployeeModel();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe@email.com");

        EmployeeModel employee2 = new EmployeeModel();
        employee2.setId(2);
        employee2.setFirstName("Adam");
        employee2.setLastName("Noah");
        employee2.setEmail("adamnoah@email.com");

        EmployeeModel employee3 = new EmployeeModel();
        employee3.setId(3);
        employee3.setFirstName("Sarah");
        employee3.setLastName("Allan");
        employee3.setEmail("sarahallen@email.com");

        employeeMap.put(employee.getId(), employee);
        employeeMap.put(employee2.getId(), employee2);
        employeeMap.put(employee3.getId(), employee3);
    }


    @Override
    public void create(EmployeeModel employee) {
        int id = employeeMap.lastKey() + 1;
        employee.setId(id);
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public List<EmployeeModel> retrieveAll() {
        return employeeMap.values().stream().toList();
    }

    @Override
    public EmployeeModel retrieveUnique(EmployeeModel employee) {
        return employeeMap.get(employee.getId());
    }

    @Override
    public void update(EmployeeModel employee) {
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public void deleteUnique(EmployeeModel employee) {
        employeeMap.remove(employee.getId());
    }
}
