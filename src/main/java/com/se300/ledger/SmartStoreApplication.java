package com.se300.ledger;

import com.se300.ledger.model.*;
import com.se300.ledger.service.LedgerAPI;
import com.se300.ledger.service.StoreModelAPI;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * Initial Data Setup
 *
 * @author Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2023-10-11
 */
@SpringBootApplication
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "basicAuth", scheme = "basic")
@EntityScan(basePackages = { "com.se300.ledger.model" })
public class SmartStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(StoreModelAPI storeService, LedgerAPI ledger) {
        return args -> {

            storeService.provisionStore(1L, "75 Forbes", "My First Store", null);

            Customer customer = storeService.provisionCustomer(1L, "Sergey", "Sundukovskiy",
                    CustomerType.guest, "ssunduko@gmail.com", "75 Forbes", null);

            Basket basket = storeService.provisionBasket(1L, null);

            storeService.assignCustomerBasket(customer.getId(), basket.getId(), null);

            // Instantiate the Ledger
            Account storeAccount = ledger.createAccount("1", 500);
            Account customerAccount = ledger.createAccount("2", 0);
            Transaction transaction = new Transaction("1", 100, 10, "Initial Transaction", storeAccount,
                    customerAccount);
            ledger.processTransaction(transaction);
        };
    }

}