package com.craftdemo.inventorymanager.service;

import com.craftdemo.inventorymanager.dto.ProductFilterCriteria;
import com.craftdemo.inventorymanager.dto.ProductInfoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
     public Page<ProductInfoDTO> getFilteredData(ProductFilterCriteria criteria, String orderBy, String sortOrder, int page, int size);

     void addProduct(ProductInfoDTO productInfoDTO) ;

     void updateProduct(Long id, ProductInfoDTO productInfoDTO);
     void deleteProduct(Long productId);
}
