package com.product.service;

import com.product.Repository.ProductRepository;
import com.product.entity.Product;
import com.product.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductsById(int id) {
        return productRepository.findById(id).get();
    }


    // for product list
    public List<Product> getProductsByUser(User user) {
        return productRepository.findByUser(user);
    }
    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        Product product = productRepository.findById(id).get();
        if(product!=null){
            productRepository.delete(product);
            return true;
        }
        return false;
    }

}
