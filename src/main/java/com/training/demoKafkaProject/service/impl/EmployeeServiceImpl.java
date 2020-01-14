package com.training.demoKafkaProject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.demoKafkaProject.entity.Employee;
import com.training.demoKafkaProject.repository.EmployeeRepository;
import com.training.demoKafkaProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void sendMessage(String TOPIC,Employee employee) throws JsonProcessingException{

        ObjectMapper ow = new ObjectMapper();
        String json = ow.writeValueAsString(employee);
        this.kafkaTemplate.send(TOPIC,json);
    }



    @KafkaListener(topics = "test", groupId = "group_id")
    public Employee consumePost(String message) throws JsonProcessingException {
        ObjectMapper ow = new ObjectMapper();
        Employee employeeEntity = ow.readValue(message,Employee.class);
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public Employee getById(String id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        return employee.get();
    }

    @Override
    public Iterable<Employee> getAll() {
        Iterable<Employee> employee=employeeRepository.findAll();
        return employee;
    }


}
