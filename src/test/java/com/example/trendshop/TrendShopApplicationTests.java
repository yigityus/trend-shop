package com.example.trendshop;

import com.example.trendshop.domain.ShoppingCart;
import com.example.trendshop.domain.Category;
import com.example.trendshop.domain.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TrendShopApplicationTests {

    Logger log = LoggerFactory.getLogger(TrendShopApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void createCart () {
        ShoppingCart cart = getCart();
        log.info(cart.toString());
        log.info(String.valueOf(cart.getTotalCost()));
    }


    private ShoppingCart getCart() {
        Category food = new Category("food");
        Product product = new Product("pizza", 10.0, food);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 3);
        return cart;
    }
}
