package com.api.thuctaptotnghiepbackend.Request;

import java.util.List;

import com.api.thuctaptotnghiepbackend.Entity.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ProductRequest {
    private Product product;
    private List<Long> colorIds;
    private List<Long> sizeIds;
}
