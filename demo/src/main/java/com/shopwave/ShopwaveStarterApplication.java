//melat, ATE/7037/14
package com.shopwave;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Product Service API",
                version = "1.0.0",
                description = "RESTful Product Catalogue — Lab 2"
        )
)
public class ShopwaveStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopwaveStarterApplication.class, args);
    }

}
