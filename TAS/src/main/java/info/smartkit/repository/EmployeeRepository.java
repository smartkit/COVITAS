package info.smartkit.repository;

import info.smartkit.pojo.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by smartkit on 28/04/2017.
 */
public interface EmployeeRepository extends MongoRepository<Employee,String>{
}
