package com.neobank.domain.interfaces;

import com.neobank.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void save(Product product);
    List<Product> getAllProducts();
    Product findById (String id);
}
