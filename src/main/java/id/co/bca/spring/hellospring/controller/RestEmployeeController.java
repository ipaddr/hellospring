package id.co.bca.spring.hellospring.controller;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.repository.EmployeeSDJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RestEmployeeController {

    @Autowired
    private EmployeeSDJRepository employeeSDJRepository;

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeModel> findAll(){
        return employeeSDJRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel findEmployeeById(@PathVariable("id") int id){
        return employeeSDJRepository.findEmployeeById(id);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel addEmployee(@RequestBody EmployeeModel employeeModel){
        return employeeSDJRepository.save(employeeModel);
    }

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeModel updateEmployee(@RequestBody EmployeeModel employeeModel
            , @PathVariable("id") int id){
        employeeModel.setId(id);
        return employeeSDJRepository.save(employeeModel);
    }

    @DeleteMapping("employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") int id){
        employeeSDJRepository.deleteById(id);
    }

}


