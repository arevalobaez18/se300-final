package com.se300.ledger;

import com.se300.ledger.model.Basket;
import com.se300.ledger.model.CustomerType;
import com.se300.ledger.service.StoreModelAPI;
import com.se300.ledger.model.Customer;
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
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2023-10-11
 */
@SpringBootApplication
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
@EntityScan(basePackages = {"com.se300.ledger.model"})
public class SmartStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(StoreModelAPI storeService) {
        return args -> {

            storeService.provisionStore(1L, "75 Forbes", "My First Store", null);

            Customer customer = storeService.provisionCustomer(1L, "Sergey", "Sundukovskiy",
                    CustomerType.guest, "ssunduko@gmail.com", "75 Forbes", null );

            Basket basket  = storeService.provisionBasket(1L, null);

            storeService.assignCustomerBasket(customer.getId(), basket.getId(), null);

            //TODO: Implement Ledger Instantiation, Account and Transaction Persistence
        };
    }

}
