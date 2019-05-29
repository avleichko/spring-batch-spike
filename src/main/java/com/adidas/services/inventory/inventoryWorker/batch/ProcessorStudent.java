package com.adidas.services.inventory.inventoryWorker.batch;

import com.adidas.services.inventory.inventoryWorker.model.Student;
import com.adidas.services.inventory.inventoryWorker.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorStudent implements ItemProcessor<Student, Student> {
    @Override
    public Student process(Student item) throws Exception {
        return item;
    }
}
