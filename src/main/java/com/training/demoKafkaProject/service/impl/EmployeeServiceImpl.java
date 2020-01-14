package com.training.demoKafkaProject.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.demoKafkaProject.entity.EmployeeEntityMongo;
import com.training.demoKafkaProject.entity.EmployeeEntityPostgres;
import com.training.demoKafkaProject.repository.EmployeeRepositoryMongo;
import com.training.demoKafkaProject.repository.EmployeeRepositoryPostgres;
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
    EmployeeRepositoryMongo employeeRepositoryMongo;

    @Autowired
    EmployeeRepositoryPostgres employeeRepositoryPostgres;

    @Override
    public void sendMessage(String TOPIC,EmployeeEntityPostgres employeeEntityPostgres) throws JsonProcessingException {

        ObjectMapper ow = new ObjectMapper();
        String json = ow.writeValueAsString(employeeEntityPostgres);
        System.out.println("Send object: "+json);
        this.kafkaTemplate.send(TOPIC,json);
    }

    @Override
    public EmployeeEntityPostgres getById(String id) {
        Optional<EmployeeEntityPostgres> employee = employeeRepositoryPostgres.findById(id);
        return employee.get();
    }

    @Override
    public Iterable<EmployeeEntityPostgres> getAll() {
        Iterable<EmployeeEntityPostgres> employeeEntities =employeeRepositoryPostgres.findAll();
        return employeeEntities;
    }

    @KafkaListener(topics = "employee", groupId = "group_id")
        public void consumeForMongo(String message) throws JsonProcessingException {
        ObjectMapper ow = new ObjectMapper();
        EmployeeEntityMongo employeeEntityMongo = ow.readValue(message,EmployeeEntityMongo.class);
        employeeRepositoryMongo.insert(employeeEntityMongo);
        System.out.println(employeeEntityMongo.getFirstName()+" from mongo");
        }

    @KafkaListener(topics = "employee", groupId = "group_id")
    public void consumeForPostgres(String message) throws JsonProcessingException {
        ObjectMapper ow = new ObjectMapper();
        EmployeeEntityPostgres employeeEntityPostgres = ow.readValue(message,EmployeeEntityPostgres.class);
        employeeRepositoryPostgres.save(employeeEntityPostgres);
        System.out.println(employeeEntityPostgres.getFirstName()+" from postgres");
    }

}
