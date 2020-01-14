package com.training.demoKafkaProject.repository;

import com.training.demoKafkaProject.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntity,Integer> {
}
