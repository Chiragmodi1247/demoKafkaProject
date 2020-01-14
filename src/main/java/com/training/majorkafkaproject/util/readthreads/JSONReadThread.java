package com.training.majorkafkaproject.util.readthreads;

import com.training.majorkafkaproject.service.Producer;
import com.training.majorkafkaproject.util.handlers.JSONHandler;

public class JSONReadThread extends Thread {
    private final Producer producer ;

    public JSONReadThread(Producer producer)
    {
        this.producer = producer;
    }
    @Override
    public void run(){
        JSONHandler jsonobj=new JSONHandler(producer);
        try {
            jsonobj.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
