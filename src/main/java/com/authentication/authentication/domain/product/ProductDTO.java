package com.authentication.authentication.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String name;
    private String description;
    private String price;
    private String stock;
    private String rating;
}

