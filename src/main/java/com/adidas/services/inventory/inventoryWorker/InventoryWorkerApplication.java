package com.adidas.services.inventory.inventoryWorker;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableBatchProcessing
public class InventoryWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryWorkerApplication.class, args);
	}

}
