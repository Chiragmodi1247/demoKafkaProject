package com.training.majorkafkaproject.repository;

import com.training.majorkafkaproject.entity.Employee;

public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Employee, String> {
}
