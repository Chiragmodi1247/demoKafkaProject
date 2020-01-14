package com.training.demoKafkaProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.demoKafkaProject.entity.EmployeeEntity;

public interface EmployeeService {

    void sendMessage(String TOPIC, EmployeeEntity employeeEntity) throws JsonProcessingException;

}
