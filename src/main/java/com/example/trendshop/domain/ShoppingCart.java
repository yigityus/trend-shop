package com.example.trendshop.domain;

import com.example.trendshop.calculate.DeliveryCostCalculator;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public static final double costPerDelivery = 1;
    public static final double costPerProduct = 1;
    public static final double fixedCost = 2.99;

    DeliveryCostCalculator deliveryCostCalculator =
            new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);

    private List<CartItem> cartItems = new ArrayList<>();
    private double campaignDiscount;
    private double couponDiscount;

    public void addItem(Product product, int quantity) {
        cartItems.add(CartItem.of(product, quantity));
    }

    public double getTotalCost() {
        return cartItems
                .stream()
                .map(CartItem::getTotalCost)
                .reduce(0d, (c, c2) -> c + c2);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalAmountAfterDiscounts() {
        return 0d;
    }

    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);
    }

    public void print() {

    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }
}
