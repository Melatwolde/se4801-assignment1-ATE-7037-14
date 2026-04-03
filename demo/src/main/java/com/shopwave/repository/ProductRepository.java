//melat, ATE/7037/14
package com.shopwave.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopwave.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByPriceLessThanEqual(BigDecimal maxPrice);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    Optional<Product> findTopByOrderByPriceDesc();
}
