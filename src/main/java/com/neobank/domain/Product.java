package com.neobank.domain;

import com.neobank.domain.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class Product {
    private String id;
    private String name;
    private double unitCost;
    private Discount discount;

    private ProductService productService;

    public Product () { }

    @Autowired
    public Product (ProductService productService) {
        this.productService = productService;
    }

    public static class Discount {
        private int quantity;
        private double discountedPrice;

        public int getQuantity () { return quantity; }

        public double getDiscountedPrice () { return discountedPrice; }

        public Discount (int quantity, double discountedPrice) {
            this.quantity = quantity;
            this.discountedPrice = discountedPrice;
        }
    }

    public String getId () { return id; }

    private double getUnitCost () { return unitCost; }

    public Discount getDiscount () { return discount; }

    public double getUnitCost (String productId) { return getProduct (productId).getUnitCost (); }

    public Optional<Discount> getDiscount (String productId) {
        if(getProduct (productId).getDiscount () == null)
            return Optional.empty();
        return Optional.of (getProduct (productId).getDiscount ());
    }

    public Product (String id, String name, double unitCost, Discount discount) {
        this.id = id;
        this.name = name;
        this.unitCost = unitCost;
        this.discount = discount;
    }

    public void addProduct (Product product) {
        this.productService.save (product);
    }

    public Product getProduct(String id) { return this.productService.findById(id); }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Product product = (Product) o;
        return Double.compare (product.unitCost, unitCost) == 0 && Objects.equals (id, product.id) && Objects.equals (name, product.name) && Objects.equals (discount, product.discount);
    }

    @Override
    public int hashCode () {
        return Objects.hash (id, name, unitCost, discount);
    }
}
