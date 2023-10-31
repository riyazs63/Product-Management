package com.product.service;

import com.product.entity.Product;
import com.product.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public Product getProductsById(int id);


    public List<Product> getProductsByUser(User user);

    public Product updateProduct(Product product);

    public boolean deleteProduct(int id);



}
