package com.training.majorkafkaproject.repository;

import com.training.majorkafkaproject.entity.Employee;

import java.util.ArrayList;

public class MyCollection {
    private static ArrayList<Employee> list = new ArrayList<>();
    private static Object lockObject = new Object();
    public static int readCounter=-1;
    private static int writeCounter=0;

    public static synchronized void add(Employee e) {
        list.add(e);
        ++writeCounter;
        readCounter++;
    }

    public static synchronized Employee get(){
        return  list.get(readCounter--);
    }
}
