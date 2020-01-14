package com.training.demoKafkaProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.demoKafkaProject.entity.Employee;

public interface EmployeeService {

    //public Employee consume(Employee employee);
    void sendMessage(String TOPIC, Employee employee) throws JsonProcessingException;
    public Employee getById(String id);
    public Iterable<Employee> getAll();
}
