package com.example.trendshop;

import com.example.trendshop.domain.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class TrendShopApplicationTests {

    Logger log = LoggerFactory.getLogger(TrendShopApplicationTests.class);

    @Test
    void createCart() {
        ShoppingCart cart = getCart();
        log.info(cart.toString());
        log.info(String.valueOf(cart.getTotalCost()));
        assertThat(cart).isNotNull();
        assertThat(cart.getTotalCost()).isEqualTo(30.0);
    }

    ShoppingCart getCart() {
        Category food = getCategory();
        Product product = new Product("pizza", 10.0, food);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 3);
        return cart;
    }

    private Category getCategory() {
        Category food = new Category("food");
        return food;
    }

    @Test
    void campaigns() {
        Campaign campaign = getCampaign();
        ShoppingCart cart = getCart().applyCampaign(campaign);
        assertThat(cart.getCampaignDiscount()).isEqualTo(3.0);

    }

    private Campaign getCampaign() {
        Campaign campaign =
                new Campaign(getCategory(), 1, DiscountType.RATE, 10.0);
        return campaign;
    }

    private Campaign getCampaign2() {
        Campaign campaign =
                new Campaign(getCategory(), 1, DiscountType.AMOUNT, 10.0);
        return campaign;
    }

    @Test
    void coupon() {
        Coupon coupon = getCoupon();
        ShoppingCart cart = getCart().applyCoupon(coupon);
        assertThat(cart.getCouponDiscount()).isEqualTo(4.0);

    }

    private Coupon getCoupon() {
        Coupon coupon =
                new Coupon(10, 4, DiscountType.AMOUNT);
        return coupon;
    }

    @Test
    void print() {
        ShoppingCart cart = getCart();
        cart.applyCampaign(getCampaign());
        cart.applyCoupon(getCoupon());
        cart.print();
    }

    @Test
    void discounts() {
        Campaign campaign = getCampaign();
        Campaign campaign2 = getCampaign2();
        ShoppingCart cart = getCart();
        cart.applyDiscounts(campaign, campaign2);
        assertThat(cart.getCampaignDiscount()).isEqualTo(10.0);
    }

    @Test
    void functionalCampaign() {
        Campaign campaign = getCampaign();
        ShoppingCart cart = getCart();
        cart = campaign.apply(cart);
        assertThat(cart.getCampaignDiscount()).isEqualTo(3.0);
    }

    @Test
    void functionalCoupon() {
        Coupon coupon = getCoupon();
        ShoppingCart cart = getCart();
        cart = coupon.apply(cart);
        assertThat(cart.getCouponDiscount()).isEqualTo(4.0);
    }

}
