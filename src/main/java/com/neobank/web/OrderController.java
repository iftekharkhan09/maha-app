package com.neobank.web;

import com.neobank.domain.interfaces.OrderService;
import com.neobank.domain.interfaces.ProductService;
import com.neobank.dto.Price;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/checkout", consumes = "application/json", produces = "application/json")
    public Price checkout(@RequestBody List<String> productsIds){
        return orderService.checkOut (productsIds);
    }
}
