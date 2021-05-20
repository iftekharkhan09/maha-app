package com.neobank.adapters.service;

import com.neobank.domain.Product;
import com.neobank.domain.interfaces.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void shouldAddProduct(){
        Product.Discount discountedPrice = new Product.Discount (3, 200.0);
        productService.save  (new Product ("001", "Rolex", 100, discountedPrice));
        productService.save  (new Product ("002", "Casio", 200, null));
    }

    @Test
    public void shouldCallSaveProductOneTime(){
        ProductService mock = mock(ProductService.class);
        mock.save (new Product ("001", "Rolex", 100, null));
        verify(mock, times(1)).save (new Product ("001", "Rolex", 100, null));
    }

    @Test
    public void shouldFindProductById(){
        Product.Discount rolexDiscountedPrice = new Product.Discount (3, 200.0);
        assertThat (productService.findById ("001"))
                .isEqualToComparingFieldByFieldRecursively (new Product ("001", "Rolex", 100, rolexDiscountedPrice));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionIfNoProductIsPresent(){
        productService.findById ("005");
    }
}
