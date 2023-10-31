package com.product.Repository;

import com.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String email);

    public User findByEmail(String email);
}
