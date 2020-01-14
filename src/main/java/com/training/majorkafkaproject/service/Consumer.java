package com.training.majorkafkaproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.majorkafkaproject.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "employees", groupId = "group-id")
    public void consume(String message) throws IOException {
        ObjectMapper ow = new ObjectMapper();
        Employee employee = ow.readValue(message,Employee.class);
        ow.readValue(message,Employee.class);
        System.out.println(employee.getFirstName()+employee.getLastName()+employee.getDateOfBirth()+employee.getExperience());
    }

}
