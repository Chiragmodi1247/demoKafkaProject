package com.training.majorkafkaproject.util.handlers;

import com.training.majorkafkaproject.entity.Employee;
import com.training.majorkafkaproject.repository.MyCollection;
import com.training.majorkafkaproject.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVHandler implements MyFileHandler {
    private final Producer producer;
//
//    @Autowired
    public CSVHandler(Producer producer) {
        this.producer = producer;
    }


    public void read() throws Exception
    {
        try {
            FileReader fileReader = new FileReader("/Users/manasarora/Downloads/kafkaproject/src/main/java/com/training/kafkaproject/inputfiles/employee.csv");
            BufferedReader csvReader = new BufferedReader(fileReader);
            String line = csvReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                // System.out.println("Employee First Name=" + data[0] + ", Last Name=" + data[1] + ", Date of Birth=" + data[2] + ", Experience=" + data[3]);
                line = csvReader.readLine();
                Employee e = new Employee();
                Date date = new SimpleDateFormat("dd/MM/yy").parse(data[2]);
                Long exp = Long.parseLong(data[3]);
                e.setDateOfBirth(date);
                e.setExperience(exp);
                e.setFirstName(data[0]);
                e.setLastName(data[1]);
//                MyCollection.add(e);
                this.producer.sendMessage(e);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//    public void write()
//    {
//        ArrayList<Employee> employees = new ArrayList<>();
//        for(int i = 0; i < 100; i++)
//            employees.add(MyCollection.get());
//        try {
//
//            FileWriter csvWriter = new FileWriter("/Users/manasarora/Downloads/outemployee2.csv");
//            for(Employee i:employees)
//            {
//                csvWriter.append(i.getFirstName());
//                csvWriter.append(",");
//                csvWriter.append(i.getLastName());
//                csvWriter.append(",");
//                String strDate = (new SimpleDateFormat("dd-mm-yy")).format(i.getDateOfBirth().getTime());
//                csvWriter.append(strDate);
//                csvWriter.append(",");
//                String exp=Double.toString(i.getExperience());
//                csvWriter.append(exp);
//                csvWriter.append("\n");
//            }
//            csvWriter.close();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}