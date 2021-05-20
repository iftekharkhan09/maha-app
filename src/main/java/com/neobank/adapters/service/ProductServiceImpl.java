package com.neobank.adapters.service;

import com.neobank.domain.Product;
import com.neobank.domain.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    List<Product> productList = new ArrayList<> ();

    @Override
    public void save (Product product) { productList.add (product); }

    @Override
    public List<Product> getAllProducts () { return loadProducts(); }

    @Override
    public Product findById (String id) {
        List<Product> products = getAllProducts ().stream ().filter (product -> product.getId ().equals (id)).collect(Collectors.toList());
        if(products.size () != 1){
            throw new IllegalStateException ();
        }
        return products.get (0);
    }

    private List<Product> loadProducts(){
        Product.Discount rolexDiscountedPrice = new Product.Discount (3, 200.0);
        Product.Discount michealCorsDiscountedPrice = new Product.Discount (2, 120);
        List<Product> products = new ArrayList<> ();
        products.add  (new Product ("001", "Rolex", 100, rolexDiscountedPrice));
        products.add  (new Product ("002", "Michael Kors", 80, michealCorsDiscountedPrice));
        products.add  (new Product ("003", "Swatch", 50, null));
        products.add  (new Product ("004", "Casio", 30, null));
        return products;
    }
}
