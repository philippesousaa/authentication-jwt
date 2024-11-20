package com.authentication.authentication.service.product;

import com.authentication.authentication.domain.product.Product;
import com.authentication.authentication.domain.product.ProductDTO;
import com.authentication.authentication.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product saveProduct(ProductDTO productDTO) {
        try {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setRating(productDTO.getRating());

            return productRepository.save(product);
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product
                        -> new ProductDTO(
                        Optional.ofNullable(product.getName()).orElse(""), // valor padrão se for null
                        Optional.ofNullable(product.getDescription()).orElse(""),
                        Optional.ofNullable(product.getPrice()).orElse("0.0"), // valor padrão para o preço
                        Optional.ofNullable(product.getStock()).orElse("0"), // valor padrão para o estoque
                        Optional.ofNullable(product.getRating()).orElse("0") // valor padrão para a avaliação
                ))
                .collect(Collectors.toList());
    }
}
