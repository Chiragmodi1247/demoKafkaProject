package com.training.majorkafkaproject.repository;

import com.training.majorkafkaproject.entity.EmployeeEntityMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryMongo extends MongoRepository<EmployeeEntityMongo, String> {

}
