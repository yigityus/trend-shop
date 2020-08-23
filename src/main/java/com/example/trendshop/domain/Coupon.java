package com.example.trendshop.domain;

public class Coupon implements Discount {

    private double minAmount;
    private double discountAmount;
    private DiscountType discountType;

    public Coupon(double minAmount, double discountAmount, DiscountType discountType) {
        this.minAmount = minAmount;
        this.discountAmount = discountAmount;
        this.discountType = discountType;
    }

    public double getDiscount(ShoppingCart shoppingCart) {

        if (getMinAmount() < shoppingCart.getTotalCost()) {
            switch (getDiscountType()) {
                case RATE:
                    return shoppingCart.getTotalCost() * (getDiscountAmount() / 100);
                case AMOUNT:
                    return shoppingCart.getTotalCost() - getDiscountAmount();
            }
        }

        return 0d;
    }
    @Override
    public ShoppingCart apply(ShoppingCart shoppingCart) {
        return null;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
