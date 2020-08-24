package com.example.trendshop.domain;

import com.example.trendshop.calculate.DeliveryCostCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {

    Logger log = LoggerFactory.getLogger(ShoppingCart.class);

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
        return getTotalCost() - getTotalDiscount();
    }

    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);
    }

    public void print() {
        Map<Category, List<Product>> cartItemsMap = getCartItems()
                .stream()
                .map(CartItem::getProduct)
                .collect(Collectors.groupingBy(Product::getCategory));

        for (Category category : cartItemsMap.keySet()) {


            List<Product> products = cartItemsMap.get(category);

            for (Product product : products) {

                int quantity = getCartItems()
                        .stream()
                        .filter(cartItem -> cartItem.getProduct().equals(product))
                        .map(CartItem::getQuantity).findFirst()
                        .get();

                double totalPrice = quantity * product.getPrice();

                log.info("{} {} {} {} {} {}",
                        category.getTitle(),
                        product.getTitle(),
                        quantity,
                        product.getPrice(),
                        totalPrice,
                        getTotalDiscount());

                log.info("{} {}", getTotalAmountAfterDiscounts(), getDeliveryCost());
            }

        }

    }

    public ShoppingCart applyDiscounts(Discount... discounts) {
        double discountAmount = 0;
        Discount discount = null;
        for (int i = 0; i < discounts.length; i++) {
            double discountTotal = discounts[i].getDiscount(this);
            if (discountAmount < discountTotal) {
                discount = discounts[i];
                discountAmount = discountTotal;
            }
        }

        return applyCampaign(discount);

    }

    public ShoppingCart applyCampaign(Discount discount) {
        setCampaignDiscount(discount.getDiscount(this));
        return this;
    }

    public ShoppingCart applyCoupon(Discount discount) {
        setCouponDiscount(discount.getDiscount(this));
        return this;
    }

    private double getTotalDiscount() {
        return getCampaignDiscount() + getCouponDiscount();
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
