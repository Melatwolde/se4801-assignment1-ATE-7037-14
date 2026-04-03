package com.shopwave.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.shopwave.model.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameContainingIgnoreCase_shouldReturnMatching() {
        Product p = Product.builder().name("Blue Phone").description("x").price(BigDecimal.TEN).stock(3).build();
        productRepository.save(p);

        var results = productRepository.findByNameContainingIgnoreCase("phone");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getName()).isEqualTo("Blue Phone");
    }
}
