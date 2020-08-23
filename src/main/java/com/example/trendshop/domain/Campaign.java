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
        List<Product> collect = shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .filter(product -> product.getCategory().equals(getCategory()))
                .collect(Collectors.toList());

        if (collect.size() >= productCount) {
            if (DiscountType.AMOUNT == getDiscountType()) {
                return discountAmount;
            } else {
                double totalCostOfCategory = collect
                        .stream()
                        .map(Product::getPrice)
                        .reduce(0d, (p, p2) -> p + p2);
                return totalCostOfCategory * (discountAmount / 100);
            }
        }

        return 0d;
    }

    @Override
    public ShoppingCart apply(ShoppingCart shoppingCart) {

        return null;
    }

    public Category getCategory() {
        return category;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
