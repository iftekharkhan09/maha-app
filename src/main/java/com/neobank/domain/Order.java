package com.neobank.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Order {
    private int orderId;

    private final Product product;

    public Order (Product product) {
        this.product = product;
    }

    public double getCartValue(List<OrderLine> orderLines) {
        return orderLines.stream().map (OrderLine -> {
            final int discountedChunk;
            double discountedPrice = 0.0;
            int discountedQuantity = 0;
            Optional<Product.Discount> discount = product.getDiscount (OrderLine.getProductId ());
            if(discount.isPresent ()){
                discountedChunk = OrderLine.getQuantity () / discount.get ().getQuantity ();
                discountedPrice = discountedChunk * discount.get().getDiscountedPrice ();
                discountedQuantity = discountedChunk * discount.get ().getQuantity ();
            }
            int nonDiscountedQuantity = OrderLine.getQuantity () - discountedQuantity;
            double nonDiscountedPrice = nonDiscountedQuantity * product.getUnitCost (OrderLine.getProductId ());
            return(discountedPrice + nonDiscountedPrice);
        }).reduce (0.0, Double::sum);
    }
}
