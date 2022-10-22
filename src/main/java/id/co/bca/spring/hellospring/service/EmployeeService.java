package id.co.bca.spring.hellospring.service;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    @Qualifier("employeeRepoHibernate")
    private IEmployeeRepository iEmployeeRepository;

    public void insert(EmployeeModel employee){
        iEmployeeRepository.create(employee);
    }

    public void update(EmployeeModel employee){
        iEmployeeRepository.update(employee);
    }

    public void delete(EmployeeModel employee){
        iEmployeeRepository.deleteUnique(employee);
    }

    public EmployeeModel findTheEmployee(EmployeeModel employee){return  iEmployeeRepository.retrieveUnique(employee);}

    public List<EmployeeModel> allEmployees(){
        return iEmployeeRepository.retrieveAll();
    }

    @Override
    public List<EmployeeModel> allEmployeesPage(int page, int size) {
        return null;
    }
}









