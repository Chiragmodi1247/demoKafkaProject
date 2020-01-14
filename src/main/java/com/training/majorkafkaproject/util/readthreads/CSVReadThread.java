package com.training.majorkafkaproject.util.readthreads;

import com.training.majorkafkaproject.service.Producer;
import com.training.majorkafkaproject.util.handlers.CSVHandler;

public class CSVReadThread extends Thread{
    private final Producer producer ;

    public CSVReadThread(Producer producer)
    {
        this.producer = producer;
    }
    @Override
    public void run() {

        CSVHandler csvobj = new CSVHandler(producer);
        try {
            csvobj.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
