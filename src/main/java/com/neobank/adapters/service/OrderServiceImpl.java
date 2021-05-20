package com.neobank.adapters.service;

import com.neobank.domain.Order;
import com.neobank.domain.OrderLine;
import com.neobank.domain.interfaces.OrderService;
import com.neobank.dto.Price;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final Order order;

    public OrderServiceImpl (Order order) {
        this.order = order;
    }

    @Override
    public Price checkOut(List<String> productIds) {
        List<OrderLine> orderLines = getOrderLines (productIds);
        return new Price (order.getCartValue (orderLines));
    }

    private List<OrderLine> getOrderLines (List<String> productIds) {
        List<OrderLine> orderLines = new ArrayList<> ();
        Map<String, Long> productCount =
                productIds.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        productCount.forEach ((product, count) -> orderLines.add (new OrderLine (product, count.intValue ())));
        return orderLines;
    }
}
