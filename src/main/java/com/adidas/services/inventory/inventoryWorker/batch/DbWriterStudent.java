package com.adidas.services.inventory.inventoryWorker.batch;

import com.adidas.services.inventory.inventoryWorker.model.Student;
import com.adidas.services.inventory.inventoryWorker.model.User;
import com.adidas.services.inventory.inventoryWorker.repository.StudentRepository;
import com.adidas.services.inventory.inventoryWorker.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbWriterStudent implements ItemWriter<Student> {

    @Autowired
    StudentRepository studentRepository;
    @Override
    public void write(List<? extends Student> items) throws Exception {
        studentRepository.saveAll(items);
    }
}
