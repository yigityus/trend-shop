package com.example.trendshop.calculate;

import com.example.trendshop.domain.ShoppingCart;

public interface DeliveryCostCalculator {
    double calculateFor(ShoppingCart cart);
}
