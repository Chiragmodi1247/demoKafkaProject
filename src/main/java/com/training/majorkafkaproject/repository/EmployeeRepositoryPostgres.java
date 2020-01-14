package com.training.majorkafkaproject.repository;

import com.training.majorkafkaproject.entity.EmployeeEntityPostgres;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepositoryPostgres extends CrudRepository<EmployeeEntityPostgres, String> {
}
