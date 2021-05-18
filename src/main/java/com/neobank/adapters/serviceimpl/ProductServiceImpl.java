package com.neobank.adapters.serviceimpl;

import com.neobank.domain.Product;
import com.neobank.domain.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    List<Product> productList = new ArrayList<> ();

    @Override
    public void save (Product product) {
       productList.add (product);
    }

    @Override
    public List<Product> getAllProducts () {
        return productList;
    }
}
