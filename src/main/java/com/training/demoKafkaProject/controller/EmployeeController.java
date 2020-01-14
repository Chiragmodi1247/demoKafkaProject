package com.training.demoKafkaProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.demoKafkaProject.dto.EmployeeDTO;
import com.training.demoKafkaProject.entity.EmployeeEntityPostgres;
import com.training.demoKafkaProject.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/send/{topic}/")
    public ResponseEntity<String> send(@PathVariable("topic") String topic, @RequestBody EmployeeDTO employeeDTO) throws JsonProcessingException {
        EmployeeEntityPostgres employee = new EmployeeEntityPostgres();
        BeanUtils.copyProperties(employeeDTO , employee);
        employeeService.sendMessage(topic,employee);
        return new ResponseEntity<String>(employee.getFirstName() +" for topic "+topic+" received",HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/employee/{id}")
    public EmployeeEntityPostgres getById(@PathVariable("id") String id)
    {
        return employeeService.getById(id);
    }

    @GetMapping(value="/employee")
    public Iterable<EmployeeEntityPostgres> getAll()
    {
        return employeeService.getAll();
    }
}
