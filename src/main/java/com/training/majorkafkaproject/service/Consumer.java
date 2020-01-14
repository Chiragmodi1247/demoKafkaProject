package com.training.majorkafkaproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.majorkafkaproject.entity.Employee;
import com.training.majorkafkaproject.entity.EmployeeEntityMongo;
import com.training.majorkafkaproject.entity.EmployeeEntityPostgres;
import com.training.majorkafkaproject.repository.EmployeeRepositoryMongo;
import com.training.majorkafkaproject.repository.EmployeeRepositoryPostgres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    private Integer count=0;
    @Autowired
    EmployeeRepositoryMongo employeeRepositoryMongo;

    @Autowired
    EmployeeRepositoryPostgres employeeRepositoryPostgres;
    @KafkaListener(topics = "employeekafka", groupId = "group-id")
    public void consume(String message) throws IOException {
        try {
            System.out.println("Got message : " + message);
            ObjectMapper ow = new ObjectMapper();
            if (count < 150) {
                EmployeeEntityMongo employeeEntityMongo = ow.readValue(message, EmployeeEntityMongo.class);
                employeeRepositoryMongo.insert(employeeEntityMongo);
                System.out.println(employeeEntityMongo.getFirstName() + " from mongo");
            } else {
                EmployeeEntityPostgres employeeEntityPostgres = ow.readValue(message, EmployeeEntityPostgres.class);
                employeeRepositoryPostgres.save(employeeEntityPostgres);
                System.out.println(employeeEntityPostgres.getFirstName() + " from postgres");
            }
            count++;
        } catch (JsonProcessingException ex) {
            System.out.println("DATABASE ADD ERROR");
        }


//        ObjectMapper ow = new ObjectMapper();
//        Employee employee = ow.readValue(message,Employee.class);
//        ow.readValue(message,Employee.class);
//        System.out.println(employee.getFirstName()+employee.getLastName()+employee.getDateOfBirth()+employee.getExperience());
//

    }
}