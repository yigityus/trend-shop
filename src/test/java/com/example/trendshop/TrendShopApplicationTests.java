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
        ShoppingCart shoppingCart = getCart();
        log.info(shoppingCart.toString());
        log.info(String.valueOf(shoppingCart.getTotalCost()));
    }


    private ShoppingCart getCart() {
        Category food = new Category("food");
        Product product = new Product("pizza", 10.0, food);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(product, 3);
        return shoppingCart;
    }
}
