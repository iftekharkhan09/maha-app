package com.neobank.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Price {
    private double price;

    public Price () { }

    public Price (double price) {
        this.price = price;
    }

    public double getPrice () { return price; }

    public void setPrice (double price) { this.price = price; }
}
