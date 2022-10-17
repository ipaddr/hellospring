package id.co.bca.spring.hellospring.controller;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public @ResponseBody List<EmployeeModel> findAll(){
        return employeeService.allEmployees();
    }

    @GetMapping("/id")
    public @ResponseBody EmployeeModel getEmployee(@RequestParam("id") int id){
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        return employeeService.findTheEmployee(model);
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstname") String firstname
            , @RequestParam("lastname") String lastname
            , @RequestParam("email") String email){
        EmployeeModel model = new EmployeeModel();
        model.setId(0);
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmail(email);
        employeeService.insert(model);
        return "redirect:/employee/all";
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") int id
            , @RequestParam("firstname") String firstname
            , @RequestParam("lastname") String lastname
            , @RequestParam("email") String email){
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmail(email);
        employeeService.update(model);
        return "redirect:/employee/all";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id){
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        employeeService.delete(model);
        return "redirect:/employee/all";
    }

}
