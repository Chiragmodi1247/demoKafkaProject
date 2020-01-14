package com.training.demoKafkaProject.service;

import com.training.demoKafkaProject.entity.EmployeeEntityPostgres;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmployeeService {

    //public Employee consume(Employee employee);
    void sendMessage(String TOPIC, EmployeeEntityPostgres employeeEntityPostgres) throws JsonProcessingException;
    public EmployeeEntityPostgres getById(String id);
    public Iterable<EmployeeEntityPostgres> getAll();
}
