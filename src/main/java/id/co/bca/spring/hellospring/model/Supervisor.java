package id.co.bca.spring.hellospring.model;
import org.springframework.stereotype.Component;
@Component
public class Supervisor extends EmployeeModel implements Employee {
    @Override
    public String salary() {
        return "Rp. 8.000.000,-";
    }
}
