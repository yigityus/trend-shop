package com.example.trendshop.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Campaign implements Discount {

    private Category category;
    private int productCount;
    private DiscountType discountType;
    private double discountAmount;

    public Campaign(Category category, int productCount,
                    DiscountType discountType, double discountAmount) {
        this.category = category;
        this.productCount = productCount;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
    }

    public double getDiscount(ShoppingCart shoppingCart) {
        Map<Category, List<Product>> collect = shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .collect(Collectors.groupingBy(Product::getCategory));




        for (Category category : collect.keySet()) {
            List<Product> products = collect.get(category);

        }

        return 0;
    }

    @Override
    public ShoppingCart apply(ShoppingCart shoppingCart) {

        return null;
    }

}
