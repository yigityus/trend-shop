package com.example.trendshop.domain;

public interface Cart {
    double costPerDelivery = 1;
    double costPerProduct = 1;
    double fixedCost = 2.99;

    void addItem(Product product, int quantity);

    double getDeliveryCost();

    void print();

    ShoppingCart applyCampaign(Discount discount);
    ShoppingCart applyCoupon(Discount discount);
}
