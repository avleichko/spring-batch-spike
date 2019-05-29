package com.adidas.services.inventory.inventoryWorker.batch;

import com.adidas.services.inventory.inventoryWorker.model.User;
import com.adidas.services.inventory.inventoryWorker.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbWriter implements ItemWriter<User> {

    @Autowired
    UserRepository userRepository;
    @Override
    public void write(List<? extends User> items) throws Exception {
        userRepository.saveAll(items);
    }
}
