package com.training.demoKafkaProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.demoKafkaProject.dto.EmployeeDTO;
import com.training.demoKafkaProject.entity.Employee;
import com.training.demoKafkaProject.service.EmployeeService;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "testPostgress")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/employee/{topic}")
    public ResponseEntity<String> add(@PathVariable("topic") String topic,@RequestBody EmployeeDTO employeeDTO) throws JsonProcessingException
    {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO , employee);
        employeeService.sendMessage(topic,employee);
        return new ResponseEntity<String>(employee.getFirstName() +" for topic "+topic+" received",HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/employee/{id}")
    public Employee getById(@PathVariable("id") String id)
    {
        return employeeService.getById(id);
    }

    @GetMapping(value="/employee")
    public Iterable<Employee> getAll()
    {
        return employeeService.getAll();
    }
}
