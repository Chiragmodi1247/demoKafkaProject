package com.training.majorkafkaproject.util.readthreads;

import com.training.majorkafkaproject.service.Producer;
import com.training.majorkafkaproject.util.handlers.XMLHandler;

public class XMLReadThread extends Thread {

    private final Producer producer ;

    public XMLReadThread(Producer producer)
    {
        this.producer = producer;
    }
    @Override
    public void run() {
        XMLHandler xmlobj = new XMLHandler(producer);
        try {
            xmlobj.read();
        } catch (Exception e) {

        }
    }
}
