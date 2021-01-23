package info.smartkit.tas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.smartkit.tas.pojo.Employee;
import info.smartkit.tas.pojo.PFMessage;
import info.smartkit.tas.repository.EmployeeRepository;
import info.smartkit.tas.service.ITASServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by smartkit on 28/04/2017.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee){

        Employee result = employeeRepository.save(employee);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{employeeId}")
    public Employee get(@PathVariable String employeeId){
        return employeeRepository.findOne(employeeId);
    }
}
