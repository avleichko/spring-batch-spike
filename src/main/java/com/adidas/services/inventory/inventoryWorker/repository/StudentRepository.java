package com.adidas.services.inventory.inventoryWorker.repository;

import com.adidas.services.inventory.inventoryWorker.model.Student;
import com.adidas.services.inventory.inventoryWorker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
