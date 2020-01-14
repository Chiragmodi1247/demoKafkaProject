package com.training.demoKafkaProject.repository;


import com.training.demoKafkaProject.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,String> {

}
