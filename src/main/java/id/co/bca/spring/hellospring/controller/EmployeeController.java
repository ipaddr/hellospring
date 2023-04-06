package id.co.bca.spring.hellospring.controller;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.model.EmployeeWithDepartment;
import id.co.bca.spring.hellospring.repository.EmployeeSDJRepository;
import id.co.bca.spring.hellospring.service.DepartmentAndEmployeeService;
import id.co.bca.spring.hellospring.service.EmployeeService;
import id.co.bca.spring.hellospring.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeServiceJPA")
    private IEmployeeService employeeService;

    @Autowired
    private DepartmentAndEmployeeService departmentAndEmployeeService;

    @Autowired
    private EmployeeSDJRepository employeeSDJRepository;

    @GetMapping("/join")
    public @ResponseBody List<EmployeeWithDepartment> findAllJoin(){
        System.out.println(SpringVersion.getVersion());
        return employeeSDJRepository.findEmployeeByIdWithDepartment();
    }

    @GetMapping("/add-ed")
    public String addEmployee(@RequestParam("firstname") String firstname
            , @RequestParam("lastname") String lastname
            , @RequestParam("email") String email
            ,@RequestParam("did") int did){
        EmployeeModel model = new EmployeeModel();
        model.setId(0);
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmail(email);
        model.setDepartment(null);
        //departmentAndEmployeeService.addEmployeeToDepartmentWith(model, did);
        departmentAndEmployeeService.addEmployeeToDepartmentWithTransactional(model, did);
        return "redirect:/employee/all";
    }

    @GetMapping("/all")
    public @ResponseBody List<EmployeeModel> findAll(){
        return employeeService.allEmployees();
    }

    @GetMapping("/allpage")
    public @ResponseBody List<EmployeeModel> findAllPage(@RequestParam("page") int page
            , @RequestParam("size") int size){
        return employeeService.allEmployeesPage(page, size);
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
