package com.training.majorkafkaproject.util.handlers;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import com.training.majorkafkaproject.entity.Employee;
import com.training.majorkafkaproject.repository.MyCollection;
import com.training.majorkafkaproject.service.Producer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONHandler implements MyFileHandler {
    private final Producer producer;
   public JSONHandler(Producer producer) {
        this.producer = producer;
    }

    public void read() throws Exception
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("/Users/manasarora/Downloads/kafkaproject/src/main/java/com/training/kafkaproject/inputfiles/employee.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;

            for(Object jsonObject: employeeList){
                JSONObject employee =(JSONObject) jsonObject;
                String FirstName = (String) employee.get("firstName");
                String LastName = (String) employee.get("lastName");
                String DateOfBirth = (String) employee.get("dateOfBirth");
                Long Experience = (Long) employee.get("experience");
                Employee e = new Employee();
                Date date=new SimpleDateFormat("dd/MM/yy").parse(DateOfBirth);
                e.setDateOfBirth(date);
                e.setExperience(Experience);
                e.setFirstName(FirstName);
                e.setLastName(LastName);
//                System.out.println(e.toString());
//                MyCollection.add(e);
                this.producer.sendMessage(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
//
//    public void write() {
//
//        ArrayList<Employee> employees = new ArrayList<>();
//        for(int i = 0; i < 100; i++)
//            employees.add(MyCollection.get());
//
//        JSONArray employeeList = new JSONArray();
//        for(Employee i:employees) {
//            JSONObject employeeDetails = new JSONObject();
//            employeeDetails.put("firstName", i.getFirstName());
//            employeeDetails.put("lastName", i.getLastName());
//            employeeDetails.put("DateOfBirth", i.getDateOfBirth());
//            employeeDetails.put("Experience", i.getDateOfBirth());
//
//            employeeList.add(employeeDetails);
//        }
//        try (FileWriter file = new FileWriter("/Users/manasarora/Downloads/outemployee.json")) {
//            file.write(employeeList.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
