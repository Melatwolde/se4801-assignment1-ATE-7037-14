//melat, ATE/7037/14
package com.shopwave.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("product not found with id: " + id);
    }

}
