package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.dto.ProductFilterCriteria;
import com.inventorymanager.inventorymanager.dto.ProductInfoDTO;

import java.util.List;

public interface ProductService {
     List<ProductInfoDTO> getFilteredData(ProductFilterCriteria criteria);

     List<String> getAllCategories();
     void addProduct(ProductInfoDTO productInfoDTO) ;

     void updateProduct(Long id, ProductInfoDTO productInfoDTO);
     void deleteProduct(Long productId);
}
