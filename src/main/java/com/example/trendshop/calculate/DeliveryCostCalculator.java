package com.example.trendshop.calculate;

import com.example.trendshop.domain.CartItem;
import com.example.trendshop.domain.Category;
import com.example.trendshop.domain.Product;
import com.example.trendshop.domain.ShoppingCart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryCostCalculator {
    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public double calculateFor(ShoppingCart cart) {
        Map<Category, List<Product>> cartItemsMap = cart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .collect(Collectors.groupingBy(Product::getCategory));

        int numberOfDeliveries = cartItemsMap.keySet().size();

        int numberOfProducts = cart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .collect(Collectors.toList()).size();

        return  (getCostPerDelivery() * numberOfDeliveries) + (getCostPerProduct() * numberOfProducts) + getFixedCost();
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(double fixedCost) {
        this.fixedCost = fixedCost;
    }
}
