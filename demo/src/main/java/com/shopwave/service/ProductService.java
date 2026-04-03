//melat, ATE/7037/14
package com.shopwave.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;
import com.shopwave.exception.ProductNotFoundException;
import com.shopwave.mapper.ProductMapper;
import com.shopwave.model.Category;
import com.shopwave.model.Product;
import com.shopwave.repository.CategoryRepository;
import com.shopwave.repository.ProductRepository;

import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO createProduct(CreateProductRequest request) {
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        }
        Product p = ProductMapper.fromCreateRequest(request, category);
        Product saved = productRepository.save(p);
        return ProductMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toDTO(p);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> searchProducts(String keyword, BigDecimal maxPrice) {
        if ((keyword == null || keyword.isBlank()) && maxPrice == null) {
            return productRepository.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
        }

        if (keyword != null && !keyword.isBlank() && maxPrice != null) {
            // Find by name then filter by price
            return productRepository.findByNameContainingIgnoreCase(keyword).stream()
                    .filter(p -> p.getPrice() != null && p.getPrice().compareTo(maxPrice) <= 0)
                    .map(ProductMapper::toDTO)
                    .collect(Collectors.toList());
        }

        if (keyword != null && !keyword.isBlank()) {
            return productRepository.findByNameContainingIgnoreCase(keyword).stream().map(ProductMapper::toDTO)
                    .collect(Collectors.toList());
        }

        // only maxPrice
        return productRepository.findByPriceLessThanEqual(maxPrice).stream().map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO updateStock(Long id, int delta) {
        Product p = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        int newStock = (p.getStock() == null ? 0 : p.getStock()) + delta;
        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot go negative");
        }
        p.setStock(newStock);
        Product saved = productRepository.save(p);
        return ProductMapper.toDTO(saved);
    }
}
