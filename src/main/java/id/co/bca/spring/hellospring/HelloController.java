package id.co.bca.spring.hellospring;

import id.co.bca.spring.hellospring.config.Api;
import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.model.Employee;
import id.co.bca.spring.hellospring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("supervisor")
    Employee employee;

    @Value("${spring.message}")
    private String message;

    @Autowired
    Api api;

    @GetMapping("/api")
    public @ResponseBody String api(){
        LOGGER.info("I am in api method");
        return "Base URL" + " : " + api.getUrl() +
                "<br>" +"" +
                "Data Type" + " : " + api.getDataType() +
                "<br>" +"" +
                message
                ;
    }

    @GetMapping("/hello")
    public @ResponseBody String hello(){
        return "Hello World!" + " " + api.getUrl();
    }

    @GetMapping("/salary")
    public @ResponseBody String salary(){ return employee.salary(); }

    @GetMapping("/message")
    public @ResponseBody String message(){ return message; }

//    @GetMapping("/employees")
//    public @ResponseBody
//    List<EmployeeModel> employees(){ return employeeService.allEmployees(); }

}
