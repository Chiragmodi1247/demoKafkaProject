package com.training.demoKafkaProject.service;

import com.training.demoKafkaProject.entity.EmployeeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmployeeService {

    //public Employee consume(Employee employee);
    void sendMessage(String TOPIC, EmployeeEntity employeeEntity) throws JsonProcessingException;
    public Employee getById(String id);
    public Iterable<Employee> getAll();
}
