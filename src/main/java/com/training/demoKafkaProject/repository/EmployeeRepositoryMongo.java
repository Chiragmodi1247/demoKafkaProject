package com.training.demoKafkaProject.repository;

import com.training.demoKafkaProject.entity.EmployeeEntityMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryMongo extends MongoRepository<EmployeeEntityMongo,String> {

}
