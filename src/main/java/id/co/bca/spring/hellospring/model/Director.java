package id.co.bca.spring.hellospring.model;
import org.springframework.stereotype.Component;
@Component
public class Director extends EmployeeModel implements Employee {
    @Override
    public String salary() {
        return "Rp.32.000.000,-";
    }
}
