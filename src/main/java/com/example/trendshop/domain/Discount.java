package com.example.trendshop.domain;

import java.util.function.Function;

public interface Discount {
    double getDiscount(ShoppingCart cart);
}
