package com.shopwave.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;
import com.shopwave.model.Category;
import com.shopwave.model.Product;
import com.shopwave.repository.CategoryRepository;
import com.shopwave.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, categoryRepository);
    }

    @Test
    void createProduct_happyPath() {
        CreateProductRequest req = CreateProductRequest.builder()
                .name("Phone")
                .description("Nice")
                .price(BigDecimal.valueOf(199.99))
                .stock(10)
                .categoryId(1L)
                .build();

        Category cat = new Category(1L, "Electronics", null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(cat));

        Product saved = Product.builder()
                .id(42L)
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .stock(req.getStock())
                .category(cat)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(saved);

        ProductDTO dto = productService.createProduct(req);

        assertEquals(42L, dto.getId());
        assertEquals("Phone", dto.getName());
        assertEquals(1L, dto.getCategoryId());
    }

    @Test
    void createProduct_categoryNotFound_throws() {
        CreateProductRequest req = CreateProductRequest.builder()
                .name("Phone")
                .description("Nice")
                .price(BigDecimal.valueOf(199.99))
                .stock(10)
                .categoryId(99L)
                .build();

        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(req));
    }
}
