package com.training.demoKafkaProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.demoKafkaProject.dto.EmployeeDTO;
import com.training.demoKafkaProject.entity.EmployeeEntity;
import com.training.demoKafkaProject.service.EmployeeService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Constructor
    public void createNewTopic(){
        String name = "employee";
        Integer numPartitions = 3;
        Short replicationFactor = 1;
        NewTopic newTopic = new NewTopic(name,numPartitions,replicationFactor);
    }


    @Autowired
    EmployeeService employeeService;

    @PostMapping("/send/{topic}/")
    public ResponseEntity<String> send(@PathVariable("topic") String topic, @RequestBody EmployeeDTO employeeDTO) throws JsonProcessingException {
        EmployeeEntity employee = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO , employee);
        employeeService.sendMessage(topic,employee);
        return new ResponseEntity<String>(employee.getFirstName() +" for topic "+topic+" received",HttpStatus.ACCEPTED);
    }

}
