package com.training.majorkafkaproject.repository;

import com.training.majorkafkaproject.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface PostgresRepository extends CrudRepository<Employee, String> {
}
