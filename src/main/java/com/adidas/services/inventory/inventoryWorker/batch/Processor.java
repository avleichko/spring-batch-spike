package com.adidas.services.inventory.inventoryWorker.batch;

import com.adidas.services.inventory.inventoryWorker.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<User, User> {
    @Override
    public User process(User item) throws Exception {
        return item;
    }
}
