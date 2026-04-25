package com.shopwave.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(CreateProductRequest request);
    Page<ProductDTO> getAllProducts(Pageable pageable);
    ProductDTO getProductById(Long id);
    List<ProductDTO> searchProducts(String keyword, BigDecimal maxPrice);
    ProductDTO updateStock(Long id, int delta);
    ProductDTO updateProduct(Long id, CreateProductRequest request);
    void deleteProduct(Long id);
}
