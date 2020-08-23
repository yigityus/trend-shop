package com.example.trendshop.domain;

public class Coupon implements Discount {

    private double minAmount;
    private double discountAmount;
    private DiscountType discountType;

    @Override
    public ShoppingCart apply(ShoppingCart shoppingCart) {

        return null;
    }
}
