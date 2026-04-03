package com.shopwave.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;
import com.shopwave.model.Category;
import com.shopwave.model.Product;

/**
 * Simple manual mapper between Product and DTOs.
 */
public final class ProductMapper {

    private ProductMapper() {}

    public static ProductDTO toDTO(Product p) {
        if (p == null) return null;
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .stock(p.getStock())
                .categoryId(p.getCategory() != null ? p.getCategory().getId() : null)
                .createdAt(p.getCreatedAt())
                .build();
    }

    public static List<ProductDTO> toDTOs(List<Product> products) {
        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    public static Product fromCreateRequest(CreateProductRequest req, Category category) {
        Product p = Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .stock(req.getStock())
                .category(category)
                .build();
        return p;
    }
}
