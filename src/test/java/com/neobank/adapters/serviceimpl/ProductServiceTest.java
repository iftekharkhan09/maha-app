package com.neobank.adapters.serviceimpl;

import com.neobank.domain.Product;
import com.neobank.domain.interfaces.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void shouldAddProduct(){
        Product.DiscountPrice discountedPrice = new Product.DiscountPrice(3, 200.0);
        productService.save  (new Product ("001", "Rolex", 100, discountedPrice));
        productService.save  (new Product ("002", "Casio", 200, null));
        List<Product> allProducts = productService.getAllProducts ();
        List<Product> expectedProductList = Arrays.asList (new Product ("001", "Rolex", 100, discountedPrice),
                new Product ("002", "Casio", 200, null));
        assertArrayEquals(allProducts.toArray(), expectedProductList.toArray());
    }

    @Test
    public void shouldCallSaveProductOneTime(){
        ProductService mock = mock(ProductService.class);
        mock.save (new Product ("001", "Rolex", 100, null));
        verify(mock, times(1)).save (new Product ("001", "Rolex", 100, null));
    }
}
