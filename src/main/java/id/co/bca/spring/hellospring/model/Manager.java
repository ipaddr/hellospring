package id.co.bca.spring.hellospring.model;
import org.springframework.stereotype.Component;
@Component
public class Manager extends EmployeeModel implements Employee {
    @Override
    public String salary() {
        return "Rp. 16.000.000,-";
    }
}
