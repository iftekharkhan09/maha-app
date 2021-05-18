package com.neobank.domain;

import com.neobank.domain.interfaces.ProductService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Product {
    private String id;
    private String name;
    private double unitCost;
    private DiscountPrice discountPrice;

    public static class DiscountPrice {
        private int quantity;
        private double discountedPrice;

        public DiscountPrice (int quantity, double discountedPrice) {
            this.quantity = quantity;
            this.discountedPrice = discountedPrice;
        }
    }

    public String getId () { return id; }

    private double getUnitCost () { return unitCost; }

    public DiscountPrice getDiscountPrice () { return discountPrice; }

    public Product () { }

    private ProductService productService;

    public Product (ProductService productService) {
        this.productService = productService;
    }

    public Map<String, Double> getProductPrice() {
        return productService.getAllProducts ().parallelStream ().collect(Collectors.toMap(Product::getId, Product::getUnitCost));
    }

    public Map<String, DiscountPrice> getProductDiscountQuantity () {
        return productService.getAllProducts ().parallelStream ().collect(Collectors.toMap(Product::getId, Product::getDiscountPrice));
    }

    public Product (String id, String name, double unitCost, DiscountPrice discountPrice) {
        this.id = id;
        this.name = name;
        this.unitCost = unitCost;
        this.discountPrice = discountPrice;
    }

    public void addProduct (Product product) {
        this.productService.save (product);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Product product = (Product) o;
        return Double.compare (product.unitCost, unitCost) == 0 && Objects.equals (id, product.id) && Objects.equals (name, product.name) && Objects.equals (discountPrice, product.discountPrice);
    }

    @Override
    public int hashCode () {
        return Objects.hash (id, name, unitCost, discountPrice);
    }
}
