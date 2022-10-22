package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentSJDRepository
        extends JpaRepository<Department, Integer> {
    Department findDepartmentById(Integer aInteger);
}



