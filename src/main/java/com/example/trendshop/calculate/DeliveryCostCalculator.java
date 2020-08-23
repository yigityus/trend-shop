package com.example.trendshop.calculate;

import com.example.trendshop.domain.ShoppingCart;

public class DeliveryCostCalculator {
    private ShoppingCart shoppingCart;
    private double deliveryCost;
    private double costPerDelivery;

    public ShoppingCart getCart() {
        return shoppingCart;
    }

    public void setCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }
}
