package id.co.bca.spring.hellospring.controller;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.model.EmployeeMongoDB;
import id.co.bca.spring.hellospring.repository.EmployeeMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employeemongodb")
public class EmployeeMongoDBController {
    @Autowired
    private EmployeeMongoRepository employeeMongoRepository;
    @GetMapping("/employee")
    public @ResponseBody List<EmployeeMongoDB> getEmployeeMongoDB(){
        return employeeMongoRepository.findAll();
    }
    @GetMapping("/employee/add")
    public String addEmployee(@RequestParam("firstname") String firstname
            , @RequestParam("lastname") String lastname
            , @RequestParam("email") String email){
        EmployeeMongoDB model = new EmployeeMongoDB();
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmail(email);
        employeeMongoRepository.save(model);
        return "redirect:/employee/all";
    }

}
