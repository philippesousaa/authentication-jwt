package com.authentication.authentication.controller.product;

import com.authentication.authentication.domain.product.Product;
import com.authentication.authentication.domain.product.ProductDTO;
import com.authentication.authentication.service.product.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }
}
