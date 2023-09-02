package com.inventorymanager.inventorymanager.service;

import com.inventorymanager.inventorymanager.dto.ProductFilterCriteria;
import com.inventorymanager.inventorymanager.dto.ProductInfoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
     public Page<ProductInfoDTO> getFilteredData(ProductFilterCriteria criteria, String orderBy, String sortOrder, int page, int size);

     List<String> getAllCategories();
     void addProduct(ProductInfoDTO productInfoDTO) ;

     void updateProduct(Long id, ProductInfoDTO productInfoDTO);
     void deleteProduct(Long productId);
}
