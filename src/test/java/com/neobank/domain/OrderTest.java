package com.neobank.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class OrderTest {

    @InjectMocks
    private Order order;

    @Mock
    private Product product;

    @Test
    public void shouldGetCartValueWhenNoDiscountedPriceIsAppliedScenarioOne() {
        List<OrderLine> orderLines = Arrays.asList (new OrderLine ("001", 2), new OrderLine ("002", 1),
                new OrderLine ("003", 1), new OrderLine ("004", 1));
        when(product.getDiscount("001")).thenReturn (java.util.Optional.of (new Product.Discount (3, 200)));
        when(product.getDiscount("002")).thenReturn (java.util.Optional.of (new Product.Discount (2, 120)));
        when(product.getDiscount("003")).thenReturn (Optional.empty ());
        when(product.getDiscount("004")).thenReturn (Optional.empty ());

        when(product.getUnitCost ("001")).thenReturn (100.0);
        when(product.getUnitCost ("002")).thenReturn (80.0);
        when(product.getUnitCost ("003")).thenReturn (50.0);
        when(product.getUnitCost ("004")).thenReturn (30.0);

        assertEquals(order.getCartValue (orderLines), 360.0);
    }

    @Test
    public void shouldGetCartValueWhenNoDiscountedPriceIsAppliedScenarioTwo() {
        List<OrderLine> orderLines = Arrays.asList (new OrderLine ("001", 2), new OrderLine ("002", 1),
                new OrderLine ("003", 1), new OrderLine ("004", 1));
        when(product.getDiscount("001")).thenReturn (Optional.empty ());
        when(product.getDiscount("002")).thenReturn (Optional.empty ());
        when(product.getDiscount("003")).thenReturn (Optional.empty ());
        when(product.getDiscount("004")).thenReturn (Optional.empty ());

        when(product.getUnitCost ("001")).thenReturn (200.0);
        when(product.getUnitCost ("002")).thenReturn (80.0);
        when(product.getUnitCost ("003")).thenReturn (50.0);
        when(product.getUnitCost ("004")).thenReturn (30.0);

        assertEquals(order.getCartValue (orderLines), 560.0);
    }

    @Test
    public void shouldGetCartValueWhenDiscountedPriceIsApplied() {
        List<OrderLine> orderLines = Arrays.asList (new OrderLine ("001", 5), new OrderLine ("002", 1),
                new OrderLine ("003", 1), new OrderLine ("004", 1));
        when(product.getDiscount("001")).thenReturn (java.util.Optional.of (new Product.Discount (2, 200)));
        when(product.getDiscount("002")).thenReturn (java.util.Optional.of (new Product.Discount (2, 120)));
        when(product.getDiscount("003")).thenReturn (Optional.empty ());
        when(product.getDiscount("004")).thenReturn (Optional.empty ());

        when(product.getUnitCost ("001")).thenReturn (200.0);
        when(product.getUnitCost ("002")).thenReturn (80.0);
        when(product.getUnitCost ("003")).thenReturn (50.0);
        when(product.getUnitCost ("004")).thenReturn (30.0);

        assertEquals(order.getCartValue (orderLines), 760.0);
    }
}
