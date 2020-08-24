package com.example.trendshop;

import com.example.trendshop.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class TrendShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrendShopApplication.class, args);
    }

    Log log = LogFactory.getLog(TrendShopApplication.class);

    @Bean
    ApplicationRunner runner() {
        return args -> {

            Category car = new Category("cars");
            Category mazda = new Category("mazda", Optional.of(car));

            Product cx3 = new Product("mazda cx-3", 1000, mazda);
            ShoppingCart cart = new ShoppingCart();
            cart.addItem(cx3, 2);

            Product cx5 = new Product("mazda cx-5", 4000, mazda);
            cart.addItem(cx5, 1);


            Campaign campaign = new Campaign(mazda, 2, DiscountType.RATE, 10);
            campaign.apply(cart);

            cart.print();


        };
    }
}
