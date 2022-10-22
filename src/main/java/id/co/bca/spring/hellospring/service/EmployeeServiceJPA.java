package id.co.bca.spring.hellospring.service;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.repository.EmployeeSDJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceJPA implements IEmployeeService{

    @Autowired
    EmployeeSDJRepository sdjRepository;

    @Override
    public void insert(EmployeeModel employee) {
        sdjRepository.save(employee);
    }

    @Override
    public void update(EmployeeModel employee) {
        sdjRepository.save(employee);
    }

    @Override
    public void delete(EmployeeModel employee) {
        sdjRepository.deleteById(employee.getId());
    }

    @Override
    public EmployeeModel findTheEmployee(EmployeeModel employee) {
        return sdjRepository.findEmployeeById(employee.getId() );
    }

    @Override
    public List<EmployeeModel> allEmployees() {
        return sdjRepository.findAllByOrderByLastName();
    }

    @Override
    public List<EmployeeModel> allEmployeesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return sdjRepository.findAll(pageable).getContent();
    }
}


