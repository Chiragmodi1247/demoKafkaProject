package com.training.majorkafkaproject.controller;

import com.training.majorkafkaproject.service.Producer;
import com.training.majorkafkaproject.util.readthreads.CSVReadThread;
import com.training.majorkafkaproject.util.readthreads.JSONReadThread;
import com.training.majorkafkaproject.util.readthreads.XMLReadThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public HttpStatus sendMessageToKafkaTopic()throws Exception {
        CSVReadThread csvReadThread = new CSVReadThread(producer);
        JSONReadThread jsonReadThread = new JSONReadThread(producer);
        XMLReadThread xmlReadThread = new XMLReadThread(producer);

        jsonReadThread.start();
        xmlReadThread.start();
        csvReadThread.start();

        jsonReadThread.join();
        csvReadThread.join();
        xmlReadThread.join();

        return HttpStatus.CREATED;

    }
}