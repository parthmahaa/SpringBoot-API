package com.example.demo1.repo;

import com.example.demo1.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    // All the database operations will be handled by JPA repository
    // No need to write any code here

    boolean existsByName(String value);
}
