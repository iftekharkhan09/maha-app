package com.neobank.domain;

import com.neobank.adapters.service.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith (MockitoJUnitRunner.class)
public class ProductTest {

    @InjectMocks
    private Product product;

    @Mock
    private ProductServiceImpl productService;

    @Test
    public void shouldCallSaveProductOneTime() {
        Product.Discount discountedPrice = new Product.Discount (3, 200.0);
        product.addProduct (new Product ("001", "Rolex", 100, discountedPrice));
        verify(productService, times(1)).save (new Product ("001", "Rolex", 100, discountedPrice));
    }

    @Test
    public void shouldCallSaveProductThreeTimes(){
        Product.Discount discountedPrice = new Product.Discount (3, 200.0);
        product.addProduct (new Product ("001", "Rolex", 100, null));
        product.addProduct (new Product ("001", "Casio", 200, discountedPrice));
        product.addProduct (new Product ("001", "Swatch", 300, null));
        verify(productService, times(1)).save (new Product ("001", "Rolex", 100, null));
    }

    @Test
    public void shouldReturnProductById(){
        String productId = "001";
        Product.Discount discountedPrice = new Product.Discount (4, 100.0);
        when (productService.findById (productId)).thenReturn (new Product ("001", "Rolex", 120, discountedPrice));
        assertEquals (product.getProduct (productId), new Product ("001", "Rolex", 120, discountedPrice));
    }

    @Test
    public void shouldReturnProductUnitCost(){
        String productId = "002";
        Product.Discount discountedPrice = new Product.Discount (4, 100.0);
        when (productService.findById (productId)).thenReturn (new Product ("002", "Test1", 50, discountedPrice));
        assert  (product.getUnitCost (productId) == 50);
    }

    @Test
    public void shouldReturnDiscount(){
        String productId = "003";
        Product.Discount discountedPrice = new Product.Discount (4, 200.0);
        when (productService.findById (productId)).thenReturn (new Product ("003", "Test1", 150, discountedPrice));
        assertThat (product.getProduct (productId).getDiscount ())
                .isEqualToComparingFieldByFieldRecursively (new Product.Discount (4, 200.0));
    }
}
