package com.training.demoKafkaProject.repository;

import com.training.demoKafkaProject.entity.EmployeeEntityPostgres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryPostgres extends CrudRepository<EmployeeEntityPostgres,String> {
}
