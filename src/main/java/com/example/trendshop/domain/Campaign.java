package com.example.trendshop.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Campaign implements Discount {

    // Campaigns are applicable to a product category

    private Category category;
    private int minProductCount;
    private DiscountType discountType;
    private double discountAmount;

    public Campaign(Category category, int minProductCount,
                    DiscountType discountType, double discountAmount) {
        this.category = category;
        this.minProductCount = minProductCount;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
    }

    public double getDiscount(ShoppingCart shoppingCart) {
        List<Product> cartItemsMap = shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .filter(product -> product.getCategory().equals(getCategory()))
                .collect(Collectors.toList());

        if (cartItemsMap.size() >= getMinProductCount()) {
            if (DiscountType.AMOUNT == getDiscountType()) {
                return getDiscountAmount();
            } else {

                double totalCostOfCategory = shoppingCart.getCartItems()
                        .stream()
                        .filter(cartItem -> cartItem.getProduct().getCategory().equals(getCategory()))
                        .map(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice())
                        .reduce((c, c2) -> c + c2).get();

                return totalCostOfCategory * (getDiscountAmount() / 100);
            }
        }

        return 0d;
    }

    public Category getCategory() {
        return category;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public int getMinProductCount() {
        return minProductCount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public ShoppingCart apply(ShoppingCart cart) {
        return cart.applyCampaign(this);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "category=" + category +
                ", minProductCount=" + minProductCount +
                ", discountType=" + discountType +
                ", discountAmount=" + discountAmount +
                '}';
    }
}
