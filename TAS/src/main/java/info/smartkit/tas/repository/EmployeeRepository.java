package info.smartkit.tas.repository;

import info.smartkit.tas.pojo.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by smartkit on 28/04/2017.
 */
public interface EmployeeRepository extends MongoRepository<Employee,String>{
}
