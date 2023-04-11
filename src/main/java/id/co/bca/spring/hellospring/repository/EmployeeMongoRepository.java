package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.model.EmployeeMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMongoRepository
        extends MongoRepository<EmployeeMongoDB, String> {
}
