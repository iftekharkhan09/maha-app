package com.neobank.domain;

import com.neobank.adapters.serviceimpl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith (MockitoJUnitRunner.class)
public class ProductTest {

    @InjectMocks
    private Product product;

    @Mock
    private ProductServiceImpl productService;

    @Test
    public void shouldCallSaveProductOneTime(){
        Product.DiscountPrice discountedPrice = new Product.DiscountPrice(3, 200.0);
        product.addProduct (new Product ("001", "Rolex", 100, discountedPrice));
        verify(productService, times(1)).save (new Product ("001", "Rolex", 100, discountedPrice));
    }

    @Test
    public void shouldCallSaveProductThreeTimes(){
        Product.DiscountPrice discountedPrice = new Product.DiscountPrice(3, 200.0);
        product.addProduct (new Product ("001", "Rolex", 100, null));
        product.addProduct (new Product ("001", "Casio", 200, discountedPrice));
        product.addProduct (new Product ("001", "Swatch", 300, null));
        verify(productService, times(1)).save (new Product ("001", "Rolex", 100, null));
    }
}
