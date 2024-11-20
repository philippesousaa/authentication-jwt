package com.authentication.authentication.service.product;

import com.authentication.authentication.domain.product.Product;
import com.authentication.authentication.domain.product.ProductDTO;

import java.util.List;

public interface ProductService {

    Product saveProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();

}
