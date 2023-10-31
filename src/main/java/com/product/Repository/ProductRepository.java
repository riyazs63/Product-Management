package com.product.Repository;

import com.product.entity.Product;
import com.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

   public List<Product> findByUser(User user);



}
