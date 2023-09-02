package com.craftdemo.inventorymanager.controller;

import com.craftdemo.inventorymanager.dto.ProductFilterCriteria;
import com.craftdemo.inventorymanager.dto.ProductInfoDTO;
import com.craftdemo.inventorymanager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductInfoDTO> getFilteredProducts(@Valid @ModelAttribute ProductFilterCriteria criteria,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size,
                                                    @RequestParam(defaultValue = "shelf.isPrime") String orderBy,
                                                     @RequestParam(defaultValue = "dsc") String sortOrder){
        return productService.getFilteredData( criteria, orderBy,  sortOrder,  page,  size);
    }
    @PostMapping
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductInfoDTO productInfoDTO ){
        productService.addProduct(productInfoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@Valid @PathVariable Long id, @Valid @RequestBody ProductInfoDTO productInfoDTO) {
        productService.updateProduct(id, productInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
