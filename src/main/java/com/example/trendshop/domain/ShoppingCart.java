package com.example.trendshop.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartItems = new ArrayList<>();
    private double campaignDiscountAmount;
    private double couponDiscountAmount;

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

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }
}
