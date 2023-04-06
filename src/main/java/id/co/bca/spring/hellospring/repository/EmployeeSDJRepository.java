package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.model.EmployeeModel;
import id.co.bca.spring.hellospring.model.EmployeeWithDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeSDJRepository extends JpaRepository<EmployeeModel, Integer> {
    EmployeeModel findEmployeeById(Integer aInteger);
    List<EmployeeModel> findAllByOrderByLastName();
    Page<EmployeeModel> findAllByOrderByEmail(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT CONCAT(e.first_name, ' ', e.last_name) as name, " +
            "e.email as email, d.name as department FROM employee as e JOIN department as d ON e.id = d.id;")
    List<EmployeeWithDepartment> findEmployeeByIdWithDepartment();
}











