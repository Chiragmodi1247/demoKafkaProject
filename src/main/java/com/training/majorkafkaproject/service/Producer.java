package com.training.majorkafkaproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.majorkafkaproject.entity.Employee;
import com.training.majorkafkaproject.repository.EmployeeRepositoryMongo;
import com.training.majorkafkaproject.repository.EmployeeRepositoryPostgres;
import com.training.majorkafkaproject.repository.MyCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "employeekafka";

    private int count=0;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Employee employee) throws JsonProcessingException {
//        for(int i=0;i<300;i++) {
//            Employee employee = MyCollection.get();
            ObjectMapper ow = new ObjectMapper();
            String json = ow.writeValueAsString(employee);
            System.out.println("Send object: "+json);
            this.kafkaTemplate.send(TOPIC,json);
//        }
    }
}